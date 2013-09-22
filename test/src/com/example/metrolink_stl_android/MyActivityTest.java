package com.example.metrolink_stl_android;

import android.test.ActivityInstrumentationTestCase2;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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

    //    public static final Date NOON = new DateTime().withHourOfDay(12).toDate();
    public final static Date NOON = dateTimeGenerator(0,0,0);
    public final static Date NOON05 = dateTimeGenerator(0,5,0);
    private final static Date NOON15 = dateTimeGenerator(0,15,0);
    public final static Date NINE05 = dateTimeGenerator(9,5,0);

    public MyActivityTest() {
        super(MyActivity.class);
    }

    private MyActivity activity;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        activity = this.getActivity();
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

    public void testDontBlowUpReturnSomething() throws MyActivity.NoAvailableTrainException {

        Date nextTrain = activity.getNextTrain(R.raw.test);

        assertNotNull(nextTrain);
    }

    public void testBoardNowTime() throws MyActivity.NoAvailableTrainException {
        MyActivity myActivity = new MyActivity();
        SystemTime.overrideDate = NOON;

        Date nextTrain = activity.getNextTrain(R.raw.test);

        assertTrue(sameTime(NOON, nextTrain));
    }

    public void testBoardInFifteenMinutes() throws MyActivity.NoAvailableTrainException {
        SystemTime.overrideDate = NOON05;

        Date nextTrain = activity.getNextTrain(R.raw.test);

        assertTrue(sameTime(NOON15, nextTrain));
    }

    public void testNoTrainToBoard() {
//        MyActivity myActivity = new MyActivity();
        SystemTime.overrideDate = NINE05;

        try {
        Date nextTrain = activity.getNextTrain(R.raw.test);
            fail();
        } catch (MyActivity.NoAvailableTrainException expect) {
            // pass
        }

    }

    public void test5MinDelay() throws MyActivity.NoAvailableTrainException {
        SystemTime.overrideDate = NOON;

        Date nextTrain = activity.getNextTrain(R.raw.test, 5);

        assertTrue(sameTime(NOON15, nextTrain));
    }

    private static boolean sameTime(Date date, Date anotherDate) {
        System.out.println(date.toString());
        System.out.println(anotherDate.toString());
        boolean b = Math.abs(date.getTime() - anotherDate.getTime()) < seconds(5);
        if (!b) assertEquals(date.toString(), anotherDate.toString());
        return b;
    }

    private static int seconds(int milliseconds) {
        return milliseconds * 1000;
    }

    public static Date dateTimeGenerator(int hour, int minute, int second){
        Calendar genCalendar = Calendar.getInstance();
        genCalendar.setTime(new Date());
        genCalendar.set(Calendar.HOUR, hour);
        genCalendar.set(Calendar.MINUTE, minute);
        genCalendar.set(Calendar.SECOND, second);
        return genCalendar.getTime();
    }
}
