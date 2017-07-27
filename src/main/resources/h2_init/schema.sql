CREATE TABLE IF NOT EXISTS `star` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `galacticLongitude` varchar(50) NOT NULL,
  `galacticLatitude` varchar(50) NOT NULL,
  `starClass` varchar(50) NOT NULL,
  `discoverer` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `star_discoverer` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
);