package com.example.implicitintent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] PERMISSIONS = {Manifest.permission.CALL_PHONE,Manifest.permission.CAMERA};

        if ((ContextCompat.checkSelfPermission(getApplicationContext(),PERMISSIONS[0])
                        != PackageManager.PERMISSION_GRANTED)
            || (ContextCompat.checkSelfPermission(getApplicationContext(),PERMISSIONS[1])
                        != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this,PERMISSIONS,1);
        }
    }

    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.btnCall:
                intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:01087706019"));
                break;
            case R.id.btnDial:
                intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:01087706019"));
                break;
            case R.id.btnSns:
                intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("tel:01087706019"));
                intent.putExtra("sns_body","객체지향언어 리포트 했나요?");
            case R.id.btnMap:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:37.18 , 128.19?z=10"));
                break;
            case R.id.btnWeb:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.semyng.ac.kr"));
                break;
            case R.id.btnContact:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people/"));
                break;
            case R.id.btnCamera:
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                break;
        }

        if(v.getId()==R.id.btnCamera){
            startActivityForResult(intent,1);
        }
        else{
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode==1) && (resultCode == RESULT_OK)) {
            Bitmap bitmap = data.getParcelableExtra("data");
            ImageView imageView = findViewById(R.id.iVCamera);
            imageView.setImageBitmap(bitmap);
        }
    }
}