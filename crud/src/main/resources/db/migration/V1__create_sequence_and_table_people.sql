CREATE SEQUENCE people_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE people (
    id bigint default nextval('people_id_seq'),
    name varchar(255) not null,
    surname varchar(255) not null,
    age serial not null,
    gender varchar(255)
);