/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;


import com.codename1.components.FloatingHint;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
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
import com.mycompany.entities.utilisateur;
import com.mycompany.services.ServiceUtilisateur;
import com.mycompany.utils.Validator;

/**
 *
 * @author ibeno
 */

 public class ForgetPassword extends BaseForm {
public utilisateur user;
 public String username;
    public ForgetPassword(Resources res) {
       
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("Activate");
        
        add(BorderLayout.NORTH, 
                BoxLayout.encloseY(
                        new Label(res.getImage("smily.png"), "LogoLabel"),
                        new Label("Awsome Thanks!", "LogoLabel")
                )
        );
          this.username = ActivateForm.username;
         Validator v = new Validator();
        TextField new_password = new TextField("", "Nouveau mot de passe", 20, TextField.PASSWORD);
        new_password.setSingleLineTextArea(false);
        
        TextField confirmer_password = new TextField("", "Confirmer mot de passe", 20, TextField.PASSWORD);
        new_password.setSingleLineTextArea(false);
        
        Button btnModifier = new Button("Modifier votre mot de passe");
      
        Label alreadHaveAnAccount = new Label("Already have an account?");
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("CenterLink");
        
        Container content = BoxLayout.encloseY(
                new FloatingHint(new_password),
                createLineSeparator(),
                new FloatingHint(confirmer_password),
                createLineSeparator(),
                new SpanLabel("Taper votre nouveau Mot de passe", "CenterLabel"),
                btnModifier,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        );
        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);
        btnModifier.requestFocus();
        btnModifier.addActionListener((e) -> {

            if (!v.test_Password(new_password.getText())) {
                Dialog.show("Attention", "Votre passe doit contenir majuscule un chiffre et une miniscule au minimum", new Command("OK"));
            } else if (!new_password.getText().equals(confirmer_password.getText())) {
                Dialog.show("Attention", "Les deux mot de passes ne correspondent pas", new Command("OK"));
            } else {

                //User u = new User(Integer.parseInt(tfStatus.getText()), tfName.getText());

                if (ServiceUtilisateur.getInstance().modifierUserMotDePasse(this.username,new_password.getText())) {
                    Dialog.show("Success", "Modification effectuée", new Command("OK"));
                    new SignInForm(res).show();
                } else {
                    Dialog.show("ERROR", "Server error", new Command("OK"));
                }
            }
        });
    }
}
