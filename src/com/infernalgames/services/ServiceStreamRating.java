package com.infernalgames.services;

import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import com.infernalgames.entities.StreamRating;
import com.infernalgames.utils.Statics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 @author Fourat
 */
public class ServiceStreamRating {

    public ArrayList<StreamRating> ratings;
    public static ServiceStreamRating instance=null;
    public boolean resultOk;
    private ConnectionRequest req;

    private ServiceStreamRating(){ req= new ConnectionRequest(); }

    public static ServiceStreamRating getInstance(){
        if(instance== null){
            instance= new ServiceStreamRating();
        }
        return instance;
    }


    public ArrayList<StreamRating> parseRatings(String jsonText){
        try {
            ratings= new ArrayList<>();
            JSONParser json=new JSONParser();
            Map<String, Object> ratingListJson=
                    json.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            if ( ratingListJson.get("root") != null ){
                List<Map<String, Object>> list = (List<Map<String, Object>>) ratingListJson.get("root");

                for (Map<String, Object> obj : list) {
                    StreamRating rating= new StreamRating();
                    rating.setId((int) Float.parseFloat(obj.get("id").toString()));
                    rating.setLabel(obj.get("label").toString());
                    ratings.add(rating);
                }
            }else {
                StreamRating rating= new StreamRating();
                rating.setId((int) Float.parseFloat(ratingListJson.get("id").toString()));
                rating.setLabel(ratingListJson.get("label").toString());
                ratings.add(rating);
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return ratings;
    }


    public ArrayList<StreamRating> getAllStreamRating(){
        req= new ConnectionRequest();
        String url = Statics.BASE_URL+"/streamsMobile/getAllStreamRating";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent networkEvent) {
                ratings= parseRatings(new String(req.getResponseData()));
                req.removeResponseCodeListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return ratings;
    }

    public boolean addRating(StreamRating rating){
        req= new ConnectionRequest();
        String url = Statics.BASE_URL+"/streamsMobile/addStreamRating";
        req.setUrl(url);
        req.setPost(false);
        req.addArgument("label", rating.getLabel());
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

    public boolean editRating(StreamRating rating){
        req= new ConnectionRequest();
        String url = Statics.BASE_URL+"/streamsMobile/editStreamRating";
        req.setUrl(url);
        req.setPost(false);
        req.addArgumentArray("id", rating.getId()+"");
        req.addArgument("label", rating.getLabel());
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

    public boolean deleteRating(StreamRating rating){
        req= new ConnectionRequest();
        String url = Statics.BASE_URL+"/streamsMobile/deleteStreamRating";
        req.setUrl(url);
        req.setPost(false);
        req.addArgumentArray("id", rating.getId()+"");
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
