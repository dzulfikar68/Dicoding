package com.digitcreativestudio.myservice;

import android.app.Service;
import android.content.Intent;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

import java.lang.ref.WeakReference;

public class OriginService extends Service implements DummyAsyncCallback {

    public static final String ORIGIN_SERVICE = "OriginService";
    public static final String TAG = OriginService.class.getSimpleName();

    public OriginService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(ORIGIN_SERVICE, "OriginService dijalankan");
        Log.e(TAG, "onStartCommand");

        DummyAsync dummyAsync = new DummyAsync(this);
        dummyAsync.execute();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
    }

    @Override
    public void preAsync() {
        Log.e(TAG, "preAsync: Mulai.....");
    }

    @Override
    public void postAsync() {
        Log.e(TAG, "postAsync: Selesai.....");
    }

    private static class DummyAsync extends AsyncTask<Void, Void, Void>{

        WeakReference<DummyAsyncCallback> callback;

        DummyAsync(DummyAsyncCallback callback){
            this.callback = new WeakReference<>(callback);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.e(TAG, "onPreExecute: ");
            callback.get().preAsync();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Log.e(TAG, "doInBackground");
            try{
                Thread.sleep(5000);
                Log.e(TAG, "finishInBackground");
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.e(TAG, "onPostExecute: ");
            callback.get().postAsync();
        }

    }
}
