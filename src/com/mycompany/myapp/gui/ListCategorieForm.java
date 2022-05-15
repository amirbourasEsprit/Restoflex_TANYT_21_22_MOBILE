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
import com.mycompany.myapp.services.ServiceCategorie;
import java.util.ArrayList;
import com.mycompany.myapp.entities.Categorie;




/**
 *
 * @author bhk
 */

public class ListCategorieForm extends Form {
Form previous;
public static Categorie categorieActuelle = null;
public ListCategorieForm(Form previous) {

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



        ArrayList<Categorie> categories = ServiceCategorie.getInstance().getAllCategories();
        for (int i = 0; i < categories.size(); i++) {
            this.add(creerCategorie(categories.get(i)));
        }



    }

    private Component creerCategorie(Categorie Categorie) {
        Container CategorieModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
       CategorieModel.setUIID("CategorieContainer");

        Label labelName = new Label((String) Categorie.getNomCategorie());
        labelName.setUIID("name");
        
  

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
                 ServiceCategorie.getInstance().supprimerCategorie(Categorie);
                categorieActuelle  = null;
                dlg.dispose();
                this.removeAll();
                addGUIs();
                this.refreshTheme();
            });
            Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
            btnContainer.addAll(btnClose, btnConfirm);
            dlg.addComponent(BorderLayout.SOUTH, btnContainer);
            dlg.show(1000, 1000, 10, 10);

        });
btnModifier.addActionListener(action -> {
categorieActuelle = Categorie;
                
            new modifcategorie(this).show();
            
            modifcategorie.tfId.setText(String.valueOf(Categorie.getId())); 
            this.removeAll();
                addGUIs();
                this.refreshTheme();
        new modifcategorie(this).show();
            
        });

        
        btnsContainer.addAll(btnModifier,btnSupprimer);
    CategorieModel.addAll(labelName, btnsContainer);     
        return CategorieModel;


    }

}
