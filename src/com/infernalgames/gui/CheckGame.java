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
import com.infernalgames.entities.Game;
import com.infernalgames.utils.Statics;

import java.io.IOException;

import static com.codename1.ui.CN.callSerially;
import static com.codename1.ui.CN.scheduleBackgroundTask;

public class CheckGame extends Form {

    private MediaPlayer mp;
    public CheckGame(Form previous, Game game){
        setTitle(game.getName());
        setLayout(BoxLayout.y());

        try {
            Image picture= Image.createImage(Statics.IMAGES_URL+"Games/"+game.getPicture()).fill(200, 200);
            add(picture);
        }catch (Exception exception){
            Label pictureError= new Label("Picture not found");
            add(pictureError);
        }

        Container cntDesc= new Container(BoxLayout.y());
        Label descLabel= new Label("Description");
        Label desc= new Label(game.getDescription());
        cntDesc.addAll(descLabel,desc);
        Label category= new Label("Category: "+game.getCategory());
        Label rating= new Label("Rating: "+game.getRating());
        Label price= new Label("Price: "+game.getPrice());
        addAll(cntDesc, category, rating, price);


        Container ct= new Container(new BorderLayout());

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        playVideo(game.getTrailer_Url());

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

    public void playVideo( String videoUrl) {
        Form player = new Form(new BorderLayout());
        player.getToolbar().setBackCommand("", e -> {
            if (mp != null) {
                mp.getMedia().cleanup();
            }
            System.out.println("He");
        });
        player.add(CENTER, new InfiniteProgress());
        scheduleBackgroundTask(() -> {
            try {
                Media video = MediaManager.createMedia(videoUrl, true);
                video.prepare();
                callSerially(() -> {
                    if (mp != null){
                        mp.getMedia().cleanup();
                    }
                    mp = new MediaPlayer(video);
                    mp.setAutoplay(true);
                    player.removeAll();
                    player.add(CENTER, mp);
                    player.revalidate();
                });
            } catch(IOException err) {
                Log.e(err);
                ToastBar.showErrorMessage("Error loading video: " + err);
            }
        });
        player.show();
    }
}
