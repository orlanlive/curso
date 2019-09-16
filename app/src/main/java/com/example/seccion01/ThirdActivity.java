package com.example.seccion01;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    private EditText editTextPhone;
    private EditText editTextWeb;
    private ImageButton imageButtonPhone;
    private ImageButton imageButtonWeh;
    private ImageButton imageButtonCamera;

    private final int PHONE_CALL_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        editTextWeb = (EditText) findViewById(R.id.editTextWeb);
        imageButtonPhone = (ImageButton) findViewById(R.id.imageButtonPhone);
        imageButtonWeh = (ImageButton) findViewById(R.id.imageButtonWeb);
        imageButtonCamera = (ImageButton) findViewById(R.id.imageButtonCamera);


        imageButtonPhone.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                String phoneNumber = editTextPhone.getText().toString();
                if (phoneNumber != null) {
                    //comprobar version actual del android que estamos corriendo
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PHONE_CALL_CODE);
                    } else {
                        OlderVersions(phoneNumber);
                    }
                }
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            private void OlderVersions(String phoneNumber) {
                Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
                if (CheckPermission(Manifest.permission.CALL_PHONE)) {
                    if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) return;
                    startActivity(intentCall);
                } else {
                    Toast.makeText(ThirdActivity.this, "You decline the access", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //ESTAMOS EN EL CASO DEL TELEFONO
        switch (requestCode) {
            case PHONE_CALL_CODE:

                String permission = permissions [0];
                int result = grantResults[0];

                if (permission.equals(Manifest.permission.CALL_PHONE)) {

                    //comprobar si ha sido aceptado o denegado la peticion de permiso
                    if (result == PackageManager.PERMISSION_GRANTED) {
                        //Concedio su permiso
                        String phoneNumber = editTextPhone.getText().toString();
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phoneNumber));
                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) return;
                        startActivity(intent);
                    }
                    else {
                        //No concedio su permiso
                        Toast.makeText(ThirdActivity.this, "You declined the access", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
                default:
                    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                    break;
        }
    }

    private boolean CheckPermission(String permission) {
        int result = this.checkCallingOrSelfPermission(permission);
        return result == PackageManager.PERMISSION_GRANTED;
    }
}
