/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.NewsfeedForm;
import com.mycompany.gui.SessionManager;
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
     public String loginResult;
    
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
                 float id = Float.parseFloat(user.get("id").toString());
                SessionManager.setIdUtilisateur((int)id);//jibt id ta3 user ly3ml login w sajltha fi session ta3i
                SessionManager.setNomUtilisateur(user.get("nom").toString());
                SessionManager.setPrenomUtilisateur(user.get("prenom").toString());
                SessionManager.setPassowrdUtilisateur(user.get("password").toString());
                SessionManager.setNumlUtilisateur(user.get("numTel").toString());
                SessionManager.settAdresseUtilisateur(user.get("adresse").toString());
                
                if(user.size()>0)
                   new NewsfeedForm(res).show();
            }
           }catch(Exception ex){
           ex.printStackTrace();
           }
        } );
          NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
     public boolean modifierUserMotDePasse(String email,String password) {

        String url = Statics.BASE_URL + "/modifyPasswordUser?email=" + email + "&password=" + password;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
     
     public String forgetPasswordCheck(String email) {
        String url = Statics.BASE_URL + "/user/getPasswordbyMail?email=" + email ;
        req=new ConnectionRequest(url,false);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                loginResult = new String(req.getResponseData());
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return loginResult;
    }
     //edite profile utilisateur 
     public static void EditUtilisateur(String nom,String prenom,String password,String numTel,String adresse) {
     String url="";
     MultipartRequest req =new MultipartRequest();
     req.setUrl(url);
     req.addArgument("id",String.valueOf(SessionManager.getIdUtilisateur()));
     req.addArgument("nom",nom);
     req.addArgument("prenom",prenom);
     req.addArgument("password",password);
     req.addArgument("numTel",numTel);
     req.addArgument("adresse",adresse);
     req.addResponseListener((response)->{
         byte[] data =(byte[]) response.getMetaData();
         String s=new String(data);
         System.out.println(s);
         if(s.equals("Account updated")){
         }else{
         Dialog.show("Erreur","Echec de modification","ok",null);
         }
     });     
     NetworkManager.getInstance().addToQueueAndWait(req);
     }
     
}
