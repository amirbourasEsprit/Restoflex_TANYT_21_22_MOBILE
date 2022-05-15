/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.BASELINE;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.services.ServiceCategorie;

/**
 *
 * @author HP
 */
public class modifcategorie extends Form  {
  static TextField tfId = new TextField();
    Categorie current;
public modifcategorie (Form previous) {
        setTitle("modifier Categorie");
        setLayout(BoxLayout.y());
        
        TextField tfName = new TextField("","name");
       
   
        Button btnValider = new Button("Modifier Categorie");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
Categorie c = new Categorie(tfName.getText().toString());
                        if( ServiceCategorie.getInstance().modifcategorie(c,Integer.parseInt(tfId.getText())))
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
        
        addAll(tfName,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
}
