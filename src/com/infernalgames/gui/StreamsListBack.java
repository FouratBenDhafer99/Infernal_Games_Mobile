package com.infernalgames.gui;

import com.codename1.ui.*;
import com.codename1.ui.layouts.*;
import com.infernalgames.entities.Stream;
import com.infernalgames.entities.StreamRating;
import com.infernalgames.services.ServiceStream;

import java.util.ArrayList;


public class StreamsListBack extends Form {
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

    public StreamsListBack(){
        setTitle("Streams");
        setLayout(BoxLayout.y());
        ArrayList<Stream> streams= ServiceStream.getInstance().getAllStreams();

        if (streams.size()>0){
            Label title= new Label("Title");
            title.getAllStyles().setFgColor(0x000000);
            Label streamer= new Label("Streamer");
            streamer.getAllStyles().setFgColor(0x000000);
            Label rating= new Label("Rating");
            rating.getAllStyles().setFgColor(0x000000);
            Label category= new Label("Category");
            category.getAllStyles().setFgColor(0x000000);
            Label state= new Label("State");
            state.getAllStyles().setFgColor(0x000000);

            Container head= new Container();
            head.setLayout(new GridLayout(1,5));
            head.add(title).add(streamer).add(rating).add(category).add(state);
            head.getAllStyles().setBgColor(0x9932CC);
            head.getAllStyles().setBgTransparency(255);
            add(head);

            Container data= new Container();
            data.setLayout(new GridLayout(streams.size()+1,1));
            add(data);

            int i=0;
            for (Stream stream: streams){
                Label sTitle= new Label(stream.getTitle());

                Label sStreamer= new Label(stream.getAccessData().getStreamer().getNom());

                Label sRating= new Label(stream.getRating().getLabel());

                Label sCategory= new Label(stream.getCategory().getLabel());


                Label sState=null;
                if (stream.getState()){
                    sState= new Label("Live");
                }else {
                    sState= new Label("Done");
                }

                Container content= new Container();
                content.setLayout(new GridLayout(1,5));
                if (++i%2==0){
                    content.getAllStyles().setBgColor(0xBA55D3);
                    content.getAllStyles().setBgTransparency(255);
                    sCategory.getAllStyles().setFgColor(0x000000);
                    sTitle.getAllStyles().setFgColor(0x000000);
                    sStreamer.getAllStyles().setFgColor(0x000000);
                    sRating.getAllStyles().setFgColor(0x000000);
                    sState.getAllStyles().setFgColor(0x000000);
                }

                content.add(sTitle).add(sStreamer).add(sRating).add(sCategory).add(sState);
                data.add(content);
            }
        }else {
            Label empty= new Label("There's no streams");
            add(empty);
        }

        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_CATEGORY, e-> new StreamCategoryList(this).show());
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_RATE_REVIEW, e-> new StreamRatingList(this).show());

    }



}
