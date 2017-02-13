package sudheesh.a16mb.com.m_d;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Basic extends AppCompatActivity {

    public static final int c_tout=10000;
    public static final int r_tout=15000;
    TextView tview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);

        tview= (TextView) findViewById(R.id.tphp);

        new AsyncRetrive().execute();

    }

    private class AsyncRetrive extends AsyncTask<String,String,String> {

        ProgressDialog progressDialog=new ProgressDialog(Basic.this);
        HttpURLConnection connection;
        URL url=null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setMessage("\tLoading....");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            try{
                url=new URL("http://sudheesh.16mb.com/php/sudheesh.php");

            }catch (MalformedURLException e){

                e.printStackTrace();
                return e.toString();
            }


            try{
                connection= (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(r_tout);
                connection.setConnectTimeout(c_tout);
                connection.setRequestMethod("GET");
                connection.setDoOutput(true);
            }catch (IOException e){

                e.printStackTrace();
                return e.toString();
            }


            try{
                int response_code=connection.getResponseCode();
                if(response_code == HttpURLConnection.HTTP_OK)
                {
                    InputStream inputStream=connection.getInputStream();
                    BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder result=new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null){
                        result.append(line);
                    }
                    return(result.toString());

                }
                else
                {
                    return ("Unsuccesful");
                }
            }catch (IOException e){
                e.printStackTrace();
                return e.toString();
            }

                   }

        @Override
        protected void onPostExecute(String s) {

            progressDialog.dismiss();
            tview.setText(s.toString());


        }
    }
}
