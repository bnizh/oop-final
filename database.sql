DROP DATABASE IF EXISTS base;
CREATE DATABASE base;
USE base;


CREATE TABLE Users (
  userID       INT AUTO_INCREMENT NOT NULL,
  password     VARCHAR(128)       NOT NULL,
  userName     VARCHAR(128)       NOT NULL UNIQUE,
  name         VARCHAR(128),
  typeOfUser   INT                NOT NULL,
  rating       INT,
  voters       INT,
  email        VARCHAR(128) UNIQUE,
  mobileNumber VARCHAR(64),
  imageUrl     VARCHAR(256),
  confirmed    BOOLEAN DEFAULT FALSE,
  PRIMARY KEY (userID)
);

CREATE TABLE categories (
  categoryID   INT AUTO_INCREMENT NOT NULL,
  categoryName VARCHAR(128) UNIQUE,
  PRIMARY KEY (categoryID)
);


CREATE TABLE items (
  itemID       INT AUTO_INCREMENT NOT NULL,
  ItemName     VARCHAR(128),
  itemImageUrl VARCHAR(256),
  categoryID   INT                NOT NULL,
  ownerID      INT                NOT NULL,
  price        FLOAT(15, 2),
  rating       INT,
  voters       INT,
  PRIMARY KEY (itemID),
  description  VARCHAR(2048),
  UNIQUE KEY combination (ownerID, ItemName),
  FOREIGN KEY (ownerID) REFERENCES Users (userID),
  FOREIGN KEY (categoryID) REFERENCES categories (categoryID)
);

CREATE TABLE itemsComments (
  commentID     INT AUTO_INCREMENT NOT NULL,
  writerID      INT                NOT NULL,
  ownerID       INT                NOT NULL,
  comm          VARCHAR(2048),
  dateOfComment DATE,
  PRIMARY KEY (commentID),
  FOREIGN KEY (writerID) REFERENCES Users (userID),
  FOREIGN KEY (ownerID) REFERENCES items (itemID)
);

CREATE TABLE usersComments (
  commentID     INT AUTO_INCREMENT NOT NULL,
  writerID      INT                NOT NULL,
  ownerID       INT                NOT NULL,
  comm          VARCHAR(2048),
  dateOfComment TIMESTAMP,
  PRIMARY KEY (commentID),
  FOREIGN KEY (writerID) REFERENCES Users (userID),
  FOREIGN KEY (ownerID) REFERENCES Users (userID)
);

CREATE TABLE gallery (
  id      INT AUTO_INCREMENT NOT NULL,
  url     VARCHAR(128) UNIQUE,
  ownerID INT                NOT NULL,
  typeOf  INT                NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (ownerID) REFERENCES Users (userID)
);


CREATE TABLE tags (
  tagName VARCHAR(128) NOT NULL,
  tagType VARCHAR(64)  NOT NULL,
  ownerID INT          NOT NULL
);

CREATE TABLE rating (
  ID        INT AUTO_INCREMENT NOT NULL,
  writerID  INT                NOT NULL,
  ownerID   INT                NOT NULL,
  ownerType VARCHAR(64),
  rating    INT                NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (writerID) REFERENCES Users (userID),
  UNIQUE KEY combination (writerID, ownerID, ownerType)
);

CREATE TABLE transactions (
  id     VARCHAR(64)  NOT NULL unique ,
  buyerID INT                NOT NULL,
  sellerID INT               NOT NULL ,
  itemID  INT                NOT NULL,
  amount   INT                NOT NULL,
  resolved BOOLEAN DEFAULT false,
  PRIMARY KEY (id),
  FOREIGN KEY (sellerID) REFERENCES Users (userID),
  FOREIGN KEY (buyerID) REFERENCES Users (userID),
  FOREIGN KEY (itemID) REFERENCES items (itemID)
);

INSERT INTO categories (categoryName) VALUES ('Hot Meal'),
  ('Drinks'), ('Alcohol'), ('Meat'), ('Fast Food'), ('Other'), ('Desert')

select * from transactions;