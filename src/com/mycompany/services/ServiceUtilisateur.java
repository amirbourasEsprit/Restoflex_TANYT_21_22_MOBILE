/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.NewsfeedForm;
import com.mycompany.utils.Statics;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author boura
 */
public class ServiceUtilisateur {
    public static ServiceUtilisateur instance =null;
    public static boolean  resultOK=true;
    String json;
    
    private ConnectionRequest req;
    
    
    public static ServiceUtilisateur getInstance(){
    if(instance ==null)
        instance =new ServiceUtilisateur();
    return instance;
    }
    
    public ServiceUtilisateur(){
    req = new ConnectionRequest();
    }
    
    //signUp Employee
    public void signupEmp(TextField nom,TextField prenom,TextField cin,TextField password,TextField email,TextField numTel,TextField dateNaissance,TextField adresse,TextField posteEmploye, ComboBox <String> restaurants ){
       
        String url=Statics.BASE_URL+"/employee/signup?nom="+nom.getText().toString()+
        "&prenom="+prenom.getText().toString()+" &cin="+cin.getText().toString()+"&password="+password.getText().toString()+"&email="+email.getText().toString()+"&numTel="+numTel.getText().toString()+
         "&dateNaissance="+dateNaissance.getText().toString()+"&adresse="+adresse.getText().toString()+"&posteEmploye="+posteEmploye.getText().toString()+"&idRest=2";
        req.setUrl(url);
        if(nom.getText().equals("")||prenom.getText().equals("")||email.getText().equals("")||cin.getText().equals("")||dateNaissance.getText().equals("")||numTel.getText().equals("")
          ||posteEmploye.getText().equals(""))
        {
            Dialog.show("erreur","Veuillez remplir les champs","ok",null);
        }
        else{
        req.addResponseListener((e)->{
        byte[]data=(byte[]) e.getMetaData();
        String responseData=new String(data);
        
            System.out.println("data ===>"+responseData);
        }
        
        );
        NetworkManager.getInstance().addToQueueAndWait(req);
      }
    }
    //signUp Fournisseur
    public void signupFourn(TextField nom,TextField prenom,TextField cin,TextField password,TextField email,TextField numTel,TextField dateNaissance,TextField adresse, ComboBox <String> restaurants , ComboBox <String> fournisseurs ){
        String url=Statics.BASE_URL+"/fournisseur/signup?nom="+nom.getText().toString()+
        "&prenom="+prenom.getText().toString()+" &cin="+cin.getText().toString()+"&password="+password.getText().toString()+"&email="+email.getText().toString()+"&numTel="+numTel.getText().toString()+
         "&dateNaissance="+dateNaissance.getText().toString()+"&adresse="+adresse.getText().toString()+"&idRest=2&idFournisseur=1";
        req.setUrl(url);
        if(nom.getText().equals("")||prenom.getText().equals("")||email.getText().equals("")||cin.getText().equals("")||dateNaissance.getText().equals("")||numTel.getText().equals("")
                )
        {
            Dialog.show("erreur","Veuillez remplir les champs","ok",null);
        }
        else{
        req.addResponseListener((e)->{
        byte[]data=(byte[]) e.getMetaData();
        String responseData=new String(data);
        
            System.out.println("data ===>"+responseData);
        }
        
        );
        NetworkManager.getInstance().addToQueueAndWait(req);
      }
    }
    //signIn
    public void signin(TextField email,TextField password, Resources res){
        String url = Statics.BASE_URL+"/user/login?email="+email.getText().toString()+"&password="+password.getText().toString();
        req= new ConnectionRequest(url,false);
        req.setUrl(url);
        
        req.addResponseListener((e)->{
            JSONParser j = new JSONParser();
            String json= new String(req.getResponseData())+ "";
           try{
           if (email.getText().equals("")||password.getText().equals(""))
           { Dialog.show("erreur","Veuillez remplir les champs","ok",null); }
           else if (json.equals("password not found")){
            Dialog.show("Echec d'autentification","Email ou mot de passe éronné","OK",null);
            }
            
            else{
                System.out.println("data==>"+json);
                Map<String,Object> user =j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                
                if(user.size()>0)
                   new NewsfeedForm(res).show();
            }
           }catch(Exception ex){
           ex.printStackTrace();
           }
        } );
          NetworkManager.getInstance().addToQueueAndWait(req);
    }
    //methode get password
    public String getPasswordByEmail(String email,Resources rs){
    String url = Statics.BASE_URL+"/user/getPasswordbyMail?email="+email;
    req=new ConnectionRequest(url,false);
    req.setUrl(url);
    req.addResponseListener((e)->{
    JSONParser j =new JSONParser();
    json =new String(req.getResponseData())+"";
    try {
        System.err.println("data==>"+json);
        Map<String,Object> password=j.parseJSON(new CharArrayReader(json.toCharArray()));
        
    }catch(Exception ex){
    ex.printStackTrace();
    }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return json;
    }
}
