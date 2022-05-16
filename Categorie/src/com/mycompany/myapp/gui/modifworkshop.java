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
import com.mycompany.myapp.entities.Workshop;
import com.mycompany.myapp.services.ServiceWorkshop;

/**
 *
 * @author HP
 */
public class modifworkshop extends Form  {
  static TextField tfId = new TextField();
    Workshop current;
public modifworkshop (Form previous) {
        setTitle("modifier event");
        setLayout(BoxLayout.y());
        
        TextField tfName = new TextField("","Name");
        TextField tfDescription= new TextField("", "description");
       
   TextField tfDate = new TextField("","date");
        TextField tfLieu= new TextField("", "lieu");
            TextField tfPicture= new TextField("", "picture");
        TextField tfCategorie= new TextField("", "categorie");
        Button btnValider = new Button("Modifier Categorie");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length()==0)||(tfDescription.getText().length()==0)||(tfDate.getText().length()==0)||(tfLieu.getText().length()==0)||(tfPicture.getText().length()==0)||(tfCategorie.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
Workshop e = new Workshop(tfName.getText().toString(), tfDescription.getText().toString(),tfDate.getText().toString(),tfLieu.getText().toString(),tfPicture.getText().toString(),tfCategorie.getText().toString());
                        if( ServiceWorkshop.getInstance().modifevent(e,Integer.parseInt(tfId.getText())))
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
        
        addAll(tfName,tfDescription,tfDate,tfLieu,tfPicture,tfCategorie,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
}
