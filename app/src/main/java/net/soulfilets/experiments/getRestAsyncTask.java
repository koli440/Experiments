package net.soulfilets.experiments;

import android.os.AsyncTask;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by soulfilets on 27.2.2015.
 */
class GetRestAsyncTask extends AsyncTask<String, Integer, Long>
{
    String text;

    protected Long doInBackground() {
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://www.seznam.cz");
        try {
            HttpResponse response = client.execute(request);

            // Get the response
            BufferedReader rd = new BufferedReader
                    (new InputStreamReader(response.getEntity().getContent()));

            String line = "";

            while ((line = rd.readLine()) != null) {
                tv.append(line);
            }
        }
        catch (Exception e)
        {
            tv.setText(e.getLocalizedMessage());
        }
    }


}