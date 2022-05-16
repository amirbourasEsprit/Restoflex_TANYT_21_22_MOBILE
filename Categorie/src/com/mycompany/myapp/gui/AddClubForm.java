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
import com.mycompany.myapp.entities.Club;

import com.mycompany.myapp.services.ServiceClub;


import java.io.IOException;

/**
 *
 * @author bhk
 */
public class AddClubForm extends Form{

    public AddClubForm(Form previous) {
        setTitle("Add a new Categorie");
        setLayout(BoxLayout.y());
        
       TextField tfName = new TextField("","name");
        TextField tfDescription= new TextField("", "description");
       

        
        
        

        Button btnValider = new Button("Add Categorie");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
           
                    try {
                        //int password, int cin, String nom, String prenom, String datenaissance, String role, String access, String image
                        Club c = new Club(tfName.getText().toString(), tfDescription.getText().toString());
                        if( ServiceClub.getInstance().addCategorie(c))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                ToastBar.getInstance().setPosition(BOTTOM);
                ToastBar.Status status = ToastBar.getInstance().createStatus();
                status.setShowProgressIndicator(true);
                //status.setIcon(res.getImage("done.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
                status.setMessage("Categorie ajouté avec succés");
                status.setExpires(10000);
                status.show();

                refreshTheme();
                    }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                
                
                
            }

           
        });
        
        addAll(tfName,tfDescription,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
}
