/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infernalgames.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.events.ActionListener;
import com.esprit.utils.DataSource;
import com.infernalgames.entities.Ad;
import com.infernalgames.utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author eya dhaouadi
 */
public class ServiceAd {
   
        private ConnectionRequest request;
    private boolean responseResult;
    public ArrayList<Ad> services;

    public ServiceAd() {
            request = DataSource.getInstance().getRequest();

    }
       public boolean addAd(Ad ad) {
     
        String url = Statics.BASE_URL + "/api/addAd?description=" + ad.getDescription()+ "&nom=" + ad.getNom()+"&image="+ad.getImage()+"&type="+ad.getType();
        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>(){
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }

    
        public ArrayList<Ad> getAllService() {
        String url = Statics.BASE_URL + "/api/allAds";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    services = parseType(new String(request.getResponseData()));
                    request.removeResponseListener(this);
                } catch (ParseException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return services;
    }
        
          public ArrayList<Ad> parseType(String jsonText) throws ParseException {
        try {
            services = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
             
            int id = (int)Float.parseFloat(obj.get("id").toString());
            String nom = obj.get("nom").toString();
            String Description = obj.get("description").toString();
            String type = obj.get("type").toString();
            String image = obj.get("image").toString();


            

           
                  
                services.add(new Ad(id, Description, nom, image, type));
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return services;
    }

       public boolean ModifierAds(Ad ad) {
     
        String url = Statics.BASE_URL + "/api/updateAd??description=" + ad.getDescription()+ "&nom=" + ad.getNom()+"&image="+ad.getImage()+"&type="+ad.getType()+"&id="+ad.getId();
        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }
          public boolean DeleteAd(int id) {
     
        String url = Statics.BASE_URL + "/api/deleteAd/"+id;

        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }
    
    
    
}
