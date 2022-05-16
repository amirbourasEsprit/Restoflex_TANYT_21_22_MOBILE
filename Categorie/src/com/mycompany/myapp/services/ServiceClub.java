package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Club;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author bhk
 */
public class ServiceClub {
public int resultCode;
  public ArrayList<Club> tasks;
   public ArrayList<Club> Categories; 
    public static ServiceClub instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceClub() {
         req = new ConnectionRequest();
    }

    public static ServiceClub getInstance() {
        if (instance == null) {
            instance = new ServiceClub();
        }
        return instance;
    }

    public boolean addCategorie(Club c) {
        System.out.println(c);
        System.out.println("********");
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = Statics.BASE_URL + "/club/club/add3?nom_club=" + c.getNom_club()+ "&description=" + c.getDescription() ;
    
       req.setUrl(url);
       
      
      System.out.println(c);
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
    
    
    public ArrayList<Club>  getAllCategories() {
        req.setUrl(Statics.BASE_URL + "/club/club/liste2");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    Categories = new ArrayList<>();
                    Map<String, Object> tasksListJson = new JSONParser().parseJSON(new CharArrayReader(
                            new String(req.getResponseData()).toCharArray()
                    ));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

                    for (Map<String, Object> obj : list) {
                        Club Categorie;
                        Categorie = new Club(
                                (int) Float.parseFloat(obj.get("id").toString()),


                                (String) obj.get("name"),
                                (String) obj.get("description")
                                
                                //  Float.parseFloat(obj.get("pourcentage_like").toString())
                        );
                        
                        Categories.add(Categorie);
                    }

                } catch (IOException ex) {
                    System.out.println("categorie vide");
                }

                req.removeResponseListener(this);
            }
        });
        try {
            NetworkManager.getInstance().addToQueueAndWait(req);
        } catch (Exception e) {

        }
        return Categories;
    }  
public boolean modifcategorie(Club c,int id ) {

          String url = Statics.BASE_URL + "club/club/updateclub/"+id+"?nom_club=" + c.getNom_club()+"&description=" + c.getDescription();
     //        req.setUrl(url);
        
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
 
    
    
    
    
    
    
public int supprimerCategorie(Club Categorie) {
    req.setUrl(Statics.BASE_URL + "/club/club/deleteclub");
        req.addArgument("id", String.valueOf(Categorie.getId()));
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