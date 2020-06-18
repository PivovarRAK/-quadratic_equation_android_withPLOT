package com.example.pivovarmath;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class coordinate_system extends MainActivity{

    LineGraphSeries<DataPoint> series;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coordinate_system_layout);
        Button btn_back = findViewById(R.id.btn_back);


        // f(x) = a*x² + b*x + c

        //Erster Zähler (von -10. bis endpoint)
        double x,y;
        x = -10.0;

        //Konfiguration vom Graphen (vllt variabel je nach groesse von y machen?)
        GraphView graph = (GraphView) findViewById(R.id.graph);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(-5.0);
        graph.getViewport().setMaxX(5.0);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(-10.0);
        graph.getViewport().setMaxY(25);
        graph.getViewport().setScalable(true);
        graph.getViewport().setScrollable(true);

        //Intent der MainActivity mit den benötigten Variablen
        Intent intent = getIntent();
        intent.getExtras();
        double varA = Double.parseDouble(intent.getStringExtra("Variable A"));
        double varB = Double.parseDouble(intent.getStringExtra("Variable B"));
        double varC = Double.parseDouble(intent.getStringExtra("Variable C"));
        double erste_Nullstelle = intent.getDoubleExtra("ns1",0);
        double zweite_Nullstelle = intent.getDoubleExtra("ns2",0);

        //Koordinaten berechnen + Zeichnen
        series = new LineGraphSeries<DataPoint>();
        for(int i = 0; i < 500; i++){
            x += 0.125;
            y = varA * (x * x) + varB * x + varC;
            series.appendData(new DataPoint(x,y), true, 1000);
        }
        graph.addSeries(series);


        TextView tv_nullstellen = findViewById(R.id.tv_nullstellen);
        if(erste_Nullstelle == 0.0 && zweite_Nullstelle == 0.0){
            tv_nullstellen.setText("Keine Nullstellen");
        }else {
            tv_nullstellen.setText("x = 0, y = " + erste_Nullstelle + "\nx = 0, y = " + zweite_Nullstelle);
        }


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(coordinate_system.this, MainActivity.class);
                startActivity(back);
            }
        });



    }


}
