package com.infernalgames.gui;

import com.codename1.ui.*;
import com.codename1.ui.layouts.*;
import com.infernalgames.entities.Stream;
import com.infernalgames.entities.StreamRating;
import com.infernalgames.services.ServiceStream;

import java.util.ArrayList;

/**
 *
 * @author Fourat
 */
public class StreamsListBack extends Form {

    public StreamsListBack(Form previous){
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

                Label sStreamer= new Label(stream.getAccessData().getStreamer().getName());

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
        getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }



}
