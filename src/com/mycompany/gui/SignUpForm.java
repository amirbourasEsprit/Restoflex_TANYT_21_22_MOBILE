/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.services.ServiceUtilisateur;
import java.util.Vector;

/**
 * Signup UI
 *
 * @author Shai Almog
 */
public class SignUpForm extends BaseForm {

    public SignUpForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
                
        TextField nom = new TextField("", "Nom", 20, TextField.ANY);
        TextField prenom = new TextField("", "Prénom", 20, TextField.ANY);
        TextField cin = new TextField("", "Cin", 20, TextField.ANY);
        TextField email = new TextField("", "E-Mail", 20, TextField.EMAILADDR);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        TextField numTel = new TextField("", "numéro téléphone", 20, TextField.ANY);
        TextField dateNaissance = new TextField("", "Date de naissance", 20, TextField.ANY);
        TextField adresse = new TextField("", "Adresse", 20, TextField.ANY);
        TextField posteEmploye = new TextField("", "Poste Employée", 20, TextField.ANY);
        
         Vector <String> VectorRestaurant;
        
        VectorRestaurant=new Vector();
        
        VectorRestaurant.add("baila");
        VectorRestaurant.add("cozy");
        ComboBox<String>restaurants=new ComboBox<>(VectorRestaurant);
        
        nom.setSingleLineTextArea(false);
        prenom.setSingleLineTextArea(false);
        cin.setSingleLineTextArea(false);
        email.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        numTel.setSingleLineTextArea(false);
        dateNaissance.setSingleLineTextArea(false);
        adresse.setSingleLineTextArea(false);
        posteEmploye.setSingleLineTextArea(false);
        Button next = new Button("Créer un compte");
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Already have an account?");
        
        Container content = BoxLayout.encloseY(
                new Label("Créer votre compte", "LogoLabel"),
                new FloatingHint(nom),
                createLineSeparator(),
                 new FloatingHint(prenom),
                createLineSeparator(),
                new FloatingHint(cin),
                createLineSeparator(),
                new FloatingHint(email),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                 new FloatingHint(numTel),
                createLineSeparator(),
                 new FloatingHint(dateNaissance),
                createLineSeparator(),
                 new FloatingHint(adresse),
                createLineSeparator(),
                 new FloatingHint(posteEmploye),
                createLineSeparator(),
                restaurants
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
        next.requestFocus();
        next.addActionListener((e) -> {
            ServiceUtilisateur.getInstance().signupEmp(nom, prenom, cin, password, email, numTel, dateNaissance, adresse, posteEmploye, restaurants);
            Dialog.show("Success","Compte créez avec succées!","ok",null);
            new SignInForm(res).show();
        });
    }
    
}
