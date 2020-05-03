package com.example.administratorweiez_1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button shop_join = findViewById(R.id.shop_sign_up);
        shop_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ShopJoinActivity.class);
                startActivity(intent);
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
