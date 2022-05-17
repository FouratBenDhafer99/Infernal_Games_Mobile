package com.infernalgames.gui;

import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.infernalgames.entities.Product;

import java.util.Date;
/**
 @author Fourat
 */
public class Everything extends Form {

    public Everything(){
        setTitle("Dashboard");
        setLayout(BoxLayout.yCenter());
        BaseForm bar= new BaseForm();
        bar.installSidemenu(UIManager.initFirstTheme("/theme"));
        add(bar);

        Button streamFront= new Button("Streams client side");
        Button streamBack= new Button("Streams management");
        Button newsletter= new Button("Newsletter management");
        Button gamesFront= new Button("Games client side");
        Button gamesBack= new Button("Games management");
        Button productBack= new Button("Products management");
        Button productFront= new Button("Product client side");
        Button adBack= new Button("Ads management");

        addAll(streamFront, streamBack, newsletter,  gamesFront, gamesBack, productFront, productBack, adBack);

        streamBack.addActionListener(e->{new StreamsListBack(this).show();});
        streamFront.addActionListener(e->{new StreamsListFront(this).show();});
        newsletter.addActionListener(e->{new NewsletterList(this).show();});
        gamesFront.addActionListener(e->{new FrontGameList(this).show();});
        gamesBack.addActionListener(e->{new BackGameList(this).show();});
        productFront.addActionListener(e->{new ProductsListFront(this).show();});
        productBack.addActionListener(e->{new ProductsListBack(this).show();});
        adBack.addActionListener(e->{new AdForm(this).show();});

    }
}
