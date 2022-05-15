package com.mycompany.gui;

import com.codename1.capture.Capture;
import com.codename1.components.CheckBoxList;
import com.codename1.components.SwitchList;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.MultipartRequest;
import static com.codename1.io.rest.Rest.options;
import com.codename1.l10n.SimpleDateFormat; 
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
 

import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
 

import com.mycompany.entities.fournisseur;
import com.mycompany.gui.back.BaseFormBack;
import com.mycompany.gui.back.MainUI;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;


 

 
import com.mycompany.services.ServiceFour;
import utils.FileChooser.FileChooser;
import  com.mycompany.utils.Statics;

public class AddForm extends BaseFormBack {
      
    
        private String imageName;
        String of;
         String of1;
         int indice,i;
         ArrayList<String> listcat = new ArrayList<String>();
	public AddForm(Resources res) {
             Toolbar tb = new Toolbar(true);
             setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
         
        /*     super("AddCarteFormBack", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Add CarteFidelite ");
        getContentPane().setScrollVisible(false);
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("baaaackkk", e -> previous.showBack());
      
        super.addSideMenu(res);
*/
           
            SwitchList switchList = new SwitchList(new DefaultListModel(listcat));
           
CheckBoxList checkBoxList = new CheckBoxList(switchList.getMultiListModel()); 
  
		setTitle("Add event");
		setLayout(BoxLayout.y());
                
  TextField tfName = new TextField("", "nom fournisseur", 20, TextArea.ANY);
 tfName.getStyle().setFgColor(0x000000);
		//TextField tfName=new TextField("","nom event ");
               TextField adres = new TextField("", "adress fournisseur", 20, TextArea.ANY);
               adres.getStyle().setFgColor(0x000000);
		//TextField tflieu=new TextField("","lieu event ");
                TextField email = new TextField("", "email fournisseur", 20, TextArea.ANY);
                  email.getStyle().setFgColor(0x000000);
                   TextField mat = new TextField("", "matricule fournisseur", 20, TextArea.ANY);
		  mat.getStyle().setFgColor(0x000000);
                   TextField domaine = new TextField("", "domaine fournisseur", 20, TextArea.ANY);
               domaine.getStyle().setFgColor(0x000000);
               TextField num = new TextField("", "num fournisseur", 20, TextArea.ANY);
               num.getStyle().setFgColor(0x000000);
		//TextField descri=new TextField("","description event ");
                //TextField image=new TextField("","event name");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
               
                //imageeeeeeeeeeeeeeeeeeeeeeeeeeeeeee upload 
      
	Button image1 =new Button("ajouter image");  
        Button annuler =new Button("annuler");  
 Label imagename=new Label();
 annuler.addActionListener((ActionEvent e)->{
      new MainUI(res).show();
 });
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
        });
		/* image1.addActionListener((e)->{
                         String path =Capture.capturePhoto(Display.getInstance().getDisplayWidth(),-1);
                         if(path !=null){
                             try {
                                 Image img=Image.createImage(path);
                                 imagename.setIcon(img);
                                 System.out.println(path);
                             } catch (IOException ex) {
                                 ex.printStackTrace();
                             }
                         }
                 });*/
                         
	Button btnAdd =new Button("Ajouter");
	btnAdd.addActionListener((evt)->{
		 
		if ((tfName.getText().length()==0)||(adres.getText().length()==0))
            Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
		else
        {
            try { 
                fournisseur t = new fournisseur(tfName.getText().toString(), adres.getText().toString(),email.getText().toString(),mat.getText().toString(),domaine.getText().toString(),num.getText().toString());
                t.setLogo(imageName);
                switchList.addActionListener(e->{
                  //  switchList.
                });
                
             
 // System.out.println(of1);
            // checkBoxList.getMultiListModel().getSelectedIndices());
                 indice=  checkBoxList.getMultiListModel().getSelectedIndex();
                 System.out.println(indice);

//int i=Integer.parseInt(of1);  
 
                if( ServiceFour.getInstance().addevent(t))
                {
                    
                	System.out.println("ajouterr");
                   Dialog.show("Success","Connection accepted",new Command("OK"));
                    new MainUI(res).show();
                }else
                    Dialog.show("ERROR", "Server error", new Command("OK"));
            } catch (NumberFormatException e) {
                Dialog.show("ERROR", "Status must be a number", new Command("OK"));
            }
            
        }
	});
  
	 addAll(tfName,  adres,email,mat,domaine,num,checkBoxList,image1,imagename,btnAdd,annuler);	
          
 //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
	
	
	}
          protected String saveFileToDevice(String hi) throws IOException {
        int index = hi.lastIndexOf("/");
        hi = hi.substring(index + 1);
        return hi;

    }
 
	}

