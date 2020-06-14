package com.example.linkingyou.ui.main;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
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

    public void interest(View v){
        BackgroundTask backgroundTask = new BackgroundTask(this);

    }

    class BackgroundTask extends AsyncTask<String,Void,String> {

        AlertDialog alertDialog;
        InterestFrag ctx;

        BackgroundTask(InterestFrag ctx) {
            this.ctx = ctx;
        }

        @Override
        protected void onPreExecute() {
            // alertDialog = new AlertDialog.Builder(ctx).create();
            //  alertDialog.setTitle("Login information...");
        }

        @Override
        protected String doInBackground(String... params) {
            //String reg_url = "http://192.168.42.43/project/register.php";
            String interest_url = "http://192.168.42.43/project/getInterest.php";
            String method = params[0];

            if (method.equals("Login")) {
                String login_name = params[1];
                String password = params[2];
                try {
                    URL url = new URL(interest_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);

                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    String data = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(login_name, "UTF-8") + "&" +
                            URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    OS.close();

                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String response = "";
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        response += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();

                    return response;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            String check = result;
            Log.d("output", check);

        }
    }
    /*@Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_main, container, false);
        final TextView textView = root.findViewById(R.id.section_label);
        /*pageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        final String Interest_id = "";
        ContentValues params = new ContentValues();
        params.put("Interest_id",Interest_id);

        //TODO: Gets all InterestG Names.
        @SuppressLint("StaticFieldLeak") AsyncHTTPPost asYncHttpPost = new AsyncHTTPPost(
                "http://localhost/project/tab.php",params) {
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
                                    ContentValues params = new ContentValues();
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
                                    AsyncHttpPost.execute();
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
    }*/


}