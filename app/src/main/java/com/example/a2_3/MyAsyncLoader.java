package com.example.a2_3;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.concurrent.TimeUnit;

public class MyAsyncLoader extends AsyncTaskLoader {
    public static final String TIME_ARG = "time";
    int time;
    public final String TAG = this.getClass().getSimpleName();

    public MyAsyncLoader (Context context, Bundle bundle) {
        super(context);
        Log.e(TAG, "MyAsyncLoader ");
        time = 5000;
    }

    @Nullable
    @Override
    public Object loadInBackground() {
        Log.e(TAG, "loadInBackground");
        try {
            TimeUnit.MILLISECONDS.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void forceLoad() {
        Log.e(TAG, "forceLoad");
        super.forceLoad();
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        Log.e(TAG, "onStartLoading");
//        forceLoad();
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
        Log.e(TAG, "onStopLoading");
    }
}
