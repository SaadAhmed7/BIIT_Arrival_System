package com.saad.biit_arrival_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.saad.biit_arrival_system.Model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Login_Page extends AppCompatActivity {

    //Refrence Objects for Layout
    EditText edtmobilenumber;
    EditText edtpassword;
    Button btnlogin;

    //User Object to save User information while login
    User user = new User();

    //WebAPI Addres to user User Database
    final String UserDatabase = "http://192.168.43.9/WebAPIServer/api/user/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__page);

        edtmobilenumber = findViewById(R.id.edtusername);
        edtpassword = findViewById(R.id.edtpassword);
        btnlogin = findViewById(R.id.btnlogin);

        //Set Login Button Listener to Login according to Role and save user Data if Matched.
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtmobilenumber.getText().toString().length() != 0 && edtpassword.getText().toString().length() != 0)
                {
                    String tempUrl = "";
                    String Number = edtmobilenumber.getText().toString();
                    String Password = edtpassword.getText().toString();
                    tempUrl += UserDatabase + "getuser?mobile=" + Number + "&password=" + Password;
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, tempUrl, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                if(jsonResponse.getString("Mobile").equals(Number)
                                        && jsonResponse.getString("Password").equals(Password)){
                                    Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_SHORT).show();
                                    user.setId(Integer.parseInt(jsonResponse.getString("Id")));
                                    user.setFirstName(jsonResponse.getString("FirstName"));
                                    user.setLastName(jsonResponse.getString("LastName"));
                                    user.setGender(jsonResponse.getString("Gender"));
                                    user.setMobile(jsonResponse.getString("Mobile"));
                                    user.setPassword(jsonResponse.getString("Password"));
                                    user.setRole(jsonResponse.getString("Role"));
                                    Log.d("Userdata", user.getId()+user.getFirstName()+user.getLastName()+user.getGender()+user.getMobile()
                                    +user.getPassword()+user.getRole());
                                    if(user.getRole().equals("Guard")){
                                        Intent intent = new Intent(Login_Page.this, GuardActivity.class);
                                        startActivity(intent);
                                    }
                                    else if(user.getRole().equals("Admin")){
                                        Intent intent = new Intent(Login_Page.this, AdminActivity.class);
                                        startActivity(intent);
                                    }
                                    else if(user.getRole().equals("Director")){
                                        Intent intent = new Intent(Login_Page.this, DirectorActivity.class);
                                        startActivity(intent);
                                    }
                                    else
                                        Toast.makeText(getApplicationContext(), "No Role is Define", Toast.LENGTH_SHORT).show();
                                }
                                else
                                    Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    stringRequest.setRetryPolicy(new DefaultRetryPolicy( 50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Enter UserName & Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}