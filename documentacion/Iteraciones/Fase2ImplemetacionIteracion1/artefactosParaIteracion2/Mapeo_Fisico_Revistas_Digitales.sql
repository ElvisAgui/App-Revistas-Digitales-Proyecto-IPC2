

CREATE DATABASE Revistas_Digitales;

USE Revistas_Digitales;

CREATE TABLE usuario(contraseña VARCHAR(50) NOT NULL, nombre VARCHAR(35) NOT NULL, rol TINYINT NOT NULL, CONSTRAINT PK_usuario PRIMARY KEY (nombre));

CREATE TABLE preferencias_Usuario(foto VARCHAR(60) NULL, hobiies VARCHAR(60) NULL, descripcion VARCHAR(75) NULL, usuario VARCHAR(35) NOT NULL , CONSTRAINT PK_descripcion PRIMARY KEY (usuario), CONSTRAINT FK_referencia_usuario FOREIGN KEY (usuario) REFERENCES usuario(nombre));

CREATE TABLE etiqueta(nombre_Etiqueta VARCHAR(25) NOT NULL, CONSTRAINT PK_etiqueta PRIMARY KEY (nombre_Etiqueta));

CREATE TABLE usuario_Etiqueta(etiqueta VARCHAR(25) NOT NULL, usuario VARCHAR(35) NOT NULL, id INT auto_increment, CONSTRAINT PK_etiqueta_Usuario PRIMARY KEY (id), CONSTRAINT FK_usuario_etiqueta FOREIGN KEY (usuario) REFERENCES usuario(nombre), CONSTRAINT FK_etiqueta_foranea FOREIGN KEY (etiqueta) REFERENCES etiqueta(nombre_Etiqueta));

CREATE TABLE categoria(nombre_Categoria VARCHAR(30) NOT NULL, CONSTRAINT PK_categoria PRIMARY KEY (nombre_Categoria))

CREATE TABLE revista(titulo VARCHAR(35) NOT NULL, editor VARCHAR(35) NOT NULL,  categoria VARCHAR(30) NOT NULL, precio INT NOT NULL, costo_Global INT NOT NULL, suscripcion BOOLEAN NOT NULL, reaccionar BOOLEAN NOT NULL, comentar BOOLEAN NOT NULL,  CONSTRAINT PK_titulo_revista PRIMARY KEY (titulo), CONSTRAINT FK_editor_revista FOREIGN KEY (editor) REFERENCES usuario(nombre), CONSTRAINT FK_categori_revista FOREIGN KEY (categoria) REFERENCES categoria(nombre_Categoria));
alter table revista modify precio DECIMAL(8,2);
alter table revista modify costo_Global DECIMAL(8,2);

CREATE TABLE revist_Etiqueta(id INT auto_increment, etiqueta VARCHAR(25) NOT NULL, revista VARCHAR(35) NOT NULL, CONSTRAINT PK_id_revista_etiqueta PRIMARY KEY (id), CONSTRAINT FK_etiqueta FOREIGN KEY (etiqueta) REFERENCES etiqueta(nombre_Etiqueta), CONSTRAINT FK_revista FOREIGN KEY (revista) REFERENCES revista(titulo));

CREATE TABLE comentario(usuario VARCHAR(35) NOT NULL, contenido VARCHAR(150), id INT auto_increment, revista VARCHAR(35) NOT NULL, CONSTRAINT PK_id PRIMARY KEY (id), CONSTRAINT FK_revista_comentario FOREIGN KEY (revista) REFERENCES (titulo), CONSTRAINT FK_usuario_comentario FOREIGN KEY (usuario) REFERENCES usuario(nombre) );

CREATE TABLE  suscripcion(usuario VARCHAR(35) NOT NULL, revista VARCHAR(35) NOT NULL, id INT auto_increment, fecha DATE NOT NULL, pagado BOOLEAN NOT NULL, CONSTRAINT PK_id_suscripcion PRIMARY KEY (id), CONSTRAINT FK_usuario_suscripcion FOREIGN KEY (usuario) REFERENCES usuario(nombre), CONSTRAINT FK_revista_comentario FOREIGN KEY (revista) REFERENCES (titulo));

CREATE TABLE edicion(codigo INT NOT NULL, fecha DATE NOT NULL, descripcion VARCHAR(150) NOT NULL, revista_pdf VARCHAR(200) NOT NULL, revista VARCHAR(35) NOT NULL, content_type VARCHAR(20) NOT NULL, CONSTRAINT PK_codigo PRIMARY KEY (codigo), CONSTRAINT FK_revista_edicion FOREIGN KEY (revista) REFERENCES revista(titulo));

CREATE TABLE anuncio(anuncio VARCHAR(60) NOT NULL, anunciante VARCHAR(35) NOT NULL, precio INT NOT NULL, id INT auto_increment, CONSTRAINT PK_etiqueta_anuncio PRIMARY KEY (id));

CREATE TABLE historial(anuncio VARCHAR(60) NOT NULL, url VARCHAR(30) NOT NULL, id INT auto_increment,  CONSTRAINT PK_historial PRIMARY KEY (id), CONSTRAINT FK_anuncio FOREIGN KEY (anuncio) REFERENCES anuncio(id));

CREATE TABLE etiqueta_anuncio(id INT auto_increment, anuncio VARCHAR(60) NOT NULL, etiqueta VARCHAR(25) NOT NULL, CONSTRAINT PK_etiqueta_anuncio PRIMARY KEY (id),CONSTRAINT FK_etiquet_anuncio FOREIGN KEY (etiqueta) REFERENCES (nombre_Etiqueta), CONSTRAINT FK_anuncio FOREIGN KEY (anuncio) REFERENCES anuncio(id));


--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
