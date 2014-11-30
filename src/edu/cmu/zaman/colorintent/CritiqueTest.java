package edu.cmu.zaman.colorintent;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by zackaman on 11/29/14.
 */
public class CritiqueTest extends Activity {
    private ArrayList<String> questions = new ArrayList<String>();
    private int questionNum = 0;
    int correctAnswer;
    ImageView[] buttons = new ImageView[6];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        questions.add(0, "6I just don't like it");
        questions.add(1, "1The black and yellow make me think of honey");
        questions.add(2, "3The yellow items contrast nicely against the black in the navbar");
        questions.add(3, "1The black and white give a nice, elegant feel to the page");
        questions.add(4, "4I wish the call to action had less contrast");
        questions.add(5, "2The colors feel right but I'm not sure why");
        questions.add(6, "5I don't like it, I think the logo needs more contrast");

        //shuffle order of questions
        Collections.shuffle(questions);



        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_critique_test);

        buttons[0] = (ImageView)findViewById(R.id.imageView);
        buttons[1] = (ImageView)findViewById(R.id.imageView2);
        buttons[2] = (ImageView)findViewById(R.id.imageView3);
        buttons[3] = (ImageView)findViewById(R.id.imageView4);
        buttons[4] = (ImageView)findViewById(R.id.imageView5);
        buttons[5] = (ImageView)findViewById(R.id.imageView6);



        getNewQuestion();
    }



    public void getNewQuestion(){

        String mytext = questions.get(questionNum);
        correctAnswer = Integer.parseInt(mytext.substring(0,1));
        mytext = mytext.substring(1, mytext.length());

        // globally
        TextView questionTextView = (TextView)findViewById(R.id.textView2);

        //in your OnCreate() method
        questionTextView.setText("\""+mytext+"\"");
//        questionTextView.setText("");

        questionNum++;
        if(questionNum >= questions.size()){
            questionNum = 0;
            Log.d("getNewQuestion", "reached end of questions array");
        }
    }

    public void checkAnswer(View view){
        int givenAnswer = Integer.parseInt(view.getTag().toString());
        Log.d("test tag", givenAnswer + "");
        boolean correct = false;
        if(givenAnswer == correctAnswer){
            Log.d("checkAnswer", "correct answer "+givenAnswer + " "+ correctAnswer);
            correct = true;
        }
        else{
            Log.d("checkAnswer", "wrong answer "+givenAnswer+" "+correctAnswer);
        }

        //textView6
        final TextView feedbackText = (TextView)findViewById(R.id.textView6);
        final TextView questionTextView = (TextView)findViewById(R.id.textView2);

        if(correct){ //correct feedback
            feedbackText.setTextColor(Color.parseColor("#ff2a7e2a"));
            switch(correctAnswer){
                case 1:
                    feedbackText.setText("monkey");
                    break;
                case 2:
                    feedbackText.setText("balls2");
                    break;
                case 3:
                    feedbackText.setText("dicks3");
                    break;
                case 4:
                    feedbackText.setText("nunus4");
                    break;
                case 5:
                    feedbackText.setText("pussycat5");
                    break;
                case 6:
                    feedbackText.setText("vuvuzela6");
                    break;
            }
        }
        else if(!correct){ //wrong feedback
            feedbackText.setTextColor(Color.parseColor("#CC2020"));
            switch(correctAnswer){
                case 1:
                    feedbackText.setText("Wrong! monkey");
                    break;
                case 2:
                    feedbackText.setText("Wrong! balls2");
                    break;
                case 3:
                    feedbackText.setText("Wrong! dicks3");
                    break;
                case 4:
                    feedbackText.setText("Wrong! nunus4");
                    break;
                case 5:
                    feedbackText.setText("Wrong! pussycat5");
                    break;
                case 6:
                    feedbackText.setText("Wrong! vuvuzela6");
                    break;
            }
        }

        //disable clickable on buttons
        for(int i = 0; i < buttons.length; i++){
            buttons[i].setClickable(false);
        }


        final Animation in = new AlphaAnimation(0.0f, 1.0f);
        in.setDuration(500);
        final Animation in2 = new AlphaAnimation(0.0f, 1.0f);
        in2.setDuration(500);

        final Animation out = new AlphaAnimation(1.0f, 0.0f);
        out.setDuration(500);

        out.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationEnd(Animation animation) {
                feedbackText.setText("");
                for(int i = 0; i < buttons.length; i++){
                    buttons[i].setClickable(true);
                }
//                feedbackText.startAnimation(in);
                getNewQuestion();
                questionTextView.startAnimation(in2);


            }
            public void onAnimationRepeat(Animation animation){}
            public void onAnimationStart(Animation animation){}
        });
        in.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationEnd(Animation animation) {
            //timeout for 3 seconds, then getNewQuestion()
                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                //re-enable clickable on the buttons
                                feedbackText.startAnimation(out);
                                questionTextView.startAnimation(out);
                            }
                        },
                        3000);
            }
            public void onAnimationRepeat(Animation animation){}
            public void onAnimationStart(Animation animation){}
        });


        feedbackText.startAnimation(in);




    }
}