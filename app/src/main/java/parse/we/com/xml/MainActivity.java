package parse.we.com.xml;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends Activity {
    private Button btnParse;
    private ListView listContents;
    String xmlData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listContents = (ListView) findViewById(R.id.listContents);
        btnParse = (Button) findViewById(R.id.btnParse);

        btnParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseApplications parse = new ParseApplications(xmlData);
                boolean operationStatus = parse.process();
                if (operationStatus) {
                    ArrayList<Application> applicationsList = parse.getApplicationArrayList();

                    ArrayAdapter<Application> adapter = new ArrayAdapter<Application>(MainActivity.this, R.layout.list_item, applicationsList);
                    listContents.setVisibility(listContents.VISIBLE);
                    listContents.setAdapter(adapter);
                } else {
                    Log.d("MainActivity", "Error parsing files");
                }
            }
        });

        new DownloadData().execute("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=25/xml");
    }

    private class DownloadData extends AsyncTask<String, Void, String> {

        String data;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... urls) {
            try {
                data = downloadXML(urls[0]);
            } catch (IOException e) {
                return "Unable to download XML files.";
            }
            return null;
        }

        private String downloadXML(String theUrl) throws IOException {
            int BUFFER_SIZE = 2000;
            InputStream is = null;

            String xmlContent = "";

            try {
                URL url = new URL(theUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(10000);
                connection.setConnectTimeout(15000);
                connection.setRequestMethod("GET"); //GET METHOD
                connection.setDoInput(true);
                //check status code
                int respone = connection.getResponseCode();
                Log.d("STATUS CODE", "The responed is : " + respone);
                is = connection.getInputStream();

                InputStreamReader isr = new InputStreamReader(is);
                int charRead;
                char[] inputBuffer = new char[BUFFER_SIZE];
                try {
                    while ((charRead = isr.read(inputBuffer)) > 0) {
                        String readString = String.copyValueOf(inputBuffer, 0, charRead);
                        xmlContent += readString;
                        inputBuffer = new char[BUFFER_SIZE];
                    }
                    //result xml
                    return xmlContent;
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            } finally {
                if (is != null) {
                    is.close();
                }
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //Log.d("onPost", data);
            xmlData = data;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}