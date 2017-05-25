package com.org.cycleinthread;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);

        // NOTE: Обычный запуск через execute, при этом таски заносятся в очередь и
        // пока одна не завершится, другая ожидает
//        new AsyncRequest().execute();

        // NOTE: в отличии от execute, потоки запускаются сразу все
        new AsyncRequest().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new AsyncRequest2().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    class AsyncRequest extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            while (true) {
                // call onProgressUpdate
                publishProgress();

                try {
                    Thread.sleep(1000);

                } catch (Exception e) {

                }
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            textView.setText(timeStamp);
        }
    }

    class AsyncRequest2 extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            while (true) {
                // call onProgressUpdate
                publishProgress();

                try {
                    Thread.sleep(3000);

                } catch (Exception e) {

                }
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            textView2.setText(timeStamp);
        }
    }
}
