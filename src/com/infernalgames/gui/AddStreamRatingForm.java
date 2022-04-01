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
public class AddStreamRatingForm extends Form {

    Form parentForm= null;
    public AddStreamRatingForm(Form previous, Form parent){
        parentForm= parent;
        setTitle("Add Stream Rating");
        setLayout(BoxLayout.y());

        TextField label =new TextField("", "Rating Label");
        Button btnValidate = new Button("Add task");

        btnValidate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (label.getText().length()==0){
                    Dialog.show("Alert", "Please give the rating a label", new Command("OK"));
                }else{
                    try {
                        StreamRating rating= new StreamRating( label.getText());
                        if (ServiceStreamRating.getInstance().addRating(rating)){
                            Dialog.show("Success","Rating added with success!",new Command("Go to the list"));
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
