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
import com.infernalgames.entities.Product;
import com.infernalgames.utils.Statics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author milin
 */
public class ServiceProduct {
    public static ServiceProduct instance = null;
    public boolean resultOK;
    //initialisation cnx request
    private ConnectionRequest req;
    public ArrayList<Product> products;


    public static ServiceProduct getInstance(){
        if (instance == null )
            instance = new ServiceProduct();
        return instance;
    }

    public ServiceProduct(){
        req = new ConnectionRequest();
    }

    public ArrayList<Product> parseProducts(String jsonText){

        try {
            products= new ArrayList<>();
            JSONParser json= new JSONParser();
            Map<String, Object> productsJSON= json.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            if (productsJSON.get("root")!=null){
                List<Map<String, Object>> list = (List<Map<String, Object>>) productsJSON.get("root");

                for (Map<String, Object> obj : list) {

                    Product product= new Product();
                    product.setName(obj.get("name").toString());
                    product.setDescription(obj.get("description").toString());
                    product.setPicture(obj.get("picture").toString());
                    product.setPrice(Float.parseFloat(obj.get("price").toString()));
                    product.setBrand(obj.get("brand").toString());
                    product.setId((int) Float.parseFloat(obj.get("id").toString()));
                    product.setCategory(obj.get("category").toString());
                    product.setQuantity((int) Float.parseFloat(obj.get("quantity").toString()));

                    products.add(product);
                }
            }else {
                Product product = new Product();
                product.setName(productsJSON.get("name").toString());
                product.setDescription(productsJSON.get("description").toString());
                product.setPicture(productsJSON.get("picture").toString());
                product.setPrice(Float.parseFloat(productsJSON.get("price").toString()));
                product.setBrand(productsJSON.get("brand").toString());
                product.setId((int) Float.parseFloat(productsJSON.get("id").toString()));
                product.setCategory(productsJSON.get("category").toString());
                product.setQuantity((int) Float.parseFloat(productsJSON.get("quantity").toString()));
                products.add(product);
            }
        }catch (Exception e){

        }

        return products;
    }

    public boolean modifierProduct(Product product){
        String url = Statics.BASE_URL+"/mobile/product/update?name="+product.getName()+
                "&description="+product.getDescription()+
                "&price="+product.getPrice()+
                "&brand="+product.getBrand()+
                "&category="+product.getCategory()+
                "&picture="+product.getPicture()+
                "&quantity="+product.getQuantity()+
                "&id="+product.getId();

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

    public boolean ajoutProduct(Product product) {
        String url = Statics.BASE_URL+"/mobile/product/ajout?name="+product.getName()+
                "&description="+product.getDescription()+
                "&price="+product.getPrice()+
                "&brand="+product.getBrand()+
                "&category="+product.getCategory()+
                "&picture="+product.getPicture()+
                "&quantity="+product.getQuantity();

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

    public ArrayList<Product>AffichageProduct(){
        ArrayList<Product> result = new ArrayList<>();
        String url = Statics.BASE_URL+"/mobile/product/all";
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent networkEvent) {
                products= parseProducts(new String(req.getResponseData()));
                req.removeResponseCodeListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return products;

    }

    public boolean deleteProduct(Product product){
        req= new ConnectionRequest();
        String url = Statics.BASE_URL+"/mobile/product/delete";
        req.setUrl(url);
        req.setPost(false);
        req.addArgumentArray("id", product.getId()+"");
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

    public Product DetailProduct (int id, Product product ) {
        String url = Statics.BASE_URL+"/mobile/product/detail"+id;
        req.setUrl(url);
        String str = new String(req.getResponseData());
        req.addResponseListener((evt) -> {
            JSONParser jsonp = new JSONParser();
            try {
                Map<String,Object> obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                product.setName(obj.get("name").toString());
                product.setDescription(obj.get("description").toString());
                product.setBrand(obj.get("brand").toString());
                product.setCategory(obj.get("category").toString());
                product.setPicture(obj.get("picture").toString());
                product.setPrice((Float.parseFloat(obj.get("price").toString())));


            }             catch(Exception ex){
                System.out.println("error related to SQL :(" +ex.getMessage());
            }
            System.out.println("data ===" +str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return product;
    }

}
