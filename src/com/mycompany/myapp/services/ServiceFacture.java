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
import com.mycompany.myapp.entities.facture;
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
public class ServiceFacture {
    
    public static ServiceFacture instance = null;
    private ConnectionRequest req;
    public static boolean  resultOK=true;
    ArrayList<facture> listF;
    public static ServiceFacture getInstance(){
        if(instance ==null)
        instance =new ServiceFacture();
       return instance; 
    }
    
    public ServiceFacture(){
    req = new ConnectionRequest();
    }
    ///////////////Ajout
    public void ajouterFacture(facture f){
        
        String url=Statics.BASE_URL+"/addFactJ?total="+f.getTotal()+"&id_fournisseur=1"+"&id_rest=2";
          
        req.setUrl(url);
        req.addResponseListener((e)->{
     
        String str=new String(req.getResponseData());
        
            System.out.println("Data**"+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
      }
    
    
    //////////Modification
    public boolean modifierFacture(facture f, long numF) {
         String url = Statics.BASE_URL +"/updateFactJ/"+numF+"?statut="+f.getStatut();
        req.setUrl(url);
        System.out.println(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOK;
    }
    
    
    ///////////Suppression
    public boolean deleteFacture(long numF ) {
        String url = Statics.BASE_URL +"/deleteFactJ/"+numF;
        
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
    public ArrayList<facture> afficherFacture(){
   req.setUrl(Statics.BASE_URL + "/displayFactJson");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                     listF = new ArrayList<>();
                    Map<String, Object> FactListJson = new JSONParser().parseJSON(new CharArrayReader(
                            new String(req.getResponseData()).toCharArray()
                    ));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) FactListJson.get("root");
                            System.out.println(FactListJson);
                    for (Map<String, Object> obj : list) {
                        facture f;
                        f = new facture(
                                (long) Float.parseFloat(obj.get("numFacture").toString()),
                                (String) obj.get("dateFacture"),
                                Float.parseFloat(obj.get("total").toString()),
                                (String) obj.get("statut")
                        );
                        
                        listF.add(f);
                    }

                } catch (IOException ex) {
                    System.out.println("facture vide");
                }

                req.removeResponseListener(this);
            }
        });
        try {
            NetworkManager.getInstance().addToQueueAndWait(req);
        } catch (Exception e) {

        }
        return listF;
}
    
 
    
}
                
    
     


