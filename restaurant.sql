drop table Cook; 
drop table Has; 
drop table Chef; 
drop table ChefAddress; 
drop table Consume; 
drop table Dishes; 
drop table Orders; 
drop table Provide; 
drop table Branch; 
drop table Ingredients; 
drop table Storage; 
drop table Shelf; 
drop table Refrigerator; 
drop table Freezer; 
drop table DeliveryPeople; 
drop table Category; 
drop table FoodSupplier; 

CREATE TABLE FoodSupplier
(
  CompanyName     CHAR(20)    PRIMARY KEY,
  Address         CHAR(20)    UNIQUE,
  ContactNumber   INTEGER     UNIQUE,
  Email           CHAR(40)    UNIQUE
);

CREATE TABLE Category
(
   Name            CHAR(20)    PRIMARY KEY,
   Type            CHAR(20)
);

CREATE TABLE DeliveryPeople(
  SIN          INTEGER PRIMARY KEY,
  Name             CHAR(20),
  ContactNumber    INTEGER UNIQUE,
  Address      CHAR(50)
);

CREATE TABLE Freezer(
  PosID           INTEGER PRIMARY KEY,
  FreezerTemp     FLOAT
); 

CREATE TABLE Refrigerator(
  PosID               INTEGER PRIMARY KEY,
  RefrigeratorTemp    FLOAT
); 

CREATE TABLE Shelf(
  PosID           INTEGER PRIMARY KEY,
  RoomTemp        FLOAT
); 

CREATE TABLE Storage(
  PosID           INTEGER PRIMARY KEY
); 

CREATE TABLE Ingredients
(
   LotNumber           INTEGER PRIMARY KEY,
   Name                CHAR(20),
   ProductionDate      DATE,
   Quantity            INTEGER,
   PosId               INTEGER NOT NULL UNIQUE,
   SIN                 INTEGER NOT NULL,
   FOREIGN KEY (PosId) REFERENCES Storage      ON DELETE CASCADE,
   FOREIGN KEY (SIN)   REFERENCES DeliveryPeople   ON DELETE CASCADE,
   FOREIGN KEY (Name)  REFERENCES Category ON DELETE SET NULL
);

CREATE TABLE Branch(
   Address     CHAR(50) PRIMARY KEY,
   Contact     INTEGER UNIQUE,
   ManagerName CHAR(20)
);

CREATE TABLE Provide(
  CompanyName     CHAR(20)    NOT NULL,
  LotNumber       INTEGER,
  PRIMARY KEY (CompanyName, LotNumber),
  FOREIGN KEY (CompanyName) REFERENCES Branch,
  FOREIGN KEY (LotNumber)   REFERENCES Ingredients
);

CREATE TABLE Orders(
  OrderNumber INTEGER PRIMARY KEY,
  Time            TIMESTAMP
); 

CREATE TABLE Dishes(
  Name                CHAR(20),
  OrderNumber         INTEGER,
  TastePreference     CHAR(40),
  PRIMARY KEY (Name, OrderNumber),
  FOREIGN KEY (OrderNumber) REFERENCES Orders
); 
 
CREATE TABLE Consume(
  DishesName      CHAR(20),
  OrderNumber     INTEGER,
  LotNumber       INTEGER     NOT NULL,
  Quantity        INTEGER,
  PRIMARY KEY (DishesName, OrderNumber, LotNumber),
  FOREIGN KEY (DishesName, OrderNumber) REFERENCES Dishes,
  FOREIGN KEY (LotNumber) REFERENCES Ingredients
); 

CREATE TABLE ChefAddress(
   HomeAddress CHAR(50)    PRIMARY KEY,
   BranchAddress   CHAR(50)    NOT NULL,
   FOREIGN KEY (BranchAddress) REFERENCES Branch ON DELETE CASCADE
);

CREATE TABLE Chef(
  Name            CHAR(20),
  SIN             INTEGER PRIMARY KEY,
  ContactNumber   INTEGER UNIQUE,
  HomeAddress     CHAR(50),
  FOREIGN KEY (HomeAddress) REFERENCES ChefAddress ON DELETE CASCADE
);
 
CREATE TABLE Has(
   Address     CHAR(50) NOT NULL,
   PosId           INTEGER,
   PRIMARY KEY (Address, PosId), 
   FOREIGN KEY (Address) REFERENCES Branch,
   FOREIGN KEY (PosId) REFERENCES Storage
);

CREATE TABLE Cook(
  SIN             INTEGER NOT NULL,
  DishName        CHAR(30),
  OrderNumber     INTEGER,
  PRIMARY KEY (SIN, DishName, OrderNumber),
  FOREIGN KEY (SIN) REFERENCES Chef,
  FOREIGN KEY (DishName, OrderNumber) REFERENCES Dishes
);
 

