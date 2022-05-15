/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author HP
 */
public class Club {
    private int id,rate;
    private String nom_club,description;

    public Club() {
    }

    

    public Club(int id, String nom_club, String description) {
        this.id = id;
        this.nom_club = nom_club;
        this.description = description;
    }



    public Club(String nom_club, String description) {
        this.nom_club = nom_club;
        this.description = description;
    }
    

    public String getNom_club() {
        return nom_club;
    }

    public void setNom_club(String nom_club) {
        this.nom_club = nom_club;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Club{" + "id=" + id + ", rate=" + rate + ", nom_club=" + nom_club + ", description=" + description + '}';
    }

   
}
