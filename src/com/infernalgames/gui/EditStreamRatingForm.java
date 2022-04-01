package com.infernalgames.gui;

import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.infernalgames.entities.StreamRating;
import com.infernalgames.services.ServiceStreamRating;
/**
 *
 * @author Fourat
 */
public class EditStreamRatingForm extends Form {

    Form parentForm=null;
    public EditStreamRatingForm(Form previous, StreamRating rating, Form parent){
        parentForm=parent;
        setTitle("Edit Stream Rating");
        setLayout(BoxLayout.y());

        TextField label =new TextField(rating.getLabel(), "Rating Label");
        Button btnValidate = new Button("Edit rating");

        btnValidate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (label.getText().length()==0){
                    Dialog.show("Alert", "Please give the category a label", new Command("OK"));
                }else{
                    try {
                        rating.setLabel(label.getText());
                        if (ServiceStreamRating.getInstance().editRating(rating)){
                            Dialog.show("Success","Rating updated with success!",new Command("Return to the list"));
                            new StreamRatingList(parentForm).showBack();

                        }else {
                            Dialog.show("ERROR","Server error :(",new Command("OK"));
                        }
                    }catch (Exception e){
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                        System.out.println(e.getMessage());
                    }
                }

            }
        });

        addAll(label, btnValidate);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
}
