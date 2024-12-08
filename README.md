# JavaEmployeeDB
Este proyecto consiste en la creación de una aplicación de escritorio interactiva que permite gestionar los datos de empleados utilizando JavaFX para la interfaz gráfica y MySQL como base de datos.

Objetivos del Proyecto

	1. Crear una interfaz gráfica funcional e intuitiva: Facilitar la navegación y uso del sistema mediante vistas claras y amigables.
 
	2. Gestionar datos de empleados: Permitir el registro, consulta y filtrado de empleados mediante validaciones robustas.
 
	3. Implementar conectividad estable: Asegurar una comunicación eficiente entre la aplicación y la base de datos usando MySQL Connector.
 
	4. Garantizar persistencia de datos: Integrar operaciones CRUD (creación, lectura, actualización, eliminación) en la base de datos.

Funcionalidades Principales

	1. Ingreso de empleados:
 
	  • Formulario con validaciones que permite registrar nuevos empleados.
	  • Verificación de campos, incluyendo coincidencia de IDs de jefe y departamento con registros existentes.
   
	2. Consulta y filtrado:
 
	  • Visualización de todos los empleados en una tabla.
	  • Filtro dinámico por nombre a través de un combo box.
   
	3. Navegación intuitiva:
 
	  • Pantalla principal con opciones para insertar o consultar empleados.

Tecnologías Utilizadas

	• JavaFX: Para la construcción de la interfaz gráfica.
	• MySQL: Para la gestión y almacenamiento de datos.
	• FXML: Para estructurar las vistas de la aplicación.
	• Eclipse IDE: Entorno de desarrollo integrado.

Estructura del Proyecto

	1. Paquete application: Incluye las clases controladoras para la interfaz gráfica.
	2. Paquete modelo: Contiene la clase Empleado y métodos para interactuar con la base de datos (e.g., insertarServicio, getServicios).
	3. Recursos FXML: Define las vistas para las diferentes funcionalidades.

Pasos para Configuración

	1. Configurar la base de datos MySQL con la tabla empleados.
	2. Agregar el JAR de MySQL Connector/J al proyecto.
	3. Ejecutar la clase Main para iniciar la aplicación.

Requisitos

	• Funcionales: Validación de datos, filtrado por criterios, persistencia de información.
	• No Funcionales: Conexión estable, compatibilidad multiplataforma, diseño de interfaz amigable.
