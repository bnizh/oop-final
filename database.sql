DROP database IF EXISTS base;
create database base;
use base;

DROP TABLE IF EXISTS Users; 
DROP TABLE IF EXISTS items; 
DROP TABLE IF EXISTS usersComments; 
DROP TABLE IF EXISTS gallery;
DROP TABLE IF EXISTS tags;
 

CREATE TABLE Users (
    userID int auto_increment not null,
    password varchar(128) not null,
    userName varchar(128) not null,
    name varchar(128),
    typeOfUser int not null,
    rating int,
    voters int,
    phone int,
    imageUrl varchar(256),
    primary key (userID)
);

Create table items(
	itemID int auto_increment not null,
    ItemName varchar(128),
    itemImageUrl varchar(256),
    categoryID int not null,
    ownerID int not null,
    rating int,
    voters int,
    primary key(itemID),
    foreign key(ownerID) references Users(userID)
);

Create table usersComments(
	commentID int auto_increment not null,
    writerID int not null,
    ownerID int not null,
    comm varchar (2048),
    dateOfComment date,
    primary key (commentID),
    foreign key(writerID)  references Users(userID),
    foreign key(ownerID)  references Users(userID)
);

create table gallery(
	id int auto_increment not null,
    url varchar (256),
    ownerID int not null,
    typeOf int not null,
    primary key (id),
    foreign key(ownerID) references Users(userID)
);


create table tags(
	tagName varchar (128) not null,
    tagType int not null,
    ownerID int not null
);