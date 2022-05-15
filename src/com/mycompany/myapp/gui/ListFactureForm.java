/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.InteractionDialog;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.facture;
import com.mycompany.myapp.services.ServiceFacture;

import java.util.ArrayList;

/**
 *
 * @author Nesrine
 */
public class ListFactureForm extends Form {
    
    Form previous;
    public static facture factAc = null;
    
public ListFactureForm(Form previous) {

        super("", new BoxLayout(BoxLayout.Y_AXIS));
        this.previous = previous;
        addGUIs();
        super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        getToolbar().hideToolbar();
}

public void refresh() {
        this.removeAll();
        this.refreshTheme();
}

private void addGUIs() {

ArrayList<facture> listF = ServiceFacture.getInstance().afficherFacture();
        for (int i = 0; i < listF.size(); i++) {
            this.add(creerFact(listF.get(i)));
        }



    }

    private Component creerFact(facture f) {
        Container FactModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
       FactModel.setUIID("FactureContainer");
        
        String nf = String.valueOf(f.getNum_facture());
        Label NumF = new Label((String) nf);
        NumF.setUIID("N° Facture");
        Label dateF = new Label((String) f.getDate_facture());
        dateF.setUIID("Date");
        String totalFact = String.valueOf(f.getTotal());
        Label totF = new Label((String) totalFact);
        totF.setUIID("Total");
        Label etat = new Label((String) f.getStatut());
        etat.setUIID("Etat");
        
        Container btnsContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        btnsContainer.setUIID("buttonsContainer");
        btnsContainer.setPreferredH(200);

        Button btnModifier = new Button();
        btnModifier.setUIID("actionButton");
              Button btnSupprimer = new Button();
              btnSupprimer.setUIID("actionButton");
                FontImage.setMaterialIcon(btnSupprimer, FontImage.MATERIAL_DELETE);
                FontImage.setMaterialIcon(btnModifier, FontImage.MATERIAL_UPDATE);           
 btnSupprimer.addActionListener(action -> {

            InteractionDialog dlg = new InteractionDialog("Confirmer la suppression");
            dlg.setLayout(new BorderLayout());
            dlg.add(BorderLayout.CENTER, new Label("Voulez vous vraiment supprimer la facture?"));
            Button btnClose = new Button("Annuler");
            btnClose.addActionListener((ee) -> dlg.dispose());
            Button btnConfirm = new Button("Confirmer");
            btnConfirm.addActionListener(actionConf -> {
                 ServiceFacture.getInstance().deleteFacture(f.getNum_facture());
                factAc = null;
                dlg.dispose();
                this.removeAll();
                addGUIs();
                this.refreshTheme();
            });
            Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
            btnContainer.addAll(btnClose, btnConfirm);
            dlg.addComponent(BorderLayout.SOUTH, btnContainer);
            //Dimension pre = dlg.getContentPane().getPreferredSize();
            //dlg.show(0, 0, Display.getInstance().getDisplayWidth() - (pre.getWidth() + pre.getWidth() / 6), 0);
            dlg.show(1000, 1000, 10, 10);

        });
        
        btnModifier.addActionListener(action -> {
            factAc = f;
                           
            ModifierFactureForm.nf.setText(String.valueOf(f.getNum_facture())); 
            this.removeAll();
                addGUIs();
                this.refreshTheme();
        new ModifierFactureForm(this).show();
            
        });

        
//labellongitude,labellatitude,
        btnsContainer.addAll(btnModifier,btnSupprimer);
    FactModel.addAll(NumF,dateF,totF,etat,btnsContainer);     
        return FactModel;


    }

}
