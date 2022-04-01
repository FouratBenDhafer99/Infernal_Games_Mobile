/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.infernalgames.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.infernalgames.entities.Game;
import com.infernalgames.utils.Statics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author milin
 */
public class ServiceGame {
    public static ServiceGame instance = null;
    public boolean resultOK;
    //initialisation cnx request
    private ConnectionRequest req;
    public ArrayList<Game> games;


    public static ServiceGame getInstance(){
        if (instance == null )
            instance = new ServiceGame();
        return instance;
    }

    public ServiceGame(){
        req = new ConnectionRequest();
    }

    public ArrayList<Game> parseGames(String jsonText){

        try {
            games= new ArrayList<>();
            JSONParser json= new JSONParser();
            Map<String, Object> gamesJSON= json.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            if (gamesJSON.get("root")!=null){
                List<Map<String, Object>> list = (List<Map<String, Object>>) gamesJSON.get("root");

                for (Map<String, Object> obj : list) {

                    Game game= new Game();
                    game.setName(obj.get("name").toString());
                    game.setDescription(obj.get("description").toString());
                    game.setPicture(obj.get("picture").toString());
                    game.setRating(Float.parseFloat(obj.get("rating").toString()));
                    game.setPrice(Float.parseFloat(obj.get("price").toString()));
                    game.setTrailer_Url(obj.get("trailerUrl").toString());
                    game.setId((int) Float.parseFloat(obj.get("id").toString()));
                    game.setCategory(obj.get("category").toString());

                    games.add(game);
                }
            }else {
                Game game= new Game();
                game.setName(gamesJSON.get("name").toString());
                game.setDescription(gamesJSON.get("description").toString());
                game.setPicture(gamesJSON.get("picture").toString());
                game.setRating(Float.parseFloat(gamesJSON.get("rating").toString()));
                game.setPrice(Float.parseFloat(gamesJSON.get("price").toString()));
                game.setTrailer_Url(gamesJSON.get("trailerUrl").toString());
                game.setId((int) Float.parseFloat(gamesJSON.get("id").toString()));
                game.setCategory(gamesJSON.get("category").toString());
                games.add(game);
            }
        }catch (Exception e){

        }

        return games;
    }


    public boolean ajoutGame(Game game) {
        String url = Statics.BASE_URL+"/game/mobile/ajout?name="+game.getName()+
                "&description="+game.getDescription()+
                "&price="+game.getPrice()+
                "&trailerUrl="+game.getTrailer_Url()+
                "&category="+game.getCategory()+
                "&rating="+game.getRating()+
                "&picture="+game.getPicture();

        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);// exécution du request
        return resultOK;
    }

    public ArrayList<Game>AffichageGame(){
        ArrayList<Game> result = new ArrayList<>();
        String url = Statics.BASE_URL+"/game/mobile/all";
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent networkEvent) {
                games= parseGames(new String(req.getResponseData()));
                req.removeResponseCodeListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return games;

    }

    public boolean deleteGame(Game game){
        req= new ConnectionRequest();
        String url = Statics.BASE_URL+"/game/mobile/delete";
        req.setUrl(url);
        req.setPost(false);
        req.addArgumentArray("id", game.getId()+"");
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

    public Game DetailGame (int id, Game game ) {
        String url = Statics.BASE_URL+"/game/mobile/detail"+id;
        req.setUrl(url);
        String str = new String(req.getResponseData());
        req.addResponseListener((evt) -> {
            JSONParser jsonp = new JSONParser();
            try {
                Map<String,Object> obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                game.setName(obj.get("name").toString());
                game.setDescription(obj.get("description").toString());
                game.setTrailer_Url(obj.get("trailer_Url").toString());
                game.setCategory(obj.get("category").toString());
                game.setPicture(obj.get("picture").toString());
                game.setPrice((Float.parseFloat(obj.get("price").toString())));
                game.setRating((Float.parseFloat(obj.get("rating").toString())));

            }             catch(Exception ex){
                System.out.println("error related to SQL :(" +ex.getMessage());
            }
            System.out.println("data ===" +str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return game;
    }

}
