/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.FontImage;
import com.codename1.ui.Tabs;
import com.codename1.ui.layouts.BorderLayout;

/**
 *
 * @author bhk
 */
public class HomeForm extends Form{           
Form current;
public static Form accueilFrontForm;
    public HomeForm() {
        accueilFrontForm=this;
        current=this; //Back 
        setTitle("Home");
        setLayout(BoxLayout.y());
        
      
        
        
        
         Button btnAddReclamation = new Button("Add Reclamation");
         Button btnListReclamations= new Button("List reclamations");
         btnAddReclamation.addActionListener(e-> new AddReclamationForm(current).show());
         btnListReclamations.addActionListener(e-> new ListReclamationForm(current).show());
         
         
         
           
           
  
         
         
         
        addAll(btnAddReclamation,btnListReclamations);

   
       
      

        
        
    }

    
    
}
