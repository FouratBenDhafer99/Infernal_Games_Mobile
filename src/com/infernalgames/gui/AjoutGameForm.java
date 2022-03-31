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


/**
 *
 * @author milin
 */
public class AjoutGameForm extends Form {
    Form current;

    private String imageSrc=null;
    private Image image=null;

    public AjoutGameForm (Form previous) {
        setTitle("Add Game");

        getContentPane().setScrollVisible(false);

        TextField name = new TextField("","Enter game name");
        name.setUIID("TextFieldBlack");
        addStringValue("Name",name);

        TextArea description = new TextArea("",10,10);
        description.setHint("Give the game a description");
        description.setUIID("TextFieldBlack");
        addStringValue("Description",description);

        TextField price = new TextField("","How much is it?");
        price.setUIID("TextFieldBlack");
        addStringValue("Price",price);


        TextField trailerUrl = new TextField("","The game's trailer");
        trailerUrl.setUIID("TextFieldBlack");
        addStringValue("TrailerUrl",trailerUrl);

        TextField category = new TextField("","Category");
        category.setUIID("TextFieldBlack");
        addStringValue("Category",category);

        Label picture= new Label("Games Image");
        Button btnPicture= new Button("Upload");
        addStringValue("", picture);
        addStringValue("Add pic", btnPicture);

        Button btnAjouter = new Button("Add");
        addStringValue("", btnAjouter);

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

        btnAjouter.addActionListener((e)-> {
            try {
                if (name .getText() =="" || description.getText()=="" || category.getText()=="" || trailerUrl.getText()==""
                        || imageSrc==null/* Bech ichouf enti 7atit taswira wala le */ ) {
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

                        Game game = new Game(name.getText(),
                                description.getText(),
                                trailerUrl.getText(),
                                category.getText(),
                                imageName,
                                pr
                        );

                        try {
                            OutputStream os= FileSystemStorage.getInstance().openOutputStream(imagePath);
                            ImageIO.getImageIO().save(image, os, ImageIO.FORMAT_PNG, 1);
                        }catch (Exception exception){System.out.println(exception.getMessage());}

                        System.out.println("data game ==" +game);
                        try {
                            if(ServiceGame.getInstance().ajoutGame(game)){
                                Dialog.show("Success","Game added with success!",new Command("Go to the list"));
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
