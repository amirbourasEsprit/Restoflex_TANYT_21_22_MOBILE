/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;
import com.codename1.components.ToastBar;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Categorie;

import com.mycompany.myapp.services.ServiceCategorie;


import java.io.IOException;

/**
 *
 * @author bhk
 */
public class AddCategorieForm extends Form{

    public AddCategorieForm(Form previous) {
        setTitle("Add a new Categorie");
        setLayout(BoxLayout.y());
        
       TextField tfName = new TextField("","name");
        
      
        
        
        

        Button btnValider = new Button("Add Categorie");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                     
                        Categorie c = new Categorie(tfName.getText().toString());
                        if( ServiceCategorie.getInstance().addCategorie(c))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                ToastBar.getInstance().setPosition(BOTTOM);
                ToastBar.Status status = ToastBar.getInstance().createStatus();
                status.setShowProgressIndicator(true);
                status.setMessage("Categorie ajouté avec succés");
                status.setExpires(10000);
                status.show();

                refreshTheme();
                    }else
                            Dialog.show("ajouté", "succes d'ajout", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }

           
        });
        
        addAll(tfName,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
}
