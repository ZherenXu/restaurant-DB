drop table Cook; 
drop table Has; 
drop table ChefAddress; 
drop table Chef; 
drop table Consume; 
drop table Dishes; 
drop table Orders; 
drop table Provide; 
drop table Branch; 
drop table Ingredients; 
drop table Shelf; 
drop table Refrigerator; 
drop table Freezer; 
drop table Storage; 
drop table DeliveryPeople; 
drop table Category; 
drop table FoodSupplier; 

purge recyclebin; 

CREATE TABLE FoodSupplier
(
 CompanyName     CHAR(20)    PRIMARY KEY,
 Address         CHAR(40)    UNIQUE,
 ContactNumber   CHAR(10)     UNIQUE,
 Email           CHAR(40)    UNIQUE
);
 
INSERT INTO FoodSupplier 
VALUES ('ABC Inc','1234 40th Ave','7789199119','abc@gmail.com'); 
 
INSERT INTO FoodSupplier 
VALUES ('XYZ Corp', '1235 41st Ave', '6041231234', 'xyz@hotmail.com'); 
 
INSERT INTO FoodSupplier 
VALUES ('JKL Limited Co', '1236 42nd Ave', '7789981314', 'jkl@gmail.com'); 
 
INSERT INTO FoodSupplier
VALUES ('ILoveFood Company', '1237 43rd Ave', '7785201314', 'ilv@gmail.com'); 
 
INSERT INTO FoodSupplier 
VALUES ('FoodSup','1238 44th Ave','7786666666','fs@gmail.com'); 
 
CREATE TABLE Category
(
  Name            CHAR(20)    PRIMARY KEY,
  Type            CHAR(20)
);
 
INSERT INTO Category
VALUES ('Tomato','Vegetable');
 
INSERT INTO Category
VALUES ('Egg','Protein');
 
INSERT INTO Category
VALUES ('Rice Noodle','Grains');
 
INSERT INTO Category
VALUES ('Grapeseed Oil','Oil');
 
INSERT INTO Category
VALUES ('Beef','Meat');
 
CREATE TABLE DeliveryPeople(
 SIN          CHAR(9) PRIMARY KEY,
 Name             CHAR(20),
 ContactNumber    CHAR(10) UNIQUE,
 Address      CHAR(40)
);
 
INSERT INTO DeliveryPeople
VALUES('122485123','Delivery Guy',7781234321,'1234 Delivery Street');
INSERT INTO DeliveryPeople
VALUES('144235334','Guy Delivery',7783211234,'1235 Guy Street');
INSERT INTO DeliveryPeople
VALUES('213875983','James Li',7783167788,'1623 77th Ave');
INSERT INTO DeliveryPeople
VALUES('625785354','Bruce Bian',7782332333,'1320 Landsdowne St.');
INSERT INTO DeliveryPeople
VALUES('543938433','Barry Xu',6046166612,'666 BoradWay');
 
CREATE TABLE Storage(
 PosID           INTEGER PRIMARY KEY
);
 
CREATE TABLE Freezer(
 PosID           INTEGER PRIMARY KEY,
 FreezerTemp     FLOAT, 
 FOREIGN KEY (PosID) REFERENCES Storage
);

CREATE OR REPLACE TRIGGER FreezerTrigger
    BEFORE INSERT ON FREEZER
    FOR EACH ROW
    BEGIN
        INSERT INTO STORAGE VALUES ( :new.POSID);
    end;
 
INSERT INTO Freezer 
VALUES (11, -30.1); 
 
INSERT INTO Freezer 
VALUES (12, -31.0); 
 
INSERT INTO Freezer 
VALUES (13, -29.5); 

INSERT INTO Freezer 
VALUES (14, -41.2); 

INSERT INTO Freezer 
VALUES (15, -29.5); 

 
CREATE TABLE Refrigerator(
 PosID               INTEGER PRIMARY KEY,
 RefrigeratorTemp    FLOAT, 
 FOREIGN KEY (PosID) REFERENCES Storage
);

CREATE OR REPLACE TRIGGER RefTrigger
    BEFORE INSERT ON Refrigerator
    FOR EACH ROW
    BEGIN
        INSERT INTO STORAGE VALUES ( :new.POSID);
    end;
 
INSERT INTO Refrigerator
VALUES (1, 2.1);
 
INSERT INTO Refrigerator
VALUES (2, 1.9);
 
INSERT INTO Refrigerator
VALUES (3, 2.4);

INSERT INTO Refrigerator
VALUES (4, 4.0);

INSERT INTO Refrigerator
VALUES (5, 1.2);
 
CREATE TABLE Shelf(
 PosID           INTEGER PRIMARY KEY,
 RoomTemp        FLOAT, 
 FOREIGN KEY (PosID) REFERENCES Storage
);

CREATE OR REPLACE TRIGGER ShelfTrigger
    BEFORE INSERT ON SHELF
    FOR EACH ROW
    BEGIN
        INSERT INTO STORAGE VALUES ( :new.POSID);
    end;

INSERT INTO Shelf
VALUES (21, 21.4);
INSERT INTO Shelf
VALUES (22, 23.1);
INSERT INTO Shelf
VALUES (23, 25.6);
INSERT INTO Shelf
VALUES (24, 21.4);
INSERT INTO Shelf
VALUES (25, 22.4);


 
CREATE TABLE Ingredients
(
  LotNumber           CHAR(20) PRIMARY KEY,
  Name                CHAR(20),
  ProductionDate      DATE,
  Quantity            INTEGER,
  PosId               INTEGER UNIQUE,
  SIN                 CHAR(9),
  FOREIGN KEY (PosId) REFERENCES Storage,
  FOREIGN KEY (SIN)   REFERENCES DeliveryPeople   ON DELETE CASCADE,
  FOREIGN KEY (Name)  REFERENCES Category
);
 
INSERT INTO Ingredients 
VALUES('00121201231074812345','Tomato',TO_DATE('2019-09-01','YYYY-MM-DD'),1,1,'122485123');
INSERT INTO Ingredients 
VALUES('01201203102412301235','Rice Noodle',TO_DATE('2019-09-01','YYYY-MM-DD'),2,22,'144235334');
INSERT INTO Ingredients 
VALUES('21356012761034901344','Egg',TO_DATE('2019-10-02','YYYY-MM-DD'),3,3,'213875983');
INSERT INTO Ingredients 
VALUES('12351269403176134753','Grapeseed Oil', TO_DATE('2019-09-01','YYYY-MM-DD'), 4, 24,'625785354');
INSERT INTO Ingredients 
VALUES('12446346034576371342','Beef',TO_DATE('2019-09-01','YYYY-MM-DD'),5,13,'543938433');
 
CREATE TABLE Branch(
  Address     CHAR(40) PRIMARY KEY,
  Contact     CHAR(10) UNIQUE,
  ManagerName CHAR(20)
);
INSERT INTO Branch
VALUES ('1234 40th Ave','7789199119','Kevin Yu');
INSERT INTO Branch
VALUES ('1235 41st Ave','6041231234','Danica Xiao');
INSERT INTO Branch
VALUES ('1236 42nd Ave','7789981314','James Li');
 
CREATE TABLE Provide(
 CompanyName     CHAR(20)    NOT NULL,
 LotNumber       CHAR(20),
 PRIMARY KEY (CompanyName, LotNumber),
 FOREIGN KEY (CompanyName) REFERENCES FoodSupplier ON DELETE CASCADE,
 FOREIGN KEY (LotNumber) REFERENCES Ingredients
);
INSERT INTO Provide
VALUES ('ABC Inc','00121201231074812345');
INSERT INTO Provide
VALUES ('XYZ Corp','01201203102412301235');
INSERT INTO Provide
VALUES ('JKL Limited Co','21356012761034901344');
INSERT INTO Provide
VALUES ('ILoveFood Company','12351269403176134753');
INSERT INTO Provide
VALUES ('FoodSup','12446346034576371342');
 
CREATE TABLE Orders(
 OrderNumber INTEGER PRIMARY KEY,
 Time            TIMESTAMP
);
 
INSERT INTO Orders
VALUES (1234,'2019-09-01 19:01:13');
 
INSERT INTO Orders
VALUES (1155,'2019-09-01 18:30:43');
 
INSERT INTO Orders
VALUES (0012,'2019-10-02 12:30:55');
 
INSERT INTO Orders
VALUES (0004,'2019-11-30 13:04:39');
 
INSERT INTO Orders
VALUES (2384,'2020-01-31 11:38:25');
 
CREATE TABLE Dishes(
 Name                CHAR(20),
 OrderNumber         INTEGER,
 TastePreference     CHAR(40),
 CONSTRAINT  pk_dishes PRIMARY KEY (Name, OrderNumber),
 FOREIGN KEY (OrderNumber) REFERENCES Orders ON DELETE CASCADE 
);

INSERT INTO Dishes 
VALUES('Pan fried egg', 1155, NULL);
INSERT INTO Dishes 
VALUES('Chili rice noodle', 1155, 'less spicy');
INSERT INTO Dishes 
VALUES('Grilled beef tognue', 0004, Null);
INSERT INTO Dishes 
VALUES('Tomato soup', 2384, 'more sugar');
INSERT INTO Dishes 
VALUES('Tomato soup', 0012, 'less sugar');
 
 
CREATE TABLE Consume(
 DishesName      CHAR(20),
 OrderNumber     INTEGER,
 LotNumber       CHAR(20)     NOT NULL,
 Quantity        INTEGER,
 PRIMARY KEY (DishesName, OrderNumber, LotNumber),
 FOREIGN KEY (LotNumber) REFERENCES Ingredients, 
 CONSTRAINT fk_dishes FOREIGN KEY (DishesName, OrderNumber) REFERENCES Dishes(Name, OrderNumber) ON DELETE CASCADE
);
 
INSERT INTO Consume 
VALUES ('Pan fried egg', 1155, '21356012761034901344', 2); 
INSERT INTO Consume 
VALUES ('Pan fried egg', 1155, '12351269403176134753', 1); 
INSERT INTO Consume 
VALUES ('Chili rice noodle', 1155, '01201203102412301235', 2); 
INSERT INTO Consume 
VALUES ('Grilled beef tognue', 0004, '12446346034576371342', 2); 
INSERT INTO Consume 
VALUES ('Tomato soup', 0012, '00121201231074812345', 2); 
 
  
CREATE TABLE Chef(
 Name            CHAR(20),
 SIN             CHAR(9) PRIMARY KEY,
 ContactNumber   CHAR(10) UNIQUE,
 HomeAddress     CHAR(40)
);
INSERT INTO Chef
VALUES ('Superman','122985423','1234567890','3124 Delivery Street');
INSERT INTO Chef
VALUES ('Spiderman','144225344','1540173729','1235 Guy Street');
INSERT INTO Chef
VALUES ('Iron man','213605003','3473869572','1683 53rd Ave');
INSERT INTO Chef
VALUES ('Wonder Women','625785404','3834523490','220 Landsdowne St.');
INSERT INTO Chef
VALUES ('Godzilla','543938233','7831097230','776 BroadWay');

CREATE TABLE ChefAddress(
  SIN             CHAR(9) PRIMARY KEY,
  BranchAddress   CHAR(40)    NOT NULL,
  FOREIGN KEY (BranchAddress) REFERENCES Branch ON DELETE CASCADE, 
  FOREIGN KEY (SIN) REFERENCES Chef ON DELETE CASCADE 
);
 
INSERT INTO ChefAddress 
VALUES ('122985423', '1234 40th Ave'); 
INSERT INTO ChefAddress
VALUES ('144225344', '1235 41st Ave'); 
INSERT INTO ChefAddress
VALUES ('213605003', '1236 42nd Ave'); 
INSERT INTO ChefAddress
VALUES ('625785404', '1235 41st Ave'); 
INSERT INTO ChefAddress
VALUES ('543938233', '1234 40th Ave'); 

 
CREATE TABLE Has(
  Address     CHAR(40) NOT NULL,
  PosId           INTEGER,
  PRIMARY KEY (Address, PosId),
  FOREIGN KEY (Address) REFERENCES Branch ON DELETE CASCADE,
  FOREIGN KEY (PosId) REFERENCES Storage
);
 
INSERT INTO Has VALUES ('1234 40th Ave', 1); 
INSERT INTO Has VALUES ('1235 41st Ave', 2);  
INSERT INTO Has VALUES ('1234 40th Ave', 3);   
INSERT INTO Has VALUES ('1235 41st Ave', 11);  
INSERT INTO Has VALUES ('1234 40th Ave', 12);  
INSERT INTO Has VALUES ('1235 41st Ave', 13);   
INSERT INTO Has VALUES ('1234 40th Ave', 22);
INSERT INTO Has VALUES ('1236 42nd Ave', 23);  
INSERT INTO Has VALUES ('1236 42nd Ave', 24);  

 
CREATE TABLE Cook(
 SIN             CHAR(9),
 DishName        CHAR(20),
 OrderNumber     INTEGER,
 PRIMARY KEY (SIN, DishName, OrderNumber),
 FOREIGN KEY (SIN) REFERENCES Chef ON DELETE CASCADE,
 CONSTRAINT fk_dish FOREIGN KEY (DishName, OrderNumber) REFERENCES Dishes(Name, OrderNumber) ON DELETE CASCADE 
);

INSERT INTO Cook
VALUES ('122985423','Pan fried egg',1155);
INSERT INTO Cook
VALUES ('144225344','Chili rice noodle',1155);
INSERT INTO Cook
VALUES ('213605003','Grilled beef tognue',0004);
INSERT INTO Cook
VALUES ('625785404','Tomato soup', 2384);
INSERT INTO Cook
VALUES ('543938233','Tomato soup', 0012);

