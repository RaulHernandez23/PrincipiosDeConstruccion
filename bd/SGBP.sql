DROP DATABASE IF EXISTS sgbp; 
create database sgbp;
use sgbp;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- Table structure for actividad
DROP TABLE IF EXISTS `actividad`;
CREATE TABLE `actividad`  (
  `idActividad` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `descripcion` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `esfuerzoMinutos` int NULL DEFAULT NULL,
  `fechaInicio` date NOT NULL,
  `fechaFin` date NULL DEFAULT NULL,
  `idEstadoActividad` int NOT NULL,
	`idTipoActividad` int NOT NULL,
  `idEstudiante` int NULL DEFAULT NULL,
  `idResponsable` int NOT NULL,
  PRIMARY KEY (`idActividad`) USING BTREE,
  CONSTRAINT `actividad_ibfk_1` FOREIGN KEY (`idEstadoActividad`) REFERENCES `estadoActividad` (`idEstadoActividad`) ON DELETE RESTRICT ON UPDATE RESTRICT,
	CONSTRAINT `actividad_ibfk_2` FOREIGN KEY (`idTipoActividad`) REFERENCES `tipoActividad` (`idTipoActividad`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `actividad_ibfk_3` FOREIGN KEY (`idEstudiante`) REFERENCES `estudiante` (`idEstudiante`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `actividad_ibfk_4` FOREIGN KEY (`idResponsable`) REFERENCES `responsableProyecto` (`idResponsableProyecto`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

DROP TABLE IF EXISTS `estadoActividad`;
CREATE TABLE `estadoActividad`  (
  `idEstadoActividad` int NOT NULL AUTO_INCREMENT,
  `estado` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`idEstadoActividad`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;


DROP TABLE IF EXISTS `tipoActividad`;
CREATE TABLE `tipoActividad`  (
  `idTipoActividad` int NOT NULL AUTO_INCREMENT,
  `tipo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`idTipoActividad`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- Table structure for cambio
DROP TABLE IF EXISTS `cambio`;
CREATE TABLE `cambio`  (
  `idCambio` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `descripcion` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `idEstadoCambio` int NOT NULL,
  `fechaRegistro` DATE NOT NULL, 
  `fechaFin` DATE NULL DEFAULT NULL, 
  PRIMARY KEY (`idCambio`) USING BTREE,
  CONSTRAINT `cambio_ibfk_1` FOREIGN KEY (`idEstadoCambio`) REFERENCES `estadoCambio` (`idEstadoCambio`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

DROP TABLE IF EXISTS `estadoCambio`;
CREATE TABLE `estadoCambio`  (
  `idEstadoCambio` int NOT NULL AUTO_INCREMENT,
  `estado` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`idEstadoCambio`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- Table structure for defecto
DROP TABLE IF EXISTS `defecto`;
CREATE TABLE `defecto`  (
  `idDefecto` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `descripcion` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `esfuerzoMinutos` INT NULL DEFAULT NULL,
  `fechaReporte` date NOT NULL,
  `idEstadoDefecto` int NOT NULL,
  `idEstudiante` int NOT NULL,
  PRIMARY KEY (`idDefecto`) USING BTREE,
  CONSTRAINT `defecto_ibfk_1` FOREIGN KEY (`idEstadoDefecto`) REFERENCES `estadoDefecto` (`idEstadoDefecto`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `defecto_ibfk_2` FOREIGN KEY (`idEstudiante`) REFERENCES `estudiante` (`idEstudiante`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- Table structure for estadoDefecto
DROP TABLE IF EXISTS `estadoDefecto`;
CREATE TABLE `estadoDefecto`  (
  `idEstadoDefecto` int NOT NULL AUTO_INCREMENT,
  `estado` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`idEstadoDefecto`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;


-- Table structure for estadoestudiante
DROP TABLE IF EXISTS `estadoEstudiante`;
CREATE TABLE `estadoEstudiante`  (
  `idEstadoEstudiante` int NOT NULL AUTO_INCREMENT,
  `estado` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`idEstadoEstudiante`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

DROP TABLE IF EXISTS `estadoSolicitud`;
CREATE TABLE `estadoSolicitud`  (
  `idEstadoSolicitud` int NOT NULL AUTO_INCREMENT,
  `estado` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`idEstadoSolicitud`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- Table structure for estudiante
DROP TABLE IF EXISTS `estudiante`;
CREATE TABLE `estudiante`  (
  `idEstudiante` int NOT NULL AUTO_INCREMENT,
  `matricula` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `nombre` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `apellidoPaterno` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `apellidoMaterno` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `idEstadoEstudiante` int NOT NULL,
  `password` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`idEstudiante`) USING BTREE,
  CONSTRAINT `estudiante_ibfk_1` FOREIGN KEY (`idEstadoEstudiante`) REFERENCES `estadoEstudiante` (`idEstadoEstudiante`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- Table structure for realiza
DROP TABLE IF EXISTS `estudianteCambio`;
CREATE TABLE `estudianteCambio`  (
  `idRealiza` int NOT NULL AUTO_INCREMENT,
  `idEstudiante` int NOT NULL,
  `idCambio` int NOT NULL,
  PRIMARY KEY (`idRealiza`) USING BTREE,
  CONSTRAINT `realiza_ibfk_1` FOREIGN KEY (`idEstudiante`) REFERENCES `estudiante` (`idEstudiante`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `realiza_ibfk_2` FOREIGN KEY (`idCambio`) REFERENCES `cambio` (`idCambio`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- Table structure for responsableproyecto
DROP TABLE IF EXISTS `responsableProyecto`;
CREATE TABLE `responsableProyecto`  (
  `idResponsableProyecto` int NOT NULL AUTO_INCREMENT,
  `numPersonal` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `nombre` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `apellidoPaterno` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `apellidoMaterno` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `correo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `telefono` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`idResponsableProyecto`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- Table structure for solicitudDeCambio
DROP TABLE IF EXISTS `solicitudDeCambio`;
CREATE TABLE `solicitudDeCambio`  (
  `idSolicitudDeCambio` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `descripcion` varchar(1500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL, 
  `razon` varchar(1500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `impacto` varchar(1500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL, 
  `accionPropuesta` varchar(1500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `fechaCreacion` date NOT NULL,
  `fechaAprobacion` date NULL DEFAULT NULL,
  `idEstadoSolicitud` int NOT NULL,
  `idEstudiante` int NOT NULL,
  `idResponsableDeProyecto` int NULL DEFAULT NULL, 
  PRIMARY KEY (`idSolicitudDeCambio`) USING BTREE,
  CONSTRAINT `solicitudDecambio_ibfk_1` FOREIGN KEY (`idEstadoSolicitud`) REFERENCES `estadoSolicitud` (`idEstadoSolicitud`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `solicitudDecambio_ibfk_2` FOREIGN KEY (`idEstudiante`) REFERENCES `estudiante` (`idEstudiante`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `solicitudDecambio_ibfk_3` FOREIGN KEY (`idResponsableDeProyecto`) REFERENCES `responsableProyecto` (`idResponsableProyecto`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;