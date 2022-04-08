package com.infernalgames.gui;

import com.codename1.components.MediaPlayer;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.Log;
import com.codename1.io.Util;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.*;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.infernalgames.entities.Stream;
import com.infernalgames.services.ServiceStream;
import com.infernalgames.utils.Statics;

public class WatchStream extends Form {

    public WatchStream(Form parent,Stream stream){
        setTitle(stream.getTitle());
        setLayout(BoxLayout.y());


        Label labelDesc= new Label(stream.getDescription());
        Label description= new Label(stream.getDescription());
        Label category= new Label("Category: "+ stream.getCategory().getLabel());

        Button reconnectBtn= new Button("Reconnect");
        try {
            Image picture= Image.createImage(Statics.IMAGES_URL+"uploads/images/products/"+stream.getAccessData().getStreamer().getImage()).fill(200, 200);
            add(picture);
        }catch (Exception exception){
            Label pictureError= new Label("Picture not found");
            add(pictureError);
        }

        BrowserComponent browser = new BrowserComponent();
        browser.setURL("http://127.0.0.1:8000/streamsMobile/watch/"+stream.getId());

        browser.setPreferredSize(new Dimension( 250, 600));

        addAll(browser, labelDesc, description, category);
        getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, e-> parent.showBack());
    }

}
