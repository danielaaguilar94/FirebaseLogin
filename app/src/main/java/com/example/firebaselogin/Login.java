package com.example.firebaselogin;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    TextView txtBienvenida;
    Button btnLogout;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        firebaseUser=mAuth.getCurrentUser();

        txtBienvenida = findViewById(R.id.textoBienvenida);
        btnLogout = findViewById(R.id.botonLogout);

        txtBienvenida.setText("Bienvenid@ "+firebaseUser.getEmail());

        /*Bundle bundle = getIntent().getExtras();

        if (bundle!=null)
        {
            String usuario = bundle.getString("user");
            txtBienvenida.setText("Bienvenid@ "+usuario);


        }
*/
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                mAuth.getInstance().signOut();
                finishAndRemoveTask();

            }
        });


    }


}
