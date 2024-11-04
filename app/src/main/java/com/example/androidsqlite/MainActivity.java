package com.example.androidsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Button bInput, bEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        bInput = (Button) findViewById(R.id.btnInput);
        bEdit = (Button) findViewById(R.id.btnEdit);

        bInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, InputActivity.class);

                // Memulai aktivitas baru
                startActivity(i);
            }
        });

        bEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, EditActivity.class);

                // Memulai aktivitas baru
                startActivity(i);
            }
        });

    }

}