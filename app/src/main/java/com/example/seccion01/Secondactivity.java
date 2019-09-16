package com.example.seccion01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Secondactivity extends AppCompatActivity {

    private TextView textView;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondactivity);

        textView = (TextView) findViewById(R.id.textViewMain);
        btnNext = (Button) findViewById(R. id.buttonGoSharing);

        //Tomar los datos del intent
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String greeter = bundle.getString("greeter");
            Toast.makeText(Secondactivity.this, greeter, Toast.LENGTH_LONG).show();
            textView.setText(greeter);
        } else {
            Toast.makeText(Secondactivity.this, "it is empty", Toast.LENGTH_LONG).show();
        }

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Secondactivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });

    }
}
