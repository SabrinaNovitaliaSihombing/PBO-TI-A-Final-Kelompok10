module PBO.TI.A.Final.Kelompok10 {
    requires spring.context;
    requires spring.beans;
    requires java.sql;

    opens libraryapp;
    opens libraryapp.entities;
    opens libraryapp.repositories;
    opens libraryapp.services;
    opens libraryapp.views;
    opens libraryapp.config;
}