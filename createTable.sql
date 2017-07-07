DROP TABLE person;
DROP TABLE translation_map;
CREATE TABLE person (
--id int NOT NULL, 
usr_name varchar(45) NOT NULL,
password varchar(45) NOT NULL,
actual_name varchar(45) NOT NULL,
--CONSTRAINT unique_usr UNIQUE (usr_name),
CONSTRAINT PK_PERSON PRIMARY KEY (usr_name)
) ;--DEFAULT CHARACTER SET=utf8;

CREATE TABLE translation_map (
--id int NOT NULL, 
name_lat varchar(45) NOT NULL,
name_translation varchar(45) NOT NULL,
locale varchar(45) NOT NULL,
--CONSTRAINT unique_name UNIQUE (name_lat, locale),
CONSTRAINT PK_TRANSLATION PRIMARY KEY (name_lat, locale)
) ;--DEFAULT CHARACTER SET=utf8;
