package com.example.metrolink_stl_android;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: mpmenne
 * Date: 9/16/13
 * Time: 10:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class SystemTime {

    // just for testing... this is bad.  use a mocking library
    public static Date overrideDate;

    public Date getCurrentTime() {
        return overrideDate == null ? new Date() : overrideDate;
    }


}
