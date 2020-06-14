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
        /*mButtonLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName=mUsername.getText().toString().trim();
                String userPassword=mPassword.getText().toString().trim();
                //ContentValues params = new ContentValues();
                //params.put("username",userName);
                //params.put("password",userPassword);


                //ToDo: processes login with username and password. Returns 0,1,or 2.
                @SuppressLint("StaticFieldLeak")AsyncHTTPPost asyncHttpPost = new AsyncHTTPPost(
                        "http://192.168.42.43/project/auth1.php",params) {
                    @Override
                    protected void onPostExecute(String output) {
                        processlogin(output);
                    }
                };
                asyncHttpPost.execute();
            }
        });*/
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

        //Task <backgroundTask.Status> backgroundTask.do
        //String resIn = backgroundTask.mesages("Login Succesful");

        /*if(resIn.equals("Login Succesful"))
        {
            Intent loginIntent = new Intent(MainActivity.this,TabActivity.class);
            startActivity(loginIntent);
        }*/

        /*mButtonLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logIntent = new Intent(MainActivity.this,TabActivity.class);
                startActivity(logIntent);
            }

        });*/
        //BackIntent.Connect connection = new BackIntent.Connect();
        //connection.execute(method,B_username,B_password);
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

    /*private void startActivity(Intent tab) {
    }*/

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

                //BackIntent backIntent = new BackIntent();
                //backIntent.Test(check);
            } else {
                Toast.makeText(ctx, "Failed to login... incorrect username or password", Toast.LENGTH_SHORT).show();
            }
        }


    }

   /* public void getInstance(String tent)
    {
        if(tent.equals("Tab")) {
            Intent TabsIntent = new Intent(MainActivity.this, TabActivity.class);
        }
    }*/

    /*public void processlogin(String output) {
        mPassword = (EditText) findViewById(R.id.edPass);
        String username = mPassword.getText().toString().trim();



        if (output.equals("regular")) {
            Intent TabsIntent = new Intent(MainActivity.this, TabActivity.class);
            //TabsIntent.putExtra("userS",username);
            Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show();
            startActivity(TabsIntent);
        }
        if (output.equals("admin")) {
            Intent AdminIntent = new Intent(MainActivity.this, AdminActivity.class);
            //TabsIntent.putExtra("userS",username);
            startActivity(AdminIntent);
        }
        if (output.equals("error")) {
            Toast.makeText(this, "Incorrect Password or Username", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this, output, Toast.LENGTH_SHORT).show();

    }*/
}