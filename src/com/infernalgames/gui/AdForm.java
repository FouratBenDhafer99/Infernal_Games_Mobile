/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infernalgames.gui;

import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.infernalgames.entities.Ad;
import com.infernalgames.services.ServiceAd;

import java.io.IOException;
import java.util.Date;

/**
 *
 * @author eya dhaouadi
 */
public class AdForm extends Form{
    
    
              String file ;
          Resources theme;
          ServiceAd ess = new ServiceAd();
    Form previous;
     public AdForm(Form previous)  {
                super("Ad", BoxLayout.y());
         this.previous= previous;
            theme = UIManager.initFirstTheme("/theme");
     this.getToolbar().setUIID("tb");
        Label logi = new Label("Ads");
        logi.setUIID("login");

    
     this.getToolbar().setUIID("tb");
        this.getToolbar().addCommandToOverflowMenu("Add Ad", null, ev->{
        Form addEvent = new Form("Add Ad",BoxLayout.y());
Label AJOUT = new Label("ADD Ad");
            AJOUT.setUIID("login");
            addEvent.add(AJOUT);
            TextField Description = new TextField("", "Description", 20, TextArea.TEXT_CURSOR);
           Description.setUIID("txtn");
        TextField Type = new TextField("", "Type", 20, TextArea.TEXT_CURSOR);
         Type.setUIID("txtn");
        TextField Nom = new TextField("", "Name", 20, TextArea.TEXT_CURSOR);
         Nom.setUIID("txtn");

            Button upload = new Button("Upload Image");
        upload.setUIID("vtnvalid");
        Button save = new Button("Add");
        save.setUIID("vtnvalid");
        addEvent.add("Description : ").add(Description);
        addEvent.add("Name : ").add(Nom);
        addEvent.add("Type : ").add(Type);
        addEvent.add(upload);
        addEvent.add(save);
        
        upload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    String fileNameInServer = "";
                    MultipartRequest cr = new MultipartRequest();
                    String filepath = Capture.capturePhoto(-1, -1);
                    cr.setUrl("http://127.0.0.1/Upload.php");
                    cr.setPost(true);
                    String mime = "image/jpeg";
                    cr.addData("file", filepath, mime);
                    String out = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
                    cr.setFilename("file", out + ".jpg");//any unique name you want
                    
                    fileNameInServer += out + ".jpg";
                    System.err.println("path2 =" + fileNameInServer);
                    file=fileNameInServer;
                    InfiniteProgress prog = new InfiniteProgress();
                    Dialog dlg = prog.showInifiniteBlocking();
                    cr.setDisposeOnCompletion(dlg);
                    NetworkManager.getInstance().addToQueueAndWait(cr);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                                        
            }
        });
    
        save.addActionListener(l
                                -> {

                            if (Description.getText().equals("")) {
                                Dialog.show("ERROR", "Description can't be empty", "OK", null);

                            }  else if (Type.getText().equals("")) {
                                Dialog.show("ERROR", "Type can't be empty", "OK", null);

                            } else if (Nom.getText().equals("")) {
                                Dialog.show("ERROR", "Name can't be empty", "OK", null);

                            } 
                             else {
                           
                                Ad e = new Ad();
                                e.setNom(Nom.getText());
                                e.setDescription(Description.getText());
                                e.setImage(file);
                                e.setType(Type.getText());
                                System.out.println("forms.addEvet.addItem()"+e);
                                if (ess.addAd(e) == true) {
                                    Dialog.show("Add Ads", "Added Ad with success!", "OK", null);
                                    new AdForm(previous);
                                    
                                    
                                } else {
                                    Dialog.show("ERROR", "Server error :(", "OK", null);
                                }

                            }

                        }
                        );
               addEvent.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), evx -> {
                this.showBack();
            });
        
        addEvent.show();
 });
              for(Ad c: ess.getAllService()){
           
 
                    try {    
                        this.add(addItem(c));
                    } catch (IOException ex) {
                    }
     
 
        }
     
     
        
           
                            

      
     
     }
        public Container addItem(Ad e) throws IOException{
            
        Container cn1=new Container(new BorderLayout());
        Container cn2=new Container(BoxLayout.y());
        Container cn3 = new Container(BoxLayout.y());
        Label titre=new Label(e.getNom());
          Label libelle_titre = new Label("Nom");
          libelle_titre.setUIID("type1");
        Label lieu=new Label(e.getDescription());
        Label libelle_lieu = new Label("Description");
          libelle_lieu.setUIID("type1");
        Label Type=new Label(e.getType());
          Label libelle_discription = new Label("Type");
          libelle_discription.setUIID("type1");
        String image = new String("http://localhost/Upload/");
            EncodedImage enc = EncodedImage.create("/sep.png");
            Image im = URLImage.createToStorage(enc, "local"+e.getImage() , image+e.getImage());
       

        Button btn=new Button("Details");
        btn.setUIID("vtnvalid");

        cn2.add(libelle_titre).add(titre);
        
        cn2.add(libelle_lieu).add(lieu);
        cn2.add(libelle_discription).add(Type);
              cn2.add(im);
        cn3.add(btn);
        cn2.add(cn3);
        cn1.add(BorderLayout.WEST, cn2);
      
        btn.addActionListener(e1->{
        
        Form  f2=new Form("Details",BoxLayout.y());
        Label titrem=new Label(e.getNom());
       
        Label lieum=new Label(e.getType());
        Label discriptionm=new Label(e.getDescription());

        
        String imaga1 = new String("http://localhost/Upload/");
            try {
                EncodedImage enc1 = EncodedImage.create("/sep.png");
            } catch (IOException ex) {
            }
        Image im1 = URLImage.createToStorage(enc, "local"+e.getImage() , image+e.getImage());
       


     Button Modifier = new Button("Modifier");
     Button Supprimer = new Button("Supprimer");
       Modifier.setUIID("vtnvalid");
         Supprimer.setUIID("vtnvalid");
     Modifier.addActionListener(mod -> 
     
     {
         
         Form fmodifier = new Form("Modifier Ads", BoxLayout.y());
         
           Label modif = new Label("EDIT Ads :");
                modif.setUIID("login");
                fmodifier.add(modif);
         fmodifier.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), evx -> {
                this.showBack();
            });
            fmodifier.getToolbar().setUIID("tb");
         Button submit = new Button("Submit");
         submit.setUIID("vtnvalid");
         AutoCompleteTextField titre2 =  new AutoCompleteTextField(e.getNom());
          
         titre2.setMinimumElementsShownInPopup(1);
         titre2.setUIID("txtn");

         AutoCompleteTextField Description2=  new AutoCompleteTextField(e.getDescription());
         Description2.setMinimumElementsShownInPopup(1);
               Description2.setUIID("txtn");
         AutoCompleteTextField nbrplace=  new AutoCompleteTextField(e.getType());
         
         nbrplace.setMinimumElementsShownInPopup(1);
           nbrplace.setUIID("txtn");

          Label lib_titre = new Label("Nom");
                lib_titre.setUIID("pass");
         fmodifier.add(lib_titre).add(titre2);

              Label lib_Description = new Label("Description");
                lib_Description.setUIID("pass");
         fmodifier.add(lib_Description).add(Description2);
     Label lib_nbr_places = new Label("Type : ");
                lib_nbr_places.setUIID("pass");
         fmodifier.add(lib_nbr_places).add(nbrplace);
         fmodifier.add(submit);
         
          fmodifier.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), evx -> {
                this.showBack();
            });
         submit.addActionListener(sub ->
                 
         {
             Ad es = new Ad(e.getId(), Description2.getText(), titre2.getText(), image, nbrplace.getText());
             System.out.println("forms.addEvet.addItem()"+es);
             if ( ess.ModifierAds(es) == true) {
                 Dialog.show("Edit Ads", "Ads Updated with success!", "OK", null);
                 
                 
             } else {
                 Dialog.show("ERROR", "Adding error", "OK", null);
             }
             new AdForm(previous).show();
             
         }
                 
         );
         fmodifier.show();
     } 
     );
     
       Supprimer.addActionListener(sup ->  
       
       {
           
            if (ess.DeleteAd(e.getId())) {
                                        Dialog.show("Supprimer Ad", "Delete Supprimer aves success ", "OK", null);
                                        
                                        new AdForm(previous).show();
                                    } else {
                                        Dialog.show("Erreur", " Erreur de suppression ", "OK", null);
                                    }
           
           
           
         
       
       
       }
       
       );
         
             f2.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), evx -> {
                this.showBack();
            });
               Label lib_titre = new Label("Nom");
                lib_titre.setUIID("pass");
      

   
              Label lib_Description = new Label("Description");
                lib_Description.setUIID("pass");
 
     Label lib_nbr_places = new Label("Type : ");
                lib_nbr_places.setUIID("pass");

                
            f2.add(im1).add(lib_titre).add(titrem).add(lib_nbr_places).add(lieum).add(lib_Description).add(discriptionm).add(Modifier).add(Supprimer);
            f2.show();
         
        });
        cn1.setLeadComponent(btn);
        return cn1;
                
    }
    
   
     
     
    
}
