package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.TypeConge;
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
public class ServiceTypeConge {

    public int resultCode;
    public ArrayList<TypeConge> tasks;
    public ArrayList<TypeConge> TypesConges;
    public static ServiceTypeConge instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceTypeConge() {
        req = new ConnectionRequest();
    }

    public static ServiceTypeConge getInstance() {
        if (instance == null) {
            instance = new ServiceTypeConge();
        }
        return instance;
    }

    public boolean AjouterTypeConge(TypeConge Tcong) {
        System.out.println(Tcong);

        String url = Statics.BASE_URL + "/addTypeConge?nomTypeConge=" + Tcong.getNom_type_conge();

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

    public ArrayList<TypeConge> getAllConges() {
        String url = Statics.BASE_URL + "/typecongedisplay";

        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    TypesConges = new ArrayList<>();
                    Map<String, Object> tasksListJson = new JSONParser().parseJSON(new CharArrayReader(
                            new String(req.getResponseData()).toCharArray()
                    ));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
               //     System.out.println(list);
                    for (Map<String, Object> obj : list) {
                        TypeConge Tc;
                        Tc = new TypeConge(
                                (int)(double)(obj.get("idTypeConge")),
                                (String) obj.get("nomTypeConge")
                        );

                        TypesConges.add(Tc);
                    }

                } catch (IOException ex) {
                    System.out.println("Type Conge vide");
                }

                req.removeResponseListener(this);
            }
        });
        try {
            NetworkManager.getInstance().addToQueueAndWait(req);
        } catch (Exception e) {

        }
        return TypesConges;
    }

    public boolean modifierTypeConge(TypeConge Tcong) {

        String url = Statics.BASE_URL + "/updateTypeConge?idTypeConge" + Tcong.getId_type_conge();
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

    public int supprimerTypeConge(int id) {

        req.setUrl(Statics.BASE_URL + "/deleteTypeConge?idTypeConge=" + id);
        System.out.println(id);
     //   req.addArgument("id", String.valueOf(Tconge.getId_type_conge()));
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
