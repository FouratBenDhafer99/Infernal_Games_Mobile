package com.infernalgames.services;

import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import com.infernalgames.entities.Newsletter;
import com.infernalgames.entities.Utilisateur;
import com.infernalgames.utils.Statics;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServiceNewsletter {

    public ArrayList<Newsletter> newsletters;
    public static ServiceNewsletter instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceNewsletter(){
        req= new ConnectionRequest();
    }

    public static ServiceNewsletter getInstance(){
        if(instance== null){
            instance= new ServiceNewsletter();
        }
        return instance;
    }

    public ArrayList<Newsletter> parseNewsletters(String jsonText){

        try {
            newsletters= new ArrayList<>();
            JSONParser json= new JSONParser();
            Map<String, Object> newslettersListJson=
                    json.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            if ( newslettersListJson.get("root")!=null ){
                List<Map<String, Object>> list = (List<Map<String, Object>>) newslettersListJson.get("root");

                for (Map<String, Object> obj : list) {

                    //Author
                    Map<String, Object> map = (Map<String, Object>) obj.get("author");
                    Utilisateur user = new Utilisateur();
                    user.setId((int) Float.parseFloat(map.get("id").toString()));
                    user.setNom(map.get("name").toString());
                    user.setPrenom(map.get("lastName").toString());
                    user.setEmail(map.get("email").toString());

                    //Newsletter
                    Newsletter newsletter = new Newsletter();
                    newsletter.setId((int) Float.parseFloat(obj.get("id").toString()));
                    newsletter.setTitleIntro(obj.get("titleIntro").toString());
                    newsletter.setContentIntro(obj.get("contentIntro").toString());
                    newsletter.setTitleF(obj.get("titleF").toString());
                    newsletter.setContentF(obj.get("contentF").toString());
                    newsletter.setImageF(obj.get("imageF").toString());
                    newsletter.setTitleS(obj.get("titleS").toString());
                    newsletter.setContentS(obj.get("contentS").toString());
                    newsletter.setImageS(obj.get("imageS").toString());
                    newsletter.setTitleT(obj.get("titleT").toString());
                    newsletter.setContentT(obj.get("contentT").toString());
                    newsletter.setImageT(obj.get("imageT").toString());
                    newsletter.setDate(LocalDate.parse(obj.get("date").toString()));
                    newsletter.setSent(Boolean.parseBoolean(obj.get("sent").toString()));
                    newsletter.setAuthor(user);

                    newsletters.add(newsletter);
                }
                }else{
                    //Only one

                    Map<String, Object> map= (Map<String, Object>) newslettersListJson.get("author");
                    Utilisateur user= new Utilisateur();
                    user.setId((int) Float.parseFloat(map.get("id").toString()));
                    user.setNom(map.get("name").toString());
                    user.setPrenom(map.get("lastName").toString());
                    user.setEmail(map.get("email").toString());

                    //Newsletter
                    Newsletter newsletter=  new Newsletter(
                            (int) Float.parseFloat(newslettersListJson.get("id").toString()),
                            newslettersListJson.get("titleIntro").toString(),
                            newslettersListJson.get("contentIntro").toString(),
                            newslettersListJson.get("titleF").toString(),
                            newslettersListJson.get("contentF").toString(),
                            newslettersListJson.get("imageF").toString(),
                            newslettersListJson.get("titleS").toString(),
                            newslettersListJson.get("contentS").toString(),
                            newslettersListJson.get("imageS").toString(),
                            newslettersListJson.get("titleT").toString(),
                            newslettersListJson.get("contentT").toString(),
                            newslettersListJson.get("imageT").toString(),
                            LocalDate.parse( newslettersListJson.get("date").toString()),
                            Boolean.parseBoolean(newslettersListJson.get("sent").toString()),
                            user
                    );
                    newsletters.add(newsletter);
                }

        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(i);
        }
        System.out.println("Finish");
        return newsletters;
    }

    public ArrayList<Newsletter> getAllNewsletters(){
        req= new ConnectionRequest();
        String url = Statics.BASE_URL+"/newslettersMobile/getAllNewsletters";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent networkEvent) {
                newsletters= parseNewsletters(new String(req.getResponseData()));
                req.removeResponseCodeListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return newsletters;
    }


    public boolean addNewsletter(Newsletter newsletter){
        req= new ConnectionRequest();
        String url = Statics.BASE_URL+"/newslettersMobile/addNewsletter";
        req.setUrl(url);
        req.setPost(false);
        req.addArgument("titleIntro", newsletter.getTitleIntro());
        req.addArgument("contentIntro", newsletter.getContentIntro());

        req.addArgument("titleF", newsletter.getTitleF());
        req.addArgument("contentF", newsletter.getContentF());
        req.addArgument("imageF", newsletter.getImageF());

        req.addArgument("titleS", newsletter.getTitleS());
        req.addArgument("contentS", newsletter.getContentS());
        req.addArgument("imageS", newsletter.getImageS());

        req.addArgument("titleT", newsletter.getTitleT());
        req.addArgument("contentT", newsletter.getContentT());
        req.addArgument("imageT", newsletter.getImageT());

        req.addArgument("sent", String.valueOf(newsletter.isSent()) );

        req.addArgument("author", String.valueOf(9));
        // ****** REPLACE IT ******
        //req.addArgument("author", String.valueOf(newsletter.getAuthor().getId()));

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

    public boolean editNewsletter(Newsletter newsletter){
        req= new ConnectionRequest();
        String url = Statics.BASE_URL+"/newslettersMobile/editNewsletter";
        req.setUrl(url);
        req.setPost(false);
        req.addArgument("id", String.valueOf(newsletter.getId()));
        req.addArgument("titleIntro", newsletter.getTitleIntro());
        req.addArgument("contentIntro", newsletter.getContentIntro());

        req.addArgument("titleF", newsletter.getTitleF());
        req.addArgument("contentF", newsletter.getContentF());
        req.addArgument("imageF", newsletter.getImageF());

        req.addArgument("titleS", newsletter.getTitleS());
        req.addArgument("contentS", newsletter.getContentS());
        req.addArgument("imageS", newsletter.getImageS());

        req.addArgument("titleT", newsletter.getTitleT());
        req.addArgument("contentT", newsletter.getContentT());
        req.addArgument("imageT", newsletter.getImageT());

        req.addArgument("sent", String.valueOf(newsletter.isSent()) );
        req.addArgument("author", String.valueOf(newsletter.getAuthor().getId()));

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

    public boolean deleteNewsletter(Newsletter newsletter){
        req= new ConnectionRequest();
        String url = Statics.BASE_URL+"/newslettersMobile/deleteNewsletter";
        req.setUrl(url);
        req.setPost(false);
        req.addArgumentArray("id", newsletter.getId()+"");
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


}
