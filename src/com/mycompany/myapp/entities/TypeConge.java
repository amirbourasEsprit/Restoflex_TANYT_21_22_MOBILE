/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.entities;


/**
 *
 * @author Yosr Belaam
 */
public class TypeConge {
     private int id_type_conge;
    private String nom_type_conge;

    public TypeConge() {
    }

    public TypeConge(String nom_type_conge) {
        this.nom_type_conge = nom_type_conge;
    }
    

    public TypeConge(int id_type_conge, String nom_type_conge) {
        this.id_type_conge = id_type_conge;
        this.nom_type_conge = nom_type_conge;
    }

    public int getId_type_conge() {
        return id_type_conge;
    }

    public void setId_type_conge(int id_type_conge) {
        this.id_type_conge = id_type_conge;
    }

    public String getNom_type_conge() {
        return nom_type_conge;
    }

    public void setNom_type_conge(String nom_type_conge) {
        this.nom_type_conge = nom_type_conge;
    }

    @Override
    public String toString() {
        return "type_conge{" + "id_type_conge=" + id_type_conge + ", nom_type_conge=" + nom_type_conge + '}';
    }
}
