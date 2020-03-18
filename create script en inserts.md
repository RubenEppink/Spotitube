```mysql
CREATE TABLE `spotitube`.`track` (
  `trackid` INT NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `performer` VARCHAR(45) NOT NULL,
  `duration` VARCHAR(45) NOT NULL,
  `album` VARCHAR(45) NOT NULL,
  `playcount` INT NOT NULL,
  `publicationdate` DATE NULL,
  `description` VARCHAR(45) NULL,
  `offline_available` TINYINT(1) NULL,
  PRIMARY KEY (`trackid`));
  
  CREATE TABLE `spotitube`.`login` (
  `user_login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user_login`));

CREATE TABLE `spotitube`.`user` (
  `user_login` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `token` VARCHAR(45) NULL,
  PRIMARY KEY (`user_login`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  UNIQUE INDEX `usercol_UNIQUE` (`token` ASC) VISIBLE);
  
  CREATE TABLE `spotitube`.`playlist` (
  `playlist_id` `playlist_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `owner` TINYINT(1) NOT NULL,
  PRIMARY KEY (`playlist_id`));
  
  CREATE TABLE `spotitube`.`tracks_in_playlist` (
  `track_id` INT NOT NULL,
  `playlist_id` INT NOT NULL,
  PRIMARY KEY (`track_id`, `playlist_id`))
  
  
ALTER TABLE `spotitube`.`tracks_in_playlist` 
ADD CONSTRAINT `fk_track_id`
  FOREIGN KEY (`track_id`)
  REFERENCES `spotitube`.`track` (`trackid`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,

ADD CONSTRAINT `fk_playlist_id`
  FOREIGN KEY (`playlist_id`)
  REFERENCES `spotitube`.`playlist` (`playlist_id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
  
  ALTER TABLE `spotitube`.`user` 
ADD CONSTRAINT `fk_user_login`
  FOREIGN KEY (`user_login`)
  REFERENCES `spotitube`.`login` (`user_login`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
  
insert into spotitube.login values ("ruben", "wachtwoord");
insert into spotitube.user values ("ruben", "3l1t3h4ckz0rz", "123456");

insert into spotitube.playlist values(1, "heavy metal", true);
insert into spotitube.playlist values(2, "pop", false);
insert into spotitube.playlist values(3, "drum and bass", true);
insert into spotitube.playlist values(4, "rock", false);
insert into spotitube.playlist values(5, "hardstyle", true);
insert into spotitube.playlist values(6, "hardcore", false);
insert into spotitube.playlist values(7, "hip hop", true);
insert into spotitube.playlist values(8, "chill", false);
insert into spotitube.playlist values(9, "electronic", true);
insert into spotitube.playlist values(10, "dubstep", false);

insert into spotitube.track values (1, "title1", "performer1", 337, "album1", 89, '2008-07-04', "description1", true);
insert into spotitube.track values (2, "title2", "performer2", 337, "album2", 89, '2010-07-04', "description2", false);
insert into spotitube.track values (3, "title3", "performer3", 337, "album3", 1, '2000-10-06', "description3", false);
insert into spotitube.track values (4, "title4", "performer4", 100, "album4", 77, '2000-12-06', "description4", false);
insert into spotitube.track values (5, "title5", "ruben", 9, "album5", 24, '1996-01-05', "description5", true);
insert into spotitube.track values (6, "title6", "performer6", 43543, "album6", 249, '1999-09-06', "description6", true);
insert into spotitube.track values (7, "title7", "performer7", 435, "album7", 208, '2020-09-07', "description7", false);
insert into spotitube.track values (8, "title8", "performer8", 46, "album8", 208, '2019-03-02', "description8", false);
insert into spotitube.track values (9, "title9", "performer9", 46, "album9", 209, '2019-03-02', "description9", false);
insert into spotitube.track values (10, "title10", "performer10", 666, "album10", 355, '2011-02-24', "description10", true);

insert into tracks_in_playlist values(1,1);
insert into tracks_in_playlist values(3,1);
insert into tracks_in_playlist values(5,1);
insert into tracks_in_playlist values(7,1);
insert into tracks_in_playlist values(9,1);

insert into tracks_in_playlist values(2,2);
insert into tracks_in_playlist values(4,2);
insert into tracks_in_playlist values(6,2);
insert into tracks_in_playlist values(8,2);
insert into tracks_in_playlist values(10,2);

insert into tracks_in_playlist values(1,3);
insert into tracks_in_playlist values(2,3);
insert into tracks_in_playlist values(3,3);
insert into tracks_in_playlist values(4,3);
insert into tracks_in_playlist values(5,3);

insert into tracks_in_playlist values(6,4);
insert into tracks_in_playlist values(7,4);
insert into tracks_in_playlist values(8,4);
insert into tracks_in_playlist values(9,4);
insert into tracks_in_playlist values(10,4);

insert into tracks_in_playlist values(1,5);
insert into tracks_in_playlist values(2,5);
insert into tracks_in_playlist values(5,5);
insert into tracks_in_playlist values(6,5);
insert into tracks_in_playlist values(10,5);

insert into tracks_in_playlist values(3,6);
insert into tracks_in_playlist values(4,6);
insert into tracks_in_playlist values(7,6);
insert into tracks_in_playlist values(8,6);
insert into tracks_in_playlist values(9,6);

insert into tracks_in_playlist values(10,7);
insert into tracks_in_playlist values(1,7);
insert into tracks_in_playlist values(9,7);
insert into tracks_in_playlist values(2,7);
insert into tracks_in_playlist values(8,7);

insert into tracks_in_playlist values(3,8);
insert into tracks_in_playlist values(7,8);
insert into tracks_in_playlist values(4,8);
insert into tracks_in_playlist values(6,8);
insert into tracks_in_playlist values(5,8);

insert into tracks_in_playlist values(1,9);
insert into tracks_in_playlist values(2,9);
insert into tracks_in_playlist values(3,9);
insert into tracks_in_playlist values(6,9);
insert into tracks_in_playlist values(9,9);

insert into tracks_in_playlist values(4,10);
insert into tracks_in_playlist values(5,10);
insert into tracks_in_playlist values(7,10);
insert into tracks_in_playlist values(8,10);
insert into tracks_in_playlist values(10,10);


```

