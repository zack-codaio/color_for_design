package edu.cmu.zaman.colorintent;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * Created by zackaman on 11/29/14.
 */
public class CritiqueTest extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {

        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_critique_test);
    }
}