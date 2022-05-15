/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author HP
 */
public class Categorie {
    private int id;
    private String nomCategorie;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public Categorie() {
    }

    @Override
    public String toString() {
        return "Categorie{" + "id=" + id + ", nomCategorie=" + nomCategorie + '}';
    }

    public Categorie(int id, String nomCategorie) {
        this.id = id;
        this.nomCategorie = nomCategorie;
    }

    public Categorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }
    
    

   

}
