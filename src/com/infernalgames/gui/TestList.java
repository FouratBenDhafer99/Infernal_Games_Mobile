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
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;

import com.codename1.ui.layouts.BoxLayout;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.concurrent.CompletableFuture;

/*
    public StreamsListBack(){
        setTitle("Streams dashboard");
        setLayout(BoxLayout.y());

        ArrayList<Stream> streams= ServiceStream.getInstance().getAllStreams();

        DefaultTableModel model= new DefaultTableModel(
                new String[]{"Title", "Streamer", "Rating", "Category", "State"},
                new Object[][] {}, false) {
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        for (Stream stream: streams){
            model.addRow(
                    stream.getAccessData().getStreamKey(),
                    "Streamer",
                    stream.getRating().toString(),
                    stream.getCategory().toString(),
                    stream.getState()
            );
        }

        Table table= new Table(model){
          @Override
          protected Component createCell(Object value, int row, int column, boolean editable){
              Component cell= super.createCell(value, row, column, editable);
              if(row==-1){
                  cell.getAllStyles().setBgColor(0x9932CC);
                  cell.getAllStyles().setBgTransparency(255);
              }else if(row%2==0){
                  cell.getAllStyles().setBgColor(0xBA55D3);
                  cell.getAllStyles().setBgTransparency(255);
              }else{
                  cell.getAllStyles().setBgColor(0xDDA0DD);
                  cell.getAllStyles().setBgTransparency(255);
              }
              return cell;
          }
        };


        add(table);
        /*
        Container streamTable= TableLayout.encloseIn(5,
                new Label("Title"),
                new Label("Streamer"),
                new Label("Rating"),
                new Label("Category"),
                new Label("State"));

        if(streams.size()>0) {
            for (Stream stream : streams) {
                streamTable.addAll(new Label(stream.getTitle()),
                        new Label(stream.getAccessData().getStreamKey()),
            //            new Label(stream.getAccessData().getStreamer().toString()),
                        new Label(stream.getRating().toString()),
                        new Label(stream.getCategory().toString()),
                        new Label(""+stream.getState()));
            }
            add(streamTable);
        }else{
            Label label = new Label("There's no live streams right now :/");
            Container ct = new Container(BoxLayout.x());

            ct.add(label);
            this.add(ct);
        }


    }*/
public class TestList extends Form {

    public TestList (){
        Form hi = new Form("Browser", BoxLayout.y());
        BrowserComponent browser = new BrowserComponent();
        browser.setURL("http://127.0.0.1:8000/streamsMobile/watch/18");

        browser.setPreferredSize(new Dimension( 250, 600));

        hi.add(browser);

        Container cntChat= new Container(BoxLayout.y());
        Container chat= new Container(BoxLayout.y());
        TextField chatText= new TextField("", "Chat");
        Label text= new Label("Hahaha");
        Label text1= new Label("HHI");
        Label text2= new Label("HaSQSQ");
        chat.addAll(text, text1, text2);
        cntChat.addAll(chat, BoxLayout.encloseYBottom(chatText));

        hi.add(cntChat);



        hi.show();

    }

}
