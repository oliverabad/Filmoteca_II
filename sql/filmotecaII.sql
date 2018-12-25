create database filmotecaII;
use filmotecaII;

create table director(
	id_director int auto_increment ,
    nombre varchar(20),
    apellidos varchar(30),
    primary key(id_director)
);

create table pelicula(
	id_pelicula int auto_increment ,
    titulo varchar(40),
    id_dir int,
    pais varchar(30),
    duracion varchar(10),
    genero varchar(30),
    primary key(id_pelicula),
    foreign key(id_dir) references director(id_director)
);

select * from director;
