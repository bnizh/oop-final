DROP database IF EXISTS base;
create database base;
use base;

SELECT * from users WHERE userID>0;
CREATE TABLE Users (
    userID int auto_increment not null ,
    password varchar(128) not null,
    userName varchar(128) not null unique,
    name varchar(128),
    typeOfUser int not null,
    rating int,
    voters int,
    email varchar(128) unique,
    mobileNumber VARCHAR(64),
    imageUrl varchar(256),
    confirmed BOOLEAN DEFAULT FALSE ,

    primary key (userID)
);

create table categories(
    categoryID int auto_increment not null,
    categoryName varchar(128) unique,
    primary key(categoryID)
);


Create table items(
	  itemID int auto_increment not null,
    ItemName varchar(128),
    itemImageUrl varchar(256),
    categoryID int not null,
    ownerID int not null,
    price float(15,2),
    rating int,
    voters int,
    primary key(itemID),
    description VARCHAR(2048),
    UNIQUE KEY combination (ownerID, ItemName),
    foreign key(ownerID) references Users(userID),
    foreign key(categoryID) references categories(categoryID)
);

create table itemsComments(
    commentID int auto_increment not null,
    writerID int not null,
    ownerID int not null,
    comm varchar (2048),
    dateOfComment date,
    primary key(commentID),
    foreign key(writerID)  references Users(userID),
    foreign key(ownerID) REFERENCES items(itemID)
);

Create table usersComments(
	  commentID int auto_increment not null,
    writerID int not null,
    ownerID int not null,
    comm varchar (2048),
    dateOfComment TIMESTAMP,
    primary key (commentID),
    foreign key(writerID)  references Users(userID),
    foreign key(ownerID)  references Users(userID)
);

create table gallery(
	id int auto_increment not null,
    url varchar (128) unique,
    ownerID int not null,
    typeOf int not null,
    primary key (id),
    foreign key(ownerID) references Users(userID)
);


create table tags(
	  tagName varchar (128) not null,
    tagType varchar(64) not null,
    ownerID int not null
);
DELETE FROM users WHERE userID<3;

SELECT *
FROM users;
CREATE TABLE rating(
    ID int AUTO_INCREMENT NOT NULL ,
    writerID int NOT NULL ,
    ownerID int NOT NULL ,
    ownerType VARCHAR(64),
    rating int NOT NULL ,
    PRIMARY KEY (ID),
    FOREIGN KEY (writerID) REFERENCES Users(userID),
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

