package com.example.metrolink_stl_android;

import android.test.ActivityInstrumentationTestCase2;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p/>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class com.example.metrolink_stl_android.MyActivityTest \
 * com.example.metrolink_stl_android.tests/android.test.InstrumentationTestRunner
 */
public class MyActivityTest extends ActivityInstrumentationTestCase2<MyActivity> {

    public MyActivityTest() {
        super("com.example.metrolink_stl_android", MyActivity.class);
    }

    public void testDontBlowUp() {

    }

    public void testZeroFormat(){
        assertEquals("00:00", MyActivity.formatTime(0));
    }

    public void testOneFormat() {
        assertEquals("00:01", MyActivity.formatTime(1000));
    }

    public void testTenFormat() {
        assertEquals("00:10", MyActivity.formatTime(10000));
    }

    public void testEleventFormat() {
        assertEquals("00:11", MyActivity.formatTime(11000));
    }

    public void testMinute() {
        assertEquals("01:00", MyActivity.formatTime(60000));
    }

    public void testTwoMinute() {
        assertEquals("02:00", MyActivity.formatTime(120000));
    }

    public void testTwoMinutesOneSecond() {
        assertEquals("02:01", MyActivity.formatTime(121000));
    }

    public void testOneMinuteOneSecond() {
        assertEquals("01:01", MyActivity.formatTime(61000));
    }

    public void testTenMinute(){
        assertEquals("10:00", MyActivity.formatTime(3600000));
    }

    public void testElevenMinute() {
        assertEquals("11:00", MyActivity.formatTime(3660000));
    }

    public void testTwoMinutesEighteenSeconds() {
        assertEquals("02:30", MyActivity.formatTime(150000));
    }

    public void testModTest(){
        assertEquals(0, 200%20);
        assertEquals(10, 210%20);
        assertEquals(1, 1%10);
    }
}
