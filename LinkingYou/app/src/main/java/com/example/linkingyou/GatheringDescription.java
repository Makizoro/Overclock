package com.example.linkingyou;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.content.ContentValues;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class GatheringDescription extends AppCompatActivity {

    EditText Name;
    EditText Desc;
    Button Create;
    CheckBox Club;
    CheckBox Social;
    CheckBox Interest;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gathering_desc);

        Name = (EditText) findViewById(R.id.editTxt);
        Desc = (EditText) findViewById(R.id.edDesc);
        Create = (Button) findViewById(R.id.btnConf);
        Club = (CheckBox) findViewById(R.id.chkClub);
        Social = (CheckBox) findViewById(R.id.chkSocial);
        Interest = (CheckBox) findViewById(R.id.chkInterest);

        Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                String SName=Name.getText().toString().trim();
                String SDesc=Desc.getText().toString().trim();

                if(Club.isChecked()){
                    ContentValues params = new ContentValues();
                    params.put("SName", SName);
                    params.put("SDesc", SDesc);

                    //todo: takes in name, desc, and creates a potential club, needs to be approved by admin.
                    @SuppressLint("StaticFieldLeak") AsyncHTTPPost AsyncHttpPost = new AsyncHTTPPost(
                            "http://127.0.0.1/onlineServer/createClub.php", params) {
                        @Override
                        protected void onPostExecute(String output) {
                            Snackbar.make(v, "Sent to Administrator for Approval!", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                    };
                    AsyncHttpPost.execute();
                }
                if(Social.isChecked()){
                    ContentValues params = new ContentValues();
                    params.put("SName", SName);
                    params.put("SDesc", SDesc);

                    //todo: takes in name, desc, and creates a potential SocialG, needs to be approved by admin.
                    @SuppressLint("StaticFieldLeak") AsyncHTTPPost AsyncHttpPost = new AsyncHTTPPost(
                            "http://127.0.0.1/onlineServer/createSocial.php", params) {
                        @Override
                        protected void onPostExecute(String output) {
                            Snackbar.make(v, "Sent to Administrator for Approval!", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                    };
                    AsyncHttpPost.execute();
                }
                if(Interest.isChecked()){
                    ContentValues params = new ContentValues();
                    params.put("SName", SName);
                    params.put("SDesc", SDesc);

                    //todo: takes in name, desc, and creates a potential InterestG, needs to be approved by admin.
                    @SuppressLint("StaticFieldLeak") AsyncHTTPPost AsyncHttpPost = new AsyncHTTPPost(
                            "http://127.0.0.1/onlineServer/createInterest.php", params) {
                        @Override
                        protected void onPostExecute(String output) {
                            Snackbar.make(v, "Sent to Administrator for Approval!", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                    };
                    AsyncHttpPost.execute();
                }
            }
        });
        //LinearLayout item = (LinearLayout) getLayoutInflater().inflate(R.layout.list_item, null);
        //final TextView marked = (TextView)item.findViewById(R.id.textview1);
        //String Club_ID = marked.getText().toString();
        /*ContentValues params = new ContentValues();
        params.put("Club_ID", Club_ID);
        @SuppressLint("StaticFieldLeak") AsyncHTTPPost AsyncHttpPost = new AsyncHTTPPost(
                "http://lamp.ms.wits.ac.za/~s1746074/getevents.php", params) {
            @Override
            protected void onPostExecute(String output) {
            }
        };
        AsyncHttpPost.execute();*/
    }
}
