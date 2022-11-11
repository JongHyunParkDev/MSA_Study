create role ecc with
    login
    nosuperuser
    inherit
    createdb
    nocreaterole
    noreplication
    PASSWORD 'ecc1234!';

create tablespace ecc
    owner ecc
    location 'C:\projects\e-commerce\tablespace';
    -- linux의 경우 경로를 변경한다.

create database ecc
    with
    owner = ecc
    encoding = 'UTF8'
    tablespace = ecc;
