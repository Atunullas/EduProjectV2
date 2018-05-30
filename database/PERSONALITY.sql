CREATE TABLE `personality` (
  `person_id` int(11) NOT NULL,
  `person_name` varchar(500) NOT NULL,
  `person_sex` varchar(50) NOT NULL,
  `person_dob` datetime DEFAULT NULL,
  `person_doe` datetime DEFAULT NULL,
  `person_about` varchar(4000) DEFAULT NULL,
  `person_pic` blob,
  PRIMARY KEY (`person_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8