package com.infernalgames.gui;

import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.ImageIO;
import com.infernalgames.entities.Newsletter;
import com.infernalgames.services.ServiceNewsletter;
import com.infernalgames.utils.Statics;

import java.io.OutputStream;
/**
 *
 * @author Fourat
 */
public class EditNewsletterForm extends Form {

    String imageSrcF=null;
    Image imgF=null;
    String imageSrcS=null;
    Image imgS=null;
    String imageSrcT=null;
    Image imgT=null;

    String iF= null;
    String iS= null;
    String iT= null;

    public EditNewsletterForm(Form previous, Newsletter nw){
        setTitle("Edit newsletter");
        setLayout(BoxLayout.y());

        TextField titleIntro =new TextField(nw.getTitleIntro(), "Intro Title");
        TextArea contentIntro = new TextArea(nw.getContentIntro(), 10,10);
        contentIntro.setHint( "Intro Content");
        addAll(titleIntro, contentIntro);

        TextField titleF =new TextField(nw.getTitleF(), "First Title");
        TextArea contentF = new TextArea(nw.getTitleF(), 10,10);
        contentIntro.setHint( "First Content");
        Label labelF= new Label("First Image");
        Button btnF= new Button("Upload");
        addAll(titleF, contentF, labelF, btnF);

        TextField titleS =new TextField(nw.getTitleS(), "Second Title");
        TextArea contentS = new TextArea(nw.getContentS(), 10,10);
        contentIntro.setHint( "Second Content");
        Label labelS= new Label("Second Image");
        Button btnS= new Button("Upload");
        addAll(titleS, contentS, labelS, btnS);

        TextField titleT =new TextField(nw.getTitleT(), "Third Title");
        TextArea contentT = new TextArea(nw.getContentT(), 10,10);
        contentIntro.setHint( "Third Content");
        Label labelT= new Label("Third Image");
        Button btnT= new Button("Upload");
        addAll(titleT, contentT, labelT, btnT);

        CheckBox send= new CheckBox("Send");
        send.setSelected(nw.isSent());
        Button validate= new Button("Validate");
        addAll(send, validate);

        btnF.addActionListener(e->{
            if(FileChooser.isAvailable()){
                FileChooser.setOpenFilesInPlace(true);
                FileChooser.showOpenDialog(".jpg, .png, .jpeg", e2->{
                    try{
                        //String imageSource= (String) e2.getSource();
                        //imageSrcF= new String(imageSource);
                        imageSrcF= (String) e2.getSource();
                        if (imageSrcF==null){
                            add("No image was added!");
                            if (imgF!=null){
                                imgF=null;
                            }
                            revalidate();
                        }else {
                            try {
                                imgF= Image.createImage(imageSrcF);
                                System.out.println(imageSrcF);
                                //add(imgF);
                                //revalidate();
                            }catch (Exception ex){}
                        }
                    }catch (Exception exception){
                        Dialog.show("ERROR","You didn't select any image!",new Command("Return and select one"));
                    }
                });
            }
        });

        btnS.addActionListener(e->{
            if(FileChooser.isAvailable()){
                FileChooser.setOpenFilesInPlace(true);
                FileChooser.showOpenDialog(".jpg, .png, .jpeg", e2->{
                    try{
                        //String imageSource= (String) e2.getSource();
                        //imageSrcF= new String(imageSource);
                        imageSrcS= (String) e2.getSource();
                        if (imageSrcS==null){
                            add("No image was added!");
                            if (imgS!=null){
                                imgS=null;
                            }
                            revalidate();
                        }else {
                            try {
                                imgS= Image.createImage(imageSrcS);
                                System.out.println(imageSrcS);
                                //add(imgF);
                                //revalidate();
                            }catch (Exception ex){}
                        }
                    }catch (Exception exception){
                        Dialog.show("ERROR","You didn't select any image!",new Command("Return and select one"));
                    }
                });
            }
        });

        btnT.addActionListener(e->{
            if(FileChooser.isAvailable()){
                FileChooser.setOpenFilesInPlace(true);
                FileChooser.showOpenDialog(".jpg, .png, .jpeg", e2->{
                    try{
                        //String imageSource= (String) e2.getSource();
                        //imageSrcF= new String(imageSource);
                        imageSrcT= (String) e2.getSource();
                        if (imageSrcT==null){
                            add("No image was added!");
                            if (imgT!=null){
                                imgT=null;
                            }
                            revalidate();
                        }else {
                            try {
                                imgT= Image.createImage(imageSrcT);
                                System.out.println(imageSrcT);
                                //add(imgF);
                                //revalidate();
                            }catch (Exception ex){}
                        }
                    }catch (Exception exception){
                        Dialog.show("ERROR","You didn't select any image!",new Command("Return and select one"));
                    }
                });
            }
        });

        validate.addActionListener(e->{
            if (titleIntro.getText() == null || contentIntro.getText() == null ||
                    titleF.getText() == null || contentF.getText() == null || imageSrcF == null ||
                    titleS.getText() == null || contentS.getText() == null || imageSrcS == null ||
                    titleT.getText() == null || contentT.getText() == null || imageSrcT == null ){
                Dialog.show("ERROR","Fill all the fields",new Command("Okay"));
            }
            else{
                System.out.println("1");
                String extF= null;
                String extS= null;
                String extT= null;
                if (imageSrcF.lastIndexOf(".")>0){
                    extF= imageSrcF.substring( imageSrcF.lastIndexOf(".")+1 );
                }
                if (imageSrcS.lastIndexOf(".")>0){
                    extS= imageSrcS.substring( imageSrcS.lastIndexOf(".")+1 );
                }
                if (imageSrcT.lastIndexOf(".")>0){
                    extT= imageSrcT.substring( imageSrcT.lastIndexOf(".")+1 );
                }
                System.out.println(extF+" "+extS+" "+extT);
                iF= titleIntro.getText()+titleF.getText()+"."+extF;
                iS= titleIntro.getText()+titleS.getText()+"."+extS;
                iT= titleIntro.getText()+titleT.getText()+"."+extT;
                System.out.println(iF+" "+iS+" "+iT);

                String imageFileF= Statics.IMAGES_URL + "Newsletters/"+iF;
                String imageFileS= Statics.IMAGES_URL + "Newsletters/"+iS;
                String imageFileT= Statics.IMAGES_URL + "Newsletters/"+iT;
                System.out.println(imageFileF+" "+imageFileS+" "+imageFileT);
                try {
                    OutputStream os= FileSystemStorage.getInstance().openOutputStream(imageFileF);
                    ImageIO.getImageIO().save(imgF, os, ImageIO.FORMAT_PNG, 1);

                    os= FileSystemStorage.getInstance().openOutputStream(imageFileS);
                    ImageIO.getImageIO().save(imgS, os, ImageIO.FORMAT_PNG, 1);

                    os= FileSystemStorage.getInstance().openOutputStream(imageFileT);
                    ImageIO.getImageIO().save(imgT, os, ImageIO.FORMAT_PNG, 1);
                }catch (Exception exception){
                    System.out.println("Here");
                    System.out.println(exception.getMessage());
                }

                //Utilisateur author= new Utilisateur();
                Newsletter newsletter= new Newsletter(
                        nw.getId(),
                        titleIntro.getText(),
                        contentIntro.getText(),
                        titleF.getText(),
                        contentF.getText(),
                        iF,
                        titleS.getText(),
                        contentS.getText(),
                        iS,
                        titleT.getText(),
                        contentT.getText(),
                        iT,
                        send.isSelected(),
                        nw.getAuthor()
                );

                try {
                    if(ServiceNewsletter.getInstance().editNewsletter(newsletter)){
                        Dialog.show("Success","Newsletter updated with success!",new Command("Go to the list"));
                        new NewsletterList(previous).showBack();
                    }else {
                        Dialog.show("ERROR","Server error :(",new Command("OK"));
                    }
                }catch (Exception exception){
                    Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    System.out.println(exception.getMessage());
                }
            }


        });

        getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, e-> new NewsletterList(previous).showBack());
    }
}
