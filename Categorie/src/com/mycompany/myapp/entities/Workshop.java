/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author HP
 */
public class Workshop {
    
    
    
     private int id;
    private String titre,description,lieu,date,file,Club;

    public Workshop() {
    }

    public Workshop(int id, String titre, String description, String lieu, String date, String file, String Club) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.lieu = lieu;
        this.date = date;
        this.file = file;
        this.Club = Club;
    }

    public Workshop(String titre, String description, String lieu, String date, String file) {
        this.titre = titre;
        this.description = description;
        this.lieu = lieu;
        this.date = date;
        this.file = file;
 
    }

    public Workshop(String titre, String description, String lieu, String date, String file, String Club) {
        this.titre = titre;
        this.description = description;
        this.lieu = lieu;
        this.date = date;
        this.file = file;
        this.Club = Club;
    }

  

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getClub() {
        return Club;
    }

    public void setClub(String Club) {
        this.Club = Club;
    }

    @Override
    public String toString() {
        return "Workshop{" + "id=" + id + ", titre=" + titre + ", description=" + description + ", lieu=" + lieu + ", date=" + date + ", file=" + file + ", Club=" + Club + '}';
    }
    

  
    

}
