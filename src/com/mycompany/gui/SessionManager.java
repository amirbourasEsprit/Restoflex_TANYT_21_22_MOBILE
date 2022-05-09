/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.io.Preferences;

/**
 *
 * @author Lenovo
 */
public class SessionManager {
    
    public static Preferences pref ; // 3ibara memoire sghira nsajlo fiha data 
    
    
    
    // hethom données ta3 user lyt7b tsajlhom fi session  ba3d login 
    private static int id_utilisateur ; 
    private static String nom; 
    private static String prenom;
    private static String numTel; 
    private static String passowrd ;
    private static String adresse;

    public static Preferences getPref() {
        return pref;
    }

    public static void setPref(Preferences pref) {
        SessionManager.pref = pref;
    }

    public static int getIdUtilisateur() {
        return pref.get("id_utilisateur",id_utilisateur);// kif nheb njib id user connecté apres njibha men pref 
    }

    public static void setIdUtilisateur(int id_utilisateur) {
        pref.set("id_utilisateur",id_utilisateur);//nsajl id user connecté  w na3tiha identifiant "id";
    }

    public static String getNomUtilisateur() {
        return pref.get("nom",nom);
    }

    public static void setNomUtilisateur(String nom) {
         pref.set("nom",nom);
    }
    
    public static String getPrenomUtilisateur() {
        return pref.get("prenom",prenom);
    }

    public static void setPrenomUtilisateur(String prenom) {
         pref.set("prenom",prenom);
    }

    public static String getNumUtilisateur() {
        return pref.get("numTel",numTel);
    }

    public static void setNumlUtilisateur(String numTel) {
         pref.set("numTel",numTel);
    }

    public static String getPassowrdUtilisateur() {
        return pref.get("passowrd",passowrd);
    }

    public static void setPassowrdUtilisateur(String passowrd) {
         pref.set("passowrd",passowrd);
    }

    public static String getAdresseUtilisateur() {
        return pref.get("adresse",adresse);
    }

    public static void settAdresseUtilisateur(String adresse) {
         pref.set("adresse",adresse);
    }
    
    
    
    
    
    
}