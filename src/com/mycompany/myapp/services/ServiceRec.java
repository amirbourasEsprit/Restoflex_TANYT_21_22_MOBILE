/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Nesrine
 */
public class ServiceRec {
    
    public static ServiceRec instance = null;
    private ConnectionRequest req;
    public static boolean  resultOK=true;
    ArrayList<Reclamation> listRec;
    public static ServiceRec getInstance(){
        if(instance ==null)
        instance =new ServiceRec();
       return instance; 
    }
    
    public ServiceRec(){
    req = new ConnectionRequest();
    }
    ///////////////Ajout
    public boolean ajouterReclamation(Reclamation rec){
        
        System.out.println("********");
       String url =Statics.BASE_URL+"/AjouterRecJson?destinataire="+rec.getDestinataire()+"&description="+rec.getDescription()+"&StatutReclamation="+rec.getStatut_reclamation()+"&date=now&idTypeReclamation=1&id=4";
     System.out.println(url);
       req.setUrl(url);
        System.out.println(rec);
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
   

    
    ///////////Suppression
    public boolean deleteReclamation(long numReclamation ) {
        String url = Statics.BASE_URL +"/SupprimerRecJson"+numReclamation;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOK;
    }
    
    
    ///////////Affichage
    public ArrayList<Reclamation> AfficherReclamation(){
  req.setUrl(Statics.BASE_URL + "/AfficheRecJson");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                     listRec = new ArrayList<>();
                    Map<String, Object> FactListJson = new JSONParser().parseJSON(new CharArrayReader(
                            new String(req.getResponseData()).toCharArray()
                    ));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) FactListJson.get("root");
                            System.out.println(list);
                    for (Map<String, Object> obj : list) {
                        Reclamation rec;
                        rec = new Reclamation(
                                 (String) obj.get("destinataire").toString(),
                        (String) obj.get("description").toString(),
                        (String) obj.get("statutReclamation").toString(),
                        (String) obj.get("dateReclamation").toString()
                               //(long) Float.parseFloat(obj.get("numReclamation").toString()),
                       
                        );
                        
                        listRec.add(rec);
                    }

                } catch (IOException ex) {
                    System.out.println("reclamation vide");
                }

                req.removeResponseListener(this);
            }
        });
        try {
            NetworkManager.getInstance().addToQueueAndWait(req);
        } catch (Exception e) {

        }
        return listRec;
}
    
 
 
    
}
                
    
     


