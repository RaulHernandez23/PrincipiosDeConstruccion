-- Insertar estados:
INSERT INTO estadoActividad (estado) VALUES
('Realizado'),
('No realizado'),
('Sin asignar');

INSERT INTO estadoDefecto (estado) VALUES
('Realizado'),
('No realizado');

INSERT INTO estadoCambio (estado) VALUES
('Realizado'),
('No realizado');

INSERT INTO estadoSolicitud (estado) VALUES
('Aprobada'),
('Rechazada'),
('Pendiente');

INSERT INTO estadoEstudiante (estado) VALUES
('Asignado'),
('No asignado');

-- Insertar Tipos de Actividad
INSERT INTO tipoActividad (tipo) VALUES
('Archivos Javascript'),
('Base de Datos'),
('Vistas'),
('Controladores'),
('Otro Archivo');

-- Insertar Responsable de Proyecto
INSERT INTO responsableProyecto (numPersonal, nombre, apellidoPaterno, apellidoMaterno, correo, telefono, password)
VALUES ('EMP001', 'Juan Carlos', 'Perez', 'Arriaga', 'correo@ejemplo.com', '2281130978', 'radisa94');

-- Insertar Estudiante
INSERT INTO estudiante (matricula, nombre, apellidoPaterno, apellidoMaterno, idEstadoEstudiante, password)
VALUES ('s20000000', 'Cristoff', 'Villa', 'Contreras', 1, 'radisa94');