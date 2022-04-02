package com.infernalgames.gui;


import com.codename1.ui.*;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.infernalgames.utils.Statics;


/**
 * Utility methods common to forms e.g. for binding the side menu
 *
 * @author Shai Almog
 */
public class BaseForm extends Form {

    public void installSidemenu(Resources res) {
        Image selection = res.getImage("selection-in-sidemenu.png");

  

        Image accueilImage = null;
        if (isCurrentAccueil()) {
            accueilImage = selection;
        }

        

     

        Image profilImage = null;
        if (isCurrentProfils()) {
            profilImage = selection;
        }

        Image userImage = null;
        if (isCurrentUsers()) {
            userImage = selection;
        }

        Button inboxButton = new Button("Accueil", accueilImage);
        inboxButton.setUIID("SideCommand");
        inboxButton.getAllStyles().setPaddingBottom(0);
        Container inbox = FlowLayout.encloseMiddle(inboxButton,
                new Label("*", "SideCommandNumber"));
        inbox.setLeadComponent(inboxButton);
        inbox.setUIID("SideCommand");
        inboxButton.addActionListener(e -> new Accueil().show());
        getToolbar().addComponentToSideMenu(inbox);

        if (Statics.userconnecter != null && Statics.userconnecter.getRoles().equals("manager")) {
            getToolbar().addCommandToSideMenu("Users", userImage, e -> new UListUsers().show());
        }

    
       // if (Statics.userconnecter != null && !Statics.userconnecter.getRoles().equals("manager")) {
          
       // }
        getToolbar().addCommandToSideMenu("Deconnexion", null, e -> new SignInForm(res).show());

        // spacer
        getToolbar().addComponentToSideMenu(new Label(" ", "SideCommand"));

        getToolbar().addComponentToSideMenu(new Label(res.getImage("teamwork126.png"), "Container"));
        getToolbar().addComponentToSideMenu(new Label(Statics.userconnecter.getName() + " " + Statics.userconnecter.getLastName(), "SideCommandNoPad"));
        getToolbar().addComponentToSideMenu(new Label(Statics.userconnecter.getEmail(), "SideCommandSmall"));
        getToolbar().addCommandToSideMenu("Profil", profilImage, e -> new UFrontProfilUpdate(res, Statics.userconnecter.getEmail()).show());
    }

    protected boolean isCurrentAccueil() {
        return false;
    }

    
    protected boolean isCurrentProfils() {
        return false;
    }

    protected boolean isCurrentUsers() {
        return false;
    }

 
}
