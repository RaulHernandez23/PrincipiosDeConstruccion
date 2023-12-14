/*
 Navicat Premium Data Transfer

 Source Server         : sgbp.mariadb.database.azure.com_3306
 Source Server Type    : MariaDB
 Source Server Version : 100323 (10.3.23-MariaDB-log)
 Source Host           : sgbp.mariadb.database.azure.com:3306
 Source Schema         : sgbp

 Target Server Type    : MariaDB
 Target Server Version : 100323 (10.3.23-MariaDB-log)
 File Encoding         : 65001

 Date: 14/12/2023 00:52:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for actividad
-- ----------------------------
DROP TABLE IF EXISTS `actividad`;
CREATE TABLE `actividad`  (
  `idActividad` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `descripcion` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `esfuerzoMinutos` int(11) NULL DEFAULT NULL,
  `fechaInicio` date NOT NULL,
  `fechaFin` date NULL DEFAULT NULL,
  `idEstadoActividad` int(11) NOT NULL,
  `idTipoActividad` int(11) NOT NULL,
  `idEstudiante` int(11) NULL DEFAULT NULL,
  `idResponsable` int(11) NOT NULL,
  `idProyecto` int(11) NOT NULL,
  PRIMARY KEY (`idActividad`) USING BTREE,
  INDEX `fk_actividad_proyecto`(`idProyecto`) USING BTREE,
  INDEX `fk_actividad_estadoActividad`(`idEstadoActividad`) USING BTREE,
  INDEX `fk_actividad_tipoActividad`(`idTipoActividad`) USING BTREE,
  INDEX `fk_actividad_estudiante`(`idEstudiante`) USING BTREE,
  INDEX `fk_actividad_responsableProyecto`(`idResponsable`) USING BTREE,
  CONSTRAINT `fk_actividad_estadoActividad` FOREIGN KEY (`idEstadoActividad`) REFERENCES `estadoactividad` (`idEstadoActividad`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_actividad_estudiante` FOREIGN KEY (`idEstudiante`) REFERENCES `estudiante` (`idEstudiante`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_actividad_proyecto` FOREIGN KEY (`idProyecto`) REFERENCES `proyecto` (`idProyecto`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_actividad_responsableProyecto` FOREIGN KEY (`idResponsable`) REFERENCES `responsableproyecto` (`idResponsableProyecto`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_actividad_tipoActividad` FOREIGN KEY (`idTipoActividad`) REFERENCES `tipoactividad` (`idTipoActividad`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of actividad
-- ----------------------------
INSERT INTO `actividad` VALUES (1, 'Plan de pruebas', 'creacion del plan de pruebas de las interfaces', 60, '2023-08-15', '2023-08-16', 1, 1, 2, 1, 1);
INSERT INTO `actividad` VALUES (2, 'Creacion de requerimientos', 'Identificacion de requerimientos del cliente', NULL, '2023-09-20', NULL, 2, 2, 1, 1, 1);
INSERT INTO `actividad` VALUES (4, 'Clase Empresa', 'Creacion de la clase Empresa', 240, '2023-10-25', '2023-10-26', 1, 4, 1, 1, 1);
INSERT INTO `actividad` VALUES (5, 'Diagramas Actividad', 'Creacion de diagrama de actividad de Arquitecto', NULL, '2023-11-05', NULL, 2, 5, 3, 1, 1);
INSERT INTO `actividad` VALUES (6, 'base de datos', 'Creacion de la base de datos', 0, '2023-12-13', '2023-12-14', 1, 2, 1, 1, 1);
INSERT INTO `actividad` VALUES (7, 'Diagramas Actividad', 'Creacion de diagrama de actividad de artista', NULL, '2023-12-13', NULL, 3, 3, 2, 1, 1);
INSERT INTO `actividad` VALUES (9, 'Clase Artista', 'La clase \"Artista\" será responsable de almacenar información detallada sobre los artistas, lo que incluye un nombre, biografía y contacto. Realiza los métodos necesarios para realizar la venta de una obra a la galería.', 100, '2023-12-14', '2023-12-14', 1, 1, NULL, 1, 1);
INSERT INTO `actividad` VALUES (10, 'Clase Arquitecto', 'Crear la clase arquitecto en la base de datos para poder usarla dentro del programa', NULL, '2023-12-14', NULL, 3, 2, 1, 1, 1);

-- ----------------------------
-- Table structure for cambio
-- ----------------------------
DROP TABLE IF EXISTS `cambio`;
CREATE TABLE `cambio`  (
  `idCambio` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `descripcion` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `esfuerzoMinutos` int(11) NULL DEFAULT NULL,
  `idEstadoCambio` int(11) NOT NULL,
  `fechaInicio` date NOT NULL,
  `fechaFin` date NULL DEFAULT NULL,
  `idSolicitudDeCambio` int(11) NOT NULL,
  `idTipoActividad` int(11) NOT NULL,
  `idProyecto` int(11) NOT NULL,
  PRIMARY KEY (`idCambio`) USING BTREE,
  INDEX `fk_cambio_estadoCambio`(`idEstadoCambio`) USING BTREE,
  INDEX `fk_cambio_solicitudDeCambio`(`idSolicitudDeCambio`) USING BTREE,
  INDEX `fk_cambio_tipoActividad`(`idTipoActividad`) USING BTREE,
  INDEX `fk_cambio_proyecto`(`idProyecto`) USING BTREE,
  CONSTRAINT `fk_cambio_estadoCambio` FOREIGN KEY (`idEstadoCambio`) REFERENCES `estadocambio` (`idEstadoCambio`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_cambio_proyecto` FOREIGN KEY (`idProyecto`) REFERENCES `proyecto` (`idProyecto`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_cambio_solicitudDeCambio` FOREIGN KEY (`idSolicitudDeCambio`) REFERENCES `solicituddecambio` (`idSolicitudDeCambio`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_cambio_tipoActividad` FOREIGN KEY (`idTipoActividad`) REFERENCES `tipoactividad` (`idTipoActividad`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cambio
-- ----------------------------
INSERT INTO `cambio` VALUES (1, 'Escritura poco practica', 'Combinacion del camel case, pascal y snake', 60, 1, '2023-08-01', '2023-08-02', 1, 1, 1);
INSERT INTO `cambio` VALUES (2, 'Modificar tabla', 'Modificar la tabla mamiferos', NULL, 1, '2023-08-03', '2023-12-13', 1, 2, 1);
INSERT INTO `cambio` VALUES (3, 'Actualizar requisitos', 'El cliente pidio nuevos requisitos por lo cual se debe de actualizar el documento de requisitos', 180, 1, '2023-08-05', '2023-08-06', 1, 3, 1);
INSERT INTO `cambio` VALUES (4, 'Agregar atributo', 'Agregar atributo de la familia de los mamiferos', NULL, 2, '2023-08-07', NULL, 2, 3, 1);
INSERT INTO `cambio` VALUES (5, 'Actulizar Clase ', 'Se debe de actulizar la clase de Arquitecto ya que presenta errores puesto que pone atributos publicos', 300, 1, '2023-08-09', '2023-08-10', 3, 1, 1);
INSERT INTO `cambio` VALUES (8, 'Mejorar prototipos', 'Los prototipos del usuario presentan deficiencias, no se muestra el diseño', 30, 1, '2023-12-14', NULL, 2, 3, 1);
INSERT INTO `cambio` VALUES (9, 'Actualizar tabla ObraDonada', 'Agregar un campo más a la tabla \"ObraDonada\" para permitir al cliente elegir a que organización irán las ganancias recaudadas en la subasta de la obra de arte.', 60, 1, '2023-12-14', NULL, 4, 2, 1);

-- ----------------------------
-- Table structure for defecto
-- ----------------------------
DROP TABLE IF EXISTS `defecto`;
CREATE TABLE `defecto`  (
  `idDefecto` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `descripcion` varchar(500) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `esfuerzoMinutos` int(11) NULL DEFAULT NULL,
  `fechaReporte` date NOT NULL,
  `fechaFin` date NULL DEFAULT NULL,
  `idEstadoDefecto` int(11) NOT NULL,
  `idEstudiante` int(11) NOT NULL,
  `idProyecto` int(11) NOT NULL,
  PRIMARY KEY (`idDefecto`) USING BTREE,
  INDEX `fk_defecto_estadoDefecto`(`idEstadoDefecto`) USING BTREE,
  INDEX `fk_defecto_estudiante`(`idEstudiante`) USING BTREE,
  INDEX `fk_defecto_proyecto`(`idProyecto`) USING BTREE,
  CONSTRAINT `fk_defecto_estadoDefecto` FOREIGN KEY (`idEstadoDefecto`) REFERENCES `estadodefecto` (`idEstadoDefecto`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_defecto_estudiante` FOREIGN KEY (`idEstudiante`) REFERENCES `estudiante` (`idEstudiante`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_defecto_proyecto` FOREIGN KEY (`idProyecto`) REFERENCES `proyecto` (`idProyecto`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of defecto
-- ----------------------------
INSERT INTO `defecto` VALUES (1, 'Error de implementacion', 'No se despliega como se mostraba en pruebas', 60, '2023-08-01', '2023-08-02', 1, 1, 1);
INSERT INTO `defecto` VALUES (2, 'Error de conexion', 'La base de datos no se conecta como deberia', 120, '2023-08-03', '2023-08-04', 1, 2, 1);
INSERT INTO `defecto` VALUES (3, 'Fallo de implementacion', 'Error al establecer los permisos del api a la base de datos', NULL, '2023-08-05', NULL, 2, 1, 1);
INSERT INTO `defecto` VALUES (4, 'Despliegue insuficiente', 'No se puede acceder al DAO mamiferos', 240, '2023-08-07', '2023-08-08', 1, 3, 1);
INSERT INTO `defecto` VALUES (5, 'Diseños incompleto', 'Se necesita que se especifiquen mejor los casos de uso del diseño', NULL, '2023-08-09', NULL, 2, 3, 1);
INSERT INTO `defecto` VALUES (8, 'Error en la base de datos en tabla Perro', 'El perro debe relacionarse con el dueño y con su veterinario', NULL, '2023-12-14', NULL, 2, 2, 1);

-- ----------------------------
-- Table structure for estadoactividad
-- ----------------------------
DROP TABLE IF EXISTS `estadoactividad`;
CREATE TABLE `estadoactividad`  (
  `idEstadoActividad` int(11) NOT NULL AUTO_INCREMENT,
  `estado` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`idEstadoActividad`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of estadoactividad
-- ----------------------------
INSERT INTO `estadoactividad` VALUES (1, 'Realizada');
INSERT INTO `estadoactividad` VALUES (2, 'No realizada');
INSERT INTO `estadoactividad` VALUES (3, 'No asignada');

-- ----------------------------
-- Table structure for estadocambio
-- ----------------------------
DROP TABLE IF EXISTS `estadocambio`;
CREATE TABLE `estadocambio`  (
  `idEstadoCambio` int(11) NOT NULL AUTO_INCREMENT,
  `estado` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`idEstadoCambio`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of estadocambio
-- ----------------------------
INSERT INTO `estadocambio` VALUES (1, 'Realizado');
INSERT INTO `estadocambio` VALUES (2, 'No realizado');

-- ----------------------------
-- Table structure for estadodefecto
-- ----------------------------
DROP TABLE IF EXISTS `estadodefecto`;
CREATE TABLE `estadodefecto`  (
  `idEstadoDefecto` int(11) NOT NULL AUTO_INCREMENT,
  `estado` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`idEstadoDefecto`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of estadodefecto
-- ----------------------------
INSERT INTO `estadodefecto` VALUES (1, 'Realizado');
INSERT INTO `estadodefecto` VALUES (2, 'No realizado');
INSERT INTO `estadodefecto` VALUES (3, 'En proceso');

-- ----------------------------
-- Table structure for estadoestudiante
-- ----------------------------
DROP TABLE IF EXISTS `estadoestudiante`;
CREATE TABLE `estadoestudiante`  (
  `idEstadoEstudiante` int(11) NOT NULL AUTO_INCREMENT,
  `estado` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`idEstadoEstudiante`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of estadoestudiante
-- ----------------------------
INSERT INTO `estadoestudiante` VALUES (1, 'Activo');
INSERT INTO `estadoestudiante` VALUES (2, 'Inactivo');

-- ----------------------------
-- Table structure for estadoproyecto
-- ----------------------------
DROP TABLE IF EXISTS `estadoproyecto`;
CREATE TABLE `estadoproyecto`  (
  `idEstadoProyecto` int(11) NOT NULL AUTO_INCREMENT,
  `estado` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`idEstadoProyecto`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of estadoproyecto
-- ----------------------------
INSERT INTO `estadoproyecto` VALUES (1, 'Activo');
INSERT INTO `estadoproyecto` VALUES (2, 'Finalizado');

-- ----------------------------
-- Table structure for estadosolicitud
-- ----------------------------
DROP TABLE IF EXISTS `estadosolicitud`;
CREATE TABLE `estadosolicitud`  (
  `idEstadoSolicitud` int(11) NOT NULL AUTO_INCREMENT,
  `estado` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`idEstadoSolicitud`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of estadosolicitud
-- ----------------------------
INSERT INTO `estadosolicitud` VALUES (1, 'Aprobada');
INSERT INTO `estadosolicitud` VALUES (2, 'Rechazada');
INSERT INTO `estadosolicitud` VALUES (3, 'Pendiente');

-- ----------------------------
-- Table structure for estudiante
-- ----------------------------
DROP TABLE IF EXISTS `estudiante`;
CREATE TABLE `estudiante`  (
  `idEstudiante` int(11) NOT NULL AUTO_INCREMENT,
  `matricula` varchar(9) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `nombre` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `apellidoPaterno` varchar(40) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `apellidoMaterno` varchar(40) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `password` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `idEstadoEstudiante` int(11) NOT NULL,
  `idProyecto` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`idEstudiante`) USING BTREE,
  UNIQUE INDEX `matricula`(`matricula`) USING BTREE,
  INDEX `fk_estudiante_estadoEstudiante`(`idEstadoEstudiante`) USING BTREE,
  INDEX `fk_estudiante_proyecto`(`idProyecto`) USING BTREE,
  CONSTRAINT `fk_estudiante_estadoEstudiante` FOREIGN KEY (`idEstadoEstudiante`) REFERENCES `estadoestudiante` (`idEstadoEstudiante`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_estudiante_proyecto` FOREIGN KEY (`idProyecto`) REFERENCES `proyecto` (`idProyecto`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of estudiante
-- ----------------------------
INSERT INTO `estudiante` VALUES (1, 's21013876', 'Albhieri Cristoff', 'Villa', 'Contreras', 'radisa94', 1, 1);
INSERT INTO `estudiante` VALUES (2, 'S21026432', 'Raul', 'Hernandez', 'Olivares', 'pass', 1, 1);
INSERT INTO `estudiante` VALUES (3, 'S21026433', 'Miguel', 'Morales', 'Cruz', 'miguelMorales', 1, 1);
INSERT INTO `estudiante` VALUES (4, 'S21026430', 'Camila', 'Morales', 'Cruz', 'camilaMorales', 1, 1);
INSERT INTO `estudiante` VALUES (9, 's22010001', 'Julian', 'Lopez', 'Garrido', 'julianLopez', 1, 1);
INSERT INTO `estudiante` VALUES (11, 'S21026436', 'Jorge', 'Perez', 'Lopez', 'jorgePerez', 1, 1);
INSERT INTO `estudiante` VALUES (12, 'S21026437', 'dcwew', 'dxew', 'edw', 'dcwewdxew', 2, NULL);
INSERT INTO `estudiante` VALUES (17, 'S21057433', 'sfcefe', 'ceed', 'cedcedc', 'sfcefeceed', 1, 1);
INSERT INTO `estudiante` VALUES (18, 'S21036548', 'fecec', 'ecdc', 'edced', 'fecececdc', 1, 1);
INSERT INTO `estudiante` VALUES (20, 'S22046538', 'Luis', 'Perez', 'Martinez', 'luisPerez', 1, 1);
INSERT INTO `estudiante` VALUES (25, 'S09864792', 'Prueba', 'cedcew', 'cewdcew', 'pruebacedcew', 1, 1);
INSERT INTO `estudiante` VALUES (31, 'S21874540', 'dfvedfve', 'evefver', 'erverv', 'dfvedfveevefver', 1, 1);
INSERT INTO `estudiante` VALUES (35, 'S74834566', 'Estudiante', 'Prueba', 'Prueba', 'estudiantePrueba', 1, 1);
INSERT INTO `estudiante` VALUES (36, 's21010002', 'Ramona', 'Lima', 'Salinas', 'ramonaLima', 1, 1);

-- ----------------------------
-- Table structure for estudiante_cambio
-- ----------------------------
DROP TABLE IF EXISTS `estudiante_cambio`;
CREATE TABLE `estudiante_cambio`  (
  `idEstudianteCambio` int(11) NOT NULL AUTO_INCREMENT,
  `idEstudiante` int(11) NOT NULL,
  `idCambio` int(11) NOT NULL,
  PRIMARY KEY (`idEstudianteCambio`) USING BTREE,
  INDEX `fk_estudiantecambio_estudiante`(`idEstudiante`) USING BTREE,
  INDEX `fk_estudiantecambio_cambio`(`idCambio`) USING BTREE,
  CONSTRAINT `fk_estudiantecambio_cambio` FOREIGN KEY (`idCambio`) REFERENCES `cambio` (`idCambio`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_estudiantecambio_estudiante` FOREIGN KEY (`idEstudiante`) REFERENCES `estudiante` (`idEstudiante`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of estudiante_cambio
-- ----------------------------
INSERT INTO `estudiante_cambio` VALUES (1, 2, 1);
INSERT INTO `estudiante_cambio` VALUES (2, 2, 2);
INSERT INTO `estudiante_cambio` VALUES (3, 2, 3);
INSERT INTO `estudiante_cambio` VALUES (4, 1, 1);
INSERT INTO `estudiante_cambio` VALUES (5, 1, 2);
INSERT INTO `estudiante_cambio` VALUES (6, 1, 4);
INSERT INTO `estudiante_cambio` VALUES (7, 1, 5);
INSERT INTO `estudiante_cambio` VALUES (8, 3, 3);
INSERT INTO `estudiante_cambio` VALUES (10, 1, 8);
INSERT INTO `estudiante_cambio` VALUES (11, 1, 9);

-- ----------------------------
-- Table structure for estudiante_periodoescolar
-- ----------------------------
DROP TABLE IF EXISTS `estudiante_periodoescolar`;
CREATE TABLE `estudiante_periodoescolar`  (
  `idEstudiantePeriodoEscolar` int(11) NOT NULL AUTO_INCREMENT,
  `idEstudiante` int(11) NOT NULL,
  `idPeriodoEscolar` int(11) NOT NULL,
  PRIMARY KEY (`idEstudiantePeriodoEscolar`) USING BTREE,
  INDEX `fk_estudianteperiodoescolar_estudiante`(`idEstudiante`) USING BTREE,
  INDEX `fk_estudianteperiodoescolar_periodoescolar`(`idPeriodoEscolar`) USING BTREE,
  CONSTRAINT `fk_estudianteperiodoescolar_estudiante` FOREIGN KEY (`idEstudiante`) REFERENCES `estudiante` (`idEstudiante`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_estudianteperiodoescolar_periodoescolar` FOREIGN KEY (`idPeriodoEscolar`) REFERENCES `periodoescolar` (`idPeriodoEscolar`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of estudiante_periodoescolar
-- ----------------------------
INSERT INTO `estudiante_periodoescolar` VALUES (1, 2, 1);
INSERT INTO `estudiante_periodoescolar` VALUES (2, 1, 2);
INSERT INTO `estudiante_periodoescolar` VALUES (3, 2, 2);
INSERT INTO `estudiante_periodoescolar` VALUES (4, 3, 2);
INSERT INTO `estudiante_periodoescolar` VALUES (7, 11, 2);
INSERT INTO `estudiante_periodoescolar` VALUES (11, 18, 2);
INSERT INTO `estudiante_periodoescolar` VALUES (12, 4, 2);
INSERT INTO `estudiante_periodoescolar` VALUES (13, 20, 2);
INSERT INTO `estudiante_periodoescolar` VALUES (14, 4, 2);
INSERT INTO `estudiante_periodoescolar` VALUES (15, 4, 2);
INSERT INTO `estudiante_periodoescolar` VALUES (16, 4, 2);
INSERT INTO `estudiante_periodoescolar` VALUES (17, 4, 2);
INSERT INTO `estudiante_periodoescolar` VALUES (18, 25, 2);
INSERT INTO `estudiante_periodoescolar` VALUES (19, 4, 2);
INSERT INTO `estudiante_periodoescolar` VALUES (20, 31, 2);
INSERT INTO `estudiante_periodoescolar` VALUES (21, 4, 2);
INSERT INTO `estudiante_periodoescolar` VALUES (22, 35, 2);
INSERT INTO `estudiante_periodoescolar` VALUES (23, 36, 2);

-- ----------------------------
-- Table structure for periodoescolar
-- ----------------------------
DROP TABLE IF EXISTS `periodoescolar`;
CREATE TABLE `periodoescolar`  (
  `idPeriodoEscolar` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(70) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `fechaInicio` date NOT NULL,
  `fechaFin` date NOT NULL,
  PRIMARY KEY (`idPeriodoEscolar`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of periodoescolar
-- ----------------------------
INSERT INTO `periodoescolar` VALUES (1, 'Febrero-Julio 23', '2023-02-01', '2023-07-31');
INSERT INTO `periodoescolar` VALUES (2, 'Agosto-Enero 23/24', '2023-08-01', '2023-01-31');

-- ----------------------------
-- Table structure for proyecto
-- ----------------------------
DROP TABLE IF EXISTS `proyecto`;
CREATE TABLE `proyecto`  (
  `idProyecto` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `descripcion` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `idEstadoProyecto` int(11) NOT NULL,
  PRIMARY KEY (`idProyecto`) USING BTREE,
  INDEX `fk_proyecto_estadoproyecto`(`idEstadoProyecto`) USING BTREE,
  CONSTRAINT `fk_proyecto_estadoproyecto` FOREIGN KEY (`idEstadoProyecto`) REFERENCES `estadoproyecto` (`idEstadoProyecto`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of proyecto
-- ----------------------------
INSERT INTO `proyecto` VALUES (1, 'VaraWeb', 'Aplicación web VaraWeb para el reporte de varamientos de mamíferos marínos', 1);

-- ----------------------------
-- Table structure for proyecto_periodoescolar
-- ----------------------------
DROP TABLE IF EXISTS `proyecto_periodoescolar`;
CREATE TABLE `proyecto_periodoescolar`  (
  `idProyectoPeriodoEscolar` int(11) NOT NULL AUTO_INCREMENT,
  `idProyecto` int(11) NOT NULL,
  `idPeriodoEscolar` int(11) NOT NULL,
  PRIMARY KEY (`idProyectoPeriodoEscolar`) USING BTREE,
  INDEX `fk_proyectoperiodoescolar_proyecto`(`idProyecto`) USING BTREE,
  INDEX `fk_proyectoperiodoescolar_periodoescolar`(`idPeriodoEscolar`) USING BTREE,
  CONSTRAINT `fk_proyectoperiodoescolar_periodoescolar` FOREIGN KEY (`idPeriodoEscolar`) REFERENCES `periodoescolar` (`idPeriodoEscolar`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_proyectoperiodoescolar_proyecto` FOREIGN KEY (`idProyecto`) REFERENCES `proyecto` (`idProyecto`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of proyecto_periodoescolar
-- ----------------------------
INSERT INTO `proyecto_periodoescolar` VALUES (1, 1, 1);
INSERT INTO `proyecto_periodoescolar` VALUES (2, 1, 2);

-- ----------------------------
-- Table structure for responsableproyecto
-- ----------------------------
DROP TABLE IF EXISTS `responsableproyecto`;
CREATE TABLE `responsableproyecto`  (
  `idResponsableProyecto` int(11) NOT NULL AUTO_INCREMENT,
  `numPersonal` varchar(39) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `nombre` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `apellidoPaterno` varchar(40) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `apellidoMaterno` varchar(40) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `correo` varchar(60) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `telefono` varchar(15) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `password` varchar(40) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`idResponsableProyecto`) USING BTREE,
  UNIQUE INDEX `numPersonal`(`numPersonal`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of responsableproyecto
-- ----------------------------
INSERT INTO `responsableproyecto` VALUES (1, 'EMP001', 'Juan Carlos', 'Perez', 'Arriaga', 'correo@ejemplo.com', '2281130978', 'radisa94');
INSERT INTO `responsableproyecto` VALUES (2, 'EMP002', 'Ana Luz', 'Polo', 'Estrella', 'correo2@ejemplo.com', '2283456723', 'radisa94');

-- ----------------------------
-- Table structure for responsableproyecto_proyecto
-- ----------------------------
DROP TABLE IF EXISTS `responsableproyecto_proyecto`;
CREATE TABLE `responsableproyecto_proyecto`  (
  `idResponsableProyectoProyecto` int(11) NOT NULL AUTO_INCREMENT,
  `idResponsableProyecto` int(11) NOT NULL,
  `idProyecto` int(11) NOT NULL,
  PRIMARY KEY (`idResponsableProyectoProyecto`) USING BTREE,
  INDEX `fk_responsableproyectoproyecto_responsableproyecto`(`idResponsableProyecto`) USING BTREE,
  INDEX `fk_responsableproyectoproyecto_proyecto`(`idProyecto`) USING BTREE,
  CONSTRAINT `fk_responsableproyectoproyecto_proyecto` FOREIGN KEY (`idProyecto`) REFERENCES `proyecto` (`idProyecto`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_responsableproyectoproyecto_responsableproyecto` FOREIGN KEY (`idResponsableProyecto`) REFERENCES `responsableproyecto` (`idResponsableProyecto`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of responsableproyecto_proyecto
-- ----------------------------
INSERT INTO `responsableproyecto_proyecto` VALUES (1, 1, 1);

-- ----------------------------
-- Table structure for solicituddecambio
-- ----------------------------
DROP TABLE IF EXISTS `solicituddecambio`;
CREATE TABLE `solicituddecambio`  (
  `idSolicitudDeCambio` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `descripcion` varchar(500) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `razon` varchar(500) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `impacto` varchar(500) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `accionPropuesta` varchar(500) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `fechaCreacion` date NOT NULL,
  `fechaEvaluacion` date NULL DEFAULT NULL,
  `idEstudiante` int(11) NOT NULL,
  `idEstadoSolicitud` int(11) NOT NULL,
  `idProyecto` int(11) NOT NULL,
  `idResponsableProyecto` int(11) NULL DEFAULT NULL,
  `idDefecto` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`idSolicitudDeCambio`) USING BTREE,
  UNIQUE INDEX `uk_titulo`(`titulo`) USING BTREE,
  INDEX `fk_solicituddecambio_estudiante`(`idEstudiante`) USING BTREE,
  INDEX `fk_solicituddecambio_estadoSolicitud`(`idEstadoSolicitud`) USING BTREE,
  INDEX `fk_solicituddecambio_proyecto`(`idProyecto`) USING BTREE,
  INDEX `fk_solicituddecambio_responsableproyecto`(`idResponsableProyecto`) USING BTREE,
  INDEX `fk_solicituddecambio_defecto`(`idDefecto`) USING BTREE,
  CONSTRAINT `fk_solicituddecambio_defecto` FOREIGN KEY (`idDefecto`) REFERENCES `defecto` (`idDefecto`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_solicituddecambio_estadoSolicitud` FOREIGN KEY (`idEstadoSolicitud`) REFERENCES `estadosolicitud` (`idEstadoSolicitud`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_solicituddecambio_estudiante` FOREIGN KEY (`idEstudiante`) REFERENCES `estudiante` (`idEstudiante`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_solicituddecambio_proyecto` FOREIGN KEY (`idProyecto`) REFERENCES `proyecto` (`idProyecto`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_solicituddecambio_responsableproyecto` FOREIGN KEY (`idResponsableProyecto`) REFERENCES `responsableproyecto` (`idResponsableProyecto`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of solicituddecambio
-- ----------------------------
INSERT INTO `solicituddecambio` VALUES (1, 'Modificar diseño', 'El usuario solicita meterle mas diseño', 'poco diseño', 'gustos del cliente', 'Crearle mas colores', '2023-08-01', '2023-08-02', 2, 1, 1, 1, 1);
INSERT INTO `solicituddecambio` VALUES (2, 'Botones poco eficaces', 'los botones se quieren cambiar a azul', 'no se ven lindos en el programa', 'gustos del cliente', 'cambio de color', '2023-08-03', '2023-08-04', 1, 2, 1, 1, 1);
INSERT INTO `solicituddecambio` VALUES (3, 'actualizar cliente', 'Se nombro un cliente equivocado', 'no se copio el nombre del ine', 'estructura del contrato', 'analizar el INE del cliente', '2023-08-05', '2023-08-06', 3, 1, 1, 1, 2);
INSERT INTO `solicituddecambio` VALUES (4, 'Solicitud de prueba', 'Faltaron mas pruebas', 'Se tenia poco tiempo', 'Razon simple', 'Solicitar mas tiempo para pruebas', '2023-12-13', '2023-12-13', 3, 1, 1, 1, 1);
INSERT INTO `solicituddecambio` VALUES (5, 'Solicitud de mas tiempo', 'Se necesita mas tiempo para la entrega de actividad', 'poco tiempo de realizacion', 'Descripcion simple ', 'mas tiempo', '2023-12-13', '2023-12-13', 3, 2, 1, 1, 1);

-- ----------------------------
-- Table structure for tipoactividad
-- ----------------------------
DROP TABLE IF EXISTS `tipoactividad`;
CREATE TABLE `tipoactividad`  (
  `idTipoActividad` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` varchar(40) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`idTipoActividad`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tipoactividad
-- ----------------------------
INSERT INTO `tipoactividad` VALUES (1, 'Archivos Javascript');
INSERT INTO `tipoactividad` VALUES (2, 'Base de Datos');
INSERT INTO `tipoactividad` VALUES (3, 'Vistas');
INSERT INTO `tipoactividad` VALUES (4, 'Controladores');
INSERT INTO `tipoactividad` VALUES (5, 'Otro Archivo');

SET FOREIGN_KEY_CHECKS = 1;
