
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;
import com.codename1.components.ToastBar;
import com.codename1.notifications.LocalNotification;
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
import com.mycompany.myapp.entities.Workshop;
import com.mycompany.myapp.entities.Club;
import com.mycompany.myapp.services.ServiceWorkshop;
import com.mycompany.myapp.services.ServiceClub;
import java.util.Map;
import com.codename1.ui.spinner.Picker;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import com.codename1.ui.Display;
import com.codename1.io.File;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import static com.codename1.ui.CN.openGallery;
import static com.codename1.ui.CN1Constants.GALLERY_IMAGE;
import com.codename1.ui.Image;
import com.codename1.ui.util.ImageIO;
import rest.file.uploader.tn.FileUploader;
import com.codename1.ui.util.Resources;
import com.codename1.ext.filechooser.FileChooser;

/**
 *
 * @author bhk
 */
public class AddWorkshopForm extends Form{
private String fileNameInServer = "";
    public AddWorkshopForm(Form previous) {
        setTitle("Add a new Event");
        setLayout(BoxLayout.y());
        
       TextField tfName = new TextField("","titre");
        TextField tfDescription= new TextField("", "description");
         TextField tfLieu= new TextField("", "lieu");
          TextField tfPicture= new TextField("", "file");
           TextField tfDate= new TextField("", "date");
               TextField tfCategorie= new TextField("", "club");
               
               
               
               Button imgBtn = new Button("parcourir");
        //addStringValue("", imgBtn);
          imgBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    FileUploader fu = new FileUploader("http://127.0.0.1:8000/uploads/");

                    //Upload
                    Display.getInstance().openGallery(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent v) {
                           

                        }
                    }, Display.GALLERY_IMAGE);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });
      // a ajouter
       
        
        
        
        

        Button btnValider = new Button("Add event");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length()==0)||(tfDescription.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        // String Name, String description, String lieu, String picture, String date
                    //     a ajouter 
                     
                         Workshop e = new Workshop(tfName.getText().toString(), tfDescription.getText().toString(),tfDate.getText().toString(),tfLieu.getText().toString(),tfPicture.getText().toString(),tfCategorie.getText().toString());
                        if( ServiceWorkshop.getInstance().addEvent(e))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                ToastBar.getInstance().setPosition(BOTTOM);
                ToastBar.Status status = ToastBar.getInstance().createStatus();
                status.setShowProgressIndicator(true);
                //status.setIcon(res.getImage("done.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
                status.setMessage("event ajouté avec succés");
                status.setExpires(10000);
                status.show();

                refreshTheme();
                    }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }

           
        });
        
        addAll(tfName,tfDescription,tfDate,tfLieu,tfPicture,tfCategorie,imgBtn,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
}
