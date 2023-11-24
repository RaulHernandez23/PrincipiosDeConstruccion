-- Desactivar las verificaciones de claves foráneas temporalmente
SET FOREIGN_KEY_CHECKS = 0;

-- Eliminar todas las tablas
DROP TABLE IF EXISTS `actividad`;
DROP TABLE IF EXISTS `cambio`;
DROP TABLE IF EXISTS `defecto`;
DROP TABLE IF EXISTS `estadoactividad`;
DROP TABLE IF EXISTS `estadoestudiante`;
DROP TABLE IF EXISTS `estadosolicitud`;
DROP TABLE IF EXISTS `estudiante`;
DROP TABLE IF EXISTS `realiza`;
DROP TABLE IF EXISTS `responsableproyecto`;
DROP TABLE IF EXISTS `solicitudcambios`;

-- Activar nuevamente las verificaciones de claves foráneas
SET FOREIGN_KEY_CHECKS = 1;