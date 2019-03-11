package be.ehb.demothreading.util;

import android.os.AsyncTask;
import android.widget.ProgressBar;

public class LoadingTask extends AsyncTask<Void, Integer, Void> {

    private ProgressBar pb;

    public LoadingTask(ProgressBar pb) {
        this.pb = pb;
    }

    @Override
    protected Void doInBackground(Void... params) {

        for( int i = 0; i <= 100; i++ ){

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            publishProgress(i);

        }

        return null;
    }

  @Override
   protected void  onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        for (Integer i : values)
            pb.setProgress(i);

  }
}
