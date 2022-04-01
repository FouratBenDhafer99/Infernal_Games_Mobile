package com.infernalgames.gui;

import com.codename1.components.MediaPlayer;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.Log;
import com.codename1.io.Util;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.*;
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
            Image picture= Image.createImage(Statics.IMAGES_URL+"uploads/images/products/"+stream.getAccessData().getStreamer().getPhoto()).fill(200, 200);
            add(picture);
        }catch (Exception exception){
            Label pictureError= new Label("Picture not found");
            add(pictureError);
        }

        addAll( labelDesc, description, category, reconnectBtn);
        getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, e-> new StreamsListFront(parent).showBack());
    }

    MediaPlayer mp;
    private void playVideo( String videoUrl) {

        CN.setScreenSaverEnabled(false);
        Form player = new Form(new BorderLayout());
        player.getToolbar().setBackCommand("Back", Toolbar.BackCommandPolicy.ALWAYS, e -> {
            if (mp != null) {
                mp.getMedia().cleanup();
            }
            CN.setScreenSaverEnabled(true);
        });
        player.add(BorderLayout.CENTER, FlowLayout.encloseCenterMiddle(
                new SpanLabel("Stream will start playing automatically when it is live")));

        player.addShowListener(l -> {
            while (!Util.downloadUrlToStorage(videoUrl, "temp.m3u8", false)) {
                CN.invokeAndBlock(() -> Util.sleep(1000));
            }
            try {
                // note that we cannot play the locally downloaded m3u8
                Media video = MediaManager.createMedia(videoUrl, true, () -> {
                    // completion handler, it's invoked when the stream connection is lost
                    if (mp != null) {
                        mp.getMedia().cleanup();
                    }
                    CN.setScreenSaverEnabled(true);
                });
                video.setNativePlayerMode(false);
                if (mp != null) {
                    mp.getMedia().cleanup();
                }
                mp = new MediaPlayer(video);
                mp.setAutoplay(true);
                mp.setHideNativeVideoControls(true);
                mp.setMaximize(false);
                player.removeAll();
                player.add(BorderLayout.CENTER, mp);
                player.revalidate();
            } catch (Exception err) {
                Log.e(err);
                ToastBar.showErrorMessage("Error loading straming");
            }
        });

        player.show();
    }
}
