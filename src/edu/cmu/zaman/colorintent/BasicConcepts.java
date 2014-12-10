package edu.cmu.zaman.colorintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by zackaman on 12/9/14.
 */
public class BasicConcepts extends Activity {
    private int[] views = new int[11];
    private int currentView = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic1);


        views[0] = R.layout.basic1;
        views[1] = R.layout.basic2;
        views[2] = R.layout.basic3;
        views[3] = R.layout.basic4;
        views[4] = R.layout.basic5;
        views[5] = R.layout.basic6;
        views[6] = R.layout.basic7;
        views[7] = R.layout.basic8;
        views[8] = R.layout.basic9;
        views[9] = R.layout.basic10;
        views[10] = R.layout.basic11;





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