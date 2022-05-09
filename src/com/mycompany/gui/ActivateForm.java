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
import static java.lang.String.valueOf;
import com.mycompany.services.SendMail;
import java.util.Random;


/**
 * Account activation UI
 *
 * @author Shai Almog
 */
public class ActivateForm extends BaseForm {
            Random random = new Random();
  public static String username;
    public static String verificationCode;
    public ActivateForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("IMGLogin");
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
        TextField email =new TextField("","sasir votre email",20,TextField.ANY);
        email.setSingleLineTextArea(false);
        
        Button verifier =new Button("Valider");
        Label haveAnAccount =new Label("Vous voulez de se connecter");
        Button signIn = new Button("Connecter");
        signIn.addActionListener(e->previous.showBack());
        signIn.setUIID("CenterLink");
        
        Container content=BoxLayout.encloseY(
       // new Label(res.getImage("oublier.png"),"CenterLabel"),
                new FloatingHint(email),
                createLineSeparator(),
                verifier,
                FlowLayout.encloseCenter(haveAnAccount),
                signIn
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER,content);
        verifier.requestFocus();
        verifier.addActionListener((e)->
        {
             if ( email.getText().length() == 0 || "false".equals(ServiceUtilisateur.getInstance().forgetPasswordCheck(email.getText()))) {
                Dialog.show("Attention", "Veuillez verifier vos parametres ", new Command("OK"));
            } else {
                String verificationCode = valueOf(random.nextInt()).substring(1, 6);
                this.verificationCode=verificationCode;
                this.username=email.getText();

                SendMail sendMail = new SendMail();
                try {
                    sendMail.send(email.getText(), "Code de verification", "Voice votre code de verification "+verificationCode);
                } catch (Exception ex) {
                    ex.printStackTrace();

                }
                Dialog.show("Code de Verification", "Veuillez verifier votre courrier", new Command("OK"));

                new VerificationCodeForgetPassword(res).show();
            }

        });


    }
  
    
}
