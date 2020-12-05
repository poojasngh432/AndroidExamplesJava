package com.example.tutorialsproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;

import com.example.tutorialsproject.R;

public class RotationAsyncActivity extends Activity {

    private RotationAwareTask task = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotation_async);

        task = (RotationAwareTask)getLastNonConfigurationInstance();

        if (task == null){
            task = new RotationAwareTask(this);
            task.execute();
        } else {
            task.attach(this);
            updateProgress(task.getProgress());

            if (task.getProgress() >= 100) {
                markAsDone();
            }
        }
    }

    @Override
    public Object onRetainNonConfigurationInstance() {
        task.detach();

        return(task);
    }

    void updateProgress(int progress) {

    }

    void markAsDone() {

    }

    static class RotationAwareTask extends AsyncTask<Void, Void, Void> {
        RotationAsyncActivity activity = null;
        int progress = 0;

        RotationAwareTask(RotationAsyncActivity activity) {
            attach(activity);
        }

        @Override
        protected Void doInBackground(Void... unused) {
            for (int i=0;i<20;i++) {
                SystemClock.sleep(500);
                publishProgress();
            }

            return(null);
        }

        @Override
        protected void onProgressUpdate(Void... unused) {
            if (activity == null) {
                Log.w("RotationAsync", "onProgressUpdate() skipped -- no activity");
            }
            else {
                progress+=5;
                activity.updateProgress(progress);
            }
        }

        @Override
        protected void onPostExecute(Void unused) {
            if (activity==null) {
                Log.w("RotationAsync", "onPostExecute() skipped -- no activity");
            }
            else {
                activity.markAsDone();
            }
        }

        void detach() {
            activity=null;
        }

        void attach(RotationAsyncActivity activity) {
            this.activity = activity;
        }

        int getProgress() {
            return(progress);
        }
    }
}