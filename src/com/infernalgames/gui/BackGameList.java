package com.infernalgames.gui;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.infernalgames.entities.Game;
import com.infernalgames.services.ServiceGame;

import java.util.ArrayList;

public class BackGameList extends Form {

    public BackGameList(){
        setTitle("Games");
        setLayout(BoxLayout.y());

        ArrayList<Game> games = ServiceGame.getInstance().AffichageGame();

        Label header= new Label("Game name");
        header.getAllStyles().setFgColor(0x000000);

        Container hdcnt= BorderLayout.center(header);
        hdcnt.getAllStyles().setBgColor(0x9932CC);
        hdcnt.getAllStyles().setBgTransparency(255);
        add(hdcnt);

        int i=0;
        for (Game game: games){
            Label label= new Label(game.getName());
            label.getAllStyles().setFgColor(0x000000);

            Button edit= new Button();
            FontImage.setMaterialIcon(edit, FontImage.MATERIAL_EDIT);

            Button delete= new Button();
            FontImage.setMaterialIcon(delete, FontImage.MATERIAL_DELETE_FOREVER);

            Container content= BorderLayout.center(label);
            content.add(BorderLayout.EAST,BoxLayout.encloseX(edit, delete));

            if (++i%2==0){
                content.getAllStyles().setBgColor(0xBA55D3);
                content.getAllStyles().setBgTransparency(255);
            }

            edit.addActionListener(e->{
                new EditGameForm(this, game).show();
            });

            delete.addActionListener(e->{
                ServiceGame.getInstance().deleteGame(game);
                getContentPane().animateUnlayoutAndWait(150, 255);
                removeComponent(content);
                getContentPane().animateLayout(150);
            });
            add(content);
        }

        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ADD, e-> new AjoutGameForm(this).show());
    }
}
