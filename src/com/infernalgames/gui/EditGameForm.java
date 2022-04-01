package com.infernalgames.gui;

import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.ImageIO;
import com.infernalgames.entities.Game;
import com.infernalgames.services.ServiceGame;
import com.infernalgames.utils.Statics;

import java.io.OutputStream;

public class EditGameForm extends Form {

    private String imageSrc=null;
    private Image image=null;

    public  EditGameForm(Form previous, Game game){
        setTitle("Edit Game");


        getContentPane().setScrollVisible(false);

        TextField name = new TextField(game.getName(),"Enter game name");
        addStringValue("Name",name);

        TextArea description = new TextArea(game.getDescription(),10,10);
        addStringValue("Description",description);

        TextField price = new TextField(String.valueOf(game.getPrice()),"How much is it?");
        addStringValue("Price",price);


        TextField trailerUrl = new TextField(game.getTrailer_Url(),"The game's trailer");
        addStringValue("TrailerUrl",trailerUrl);

        TextField category = new TextField(game.getCategory(),"Category");
        addStringValue("Category",category);

        Button btnPicture= new Button("Upload");
        addStringValue("Game's picture", btnPicture);

        Button btnUpdate = new Button("Update");
        addStringValue("", btnUpdate);

        btnPicture.addActionListener(e->{
            if(FileChooser.isAvailable()){
                FileChooser.setOpenFilesInPlace(true);
                FileChooser.showOpenDialog(".jpg, .png, .jpeg", e2->{
                    try{
                        imageSrc= (String) e2.getSource();
                        if(imageSrc==null){
                            add("No image found!");
                            if(image!=null){
                                image=null;
                            }
                            revalidate();
                        }else {
                            try {
                                image= Image.createImage(imageSrc);
                            }catch (Exception ex){}

                        }
                    }catch (Exception exception){
                        Dialog.show("ERROR","You didn't select any image!",new Command("Return and select one"));
                    }
                });
            }
        });

        btnUpdate.addActionListener((e)-> {
            try {
                if (name .getText() =="" || description.getText()=="" || category.getText()=="" || trailerUrl.getText()=="" || imageSrc==null) {
                    Dialog.show("ERROR", "Fill all the fields", "Okay", "Cancel");
                }
                else {
                    String ext= null;
                    if(imageSrc.lastIndexOf(".")>0)
                        ext= imageSrc.substring(imageSrc.lastIndexOf(".")+1);

                    String imageName= name.getText()+"."+ext;
                    String imagePath= Statics.IMAGES_URL+"Games/"+imageName;


                    try {
                        Float pr= Float.parseFloat(price.getText());

                        Game g = new Game(
                                game.getId(),
                                name.getText(),
                                description.getText(),
                                trailerUrl.getText(),
                                category.getText(),
                                imageName,
                                pr,
                                game.getRating()
                        );
                        try {
                            OutputStream os= FileSystemStorage.getInstance().openOutputStream(imagePath);
                            ImageIO.getImageIO().save(image, os, ImageIO.FORMAT_PNG, 1);
                        }catch (Exception exception){System.out.println(exception.getMessage());}
                        System.out.println("data game ==" +g);
                        try {
                            if(ServiceGame.getInstance().ajoutGame(g)){
                                Dialog.show("Success","Game updated with success!",new Command("Go to the list"));
                                new BackGameList().showBack();
                            }else {
                                Dialog.show("ERROR","Server error :(",new Command("OK"));
                            }
                        }catch (Exception exception){
                            Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                            System.out.println(exception.getMessage());
                        }

                    }catch (Exception ex){
                        Dialog.show("ERROR", "Price should be a float!", "Okay", "Cancel");
                    }



                }}
            catch(Exception ex ){
                ex.printStackTrace();

            }
        });
        getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, e-> new BackGameList().showBack());
    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s,"PaddedLabel")).add(BorderLayout.CENTER,v));
        //add(createLineSeparator(0xeeeeee));
    }
}
