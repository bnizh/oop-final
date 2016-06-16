DROP DATABASE IF EXISTS base;
CREATE DATABASE base;
USE base;

DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS items;
DROP TABLE IF EXISTS usersComments;
DROP TABLE IF EXISTS gallery;
DROP TABLE IF EXISTS tags;
DROP TABLE IF EXISTS categories;


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
  price        INT,
  rating       INT,
  voters       INT,
  PRIMARY KEY (itemID),
  FOREIGN KEY (ownerID) REFERENCES Users (userID),
  FOREIGN KEY (categoryID) REFERENCES categories (categoryID)
);

CREATE TABLE itemsComments (
  commentID     INT AUTO_INCREMENT NOT NULL,
  writerID      INT                NOT NULL,
  ownerID       INT                NOT NULL,
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
  dateOfComment DATE,
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
  tagType INT          NOT NULL,
  ownerID INT          NOT NULL
);
