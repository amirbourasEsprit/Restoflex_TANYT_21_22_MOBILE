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
import com.mycompany.myapp.entities.Reclamation;


import com.mycompany.myapp.services.ServiceReclamation;


import java.io.IOException;

/**
 *
 * @author bhk
 */
public class AddReclamationForm extends Form{

    public AddReclamationForm(Form previous) {
       setTitle("Ajouter une reclamation");
        setLayout(BoxLayout.y());
          
        
       TextField tfdestinataire = new TextField("","destinataire");
        TextField tfDescription= new TextField("", "description");
          TextField tfStatutReclamation = new TextField("","StatutReclamation");
     
        
        Button btnValider = new Button("Ajouter reclamation");
         btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfdestinataire.getText().length()==0)||(tfDescription.getText().length()==0)||(tfStatutReclamation.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                     {
                    try {
                        //int password, int cin, String nom, String prenom, String datenaissance, String role, String access, String image
                        Reclamation r = new Reclamation(tfdestinataire.getText().toString(), tfDescription.getText().toString(), tfStatutReclamation.getText().toString());
                        if( ServiceReclamation.getInstance().ajoutReclamation(r))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                ToastBar.getInstance().setPosition(BOTTOM);
                ToastBar.Status status = ToastBar.getInstance().createStatus();
                status.setShowProgressIndicator(true);
                //status.setIcon(res.getImage("done.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
                status.setMessage("reclamation ajouté avec succés");
                status.setExpires(10000);
                status.show();

                   refreshTheme();
                    }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
            }
           
        });
        
        
        addAll(tfdestinataire,tfDescription,tfStatutReclamation,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    
 
                 }}