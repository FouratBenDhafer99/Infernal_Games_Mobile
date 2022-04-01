package com.infernalgames.gui;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.infernalgames.entities.Game;
import com.infernalgames.services.ServiceGame;
import com.infernalgames.utils.Statics;

import java.util.ArrayList;

public class FrontGameList extends Form {

    public FrontGameList(Form previous){
        setTitle("Games");
        setLayout(BoxLayout.y());

        ArrayList<Game> games = ServiceGame.getInstance().AffichageGame();

        for (Game game: games){
            Button btnGame= new Button();
            btnGame.addActionListener(e->{
                new CheckGame(previous, game).show();
            });



            Container cntParent= new Container(BoxLayout.x());
            try {
                Image picture= Image.createImage(Statics.IMAGES_URL+"Games/"+game.getPicture()).fill(200, 200);
                cntParent.add(picture);
            }catch (Exception exception){
                Label pictureError= new Label("Picture not found");
                cntParent.add(pictureError);
            }
            Container cntChild= new Container(BoxLayout.yCenter());
            Label name= new Label(game.getName());
            Label category= new Label(game.getCategory());
            Label rating = new Label(String.valueOf(game.getRating()));
            cntChild.addAll(name, category, rating);
            cntParent.add(cntChild);

            cntParent.setLeadComponent(btnGame);
            add(cntParent);
        }
        getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
}
