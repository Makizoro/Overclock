package com.example.linkingyou;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.linkingyou.AsyncHTTPPost;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

public class AdminActivity extends AppCompatActivity {

    Button ButtonAcc;
    Button ButtonRej;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity);
        ButtonAcc = (Button) findViewById(R.id.btnAccept);
        ButtonAcc.setVisibility(View.INVISIBLE);
        ButtonRej = (Button) findViewById(R.id.btnReject);
        ButtonRej.setVisibility(View.INVISIBLE);
        processGroups();

    }

    public void processGroups() {
        final TextView textView = findViewById(R.id.section_label1);
        /*pageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        final String Club_id = "";
        ContentValues params = new ContentValues();
        params.put("Club_id",Club_id);

        //todo: gets all new groups with their description as one object.
        @SuppressLint("StaticFieldLeak") AsyncHTTPPost asYncHttpPost = new AsyncHTTPPost(
                "http://0.100.15.21/project/getNewGroups.php",params) {
            @Override
            protected void onPostExecute(String output) {
                LinearLayout l = (LinearLayout) findViewById(R.id.list);
                l.removeAllViews();
                Log.d("output", output);
                try {
                    JSONArray ja = new JSONArray(output);
                    for (int i = 0; i < ja.length(); i++) {
                        JSONObject jo = (JSONObject) ja.get(i);
                        LinearLayout item = (LinearLayout) getLayoutInflater().inflate(R.layout.list_item, null);


                        // TODO: Set field to Group_NAME so I can call that field.
                        TextView assigntxt = (TextView) item.findViewById(R.id.textview1);
                        assigntxt.setText(jo.getString("Group_NAME"));
                        final String id = jo.getString("Group_NAME");

                        l.addView(item);
                        item.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ButtonAcc.setVisibility(View.VISIBLE);
                                ButtonRej.setVisibility(View.VISIBLE);
                                ButtonAcc.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        LinearLayout item = (LinearLayout) getLayoutInflater().inflate(R.layout.list_item, null);
                                        final TextView marked = (TextView)item.findViewById(R.id.textview1);
                                        String Group_ID = marked.getText().toString();
                                        ContentValues params = new ContentValues();
                                        params.put("Group_NAME", Group_ID);

                                        //todo: takes the group name and puts it in the club/interest/social table and removes it from the NewGroups table.
                                        @SuppressLint("StaticFieldLeak") AsyncHTTPPost AsyncHttpPost = new AsyncHTTPPost(
                                                "http://lamp.ms.wits.ac.za/~s1746074/AcceptGroup.php", params) {
                                            @Override
                                            protected void onPostExecute(String output) {
                                            }
                                        };
                                        AsyncHttpPost.execute();
                                        Intent TabsIntent = new Intent(AdminActivity.this, AdminActivity.class);
                                        //TabsIntent.putExtra("userS",username);
                                        startActivity(TabsIntent);
                                    }
                                });
                                ButtonRej.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        LinearLayout item = (LinearLayout) getLayoutInflater().inflate(R.layout.list_item, null);
                                        final TextView marked = (TextView)item.findViewById(R.id.textview1);
                                        String Group_ID = marked.getText().toString();
                                        ContentValues params = new ContentValues();
                                        params.put("Group_NAME", Group_ID);

                                        //todo: takes the group name and removes it from the NewGroups table.
                                        @SuppressLint("StaticFieldLeak") AsyncHTTPPost AsyncHttpPost = new AsyncHTTPPost(
                                                "http://lamp.ms.wits.ac.za/~s1746074/RejectGroup.php", params) {
                                            @Override
                                            protected void onPostExecute(String output) {
                                            }
                                        };
                                        AsyncHttpPost.execute();
                                        Intent TabsIntent = new Intent(AdminActivity.this, AdminActivity.class);
                                        //TabsIntent.putExtra("userS",username);
                                        startActivity(TabsIntent);
                                    }
                                });
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        asYncHttpPost.execute();


        return;
    }
}
