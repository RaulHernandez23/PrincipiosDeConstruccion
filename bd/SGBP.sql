/*
 Navicat Premium Data Transfer

 Source Server         : Pruebas de software
 Source Server Type    : MySQL
 Source Server Version : 100428 (10.4.28-MariaDB)
 Source Host           : localhost:3306
 Source Schema         : sgbp

 Target Server Type    : MySQL
 Target Server Version : 100428 (10.4.28-MariaDB)
 File Encoding         : 65001

 Date: 22/11/2023 19:43:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for actividad
-- ----------------------------
DROP TABLE IF EXISTS `actividad`;
CREATE TABLE `actividad`  (
  `IdActividad` int NOT NULL AUTO_INCREMENT,
  `Titulo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Descripcion` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `EsfuerzoMinutos` int NOT NULL,
  `FechaInicio` date NOT NULL,
  `FechaFin` date NULL DEFAULT NULL,
  `IdEstadoActividad` int NOT NULL,
	`IdTipoActividad` int NOT NULL,
  `IdEstudiante` int NULL DEFAULT NULL,
  `IdResponsable` int NOT NULL,
  PRIMARY KEY (`IdActividad`) USING BTREE,
  INDEX `IdEstadoActividad`(`IdEstadoActividad` ASC) USING BTREE,
  INDEX `IdEstudiante`(`IdEstudiante` ASC) USING BTREE,
  INDEX `IdResponsable`(`IdResponsable` ASC) USING BTREE,
  CONSTRAINT `actividad_ibfk_1` FOREIGN KEY (`IdEstadoActividad`) REFERENCES `estadoactividad` (`IdEstadoActividad`) ON DELETE RESTRICT ON UPDATE RESTRICT,
	CONSTRAINT `actividad_ibfk_2` FOREIGN KEY (`IdTipoActividad`) REFERENCES `tipoactividad` (`IdTipoActividad`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `actividad_ibfk_3` FOREIGN KEY (`IdEstudiante`) REFERENCES `estudiante` (`IdEstudiante`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `actividad_ibfk_4` FOREIGN KEY (`IdResponsable`) REFERENCES `responsableproyecto` (`IdResponsableProyecto`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of actividad
-- ----------------------------

-- ----------------------------
-- Table structure for cambio
-- ----------------------------
DROP TABLE IF EXISTS `cambio`;
CREATE TABLE `cambio`  (
  `idCambio` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Descripcion` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `IdEstadoActividad` int NOT NULL,
  `idSolicitud` int NOT NULL,
  PRIMARY KEY (`idCambio`) USING BTREE,
  INDEX `IdEstadoActividad`(`IdEstadoActividad` ASC) USING BTREE,
  INDEX `idSolicitud`(`idSolicitud` ASC) USING BTREE,
  CONSTRAINT `cambio_ibfk_1` FOREIGN KEY (`IdEstadoActividad`) REFERENCES `estadoactividad` (`IdEstadoActividad`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `cambio_ibfk_2` FOREIGN KEY (`idSolicitud`) REFERENCES `solicitudcambios` (`IdSolicitudCambios`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cambio
-- ----------------------------

-- ----------------------------
-- Table structure for defecto
-- ----------------------------
DROP TABLE IF EXISTS `defecto`;
CREATE TABLE `defecto`  (
  `IdDefecto` int NOT NULL AUTO_INCREMENT,
  `Titulo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Descripcion` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `EsfuerzoMinutos` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `FechaReporte` date NOT NULL,
  `IdEstadoActividad` int NOT NULL,
  `IdEstudiante` int NOT NULL,
  PRIMARY KEY (`IdDefecto`) USING BTREE,
  INDEX `IdEstadoActividad`(`IdEstadoActividad` ASC) USING BTREE,
  INDEX `IdEstudiante`(`IdEstudiante` ASC) USING BTREE,
  CONSTRAINT `defecto_ibfk_1` FOREIGN KEY (`IdEstadoActividad`) REFERENCES `estadoactividad` (`IdEstadoActividad`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `defecto_ibfk_2` FOREIGN KEY (`IdEstudiante`) REFERENCES `estudiante` (`IdEstudiante`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of defecto
-- ----------------------------

-- ----------------------------
-- Table structure for estadoactividad
-- ----------------------------
DROP TABLE IF EXISTS `estadoactividad`;
CREATE TABLE `estadoactividad`  (
  `IdEstadoActividad` int NOT NULL AUTO_INCREMENT,
  `Estado` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`IdEstadoActividad`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of estadoactividad
-- ----------------------------

-- ----------------------------
-- Table structure for tipoactividad
-- ----------------------------
DROP TABLE IF EXISTS `tipoactividad`;
CREATE TABLE `tipoactividad`  (
  `IdTipoActividad` int NOT NULL AUTO_INCREMENT,
  `Tipo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`IdTipoActividad`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of estadoactividad
-- ----------------------------

-- ----------------------------
-- Table structure for estadoestudiante
-- ----------------------------
DROP TABLE IF EXISTS `estadoestudiante`;
CREATE TABLE `estadoestudiante`  (
  `IdEstadoEstudiante` int NOT NULL AUTO_INCREMENT,
  `Estado` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`IdEstadoEstudiante`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of estadoestudiante
-- ----------------------------

-- ----------------------------
-- Table structure for estadosolicitud
-- ----------------------------
DROP TABLE IF EXISTS `estadosolicitud`;
CREATE TABLE `estadosolicitud`  (
  `IdEstadoSolicitud` int NOT NULL AUTO_INCREMENT,
  `Estado` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`IdEstadoSolicitud`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of estadosolicitud
-- ----------------------------

-- ----------------------------
-- Table structure for estudiante
-- ----------------------------
DROP TABLE IF EXISTS `estudiante`;
CREATE TABLE `estudiante`  (
  `IdEstudiante` int NOT NULL AUTO_INCREMENT,
  `Matricula` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Nombre` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `ApellidoPaterno` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `ApellidoMaterno` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `IdEstadoEstudiante` int NOT NULL,
  `Contraseña` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`IdEstudiante`) USING BTREE,
  INDEX `IdEstadoEstudiante`(`IdEstadoEstudiante` ASC) USING BTREE,
  CONSTRAINT `estudiante_ibfk_1` FOREIGN KEY (`IdEstadoEstudiante`) REFERENCES `estadoestudiante` (`IdEstadoEstudiante`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of estudiante
-- ----------------------------

-- ----------------------------
-- Table structure for realiza
-- ----------------------------
DROP TABLE IF EXISTS `realiza`;
CREATE TABLE `realiza`  (
  `IdRealiza` int NOT NULL AUTO_INCREMENT,
  `IdEstudiante` int NOT NULL,
  `IdCambio` int NOT NULL,
  PRIMARY KEY (`IdRealiza`) USING BTREE,
  INDEX `IdEstudiante`(`IdEstudiante` ASC) USING BTREE,
  INDEX `IdCambio`(`IdCambio` ASC) USING BTREE,
  CONSTRAINT `realiza_ibfk_1` FOREIGN KEY (`IdEstudiante`) REFERENCES `estudiante` (`IdEstudiante`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `realiza_ibfk_2` FOREIGN KEY (`IdCambio`) REFERENCES `cambio` (`idCambio`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of realiza
-- ----------------------------

-- ----------------------------
-- Table structure for responsableproyecto
-- ----------------------------
DROP TABLE IF EXISTS `responsableproyecto`;
CREATE TABLE `responsableproyecto`  (
  `IdResponsableProyecto` int NOT NULL AUTO_INCREMENT,
  `NumPersonal` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Nombre` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `ApellidoPaterno` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `ApellidoMaterno` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `Correo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Telefono` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `Contraseña` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`IdResponsableProyecto`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of responsableproyecto
-- ----------------------------

-- ----------------------------
-- Table structure for solicitudcambios
-- ----------------------------
DROP TABLE IF EXISTS `solicitudcambios`;
CREATE TABLE `solicitudcambios`  (
  `IdSolicitudCambios` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Descripcion` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Razon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Impacto` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Propuesta` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `FechaCreacion` date NOT NULL,
  `IdEstadoSolicitud` int NOT NULL,
  `IdEstudiante` int NULL DEFAULT NULL,
  `IdResponsable` int NULL DEFAULT NULL, 
  PRIMARY KEY (`IdSolicitudCambios`) USING BTREE,
  INDEX `IdEstadoSolicitud`(`IdEstadoSolicitud` ASC) USING BTREE,
  INDEX `IdEstudiante`(`IdEstudiante` ASC) USING BTREE,
  INDEX `IdResponsable`(`IdResponsable` ASC) USING BTREE,
  CONSTRAINT `solicitudcambios_ibfk_1` FOREIGN KEY (`IdEstadoSolicitud`) REFERENCES `estadosolicitud` (`IdEstadoSolicitud`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `solicitudcambios_ibfk_2` FOREIGN KEY (`IdEstudiante`) REFERENCES `estudiante` (`IdEstudiante`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `solicitudcambios_ibfk_3` FOREIGN KEY (`IdResponsable`) REFERENCES `responsableproyecto` (`IdResponsableProyecto`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;
-- ----------------------------
-- Records of solicitudcambios
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
