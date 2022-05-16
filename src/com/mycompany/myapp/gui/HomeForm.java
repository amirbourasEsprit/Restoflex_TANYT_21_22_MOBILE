/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Yosr Belaam
 */
public class HomeForm extends Form {

    Form current;
    public static Form accueilFrontForm;

    public HomeForm() {
        accueilFrontForm = this;
        current = this; //Back 
        setTitle("Home");
        setLayout(BoxLayout.y());

        add(new Label("Choose an option"));

        //  Button btnAddConge = new Button("Ajouter Conge");
        //  Button btnListConges= new Button("Liste des Congés");
        // btnAddConge.addActionListener(e-> new AjouterConge(current).show());
        //   btnListConges.addActionListener(e-> new ListCongeForm(current).show());  
        Button btnAddTypeConge = new Button("Ajouter Type Conge");
        Button btnListTypeConges = new Button("Liste des Types de Congés");
        btnAddTypeConge.addActionListener(e -> new AjouterTypeConge(current).show());
        btnListTypeConges.addActionListener(e -> new ListTypeCongeForm(current).show());

        // addAll(btnAddConge,btnListConges,btnAddTypeConge,btnListTypeConges);
        addAll(btnAddTypeConge, btnListTypeConges);

    }

}
