-- Insertar estados:
INSERT INTO estadoActividad (estado) VALUES
('Realizada'),
('No realizada'),
('No asignada');

INSERT INTO estadoDefecto (estado) VALUES
('Realizado'),
('No realizado'),
('En proceso');

INSERT INTO estadoCambio (estado) VALUES
('Realizado'),
('No realizado');

INSERT INTO estadoSolicitud (estado) VALUES
('Aprobada'),
('Rechazada'),
('Pendiente');

-- Insertar Tipos de Actividad
INSERT INTO tipoActividad (tipo) VALUES
('Archivos Javascript'),
('Base de Datos'),
('Vistas'),
('Controladores'),
('Otro Archivo');

INSERT INTO estadoProyecto (estado) VALUES
('Activo'),
('Finalizado');

INSERT INTO estadoEstudiante (estado) VALUES
('Activo'),
('Inactivo');

INSERT INTO periodoEscolar (nombre, fechaInicio, fechaFin) VALUES
('Febrero-Julio 23', '2023-02-01', '2023-07-31'),
('Agosto-Enero 23/24', '2023-08-01', '2023-01-31');

-- Insertar proyecto
INSERT INTO proyecto (nombre, descripcion, idEstadoProyecto) VALUES
('VaraWeb', 'Aplicación web VaraWeb para el reporte de varamientos de mamíferos marínos', 1);

-- Relacionar proyecto con periodo escolar
INSERT INTO proyecto_periodoEscolar (idPeriodoEscolar, idProyecto) VALUES
(1, 1),
(2, 1);

-- Insertar Responsable de Proyecto
INSERT INTO responsableProyecto (numPersonal, nombre, apellidoPaterno, apellidoMaterno, correo, telefono, password)
VALUES 
('EMP001', 'Juan Carlos', 'Perez', 'Arriaga', 'correo@ejemplo.com', '2281130978', 'radisa94'),
('EMP002', 'Ana Luz', 'Polo', 'Estrella', 'correo2@ejemplo.com', '2283456723', 'radisa94');

-- Insertar Estudiante
INSERT INTO estudiante (matricula, nombre, apellidoPaterno, apellidoMaterno, password, idEstadoEstudiante, idProyecto)
VALUES 
('s21013876', 'Albhieri Cristoff', 'Villa', 'Contreras', 'radisa94', 1, 1),
('S21026432', 'Raul', 'Hernandez', 'Olivares', 'pass', 1, 1);

-- Relacionar estudiante con periodoEscolar
INSERT INTO estudiante_periodoEscolar (idPeriodoEscolar, idEstudiante) VALUES
(1, 2),
(2, 2);

-- Insertar actividades
INSERT INTO actividad (titulo, descripcion, esfuerzoMinutos, fechaInicio, fechaFin, idEstadoActividad, idTipoActividad, idEstudiante, idResponsable, idProyecto) VALUES
('Actividad 1', 'Descripción 1', 60, '2023-08-15', '2023-08-16', 1, 1, 2, 1, 1),
('Actividad 2', 'Descripción 2', 120, '2023-09-20', '2023-09-21', 2, 2, 2, 1, 1),
('Actividad 3', 'Descripción 3', 180, '2023-10-10', '2023-10-11', 3, 3, 2, 1, 1),
('Actividad 4', 'Descripción 4', 240, '2023-10-25', '2023-10-26', 1, 4, 2, 1, 1),
('Actividad 5', 'Descripción 5', 300, '2023-11-05', '2023-11-06', 2, 5, 2, 1, 1);

-- Relacionar responsable con proyecto
INSERT INTO responsableProyecto_Proyecto (idResponsableProyecto, idProyecto) VALUES
(1, 1);

-- Insertar defectos
INSERT INTO defecto (titulo, descripcion, esfuerzoMinutos, fechaReporte, fechaFin, idEstadoDefecto, idEstudiante, idProyecto) VALUES
('Defecto 1', 'Descripción 1', 60, '2023-08-01', '2023-08-02', 1, 2, 1),
('Defecto 2', 'Descripción 2', 120, '2023-08-03', '2023-08-04', 1, 2, 1),
('Defecto 3', 'Descripción 3', 180, '2023-08-05', '2023-08-06', 2, 2, 1),
('Defecto 4', 'Descripción 4', 240, '2023-08-07', '2023-08-08', 1, 2, 1),
('Defecto 5', 'Descripción 5', 300, '2023-08-09', '2023-08-10', 2, 2, 1);

-- Insertar solicitudes de cambio
INSERT INTO solicitudDeCambio (titulo, descripcion, razon, impacto, accionPropuesta, fechaCreacion, fechaEvaluacion, idEstudiante, idEstadoSolicitud, idProyecto, idResponsableProyecto, idDefecto) VALUES
('Solicitud de cambio 1', 'Descripción 1', 'Razón 1', 'Impacto 1', 'Acción propuesta 1', '2023-08-01', '2023-08-02', 2, 1, 1, 1, 1),
('Solicitud de cambio 2', 'Descripción 2', 'Razón 2', 'Impacto 2', 'Acción propuesta 2', '2023-08-03', '2023-08-04', 2, 2, 1, 1, 1),
('Solicitud de cambio 3', 'Descripción 3', 'Razón 3', 'Impacto 3', 'Acción propuesta 3', '2023-08-05', '2023-08-06', 2, 1, 1, 1, 2);

-- Insertar cambios
INSERT INTO cambio (titulo, descripcion, esfuerzoMinutos, idEstadoCambio, fechaInicio, fechaFin, idSolicitudDeCambio, idTipoActividad) VALUES
('Cambio 1', 'Descripción 1', 60, 1, '2023-08-01', '2023-08-02', 1, 1),
('Cambio 2', 'Descripción 2', 120, 2, '2023-08-03', '2023-08-04', 1, 2),
('Cambio 3', 'Descripción 3', 180, 1, '2023-08-05', '2023-08-06', 1, 3),
('Cambio 4', 'Descripción 4', 240, 2, '2023-08-07', '2023-08-08', 2, 3),
('Cambio 5', 'Descripción 5', 300, 1, '2023-08-09', '2023-08-10', 3, 1);