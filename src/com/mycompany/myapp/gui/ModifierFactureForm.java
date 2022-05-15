/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.facture;
import com.mycompany.myapp.services.ServiceFacture;


/**
 *
 * @author Nesrine
 */
public class ModifierFactureForm extends Form  {
  static TextField nf = new TextField();
    facture current;
public ModifierFactureForm (Form previous) {
        setTitle("modifier Facture");
        setLayout(BoxLayout.y());
        
       
        TextField tfStatut= new TextField("", "statut");
       
   
        Button btnValider = new Button("Modifier Facture");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfStatut.getText().length()==0))
                    Dialog.show("Alert", "Veuillez remplir le champ!", new Command("OK"));
                else
                {
                    try {
                    facture f = new facture(tfStatut.getText().toString());
                        if( ServiceFacture.getInstance().modifierFacture(f,Long.parseLong(nf.getText())))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "nom must be a string", new Command("OK"));
                        
                    }
                    
                }
                
         }
        });
        
        addAll(tfStatut,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
}

