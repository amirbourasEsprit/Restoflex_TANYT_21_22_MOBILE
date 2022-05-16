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
import com.mycompany.myapp.entities.Conge;
import com.mycompany.myapp.entities.TypeConge;
import com.mycompany.myapp.services.ServiceConge;
import com.mycompany.myapp.services.ServiceTypeConge;
import java.util.Date;
import javafx.scene.control.DatePicker;

/**
 *
 * @author Yosr Belaam
 */
public class ModifierTypeConge extends Form{
    static TextField tfId = new TextField();
    Conge current;
public ModifierTypeConge (Form previous) {
        setTitle("Modifier Type Congé");
        setLayout(BoxLayout.y());
        
        
        TextField tfNom = new TextField("", "Nom du Type");
   
        Button btnValider = new Button("Modifier Type Congé");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().isEmpty()))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        TypeConge Tc = new TypeConge();
                        Tc.setNom_type_conge(tfNom.getText());
                       
                        if( ServiceTypeConge.getInstance().modifierTypeConge(Tc))
                        {
                           Dialog.show("Success","Type Conge Modifié",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "nom must be a string", new Command("OK"));
                        
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfNom,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }

    private void addAll( TextField tfNom, Button btnValider) {
    }
    
}
