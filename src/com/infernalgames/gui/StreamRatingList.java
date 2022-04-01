package com.infernalgames.gui;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.infernalgames.entities.StreamRating;
import com.infernalgames.services.ServiceStreamRating;

import java.util.ArrayList;
/**
 *
 * @author Fourat
 */
public class StreamRatingList extends Form {

    public StreamRatingList(Form previous){
        setTitle("Stream Ratings");
        setLayout(BoxLayout.y());

        ArrayList<StreamRating> ratings = ServiceStreamRating.getInstance().getAllStreamRating();

        Label header= new Label("Label");
        header.getAllStyles().setFgColor(0x000000);

        Container hdcnt= BorderLayout.center(header);
        hdcnt.getAllStyles().setBgColor(0x9932CC);
        hdcnt.getAllStyles().setBgTransparency(255);
        add(hdcnt);

        int i=0;

        for (StreamRating rating: ratings){
            Label label= new Label(rating.getLabel());

            Button edit= new Button();
            FontImage.setMaterialIcon(edit, FontImage.MATERIAL_EDIT);

            Button delete= new Button();
            FontImage.setMaterialIcon(delete, FontImage.MATERIAL_DELETE_FOREVER);

            Container content= BorderLayout.center(label);
            content.add(BorderLayout.EAST,BoxLayout.encloseX(edit, delete));

            if (++i%2==0){
                content.getAllStyles().setBgColor(0xBA55D3);
                content.getAllStyles().setBgTransparency(255);
                label.getAllStyles().setFgColor(0x000000);
            }
            edit.addActionListener(e->{
                new EditStreamRatingForm(this, rating, previous).show();
            });
            delete.addActionListener(e->{
                ServiceStreamRating.getInstance().deleteRating(rating);
                getContentPane().animateUnlayoutAndWait(150, 255);
                removeComponent(content);
                getContentPane().animateLayout(150);
            });
            add(content);
        }
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ADD, e-> new AddStreamRatingForm(this, previous).show());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
}
