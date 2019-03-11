package be.ehb.demothreading;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import be.ehb.demothreading.util.LoadingHandler;
import be.ehb.demothreading.util.LoadingTask;

public class MainActivity extends AppCompatActivity {

    private ProgressBar pbLoading, pbLoadingAsync;
    private TextView tvLoading;
    private LoadingHandler loadingHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pbLoading = findViewById(R.id.pb_loading);
        tvLoading = findViewById(R.id.tv_loading);
        pbLoadingAsync = findViewById(R.id.pb_loading_async);
        loadingHandler = new LoadingHandler(pbLoading, tvLoading);

    }

    public void onStartClicked(View view){
        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++){

                    Message message = new Message();
                    message.arg1 = i;
                    loadingHandler.sendMessage(message);

                    //Schrijf zoiets nooit, enkel om te testen!!!!
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        backgroundThread.start();
    }

    public void onStartAsyncClicked(View view){
        LoadingTask loadingTask = new LoadingTask(pbLoadingAsync);
        loadingTask.execute();
        
    }

}
