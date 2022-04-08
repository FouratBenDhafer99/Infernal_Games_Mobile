package com.infernalgames.gui;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.infernalgames.entities.Product;
import com.infernalgames.services.ServiceProduct;

import java.util.ArrayList;

public class ProductsListBack extends Form {

    public ProductsListBack(Form previous){
        setTitle("Products");
        setLayout(BoxLayout.y());

        ArrayList<Product> products = ServiceProduct.getInstance().AffichageProduct();

        Label header= new Label("Product name");
        header.getAllStyles().setFgColor(0x000000);

        Container hdcnt= BorderLayout.center(header);
        hdcnt.getAllStyles().setBgColor(0x9932CC);
        hdcnt.getAllStyles().setBgTransparency(255);
        add(hdcnt);

        int i=0;
        for (Product product: products){
            Label label= new Label(product.getName());


            Button edit= new Button();
            FontImage.setMaterialIcon(edit, FontImage.MATERIAL_EDIT);

            Button delete= new Button();
            FontImage.setMaterialIcon(delete, FontImage.MATERIAL_DELETE_FOREVER);

            Container content= BorderLayout.center(label);
            content.add(BorderLayout.EAST,BoxLayout.encloseX(edit, delete));

            if (++i%2==0){
                content.getAllStyles().setBgColor(0xBA55D3);
                content.getAllStyles().setBgTransparency(255);
                label.getAllStyles().setFgColor(0x000000);
            }

            edit.addActionListener(e->{
                new EditProductForm(previous, product).show();
            });

            delete.addActionListener(e->{
                ServiceProduct.getInstance().deleteProduct(product);
                getContentPane().animateUnlayoutAndWait(150, 255);
                removeComponent(content);
                getContentPane().animateLayout(150);
            });
            add(content);
        }

        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ADD, e-> new AddProductForm(previous).show());
        getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
}
