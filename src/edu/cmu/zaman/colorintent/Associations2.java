package edu.cmu.zaman.colorintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import edu.cmu.zaman.colorintent.R;

/**
 * Created by zackaman on 12/9/14.
 */
public class Associations2 extends Activity {
    private TextView feedbackText;
    private TextView results;
    private int numQuestions;
    private int questionsCorrect;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.associations2);


        feedbackText = (TextView)findViewById(R.id.textView11);
        results = (TextView)findViewById(R.id.textView8);



        //https://stackoverflow.com/questions/2091465/how-do-i-pass-data-between-activities-in-android
//        Intent i = new Intent(getApplicationContext(), NewActivity.class);
//        i.putExtra("new_variable_name","value");
//        startActivity(i);
//        Then in the new Activity, retrieve those values:
//
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
//            String value = extras.getString("new_variable_name");
            numQuestions = Integer.parseInt(extras.getString("numQuestions"));
            questionsCorrect = Integer.parseInt(extras.getString("questionsCorrect"));
        }

        String output = "You got "+questionsCorrect+" out of "+numQuestions+" questions correct.";
        results.setText(output);






    }

    public void next(View view){
        //return to home screen
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}