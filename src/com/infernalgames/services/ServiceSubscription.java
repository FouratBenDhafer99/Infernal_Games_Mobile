package com.infernalgames.services;

import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import com.infernalgames.entities.Subscription;
import com.infernalgames.entities.User;
import com.infernalgames.utils.Statics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 @author Fourat
 */
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
                    User user = new User();
                    user.setId((int) Float.parseFloat(map.get("id").toString()));
                    user.setName(map.get("name").toString());
                    user.setLastName(map.get("lastName").toString());
                    user.setEmail(map.get("email").toString());

                    Subscription sub= new Subscription();
                    sub.setId((int) Float.parseFloat(obj.get("id").toString()));
                    sub.setStatus(Boolean.parseBoolean(obj.get("status").toString()));
                    sub.setUser(user);

                    subscriptions.add(sub);
                }
            }else {
                Map<String, Object> map = (Map<String, Object>) subsListJson.get("user");
                User user = new User();
                user.setId((int) Float.parseFloat(map.get("id").toString()));
                user.setName(map.get("name").toString());
                user.setLastName(map.get("lastName").toString());
                user.setEmail(map.get("email").toString());

                Subscription sub= new Subscription();
                sub.setId((int) Float.parseFloat(subsListJson.get("id").toString()));
                sub.setStatus(Boolean.parseBoolean(subsListJson.get("status").toString()));
                sub.setUser(user);

                subscriptions.add(sub);
            }

        }catch (Exception e){

        }
        return subscriptions;
    }

    public ArrayList<Subscription> getAllSubs(){
        req= new ConnectionRequest();
        String url = Statics.BASE_URL+"/newslettersMobile/getAllSubs";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent networkEvent) {
                subscriptions= parseSubs(new String(req.getResponseData()));
                req.removeResponseCodeListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return subscriptions;
    }

    public boolean subscribe(User user){
        req= new ConnectionRequest();
        String url = Statics.BASE_URL+"/newslettersMobile/subscribeToNewsletter";
        req.setUrl(url);
        req.setPost(false);
        req.addArgument("userId", String.valueOf(user.getId()) );
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent networkEvent) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseCodeListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean unsubscribe(User user){
        req= new ConnectionRequest();
        String url = Statics.BASE_URL+"/newslettersMobile/unsubscribeToNewsletter";
        req.setUrl(url);
        req.setPost(false);
        req.addArgument("userId", String.valueOf(user.getId()) );
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent networkEvent) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseCodeListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

}
