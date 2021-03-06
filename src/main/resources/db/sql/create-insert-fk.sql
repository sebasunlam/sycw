/**
*  Ejecutar Primero esta parte
 */
CREATE TABLE ESTADOSEXAMENES (
	ID                   INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) PRIMARY KEY NOT NULL,
	DESCRIPCION          varchar(50)   NOT NULL
 );

CREATE TABLE ESTADOSMATERIAS (
	ID                   integer GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) PRIMARY KEY NOT NULL,
	DESCRIPCION          varchar(50)   NOT NULL
 );

CREATE TABLE ESTADOSUSUARIOS (
	ID                   integer GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) PRIMARY KEY NOT NULL,
	DESCRIPCION          varchar(50)   NOT NULL
 );

CREATE TABLE ROLES (
	ID                   integer GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) PRIMARY KEY NOT NULL,
	DESCRIPCION          varchar(50)   NOT NULL
 );

CREATE TABLE TIPOSRESPUESTAS (
	ID                   integer GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) PRIMARY KEY  NOT NULL,
	DESCRIPCION          varchar(50)
 );

CREATE TABLE USUARIOS (
	ID                   integer GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) PRIMARY KEY  NOT NULL,
	EMAIL                varchar(255)   NOT NULL,
	PASSWORD             varchar(255)   NOT NULL,
	APELLIDO             varchar(255)   NOT NULL,
	NOMBRE               varchar(255)   NOT NULL,
	IDESTADOUSUARIO      integer   NOT NULL
 );

CREATE TABLE MATERIAS (
	ID                   integer GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) PRIMARY KEY  NOT NULL,
	NOMBRE               varchar(255)   NOT NULL,
	IDDOCENTETITULAR     integer   NOT NULL,
	IDESTADOMATERIA      integer   NOT NULL
 );

CREATE TABLE ROLESUSUARIOS (
	IDUSUARIO            integer   NOT NULL,
	IDROL                integer   NOT NULL
 );

CREATE TABLE EXAMENES (
	ID                   integer GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) PRIMARY KEY NOT NULL,
	NOMBRE               varchar(255)   NOT NULL,
	IDMATERIA            integer   NOT NULL,
	IDESTADOEXAMEN       integer   NOT NULL
 );

CREATE TABLE MATERIAALUMNO (
	IDALUMNO             integer   NOT NULL,
	IDMATERIA            integer
 );

CREATE TABLE PREGUNTAS (
	ID                   integer GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) PRIMARY KEY  NOT NULL,
	IDEXAMEN             integer   NOT NULL,
	PREGUNTA             varchar(255)   NOT NULL
 );

CREATE TABLE RESPUESTAS (
	ID                   integer GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) PRIMARY KEY NOT NULL,
	IDPREGUNTA           integer   NOT NULL,
	RESPUESTA            varchar(255)   NOT NULL,
	IDTIPORESPUESTA      integer   NOT NULL
 );

CREATE TABLE RESPUESTAALUMNO (
	IDALUMNO             integer NOT NULL,
	IDRESPUESTA          integer NOT NULL
);

/**
*  Ejecutar Segundo esta parte
 */
ALTER TABLE EXAMENES ADD CONSTRAINT FK_EXAMENES FOREIGN KEY ( IDESTADOEXAMEN ) REFERENCES ESTADOSEXAMENES( ID ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE EXAMENES ADD CONSTRAINT FK_EXAMENES_0 FOREIGN KEY ( IDMATERIA ) REFERENCES MATERIAS( ID ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE MATERIAALUMNO ADD CONSTRAINT FK_MATERIAALUMNO FOREIGN KEY ( IDALUMNO ) REFERENCES USUARIOS( ID ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE MATERIAALUMNO ADD CONSTRAINT FK_MATERIAALUMNO_0 FOREIGN KEY ( IDMATERIA ) REFERENCES MATERIAS( ID ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE MATERIAS ADD CONSTRAINT FK_MATERIAS FOREIGN KEY ( IDESTADOMATERIA ) REFERENCES ESTADOSMATERIAS( ID ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE MATERIAS ADD CONSTRAINT FK_MATERIAS_0 FOREIGN KEY ( IDDOCENTETITULAR ) REFERENCES USUARIOS( ID ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE PREGUNTAS ADD CONSTRAINT FK_PREGUNTAS FOREIGN KEY ( IDEXAMEN ) REFERENCES EXAMENES( ID ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE RESPUESTAS ADD CONSTRAINT FK_RESPUESTAS FOREIGN KEY ( IDPREGUNTA ) REFERENCES PREGUNTAS( ID ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE RESPUESTAS ADD CONSTRAINT FK_RESPUESTAS_0 FOREIGN KEY ( IDTIPORESPUESTA ) REFERENCES TIPOSRESPUESTAS( ID ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE ROLESUSUARIOS ADD CONSTRAINT FK_ROLESUSUARIOS FOREIGN KEY ( IDROL ) REFERENCES ROLES( ID ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE ROLESUSUARIOS ADD CONSTRAINT FK_ROLESUSUARIOS_0 FOREIGN KEY ( IDUSUARIO ) REFERENCES USUARIOS( ID ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE USUARIOS ADD CONSTRAINT FK_USUARIOS FOREIGN KEY ( IDESTADOUSUARIO ) REFERENCES ESTADOSUSUARIOS( ID ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE RESPUESTAALUMNO ADD CONSTRAINT FK_RESPUESTAALUMNO FOREIGN KEY ( IDALUMNO ) REFERENCES USUARIOS( ID ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE RESPUESTAALUMNO ADD CONSTRAINT FK_RESPUESTAALUMNO_0 FOREIGN KEY ( IDRESPUESTA ) REFERENCES RESPUESTAS( ID ) ON DELETE NO ACTION ON UPDATE NO ACTION;

/**
*  Ejecutar Tercero esta parte
 */
INSERT INTO ESTADOSEXAMENES(DESCRIPCION ) VALUES ('Pendiente' ); 
INSERT INTO ESTADOSEXAMENES(DESCRIPCION ) VALUES ('Examen en curso' ); 
INSERT INTO ESTADOSEXAMENES(DESCRIPCION ) VALUES ('Examen finalizado' ); 

INSERT INTO ESTADOSMATERIAS(DESCRIPCION ) VALUES ('Materia Habilitada' ); 
INSERT INTO ESTADOSMATERIAS(DESCRIPCION ) VALUES ('Materia Eliminada' ); 

INSERT INTO ESTADOSUSUARIOS(DESCRIPCION ) VALUES ('Pendiente Habilitación' ); 
INSERT INTO ESTADOSUSUARIOS(DESCRIPCION ) VALUES ('Habilitado' ); 
INSERT INTO ESTADOSUSUARIOS(DESCRIPCION ) VALUES ('Habilitación Rechazada' ); 
INSERT INTO ESTADOSUSUARIOS(DESCRIPCION ) VALUES ( 'Eliminado' );

INSERT INTO ROLES(DESCRIPCION ) VALUES ('Administrador' ); 
INSERT INTO ROLES(DESCRIPCION ) VALUES ('Docente' ); 
INSERT INTO ROLES(DESCRIPCION ) VALUES ('Alumno' ); 

INSERT INTO TIPOSRESPUESTAS(DESCRIPCION ) VALUES ('Repuesta Correcta' );
INSERT INTO TIPOSRESPUESTAS(DESCRIPCION ) VALUES ('Repuesta Incorrecta' );

INSERT INTO USUARIOS(EMAIL, PASSWORD, APELLIDO, NOMBRE, IDESTADOUSUARIO ) VALUES ('cgioia@unlam.edu.ar', '1234', 'Gioia', 'Cintia', 2 ); 
INSERT INTO USUARIOS(EMAIL, PASSWORD, APELLIDO, NOMBRE, IDESTADOUSUARIO ) VALUES ('wureta@unlam.edu.ar', '1234', 'Ureta', 'Walter', 2 ); 
INSERT INTO USUARIOS(EMAIL, PASSWORD, APELLIDO, NOMBRE, IDESTADOUSUARIO ) VALUES ('aborgeat@unlam.edu.ar', '1234', 'Borgeat', 'Andres', 2 ); 
INSERT INTO USUARIOS(EMAIL, PASSWORD, APELLIDO, NOMBRE, IDESTADOUSUARIO ) VALUES ('jmonteagudo@unlam.edu.ar', '1234', 'Monteagudo', 'Juan', 2 ); 

INSERT INTO MATERIAS(NOMBRE, IDDOCENTETITULAR, IDESTADOMATERIA ) VALUES ('Seguridad y Calidad en Aplicaciones Web', 2, 1 );

INSERT INTO ROLESUSUARIOS(IDUSUARIO, IDROL ) VALUES ( 1, 1 ); 
INSERT INTO ROLESUSUARIOS(IDUSUARIO, IDROL ) VALUES ( 1, 2 ); 
INSERT INTO ROLESUSUARIOS(IDUSUARIO, IDROL ) VALUES ( 2, 1 ); 
INSERT INTO ROLESUSUARIOS(IDUSUARIO, IDROL ) VALUES ( 2, 2 ); 
INSERT INTO ROLESUSUARIOS(IDUSUARIO, IDROL ) VALUES ( 3, 1 ); 
INSERT INTO ROLESUSUARIOS(IDUSUARIO, IDROL ) VALUES ( 3, 2 ); 
INSERT INTO ROLESUSUARIOS(IDUSUARIO, IDROL ) VALUES ( 3, 3 ); 
INSERT INTO ROLESUSUARIOS(IDUSUARIO, IDROL ) VALUES ( 4, 1 ); 
INSERT INTO ROLESUSUARIOS(IDUSUARIO, IDROL ) VALUES ( 4, 2 ); 
INSERT INTO ROLESUSUARIOS(IDUSUARIO, IDROL ) VALUES ( 4, 3 );

INSERT INTO EXAMENES(NOMBRE, IDMATERIA, IDESTADOEXAMEN ) VALUES ('2017-1C 1er Parcial', 1, 1 );

INSERT INTO PREGUNTAS(IDEXAMEN, PREGUNTA ) VALUES (1, 'Información cambia el estado de ... del sujeto o sistema que lo recibe' ); 
INSERT INTO PREGUNTAS(IDEXAMEN, PREGUNTA ) VALUES (1, 'Información es:' ); 
INSERT INTO PREGUNTAS(IDEXAMEN, PREGUNTA ) VALUES (1, 'Contenedores de información pueden ser:' ); 
INSERT INTO PREGUNTAS(IDEXAMEN, PREGUNTA ) VALUES (1, 'Seguridad es una sensación' ); 
INSERT INTO PREGUNTAS(IDEXAMEN, PREGUNTA ) VALUES (1, 'Tiangulo ...' ); 

INSERT INTO RESPUESTAS(IDPREGUNTA, RESPUESTA, IDTIPORESPUESTA ) VALUES (1, 'Conocimiento', 1 ); 
INSERT INTO RESPUESTAS(IDPREGUNTA, RESPUESTA, IDTIPORESPUESTA ) VALUES (1, 'Enamoramiento', 2 ); 
INSERT INTO RESPUESTAS(IDPREGUNTA, RESPUESTA, IDTIPORESPUESTA ) VALUES (2, 'Critica', 1 ); 
INSERT INTO RESPUESTAS(IDPREGUNTA, RESPUESTA, IDTIPORESPUESTA ) VALUES (2, 'Valiosa', 1 ); 
INSERT INTO RESPUESTAS(IDPREGUNTA, RESPUESTA, IDTIPORESPUESTA ) VALUES (2, 'Sensitiva', 1 ); 
INSERT INTO RESPUESTAS(IDPREGUNTA, RESPUESTA, IDTIPORESPUESTA ) VALUES (2, 'Adictiva', 2 ); 
INSERT INTO RESPUESTAS(IDPREGUNTA, RESPUESTA, IDTIPORESPUESTA ) VALUES (3, 'Lindos', 2 ); 
INSERT INTO RESPUESTAS(IDPREGUNTA, RESPUESTA, IDTIPORESPUESTA ) VALUES (3, 'Feos', 2 ); 
INSERT INTO RESPUESTAS(IDPREGUNTA, RESPUESTA, IDTIPORESPUESTA ) VALUES (3, 'Sistemas Aislados', 1 ); 
INSERT INTO RESPUESTAS(IDPREGUNTA, RESPUESTA, IDTIPORESPUESTA ) VALUES (3, 'Sistema Interconectados', 1 ); 
INSERT INTO RESPUESTAS(IDPREGUNTA, RESPUESTA, IDTIPORESPUESTA ) VALUES (4, 'Verdadero', 1 ); 
INSERT INTO RESPUESTAS(IDPREGUNTA, RESPUESTA, IDTIPORESPUESTA ) VALUES (4, 'Falso', 2 ); 
INSERT INTO RESPUESTAS(IDPREGUNTA, RESPUESTA, IDTIPORESPUESTA ) VALUES (5, 'Bermudas', 2 ); 
INSERT INTO RESPUESTAS(IDPREGUNTA, RESPUESTA, IDTIPORESPUESTA ) VALUES (5, 'ID', 1 ); 

