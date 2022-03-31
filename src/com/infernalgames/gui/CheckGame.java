package com.infernalgames.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.MediaPlayer;
import com.codename1.components.ToastBar;
import com.codename1.io.Log;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
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

        Container cntParent= new Container(BoxLayout.y());
        try {
            Image picture= Image.createImage(Statics.IMAGES_URL+"Games/"+game.getPicture()).fill(200, 200);
            cntParent.add(picture);
        }catch (Exception exception){
            Label pictureError= new Label("Picture not found");
            cntParent.add(pictureError);
        }

        Label descLabel= new Label("Description");
        Label desc= new Label(game.getDescription());
        Label category= new Label("Category: "+game.getRating());
        Label rating= new Label("Rating: "+game.getRating());
        addAll(descLabel, desc, category, rating);


        Container ct= new Container(new BorderLayout());

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
