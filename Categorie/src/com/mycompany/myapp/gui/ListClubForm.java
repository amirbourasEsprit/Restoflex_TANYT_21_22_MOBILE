package com.mycompany.myapp.gui;

import com.codename1.components.InteractionDialog;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.services.ServiceClub;
import java.util.ArrayList;
import com.mycompany.myapp.entities.Club;




/**
 *
 * @author bhk
 */

public class ListClubForm extends Form {
Form previous;
public static Club categorieActuelle = null;
public ListClubForm(Form previous) {

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



        ArrayList<Club> categories = ServiceClub.getInstance().getAllCategories();
        for (int i = 0; i < categories.size(); i++) {
            this.add(creerCategorie(categories.get(i)));
        }



    }

    private Component creerCategorie(Club Categorie) {
        Container CategorieModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
       CategorieModel.setUIID("CategorieContainer");

        Label labelName = new Label((String) Categorie.getNom_club());
        labelName.setUIID("nom_club");
        Label labelDescription = new Label((String) Categorie.getDescription());
        labelDescription.setUIID("description");
        
  
       /* Label labellongitude = new Label((String) Evenement.getlongitude());
        labellongitude.setUIID("longitude");
        
        Label labellatitude = new Label((String) Evenement.getlatitude());
        labellatitude.setUIID("latitude");
        */

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
            dlg.add(BorderLayout.CENTER, new Label("Voulez vous vraiment supprimer?"));
            Button btnClose = new Button("Annuler");
            btnClose.addActionListener((ee) -> dlg.dispose());
            Button btnConfirm = new Button("Confirmer");
            btnConfirm.addActionListener(actionConf -> {
                 ServiceClub.getInstance().supprimerCategorie(Categorie);
                categorieActuelle  = null;
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
categorieActuelle = Categorie;
                
            new modifclub(this).show();
            
            modifclub.tfId.setText(String.valueOf(Categorie.getId())); 
            this.removeAll();
                addGUIs();
                this.refreshTheme();
        new modifclub(this).show();
            
        });

        
//labellongitude,labellatitude,
        btnsContainer.addAll(btnModifier,btnSupprimer);
    CategorieModel.addAll(labelName, labelDescription,btnsContainer);     
        return CategorieModel;


    }

}
