package  com.mycompany.services;

import com.codename1.components.ToastBar;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.codename1.util.Base64;
import com.mycompany.entities.Categorie;
  

import com.mycompany.entities.fournisseur;
 
import  com.mycompany.utils.Statics;
 
import com.mycompany.utils.Toaster;


public class ServiceFour {
    ToastBar.Status popup;
	public ArrayList<fournisseur> forn;
	public ConnectionRequest req;
	//public ArrayList<Images> limg;
	private  static ServiceFour instance;
	public boolean resultOK;
	private ServiceFour() {
		req=new ConnectionRequest();
	}
	public static ServiceFour getInstance() {
		if(instance==null)
			instance=new ServiceFour();
		return instance;
	}
        public int tailleevent(){
            return getAllEvent().size();
            
        }
	
	public ArrayList<fournisseur> getAllEvent(){
		String url =Statics.BASE_URL1+"fournisseurdisplay";
		req.setUrl(url);
		req.setPost(false);
		//req.addArgument("nom", e.getNom());
		//req.addArgument("lieu", e.getLieu());
		//req.addArgument("description", e.getDescription());
		
		req.addResponseListener(new ActionListener<NetworkEvent>()
				{
			@Override
            public void actionPerformed(NetworkEvent evt) {
    			forn = parseEvent(new String(req.getResponseData()));

                req.removeResponseListener(this);
            }
		});
		NetworkManager.getInstance().addToQueueAndWait(req);
	
	return forn;
	}
private ArrayList<fournisseur> parseEvent(String jsonText) {
	
		
 forn = new ArrayList<>();
JSONParser j =new JSONParser();


try {
	Map<String ,Object> EvntListJdon =j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
List<Map<String ,Object>> list= (List<Map<String, Object>>) EvntListJdon.get("root");
for(Map<String ,Object> obj :list) {
	
	 
        fournisseur f= new fournisseur();
   float id= Float.parseFloat(obj.get("idFournisseur").toString());
    String nom=obj.get("nomFournisseur").toString();
            String mat=obj.get("matriculeFiscale").toString();
            
                      String domaine=obj.get("domaineFournisseur").toString();
                     String num=obj.get("numTelFournisseur").toString();
                               String email=obj.get("emailFournisseur").toString();
                                       String adress=obj.get("adresseFournisseur").toString();
                                     //   String logo=obj.get("logo").toString();
                                      f.setId_fournisseur((int)id);
                                       f.setAdresse_fournisseur(adress);
                                       f.setNom_fournisseur(nom);
                                       f.setDomaine_fournisseur(domaine);
                                       f.setNum_tel_fournisseur(num);
                                       f.setMatricule_fiscale(mat);
                                       f.setEmail_fournisseur(email);
                                      // f.setLogo(logo);
                                       forn.add(f);
 
}

} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
return forn;
	}
 	
public boolean addevent(fournisseur t) {
         //   String url = Statics.BASE_URL+"/updateProduitMobile?nom=" + t.getNom()+"&description="+t.getDescription()+"&prix="+t.getPrix()+"&stock="+t.getStock()+"&id="+t.getId(); //création de l'URL

	       String url = Statics.BASE_URL1 +"addFRS?nomFournisseur="+t.getNom_fournisseur()+"&matriculeFiscale="+t.getMatricule_fiscale()+"&domaineFournisseur="+ t.getDomaine_fournisseur()+"&numTelFournisseur="+t.getNum_tel_fournisseur()+"&emailFournisseur="+t.getEmail_fournisseur()+"&adresseFournisseur="+t.getAdresse_fournisseur();
	       req.setUrl(url);
	       // req.setPost(false);
	     /*  req.addArgument("nomFournisseur", t.getNom_fournisseur()+"");
               System.out.print(t.getNom_fournisseur());
	       req.addArgument("domaineFournisseur", t.getDomaine_fournisseur()+"");
	       req.addArgument("matriculeFiscale", t.getMatricule_fiscale()+"");
               req.addArgument("numTelFournisseur", t.getNum_tel_fournisseur()+"");
               req.addArgument("emailFournisseur" ,t.getEmail_fournisseur()+"");*/
	       req.addResponseListener(new ActionListener<NetworkEvent>() {
	            @Override
	            public void actionPerformed(NetworkEvent evt) {
	                resultOK = req.getResponseCode() == 200; 
	                req.removeResponseListener(this);
	                System.out.println("ajouterservice1");
	            }
	        });
	        NetworkManager.getInstance().addToQueueAndWait(req);
	        return resultOK;
	     
	    }
 public boolean delete(int id) {

        popup = Toaster.showLoading();
        String url = Statics.BASE_URL1 +"delete/"+id;
        ConnectionRequest request = new ConnectionRequest(url);
       
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = request.getResponseCode() == 200;
                popup.clear();
                request.removeResponseListener((ActionListener<NetworkEvent>) this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(request);
        return resultOK;
    }
  public boolean modifierEvent(fournisseur t,int id) {
         	       String url = Statics.BASE_URL1 +"update/"+id+"?nomFournisseur="+t.getNom_fournisseur()+"&matriculeFiscale="+t.getMatricule_fiscale()+"&domaineFournisseur="+ t.getDomaine_fournisseur()+"&numTelFournisseur="+t.getNum_tel_fournisseur()+"&emailFournisseur="+t.getEmail_fournisseur()+"&adresseFournisseur="+t.getAdresse_fournisseur();

         
	       req.setUrl(url);
	        
            
	       req.addResponseListener(new ActionListener<NetworkEvent>() {
	            @Override
	            public void actionPerformed(NetworkEvent evt) {
	                resultOK = req.getResponseCode() == 200;  
	                req.removeResponseListener(this);
	                System.out.println("ajouterservice1");
	            }
	        });
	        NetworkManager.getInstance().addToQueueAndWait(req);
	        return resultOK;
    }
  
  
   public void sendSms() {
     String accountSID = "AC3777ce6363cc60a58f834d642ed7c8fe";
String authToken = "15f2a16479748344cc2de86463c8c2d6";
String fromPhone = "+15076056976";

       


 Response<Map> smsresult = Rest.post("https://api.twilio.com/2010-04-01/Accounts/" + accountSID + "/Messages.json").
                        queryParam("To", "+21658752219").
                        queryParam("From", fromPhone).
                        queryParam("Body", "Bienvenue a Restoflex "
                                + ". Commande effectuée,merci!").
                        header("Authorization", "Basic " + Base64.encodeNoNewline((accountSID + ":" + authToken).getBytes())).
                        getAsJsonMap();;



}
     
	
}
