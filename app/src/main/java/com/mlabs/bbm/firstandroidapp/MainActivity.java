package com.mlabs.bbm.firstandroidapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    LoginDataBaseAdapter loginDataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // create a instance of SQLite Database
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        final EditText email = (EditText) findViewById(R.id.email);
        final EditText password = (EditText) findViewById(R.id.password);

        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        Button btnShow = (Button) findViewById(R.id.btnShow);
        final TextView signup = (TextView) findViewById(R.id.txtsignup);


//        button login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get The User name and Password
                String main_email=email.getText().toString();
                String pword2=password.getText().toString();

                // fetch the Password form database for respective user name
                String storedPassword=loginDataBaseAdapter.getSinlgeEntry(main_email);

                if(pword2.equals(storedPassword))
                {
                    Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(getApplicationContext(),Main2Activity.class);
                    startActivity(i);
                    finish();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Error: Username or Password does not match", Toast.LENGTH_SHORT).show();
                }
            }
        });
//      btnsignup
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, signupactivity.class);
                startActivity(i);
//                finish();
            }
        });


        btnShow.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent motionevent) {
                final int cursor = password.getSelectionStart();
                int event = motionevent.getAction();
                switch (event) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d("Classname","ACTION_DOWN");
                        password.setTransformationMethod(null);
                        password.setSelection(cursor);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        password.setTransformationMethod(new PasswordTransformationMethod());
                        password.setSelection(cursor);
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.d("Classname","ACTION_UP");
                        password.setTransformationMethod(new PasswordTransformationMethod());
                        password.setSelection(cursor);
                        break;
                }
                return true;
            }
        });
    }

//    //      validating email
//    protected boolean validateEmail(String email) {
//        String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
//        Pattern pattern = Pattern.compile(emailPattern);
//        Matcher matcher = pattern.matcher(email);
//
//        return matcher.matches();
//    }
//    //      validate password
////    return true of the passwrod is valid
//    protected boolean validatePassword(String password) {
//        if(password!=null && password.length() >6) {
//            return true;
//        } else {
//            return false;
//        }
//    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close The Database
        loginDataBaseAdapter.close();
    }
}