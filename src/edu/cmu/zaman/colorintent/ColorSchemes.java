package edu.cmu.zaman.colorintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by zackaman on 12/9/14.
 */
public class ColorSchemes extends Activity {
    private int[] views = new int[13];
    private int currentView = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scheme1);


        views[0] = R.layout.scheme1;
        views[1] = R.layout.scheme2;
        views[2] = R.layout.scheme3;
        views[3] = R.layout.scheme4;
        views[4] = R.layout.scheme5;
        views[5] = R.layout.scheme6;
        views[6] = R.layout.scheme7;
        views[7] = R.layout.scheme8;
        views[8] = R.layout.scheme10;
        views[9] = R.layout.scheme11;
        views[10] = R.layout.scheme12;
        views[11] = R.layout.scheme13;
        views[12] = R.layout.scheme14;





    }

    public void next(View view){
        if(currentView + 1 < views.length){
            currentView++;
            setContentView(views[currentView]);

            if(currentView + 1 == views.length) {
                //change button to finish
                Button nextButton = (Button) findViewById(R.id.button1);
                nextButton.setText("FINISH");
            }
        }
        else{
            //return home
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }
    public void back(View view){
        if(currentView - 1 >= 0){
            currentView--;
            setContentView(views[currentView]);
            //change button to next
            Button nextButton = (Button)findViewById(R.id.button1);
            nextButton.setText("NEXT");
        }
        else{
            //return home
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }
}