package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Conge;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Yosr Belaam
 */
public class ServiceConge {

    public int resultCode;
    public ArrayList<Conge> tasks;
    public ArrayList<Conge> Conges;
    public static ServiceConge instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceConge() {
        req = new ConnectionRequest();
    }

    public static ServiceConge getInstance() {
        if (instance == null) {
            instance = new ServiceConge();
        }
        return instance;
    }

    public boolean AjouterConge(Conge cong) {
        System.out.println(cong);

        String url = Statics.BASE_URL + "/addConge?dateDeb=" + cong.getDate_deb() + "&dateFin=" + cong.getDate_fin() + "&idUtilisateur=" + cong.getId_utilisateur() + "&idTypeConge=" + cong.getId_type_conge();

        req.setUrl(url);

        //  System.out.println(cong);
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

    public ArrayList<Conge> AfficherConge() {
        req.setUrl(Statics.BASE_URL + "/congedisplay");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    Conges = new ArrayList<>();
                    Map<String, Object> tasksListJson = new JSONParser().parseJSON(new CharArrayReader(
                            new String(req.getResponseData()).toCharArray()
                    ));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
                    System.out.println(tasksListJson);
                    
                    for (Map<String, Object> obj : list) {
                        Conge cong = new Conge(
                                 (String) obj.get("dateDeb"),
                                (String) obj.get("dateFin"),
                                (int) obj.get("idUtilisateur"),
                                (int) obj.get("idTypeConge")
                        );

                        Conges.add(cong);
                    }

                } catch (IOException ex) {
                    System.out.println("Conge vide");
                }

                req.removeResponseListener(this);
            }
        });
        try {
            NetworkManager.getInstance().addToQueueAndWait(req);
       } catch (Exception e) { }
        return Conges;
    }

    public boolean modifierConge(Conge cong, int id) {

        String url = Statics.BASE_URL + "updateConge?idConge" + cong.getId_conge() + "&dateDeb=" + cong.getDate_deb() + "&dateFin=" + cong.getDate_fin() + "&idTypeConge=" + cong.getId_type_conge();
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

    public int supprimerConge(Conge conge) {

        req.setUrl(Statics.BASE_URL + "deleteConge?idConge=" + conge.getId_conge());
        req.addArgument("id", String.valueOf(conge.getId_conge()));
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
