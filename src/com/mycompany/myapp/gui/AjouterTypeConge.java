/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.TypeConge;
import com.mycompany.myapp.services.ServiceTypeConge;

/**
 *
 * @author Yosr Belaam
 */
public class AjouterTypeConge extends Form{

    public AjouterTypeConge(Form previous) {
        setTitle("Ajouter Type Congé");
        setLayout(BoxLayout.y());

  
        TextField tfNom = new TextField("", "Nom de Type congé");

        Button btnValider = new Button("Valider");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().isEmpty()))
                {
                    Dialog.show("Alert", "Champs Vides", new Command("OK"));
                } else {
                    try {
                        TypeConge Tc = new TypeConge();
                        Tc.setNom_type_conge(tfNom.getText());                              
                        
                        if (ServiceTypeConge.getInstance().AjouterTypeConge(Tc)) {
                            Dialog.show("Success", "Type Conge ajouté", new Command("OK"));
                            ToastBar.getInstance().setPosition(BOTTOM);
                            ToastBar.Status status = ToastBar.getInstance().createStatus();
                            status.setShowProgressIndicator(true);
                            status.setMessage("Type Congé ajouté avec succés");
                            status.setExpires(10000);
                            status.show();
                            refreshTheme();
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }

            }

        });

        addAll(tfNom,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
}
