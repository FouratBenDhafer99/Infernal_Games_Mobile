package com.infernalgames.gui;

import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.infernalgames.entities.StreamCategory;
import com.infernalgames.services.ServiceStreamCategory;

public class EditStreamCategoryForm extends Form{

    public EditStreamCategoryForm(Form previous, StreamCategory category){
        setTitle("Add Stream Category");
        setLayout(BoxLayout.y());

        TextField label =new TextField(category.getLabel(), "Category Label");
        Button btnValidate = new Button("Edit category");

        btnValidate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (label.getText().length()==0){
                    Dialog.show("Alert", "Please give the category a label", new Command("OK"));
                }else{
                    try {
                        category.setLabel(label.getText());
                        if (ServiceStreamCategory.getInstance().editCategory(category)){
                            Dialog.show("Success","Category updated with success!",new Command("Return to the list"));
                            new StreamCategoryList().showBack();

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
