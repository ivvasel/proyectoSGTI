-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.4.14-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para 6enraya
CREATE DATABASE IF NOT EXISTS `6enraya` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `6enraya`;

-- Volcando estructura para tabla 6enraya.detallespartida
CREATE TABLE IF NOT EXISTS `detallespartida` (
  `IdPartida` int(11) NOT NULL,
  `IdJugador` int(11) NOT NULL,
  `Turno` bit(1) DEFAULT NULL,
  `Puntos` int(11) DEFAULT NULL,
  `Activa` bit(1) NOT NULL,
  PRIMARY KEY (`IdPartida`,`IdJugador`),
  KEY `FK_detallespartida_usuarios` (`IdJugador`),
  CONSTRAINT `FK_detallespartida_partidas` FOREIGN KEY (`IdPartida`) REFERENCES `partidas` (`IdPartida`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_detallespartida_usuarios` FOREIGN KEY (`IdJugador`) REFERENCES `usuarios` (`IdUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Detalles de un jugador específico dentro de la partida';

-- Volcando datos para la tabla 6enraya.detallespartida: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `detallespartida` DISABLE KEYS */;
INSERT INTO `detallespartida` (`IdPartida`, `IdJugador`, `Turno`, `Puntos`, `Activa`) VALUES
	(86, 67, b'1', 0, b'0'),
	(86, 114, b'0', 3, b'0');
/*!40000 ALTER TABLE `detallespartida` ENABLE KEYS */;

-- Volcando estructura para tabla 6enraya.historicopartidas
CREATE TABLE IF NOT EXISTS `historicopartidas` (
  `IdHistorico` int(11) NOT NULL AUTO_INCREMENT,
  `IdUsuario` int(11) DEFAULT NULL,
  `Ganadas` int(11) DEFAULT NULL,
  `Perdidas` int(11) DEFAULT NULL,
  `PuntosTotales` int(11) DEFAULT NULL,
  PRIMARY KEY (`IdHistorico`),
  KEY `FK_historicopartidas_usuarios` (`IdUsuario`),
  CONSTRAINT `FK_historicopartidas_usuarios` FOREIGN KEY (`IdUsuario`) REFERENCES `usuarios` (`IdUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='Histórico de las partidas jugadas por un jugador';

-- Volcando datos para la tabla 6enraya.historicopartidas: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `historicopartidas` DISABLE KEYS */;
INSERT INTO `historicopartidas` (`IdHistorico`, `IdUsuario`, `Ganadas`, `Perdidas`, `PuntosTotales`) VALUES
	(1, 114, 1, 0, 3),
	(2, 67, 0, 1, 0);
/*!40000 ALTER TABLE `historicopartidas` ENABLE KEYS */;

-- Volcando estructura para tabla 6enraya.movimientos
CREATE TABLE IF NOT EXISTS `movimientos` (
  `IdPartida` int(11) DEFAULT NULL,
  `IdUsuario` int(11) DEFAULT NULL,
  `Casilla` text DEFAULT NULL,
  KEY `FK_movimientos_partidas` (`IdPartida`),
  KEY `FK_movimientos_usuarios` (`IdUsuario`),
  KEY `FK_movimientos_casillas` (`Casilla`(768)) USING BTREE,
  CONSTRAINT `FK_movimientos_partidas` FOREIGN KEY (`IdPartida`) REFERENCES `partidas` (`IdPartida`),
  CONSTRAINT `FK_movimientos_usuarios` FOREIGN KEY (`IdUsuario`) REFERENCES `usuarios` (`IdUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla 6enraya.movimientos: ~34 rows (aproximadamente)
/*!40000 ALTER TABLE `movimientos` DISABLE KEYS */;
INSERT INTO `movimientos` (`IdPartida`, `IdUsuario`, `Casilla`) VALUES
	(86, 114, '50'),
	(86, 67, '51'),
	(86, 114, '41'),
	(86, 67, '40'),
	(86, 114, '52'),
	(86, 67, '30'),
	(86, 114, '42'),
	(86, 67, '53'),
	(86, 114, '32'),
	(86, 67, '20'),
	(86, 114, '22'),
	(86, 67, '12'),
	(86, 114, '10'),
	(86, 67, '54'),
	(86, 114, '43'),
	(86, 67, '44'),
	(86, 114, '33'),
	(86, 67, '23'),
	(86, 114, '34'),
	(86, 67, '24'),
	(86, 114, '31'),
	(86, 67, '21'),
	(86, 114, '55'),
	(86, 67, '13'),
	(86, 114, '11'),
	(86, 67, '00'),
	(86, 114, '03'),
	(86, 67, '14'),
	(86, 114, '02'),
	(86, 67, '01'),
	(86, 114, '04'),
	(86, 67, '45'),
	(86, 114, '35'),
	(86, 67, '25'),
	(86, 114, '15'),
	(86, 67, '05');
/*!40000 ALTER TABLE `movimientos` ENABLE KEYS */;

-- Volcando estructura para tabla 6enraya.partidas
CREATE TABLE IF NOT EXISTS `partidas` (
  `IdPartida` int(11) NOT NULL AUTO_INCREMENT,
  `IdJugador1` int(11) DEFAULT NULL,
  `IdJugador2` int(11) DEFAULT NULL,
  PRIMARY KEY (`IdPartida`),
  KEY `FK_partidas_usuarios` (`IdJugador1`),
  KEY `FK_partidas_usuarios_2` (`IdJugador2`),
  CONSTRAINT `FK_partidas_usuarios` FOREIGN KEY (`IdJugador1`) REFERENCES `usuarios` (`IdUsuario`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_partidas_usuarios_2` FOREIGN KEY (`IdJugador2`) REFERENCES `usuarios` (`IdUsuario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8mb4 COMMENT='Datos básicos sobre partida';

-- Volcando datos para la tabla 6enraya.partidas: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `partidas` DISABLE KEYS */;
INSERT INTO `partidas` (`IdPartida`, `IdJugador1`, `IdJugador2`) VALUES
	(86, 114, 67);
/*!40000 ALTER TABLE `partidas` ENABLE KEYS */;

-- Volcando estructura para tabla 6enraya.usuarios
CREATE TABLE IF NOT EXISTS `usuarios` (
  `IdUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `Nick` text DEFAULT NULL,
  `Nombre` text DEFAULT NULL,
  `Contraseña` text DEFAULT NULL,
  `email` text DEFAULT NULL,
  PRIMARY KEY (`IdUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8mb4 COMMENT='Información sobre un usuario';

-- Volcando datos para la tabla 6enraya.usuarios: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` (`IdUsuario`, `Nick`, `Nombre`, `Contraseña`, `email`) VALUES
	(67, 'IvanVa', 'Ivan', '1234', 'ivvasel@teleco.upv.es'),
	(68, 'damuocle', 'David', 'damuocle', 'damuocle@teleco.upv.es'),
	(114, 'jelopez', 'jose', '1234', 'jelopez@dcom.upv.es');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
