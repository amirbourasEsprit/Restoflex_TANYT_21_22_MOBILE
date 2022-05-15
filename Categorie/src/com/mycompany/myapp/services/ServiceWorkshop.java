package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Workshop;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author bhk
 */
public class ServiceWorkshop {
public int resultCode;
  public ArrayList<Workshop> tasks;
   public ArrayList<Workshop> Workshops; 
    public static ServiceWorkshop instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceWorkshop() {
         req = new ConnectionRequest();
    }

    public static ServiceWorkshop getInstance() {
        if (instance == null) {
            instance = new ServiceWorkshop();
        }
        return instance;
    }

    public boolean addEvent(Workshop e ) {
        System.out.println(e);
        System.out.println("********");
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = Statics.BASE_URL + "/workshop/workshop/add3?Club="+e.getClub()+"titre=" + e.getTitre()+ "&description=" + e.getDescription() + "&lieu=" +e.getLieu() +"&date=" +e.getDate()+"&file="+e.getFile();
    
       req.setUrl(url);
       
      
      System.out.println(e);
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
    
    
    public ArrayList<Workshop>  getAllWorkshops() {
        req.setUrl(Statics.BASE_URL + "/workshop/workshop/liste2");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                   Workshops = new ArrayList<>();
                    Map<String, Object> tasksListJson = new JSONParser().parseJSON(new CharArrayReader(
                            new String(req.getResponseData()).toCharArray()
                    ));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

                    for (Map<String, Object> obj : list) {
                        Workshop E;
                        E = new Workshop(
                                (int) Float.parseFloat(obj.get("id").toString()),
                                 (String) obj.get("club_id"),
                                (String) obj.get("titre"),
                                (String) obj.get("date"),
                                
                                (String) obj.get("description"),
                                (String) obj.get("lieu"),
                                        
                                                (String) obj.get("file")
                                
                                //  Float.parseFloat(obj.get("pourcentage_like").toString())
                        );
                        
                      Workshops.add(E);
                    }

                } catch (IOException ex) {
                    System.out.println("workshop vide");
                }

                req.removeResponseListener(this);
            }
        });
        try {
            NetworkManager.getInstance().addToQueueAndWait(req);
        } catch (Exception e) {

        }
        return Workshops;
    }

public boolean modifevent(Workshop e,int id ) {

             String url = Statics.BASE_URL + "/workshop/workshop/updateworkshop/"+id+"?titre=" + e.getTitre()+ "&description=" + e.getDescription() + "&lieu=" +e.getLieu() +"&date=" +e.getDate()+"&picture="+e.getFile()+"&Categorie="+e.getClub();
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
 
    
    
    
    
    
    
public int supprimerEvent(Workshop Event) {
    req.setUrl(Statics.BASE_URL + "/workshop/workshop/deleteworkshop");
        req.addArgument("id", String.valueOf(Event.getId()));
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

    public List<String> fillintot() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
    
}













