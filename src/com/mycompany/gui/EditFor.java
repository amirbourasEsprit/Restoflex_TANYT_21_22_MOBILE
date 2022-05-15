/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;
import com.codename1.components.ImageViewer;
import com.codename1.components.ToastBar;
 
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
 
import com.codename1.ui.Component;
 
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.*;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.ImageIO;
 
import com.codename1.ui.util.Resources;

import com.mycompany.entities.fournisseur;
import com.mycompany.gui.back.BaseFormBack;
import com.mycompany.gui.back.MainUI;
 
 
 
import java.io.IOException;
import java.io.OutputStream;
import com.mycompany.services.ServiceFour;
import utils.FileChooser.FileChooser;
 
/**
 *
 * @author asmab
 */
public class EditFor  extends BaseFormBack {
     public Resources theme;
    private fournisseur user;

    private Image img ;
    private TextField nom = new TextField();
    private  TextField prenom = new TextField();
    private  TextField email = new TextField();
    private  FontImage save;
    private Image uploadImg;
    private ImageViewer imageViewer;
    private String imageName = "";
//    private PickerComponent birthDate = new PickerComponent();

    public EditFor(fournisseur user,Resources res) {
   Toolbar tb = new Toolbar(true);
             setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
       setTitle("Edit fournisseur");
		setLayout(BoxLayout.y());
                 TextField tfName = new TextField(user.getNom_fournisseur(),"nom fournisseur");
 tfName.getStyle().setFgColor(0x000000);
	   TextField tfdomaine = new TextField(user.getDomaine_fournisseur(),"domaine founisseur");
 tfdomaine.getStyle().setFgColor(0x000000);
		  TextField mat = new TextField(user.getMatricule_fiscale(),"matricule fournisseur");
 mat.getStyle().setFgColor(0x000000);
  TextField num = new TextField(user.getNum_tel_fournisseur(),"numtel fournisseur");
 num.getStyle().setFgColor(0x000000);
	   TextField email = new TextField(user.getEmail_fournisseur(),"email founisseur");
 email.getStyle().setFgColor(0x000000);
		  TextField adrs = new TextField(user.getAdresse_fournisseur(),"adresse fournisseur");
 adrs.getStyle().setFgColor(0x000000);
		
                // TextField image=new TextField(user.getImage(),"event image");
                Button btnAdd =new Button("modifier");
                        	 
	Button image1 =new Button("ajouter imaage");  
 Label imagename=new Label();
 fournisseur t=new fournisseur();
 /*
 t.setImage(user.);
 image1.addActionListener((ActionEvent e) -> {
            if (FileChooser.isAvailable()) {
                // FileChooser.setOpenFilesInPlace(true);
                FileChooser.showOpenDialog(false, ".jpg, .jpeg, .png", (ActionEvent e2) -> {
                    String file = (String) e2.getSource();
                    if (file == null) {
                        add("No file was selected");
                        revalidate();
                    } else {
                        Image logo;

                        try {
                            logo = Image.createImage(file).scaledHeight(500);
                            add(logo);
                            if (file.lastIndexOf(".") > 0) {
                                StringBuilder hi = new StringBuilder(file);
                                if (file.startsWith("file://")) {
                                    hi.delete(0, 7);
                                }
                                Log.p(hi.toString());
                                try {
                                    String namePic = saveFileToDevice(file);
                                    this.imageName = namePic;
                                    System.out.println(namePic);
                                } catch (IOException ex) {
                                }

                                revalidate();


                            }
                            String imageFile = FileSystemStorage.getInstance().getAppHomePath() + imageName;

                            try (OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
                                ImageIO.getImageIO().save(logo, os, ImageIO.FORMAT_PNG, 1);
                            } catch (IOException err) {
                            }
                        } catch (IOException ex) {
                        }
                    }
                });
            }
        });*/
	btnAdd.addActionListener((evt)->{
           
		if ((tfName.getText().length()==0)||(tfdomaine.getText().length()==0))
            Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
		else
        {
            try {
                //Event t = new Event(tfName.getText().toString(), tflieu.getText().toString(),descri.getText().toString());
                t.setNom_fournisseur(tfName.getText().toString());
                t.setDomaine_fournisseur(tfdomaine.getText().toString());
                t.setEmail_fournisseur(email.getText().toString());
                t.setNum_tel_fournisseur(num.getText().toString());
                t.setMatricule_fiscale(mat.getText().toString());
                t.setAdresse_fournisseur(adrs.getText().toString());
               /* if(imageName!=""){
                t.setImage(imageName);
                }
                else{
                    t.setImage(user.getImage());
                }*/
           //System.out.println(image.getText().toString());    
                
// System.out.println(tfName.getText().toString());
                // System.out.println(tflieu.getText().toString());
                if( ServiceFour.getInstance().modifierEvent(t,user.getId_fournisseur()))
                {
                	System.out.println("modifier");
                   Dialog.show("Success","Connection accepted",new Command("OK"));
                     new MainUI(res).show();
                }else
                    Dialog.show("ERROR", "Server error", new Command("OK"));
            } catch (NumberFormatException e) {
                Dialog.show("ERROR", "Status must be a number", new Command("OK"));
            }
            
        }
        });
       
        addAll( tfName,tfdomaine,mat,num,email,adrs,image1,btnAdd);
       //  MapContainer mc = new MapContainer();
              //  getContentPane().replace(getContentPane().getComponentAt(0), dishes CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, false, 300));
                         //  getContentPane().replace(getContentPane().getComponentAt(0),mc , CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, false, 300));

    }
    
    
    
     public final void test (){
            System.out.println("tessssssssssssst");
        }
     protected String saveFileToDevice(String hi) throws IOException {
        int index = hi.lastIndexOf("/");
        hi = hi.substring(index + 1);
        return hi;

    }
   
}
