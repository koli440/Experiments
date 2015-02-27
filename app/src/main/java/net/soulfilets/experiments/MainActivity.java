package net.soulfilets.experiments;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;


public class MainActivity extends ActionBarActivity {
    TextView tv;
    TextView errorTv;
    GetRestAsyncTask result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    /** Called when the user touches the button */
    public void getMessage(View view) {
        result = (GetRestAsyncTask) new GetRestAsyncTask().execute("");
    }

    /** Called when the user touches the button */
    public void refreshText(View view)
    {
        String resultString = result.getStringResponse();

        tv = (TextView) findViewById(R.id.text);
        tv.setText("");

        errorTv = (TextView) findViewById(R.id.error);

        JSONArray notes;

        if (resultString != null && resultString != "")
        try {
            JSONObject jsonObject = new JSONObject(resultString);
            notes = jsonObject.getJSONArray("notes");
            for (int i = 0; i < notes.length(); i++) {
                JSONObject c = notes.getJSONObject(i);
                tv.append(c.getString("id"));
                tv.append(" - ");
                tv.append(c.getString("title"));
            }
        }
        catch (Exception e) {
            errorTv.setText(e.getLocalizedMessage());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}