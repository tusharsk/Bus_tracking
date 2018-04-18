package com.example.tusharsk.bus_tracking;


import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.suitebuilder.TestMethod;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PersonalInfo_show extends AppCompatActivity {

    TextView tvname,tvmobile,tvemail,tvpassword,tvgender,tvaddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info_show);
        tvname=(TextView) findViewById(R.id.tvname);
        tvmobile=(TextView) findViewById(R.id.tvmobile);
        tvemail=(TextView) findViewById(R.id.tvemail);
        tvpassword=(TextView) findViewById(R.id.tvpassword);
        tvgender=(TextView) findViewById(R.id.tvgender);
        tvaddress=(TextView) findViewById(R.id.tvaddress);
        Toast.makeText(this," id "+SaveSettings.UserID,Toast.LENGTH_SHORT).show();
        String url="https://anubhavaron000001.000webhostapp.com/bus_tracking_show.php?name="+SaveSettings.UserID;
        new MyAsyncTaskgetNews().execute(url);
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


                if (json.getString("msg").equalsIgnoreCase("Yes")) {


                    JSONArray jsonArray=new JSONArray(json.getString("info"));
                    JSONObject jsonObject=jsonArray.getJSONObject(0);

                    //Toast.makeText(getApplicationContext(),"t"+jsonObject.getString("name"),Toast.LENGTH_SHORT).show();
                    tvname.setText("Name:   "+jsonObject.getString("name"));
                    tvmobile.setText("Mobile:    "+jsonObject.getString("phone_no"));
                    tvemail.setText( "Email:     "+jsonObject.getString("email"));
                    tvpassword.setText("Password:   "+jsonObject.getString("password"));
                    tvgender.setText("Gender:     "+jsonObject.getString("sex"));
                    tvaddress.setText("Address:     "+jsonObject.getString("address"));
                    Toast.makeText(getApplicationContext(),"Details!!",Toast.LENGTH_SHORT).show();
                    //login
                }

                if (json.getString("msg").equalsIgnoreCase("No")) {
                    Toast.makeText(getApplicationContext(),"Email Already Registered!",Toast.LENGTH_SHORT).show();
                }

            } catch (Exception ex) {
                //Log.d("er",  ex.getMessage());
            }


        }

        protected void onPostExecute(String  result2){


        }

    }

}