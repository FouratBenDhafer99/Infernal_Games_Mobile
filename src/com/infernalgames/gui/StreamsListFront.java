package com.infernalgames.gui;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.infernalgames.entities.Stream;
import com.infernalgames.services.ServiceStream;
import com.infernalgames.utils.Statics;

import java.util.ArrayList;
/**
 *
 * @author Fourat
 */
public class StreamsListFront extends Form {

    public StreamsListFront(Form previous){
        setTitle("Streams");
        setLayout(BoxLayout.y());

        ArrayList<Stream> streams= ServiceStream.getInstance().getAllActiveStreams();

        if(streams.size()>0) {
            for (Stream stream : streams) {
                Container cntParent= new Container(BoxLayout.x());
                Container ctChild = new Container(BoxLayout.yCenter());
                Label title = new Label(stream.getTitle());
                Label cat = new Label(stream.getCategory().getLabel());
                Label rating = new Label(stream.getRating().getLabel());
                Button watchStreamBtn = new Button();
                cntParent.setLeadComponent(watchStreamBtn);

                try {
                    Image picture= Image.createImage(Statics.IMAGES_URL+"uploads/images/products/"+stream.getAccessData().getStreamer().getPhoto()).fill(200, 200);
                    cntParent.add(picture);
                }catch (Exception exception){
                    Label pictureError= new Label("Picture not found");
                    cntParent.add(pictureError);
                }
                watchStreamBtn.addActionListener(e->{
                    new WatchStream(previous, stream).show();
                });
                ctChild.addAll(title, cat, rating);
                cntParent.add(ctChild);
                add(cntParent);
            }
        }else{
            Label label = new Label("There's no live streams right now :/");
            Container ct = new Container(BoxLayout.x());

            ct.add(label);
            this.add(ct);
        }
        getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

        /*
        SpanLabel sp= new SpanLabel();
        sp.setText(ServiceStream.getInstance().getAllActiveStreams().toString());
        Button watchStreamBtn = new Button("Watch stream!");
        addAll(sp, watchStreamBtn);
         */
    }

}
