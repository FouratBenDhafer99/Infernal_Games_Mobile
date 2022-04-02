package com.infernalgames.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.MediaPlayer;
import com.codename1.components.ToastBar;
import com.codename1.io.Log;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.infernalgames.entities.Product;
import com.infernalgames.utils.Statics;

import java.io.IOException;

import static com.codename1.ui.CN.callSerially;
import static com.codename1.ui.CN.scheduleBackgroundTask;

public class CheckProduct extends Form {

    private MediaPlayer mp;
    public CheckProduct(Form previous, Product product){
        setTitle(product.getName());
        setLayout(BoxLayout.y());

        try {
            Image picture= Image.createImage(Statics.IMAGES_URL+"Products/"+product.getPicture()).fill(200, 200);
            add(picture);
        }catch (Exception exception){
            Label pictureError= new Label("Picture not found");
            add(pictureError);
        }

        Container cntDesc= new Container(BoxLayout.y());
        Label descLabel= new Label("Description");
        Label desc= new Label(product.getDescription());
        cntDesc.addAll(descLabel,desc);
        Label category= new Label("Category: "+product.getCategory());
        Label brand= new Label("Price: "+product.getBrand());
        Label price= new Label("Price: "+product.getPrice());
        addAll(cntDesc, category, brand, price);


        Container ct= new Container(new BorderLayout());

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());


        try{
            /*Media media= MediaManager.createMedia(game.getTrailer_Url(), true);
            media.prepare();

            if (mp != null) {
                mp.getMedia().cleanup();
            }
            mp= new MediaPlayer(media);
            mp.setAutoplay(true);
            ct.add(BorderLayout.CENTER, mp);
            add(ct);
            revalidate();*/


        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }


}
