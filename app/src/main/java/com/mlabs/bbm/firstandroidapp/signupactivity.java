package com.mlabs.bbm.firstandroidapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class signupactivity extends AppCompatActivity {

    LoginDataBaseAdapter loginDataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupactivity);

        final EditText txtemail = (EditText) findViewById(R.id.S_email);
        final EditText txtpassword = (EditText) findViewById(R.id.S_pword);
        final EditText con_password = (EditText) findViewById(R.id.S_Cpword);
        final EditText txtfname = (EditText) findViewById(R.id.S_fname);
        final EditText txtlname = (EditText) findViewById(R.id.S_lname);
        final EditText txtusername = (EditText) findViewById(R.id.S_username);
        Button btnsignup = (Button) findViewById(R.id.btnsignup);

        // get Instance  of Database Adapter
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();
        //        button login
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validateEmail(txtemail.getText().toString())) {
                    txtemail.setError("Invalid Email");
                    txtemail.requestFocus();
                }
                else if(!validatePassword(txtpassword.getText().toString())){
                    txtpassword.setError("Invalid Password");
                    txtpassword.requestFocus();
                }
                else if(!txtpassword.getText().toString().equals(con_password.getText().toString())) {
                    con_password.setError("Password does not match");
                    txtpassword.requestFocus();
                }
                else if(!validatename(txtfname.getText().toString())) {
                    txtfname.setError("Invalid Firsname");
                    txtfname.requestFocus();
                }
                else if(!validatename(txtlname.getText().toString())) {
                    txtlname.setError("Invalid Lastname");
                    txtlname.requestFocus();
                }
                else if(validateEmail(txtusername.getText().toString())) {
                    txtusername.setError("Username cannot be an email format");
                    txtusername.requestFocus();
                }
                else if(loginDataBaseAdapter.validateExisting(txtusername.getText().toString())) {
                    txtusername.setError("username or email already exist");
                    txtusername.requestFocus();
                }
                else if(loginDataBaseAdapter.validateExisting(txtemail.getText().toString())) {
                    txtusername.setError("username or email already exist");
                    txtusername.requestFocus();
                }
                else {
//                    loginDataBaseAdapter.insertEntry(email.getText().toString(), password.getText().toString(),getCurrentDateTime());
                    loginDataBaseAdapter.registeruser(txtemail.getText().toString(), txtpassword.getText().toString(), txtfname.getText().toString(),txtlname.getText().toString(),txtusername.getText().toString());
                    Toast.makeText(getApplicationContext(), "Added Account.", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(signupactivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });

    }
    public String getCurrentDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        return dateFormat.format(date).toString();
    }
    //      validating name
    protected boolean validatename(String name) {
        String fnamelname = "[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(fnamelname);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }
    //      validating email
    protected boolean validateEmail(String email) {
        String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    //      validate password
    protected boolean validatePassword(String password) {
        if(password!=null && password.length() >8) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        loginDataBaseAdapter.close();
    }
}


