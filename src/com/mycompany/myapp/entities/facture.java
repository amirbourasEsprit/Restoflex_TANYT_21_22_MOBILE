/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;




/**
 *
 * @author Nesrine
 */
public class facture {
     private long num_facture;
    private String date_facture;
    private float total;
    private String statut;
    private long id_fournisseur;
    private long id_rest;

    public facture() {
    }

    public facture(long num_facture, String date_facture, float total, String statut, long id_fournisseur, long id_rest) {
        this.num_facture = num_facture;
        this.date_facture = date_facture;
        this.total = total;
        this.statut = statut;
        this.id_fournisseur = id_fournisseur;
        this.id_rest = id_rest;
    }

    public facture(String date_facture, float total, String statut, long id_fournisseur, long id_rest) {
        this.date_facture = date_facture;
        this.total = total;
        this.statut = statut;
        this.id_fournisseur = id_fournisseur;
        this.id_rest = id_rest;
    }

    public facture(long num_facture, String statut) {
        this.num_facture = num_facture;
        this.statut = statut;
    }

    public facture(String statut) {
        this.statut = statut;
    }

    public facture(long num_facture, String date_facture, float total, String statut) {
        this.num_facture = num_facture;
        this.date_facture = date_facture;
        this.total = total;
        this.statut = statut;
    }
    

    public long getNum_facture() {
        return num_facture;
    }

    public void setNum_facture(long num_facture) {
        this.num_facture = num_facture;
    }

    public String getDate_facture() {
        return date_facture;
    }

    public void setDate_facture(String date_facture) {
        this.date_facture = date_facture;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public long getId_fournisseur() {
        return id_fournisseur;
    }

    public void setId_fournisseur(long id_fournisseur) {
        this.id_fournisseur = id_fournisseur;
    }

    public long getId_rest() {
        return id_rest;
    }

    public void setId_rest(long id_rest) {
        this.id_rest = id_rest;
    }

    @Override
    public String toString() {
        return "facture{" + "num_facture=" + num_facture + ", date_facture=" + date_facture + ", total=" + total + ", statut=" + statut + ", id_fournisseur=" + id_fournisseur + ", id_rest=" + id_rest + '}';
    }
    
    
}
