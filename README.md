## Sistema Gestor de Bitácoras de Proyectos

## Desarrolladores

- Raúl Hernández Olivares
- Cesar Gonzáles López
- Miguel Ángel Morales Cruz
- Albhieri Cristoff Villa Contreras

## Descripción del Proyecto

El **Sistema Gestor de Bitácoras de Proyectos** es un proyecto desarrollado en el marco de la experiencia educativa "Principios de Construcción de Software" de la carrera en Ingeniería de Software de la Universidad Veracruzana. Este proyecto tiene como objetivo principal la creación de una aplicación usando la tecnología JavaFX para que se administren de manera eficiente las bitácoras correspondientes a las actividades y labores realizadas por los estudiantes durante sus prácticas profesionales y servicio social en el Laboratorio de Innovación.

### Funcionalidades Principales

- **Administración de Bitácoras:** El sistema permite a los docentes o encargados de un proyecto llevar un registro detallado de las diversas actividades realizadas por los estudiantes, abarcando todas las fases del ciclo de vida del desarrollo de software.

- **Reemplazo de Documentación:** Tradicionalmente, los estudiantes y profesores llenan las bitácoras en documentos enriquecidos. Nuestra aplicación busca modernizar este proceso, proporcionando una interfaz intuitiva y eficiente para gestionar estas actividades de manera más efectiva.

### Integración con Otras Materias

Este proyecto está estrechamente relacionado con la materia de "Principios de Diseño de Software", impartida simultáneamente. Durante esta experiencia educativa, se han desarrollado artefactos específicos que sirven como base para la implementación del Sistema Gestor de Bitácoras de Proyectos. La rigurosa adherencia a estos artefactos es fundamental para el éxito del proyecto y, a su vez, contribuirá a la acreditación de ambas materias.

## Tecnologías Utilizadas

- JavaFX: Utilizado para el desarrollo de la interfaz de usuario, proporcionando una experiencia visual atractiva y funcional.

## Instrucciones de Despliegue en NetBeans

1. Clona el repositorio en tu entorno de desarrollo local:

   ```bash
   git clone https://github.com/RaulHernandez23/PrincipiosDeConstruccion.git
   ```

2. El proyecto en NetBeans para MacOS, Windows y Linux se encuentra en la carpeta "netbeans" del repositorio.

3. Abre tu NetBeans y selecciona la carpeta que está dentro de la carpeta netbeans llamada "sgbp".

4. Compila y ejecuta la aplicación.

## Instrucciones de Despliegue en Visual Studio Code (Windows)

1. Clona el repositorio en tu entorno de desarrollo local:

   ```bash
   git clone https://github.com/RaulHernandez23/PrincipiosDeConstruccion.git
   ```

2. Instala el JDK de Java. (JDK 21 Recomendado)

3. Agrega el JDK de Java a tu PATH y JAVA_HOME en las variables de entorno.

4. Instala el Scene Builder en una carpeta de tu preferencia.

5. Instala el SDK de JavaFX en una carpeta de tu preferencia (Recomendación: descargala en la misma carpeta que tu JDK para no olvidar la ruta).

6. Abre tu Visual Studio Code en la carpeta del repositorio clonado.

7. Descarga las extensiones recomendadas por Visual Studio Code.

- Extension Pack for Java v0.25.15+ (Microsoft)
- JavaFX Support v0.0.1+ (Shrey Pandya)
- JavaFX CSS Support v1.0.4+ (Bernardo Amorim)
- SceneBuilder extension for Visual Studio Code v1.0.1+ (Bilal Ekrem Harmansa)

9. Abre el archivo main de la carpeta src.

10. En la parte superior izquierda de Visual Studio Code, selecciona en la barra de herramientas la opción "Run" y luego "Add Configuration" o bien en español "Ejecutar" y luego "Agregar Configuración".

11. La configuración para el proyecto en Visual Studio Code se encuentra en la carpeta .vscode donde hay un archivo llamado "settings.json", se habrá creado un archivo llamado "launch.json" luego de agregar la configuración.

12. En el archivo "launch.json" se encuentra la configuración para ejecutar el proyecto, a la derecha de "projectName" al final coloca una coma y luego da enter.

13. En la linea deberás escribir la siguiente configuración:

```bash
"vmArgs": "--module-path \"C:/Program Files/Java/javafx-sdk-21.0.1/lib\" --add-modules javafx.controls,javafx.fxml"
```

**Nota:** La ruta del lib del SDK de JavaFX puede variar dependiendo de la carpeta donde lo hayas descargado asegurate que tenga el formato anterior y respeta los simbolo \" y \" al inicio y al final de la ruta y pon las divisiones de carpetas con / en lugar de \ o \\.

14. Guarda el archivo y cierralo.

15. Busca en la carpeta lib de tu SDK de JavaFX los archivos:

- javafx-swt.jar
- javafx.base.jar
- javafx.controls.jar
- javafx.fxml.jar
- javafx.graphics.jar
- javafx.media.jar
- javafx.swing.jar
- javafx.web.jar

16. Copia estos archivos y pegalos en la carpeta lib del repositorio si no se encuentran.

17. Busca en la carpeta bd del repositorio el archivo llamado "sgbp.sql" y copialo y ejecutalo en tu instancia de MySQL Workbench, Navicat o cualquier otro gestor de bases de datos.

18. Modifica el archivo configuracion.properties que se encuentra en la carpeta src/utilidades y cambia los valores de las variables por los de tu instancia de MySQL.

19. Modifica el archivo ConectorBaseDatos.java que se encuentra en la carpeta src/modelo y cambia el valor de int usuarioActual por el indice del usuario que hayas agregado en el configuracion.properties. (Recomendación: Pon tu usuario al principio de usuarios y tu contraseña al principio de passwords para que tu indice sea el 0, deberas poner una coma ',' al final para separalos de los demás valores tanto de los usuarios como de las contraseñas).

20. Dale a guardar a todos los archivos modificados.

21. Colocate de nuevo en el archivo main de la carpeta src llamado Sgbp.java y en la parte lateral izquierda de Visual Studio Code da clic en el icono de Run and Debug o bien en español Ejecutar y Depurar.

22. Selecciona la parte superior la lista desplegable que dice current file y selecciona la opción Sgbp.

23. Da clic en el icono de play.

24. La aplicación deberá ejecutarse sin problemas.

## Instrucciones de Despliegue en Visual Studio Code (MacOS y Linux)

1. Clona el repositorio en tu entorno de desarrollo local:

   ```bash
   git clone
   ```

2. Instala el JDK de Java. (JDK 21 Recomendado)

3. Agrega el JDK de Java a tu PATH y JAVA_HOME en las variables de entorno.

4. Instala el Scene Builder en una carpeta de tu preferencia.

5. Instala el SDK de JavaFX en una carpeta de tu preferencia (Recomendación: descargala en la misma carpeta que tu JDK para no olvidar la ruta).

6. Abre tu Visual Studio Code en la carpeta del repositorio clonado.

7. Descarga las extensiones recomendadas por Visual Studio Code.

- Extension Pack for Java v0.25.15+ (Microsoft)
- JavaFX Support v0.0.1+ (Shrey Pandya)
- JavaFX CSS Support v1.0.4+ (Bernardo Amorim)
- SceneBuilder extension for Visual Studio Code v1.0.1+ (Bilal Ekrem Harmansa)

9. Abre el archivo main de la carpeta src.

10. En la parte superior izquierda de Visual Studio Code, selecciona en la barra de herramientas la opción "Run" y luego "Add Configuration" o bien en español "Ejecutar" y luego "Agregar Configuración".

11. La configuración para el proyecto en Visual Studio Code se encuentra en la carpeta .vscode donde hay un archivo llamado "settings.json", se habrá creado un archivo llamado "launch.json" luego de agregar la configuración.

12. En el archivo "launch.json" se encuentra la configuración para ejecutar el proyecto, a la derecha de "projectName" al final coloca una coma y luego da enter.

13. En la linea deberás escribir la siguiente configuración:

```bash
"vmArgs": "--module-path ruta/javafx-sdk-21.0.1/lib --add-modules javafx.controls,javafx.fxml"
```

**Nota:** La ruta del lib del SDK de JavaFX puede variar dependiendo de la carpeta donde lo hayas descargado asegurate que tenga el formato anterior y respeta los simbolo / cada vez que haya una carpeta.

14. Guarda el archivo y cierralo.

15. Busca en la carpeta lib de tu SDK de JavaFX los archivos:

- javafx-swt.jar
- javafx.base.jar
- javafx.controls.jar
- javafx.fxml.jar
- javafx.graphics.jar
- javafx.media.jar
- javafx.swing.jar
- javafx.web.jar

16. Copia estos archivos y pegalos en la carpeta lib del repositorio si no se encuentran.

17. Busca en la carpeta bd del repositorio el archivo llamado "sgbp.sql" y copialo y ejecutalo en tu instancia de MySQL Workbench, Navicat o cualquier otro gestor de bases de datos.

18. Modifica el archivo configuracion.properties que se encuentra en la carpeta src/utilidades y cambia los valores de las variables por los de tu instancia de MySQL.

19. Modifica el archivo ConectorBaseDatos.java que se encuentra en la carpeta src/modelo y cambia el valor de int usuarioActual por el indice del usuario que hayas agregado en el configuracion.properties. (Recomendación: Pon tu usuario al principio de usuarios y tu contraseña al principio de passwords para que tu indice sea el 0, deberas poner una coma ',' al final para separalos de los demás valores tanto de los usuarios como de las contraseñas).

20. Dale a guardar a todos los archivos modificados.

21. Colocate de nuevo en el archivo main de la carpeta src llamado Sgbp.java y en la parte lateral izquierda de Visual Studio Code da clic en el icono de Run and Debug o bien en español Ejecutar y Depurar.

22. Selecciona la parte superior la lista desplegable que dice current file y selecciona la opción Sgbp.

23. Da clic en el icono de play.

24. La aplicación deberá ejecutarse sin problemas.
