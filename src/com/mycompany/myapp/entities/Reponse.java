/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author HP
 */
public class Reponse {
      private int id;
    private String reponse,date,Reclamation;

    public Reponse() {
    }

    public Reponse(String reponse, String date) {
        this.reponse = reponse;
        this.date = date;
    }

    public Reponse(int id, String reponse, String date) {
        this.id = id;
        this.reponse = reponse;
        this.date = date;
    }

    public Reponse(String reponse, String date, String Reclamation) {
        this.reponse = reponse;
        this.date = date;
        this.Reclamation = Reclamation;
    }

      
 @Override
    public String toString() {
       return "Reponse{" + "id=" + id + ", reponse=" + reponse +",date=" + date   + "Reclamation=" + Reclamation +"}";
    }

    public int getId() {
        return id;
    }

    public String getReponse() {
        return reponse;
    }

    public String getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReclamation() {
        return Reclamation;
    }

    public void setReclamation(String Reclamation) {
        this.Reclamation = Reclamation;
    }
               
        
    
    
}
