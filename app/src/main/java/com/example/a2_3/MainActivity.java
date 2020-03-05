package com.example.a2_3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Integer> {
    private ProgressBar pb;
    private TextView tv;
    private Button bt;
    private Loader<Integer> loader;
    public final String TAG = this.getClass().getSimpleName();

    void getViews() {
        pb = findViewById(R.id.pb);
        tv = findViewById(R.id.tv);
        bt = findViewById(R.id.bt);
    }

    void setOnViews() {
        bt.setEnabled(false);
        pb.setVisibility(View.VISIBLE);
        tv.setText("Loading..");
    }

    @NonNull
    @Override
    public Loader<Integer> onCreateLoader(int i, @Nullable Bundle bundle) {
        Log.e(TAG, "onCreateLoader");
        Loader<Integer> mLoader = new MyAsyncLoader(this, Bundle.EMPTY);
        getViews();
        bt.setEnabled(false);
        pb.setVisibility(View.VISIBLE);
        tv.setText("Loading..");
        return mLoader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Integer> loader, Integer integer) {
        Log.e(TAG, "onLoadFinished " + integer.toString());
        getViews();
        bt.setEnabled(true);
        pb.setVisibility(View.INVISIBLE);
        tv.setText("Ready");
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Integer> loader) {
        Log.e(TAG, "onLoaderReset");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getViews();
        loader = getSupportLoaderManager().getLoader(1);


        Log.e(TAG, "hasRunning " + String.valueOf(getSupportLoaderManager().hasRunningLoaders()));
        Log.e(TAG, "hasRunning " + getSupportLoaderManager().getLoader(1));
        if (loader != null) {
            Log.e(TAG, String.valueOf(getSupportLoaderManager().getLoader(1).getContext()));
            getViews();
            setOnViews();
            getSupportLoaderManager().initLoader(1, Bundle.EMPTY, MainActivity.this);
        }
        View.OnClickListener btOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportLoaderManager().restartLoader(1, Bundle.EMPTY, MainActivity.this).forceLoad();
            }
        };
        bt.setOnClickListener(btOnClickListener);
    }

    class MyTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                TimeUnit.MILLISECONDS.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            bt.setEnabled(false);
            pb.setVisibility(View.VISIBLE);
            tv.setText("Loading..");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            bt.setEnabled(true);
            pb.setVisibility(View.INVISIBLE);
            tv.setText("Ready");
        }
    }
}
