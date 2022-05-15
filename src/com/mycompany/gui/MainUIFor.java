package com.mycompany.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.SpanButton;
//import com.codename1.googlemaps.MapContainer;
import com.codename1.maps.Coord;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

import com.mycompany.entities.fournisseur;
import com.mycompany.gui.EditFor;
import com.mycompany.gui.AddForm;
import com.mycompany.gui.back.BaseFormBack;
import java.util.ArrayList;

import com.mycompany.services.ServiceFour;

/**
 * This class includes the user interface for the restaurant, most of the theme elements are derived from the 
 * designer and can be replaced there. To change a lot of the constants such as the address, title etc. you can 
 * use the static variables defined at the top
 */
public class MainUIFor extends BaseForm {
    private static final String RESTAURANT_NAME = "Ratatouille";
    private static final double RESTAURANT_LATITUDE = 40.7127;
    private static final double RESTAURANT_LONGITUDE = 74.0059;
    private static final Coord RESTAURANT_LOCATION = new Coord(RESTAURANT_LATITUDE, RESTAURANT_LONGITUDE);
    private static final String RESTAURANT_PHONE_NUMBER = "+1800777777";
    private static final String RESTAURANT_DISPLAY_PHONE_NUMBER = "+1-800-777-7777";
    private static final String RESTAURANT_EMAIL = "restaurant@gmail.com";
    private static final String RESTAURANT_DISPLAY_ADDRESS = "Ratatouille\n55 3rd street\nNY NY 66666";
    
    private static final boolean INCLUDE_RESERVATIONS = true;
    private static final String OPEN_TABLE_RESERVATION_ID = "168";
     private Form current;
     
    private Resources theme;
    
    public MainUIFor(Resources theme) {
        
       super("Newsfeed", BoxLayout.y());
      Toolbar t = new Toolbar(true);
        setToolbar(t);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(theme);
        setLayout(new BorderLayout());
        
        Container dishes = createDishesContainer();
        addComponent(BorderLayout.CENTER, dishes);
        revalidate();
        
        Style iconStyle = UIManager.getInstance().getComponentStyle("SideCommandIcon");
        
       
    }

    private void showContactUs() {
        getContentPane().replace(getContentPane().getComponentAt(0), createContactUs(), CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, false, 300));
    }
    
    
    private Container createContactUs() {
        BorderLayout abs = new BorderLayout();
        abs.setCenterBehavior(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE);
        Container contactUsParent = new Container(abs);
        
        Container contactUs = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        contactUsParent.addComponent(BorderLayout.CENTER, contactUs);
        
        contactUs.setScrollableY(true);
        
        Button phone = new Button(RESTAURANT_DISPLAY_PHONE_NUMBER);
        phone.addActionListener((e) -> {
            Display.getInstance().dial(RESTAURANT_PHONE_NUMBER);
        });
        
        phone.setUIID("Label");
        contactUs.addComponent(phone);
        contactUs.setUIID("ContactUsLayer");
        contactUsParent.setUIID("ContactUs");

        Button email = new Button(RESTAURANT_EMAIL);
        email.addActionListener((e) -> {
            Message m = new Message("Type your message here:\n");
            Display.getInstance().sendMessage(new String[] {RESTAURANT_EMAIL}, "Contact from app", m);
        });
        
        email.setUIID("Label");
        contactUs.addComponent(email);
        
        SpanButton address = new SpanButton(RESTAURANT_DISPLAY_ADDRESS);
        address.setUIID("Container");
        address.setTextUIID("Label");
        address.addActionListener((e) -> {
            Display.getInstance().openNativeNavigationApp(RESTAURANT_LATITUDE, RESTAURANT_LONGITUDE);
        });
        
        contactUs.addComponent(address);
        
        return contactUsParent;
    }
    
   /* private void showMap() {
        MapContainer mc = new MapContainer();
        mc.addMarker((EncodedImage)theme.getImage("round_logo.png"), RESTAURANT_LOCATION, RESTAURANT_NAME, RESTAURANT_NAME, null);
        mc.zoom(RESTAURANT_LOCATION, mc.getMaxZoom() - 2);
        getContentPane().replace(getContentPane().getComponentAt(0), mc, CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, false, 300));
}*/
    
    private void showDishesContainer() {
        Container dishes = createDishesContainer();

        getContentPane().replace(getContentPane().getComponentAt(0), dishes, CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, false, 300));
    }
    
    public Container createDishesContainer() {
        Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        cnt.setScrollableY(true);
       ArrayList<fournisseur> events; 
       	events= ServiceFour.getInstance().getAllEvent();
        // allows elements to slide into view
        for(fournisseur e: events) {
      
            Component dish = createDishComponent(e);
            cnt.addComponent(dish);    
              
         //  boxAndPrice.addComponent(BorderLayout.EAST, delete);
          // box.addComponent(delete);
        }


         
     
         
        return cnt;
    }
    
    private Container createDishComponent(fournisseur d) {
     	Label image=new Label();
          System.out.println(image.getText());
       // Image img = theme.getImage(image.getText());
                             EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(300, 300, 0xffff0000), true);

     //  URLImage img = URLImage.createToStorage(placeholder, d.getImage(), "");

        Container mb = new Container(new BorderLayout());
      /* mb.getUnselectedStyle().setBgImage(img);
        mb.getSelectedStyle().setBgImage(img);
        mb.getPressedStyle().setBgImage(img);
        mb.getUnselectedStyle().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        mb.getSelectedStyle().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        mb.getPressedStyle().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
      */  
        Container box = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Button title = new Button(d.getNom_fournisseur());
        title.setUIID("DishTitle");
        Label highlights = new Label(d.getAdresse_fournisseur());
        TextArea details = new TextArea(d.getDomaine_fournisseur());
        details.setUIID("DishBody");
        highlights.setUIID("DishBody");
        Label cat = new Label(d.getEmail_fournisseur());/////////////////////////caaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaattttttttttttttttttttttttttttttttttttttttttttttttttttttttt
        cat.setUIID("DishPrice");
        box.addComponent(title);
        box.addComponent(highlights);
        box.addComponent(cat);
        Container boxAndPrice = new Container(new BorderLayout());
        boxAndPrice.addComponent(BorderLayout.CENTER, box);
         Container box1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
       
        
       
        mb.addComponent(BorderLayout.SOUTH, boxAndPrice);
        
        mb.setLeadComponent(title);
       // mb.setLeadComponent(cat);
        title.addActionListener((e) -> {
            if(highlights.getParent() != null) {
                box.removeComponent(highlights);
                box.addComponent(details);
            } else {
                box.removeComponent(details);
                box.addComponent(highlights);
            }
            mb.getParent().animateLayout(300);
        });
        return mb;
    }
    
    
}
