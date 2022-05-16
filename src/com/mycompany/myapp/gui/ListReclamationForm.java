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
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceRec;

import java.util.ArrayList;

/**
 *
 * @author Nesrine
 */
public class ListReclamationForm extends Form {
    
    Form previous;
    public static Reclamation Rec = null;
    
public ListReclamationForm(Form previous) {

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

ArrayList<Reclamation> listRec = ServiceRec.getInstance().AfficherReclamation();
        for (int i = 0; i < listRec.size(); i++) {
            this.add(creerRec(listRec.get(i)));
            System.out.println(listRec.size());
        }
        



    }

    private Component creerRec(Reclamation rec) {
        Container FactModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
       FactModel.setUIID("ReclamationContainer");
             Label destinataire = new Label((String) rec.getDestinataire());
        destinataire.setUIID("Destinataire");
          Label desc = new Label((String) rec.getDescription());
        desc.setUIID("Descripion");
        Label dateRec = new Label((String) rec.getDate_reclamation());
        dateRec.setUIID("Date");
        Label statut = new Label((String) rec.getStatut_reclamation());
        statut.setUIID("Etat");
        
        Container btnsContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        btnsContainer.setUIID("buttonsContainer");
        btnsContainer.setPreferredH(200);
              Button btnSupprimer = new Button();
              btnSupprimer.setUIID("actionButton");
                FontImage.setMaterialIcon(btnSupprimer, FontImage.MATERIAL_DELETE);
 btnSupprimer.addActionListener(action -> {

            InteractionDialog dlg = new InteractionDialog("Confirmer la suppression");
            dlg.setLayout(new BorderLayout());
            dlg.add(BorderLayout.CENTER, new Label("Voulez vous vraiment supprimer la reclamation?"));
            Button btnClose = new Button("Annuler");
            btnClose.addActionListener((ee) -> dlg.dispose());
            Button btnConfirm = new Button("Confirmer");
            btnConfirm.addActionListener(actionConf -> {
                 ServiceRec.getInstance().deleteReclamation(rec.getNum_reclamation());
                Rec = null;
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
        


        
//labellongitude,labellatitude,
        btnsContainer.addAll(btnSupprimer);
    FactModel.addAll(destinataire,desc,dateRec,statut,btnsContainer);     
        return FactModel;


    }

}

