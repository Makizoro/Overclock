package com.example.linkingyou;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class RegisterActivity extends AppCompatActivity {
    EditText mUsername;
    EditText mEmail;
    EditText mPassword;
    EditText mPassCon;
    Button mBack;
    Button mReg;

    String B_username, B_password, B_email;
    String method;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mUsername = (EditText) findViewById(R.id.edUserReg);
        mEmail = (EditText) findViewById(R.id.Stud_id);
        mPassword = (EditText) findViewById(R.id.edPassReg);
        mPassCon = (EditText) findViewById(R.id.edPassConReg);
        mBack = (Button) findViewById(R.id.btnBack1);
        mReg = (Button) findViewById(R.id.btnReg);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MainIntent = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(MainIntent);
            }
        });
    }

    public void userReg(View v){
        B_username = mUsername.getText().toString();
        B_password = mPassword.getText().toString();
        B_email = mEmail.getText().toString();


        String method = "Register";
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method,B_username,B_password,B_email);
    }

    class BackgroundTask extends AsyncTask<String,Void,String> {

        AlertDialog alertDialog;
        Context ctx;

        BackgroundTask(Context ctx){
            this.ctx = ctx;
        }

        @Override
        protected void onPreExecute() {
            alertDialog = new AlertDialog.Builder(ctx).create();
            alertDialog.setTitle("Login information...");
        }

        @Override
        protected String doInBackground(String... params){
            String reg_url = "http://192.168.110.1/project/register.php";
            String method = params[0];

            if(method.equals("Register")){
                String userName = params[1];
                String password = params[2];
                String email = params[3];
                try {
                    URL url = new URL(reg_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);

                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                    String data = URLEncoder.encode("userName", "UTF-8") + "=" + URLEncoder.encode(userName, "UTF-8") + "&" +
                            URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8") + "&" +
                            URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String response = "";
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null){
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

    /*private void startActivity(Intent tab) {
    }*/

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            String check = result;
            Toast.makeText(ctx, result, Toast.LENGTH_SHORT).show();
            Log.d("onPostExecute: ", result);
            if (check.equals("Registration successful..")) {
                Toast.makeText(ctx, check, Toast.LENGTH_SHORT).show();
                Intent TabsIntent = new Intent(RegisterActivity.this, TabActivity.class);
                startActivity(TabsIntent);

            } else {
                Toast.makeText(ctx, "Username or password already exists.", Toast.LENGTH_SHORT).show();
            }
        }


    }
}
