-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Jeu 05 Décembre 2013 à 19:57
-- Version du serveur: 5.5.24-log
-- Version de PHP: 5.4.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `ortoba`
--
CREATE DATABASE `ortoba` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `ortoba`;

-- --------------------------------------------------------

--
-- Structure de la table `equipes`
--

CREATE TABLE IF NOT EXISTS `equipes` (
  `id_equipe` int(11) NOT NULL AUTO_INCREMENT,
  `nom_equipe` varchar(32) NOT NULL,
  `ville_equipe` varchar(32) NOT NULL,
  `score_equipe` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_equipe`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

-- --------------------------------------------------------

--
-- Structure de la table `matchs`
--

CREATE TABLE IF NOT EXISTS `matchs` (
  `id_match` int(11) NOT NULL AUTO_INCREMENT,
  `id_equipe_1` int(11) NOT NULL,
  `id_equipe_2` int(11) NOT NULL,
  `resultat_equipe_1` int(11) NOT NULL,
  `resultat_equipe_2` int(11) NOT NULL,
  PRIMARY KEY (`id_match`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

--
-- Déclencheurs `matchs`
--
DROP TRIGGER IF EXISTS `calcul_score`;
DELIMITER //
CREATE TRIGGER `calcul_score` AFTER INSERT ON `matchs`
 FOR EACH ROW BEGIN
DECLARE point_1, point_2 INT DEFAULT 2;
DECLARE temp_1, temp_2 INT;
DECLARE bonus INT DEFAULT 10;
DECLARE temp INT;

IF (NEW.resultat_equipe_1 > NEW.resultat_equipe_2) THEN
  SET point_1 = 3;
        SET point_2 = 1;
ELSEIF (NEW.resultat_equipe_1 < NEW.resultat_equipe_2) THEN
  SET point_1 = 1;
        SET point_2 = 3;
END IF;

SET temp_1 = (SELECT equipes.score_equipe FROM equipes WHERE equipes.id_equipe = NEW.id_equipe_1);
SET temp_2 = (SELECT equipes.score_equipe FROM equipes WHERE equipes.id_equipe = NEW.id_equipe_2);

UPDATE equipes SET equipes.score_equipe = (temp_1 + point_1) WHERE equipes.id_equipe = NEW.id_equipe_1;
UPDATE equipes SET equipes.score_equipe = (temp_2 + point_2) WHERE equipes.id_equipe = NEW.id_equipe_2;

IF ((NEW.resultat_equipe_1 - NEW.resultat_equipe_2) >9) THEN
  SET temp = (SELECT equipes.score_equipe FROM equipes WHERE equipes.id_equipe = NEW.id_equipe_1);
  UPDATE equipes SET equipes.score_equipe = (temp + bonus) WHERE equipes.id_equipe = NEW.id_equipe_1;
ELSEIF ((NEW.resultat_equipe_2 - NEW.resultat_equipe_1) >9) THEN
  SET temp = (SELECT equipes.score_equipe FROM equipes WHERE equipes.id_equipe = NEW.id_equipe_2);
  UPDATE equipes SET equipes.score_equipe = (temp + bonus) WHERE equipes.id_equipe = NEW.id_equipe_2;
END IF;

END
//
DELIMITER ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;