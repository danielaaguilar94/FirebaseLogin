# FirebaseLogin
Un Login Implementando autenticación Firebase


La aplicación emplea la autenticación mediante Firebase.
Se hacen las configuraciones siguiendo los pasos de la consola de Firebase, como lo es la colocación de dependencias
y plugins en el gradle de la app.
Consta de una pantalla la cual se compone de componentes como lo es el ImageView, dos EditText, y dos botones.
También se emplea un ProgressDialog para mostrar de manera más visual el progreso de cada proceso.
El botón de Registrar Usuario tiene la accion de registrarUsuarios() y en la cual se emplea FirebaseAuth.
Para implementar FirebaseAuth se declara la instancia y se inicializa en el método onCreate() de la Actividad. 
Esto con el fin de hacer uso de esta en cada caso que se requiera, ya sea para crear nuevos usuarios con el método
createUserWithEmailAndPassword el cual recibe los parámetros del correo y contraseña ya obtenidos de las cajas de texto
o EditText, y se asigna el método addOnCompleteListener que será encargado de realizar dicho proceso de creación, y si 
se completa la operación con éxito, nos muestra un Toast con un mensaje, entrando a otra condición, nos mostrará un mensaje que el
usuario ya existe empleando un método llamado FirebaseAuthUserCollisionException y si no se cumple un mensaje que no se pudo 
registrar seguido del mensaje de la excepción que surgió en el task o proceso.

En el caso de LoguearUsuarios, sucede con la misma estructura de la creación de los usuarios, solamente que cambia el 
método por el de signInWithEmailAndPassword() y al ser el task exitoso, nos muestra un toast con el email y se crea un Intent
para iniciar la otra pantalla o actividad.
en caso contrario, mostrar que no se pudo Loguear, segudio del mensaje de la excepción del task.
En la siguiente Actividad llamada Login, que consta de un TextView y un boton Logout el cual sera el encargado de cerrar 
el Login del usuario ingresado. También se instancia y se inicializa el FirebaseAuth y ésta vez declaramos e inicializamos
FirebaseUser el cual será el encargado de ver el usuario que está logueado.
Se muestra el el TextView un mensaje de bienvenida seguido del email del usuario.

Se agrega la acción al botón de SignOut obteniendo la instancia del FirebaseAuth y así se desconecta el usuario.
con el método finishAndRemoveTask(); cerramos la actividad y removemos el proceso. para así regresar a la pantalla principal
de nuevo.





