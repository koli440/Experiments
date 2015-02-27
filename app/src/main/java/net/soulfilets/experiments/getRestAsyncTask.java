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
    String text = "";

    @Override
    protected Long doInBackground(String... params) {
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://private-2d9dc-hktest.apiary-mock.com/notes");
        try {
            HttpResponse response = client.execute(request);

            // Get the response
            BufferedReader rd = new BufferedReader
                    (new InputStreamReader(response.getEntity().getContent()));
            String line = "";
            while ((line = rd.readLine()) != null) {
                text += line;
            }

        }
        catch (Exception e)
        {
            return Long.getLong("-1");
        }
        return Long.getLong("1");
    }

    public String getStringResponse()
    {
        return text;
    }
}