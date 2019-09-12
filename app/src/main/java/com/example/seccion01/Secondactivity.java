package com.example.seccion01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Secondactivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondactivity);

        textView = (TextView) findViewById(R.id.textViewMain);

        //Tomar los datos del intent
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String greeter = bundle.getString("greeter");
            Toast.makeText(Secondactivity.this, greeter, Toast.LENGTH_LONG).show();
            textView.setText(greeter);
        } else {
            Toast.makeText(Secondactivity.this, "it is empty", Toast.LENGTH_LONG).show();
        }
    }
}
