package com.example.hanifsugiyanto.firebasee;

import android.app.Application;
import android.os.Bundle;

import com.firebase.client.Firebase;

/**
 * Created by hanifsugiyanto on 01/11/15.
 */
public class BaseApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);





    }
}
