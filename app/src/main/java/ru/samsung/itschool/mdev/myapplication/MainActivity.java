package ru.samsung.itschool.mdev.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private OneTimeWorkRequest work;
    public static final String KEYA = "keyA";
    public static final String KEYB = "keyB";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        work = new OneTimeWorkRequest.Builder(MyWorker.class).build();
        WorkManager.getInstance(this).enqueue(work);
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(work.getId()).observe(
                MainActivity.this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(WorkInfo workInfo) {
                        if(workInfo != null) {
                            Log.d("RRR", "State: " + workInfo.getState());
                            String message = workInfo.getOutputData().getString(KEYA);
                            int i = workInfo.getOutputData().getInt(KEYB,0);
                            Log.d("RRR","message: "+message+"; i: "+i);
                        }

                    }
                }
        );

    }
}