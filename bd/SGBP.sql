DROP DATABASE IF EXISTS sgbp2; 
create database sgbp2;
use sgbp2;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- Table structure for actividad
DROP TABLE IF EXISTS `actividad`;
CREATE TABLE `actividad`  (
  `idActividad` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `descripcion` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `esfuerzoMinutos` int NOT NULL,
  `fechaInicio` date NOT NULL,
  `fechaFin` date NULL DEFAULT NULL,
  `idEstadoActividad` int NOT NULL,
  `idTipoActividad` int NOT NULL,
  `idEstudiante` int NOT NULL,
  `idResponsable` int NOT NULL,
  PRIMARY KEY (`idActividad`) USING BTREE,
  CONSTRAINT `actividad_ibfk_1` FOREIGN KEY (`idEstadoActividad`) REFERENCES `estadoactividad` (`idEstadoActividad`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `actividad_ibfk_2` FOREIGN KEY (`idTipoActividad`) REFERENCES `tipoactividad` (`idTipoActividad`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `actividad_ibfk_3` FOREIGN KEY (`idEstudiante`) REFERENCES `estudiante` (`idEstudiante`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `actividad_ibfk_4` FOREIGN KEY (`idResponsable`) REFERENCES `responsableproyecto` (`idResponsableProyecto`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

DROP TABLE IF EXISTS `tipoactividad`;
CREATE TABLE `tipoactividad`  (
  `idTipoActividad` int NOT NULL AUTO_INCREMENT,
  `tipo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`idTipoActividad`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- Table structure for cambio
DROP TABLE IF EXISTS `cambio`;
CREATE TABLE `cambio`  (
  `idCambio` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `descripcion` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `idEstadoActividad` int NOT NULL,
  `idSolicitud` int NOT NULL,
  PRIMARY KEY (`idCambio`) USING BTREE,
  CONSTRAINT `cambio_ibfk_1` FOREIGN KEY (`idEstadoActividad`) REFERENCES `estadoactividad` (`idEstadoActividad`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `cambio_ibfk_2` FOREIGN KEY (`idSolicitud`) REFERENCES `solicitudcambios` (`idSolicitudCambios`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- Table structure for defecto
DROP TABLE IF EXISTS `defecto`;
CREATE TABLE `defecto`  (
  `idDefecto` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `descripcion` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `esfuerzoMinutos` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `fechaReporte` date NOT NULL,
  `idEstadoActividad` int NOT NULL,
  `idEstudiante` int NOT NULL,
  PRIMARY KEY (`idDefecto`) USING BTREE,
  CONSTRAINT `defecto_ibfk_1` FOREIGN KEY (`idEstadoActividad`) REFERENCES `estadoactividad` (`idEstadoActividad`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `defecto_ibfk_2` FOREIGN KEY (`idEstudiante`) REFERENCES `estudiante` (`idEstudiante`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- Table structure for estadoactividad
DROP TABLE IF EXISTS `estado`;
CREATE TABLE `estado`  (
  `idEstado` int NOT NULL AUTO_INCREMENT,
  `estado` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`idEstado`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- Table structure for estadoestudiante
DROP TABLE IF EXISTS `estadoestudiante`;
CREATE TABLE `estadoestudiante`  (
  `idEstadoEstudiante` int NOT NULL AUTO_INCREMENT,
  `estado` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`idEstadoEstudiante`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

DROP TABLE IF EXISTS `estadosolicitud`;
CREATE TABLE `estadosolicitud`  (
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
  `apellidoMaterno` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `idEstadoEstudiante` int NOT NULL,
  `contrasena` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`idEstudiante`) USING BTREE,
  CONSTRAINT `estudiante_ibfk_1` FOREIGN KEY (`idEstadoEstudiante`) REFERENCES `estadoestudiante` (`idEstadoEstudiante`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- Table structure for realiza
DROP TABLE IF EXISTS `realiza`;
CREATE TABLE `realiza`  (
  `idRealiza` int NOT NULL AUTO_INCREMENT,
  `idEstudiante` int NOT NULL,
  `idCambio` int NOT NULL,
  PRIMARY KEY (`idRealiza`) USING BTREE,
  CONSTRAINT `realiza_ibfk_1` FOREIGN KEY (`idEstudiante`) REFERENCES `estudiante` (`idEstudiante`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `realiza_ibfk_2` FOREIGN KEY (`idCambio`) REFERENCES `cambio` (`idCambio`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- Table structure for responsableproyecto
DROP TABLE IF EXISTS `responsableproyecto`;
CREATE TABLE `responsableproyecto`  (
  `idResponsableProyecto` int NOT NULL AUTO_INCREMENT,
  `numPersonal` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `nombre` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `apellidoPaterno` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `apellidoMaterno` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `correo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `telefono` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `contrasena` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`idResponsableProyecto`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- Table structure for solicitudcambios
DROP TABLE IF EXISTS `solicituddecambio`;
CREATE TABLE `solicituddecambio`  (
  `idSolicitudDeCambio` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `descripcion` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `razon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `impacto` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `propuesta` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `fechaCreacion` date NOT NULL,
  `idEstadoSolicitud` int NOT NULL,
  `idEstudiante` int NULL DEFAULT NULL,
  `idResponsable` int NULL DEFAULT NULL, 
  PRIMARY KEY (`idSolicitudDeCambio`) USING BTREE,
  CONSTRAINT `solicitudDecambio_ibfk_1` FOREIGN KEY (`idEstadoSolicitud`) REFERENCES `estadosolicitud` (`idEstadoSolicitud`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `solicitudDecambio_ibfk_2` FOREIGN KEY (`idEstudiante`) REFERENCES `estudiante` (`idEstudiante`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `solicitudDecambio_ibfk_3` FOREIGN KEY (`idResponsable`) REFERENCES `responsableproyecto` (`idResponsableProyecto`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;