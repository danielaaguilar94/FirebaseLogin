package com.example.firebaselogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

   // declaramos los views;
    EditText etCorreo, etContraseña;
    Button btnRegistrarUsuario, btnLoguearUsuario;
    //Se declara la instancia de FirebaseAuth
    private FirebaseAuth firebaseAuth;
    //se declara un ProgressDialog para mostrar mientras registra usuario
    private ProgressDialog dialogoProgreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //se inicializa la instancia FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();

        //referenciamos los views EditText
        etCorreo= findViewById(R.id.editTextCorreo);
        etContraseña=findViewById(R.id.editTextContraseña);
        btnRegistrarUsuario=findViewById(R.id.botonRegistrar);
        btnLoguearUsuario = findViewById(R.id.botonLogin);

            //Se crea un objeto ProgressDialog para aviso al usuario del progreso de autenticación
        dialogoProgreso = new ProgressDialog(this);
        //Se agrega la funcionalidad onClickListener
        btnRegistrarUsuario.setOnClickListener(this);
        btnLoguearUsuario.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.botonRegistrar:
                //al presionar botón registrar usuario, se ejecuta el método registrar usuario
                registrarUsuarios();
                break;
            case R.id.botonLogin:
                loguearUsuario();
                break;
        }

    }

    private void loguearUsuario() {
        final String correo, contraseña;
        correo=etCorreo.getText().toString().trim();
        contraseña=etContraseña.getText().toString().trim();

        //si lo que hay en la caja de Texto asignada a correo está vacía
        if(correo.equals(""))
        {
            Toast.makeText(getApplicationContext(), "Ingrese correo",Toast.LENGTH_SHORT).show();
            return;
            // etCorreo.setError("Debe ingresar un correo");
        }
        //si lo que hay en la caja de Texto asignada a contraseña está vacía
        if(contraseña.equals(""))
        {
            Toast.makeText(getApplicationContext(),"Falta ingresar contraseña",Toast.LENGTH_SHORT).show();
            return;
        }
        //si las dos sentencias se cumplen, prosigue con el registro, se muestra un mensaje con una ProgressDialog
        dialogoProgreso.setMessage("Consultando usuario para iniciar sesión....");
        //se muestra en pantalla
        dialogoProgreso.show();
        //se asigna el metodo createUserWithEmailAndPassword a la la instancia de FirebaseAuth
        firebaseAuth.signInWithEmailAndPassword(correo, contraseña)//se reciben como argumentos los Strings de correo y contraseña
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {//si el proceso de Autenticación se realiza con éxito
                            // Sign in success, update UI with the signed-in user's information
                            //Se muestra un mensaje al usuario mediante un Toast
                            Toast.makeText(getApplicationContext(), "Bienvenido usuario:  "+etCorreo.getText().toString(),Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),Login.class);
                            //Bundle bundle = new Bundle();
                            //bundle.putString("user", correo);
                            //intent.putExtras(bundle);
                            startActivity(intent);


                            //FirebaseUser user = firebaseAuth.getCurrentUser();
                            //updateUI(user);
                        }
                        else
                        {

                                Toast.makeText(getApplicationContext(), "No se pudo loguear: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }

                        dialogoProgreso.dismiss();
                    }

                });
    }

    private void registrarUsuarios() {


        final String correo, contraseña;
        correo=etCorreo.getText().toString().trim();
        contraseña=etContraseña.getText().toString().trim();

        //si lo que hay en la caja de Texto asignada a correo está vacía
        if(correo.equals(""))
        {
            Toast.makeText(getApplicationContext(), "Ingrese correo",Toast.LENGTH_SHORT).show();
            return;
           // etCorreo.setError("Debe ingresar un correo");
        }
        //si lo que hay en la caja de Texto asignada a contraseña está vacía
        if(contraseña.equals(""))
        {
            Toast.makeText(getApplicationContext(),"Falta ingresar contraseña",Toast.LENGTH_SHORT).show();
            return;
        }
        //si las dos sentencias se cumplen, prosigue con el registro, se muestra un mensaje con una ProgressDialog
        dialogoProgreso.setMessage("Registrando usuario, espere....");
        //se muestra en pantalla
        dialogoProgreso.show();
        //se asigna el metodo createUserWithEmailAndPassword a la la instancia de FirebaseAuth
        firebaseAuth.createUserWithEmailAndPassword(correo, contraseña)//se reciben como argumentos los Strings de correo y contraseña
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {//si el proceso de Autenticación se realiza con éxito
                            // Sign in success, update UI with the signed-in user's information
                            //Se muestra un mensaje al usuario mediante un Toast
                            Toast.makeText(getApplicationContext(), "Usuario registrado con éxito:  "+etCorreo.getText().toString(),Toast.LENGTH_SHORT).show();
                            //FirebaseUser user = firebaseAuth.getCurrentUser();
                            //updateUI(user);
                        }
                        else
                            {
                                if (task.getException() instanceof FirebaseAuthUserCollisionException)
                                {
                                    Toast.makeText(getApplicationContext(), "El usuario ya existe", Toast.LENGTH_SHORT).show();
                                }
                                else
                                //si no se cumple, se muestra un mensaje en el Toast seguido de la excepción que se produjo en proceso
                                {
                                    Toast.makeText(getApplicationContext(), "No se pudo registrar: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }
                        }
                        dialogoProgreso.dismiss();
                    }
                });

    }

}
