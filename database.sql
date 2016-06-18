DROP database IF EXISTS base;
create database base;
use base;

DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS items;
DROP TABLE IF EXISTS usersComments;
DROP TABLE IF EXISTS gallery;
DROP TABLE IF EXISTS tags;
DROP TABLE IF EXISTS categories;


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
    primary key (userID)
);

create table categories(
    categoryID int auto_increment not null,
    categoryName varchar(128) unique,
    primary key(categoryID)
);
INSERT INTO categories (categoryName)
VALUES ('Sandvich');
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
 SELECT *FROM  users;
create table itemsComments(
    commentID int auto_increment not null,
    writerID int not null,
    ownerID int not null,
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