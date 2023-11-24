-- Insertar Encargados de Proyecto
INSERT INTO `responsableproyecto` (`NumPersonal`, `Nombre`, `ApellidoPaterno`, `ApellidoMaterno`, `Correo`, `Telefono`, `Contraseña`)
VALUES
('EMP001', 'José', 'López', 'Martínez', 'jose.lopez@example.com', '2281234567', 'radisa94'),
('EMP002', 'María', 'García', 'Ramírez', 'maria.garcia@example.com', '2289876543', 'E2g$6r3');

-- Insertar Estados de Estudiante
INSERT INTO `estadoestudiante` (`Estado`) VALUES
('Asignado'),
('No Asignado');

-- Insertar Estudiantes
INSERT INTO `estudiante` (`Matricula`, `Nombre`, `ApellidoPaterno`, `ApellidoMaterno`, `IdEstadoEstudiante`, `Contraseña`)
VALUES
('s21010001', 'Carlos', 'Hernández', 'Gómez', 1, 'radisa94'),
('s21010002', 'Ana', 'Rodríguez', 'Sánchez', 1, 'A#R2o3l4'),
('s21010003', 'Juan', 'Pérez', 'López', 2, 'J7u1@P2e3'); 