/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.l10n.SimpleDateFormat;
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
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.entities.Conge;
import com.mycompany.myapp.services.ServiceConge;
import java.util.Date;
import javafx.scene.control.DatePicker;

/**
 *
 * @author Yosr Belaam
 */
public class ModifierConge extends Form {

    static TextField tfId = new TextField();
    Conge current;

    public ModifierConge(Form previous) {
        setTitle("modifier conge");
        setLayout(BoxLayout.y());
        Picker dateDeb = new Picker();
        dateDeb.setType(Display.PICKER_TYPE_DATE);
        Picker dateFin = new Picker();
        dateDeb.setType(Display.PICKER_TYPE_DATE);

        TextField tfType = new TextField("", "Type de Congé");
        TextField tfId = new TextField("", "id de l'utilisateur");

        Button btnValider = new Button("Modifier Congé");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((dateDeb.getText().isEmpty()) || (dateFin.getText().isEmpty()) || (tfType.getText().isEmpty())
                        || (tfId.getText().isEmpty())) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy_MM-dd");

                        Conge c = new Conge();
                        c.setId_utilisateur(Integer.parseInt(tfId.getText()));

                        c.setDate_deb(format.format(dateDeb.getDate()));
                        c.setDate_fin(format.format(dateFin.getDate()));
                        c.setEtat("en cours");
                        if (ServiceConge.getInstance().modifierConge(c, Integer.parseInt(tfId.getText()))) {
                            Dialog.show("Success", "Conge Modifié", new Command("OK"));
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "nom must be a string", new Command("OK"));

                    }

                }

            }
        });

        addAll(dateDeb, dateFin, tfType, tfId, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

    private void addAll(DatePicker DpDateDeb, DatePicker DpDateFin, TextField tfType, TextField tfId, Button btnValider) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
