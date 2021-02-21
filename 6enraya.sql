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
  `Numero` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`IdCasilla`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8mb4 COMMENT='Información sonbre las diferentes casillas que forman el tablero (un total de 36)';

-- Volcando datos para la tabla 6enraya.casillas: ~36 rows (aproximadamente)
/*!40000 ALTER TABLE `casillas` DISABLE KEYS */;
INSERT INTO `casillas` (`IdCasilla`, `Numero`) VALUES
	(38, 11),
	(39, 12),
	(40, 13),
	(41, 14),
	(42, 15),
	(43, 16),
	(44, 21),
	(45, 22),
	(46, 23),
	(47, 24),
	(48, 25),
	(49, 26),
	(50, 31),
	(51, 32),
	(52, 33),
	(53, 34),
	(54, 35),
	(55, 36),
	(56, 41),
	(57, 42),
	(58, 43),
	(59, 44),
	(60, 45),
	(61, 46),
	(62, 51),
	(63, 52),
	(64, 53),
	(65, 54),
	(66, 55),
	(67, 56),
	(68, 61),
	(69, 62),
	(70, 63),
	(71, 64),
	(72, 65),
	(73, 66);
/*!40000 ALTER TABLE `casillas` ENABLE KEYS */;

-- Volcando estructura para tabla 6enraya.detallespartida
CREATE TABLE IF NOT EXISTS `detallespartida` (
  `IdPartida` int(11) NOT NULL,
  `IdJugador` int(11) NOT NULL,
  `Turno` bit(1) DEFAULT NULL,
  `IdCasilla` int(11) DEFAULT NULL,
  `CasillaOcupada` bit(1) DEFAULT NULL,
  `Puntos` int(11) DEFAULT NULL,
  PRIMARY KEY (`IdPartida`,`IdJugador`),
  KEY `FK_detallespartida_usuarios` (`IdJugador`),
  KEY `FK_detallespartida_casillas` (`IdCasilla`),
  CONSTRAINT `FK_detallespartida_casillas` FOREIGN KEY (`IdCasilla`) REFERENCES `casillas` (`IdCasilla`),
  CONSTRAINT `FK_detallespartida_partidas` FOREIGN KEY (`IdPartida`) REFERENCES `partidas` (`IdPartida`),
  CONSTRAINT `FK_detallespartida_usuarios` FOREIGN KEY (`IdJugador`) REFERENCES `usuarios` (`IdUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Detalles de un jugador específico dentro de la partida';

-- Volcando datos para la tabla 6enraya.detallespartida: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `detallespartida` DISABLE KEYS */;
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

-- Volcando estructura para tabla 6enraya.partidas
CREATE TABLE IF NOT EXISTS `partidas` (
  `IdPartida` int(11) NOT NULL AUTO_INCREMENT,
  `IdJugador1` int(11) DEFAULT NULL,
  `IdJugador2` int(11) DEFAULT NULL,
  `Activa` int(11) DEFAULT NULL,
  PRIMARY KEY (`IdPartida`),
  KEY `FK_partidas_usuarios` (`IdJugador1`),
  KEY `FK_partidas_usuarios_2` (`IdJugador2`),
  CONSTRAINT `FK_partidas_usuarios` FOREIGN KEY (`IdJugador1`) REFERENCES `usuarios` (`IdUsuario`),
  CONSTRAINT `FK_partidas_usuarios_2` FOREIGN KEY (`IdJugador2`) REFERENCES `usuarios` (`IdUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Datos básicos sobre partida';

-- Volcando datos para la tabla 6enraya.partidas: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `partidas` DISABLE KEYS */;
/*!40000 ALTER TABLE `partidas` ENABLE KEYS */;

-- Volcando estructura para tabla 6enraya.usuarios
CREATE TABLE IF NOT EXISTS `usuarios` (
  `IdUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `Nick` text DEFAULT NULL,
  `Nombre` text DEFAULT NULL,
  `Contraseña` text DEFAULT NULL,
  `buscaPartida` bit(1) NOT NULL DEFAULT b'0',
  `email` text DEFAULT NULL,
  PRIMARY KEY (`IdUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb4 COMMENT='Información sobre un usuario';

-- Volcando datos para la tabla 6enraya.usuarios: ~7 rows (aproximadamente)
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` (`IdUsuario`, `Nick`, `Nombre`, `Contraseña`, `buscaPartida`, `email`) VALUES
	(2, 'Alvaro', 'DA', 'DA lopez', b'0', NULL),
	(3, 'david', 'DA2', 'DA lopez', b'1', NULL),
	(5, 'Perico', 'David', 'David', b'0', NULL),
	(17, 'Juanito', 'DA2', 'DA lopez', b'0', NULL),
	(27, 'Pablo', 'DA2', 'DA lopez', b'1', NULL),
	(60, 'Beneit', 'DA2', 'DA lopez', b'1', NULL),
	(61, 'DA2', 'DA2', 'DA lopez', b'0', NULL),
	(62, 'dededi', 'dededi', 'dededi', b'0', NULL);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
