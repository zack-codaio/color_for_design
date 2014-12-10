package edu.cmu.zaman.colorintent;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by zackaman on 12/8/14.
 */
public class ColorAssociations extends Activity {

    private String[] words = new String[6];
    private int[] correctAnswer = new int[6];
    private String[] explanation = new String[6];
    private int whichQuestion = 0;
    private TextView feedbackText;
    private TextView mainText;
    private TableRow[] colorButtons = new TableRow[6];
    private int questionsCorrect = 0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.color_associations);

        feedbackText = (TextView)findViewById(R.id.textView10);
        mainText = (TextView)findViewById(R.id.textView9);

        words[0] = "Royalty";
        correctAnswer[0] = 2;
        explanation[0] = "Purple has typically been associated with royalty, due to its rare dye only being affordable by the upper class.";
        colorButtons[0] = (TableRow)findViewById(R.id.color0);

        words[1] = "Passion";
        correctAnswer[1] = 0;
        explanation[1] = "Red is a powerful, emotionally charged color. It is the color of love and hearts but also the power of blood and war.";
        colorButtons[1] = (TableRow)findViewById(R.id.color1);

        words[2] = "Corporate";
        correctAnswer[2] = 1;
        explanation[2] = "Blue is often seen as a corporate color, due to it's associations with trust and stability. It is heavily used by companies such as Facebook and IBM.";
        colorButtons[2] = (TableRow)findViewById(R.id.color2);


        words[3] = "Growth";
        correctAnswer[3] = 5;
        explanation[3] = "Green is most typically associated with nature and the vitality of the natural world.";
        colorButtons[3] = (TableRow)findViewById(R.id.color3);

        words[4] = "Joy";
        correctAnswer[4] = 3;
        explanation[4] = "Yellow is often associated with sunshine and a bubbling, energetic feeling.";
        colorButtons[4] = (TableRow)findViewById(R.id.color4);

        words[5] = "Energy";
        correctAnswer[5] = 4;
        explanation[5] = "Orange is a very visible, energetic color. It is warm and inviting without the deep intensity of red and more serious than yellow.";
        colorButtons[5] = (TableRow)findViewById(R.id.color5);


//        words[6] = "Royalty";
//        correctAnswer[6] = 2;
//        explanation[6] = "Purple has typically been associated with royalty, due to its rare dye only being affordable by the upper class.";




    }


    public void new_question(){
        //increment counter

        whichQuestion++;
        if(whichQuestion == words.length){
            //go to next screen
            Intent intent = new Intent(getApplicationContext(), Associations2.class);


            //pass results to the next step, or maybe display on here

            Log.d("intent.putExtra test", questionsCorrect+"");
            Log.d("intent.putExtra test", words.length + "");

                intent.putExtra("questionsCorrect", Integer.toString(questionsCorrect));
                intent.putExtra("numQuestions", Integer.toString(words.length));

            startActivity(intent);
        }
        else{
            feedbackText.setTextColor(Color.parseColor("#aaaaaa"));
            feedbackText.setText("Pick the color that best matches the word.");
            mainText.setText(words[whichQuestion]);
        }

    }

    public void color_click(View view){
        final int givenAnswer = Integer.parseInt(view.getTag().toString());
        Log.d("test tag", givenAnswer + "");





        final Animation in = new AlphaAnimation(0.0f, 1.0f);
        in.setDuration(500);

        final Animation in2 = new AlphaAnimation(0.0f, 1.0f);
        in2.setDuration(500);

        final Animation out = new AlphaAnimation(1.0f, 0.0f);
        out.setDuration(500);

        final Animation out2 = new AlphaAnimation(1.0f, 0.0f);
        out2.setDuration(500);

        final Animation dimColor = new AlphaAnimation(1.0f, 0.2f);
        dimColor.setDuration(500);

        final Animation reColor = new AlphaAnimation(0.2f, 1.0f);
        reColor.setDuration(500);


        out.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                //check to see if it's right
                if(givenAnswer == correctAnswer[whichQuestion]){
                    //correct
                    Log.d("color test", "correct");
                    //change text color to green
                    //change text to Correct!
                    feedbackText.setTextColor(Color.parseColor("#ff2a7e2a"));
                    feedbackText.setText("Correct!");
                    questionsCorrect++;
                }
                else{
                    //false
                    Log.d("color test", "false");
                    //change text color to red
                    //change text to Incorrect
                    feedbackText.setTextColor(Color.parseColor("#CC2020"));
                    feedbackText.setText("Incorrect.");
                }

                //change main text
                mainText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
                mainText.setText(explanation[whichQuestion]);

                //give border to selection

                //fade all but right answer to gray
                for(int i = 0; i < colorButtons.length; i++){
                    if(i != correctAnswer[whichQuestion]){
//                        colorButtons[i].startAnimation(dimColor);
                        colorButtons[i].setBackgroundColor(Color.parseColor("#AAAAAA"));
                    }
                }

                mainText.startAnimation(in);
                feedbackText.startAnimation(in);



            }
            public void onAnimationRepeat(Animation animation){}
            public void onAnimationStart(Animation animation){
                //set clickable to false on all buttons
                for(int i = 0; i < colorButtons.length; i++){
                        colorButtons[i].setClickable(false);
                }
            }
        });

        in.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                //timeout for 3 seconds, then getNewQuestion()
                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                //re-enable clickable on the buttons
                                feedbackText.startAnimation(out2);
                                mainText.startAnimation(out2);
                            }
                        },
                        3000);
            }
            public void onAnimationRepeat(Animation animation){}
            public void onAnimationStart(Animation animation){}
        });

        out2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                feedbackText.setText("Pick the color that best matches the word.");
                mainText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);


                new_question();
                    mainText.startAnimation(in2);

                //remove border from selection
                //fade all but right answer from gray to colored
//                for(int i = 0; i < colorButtons.length; i++){
//                    if(i != correctAnswer[whichQuestion]){
//                        colorButtons[i].startAnimation(reColor);
//                    }
//                }
                colorButtons[0].setBackgroundColor(Color.parseColor("#C11A1A"));
                colorButtons[1].setBackgroundColor(Color.parseColor("#2B6DEE"));
                colorButtons[2].setBackgroundColor(Color.parseColor("#9915EB"));
                colorButtons[3].setBackgroundColor(Color.parseColor("#E4D533"));
                colorButtons[4].setBackgroundColor(Color.parseColor("#ED733A"));
                colorButtons[5].setBackgroundColor(Color.parseColor("#26C52A"));



            }
            public void onAnimationRepeat(Animation animation){}
            public void onAnimationStart(Animation animation){}
        });

        in2.setAnimationListener(new Animation.AnimationListener(){
            public void onAnimationEnd(Animation animation) {
                //set clickable to true on all buttons
                for(int i = 0; i < colorButtons.length; i++){
                    colorButtons[i].setClickable(true);
                }
            }
            public void onAnimationRepeat(Animation animation){}
            public void onAnimationStart(Animation animation){}
        });

        feedbackText.startAnimation(out);
        mainText.startAnimation(out);


    }
}