package com.example.administratorweiez_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.administratorweiez_1.request.ShopJoinRequest;

import org.json.JSONObject;

public class ShopJoinActivity extends AppCompatActivity {

    private String shopSelect;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrollment);

        final EditText shop_name_text= findViewById(R.id.shop_name);
        final EditText shop_addr_text = findViewById(R.id.shop_addr);
        final EditText shop_phone_text = findViewById(R.id.shop_phone);

        final RadioGroup selectGroup = (RadioGroup) findViewById(R.id.radioGroup);
        int selectGroupID = selectGroup.getCheckedRadioButtonId();
        shopSelect = ((RadioButton) findViewById(selectGroupID)).getText().toString();

        selectGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectButton = (RadioButton) findViewById(i);
                shopSelect = selectButton.getText().toString();
            }
        });

        Button shop_join = findViewById(R.id.shop_join_btn);
        shop_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int status = NetworkStatus.getConnectivityStatus(getApplicationContext());
                if (status == NetworkStatus.TYPE_MOBILE || status == NetworkStatus.TYPE_WIFI) {

                    String shopName = shop_name_text.getText().toString();
                    String shopAddr = shop_addr_text.getText().toString();
                    String shopPhone = shop_phone_text.getText().toString();


                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                                if (success) {

                                    AlertDialog.Builder builder = new AlertDialog.Builder(ShopJoinActivity.this);
                                    dialog = builder.setMessage("매장 등록에 성공했습니다.")
                                            .setPositiveButton("확인", null)
                                            .create();
                                    dialog.show();
                                    finish();
                                    Intent intent = new Intent(ShopJoinActivity.this,LoginActivity.class);
                                    startActivity(intent);



                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(ShopJoinActivity.this);
                                    dialog = builder.setMessage("매장 등록에 실패했습니다.")
                                            .setNegativeButton("확인", null)
                                            .create();
                                    dialog.show();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    ShopJoinRequest shopJoinRequest = new ShopJoinRequest(shopName, shopAddr, shopPhone, shopSelect, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(ShopJoinActivity.this);
                    queue.add(shopJoinRequest);
                }
                else {
                    Toast.makeText(getApplicationContext(),"인터넷을 확인해주세요",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
