package sudheesh.a16mb.com.m_d;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static sudheesh.a16mb.com.m_d.Basic.c_tout;
import static sudheesh.a16mb.com.m_d.Basic.r_tout;


/**
 * Created by KUTTAN on 24-11-2016.
 */

public class dbview_java extends Activity {
    InputStream inputStream=null;
    HttpURLConnection connection;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dbphp);



        String result=null;

        URL url=null;
        try{
            url=new URL("http://sudheesh.16mb.com/php/dbview.php");

        }catch (MalformedURLException e){

        }


        try{
            connection= (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(r_tout);
            connection.setConnectTimeout(c_tout);
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
        }catch (IOException e){

        }





        try{
            int response_code=connection.getResponseCode();
            if(response_code == HttpURLConnection.HTTP_OK) {

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));//here come some extra codes (inputstream,"iso - 8859 - 1"),8)
                StringBuilder stringBuilder = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }
                inputStream.close();
                result = stringBuilder.toString();

            }else {}
        }catch (Exception e){Log.e("log_tag","connvertion Error");}


        try{

            JSONArray jsonArray=new JSONArray(result);
            TableLayout tableLayout= (TableLayout) findViewById(R.id.table);
            tableLayout.removeAllViewsInLayout();
            int flag = 1;
            for(int i=-1; i<jsonArray.length() -1;i++)
            {
                TableRow tableRow=new TableRow(dbview_java.this);
                tableLayout.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
                if(flag == 1){

                    TextView textView=new TextView(dbview_java.this);
                    textView.setText("ID");
                    textView.setTextColor(Color.RED);
                    textView.setTextSize(15);
                    tableRow.addView(textView);

                    TextView textView1=new TextView(dbview_java.this);
                    textView1.setPadding(10,0,0,0);
                    textView1.setText("NAME");
                    textView1.setTextColor(Color.RED);
                    textView1.setTextSize(15);
                    tableRow.addView(textView1);

                    TextView textView2=new TextView(dbview_java.this);
                    textView2.setPadding(10,0,0,0);
                    textView2.setText("PHONE");
                    textView2.setTextColor(Color.RED);
                    textView2.setTextSize(15);
                    tableRow.addView(textView2);

                    TextView textView3=new TextView(dbview_java.this);
                    textView3.setPadding(10,0,0,0);
                    textView3.setText("GENDER");
                    textView3.setTextColor(Color.RED);
                    textView3.setTextSize(15);
                    tableRow.addView(textView3);

                    final View view=new View(dbview_java.this);

                    view.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,2));
                    view.setBackgroundColor(Color.BLUE);
                    tableLayout.addView(view);
                    flag=0;


                }else
                {
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    TextView textView=new TextView(dbview_java.this);
                    String ss=String.valueOf(jsonObject.getInt("id"));
                    textView.setText(ss);
                    textView.setTextColor(Color.YELLOW);
                    textView.setTextSize(15);
                    tableRow.addView(textView);

                    TextView textView1=new TextView(dbview_java.this);
                    textView1.setPadding(10,0,0,0);
                    String ss1=String.valueOf(jsonObject.getInt("name"));
                    textView1.setText(ss);
                    textView1.setTextColor(Color.BLACK);
                    textView1.setTextSize(15);
                    tableRow.addView(textView1);

                    TextView textView2=new TextView(dbview_java.this);
                    textView2.setPadding(10,0,0,0);
                    String ss2=String.valueOf(jsonObject.getInt("phone"));
                    textView2.setText(ss);
                    textView2.setTextColor(Color.BLACK);
                    textView2.setTextSize(15);
                    tableRow.addView(textView2);

                    TextView textView3=new TextView(dbview_java.this);
                    textView3.setPadding(10,0,0,0);
                    String ss3=String.valueOf(jsonObject.getInt("gender"));
                    textView3.setText(ss);
                    textView3.setTextColor(Color.BLACK);
                    textView3.setTextSize(15);
                    tableRow.addView(textView3);
                    tableLayout.addView(tableRow);

                    final View view=new View(dbview_java.this);
                    view.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,1));
                    view.setBackgroundColor(Color.WHITE);
                    tableRow.addView(view);
                }
            }
        }catch (JSONException e){Log.e("log_tag","error Parsing the Data");}





    }
}
