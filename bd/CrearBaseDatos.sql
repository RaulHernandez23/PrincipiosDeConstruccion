DROP DATABASE IF EXISTS sgbp;
CREATE DATABASE sgbp;
USE sgbp;
SET FOREIGN_KEY_CHECKS = 0;

-- Crear tabla EstadoProyecto
DROP TABLE IF EXISTS `EstadoProyecto`;
CREATE TABLE `EstadoProyecto` (
    `idEstadoProyecto` INT NOT NULL AUTO_INCREMENT,
    `estado` VARCHAR(255) NOT NULL,

    PRIMARY KEY (`idEstadoProyecto`)
);

-- Crear tabla Proyecto
DROP TABLE IF EXISTS `Proyecto`;
CREATE TABLE `Proyecto` (
    `idProyecto` INT NOT NULL AUTO_INCREMENT,
    `nombre` VARCHAR(255) NOT NULL,
    `descripcion` VARCHAR(255) NOT NULL,
    `idEstadoProyecto` INT NOT NULL,

    PRIMARY KEY (`idProyecto`),

    CONSTRAINT `fk_proyecto_estadoproyecto` FOREIGN KEY (`idEstadoProyecto`) REFERENCES `EstadoProyecto`(`idEstadoProyecto`)
);

-- Crear tabla Actividad
DROP TABLE IF EXISTS `Actividad`;
CREATE TABLE `Actividad` (
    `idActividad` INT NOT NULL AUTO_INCREMENT,
    `titulo` VARCHAR(255) NOT NULL,
    `descripcion` VARCHAR(255) NOT NULL,
    `esfuerzoMinutos` INT NULL DEFAULT NULL,
    `fechaInicio` date NOT NULL,
    `fechaFin` date NULL DEFAULT NULL,
    `idEstadoActividad` INT NOT NULL,
    `idTipoActividad` INT NOT NULL,
    `idEstudiante` INT NULL DEFAULT NULL,
    `idResponsable` INT NOT NULL,
    `idProyecto` INT NOT NULL,

    PRIMARY KEY (`idActividad`),

    CONSTRAINT `fk_actividad_proyecto` FOREIGN KEY (`idProyecto`) REFERENCES `proyecto`(`idProyecto`),
    CONSTRAINT `fk_actividad_estadoActividad` FOREIGN KEY (`idEstadoActividad`) REFERENCES `estadoActividad`(`idEstadoActividad`),
    CONSTRAINT `fk_actividad_tipoActividad` FOREIGN KEY (`idTipoActividad`) REFERENCES `tipoActividad`(`idTipoActividad`),
    CONSTRAINT `fk_actividad_estudiante` FOREIGN KEY (`idEstudiante`) REFERENCES `estudiante`(`idEstudiante`),
    CONSTRAINT `fk_actividad_responsableProyecto` FOREIGN KEY (`idResponsable`) REFERENCES `responsableProyecto`(`idResponsableProyecto`)
);

-- Crear tabla EstadoActividad
DROP TABLE IF EXISTS `EstadoActividad`;
CREATE TABLE `EstadoActividad` (
    `idEstadoActividad` INT NOT NULL AUTO_INCREMENT,
    `estado` VARCHAR(255) NOT NULL,

    PRIMARY KEY (`idEstadoActividad`)
);

-- Crear tabla TipoActividad
DROP TABLE IF EXISTS `TipoActividad`;
CREATE TABLE `TipoActividad` (
    `idTipoActividad` INT NOT NULL AUTO_INCREMENT,
    `tipo` VARCHAR(255) NOT NULL,

    PRIMARY KEY (`idTipoActividad`)
);

-- Crear tabla Cambio
DROP TABLE IF EXISTS `Cambio`;
CREATE TABLE `Cambio` (
    `idCambio` INT NOT NULL AUTO_INCREMENT,
    `titulo` VARCHAR(255) NOT NULL,
    `descripcion` VARCHAR(255) NOT NULL,
    `idEstadoCambio` INT NOT NULL,
    `fechaInicio` DATE NOT NULL,
    `fechaFin` DATE NULL DEFAULT NULL,
    `idSolicitudDeCambio` INT NOT NULL,

    PRIMARY KEY (`idCambio`),

    CONSTRAINT `fk_cambio_estadoCambio` FOREIGN KEY (`idEstadoCambio`) REFERENCES `EstadoCambio`(`idEstadoCambio`),
    CONSTRAINT `fk_cambio_solicitudDeCambio` FOREIGN KEY (`idSolicitudDeCambio`) REFERENCES `SolicitudDeCambio`(`idSolicitudDeCambio`)
);

-- Crear tabla EstadoCambio
DROP TABLE IF EXISTS `EstadoCambio`;
CREATE TABLE `EstadoCambio` (
    `idEstadoCambio` INT NOT NULL AUTO_INCREMENT,
    `estado` VARCHAR(255) NOT NULL,

    PRIMARY KEY (`idEstadoCambio`)
);

-- Crear tabla Defecto
DROP TABLE IF EXISTS `Defecto`;
CREATE TABLE `Defecto` (
    `idDefecto` INT NOT NULL AUTO_INCREMENT,
    `titulo` VARCHAR(255) NOT NULL,
    `descripcion` VARCHAR(255) NOT NULL,
    `esfuerzoMinutos` INT NULL DEFAULT NULL,
    `fechaReporte` DATE NOT NULL,
    `fechaFin` DATE NULL DEFAULT NULL,
    `idEstadoDefecto` INT NOT NULL,
    `idEstudiante` INT NOT NULL,
    `idProyecto` INT NOT NULL,

    PRIMARY KEY (`idDefecto`),

    CONSTRAINT `fk_defecto_estadoDefecto` FOREIGN KEY (`idEstadoDefecto`) REFERENCES `EstadoDefecto`(`idEstadoDefecto`),
    CONSTRAINT `fk_defecto_estudiante` FOREIGN KEY (`idEstudiante`) REFERENCES `Estudiante`(`idEstudiante`),
    CONSTRAINT `fk_defecto_proyecto` FOREIGN KEY (`idProyecto`) REFERENCES `Proyecto`(`idProyecto`)
);

-- Crear tabla EstadoDefecto
DROP TABLE IF EXISTS `EstadoDefecto`;
CREATE TABLE `EstadoDefecto` (
    `idEstadoDefecto` INT NOT NULL AUTO_INCREMENT,
    `estado` VARCHAR(255) NOT NULL,

    PRIMARY KEY (`idEstadoDefecto`)
);

-- Crear tabla Estudiante
DROP TABLE IF EXISTS `Estudiante`;
CREATE TABLE `Estudiante` (
    `idEstudiante` INT NOT NULL AUTO_INCREMENT,
    `matricula` VARCHAR(255) NOT NULL,
    `nombre` VARCHAR(255) NOT NULL,
    `apellidoPaterno` VARCHAR(255) NOT NULL,
    `apellidoMaterno` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `idEstadoEstudiante` INT NOT NULL,
    `idProyecto` INT NULL DEFAULT NULL,

    PRIMARY KEY (`idEstudiante`),

    CONSTRAINT `fk_estudiante_estadoEstudiante` FOREIGN KEY (`idEstadoEstudiante`) REFERENCES `EstadoEstudiante`(`idEstadoEstudiante`),
    CONSTRAINT `fk_estudiante_proyecto` FOREIGN KEY (`idProyecto`) REFERENCES `Proyecto`(`idProyecto`)
);

-- Crear tabla EstadoEstudiante
DROP TABLE IF EXISTS `EstadoEstudiante`;
CREATE TABLE `EstadoEstudiante` (
    `idEstadoEstudiante` INT NOT NULL AUTO_INCREMENT,
    `estado` VARCHAR(255) NOT NULL,

    PRIMARY KEY (`idEstadoEstudiante`)
);

-- Crear tabla Estudiante_PeriodoEscolar
DROP TABLE IF EXISTS `Estudiante_PeriodoEscolar`;
CREATE TABLE `Estudiante_PeriodoEscolar` (
    `idEstudiantePeriodoEscolar` INT NOT NULL AUTO_INCREMENT,
    `idEstudiante` INT NOT NULL,
    `idPeriodoEscolar` INT NOT NULL,

    PRIMARY KEY (`idEstudiantePeriodoEscolar`),

    CONSTRAINT `fk_estudianteperiodoescolar_estudiante` FOREIGN KEY (`idEstudiante`) REFERENCES `Estudiante`(`idEstudiante`),
    CONSTRAINT `fk_estudianteperiodoescolar_periodoescolar` FOREIGN KEY (`idPeriodoEscolar`) REFERENCES `PeriodoEscolar`(`idPeriodoEscolar`)
);

-- Crear tabla Estudiante_Cambio
DROP TABLE IF EXISTS `Estudiante_Cambio`;
CREATE TABLE `Estudiante_Cambio` (
    `idEstudianteCambio` INT NOT NULL AUTO_INCREMENT,
    `idEstudiante` INT NOT NULL,
    `idCambio` INT NOT NULL,
		
		PRIMARY KEY (`idEstudianteCambio`),

    CONSTRAINT `fk_estudiantecambio_estudiante` FOREIGN KEY (`idEstudiante`) REFERENCES `Estudiante`(`idEstudiante`),
    CONSTRAINT `fk_estudiantecambio_cambio` FOREIGN KEY (`idCambio`) REFERENCES `Cambio`(`idCambio`)
);

-- Crear tabla ResponsableProyecto
DROP TABLE IF EXISTS `ResponsableProyecto`;
CREATE TABLE `ResponsableProyecto` (
    `idResponsableProyecto` INT NOT NULL AUTO_INCREMENT,
    `numPersonal` VARCHAR(255) NOT NULL,
    `nombre` VARCHAR(255) NOT NULL,
    `apellidoPaterno` VARCHAR(255) NOT NULL,
    `apellidoMaterno` VARCHAR(255) NOT NULL,
    `correo` VARCHAR(255) NOT NULL,
    `telefono` VARCHAR(255) NOT NULL,
    `password` VARCHAR(40) NOT NULL,

    PRIMARY KEY (`idResponsableProyecto`)
);

-- Crear tabla SolicitudDeCambio
DROP TABLE IF EXISTS `SolicitudDeCambio`;
CREATE TABLE `SolicitudDeCambio` (
    `idSolicitudDeCambio` INT NOT NULL AUTO_INCREMENT,
    `titulo` VARCHAR(255) NOT NULL,
    `descripcion` VARCHAR(1000) NOT NULL,
    `razon` VARCHAR(1000) NOT NULL,
    `impacto` VARCHAR(1000) NOT NULL,
    `accionPropuesta` VARCHAR(1000) NOT NULL,
    `fechaCreacion` DATE NOT NULL,
    `fechaEvaluacion` DATE NULL DEFAULT NULL,
    `idEstudiante` INT NOT NULL,
    `idEstadoSolicitud` INT NOT NULL,
    `idProyecto` INT NOT NULL,
    `idResponsableProyecto` INT NULL DEFAULT NULL,
    `idDefecto` INT NULL DEFAULT NULL,

    PRIMARY KEY (`idSolicitudDeCambio`),

    CONSTRAINT `fk_solicituddecambio_estudiante` FOREIGN KEY (`idEstudiante`) REFERENCES `Estudiante`(`idEstudiante`),
    CONSTRAINT `fk_solicituddecambio_estadoSolicitud` FOREIGN KEY (`idEstadoSolicitud`) REFERENCES `EstadoSolicitud`(`idEstadoSolicitud`),
    CONSTRAINT `fk_solicituddecambio_proyecto` FOREIGN KEY (`idProyecto`) REFERENCES `Proyecto`(`idProyecto`),
    CONSTRAINT `fk_solicituddecambio_responsableproyecto` FOREIGN KEY (`idResponsableProyecto`) REFERENCES `ResponsableProyecto`(`idResponsableProyecto`),
    CONSTRAINT `fk_solicituddecambio_defecto` FOREIGN KEY (`idDefecto`) REFERENCES `Defecto`(`idDefecto`)
);

-- Crear tabla EstadoSolicitud
DROP TABLE IF EXISTS `EstadoSolicitud`;
CREATE TABLE `EstadoSolicitud` (
    `idEstadoSolicitud` INT NOT NULL AUTO_INCREMENT,
    `estado` VARCHAR(255) NOT NULL,

    PRIMARY KEY (`idEstadoSolicitud`)
);

-- Crear tabla PeriodoEscolar
DROP TABLE IF EXISTS `PeriodoEscolar`;
CREATE TABLE `PeriodoEscolar` (
    `idPeriodoEscolar` INT NOT NULL AUTO_INCREMENT,
    `nombre` VARCHAR(255) NOT NULL,
    `fechaInicio` DATE NOT NULL,
    `fechaFin` DATE NOT NULL,

    PRIMARY KEY (`idPeriodoEscolar`)
);


DROP TABLE IF EXISTS `Proyecto_PeriodoEscolar`;
CREATE TABLE `Proyecto_PeriodoEscolar` (
    `idProyectoPeriodoEscolar` INT NOT NULL AUTO_INCREMENT,
    `idProyecto` INT NOT NULL,
    `idPeriodoEscolar` INT NOT NULL,

    PRIMARY KEY (`idProyectoPeriodoEscolar`),

    CONSTRAINT `fk_proyectoperiodoescolar_proyecto` FOREIGN KEY (`idProyecto`) REFERENCES `Proyecto`(`idProyecto`),
    CONSTRAINT `fk_proyectoperiodoescolar_periodoescolar` FOREIGN KEY (`idPeriodoEscolar`) REFERENCES `PeriodoEscolar`(`idPeriodoEscolar`)
);

-- Crear tabla ResponsableProyecto_Proyecto
DROP TABLE IF EXISTS `ResponsableProyecto_Proyecto`;
CREATE TABLE `ResponsableProyecto_Proyecto` (
    `idResponsableProyectoProyecto` INT NOT NULL AUTO_INCREMENT,
    `idResponsableProyecto` INT NOT NULL,
    `idProyecto` INT NOT NULL,

    PRIMARY KEY (`idResponsableProyectoProyecto`),

    CONSTRAINT `fk_responsableproyectoproyecto_responsableproyecto` FOREIGN KEY (`idResponsableProyecto`) REFERENCES `ResponsableProyecto`(`idResponsableProyecto`),
    CONSTRAINT `fk_responsableproyectoproyecto_proyecto` FOREIGN KEY (`idProyecto`) REFERENCES `Proyecto`(`idProyecto`)
);

SET FOREIGN_KEY_CHECKS = 1;