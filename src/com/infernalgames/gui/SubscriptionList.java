package com.infernalgames.gui;

import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.infernalgames.entities.Subscription;
import com.infernalgames.services.ServiceSubscription;

import java.util.ArrayList;

public class SubscriptionList extends Form {

    public SubscriptionList(Form previous){
        setTitle("Subscriptions");
        setLayout(BoxLayout.y());

        ArrayList<Subscription> subs= ServiceSubscription.getInstance().getAllSubs();

        if (subs.size()>0){
            Label userNameH= new Label("User name");
            userNameH.getAllStyles().setFgColor(0x000000);
            Label statusH= new Label("Status");
            statusH.getAllStyles().setFgColor(0x000000);

            Container head= new Container();
            head.setLayout(new GridLayout(1,2));
            head.addAll(userNameH, statusH);
            head.getAllStyles().setBgColor(0x9932CC);
            head.getAllStyles().setBgTransparency(255);
            add(head);

            Container data= new Container();
            data.setLayout(new GridLayout(subs.size()+1,1));
            add(data);

            int i=0;
            for (Subscription sub: subs){
                Label userName= new Label(sub.getUser().getNom()+" "+sub.getUser().getPrenom());

                Label status=null;
                if (sub.isStatus()){
                    status= new Label("Subscribed");
                }else {
                    status= new Label("Expired");
                }


                Container content= new Container();
                content.setLayout(new GridLayout(1,2));
                if (++i%2==0){
                    content.getAllStyles().setBgColor(0xBA55D3);
                    content.getAllStyles().setBgTransparency(255);
                    userName.getAllStyles().setFgColor(0x000000);
                    status.getAllStyles().setFgColor(0x000000);
                }

                content.addAll(userName, status);
                data.add(content);
            }
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
}
