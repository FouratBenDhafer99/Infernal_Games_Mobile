package com.infernalgames.gui;

import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.infernalgames.entities.StreamCategory;
import com.infernalgames.services.ServiceStreamCategory;
/**
 *
 * @author Fourat
 */
public class AddStreamCategoryForm extends Form {

    //To delete
    Form parentForm=null;
    public AddStreamCategoryForm(Form previous, Form parent){
         parentForm=parent;
        setTitle("Add Stream Category");
        setLayout(BoxLayout.y());

        TextField label =new TextField("", "Category Label");
        Button btnValidate = new Button("Add category");
        addAll(label, btnValidate);
        btnValidate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (label.getText().length()==0){
                    Dialog.show("Alert", "Please give the category a label", new Command("OK"));
                }else{
                    try {
                        StreamCategory category= new StreamCategory( label.getText());
                        if (ServiceStreamCategory.getInstance().addCategory(category)){
                            Dialog.show("Success","Category added with success!",new Command("Go to the list"));
                        }else {
                            Dialog.show("ERROR","Server error :(",new Command("OK"));
                        }
                    }catch (Exception e){
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                }

            }
        });

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }

}
