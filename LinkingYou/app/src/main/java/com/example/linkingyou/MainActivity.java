package com.example.linkingyou;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.linkingyou.ui.main.SectionsPagerAdapter;

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


public class MainActivity extends AppCompatActivity {

    EditText mUsername;
    EditText mPassword;
    Button mButtonLog;
    TextView mRegister;
    TextView mTextWrong;
    private Object HttpURLConnection;

    String Login;
    String Register;
    String B_username;
    String B_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUsername = (EditText) findViewById(R.id.editText);
        mPassword = (EditText) findViewById(R.id.edPass);
        mButtonLog = (Button) findViewById(R.id.btnLog);
        mRegister = (TextView) findViewById(R.id.txtReg);
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

        mButtonLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                               userLogIn(view);
            }
        });
    }

    public void userReg(View v){
        startActivity(new Intent(this, RegisterActivity.class));
    }


    public void userLogIn(View v){
        B_username = mUsername.getText().toString();
        B_password = mPassword.getText().toString();

        String method = "Login";
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method,B_username,B_password);
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
            //String reg_url = "http://192.168.42.43/project/register.php";
            String login_url = "http://192.168.42.43/project/auth1.php";
            String method = params[0];

           if(method.equals("Login")){
                String login_name = params[1];
                String password = params[2];
                try {
                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);

                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
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

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            String check = result;
            Log.d("output", check);
            if (result.equals("Login Succesful")) {
                Intent TabsIntent = new Intent(MainActivity.this, TabActivity.class);
                //TabsIntent.putExtra("userS",username);
                Toast.makeText(ctx, check, Toast.LENGTH_SHORT).show();
                startActivity(TabsIntent);
            } else if(result.equals("Login Succesful..Admin")){
                Intent AmdIntent = new Intent(MainActivity.this, AdminActivity.class);
                Toast.makeText(ctx, check, Toast.LENGTH_SHORT).show();
                startActivity(AmdIntent);

            }else{
                Toast.makeText(ctx, "Failed to login... incorrect username or password", Toast.LENGTH_SHORT).show();
            }
        }
    }
}