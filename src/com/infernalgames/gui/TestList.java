package com.infernalgames.gui;

import com.codename1.components.MediaPlayer;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.Log;
import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;

import com.codename1.ui.layouts.BoxLayout;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TestList extends Form {

    MediaPlayer mp;

    public TestList(String videoUrl){
        setTitle("Test");
        setLayout(new BorderLayout());

        CN.setScreenSaverEnabled(false);
        getToolbar().setBackCommand("Back", Toolbar.BackCommandPolicy.ALWAYS, e->{
            if (mp!=null){
                mp.getMedia().cleanup();
            }
            CN.setScreenSaverEnabled(true);
            System.out.println("Back");
        });

        add(BorderLayout.CENTER, FlowLayout.encloseCenterMiddle(
                new SpanLabel("Stream will start when it's live")
        ));

        addShowListener(l->{
            /*
            while (!Util.downloadUrlToStorage(videoUrl, "index.m3u8",false)){
                CN.invokeAndBlock(()->Util.sleep(1000));
            }*/
            try {
                System.out.println("Hey");
                //InputStream k= new FileInputStream("C:/Dev/NginxUstoopia - InfernalGames/temp/hls_temp/Bruh9/index.m3u8");
                Media video= MediaManager.createMedia( "https://www.youtube.com/embed/e_E9W2vsRbQ", true);
                if(mp!=null){
                    mp.getMedia().cleanup();
                }
                System.out.println("Yoo");
                video.setNativePlayerMode(false);
                if(mp!=null){
                    mp.getMedia().cleanup();
                }
                mp= new MediaPlayer(video);
                mp.setAutoplay(true);
                mp.setHideNativeVideoControls(true);
                mp.setMaximize(false);
                removeAll();
                add(BorderLayout.CENTER, mp);
                revalidate();

            }catch (Exception e){
                e.printStackTrace();
                ToastBar.showErrorMessage("Error loading straming");
            }
        });







    }

    public TestList(int k) {

        Form hi = new Form("Storage", new BoxLayout(BoxLayout.Y_AXIS));
        hi.getToolbar().addCommandToRightBar("+", null, (e) -> {
            TextField tf = new TextField("", "File Name", 20, TextField.ANY);
            TextArea body = new TextArea(5, 20);
            body.setHint("File Body");
            Command ok = new Command("OK");
            Command cancel = new Command("Cancel");
            Command result = Dialog.show("File Name", BorderLayout.north(tf).add(BorderLayout.CENTER, body), ok, cancel);
            if(ok == result) {
                try(OutputStream os = Storage.getInstance().createOutputStream(tf.getText())) {
                    os.write(body.getText().getBytes("UTF-8"));
                    createFileEntry(hi, tf.getText());
                    hi.getContentPane().animateLayout(250);
                } catch(IOException err) {
                    Log.e(err);
                }
            }
        });

        for(String file : Storage.getInstance().listEntries()) {
            createFileEntry(hi, file);
        }
        add(hi);
    }

    private void createFileEntry(Form hi, String file) {
        Label fileField = new Label(file);
        Button delete = new Button();
        Button view = new Button();
        FontImage.setMaterialIcon(delete, FontImage.MATERIAL_DELETE);
        FontImage.setMaterialIcon(view, FontImage.MATERIAL_OPEN_IN_NEW);
        Container content = BorderLayout.center(fileField);
        int size = Storage.getInstance().entrySize(file);
        content.add(BorderLayout.EAST, BoxLayout.encloseX(new Label(size + "bytes"), delete, view));
        delete.addActionListener((e) -> {
            Storage.getInstance().deleteStorageFile(file);
            content.setY(hi.getWidth());
            hi.getContentPane().animateUnlayoutAndWait(150, 255);
            hi.removeComponent(content);
            hi.getContentPane().animateLayout(150);
        });
        view.addActionListener((e) -> {
            try(InputStream is = Storage.getInstance().createInputStream(file)) {
                String s = Util.readToString(is, "UTF-8");
                Dialog.show(file, s, "OK", null);
            } catch(IOException err) {
                Log.e(err);
            }
        });
        hi.add(content);
    }
}
