package com.example.gastrocs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class restaurantRegister extends AppCompatActivity {
    EditText benutzername,kennwort,rekennwort, geheim1;
    Button registerbtn, anmeldebtn;
    DBHelperRestaurant DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_register);
        benutzername = (EditText) findViewById(R.id.benutzername);
        kennwort= (EditText) findViewById(R.id.kennwort);
        rekennwort= (EditText) findViewById(R.id.kennwortwiederholen);
        geheim1=(EditText) findViewById(R.id.geheimzahl);
        anmeldebtn= (Button) findViewById(R.id.anmeldebtn);
        registerbtn= (Button) findViewById(R.id.registerbtn);
        DB = new DBHelperRestaurant(this);
        anmeldebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=benutzername.getText().toString();
                String pass=kennwort.getText().toString();
                String repass=rekennwort.getText().toString();
              String geheim= geheim1.getText().toString();
                if(user.equals("")||pass.equals("")||repass.equals("")||geheim.equals("") )
                    Toast.makeText(restaurantRegister.this, "Bitte alle Felder ausfuellen",Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        boolean checkuser = DB.checkBenutzername(user);
                        int geheim1 = Integer.parseInt(geheim);
                        if(checkuser==false && geheim1==12345 ){
                            boolean insert= DB.insertData(user,pass);
                            if(insert==true){
                                Toast.makeText(restaurantRegister.this, "Registrierung erfolgreich",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(getApplicationContext(),restaurantHome.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(restaurantRegister.this, "Registrierung fehlgeschlagen", Toast.LENGTH_SHORT);

                            }
                        }
                        else{
                            Toast.makeText(restaurantRegister.this, "Benutzername schon registriert oder Geheimzahl falsch!",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(restaurantRegister.this, "Kennwoerter stimmen nicht ueberein!",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //wenn dieser Button geklickt wird, landen wir ind anm. Akt. Klasse
                Intent intent= new Intent(getApplicationContext(),restaurantAnmelden.class);
                startActivity(intent);

            }
        });
    }
}