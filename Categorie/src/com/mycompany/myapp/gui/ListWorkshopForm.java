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
import com.mycompany.myapp.services.ServiceWorkshop;
import java.util.ArrayList;
import com.mycompany.myapp.entities.Workshop;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.components.ImageViewer;
import com.codename1.components.ToastBar;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.Container;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.services.ServiceClub;



/**
 *
 * @author bhk
 */

public class ListWorkshopForm extends Form {
 String url1 = "http://127.0.0.1/Uploads/image";

Form previous;
public static Workshop eventActuelle = null;
public ListWorkshopForm(Form previous) {

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



        ArrayList<Workshop> events = ServiceWorkshop.getInstance().getAllWorkshops();
        for (int i = 0; i < events.size(); i++) {
            this.add(creerEvent(events.get(i)));
        }



    }

    private Component creerEvent(Workshop Event) {
        Container EventModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
       EventModel.setUIID("EventContainer");

        Label labelName = new Label((String) Event.getTitre());
        labelName.setUIID("titre");
        Label labelDate= new Label((String) Event.getDate());
        labelDate.setUIID("date");
        Label labelDescription = new Label((String) Event.getDescription());
        labelDescription.setUIID("description");
        Label labelLieu = new Label((String) Event.getLieu());
        labelLieu.setUIID("lieu");
        
        
        
           Image placeholder = Image.createImage(200, 200);
            EncodedImage enc = EncodedImage.createFromImage(placeholder, false);
           
//            URLImage urlim = URLImage.createToStorage(enc, Event.getFile(), url1 + "/" + Event.getFile());
            ImageViewer imgV = new ImageViewer();
            //imgV.setImage(urlim);
            
            
            
           
            
            
            
            
            
         Label labelCategorie= new Label((String) Event.getClub());
        labelCategorie.setUIID("Categorie");
  
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
                 ServiceWorkshop.getInstance().supprimerEvent(Event);
                eventActuelle  = null;
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
eventActuelle = Event;
                
            new modifworkshop(this).show();
            
            modifworkshop.tfId.setText(String.valueOf(Event.getId())); 
            this.removeAll();
                addGUIs();
                this.refreshTheme();
        new modifworkshop(this).show();
            
        });

        
//labellongitude,labellatitude,
        btnsContainer.addAll(btnModifier,btnSupprimer);
 EventModel.addAll(labelName, labelDescription, labelDate, labelLieu,imgV,labelCategorie, btnsContainer);     
        return EventModel;


    }

}
