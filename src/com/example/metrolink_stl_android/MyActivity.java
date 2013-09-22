package com.example.metrolink_stl_android;

import android.app.Activity;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import org.joda.time.DateTime;

import java.io.InputStream;
import java.util.Date;

public class MyActivity extends Activity {

    private Button readyToGoButton;
    private CountDownTimer appCountdownTimer;
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
                try {
                    Date nextTrainTime = getNextTrain(R.raw.late_night_shrewsbury, 5);
                    Date currentTime = new SystemTime().getCurrentTime();
                    appCountdownTimer = new TrainCountDownTimer(nextTrainTime.getTime() - currentTime.getTime() - 300000, 1000);
                } catch (NoAvailableTrainException e) {
                    Toast.makeText(getApplicationContext(), R.string.toast_no_more_trains, Toast.LENGTH_LONG).show();
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                appCountdownTimer.start();


            }
        });

    }

    public static String formatTime(long millis) {
        return "" + (millis / 3600000) % 60 + (millis / 60000) % 60 + ":" + secondNumbers(millis);
    }

    private static String secondNumbers(long millis) {
        if (millis / 1000 >= 60) {
            return numberToString(millis / 1000 % 60);
        }
        return numberToString(millis / 1000);
    }

    private static String numberToString(long millis) {
        return millis < 10 ? "0" + millis : "" + millis;
    }

    public Date getNextTrain(int resId) throws NoAvailableTrainException {
        return getNextTrain(resId, 0);
    }


    public Date getNextTrain(int resId, int delay) throws NoAvailableTrainException {

        Date systemTimePlusDelay = new DateTime(new SystemTime().getCurrentTime().getTime()).plusMinutes(delay).toDate();
        String csv = readRaw(resId);
        String[] split = csv.split("\\n");

        for (String s : split) {
            String csvString = s.split(",")[4];
            int hour = Integer.parseInt(csvString.split(":")[0]);
            int minute = Integer.parseInt(csvString.split(":")[1]);
            int second = Integer.parseInt(csvString.split(":")[2]);
            Date csvDate = (new DateTime().withHourOfDay(hour).withMinuteOfHour(minute).withSecondOfMinute(second).toDate());
            if (csvDate.after(systemTimePlusDelay)) {
                return csvDate;
            }
        }
        throw new NoAvailableTrainException();
    }

    private String readRaw(int resId) {
        try {
            Resources res = getResources();
            InputStream in_s = res.openRawResource(resId);

            byte[] b = new byte[in_s.available()];
            in_s.read(b);
            return new String(b);
        } catch (Exception e) {
            // e.printStackTrace();
            //yikes!  ... don't do this.  handle exception
            return "";
        }
    }

    public class TrainCountDownTimer extends CountDownTimer {

        public TrainCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            TextView viewById = (TextView) findViewById(R.id.timerTextView);
            viewById.setText("" + formatTime(millisUntilFinished));
        }

        @Override
        public void onFinish() {
            appCountdownTimer.cancel();
            mPlayer.start();
            TextView viewById = (TextView) findViewById(R.id.timerTextView);
            viewById.setText("");
        }
    }

    public class NoAvailableTrainException extends Exception {
    }
}
