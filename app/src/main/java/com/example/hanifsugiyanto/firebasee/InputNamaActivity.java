package com.example.hanifsugiyanto.firebasee;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InputNamaActivity extends AppCompatActivity {

    Button btn_login;
    EditText input_username;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_nama);


        //initialisation
        btn_login = (Button) findViewById(R.id.btn_login);
        input_username = (EditText) findViewById(R.id.input_username);

        btn_login.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                String username = input_username.getText().toString();

                startActivity(new Intent(InputNamaActivity.this, MainActivity.class).putExtra("username",username));

            }
        });


    }

}
