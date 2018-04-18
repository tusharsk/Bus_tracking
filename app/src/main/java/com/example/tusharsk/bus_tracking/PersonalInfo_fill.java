package com.example.tusharsk.bus_tracking;


import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PersonalInfo_fill extends AppCompatActivity {


    SaveSettings saveSettings;
    TextView tvname;
    EditText sex,phone,address;
    String dobs,bgs,cgs;
    Button  bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        saveSettings=new SaveSettings(getApplicationContext());
        String username=saveSettings.Username();
        String flag=SaveSettings.flag;


        if(!(flag.matches("null")))
        {
            Intent intent=new Intent(getApplicationContext(),PersonalInfo_show.class);
            startActivity(intent);
            finish();
        }

        setContentView(R.layout.activity_personal_info_fill);
        //tvname.setText(username);
        bt=(Button) findViewById(R.id.tvsave);
        sex=(EditText) findViewById(R.id.tvsex);
        phone=(EditText) findViewById(R.id.tvphone);
        address=(EditText) findViewById(R.id.tvaddress);

    }

    public void Save(View view) {
        Toast.makeText(getApplicationContext(), "Details !", Toast.LENGTH_LONG).show();
        dobs=sex.getText().toString();
        bgs=phone.getText().toString();
        cgs=address.getText().toString();

        if(!dobs.matches("")&&!bgs.matches("")&&!cgs.matches("")){
            String url="https://anubhavaron000001.000webhostapp.com/bus_tracking_personal_info.php?name="+dobs+"&phone="+bgs+"&address"+cgs+"&flag=1"+"&name="+SaveSettings.UserID;
            bt.setEnabled(false);
            new MyAsyncTaskgetNews().execute(url);
        }
    }


    public class MyAsyncTaskgetNews extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            //before works
        }
        @Override
        protected String  doInBackground(String... params) {
            // TODO Auto-generated method stub
            try {
                String NewsData;
                //define the url we have to connect with
                URL url = new URL(params[0]);
                //make connect with url and send request
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                //waiting for 7000ms for response
                urlConnection.setConnectTimeout(7000);//set timeout to 5 seconds

                try {
                    //getting the response data
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    //convert the stream to string
                    Operations operations=new Operations(getApplicationContext());
                    NewsData = operations.ConvertInputToStringNoChange(in);
                    //send to display data
                    publishProgress(NewsData);
                } finally {
                    //end connection
                    urlConnection.disconnect();
                }

            }catch (Exception ex){}
            return null;
        }
        protected void onProgressUpdate(String... progress) {

            try {
                JSONObject json= new JSONObject(progress[0]);
                //display response data





                //login


            } catch (Exception ex) {
                //Log.d("er",  ex.getMessage());
            }


        }

        protected void onPostExecute(String  result2){
            Toast.makeText(getApplicationContext(),"Details Filled!", Toast.LENGTH_LONG).show();
            SaveSettings.flag="1";
            Intent intent=new Intent(getApplicationContext(),PersonalInfo_show.class);
            startActivity(intent);
            finish();


        }

    }
}
