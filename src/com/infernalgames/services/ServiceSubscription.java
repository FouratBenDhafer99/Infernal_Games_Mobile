package com.infernalgames.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.infernalgames.entities.Subscription;
import com.infernalgames.entities.Utilisateur;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServiceSubscription {

    public ArrayList<Subscription> subscriptions;
    public static ServiceSubscription instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceSubscription(){
        req= new ConnectionRequest();
    }

    public static ServiceSubscription getInstance(){
        if(instance== null){
            instance= new ServiceSubscription();
        }
        return instance;
    }

    public ArrayList<Subscription> parseSubs(String jsonText){
        try {
            subscriptions= new ArrayList<>();
            JSONParser json= new JSONParser();
            Map<String, Object> subsListJson=
                    json.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            if(subsListJson.get("root")!=null){
                List<Map<String, Object>> list = (List<Map<String, Object>>) subsListJson.get("root");

                for (Map<String, Object> obj : list) {
                    Map<String, Object> map = (Map<String, Object>) obj.get("user");
                    Utilisateur user = new Utilisateur();
                    user.setId((int) Float.parseFloat(map.get("id").toString()));
                    user.setNom(map.get("name").toString());
                    user.setPrenom(map.get("lastName").toString());
                    user.setEmail(map.get("email").toString());

                    Subscription sub= new Subscription();
                    sub.setId((int) Float.parseFloat(obj.get("id").toString()));
                    sub.setStatus(Boolean.parseBoolean(obj.get("sent").toString()));
                    sub.setUser(user);

                    subscriptions.add(sub);
                }
            }else {

            }

        }catch (Exception e){

        }
    }


}
