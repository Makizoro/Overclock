package com.example.linkingyou.ui.main;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.linkingyou.AdminActivity;
import com.example.linkingyou.AsyncHTTPPost;
import com.example.linkingyou.MainActivity;
import com.example.linkingyou.R;
import com.example.linkingyou.TabActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * A placeholder fragment containing a simple view.
 */
public class InterestFrag extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    Activity context;
    
    public static InterestFrag newInstance(int index) {
        InterestFrag fragment = new InterestFrag();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);

    }


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        context.getParentActivityIntent();
        final View root = inflater.inflate(R.layout.fragment_main, container, false);
        final TextView textView = root.findViewById(R.id.section_label);
        /*pageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        final String Interest_id = "";
        ContentValues params = new ContentValues();
        params.put("Interest_id",Interest_id);

        //TODO: Gets all InterestG Names.
        @SuppressLint("StaticFieldLeak") AsyncHTTPPost asYncHttpPost = new AsyncHTTPPost(
                "http://192.168.110.1/project/tab.php",params) {
            @Override
            protected void onPostExecute(String output) {
                    LinearLayout l = (LinearLayout) root.findViewById(R.id.list);
                    l.removeAllViews();
                    Log.d("output", output);

                    try {
                        JSONArray ja = new JSONArray(output);
                        for (int i = 0; i < ja.length(); i++) {
                            JSONObject jo = (JSONObject) ja.get(i);
                            LinearLayout item = (LinearLayout) getLayoutInflater().inflate(R.layout.list_item, null);


                            // TODO: Set field to Interest_NAME so I can call that field.
                            TextView assigntxt = (TextView) item.findViewById(R.id.textview1);
                            assigntxt.setText(jo.getString("Interest_NAME"));
                            final String id = jo.getString("Interest_NAME");

                            l.addView(item);
                            item.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    LinearLayout item = (LinearLayout) getLayoutInflater().inflate(R.layout.list_item, null);
                                    final TextView marked = (TextView)item.findViewById(R.id.textview1);
                                    String Interest_ID = marked.getText().toString();
                                    
                                    //Stuff Sayan added.
                                Intent Prof = new Intent(context, ProfActivity.class);
                                Prof.putExtra("Club_ID", Club_ID);
                                startActivity(Prof);
                                    /*ContentValues params = new ContentValues();
                                    params.put("Interest_ID", Interest_ID);

                                    //TODO: Gets all InterestG descriptions.
                                    @SuppressLint("StaticFieldLeak") AsyncHTTPPost AsyncHttpPost = new AsyncHTTPPost(
                                            "http://lamp.ms.wits.ac.za/~s1746074/getdesc.php", params) {
                                        @Override
                                        protected void onPostExecute(String output) {
                                            LinearLayout l = (LinearLayout) root.findViewById(R.id.list);
                                            l.removeAllViews();
                                            Log.d("output", output);
                                            try {
                                                JSONArray ja = new JSONArray(output);
                                                for (int i = 0; i < ja.length(); i++) {
                                                    JSONObject jo = (JSONObject) ja.get(i);
                                                    LinearLayout item = (LinearLayout) getLayoutInflater().inflate(R.layout.list_item, null);

                                                    TextView assigntxt = (TextView) item.findViewById(R.id.textview1);
                                                    assigntxt.setText(jo.getString("Interest_NAME"));
                                                    final String id = jo.getString("Interest_NAME");

                                                    TextView assigntxt2 = (TextView) item.findViewById(R.id.textview2);
                                                    assigntxt2.setText(jo.getString("Interest_Desc"));
                                                    final String id2 = jo.getString("Interest_Desc");
                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    };
                                    AsyncHttpPost.execute();*/
                                }
                            });
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        };
        asYncHttpPost.execute();


        return root;
    }


}
