package com.example.gastrocs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class kundeRegister extends AppCompatActivity {
    EditText benutzername,kennwort,rekennwort;
    Button registerbtn, anmeldebtn;
    DBHelperKunde DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kunde_register);
        benutzername = (EditText) findViewById(R.id.benutzername);
        kennwort= (EditText) findViewById(R.id.kennwort);
        rekennwort= (EditText) findViewById(R.id.kennwortwiederholen);
        anmeldebtn= (Button) findViewById(R.id.anmeldebtn);
        registerbtn= (Button) findViewById(R.id.registerbtn);
        DB = new DBHelperKunde(this);
        anmeldebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=benutzername.getText().toString();
                String pass=kennwort.getText().toString();
                String repass=rekennwort.getText().toString();
                if(user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(kundeRegister.this, "Bitte alle Felder ausfuellen",Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        boolean checkuser = DB.checkBenutzername(user);
                        if(checkuser==false){
                            boolean insert= DB.insertData(user,pass);
                            if(insert==true){
                                Toast.makeText(kundeRegister.this, "Registrierung erfolgreich",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(getApplicationContext(),kundeAnmelden.class);
                                startActivity(intent);
                                benutzername.setText("");
                                kennwort.setText("");
                                rekennwort.setText("");
                            }
                            else{
                                Toast.makeText(kundeRegister.this, "Registrierung fehlgeschlagen",Toast.LENGTH_SHORT).show();

                            }
                        }
                        else{
                            Toast.makeText(kundeRegister.this, "Benutzername ist schon registriert!, bitte anmelden!",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(kundeRegister.this, "Kennwoerter stimmen nicht ueberein",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //wenn dieser Button geklickt wird, landen wir ind anm. Akt. Klasse
                Intent intent= new Intent(getApplicationContext(),kundeAnmelden.class);
                startActivity(intent);

            }
        });
    }
}