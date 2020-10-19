package com.example.gastrocs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class kundeAnmelden extends AppCompatActivity {
    EditText benutzername, kennwort;
    Button anmeldebtn, registerbtn;
    DBHelper DB;
    int counter=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kunde_anmelden);
        benutzername = (EditText) findViewById(R.id.benutzername2);
        kennwort= (EditText) findViewById(R.id.kennwort2);
        anmeldebtn= (Button) findViewById(R.id.anmeldebtn2);
        registerbtn= (Button) findViewById(R.id.registerbtn2);
        DB= new DBHelper(this);
        this.counter=counter;
        anmeldebtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                counter--;
                if(counter<1){
                    Toast.makeText(kundeAnmelden.this, "Sie haben die Anazahl der erlaubten Versuche ueberschritten!",Toast.LENGTH_SHORT).show();

                    return;
                }
                String user=benutzername.getText().toString();
                String pass=kennwort.getText().toString();
                if(user.equals("")||pass.equals(""))
                    Toast.makeText(kundeAnmelden.this, "Bitte alle Felder ausfuellen",Toast.LENGTH_SHORT).show();
                else{
                    boolean checkuserpass=DB.checkBenutzernameKennwort(user,pass);

                    if(checkuserpass==true){
                        Toast.makeText(kundeAnmelden.this, "Anmeldung erfolgreich",Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(getApplicationContext(),kundeHome.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(kundeAnmelden.this, "Anmeldung fehlgeschlagen",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), kundeRegister.class);
                startActivity(intent);

            } });

    }}