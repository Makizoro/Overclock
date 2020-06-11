package com.example.linkingyou;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText mUsername;
    EditText mStudent_ID;
    EditText mPassword;
    EditText mPassCon;
    Button mBack;
    Button mReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mUsername = (EditText) findViewById(R.id.edUserReg);
        mStudent_ID = (EditText) findViewById(R.id.Stud_id);
        mPassword = (EditText) findViewById(R.id.edPassReg);
        mPassCon = (EditText) findViewById(R.id.edPassConReg);
        mBack = (Button) findViewById(R.id.btnBack1);
        mReg = (Button) findViewById(R.id.btnReg);
        //final CheckBox mStud = (CheckBox) findViewById(R.id.chkstud);
        //final CheckBox mLec = (CheckBox) findViewById(R.id.chklec);

        mReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UserName = mUsername.getText().toString();
                String studentId = mStudent_ID.getText().toString();
                String UserPassword = mPassword.getText().toString();
                String conpass = mPassCon.getText().toString();

                    if (conpass.equals(UserPassword)) {
                        ContentValues params = new ContentValues();
                        params.put("UserName", UserName);
                        params.put("studentId", studentId);
                        params.put("UserPassword", UserPassword);

                        //todo: takes username, student id and password, registers them. returns 0 or 1 (a 0 if such a student id already exists)
                        AsyncHTTPPost asyncHttpPost = new AsyncHTTPPost("http://10.100.15.21/project/register.php", params) {
                            @Override
                            protected void onPostExecute(String output) {
                                if (output.equals("0")) {
                                    TextView mWrong = (TextView) findViewById(R.id.txtunmatched);
                                    mWrong.setText("You already have an account...");
                                }else{
                                    TextView mWrong = (TextView) findViewById(R.id.txtunmatched);
                                    mWrong.setText("Registered Successfully!");
                                }
                            }
                        };
                        asyncHttpPost.execute();
                    } else {
                        TextView mWrong = (TextView) findViewById(R.id.txtunmatched);
                        mWrong.setText("Passwords do not match.");
                    }

            }
        });
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MainIntent = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(MainIntent);
            }
        });
    }
}
