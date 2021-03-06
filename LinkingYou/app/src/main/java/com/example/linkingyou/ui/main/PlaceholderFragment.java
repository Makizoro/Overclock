package com.example.linkingyou.ui.main;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.linkingyou.AsyncHTTPPost;
import com.example.linkingyou.GatheringDescription;
import com.example.linkingyou.MainActivity;
import com.example.linkingyou.R;
import com.example.linkingyou.TabActivity;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
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
        final View root = inflater.inflate(R.layout.fragment_main, container, false);
        final TextView textView = root.findViewById(R.id.section_label);
        /*pageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        final String Club_id = "";
        ContentValues params = new ContentValues();
        params.put("Club_id",Club_id);

        //todo: takes empty string and returns Club names.
        @SuppressLint("StaticFieldLeak") AsyncHTTPPost asYncHttpPost = new AsyncHTTPPost(
                "http://lamp.ms.wits.ac.za/~s1746074/clubs.php",params) {
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


                            // TODO: Set field to CLUB_NAME so I can call that field.
                            TextView assigntxt = (TextView) item.findViewById(R.id.textview1);
                            assigntxt.setText(jo.getString("CLUB_NAME"));
                            final String id = jo.getString("CLUB_NAME");

                            l.addView(item);
                            item.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    LinearLayout item = (LinearLayout) getLayoutInflater().inflate(R.layout.list_item, null);
                                    final TextView marked = (TextView)item.findViewById(R.id.textview1);
                                    String Club_ID = marked.getText().toString();
                                    ContentValues params = new ContentValues();
                                    params.put("Club_ID", Club_ID);

                                    //TODO: Gets all Club descriptions.
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
                                                    assigntxt.setText(jo.getString("CLUB_NAME"));
                                                    final String id = jo.getString("CLUB_NAME");

                                                    TextView assigntxt2 = (TextView) item.findViewById(R.id.textview2);
                                                    assigntxt2.setText(jo.getString("CLUB_Desc"));
                                                    final String id2 = jo.getString("CLUB_Desc");
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
    }


}