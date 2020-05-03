package com.example.administratorweiez_1;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.administratorweiez_1.request.RegisterRequest;

import org.json.JSONObject;

public class SignUpActivity extends AppCompatActivity {

    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);



        final EditText sign_name = findViewById(R.id.sign_up_name);
        final EditText sign_id = findViewById(R.id.sign_up_id);
        final EditText sign_phone = findViewById(R.id.sign_up_phone);
        final EditText sign_password = findViewById(R.id.sign_up_password);

        Button sign_up = findViewById(R.id.sign_up_btn2);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int status = NetworkStatus.getConnectivityStatus(getApplicationContext());

                if (status == NetworkStatus.TYPE_MOBILE || status == NetworkStatus.TYPE_WIFI) {


                    String userName = sign_name.getText().toString();
                    String userID = sign_id.getText().toString();
                    String userPhone = sign_phone.getText().toString();
                    String userPassword = sign_password.getText().toString();


                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                                if (success) {

                                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                                    dialog = builder.setMessage("회원 등록에 성공했습니다.")
                                            .setPositiveButton("확인", null)
                                            .create();
                                    dialog.show();
                                    finish();


                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                                    dialog = builder.setMessage("회원 등록에 실패했습니다.")
                                            .setNegativeButton("확인", null)
                                            .create();
                                    dialog.show();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    RegisterRequest registerRequest = new RegisterRequest(userName, userID, userPhone, userPassword, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(SignUpActivity.this);
                    queue.add(registerRequest);
                }
                else {
                    Toast.makeText(getApplicationContext(),"인터넷을 확인해주세요",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}
