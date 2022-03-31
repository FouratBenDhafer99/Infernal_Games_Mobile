package com.infernalgames.gui;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.infernalgames.entities.StreamCategory;
import com.infernalgames.services.ServiceStreamCategory;

import java.util.ArrayList;

public class StreamCategoryList extends Form {

    public StreamCategoryList(){
        setTitle("Stream Categories");
        setLayout(BoxLayout.y());

        ArrayList<StreamCategory> categories = ServiceStreamCategory.getInstance().getAllStreamCategory();

        Label header= new Label("Label");
        header.getAllStyles().setFgColor(0x000000);

        Container hdcnt= BorderLayout.center(header);
        hdcnt.getAllStyles().setBgColor(0x9932CC);
        hdcnt.getAllStyles().setBgTransparency(255);
        add(hdcnt);

        int i=0;

        for (StreamCategory category: categories){
            Label label= new Label(category.getLabel());
            label.getAllStyles().setFgColor(0x000000);

            Button edit= new Button();
            FontImage.setMaterialIcon(edit, FontImage.MATERIAL_EDIT);

            Button delete= new Button();
            FontImage.setMaterialIcon(delete, FontImage.MATERIAL_DELETE_FOREVER);

            Container content= BorderLayout.center(label);
            content.add(BorderLayout.EAST,BoxLayout.encloseX(edit, delete));

            if (++i%2==0){
                content.getAllStyles().setBgColor(0xBA55D3);
                content.getAllStyles().setBgTransparency(255);
            }
            edit.addActionListener(e->{
                new EditStreamCategoryForm(this, category).show();
            });
            delete.addActionListener(e->{
                ServiceStreamCategory.getInstance().deleteCategory(category);
                getContentPane().animateUnlayoutAndWait(150, 255);
                removeComponent(content);
                getContentPane().animateLayout(150);
            });
            add(content);
        }
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ADD, e-> new AddStreamCategoryForm(this).show());
    }
}
