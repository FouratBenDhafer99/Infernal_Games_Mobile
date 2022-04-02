package com.infernalgames.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.infernalgames.entities.Product;

import java.util.Date;

public class Everything extends Form {

    public Everything(){
        setTitle("Dashboard");
        setLayout(BoxLayout.yCenter());
        BaseForm bar= new BaseForm();
        bar.installSidemenu(UIManager.initFirstTheme("/theme"));
        add(bar);


        Button streamFront= new Button("Streams Front");
        Button streamBack= new Button("Streams Back");
        Button newsletter= new Button("Newsletter");
        Button gamesFront= new Button("Games Front");
        Button gamesBack= new Button("Games Back");
        Button productBack= new Button("Product Back");
        Button productFront= new Button("Product Front");

        addAll(streamFront, streamBack, newsletter, gamesBack, gamesFront);

        streamBack.addActionListener(e->{new StreamsListBack(this).show();});
        streamFront.addActionListener(e->{new StreamsListFront(this).show();});
        newsletter.addActionListener(e->{new NewsletterList(this).show();});
        gamesFront.addActionListener(e->{new FrontGameList(this).show();});
        gamesBack.addActionListener(e->{new BackGameList(this).show();});
        productFront.addActionListener(e->{new ProductsListFront(this).show();});
        productBack.addActionListener(e->{new ProductsListBack(this).show();});


    }
}
