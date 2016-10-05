package com.mlabs.bbm.firstandroidapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final ImageView imgs = (ImageView) findViewById(R.id.imgtouch);
        final TextView txtx1y1 = (TextView) findViewById(R.id.txtx1y1);
        final TextView txtx2y2 = (TextView) findViewById(R.id.txtx2y2);
        final TextView txtmotion = (TextView) findViewById(R.id.txtmotion);
        final TextView txtdiff = (TextView) findViewById(R.id.txtdiffxy);
        final TextView txtquadrant = (TextView) findViewById(R.id.txtquad);

        imgs.setOnTouchListener(new View.OnTouchListener() {
            float inx =0;
            float iny=0;
            float finalx=0;
            float finaly=0;
            float a = 0;
            float b =0;
            String INX = Float.toString(inx);
            String INY = Float.toString(iny);
            String FINALX = Float.toString(finalx);
            String FINALY = Float.toString(finaly);
            String ms1="";
            String ms2="";

            float  ans = 0;
            float answ = 0;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int ev = event.getAction();
                switch (ev) {
                    case MotionEvent.ACTION_DOWN:
                        inx = event.getX();
                        iny = event.getY();
                        String INX = Float.toString(inx);
                        String INY = Float.toString(iny);
                        txtx1y1.setText(INX + " , " + INY);
                        break;

                    case MotionEvent.ACTION_UP:
                        finalx = event.getX();
                        finaly= event.getY();
                        txtx2y2.setText(finalx +","+finaly);
                        a=inx-finalx;
                        b=iny-finaly;
                        txtdiff.setText(Math.abs(a) +","+Math.abs(b));
                        if (a>0 & b>0 ){ms2="Quadrant II";}
                        if (a>0 & b<0){ms2="Quadrant III";}
                        if (a<0 & b<0){ms2="Quadrant IV";}
                        if (a<0 & b>0){ms2="Quadrant I";}

                        if (iny < finaly){ms1 +="  Bottom";}
                        if (iny > finaly){ms1 +="  Up";}
                        if (inx > finalx){ms1 +="  Left";}
                        if (inx < finalx){ms1 +="  Right";}
                        txtmotion.setText(ms1);
                        ms1="";
                        txtquadrant.setText(ms2);
                        ms2="";

                }
                return true;
            }

        });

    }
}
