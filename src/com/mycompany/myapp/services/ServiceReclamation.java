package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author bhk
 */
public class ServiceReclamation {
    public int resultCode;
  public ArrayList<Reclamation> tasks;
   public ArrayList<Reclamation> Reclamations; 
    //singleton
    public static ServiceReclamation instance=null;
        public boolean resultOK;

    
        public static boolean resultOk = true;

     
    //initilisation connection request 
    private ConnectionRequest req;
    
    public static ServiceReclamation getInstance() {
        if (instance == null)
            instance = new ServiceReclamation();
            return instance;
        }
        
    public ServiceReclamation() {
          req = new ConnectionRequest();  
    }
    
   public boolean ajoutReclamation(Reclamation rec){
        System.out.println(rec);
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
   
   
   
   //set??????????
   
    public ArrayList<Reclamation>affichageReclamations() {
        req.setUrl(Statics.BASE_URL + "/AfficherReclamation/reclamation");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    Reclamations = new ArrayList<>();
                    Map<String, Object> tasksListJson = new JSONParser().parseJSON(new CharArrayReader(
                            new String(req.getResponseData()).toCharArray()
                    ));
                    System.out.println(tasksListJson);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
                    for(Map<String, Object> obj : list) {
                        Reclamation rec;
                        rec= new Reclamation(
                                
                        
                      //  (int) Float.parseFloat(obj.get("numReclamation").toString()),
                        (String) obj.get("destinataire"),
                        (String) obj.get("description"),
                        (String) obj.get("StatutReclamation"),
                        (String) obj.get("dateReclamation")
                       // (int)Float.parseFloat(obj.get("idTypeReclamation").toString()),
                       
                        //(int)Float.parseFloat(obj.get("id").toString())
                        );
                        Reclamations.add(rec);       
                    }
                     

                } catch (IOException ex) {
                    System.out.println("Reclamation vide");
                }

                req.removeResponseListener(this);
            }
        });
        try {
            NetworkManager.getInstance().addToQueueAndWait(req);
        } catch (Exception e) {

        }
        return Reclamations;
    }
 
  
 public int SupprimerReclamation(Reclamation reclamation) {
        req.setUrl(Statics.BASE_URL +"/SupprimerRecJson");
        req.addArgument("numReclamation", String.valueOf(reclamation.getNum_reclamation()));

        
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultCode = req.getResponseCode();
                req.removeResponseListener(this);

            }
        });
        try {
            NetworkManager.getInstance().addToQueueAndWait(req);
        } catch (Exception e) {

        }
        return resultCode;
    }
   
}


