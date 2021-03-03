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

-- Volcando estructura para tabla 6enraya.casillas
CREATE TABLE IF NOT EXISTS `casillas` (
  `IdCasilla` int(11) NOT NULL AUTO_INCREMENT,
  `Numero` text NOT NULL DEFAULT '0',
  PRIMARY KEY (`IdCasilla`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8mb4 COMMENT='Información sonbre las diferentes casillas que forman el tablero (un total de 36)';

-- Volcando datos para la tabla 6enraya.casillas: ~34 rows (aproximadamente)
/*!40000 ALTER TABLE `casillas` DISABLE KEYS */;
INSERT INTO `casillas` (`IdCasilla`, `Numero`) VALUES
	(1, '00'),
	(2, '01'),
	(3, '02'),
	(4, '03'),
	(5, '04'),
	(6, '05'),
	(7, '10'),
	(8, '11'),
	(9, '12'),
	(10, '13'),
	(11, '14'),
	(12, '15'),
	(13, '20'),
	(14, '21'),
	(15, '22'),
	(16, '23'),
	(17, '24'),
	(18, '25'),
	(19, '30'),
	(20, '31'),
	(21, '32'),
	(22, '33'),
	(23, '34'),
	(24, '35'),
	(25, '40'),
	(26, '41'),
	(27, '42'),
	(28, '43'),
	(29, '44'),
	(30, '45'),
	(31, '50'),
	(32, '51'),
	(33, '52'),
	(34, '53'),
	(35, '54'),
	(36, '55');
/*!40000 ALTER TABLE `casillas` ENABLE KEYS */;

-- Volcando estructura para tabla 6enraya.detallespartida
CREATE TABLE IF NOT EXISTS `detallespartida` (
  `IdPartida` int(11) NOT NULL,
  `IdJugador` int(11) NOT NULL,
  `Turno` bit(1) DEFAULT NULL,
  `Puntos` int(11) DEFAULT NULL,
  PRIMARY KEY (`IdPartida`,`IdJugador`),
  KEY `FK_detallespartida_usuarios` (`IdJugador`),
  CONSTRAINT `FK_detallespartida_partidas` FOREIGN KEY (`IdPartida`) REFERENCES `partidas` (`IdPartida`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_detallespartida_usuarios` FOREIGN KEY (`IdJugador`) REFERENCES `usuarios` (`IdUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Detalles de un jugador específico dentro de la partida';

-- Volcando datos para la tabla 6enraya.detallespartida: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `detallespartida` DISABLE KEYS */;
INSERT INTO `detallespartida` (`IdPartida`, `IdJugador`, `Turno`, `Puntos`) VALUES
	(1, 63, b'1', 20);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Histórico de las partidas jugadas por un jugador';

-- Volcando datos para la tabla 6enraya.historicopartidas: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `historicopartidas` DISABLE KEYS */;
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

-- Volcando datos para la tabla 6enraya.movimientos: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `movimientos` DISABLE KEYS */;
/*!40000 ALTER TABLE `movimientos` ENABLE KEYS */;

-- Volcando estructura para tabla 6enraya.partidas
CREATE TABLE IF NOT EXISTS `partidas` (
  `IdPartida` int(11) NOT NULL AUTO_INCREMENT,
  `IdJugador1` int(11) DEFAULT NULL,
  `IdJugador2` int(11) DEFAULT NULL,
  `Activa` int(11) DEFAULT NULL,
  PRIMARY KEY (`IdPartida`),
  KEY `FK_partidas_usuarios` (`IdJugador1`),
  KEY `FK_partidas_usuarios_2` (`IdJugador2`),
  CONSTRAINT `FK_partidas_usuarios` FOREIGN KEY (`IdJugador1`) REFERENCES `usuarios` (`IdUsuario`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_partidas_usuarios_2` FOREIGN KEY (`IdJugador2`) REFERENCES `usuarios` (`IdUsuario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COMMENT='Datos básicos sobre partida';

-- Volcando datos para la tabla 6enraya.partidas: ~11 rows (aproximadamente)
/*!40000 ALTER TABLE `partidas` DISABLE KEYS */;
INSERT INTO `partidas` (`IdPartida`, `IdJugador1`, `IdJugador2`, `Activa`) VALUES
	(1, 63, 27, 1),
	(2, 63, 27, 1),
	(3, 63, 27, 0),
	(4, 63, 27, 0),
	(5, 63, 27, 0),
	(6, 63, 27, 0),
	(7, 63, 27, 0),
	(8, 63, 60, 0),
	(9, 63, 60, 0),
	(10, 63, 3, 0),
	(11, 63, 3, 1),
	(12, 63, 2, 1),
	(13, 63, 3, 1),
	(14, 63, 3, 1),
	(15, 63, 2, 1),
	(16, 63, 2, 1),
	(17, 63, 2, 1),
	(18, 63, 2, 1),
	(19, 63, 2, 1),
	(20, 63, 2, 1),
	(21, 63, 2, 1),
	(22, 63, 2, 1),
	(23, 63, 2, 1),
	(24, 63, 2, 1),
	(25, 63, 2, 1),
	(26, 63, 2, 1),
	(27, 63, 2, 1),
	(28, 63, 2, 1),
	(29, 63, 2, 1),
	(30, 63, 2, 1),
	(31, 63, 2, 1),
	(32, 63, 2, 1),
	(33, 63, 2, 1),
	(34, 63, 2, 1),
	(35, 63, 2, 1),
	(36, 63, 2, 1);
/*!40000 ALTER TABLE `partidas` ENABLE KEYS */;

-- Volcando estructura para tabla 6enraya.usuarios
CREATE TABLE IF NOT EXISTS `usuarios` (
  `IdUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `Nick` text DEFAULT NULL,
  `Nombre` text DEFAULT NULL,
  `Contraseña` text DEFAULT NULL,
  `email` text DEFAULT NULL,
  PRIMARY KEY (`IdUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4 COMMENT='Información sobre un usuario';

-- Volcando datos para la tabla 6enraya.usuarios: ~7 rows (aproximadamente)
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` (`IdUsuario`, `Nick`, `Nombre`, `Contraseña`, `email`) VALUES
	(2, 'Alvaro', 'DA', 'DA lopez', NULL),
	(3, 'david', 'DA2', 'DA lopez', NULL),
	(5, 'Perico', 'David', 'David', NULL),
	(17, 'Juanito', 'DA2', 'DA lopez', NULL),
	(27, 'Pablo', 'DA2', 'DA lopez', NULL),
	(60, 'Beneit', 'DA2', 'DA lopez', NULL),
	(61, 'DA2', 'DA2', 'DA lopez', NULL),
	(62, 'dededi', 'dededi', 'dededi', NULL),
	(63, 'damuocle', 'Deivid', 'david', NULL);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
