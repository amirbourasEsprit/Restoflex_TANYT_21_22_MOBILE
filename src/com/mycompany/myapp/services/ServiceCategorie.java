package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author bhk
 */
public class ServiceCategorie {
public int resultCode;
  public ArrayList<Categorie> tasks;
   public ArrayList<Categorie> Categories; 
    public static ServiceCategorie instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceCategorie() {
         req = new ConnectionRequest();
    }

    public static ServiceCategorie getInstance() {
        if (instance == null) {
            instance = new ServiceCategorie();
        }
        return instance;
    }

    public boolean addCategorie(Categorie c) {
        System.out.println(c);
        System.out.println("********");
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = Statics.BASE_URL + "/categorie/categ/add3?nomCategorie=" + c.getNomCategorie();
    
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
    
    
    public ArrayList<Categorie>  getAllCategories() {
        req.setUrl(Statics.BASE_URL + "/categorie/categ/liste2");
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
                        Categorie Categorie;
                        Categorie = new Categorie(
                                (int) Float.parseFloat(obj.get("idCategorie").toString()),


                                (String) obj.get("nomCategorie")
                            
                                
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

 
    
    
 
    
    
    
    
    
  
    
public boolean modifcategorie(Categorie c,int idCategorie ) {

             String url = Statics.BASE_URL + "/categorie/club/updateclub/"+idCategorie+"?nomCategorie=" + c.getNomCategorie();
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
 
    
    
    
    
    
    
public int supprimerCategorie(Categorie Categorie) {
    req.setUrl(Statics.BASE_URL + "/categorie/club/deleteclub");
        req.addArgument("idCategorie", String.valueOf(Categorie.getId()));
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