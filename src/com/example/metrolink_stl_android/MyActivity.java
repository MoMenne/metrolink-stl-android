package com.example.metrolink_stl_android;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MyActivity extends Activity {

    private Button readyToGoButton;
    private CountDownTimer appCountdownTimer = new CountDownTimer(secondsToMillis(10), secondsToMillis(1)) {
        @Override
        public void onTick(long millisUntilFinished) {
            Toast.makeText(getApplicationContext(), R.string.toast_test, Toast.LENGTH_LONG).show();
            TextView viewById = (TextView) findViewById(R.id.timerTextView);
            viewById.setText(""+ (millisUntilFinished/1000l));
        }

        @Override
        public void onFinish() {
            mPlayer.start();
        }
    };

    private MediaPlayer mPlayer;


    private static long secondsToMillis(int seconds) {
        return seconds * 1000;
    }

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.music_noise);

        readyToGoButton = (Button) findViewById(R.id.readyToGoButton);
        readyToGoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.toast_test, Toast.LENGTH_LONG).show();
                appCountdownTimer.start();


            }
        });

    }

    public static String formatTime(int millis) {
            return "" +  (millis/3600000) % 60 + (millis/60000) % 60 + ":" + secondNumbers(millis);
    }

    private static String secondNumbers(int millis) {
        if (millis/1000 >= 60) {
            return numberToString(millis/1000 % 60);
        }
        return numberToString(millis / 1000);
    }

    private static String numberToString(int millis) {
        return millis < 10 ? "0" + millis : "" + millis;
    }

}
