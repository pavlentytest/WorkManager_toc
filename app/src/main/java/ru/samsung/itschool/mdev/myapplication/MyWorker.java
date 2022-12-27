package ru.samsung.itschool.mdev.myapplication;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyWorker extends Worker {

    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d("RRR", "start doWork()");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("RRR","completed!");
        Data output = new Data.Builder()
                .putString(MainActivity.KEYA, "Hello from worker!")
                .putInt(MainActivity.KEYB, 10)
                .build();
        return Worker.Result.success(output);
    }
}
