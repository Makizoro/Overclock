package com.example.linkingyou;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

public class ProfActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prof_layout);

        String GroupName = getIntent().getStringExtra("Club_ID");
        TextView txtName = (TextView) findViewById(R.id.textv);
        txtName.setText(GroupName);
        ContentValues params = new ContentValues();
        params.put("GroupName", GroupName);

        @SuppressLint("StaticFieldLeak") AsyncHTTPPost AsyncHttpPost = new AsyncHTTPPost(
                "http://192.168.110.1/project/getDesc.php", params) {
            @Override
            protected void onPostExecute(String output) {
                TextView assigntxt = (TextView) findViewById(R.id.textv2);
                assigntxt.setText(output);
            }
        };
        AsyncHttpPost.execute();

        //TODO: Gets all Group Events.
        @SuppressLint("StaticFieldLeak") AsyncHTTPPost aasyncHttpPost = new AsyncHTTPPost(
                "http://192.168.110.1/project/getEvents.php", params) {
            @Override
            protected void onPostExecute(String output) {
                LinearLayout l = (LinearLayout) findViewById(R.id.list2);
                Log.d("output", output);
                try {
                    JSONArray ja = new JSONArray(output);
                    for (int i = 0; i < ja.length(); i++) {
                        JSONObject jo = (JSONObject) ja.get(i);
                        LinearLayout item = (LinearLayout) getLayoutInflater().inflate(R.layout.list_item, null);

                        TextView assigntxt = (TextView) item.findViewById(R.id.textview1);
                        assigntxt.setText(jo.getString("Events"));

                        l.addView(item);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        aasyncHttpPost.execute();
    }
}
