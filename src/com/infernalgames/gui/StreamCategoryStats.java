package com.infernalgames.gui;

import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.infernalgames.entities.Stream;

import javax.swing.*;
import java.util.ArrayList;

public class StreamCategoryStats extends Form {

    public StreamCategoryStats(Form parent, ArrayList<Stream> liveStreams){
        setTitle("Stream Category Stats");
        setLayout(BoxLayout.yCenter());


        //ArrayList<Stream> ss= liveStreams.stream().filter(stream->stream.getCategory())

        //PieChart pie= new PieChart();

    }
}
