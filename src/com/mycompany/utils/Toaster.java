/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.utils;

import com.codename1.components.ToastBar;

/**
 *
 * @author Dah
 */
public class Toaster {
    
    public static ToastBar.Status showLoading() {
        ToastBar.Status status = ToastBar.getInstance().createStatus();
        status.setMessage("Chargement en cours...");
        status.show(); 
        return status;
    }
}
