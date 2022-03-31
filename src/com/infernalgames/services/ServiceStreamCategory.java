package com.infernalgames.services;

import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import com.infernalgames.entities.StreamCategory;
import com.infernalgames.utils.Statics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServiceStreamCategory {
    public ArrayList<StreamCategory> categories;
    public static ServiceStreamCategory instance=null;
    public boolean resultOk;
    private ConnectionRequest req;

    private ServiceStreamCategory(){ req= new ConnectionRequest(); }

    public static ServiceStreamCategory getInstance(){
        if(instance== null){
            instance= new ServiceStreamCategory();
        }
        return instance;
    }


    public ArrayList<StreamCategory> parseCategories(String jsonText){
        try {
            categories= new ArrayList<>();
            JSONParser json=new JSONParser();
            Map<String, Object> categoryListJson=
                    json.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            if ( categoryListJson.get("root") != null ){
                List<Map<String, Object>> list = (List<Map<String, Object>>) categoryListJson.get("root");

                for (Map<String, Object> obj : list) {
                    StreamCategory category= new StreamCategory();
                    category.setId((int) Float.parseFloat(obj.get("id").toString()));
                    category.setLabel(obj.get("label").toString());
                    categories.add(category);
                }
            }else {
                StreamCategory category= new StreamCategory();
                category.setId((int) Float.parseFloat(categoryListJson.get("id").toString()));
                category.setLabel(categoryListJson.get("label").toString());
                categories.add(category);
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return categories;
    }


    public ArrayList<StreamCategory> getAllStreamCategory(){
        req= new ConnectionRequest();
        String url = Statics.BASE_URL+"/streamsMobile/getAllStreamCategory";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent networkEvent) {
                categories= parseCategories(new String(req.getResponseData()));
                req.removeResponseCodeListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return categories;
    }

    public boolean addCategory(StreamCategory category){
        req= new ConnectionRequest();
        String url = Statics.BASE_URL+"/streamsMobile/addStreamCategory";
        req.setUrl(url);
        req.setPost(false);
        req.addArgument("label", category.getLabel());
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }

    public boolean editCategory(StreamCategory category){
        req= new ConnectionRequest();
        String url = Statics.BASE_URL+"/streamsMobile/editStreamCategory";
        req.setUrl(url);
        req.setPost(false);
        req.addArgumentArray("id", category.getId()+"");
        req.addArgument("label", category.getLabel());
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }

    public boolean deleteCategory(StreamCategory category){
        req= new ConnectionRequest();
        String url = Statics.BASE_URL+"/streamsMobile/deleteStreamCategory";
        req.setUrl(url);
        req.setPost(false);
        req.addArgumentArray("id", category.getId()+"");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }

}
