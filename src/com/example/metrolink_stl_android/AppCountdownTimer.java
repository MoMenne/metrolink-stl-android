package com.example.metrolink_stl_android;

import android.os.CountDownTimer;
import android.widget.Toast;

/**
 * Created with IntelliJ IDEA.
 * User: mpmenne
 * Date: 9/11/13
 * Time: 8:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class AppCountdownTimer extends CountDownTimer {
    

    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public AppCountdownTimer(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    @Override
    public void onTick(long millisUntilFinished) {

    }

    @Override
    public void onFinish() {
        
    }
}
