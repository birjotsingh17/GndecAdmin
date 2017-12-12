package com.birjot.gndec_sports_admin.Activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.birjot.gndec_sports_admin.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

public class Graphs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }

        setContentView(R.layout.activity_graphs);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        setTitle("Analysis");



        GraphView graph = (GraphView) findViewById(R.id.graph);
        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 150),
                new DataPoint(1, 76),
                new DataPoint(2, 108),
                new DataPoint(3, 170),
                new DataPoint(4, 37),
                new DataPoint(5, 106),
                new DataPoint(6, 29)
               // new DataPoint(7, 8)
        });
        graph.addSeries(series);

// styling
        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.rgb((int) data.getX()*255/4, (int) Math.abs(data.getY()*255/6), 100);
            }

        }

        );

       // series.setSpacing(50);

// draw values on top
        series.setDrawValuesOnTop(true);
        series.setValuesOnTopColor(Color.RED);
       // graph.setTitle("foo");



        // LABELING https://stackoverflow.com/questions/30279536/graphview-how-to-show-x-axis-label
        GridLabelRenderer gridLabel = graph.getGridLabelRenderer();
        //gridLabel.setHorizontalAxisTitle("Various Branches");
        gridLabel.setVerticalAxisTitle("Number of Participants");
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);


        /*
        staticLabelsFormatter.setHorizontalLabels(new String[] {"CIVIL", "ECE", "Elect.","Mech","Prod.","CSE"});*/
        //staticLabelsFormatter.setVerticalLabels(new String[] {"low", "middle", "high"});
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);


//series.setValuesOnTopSize(50);




       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }

}
