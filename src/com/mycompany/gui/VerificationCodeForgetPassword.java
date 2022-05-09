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


/**
 *
 * @author SeifBS
 */

   
public class VerificationCodeForgetPassword extends BaseForm {

    public int tentative=0;

    public VerificationCodeForgetPassword(Resources res) {
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
        
        TextField verificationCode = new TextField("", "Code de verification", 20, TextField.ANY);
        verificationCode.setSingleLineTextArea(false);
        
        Button verifier = new Button("verifier");
      
       
        Label alreadHaveAnAccount = new Label("Already have an account?");
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("CenterLink");
        
        Container content = BoxLayout.encloseY(
                new FloatingHint(verificationCode),
                createLineSeparator(),
                new SpanLabel("Taper Le code Envoyer", "CenterLabel"),
                
                verifier,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        );
        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);
        verifier.requestFocus();
         verifier.addActionListener((e) -> {
            

            if (!verificationCode.getText().equals(ActivateForm.verificationCode)) {
                tentative++;
                Dialog.show("Attention", "Code incorrect", new Command("OK"));
                
                if(tentative>2)
                {
                Dialog.show("Erreur", "Vous avez atteint toutes les tentatives ", new Command("OK"));
                new SignInForm(res).show();
                }

            } 
            
            else {
                Dialog.show("Succees", "Code correct", new Command("OK"));
                new ForgetPassword(res).show();
            }

        });
    }
    
}