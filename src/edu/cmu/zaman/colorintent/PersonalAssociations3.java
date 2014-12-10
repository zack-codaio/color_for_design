package edu.cmu.zaman.colorintent;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.os.Environment;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Context;
import android.util.Log;
import android.media.MediaRecorder;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.IOException;
import java.util.Random;

/**
 * Created by zackaman on 12/9/14.
 */

//adapted from https://developer.android.com/guide/topics/media/audio-capture.html
    // Note: The Android Emulator does not have the ability to capture audio, but actual devices are likely to provide these capabilities. (from their page)
public class PersonalAssociations3 extends Activity
{
    private static final String LOG_TAG = "AudioRecordTest";
    private static String mFileName = null;

    private Button mRecordButton = null;
    private MediaRecorder mRecorder = null;

    private Button   mPlayButton = null;
    private MediaPlayer   mPlayer = null;

    private TextView timerText = null;
    private CountDownTimer myTimer = null;

    private boolean finished = false;
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.personal_associations3);
//    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setContentView(R.layout.personal_associations3);



//        textView13 for the timer
        timerText = (TextView)findViewById(R.id.textView13);

        mRecordButton = (Button)findViewById(R.id.mRecordButton);
        mPlayButton = (Button)findViewById(R.id.mPlayButton);



        randomColor();
    }

    private void randomColor(){
        Random rand = new Random();
        int r = rand.nextInt(255);
        int g = rand.nextInt(255);
        int b = rand.nextInt(255);
        int randomColor =  Color.rgb(r, g, b);
        TableRow colorContainer = (TableRow)findViewById(R.id.colorView);
        colorContainer.setBackgroundColor(randomColor);
        finished = false;
    }

    private void onRecord(boolean start) {
        if (start) {
//            startRecording();
        }
//        else if(false){
//            //get new color
//
//        }
        else {
//            stopRecording();
            myTimer.cancel();

        }
    }

    private void onPlay(boolean start) {
        if (start) {
//            startPlaying();
        }

        else {
//            stopPlaying();
        }
    }

    private void startPlaying() {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(mFileName);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
    }

    private void stopPlaying() {
        mPlayer.release();
        mPlayer = null;
    }

    private void startRecording() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        mRecorder.start();
    }

    private void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }

    private boolean mStartRecording = true;

    public void recordClick(View v) {
        onRecord(mStartRecording);
        Button myV = (Button)v;
        if(finished == false) {
            if (mStartRecording) {
                myV.setText("Stop recording");

                //for some reason it stops at 2 if you give it 60000, not sure why
                myTimer = new MyCountDownTimer(62000, 1000);
                myTimer.start();
            } else {
                myV.setText("Start recording");

            }
        }
        else if(finished == true){
            randomColor();
            myV.setText("Start recording");
        }
        mStartRecording = !mStartRecording;
    }

    private boolean mStartPlaying = true;
    public void playClick(View v) {
        onPlay(mStartPlaying);
        Button myV = (Button)v;
        if (mStartPlaying) {
            myV.setText("Stop playing");
        } else {
            myV.setText("Start playing");
        }

        mStartPlaying = !mStartPlaying;
    }

    class MyCountDownTimer extends CountDownTimer {
        private long totalTime;

        public MyCountDownTimer(long startTime, long interval){
            super(startTime, interval);
            totalTime = startTime;
        }

        public void onTick(long timeleft){
            int timeElapsed = (int)(totalTime - timeleft);
            timeElapsed = 60 - (timeElapsed / 1000);
            timerText.setText(Integer.toString(timeElapsed));
//            Log.d("timer tick", timeElapsed + "");
        }

        public void onFinish(){
            //give option to start again, play audio, or return to home screen
            finished = true;
            mRecordButton.setText("New Color");
        }
    }

    public void AudioRecordTest() {
        mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        mFileName += "/audiorecordtest.3gp";
    }



    @Override
    public void onPause() {
        super.onPause();
        if (mRecorder != null) {
            mRecorder.release();
            mRecorder = null;
        }

        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }

    public void next(View v){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}

