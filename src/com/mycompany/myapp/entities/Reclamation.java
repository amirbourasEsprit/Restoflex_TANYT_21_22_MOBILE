/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author Asus
 */
public class Reclamation {
 private long num_reclamation;
    private String destinataire;
    private String description;
    private String statut_reclamation;
    private String date_reclamation;
    private long id_type_reclamation;
    private long id_utilisateur;
    
      public Reclamation() {
    }

    public Reclamation(String destinataire, String description, String statut_reclamation, String date_reclamation) {
        this.destinataire = destinataire;
        this.description = description;
        this.statut_reclamation = statut_reclamation;
        this.date_reclamation = date_reclamation;
    }


    public Reclamation(long num_reclamation, String destinataire, String description, String statut_reclamation, String date_reclamation, long id_type_reclamation, long id_utilisateur) {
        this.num_reclamation = num_reclamation;
        this.destinataire = destinataire;
        this.description = description;
        this.statut_reclamation = statut_reclamation;
        this.date_reclamation = date_reclamation;
        this.id_type_reclamation = id_type_reclamation;
        this.id_utilisateur = id_utilisateur;
    }

    public Reclamation(String destinataire, String description, String statut_reclamation, String date_reclamation, long id_type_reclamation, long id_utilisateur) {
        this.destinataire = destinataire;
        this.description = description;
        this.statut_reclamation = statut_reclamation;
        this.date_reclamation = date_reclamation;
        this.id_type_reclamation = id_type_reclamation;
        this.id_utilisateur = id_utilisateur;
    }

    public Reclamation(String statut_reclamation) {
        this.statut_reclamation = statut_reclamation;
    }

    public long getNum_reclamation() {
        return num_reclamation;
    }

    public Reclamation(String destinataire, String description, String statut_reclamation) {
        this.destinataire = destinataire;
        this.description = description;
        this.statut_reclamation = statut_reclamation;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public String getDescription() {
        return description;
    }

    public String getStatut_reclamation() {
        return statut_reclamation;
    }

    public String getDate_reclamation() {
        return date_reclamation;
    }

    public long getId_type_reclamation() {
        return id_type_reclamation;
    }

    public long getId_utilisateur() {
        return id_utilisateur;
    }

    public void setNum_reclamation(long num_reclamation) {
        this.num_reclamation = num_reclamation;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatut_reclamation(String statut_reclamation) {
        this.statut_reclamation = statut_reclamation;
    }

    public void setDate_reclamation(String date_reclamation) {
        this.date_reclamation = date_reclamation;
    }

    public void setId_type_reclamation(long id_type_reclamation) {
        this.id_type_reclamation = id_type_reclamation;
    }

    public void setId_utilisateur(long id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }
     @Override
    public String toString() {
        return "reclamation{" + "num_reclamation=" + num_reclamation + ", destinataire=" + destinataire + ", description=" + description + ", statut_reclamation=" + statut_reclamation + ", date_reclamation=" + date_reclamation + ", id_type_reclamation=" + id_type_reclamation + ", id_utilisateur=" + id_utilisateur + '}';
    }
    
    }
    
    
    
   
    

