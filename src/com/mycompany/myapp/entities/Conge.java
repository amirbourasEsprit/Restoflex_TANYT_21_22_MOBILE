/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.entities;

import java.util.Date;





/**
 *
 * @author Yosr Belaam
 */
public class Conge {
     int id_conge;
     String date_deb;
     String date_fin;
     int solde_restant;
     String etat;
     int id_type_conge;
     int id_utilisateur;
   // public utilisateur user;
    public Conge() {
    }

    public Conge(String date_deb, String date_fin, int id_type_conge, int id_utilisateur) {
        this.date_deb = date_deb;
        this.date_fin = date_fin;
        this.etat ="en cours";
        this.id_type_conge = id_type_conge;
        this.id_utilisateur = id_utilisateur;
    }

    
    public Conge(int id_conge, String date_deb, String date_fin, int solde_restant, String etat, int id_type_conge, int id_utilisateur) {
        this.id_conge = id_conge;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
        this.solde_restant = solde_restant;
        this.etat = etat;
        this.id_type_conge = id_type_conge;
        this.id_utilisateur = id_utilisateur;
    }

    public int getId_conge() {
        return id_conge;
    }

    public String getDate_deb() {
        return date_deb;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public int getSolde_restant() {
        return solde_restant;
    }

    public String getEtat() {
        return etat;
    }

    public int getId_type_conge() {
        return id_type_conge;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_conge(int id_conge) {
        this.id_conge = id_conge;
    }

    public void setDate_deb(String date_deb) {
        this.date_deb = date_deb;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public void setSolde_restant(int solde_restant) {
        this.solde_restant = solde_restant;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setId_type_conge(int id_type_conge) {
        this.id_type_conge = id_type_conge;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    @Override
    public String toString() {
        return "Conge{" + "id_conge=" + id_conge + ", date_deb=" + date_deb + ", date_fin=" + date_fin + ", solde_restant=" + solde_restant + ", etat=" + etat + ", id_type_conge=" + id_type_conge + ", id_utilisateur=" + id_utilisateur + '}';
    }
    
}
