package com.infernalgames.gui;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.infernalgames.entities.Stream;
import com.infernalgames.services.ServiceStream;

import java.util.ArrayList;

public class StreamsListFront extends Form {

    public StreamsListFront(){
        setTitle("Streams");
        setLayout(BoxLayout.y());

        ArrayList<Stream> streams= ServiceStream.getInstance().getAllActiveStreams();

        if(streams.size()>0) {
            for (Stream stream : streams) {
                Label label = new Label(stream.getTitle());
                Container ct = new Container(BoxLayout.x());
                Button watchStreamBtn = new Button("Watch stream!");

                ct.addAll(label, watchStreamBtn);
                add(ct);
            }
        }else{
            Label label = new Label("There's no live streams right now :/");
            Container ct = new Container(BoxLayout.x());

            ct.add(label);
            this.add(ct);
        }

        /*
        SpanLabel sp= new SpanLabel();
        sp.setText(ServiceStream.getInstance().getAllActiveStreams().toString());
        Button watchStreamBtn = new Button("Watch stream!");
        addAll(sp, watchStreamBtn);
         */
    }
    public StreamsListFront(Form previous){
        setTitle("Streams");
        setLayout(BoxLayout.y());

        ArrayList<Stream> streams= ServiceStream.getInstance().getAllActiveStreams();

        if(streams.size()>0) {
            for (Stream stream : streams) {
                Label label = new Label(stream.getTitle());
                Container ct = new Container(BoxLayout.x());
                Button watchStreamBtn = new Button("Watch stream!");

                ct.addAll(label, watchStreamBtn);
                this.add(ct);
            }
        }else{
            Label label = new Label("There's no live streams right now :/");
            Container ct = new Container(BoxLayout.x());

            ct.add(label);
            this.add(ct);
        }
    }
}
