package com.example.stationcode;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {


    EditText m;
    Button b;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m= findViewById(R.id.editText);
        b = findViewById(R.id.button);



        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }

    public void code(View v) throws IOException, JSONException {
        try {
            String a = m.getText().toString() + "/";
            if (a != null) {
                String url = "https://indianrailapi.com/api/v2/StationNameToCode/apikey/bb0c892243d013eab4f9e6afb67eda96/StationName/";


                url = url + a;


                //System.out.println(url);

                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                // optional default is GET


                con.setRequestMethod("GET");
                //int responseCode = con.getResponseCode();
                //System.out.println("\nSending 'GET' request to URL : " + url);
                //System.out.println("Response Code : " + responseCode);

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                //print in String
                //System.out.println(response.toString());
                //Read JSON response and print
                JSONObject myResponse = new JSONObject(response.toString());

//            System.out.println("Response Code- "+myResponse.getString("ResponseCode"));
//            System.out.println("Status - "+myResponse.getString("Status"));
//            System.out.println("Station - "+myResponse.getString("Station"));

                System.out.println("Store Code - " + myResponse);
                JSONObject pilot = myResponse.getJSONObject("Station");

                if (!(pilot.getString("StationCode").equals(null))){
                    t =findViewById(R.id.textView);
                    t.setText( pilot.getString("StationCode"));

                    //Toast.makeText(MainActivity.this, pilot.getString("StationCode"), Toast.LENGTH_LONG).show();
                    }
                else
                    Toast.makeText(MainActivity.this, "Invalid Input", Toast.LENGTH_LONG).show();


                //Toast.makeText(MainActivity.this,url, Toast.LENGTH_LONG).show();

            }
        }
        catch (Exception e)
        {
            Toast.makeText(MainActivity.this, "Invalid Input", Toast.LENGTH_LONG).show();
        }

    }
//    public class Send_HTTP_Request2 {
//        public static void main(String[] args) {
//            try {
//                Send_HTTP_Request2.call_me();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        public static void call_me() throws Exception {
//            String url = "http://api.ipinfodb.com/v3/ip-city/?key=d64fcfdfacc213c7ddf4ef911dfe97b55e4696be3532bf8302876c09ebd06b&ip=74.125.45.100&format=json";
//            URL obj = new URL(url);
//            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//            // optional default is GET
//            con.setRequestMethod("GET");
//            //add request header
//            con.setRequestProperty("User-Agent", "Mozilla/5.0");
//            int responseCode = con.getResponseCode();
//            System.out.println("\nSending 'GET' request to URL : " + url);
//            System.out.println("Response Code : " + responseCode);
//            BufferedReader in = new BufferedReader(
//                    new InputStreamReader(con.getInputStream()));
//            String inputLine;
//            StringBuffer response = new StringBuffer();
//            while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//            }
//            in.close();
//            //print in String
//            System.out.println(response.toString());
//            //Read JSON response and print
//            JSONObject myResponse = new JSONObject(response.toString());
//            System.out.println("result after Reading JSON Response");
//            System.out.println("statusCode- "+myResponse.getString("statusCode"));
//            System.out.println("statusMessage- "+myResponse.getString("statusMessage"));
//            System.out.println("ipAddress- "+myResponse.getString("ipAddress"));
//            System.out.println("countryCode- "+myResponse.getString("countryCode"));
//            System.out.println("countryName- "+myResponse.getString("countryName"));
//            System.out.println("regionName- "+myResponse.getString("regionName"));
//            System.out.println("cityName- "+myResponse.getString("cityName"));
//            System.out.println("zipCode- "+myResponse.getString("zipCode"));
//            System.out.println("latitude- "+myResponse.getString("latitude"));
//            System.out.println("longitude- "+myResponse.getString("longitude"));
//            System.out.println("timeZone- "+myResponse.getString("timeZone"));
//        }
//    }



}
