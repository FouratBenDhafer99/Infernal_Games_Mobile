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

public class WatchStream extends Form {

    public WatchStream(){
        Stream stream= ServiceStream.getInstance().getStream(16);
        setTitle("Watch stream");

        Label title= new Label(stream.getTitle());
        Label description= new Label(stream.getDescription());
        Button reconnectBtn= new Button("Reconnect");
        Container ct = new Container(BoxLayout.x());

        ct.addAll(title, description, reconnectBtn);
        add(ct);

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
