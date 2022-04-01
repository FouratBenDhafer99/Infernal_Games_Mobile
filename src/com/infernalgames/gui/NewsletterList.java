package com.infernalgames.gui;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.infernalgames.entities.Newsletter;
import com.infernalgames.services.ServiceNewsletter;

import java.util.ArrayList;

public class NewsletterList extends Form {

    public NewsletterList(){
        setTitle("Newsletters");
        setLayout(BoxLayout.y());

        ArrayList<Newsletter> newsletters= ServiceNewsletter.getInstance().getAllNewsletters();

        Label headerTitle= new Label("Title");
        headerTitle.getAllStyles().setFgColor(0x000000);
        Label headerAuth= new Label("Author");
        headerAuth.getAllStyles().setFgColor(0x000000);
        Label headerDate= new Label("Last Modification");
        headerDate.getAllStyles().setFgColor(0x000000);
        Label headerSent= new Label("Sent");
        headerSent.getAllStyles().setFgColor(0x000000);

        Container head= new Container();
        head.setLayout(new GridLayout(1,5));
        head.add(headerTitle).add(headerAuth).add(headerDate).add(headerSent);
        head.getAllStyles().setBgColor(0x9932CC);
        head.getAllStyles().setBgColor(0x311854);
        head.getAllStyles().setBgTransparency(255);
        add(head);

        Container data= new Container();
        data.setLayout(new GridLayout(newsletters.size()+1,1));
        add(data);

        int i=0;
        for (Newsletter newsletter: newsletters){
            Label title= new Label(newsletter.getTitleIntro());
            //
            Label author= new Label(newsletter.getAuthor().getNom());
            //
            Label date= new Label(String.valueOf(newsletter.getDate()));
            //
            Label sent= new Label(String.valueOf(newsletter.isSent()));
            //

            Button edit= new Button();
            FontImage.setMaterialIcon(edit, FontImage.MATERIAL_EDIT);
            Button delete= new Button();
            FontImage.setMaterialIcon(delete, FontImage.MATERIAL_DELETE_FOREVER);

            Container content= new Container();
            content.setLayout(new GridLayout(1,5));

            if (++i%2==0){
                content.getAllStyles().setBgColor(0xBA55D3);
                content.getAllStyles().setBgTransparency(255);
                title.getAllStyles().setFgColor(0x000000);
                author.getAllStyles().setFgColor(0x000000);
                date.getAllStyles().setFgColor(0x000000);
                sent.getAllStyles().setFgColor(0x000000);
            }

            Container actions= BoxLayout.encloseX(edit, delete);
            content.add(title).add(author).add(date).add(sent).add(actions);
            data.add(content);

            edit.addActionListener(e->{
                new EditNewsletterForm(this, newsletter).show();
            });
            delete.addActionListener(e->{
                ServiceNewsletter.getInstance().deleteNewsletter(newsletter);
                getContentPane().animateUnlayoutAndWait(150, 255);
                removeComponent(content);
                getContentPane().animateLayout(150);
            });
        }

        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ADD, e-> new AddNewsletterForm(this).show());
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_PEOPLE_ALT, e-> new SubscriptionList(this).show());

    }
}
