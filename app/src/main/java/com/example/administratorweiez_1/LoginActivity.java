package com.example.administratorweiez_1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.administratorweiez_1.request.LoginRequest;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    EditText idText, passText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        idText = findViewById(R.id.input_id);
        passText = findViewById(R.id.input_password);

        final Button login = findViewById(R.id.login_btn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int status = NetworkStatus.getConnectivityStatus(getApplicationContext());
                if (status == NetworkStatus.TYPE_MOBILE || status == NetworkStatus.TYPE_WIFI) {

                    String userID = idText.getText().toString().trim();
                    String userPassword = passText.getText().toString().trim();

                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                                if (success) {


                                    String userName = jsonResponse.getString("userName");
                                    String userID = jsonResponse.getString("userID");
                                    String userPhone = jsonResponse.getString("userPhone");
                                    String userPassword = jsonResponse.getString("userPassword");

                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    LoginActivity.this.startActivity(intent);
                                    finish();
                                    Toast.makeText(getApplicationContext(), userID + "님으로 로그인 하셨습니다", Toast.LENGTH_LONG).show();


                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                    builder.setMessage("아이디나 비밀번호를 확인하세요.")
                                            .setNegativeButton("다시 시도", null)
                                            .create()
                                            .show();

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    LoginRequest loginRequest = new LoginRequest(userID, userPassword, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                    queue.add(loginRequest);
                }
                else {
                    Toast.makeText(getApplicationContext(),"인터넷을 확인해주세요",Toast.LENGTH_SHORT).show();
                }

            }
        });

        final Button sign_up = findViewById(R.id.sign_up_btn);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                LoginActivity.this.startActivity(intent);
            }
        });

    }
    private long time=0;
    @Override
    public void onBackPressed() {
        if(System.currentTimeMillis()-time>=2000){
            time= System.currentTimeMillis();
            Toast.makeText(getApplicationContext(), "한번 더 누르면 종료 됩니다.", Toast.LENGTH_SHORT).show();
        }else if(System.currentTimeMillis()-time<2000){
            finish();
        }
    }
}
