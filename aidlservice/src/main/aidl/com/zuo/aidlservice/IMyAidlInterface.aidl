// IMyAidlInterface.aidl
package com.zuo.aidlservice;
import com.zuo.aidlservice.DataBean;
// Declare any non-default types here with import statements

interface IMyAidlInterface {

    DataBean getData();

    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
}