package com.mycompany.myapp.gui;

import com.codename1.components.InteractionDialog;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;
import com.mycompany.myapp.entities.TypeConge;
import com.mycompany.myapp.services.ServiceConge;
import com.mycompany.myapp.services.ServiceTypeConge;
import java.util.Date;




/**
 *
 * @author Yosr Belaam
 */

public class ListTypeCongeForm extends Form {
Form previous;
    public static TypeConge TypeCongeActuel = null;

    public ListTypeCongeForm(Form previous) {
 
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

        ArrayList<TypeConge> Tconges = ServiceTypeConge.getInstance().getAllConges();
        for (int i = 0; i < Tconges.size(); i++) {
            this.add(creerTypeConge(Tconges.get(i)));
        }

    }

    private Component creerTypeConge(TypeConge Tconge) {
        Container TypeCongeModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        TypeCongeModel.setUIID("TypeCongeContainer");
       
        Label labelNom = new Label((String) Tconge.getNom_type_conge());
        labelNom.setUIID("Nom du Type de Congé");
        System.out.println(Tconge.getId_type_conge());
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
                ServiceTypeConge.getInstance().supprimerTypeConge(Tconge.getId_type_conge());
                TypeCongeActuel = null;
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
            TypeCongeActuel = Tconge;

            new ModifierTypeConge(this).show();

            ModifierTypeConge.tfId.setText(String.valueOf(Tconge.getId_type_conge()));
            this.removeAll();
            addGUIs();
            this.refreshTheme();
            new ModifierTypeConge(this).show();

        });
        btnsContainer.addAll(btnModifier, btnSupprimer);
        TypeCongeModel.addAll(labelNom,btnsContainer);
        return TypeCongeModel;

    }

}
