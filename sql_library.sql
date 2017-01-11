DROP SCHEMA library;
CREATE SCHEMA if not exists library;

CREATE USER if not exists 'library_admin'@'localhost' IDENTIFIED BY 'library_admin_pass';
GRANT ALL privileges on library.* to 'library_admin'@'localhost';



Drop table if exists  `library`.`library_users`;
CREATE TABLE `library`.`library_users` (
  `uuid` VARCHAR(80) NOT NULL,
  `name` VARCHAR(45) NOT NULL UNIQUE,
  `email` VARCHAR(45) NOT NULL UNIQUE,
  `user_type` enum('ADMIN','READER'),
  `loyalty_index` INT(2),
   `password` VARCHAR(80),
  PRIMARY KEY (`uuid`),
  UNIQUE INDEX `uuid_UNIQUE` (`uuid` ASC)); 
  
INSERT INTO `library`.`library_users` (`uuid`, `name`, `email`,`user_type`, `loyalty_index`, `password`) VALUES ('123', 'Robi','alma@fa.com', 'ADMIN', '10', '12345');
INSERT INTO `library`.`library_users` (`uuid`, `name`, `email`, `user_type`, `loyalty_index`, `password`) VALUES ('145', 'Terez','terez@msg.com', 'READER', '10', '12345');
INSERT INTO `library`.`library_users` (`uuid`, `name`,  `email`,`user_type`, `loyalty_index`, `password`) VALUES ('146', 'Joco', ' joco@msg.com','READER', '0', '12345');
 
  
   Drop table if exists  `library`.`authors`;
   CREATE TABLE `library`.`authors` (
  `uuid` VARCHAR(80) NOT NULL,
  `name` VARCHAR(45) NOT NULL UNIQUE,
  PRIMARY KEY (`uuid`),
  UNIQUE INDEX `uuid_UNIQUE` (`uuid` ASC));
  
INSERT INTO `library`.`authors` (`uuid`, `name`) VALUES ('123', 'Arany Janos');
INSERT INTO `library`.`authors` (`uuid`, `name`) VALUES ('124', 'Jokai Mor');
  
	Drop table if exists `library`.`publication_type`;
CREATE TABLE `library`.`publication_type` (
`id` INT(5) NOT NULL,
`name` VARCHAR(45) NOT NULL UNIQUE,
PRIMARY KEY (`id`));

INSERT INTO `library`.`publication_type` (`id`, `name`) VALUES ('1', 'book');
INSERT INTO `library`.`publication_type` (`id`, `name`) VALUES ('2', 'magazine');
INSERT INTO `library`.`publication_type` (`id`, `name`) VALUES ('3', 'newspaper');


 Drop table if exists  `library`.`publications`;
  CREATE TABLE `library`.`publications` (
  `uuid` VARCHAR(80) NOT NULL,
  `title` VARCHAR(45) NOT NULL UNIQUE,
  `publisher` VARCHAR(45),  
  `release_date` date,
  `nr_of_copies` INT(5),
  `copies_left` INT(5),
  `type` INT(5),
  PRIMARY KEY (`uuid`),
  FOREIGN KEY (type) 
	REFERENCES `library`.`publication_type`(id)
    ON DELETE CASCADE,
  UNIQUE INDEX `uuid_UNIQUE` (`uuid` ASC)); 
  
INSERT INTO `library`.`publications` (`uuid`, `title`, `publisher`, `release_date`, `nr_of_copies`, `copies_left`, `type`) VALUES ('5234', 'Arany Ember', 'Korvin kiado', '2010-10-5', '5', '5', '1');
INSERT INTO `library`.`publications` (`uuid`, `title`, `publisher`, `release_date`, `nr_of_copies`, `copies_left`, `type`) VALUES ('2134', 'Napi hirek', 'Hirlap', '2000-12-12', '2', '2', '3');
INSERT INTO `library`.`publications` (`uuid`, `title`, `publisher`, `release_date`, `nr_of_copies`, `copies_left`, `type`) VALUES ('1234', 'Nok lapja', 'Nok kiadoja', '2016-01-10', '10', '10', '2');

  
  Drop table if exists  `library`.`publications_authors`;
  CREATE TABLE `library`.`publications_authors` (
  `publications_uuid` VARCHAR(80) NOT NULL,
  `authors_uuid` VARCHAR(80) NOT NULL, 
PRIMARY KEY (`publications_uuid`,`authors_uuid`),
FOREIGN KEY (publications_uuid) 
	REFERENCES `library`.`publications`(uuid)
    ON DELETE CASCADE,
FOREIGN KEY (authors_uuid) 
	REFERENCES `library`.`authors`(uuid)
    ON DELETE CASCADE); 
    
INSERT INTO `library`.`publications_authors` (`publications_uuid`, `authors_uuid`) VALUES ('1234', '123');
INSERT INTO `library`.`publications_authors` (`publications_uuid`, `authors_uuid`) VALUES ('5234', '124');


Drop table if exists `library`.`publication_borrowings`;
create table `library`.`publication_borrowings`(
`publications_uuid` VARCHAR(80) NOT NULL,
`user_uuid` VARCHAR(80) NOT NULL,
`borrowing_date` DATE,
`deadline` DATE,
PRIMARY KEY (`publications_uuid`,`user_uuid`),
FOREIGN KEY (publications_uuid) 
	REFERENCES `library`.`publications`(uuid)
    ON DELETE CASCADE,
FOREIGN KEY (user_uuid) 
	REFERENCES `library`.`library_users`(uuid)
    ON DELETE CASCADE); 
    
INSERT INTO `library`.`publication_borrowings` (`publications_uuid`, `user_uuid`, `borrowing_date`, `deadline`) VALUES ('5234', '123', '2016-01-10', '2016-02-10');
INSERT INTO `library`.`publication_borrowings` (`publications_uuid`, `user_uuid`, `borrowing_date`, `deadline`) VALUES ('1234', '123', '2016-02-20', '2016-02-21');

UPDATE `library`.`publications` SET `copies_left`='4' WHERE `uuid`='5234';
UPDATE `library`.`publications` SET `copies_left`='9' WHERE `uuid`='1234';

