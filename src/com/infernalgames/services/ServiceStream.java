package com.infernalgames.services;

import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import com.infernalgames.entities.*;
import com.infernalgames.utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

public class ServiceStream {

    public ArrayList<Stream> streams;
    public static ServiceStream instance=null;
    public boolean resultOk;
    private ConnectionRequest req;

    private ServiceStream(){
        req= new ConnectionRequest();
    }

    public static ServiceStream getInstance(){
        if(instance== null){
            instance= new ServiceStream();
        }
        return instance;
    }

    public ArrayList<Stream> parseStreams(String jsonText) {
        try{
            streams= new ArrayList<>();
            JSONParser json=new JSONParser();
            Map<String, Object> streamsListJson=
                    json.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            if( streamsListJson.get("root") != null ) {
                // Streams list
                List<Map<String, Object>> list = (List<Map<String, Object>>) streamsListJson.get("root");

                for (Map<String, Object> obj : list) {

                    //Category
                    Map<String, Object> map= (Map<String, Object>) obj.get("category");
                    StreamCategory category= new StreamCategory();
                    category.setId((int) Float.parseFloat(map.get("id").toString()));
                    category.setLabel(map.get("label").toString());
                    //Rating
                    map= (Map<String, Object>) obj.get("rating");
                    StreamRating rating= new StreamRating();
                    rating.setId((int) Float.parseFloat(map.get("id").toString()));
                    rating.setLabel(map.get("label").toString());
                    //AccessData
                    map= (Map<String, Object>) obj.get("accessData");
                    StreamData streamData= new StreamData();
                    streamData.setId((int) Float.parseFloat(map.get("id").toString()));
                    streamData.setStreamKey(map.get("streamKey").toString());
                    //Streamer
                    Map<String, Object> mapU = (Map<String, Object>) map.get("streamer");
                    Utilisateur user = new Utilisateur();
                    user.setId((int) Float.parseFloat(mapU.get("id").toString()));
                    user.setNom(mapU.get("name").toString());
                    user.setPrenom(mapU.get("lastName").toString());
                    user.setEmail(mapU.get("email").toString());
                    streamData.setStreamer(user);
                    //Stream
                    Stream s = new Stream();
                    s.setId((int) Float.parseFloat(obj.get("id").toString()));
                    s.setTitle((obj.get("title").toString()));
                    s.setDescription((obj.get("description").toString()));
                    s.setState( Boolean.parseBoolean(obj.get("state").toString())  );
                    s.setCategory(category);
                    s.setRating(rating);
                    s.setAccessData(streamData);

                    streams.add(s);
                }
            }else{
                //Only one stream
                Map<String, Object> map= (Map<String, Object>) streamsListJson.get("category");
                StreamCategory category= new StreamCategory();
                category.setId((int) Float.parseFloat(map.get("id").toString()));
                category.setLabel(map.get("label").toString());
                //Filling Rating
                map= (Map<String, Object>) streamsListJson.get("category");
                StreamRating rating= new StreamRating();
                rating.setId((int) Float.parseFloat(map.get("id").toString()));
                rating.setLabel(map.get("label").toString());
                //Filling AccessData
                map= (Map<String, Object>) streamsListJson.get("accessData");
                StreamData streamData= new StreamData();
                streamData.setId((int) Float.parseFloat(map.get("id").toString()));
                streamData.setStreamKey(map.get("streamKey").toString());


                Stream s = new Stream();
                s.setId((int) Float.parseFloat(streamsListJson.get("id").toString()));
                s.setTitle((streamsListJson.get("title").toString()));
                s.setDescription((streamsListJson.get("description").toString()));
                s.setState((boolean) streamsListJson.get("state") );
                s.setCategory(category);
                s.setRating(rating);
                s.setAccessData(streamData);

                streams.add(s);
            }
        }catch(IOException exception){

        }
        return streams;
    }


    public Stream getStream(int streamId){
        req= new ConnectionRequest();
        String url = Statics.BASE_URL+"/streamsMobile/watchStream/"+streamId;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent networkEvent) {
                streams= parseStreams(new String(req.getResponseData()));
                req.removeResponseCodeListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return streams.get(0);
    }

    public ArrayList<Stream> getAllActiveStreams(){
        req= new ConnectionRequest();
        String url = Statics.BASE_URL+"/streamsMobile/getActiveStreams";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent networkEvent) {
                streams= parseStreams(new String(req.getResponseData()));
                req.removeResponseCodeListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return streams;
    }

    public ArrayList<Stream> getAllStreams(){
        req= new ConnectionRequest();
        String url = Statics.BASE_URL+"/streamsMobile/getStreams";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent networkEvent) {
                streams= parseStreams(new String(req.getResponseData()));
                req.removeResponseCodeListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return streams;
    }

}
