package com.infernalgames.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

public class Everything extends Form {

    public Everything(){
        setTitle("Dashboard");
        setLayout(BoxLayout.yCenter());

        Button streamFront= new Button("Streams Front");
        Button streamBack= new Button("Streams Back");
        Button newsletter= new Button("Newsletter");
        Button gamesFront= new Button("Games Front");
        Button gamesBack= new Button("Games Back");

        addAll(streamFront, streamBack, newsletter, gamesBack, gamesFront);

        streamBack.addActionListener(e->{new StreamsListBack(this).show();});
        streamFront.addActionListener(e->{new StreamsListFront(this).show();});
        newsletter.addActionListener(e->{new NewsletterList(this).show();});
        gamesFront.addActionListener(e->{new FrontGameList(this).show();});
        gamesBack.addActionListener(e->{new BackGameList(this).show();});



    }
}
