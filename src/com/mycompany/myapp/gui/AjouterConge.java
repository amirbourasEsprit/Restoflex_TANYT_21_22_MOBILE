/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ToastBar;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.entities.Conge;
import com.mycompany.myapp.services.ServiceConge;
import java.util.Date;

import javafx.scene.control.DatePicker;

/**
 *
 * @author Yosr Belaam
 */
public class AjouterConge extends Form {

    public AjouterConge(Form previous) {
        setTitle("Ajouter Congé");
        setLayout(BoxLayout.y());
        
        Picker dateDeb = new Picker();
        dateDeb.setType(Display.PICKER_TYPE_DATE);
      

        //tfDateDeb.setUIID("TextField");
       // addStringValue("dateDeb",tfDateDeb);
        
        
    //    TextField objet2 = new TextField("", " Date Fin");
    //    objet2.setUIID("TextField");
    //    addStringValue("Objet",objet2);
        Picker dateFin = new Picker();
        dateDeb.setType(Display.PICKER_TYPE_DATE);

        //  DatePicker DpDateDeb = new DatePicker();
        //  DatePicker DpDateFin = new DatePicker();
         
        TextField tfType = new TextField("", "Type Congé");
        TextField tfId = new TextField("", "id de l'utilisateur");
        TextField tfDateDeb = new TextField("", "Date debut");
        TextField tfDateFin= new TextField("", "Date fin");
        

        SimpleDateFormat format = new SimpleDateFormat("yyyy_MM-dd");

        Button btnValider = new Button("Valider");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfType.getText().isEmpty())
                        || (tfId.getText().isEmpty())) {
                    Dialog.show("Alert", "Champs Vides", new Command("OK"));
                } else {
                    try {
                        Conge c = new Conge();
                        c.setId_utilisateur(Integer.parseInt(tfId.getText()));
                        c.setDate_deb(format.format(dateDeb.getDate()));
                        c.setDate_fin(format.format(dateFin.getDate()));
                        c.setEtat("en cours");
                        if (ServiceConge.getInstance().AjouterConge(c)) {
                            Dialog.show("Success", "Conge ajouté", new Command("OK"));
                            ToastBar.getInstance().setPosition(BOTTOM);
                            ToastBar.Status status = ToastBar.getInstance().createStatus();
                            status.setShowProgressIndicator(true);
                            status.setMessage("Congé ajouté avec succés");
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

        addAll(tfType, tfId,tfDateDeb,tfDateFin, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

    private void addAll(DatePicker DpDateDeb, DatePicker DpDateFin, TextField tfType, TextField tfId, Button btnValider) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void addStringValue(String objet, TextField objet0) {
    }

}
