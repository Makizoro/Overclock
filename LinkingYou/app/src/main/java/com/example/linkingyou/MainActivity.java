package com.example.linkingyou;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.linkingyou.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    EditText mUsername;
    EditText mPassword;
    Button mButtonLog;
    TextView mRegister;
    TextView mTextWrong;

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
                //TODO: remove when done
                Intent registerIntent = new Intent(MainActivity.this,TabActivity.class);
                startActivity(registerIntent);
                //Intent registerIntent = new Intent(MainActivity.this,RegisterActivity.class);
                //startActivity(registerIntent);
            }
        });
        mButtonLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: remove when done

                String UserName=mUsername.getText().toString().trim();
                String UserPassword=mPassword.getText().toString().trim();
                ContentValues params = new ContentValues();
                params.put("UserName",UserName);
                params.put("UserPassword",UserPassword);
                AsyncHTTPPost asyncHttpPost = new AsyncHTTPPost(
                        "http://192.168.43.132/TESTING/PHPFILES/auth.php",params) {
                    @Override
                    protected void onPostExecute(String output) {
                        processlogin(output);
                    }
                };
                asyncHttpPost.execute();
            }
        });
    }

    public void processlogin(String output) {
        mPassword = (EditText) findViewById(R.id.edPass);
        String username=mPassword.getText().toString().trim();
        if(output.equals("0")){
            Intent TabsIntent = new Intent(MainActivity.this, TabActivity.class);
            //TabsIntent.putExtra("userS",username);
            startActivity(TabsIntent);
        }else if(output.equals("1")){
            Toast.makeText(this,"Incorrect Password or Username",Toast.LENGTH_SHORT).show();
        }
    }
}