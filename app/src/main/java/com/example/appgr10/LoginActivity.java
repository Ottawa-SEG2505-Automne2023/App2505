package com.example.appgr10;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

        private final String ADMIN = "admin";
        private final String ADMINPW = "1234@abc";
        private EditText edUsername, edPassword; // entrée username et password
        private Button btn; // boutonLogin
        private TextView tv; // tv pour textView
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            init(); // initialisation des E/S

            // lorsqu'on presse sur le bouton pour se connecter
            btn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) { // la fonction ici est exécutée lorsqu'on presse sur le bouton
//                    Database db = new Database(getApplicationContext(), "novigrad", null, 1);
//                    db.register("Bob", "bob@gmail.com", "1234@abc", "true");
//                    db.register("Robert", "robibi@gmail.com", "1234@abc", "false");
                    //startActivity(new Intent(LoginActivity.this, HomeActivity.class));
//                         lire username et password
                    String username = edUsername.getText().toString();
                    String password = edPassword.getText().toString();
                    Database db = new Database(getApplicationContext(), "novigrad", null, 1); // création de la database "novigrad"
                    if(username.isEmpty() || password.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Please fill All details", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        if(username.equals("admin") && password.equals("1234@abc"))  { // ADMIN
                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                        } else { // autre
                            if(db.login(username, password)) { // les informations existent dans la base de données
                                Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();

                                // partager les infos avec la page suivante
                                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("username", username);
                                editor.apply();

                            } else {
                                Toast.makeText(getApplicationContext(), "Invalid Username and Password", Toast.LENGTH_SHORT).show();
                                edUsername.setText("");
                                edPassword.setText("");
                            }
                        }
                    }
                }
            });

            // on clique pour enregistrer un nouvel utilisateur
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // aller à la page d'entregistrement
                    startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                }
            });
        }

        /**
         * Initialisation des entrées et des sorties
         */
        private void init() {
            edUsername = findViewById(R.id.editTextLoginUsername);
            edPassword = findViewById(R.id.editTextLoginPassword);
            btn= findViewById(R.id.buttonLogin);
            tv = findViewById(R.id.textViewNewUser);

            edUsername.getText().clear();
            edPassword.getText().clear();
        }
}