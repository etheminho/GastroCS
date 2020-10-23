package com.example.gastrocs;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class GerichtAnlegen extends AppCompatActivity {
    EditText nameTxt, preisTxt;
    Button showBtn, waehlenBtn, addBtn;
    ImageView imgView;
    final int code = 999;
    public static DBHelperGerichtAnlegen dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gericht_anlegen);
        init();
        dbhelper = new DBHelperGerichtAnlegen(this, "gerichtAnlegen.sqlite", null, 1);
        dbhelper.queryDaten("CREATE TABLE IF NOT EXISTS Gericht (id INTEGER, name Text, preis Text, image BLOB)");
        waehlenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        GerichtAnlegen.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        code
                );
            }
        });


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    dbhelper.insertData(nameTxt.getText().toString().trim(),
                            preisTxt.getText().toString().trim(),
                            ImageViewToByte(imgView));
                    Toast.makeText(getApplicationContext(), "Gericht wurde erfolgreich hinzugefügt", Toast.LENGTH_SHORT).show();
                    nameTxt.setText("");
                    preisTxt.setText("");
                    imgView.setImageResource(R.mipmap.ic_launcher);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });

    }

    private byte[] ImageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == code) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, code);
            } else {
                Toast.makeText(getApplicationContext(), "Problem aufgetreten", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == code && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void init() {
        nameTxt = findViewById(R.id.name);
        preisTxt = findViewById(R.id.preis);

        waehlenBtn = findViewById(R.id.imgWaehlen);
        imgView = findViewById(R.id.gerichtFoto);
        addBtn = findViewById(R.id.addButton);
    }
}