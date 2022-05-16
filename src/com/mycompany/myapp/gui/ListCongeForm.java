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
import com.mycompany.myapp.entities.Conge;
import com.mycompany.myapp.services.ServiceConge;
import java.util.Date;




/**
 *
 * @author Yosr Belaam
 */

public class ListCongeForm extends Form {
Form previous;
    public static Conge CongeActuel = null;

    public ListCongeForm(Form previous) {

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

        ArrayList<Conge> conges = ServiceConge.getInstance().AfficherConge();
        for (int i = 0; i < conges.size(); i++) {
            this.add(creerConge(conges.get(i)));
        }

    }

    private Component creerConge(Conge conge) {
        Container CongeModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        CongeModel.setUIID("CongeContainer");
      
        
        
        Label labelId = new Label(Integer.toString(conge.getId_utilisateur()));
        labelId.setUIID("idUtilisateur");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Label labelDateDeb= new Label( dateFormat.format((conge.getDate_fin())));
        labelDateDeb.setUIID("dateDeb");
        Label labelDateFin= new Label(dateFormat.format(conge.getDate_fin()));
        labelDateFin.setUIID("dateFin");
        Label labelEtat = new Label(Integer.toString(conge.getId_type_conge()));
        labelEtat.setUIID("idTypeConge");
       

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
                ServiceConge.getInstance().supprimerConge(conge);
                CongeActuel = null;
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
            CongeActuel = conge;

            new ModifierConge(this).show();

            ModifierConge.tfId.setText(String.valueOf(conge.getId_conge()));
            this.removeAll();
            addGUIs();
            this.refreshTheme();
            new ModifierConge(this).show();

        });
        btnsContainer.addAll(btnModifier, btnSupprimer);
        CongeModel.addAll(labelId,labelDateDeb,labelDateFin,labelEtat,btnsContainer);
        return CongeModel;

    }

}
