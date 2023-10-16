package com.demirgroup.sil2;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        u();
        ImageButton b1=findViewById(R.id.call);
        EditText editText = findViewById(R.id.numbers);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    izin.launch(Manifest.permission.CALL_PHONE);
                }else{
                    if (editText.getText().toString().equals("")){
                        Toast.makeText(MainActivity.this,"Please number",Toast.LENGTH_SHORT).show();
                    }else{
                        Intent i=new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+editText.getText().toString()));
                        startActivity(i);
                    }
                }



            }
        });
        ImageButton b2 = findViewById(R.id.numberdial);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this,"Please number",Toast.LENGTH_SHORT).show();
                }else{
                    Intent i=new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+editText.getText().toString()));
                    startActivity(i);
                }
            }
        });
    }
    public void u(){
        izin = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                if (result){
                    String phonenumber="tel:+90537451671";
                    Intent i=new Intent(Intent.ACTION_CALL,Uri.parse(phonenumber));
                }
            }
        });
    }
    ActivityResultLauncher<String> izin;
}