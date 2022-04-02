package com.infernalgames.gui;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.infernalgames.entities.Product;
import com.infernalgames.services.ServiceProduct;
import com.infernalgames.utils.Statics;

import java.util.ArrayList;

public class ProductsListFront extends Form {

    public ProductsListFront(Form previous){
        setTitle("Products");
        setLayout(BoxLayout.y());

        ArrayList<Product> products = ServiceProduct.getInstance().AffichageProduct();

        for (Product product: products){
            Button btnGame= new Button();
            btnGame.addActionListener(e->{
                new CheckProduct(this, product).show();
            });



            Container cntParent= new Container(BoxLayout.x());
            try {
                Image picture= Image.createImage(Statics.IMAGES_URL+"Products/"+product.getPicture()).fill(200, 200);
                cntParent.add(picture);
            }catch (Exception exception){
                Label pictureError= new Label("Picture not found");
                cntParent.add(pictureError);
            }
            Container cntChild= new Container(BoxLayout.yCenter());
            Label name= new Label(product.getName());
            Label category= new Label(product.getCategory());
            Label rating = new Label(String.valueOf(product.getPrice()));
            cntChild.addAll(name, category, rating);
            cntParent.add(cntChild);

            cntParent.setLeadComponent(btnGame);
            add(cntParent);
        }

    }
}
