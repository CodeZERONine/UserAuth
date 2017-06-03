package com.example.bobby.userauth;

 import android.content.Intent;
 import android.support.annotation.NonNull;
 import android.support.annotation.Nullable;
 import android.support.v7.app.AppCompatActivity;
 import android.os.Bundle;
 import android.util.Log;
 import android.view.View;
 import android.widget.Button;
 import android.widget.EditText;
 import android.widget.Toast;

 import com.google.android.gms.tasks.OnCompleteListener;
 import com.google.android.gms.tasks.Task;
 import com.google.firebase.auth.AuthResult;
 import com.google.firebase.auth.FirebaseAuth;
 import com.google.firebase.auth.FirebaseUser;


public class RegisterActivity extends AppCompatActivity{
    private static final String TAG ="Success" ;
    EditText et3;
    EditText et4;
    Button bt3;
   private  FirebaseAuth mAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth=FirebaseAuth.getInstance();

        et3=(EditText)findViewById(R.id.editText3);
        et4=(EditText)findViewById(R.id.editText4);
        bt3=(Button)findViewById(R.id.button3);
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String email=et3.getText().toString().trim();
                String pass=et3.getText().toString().trim();
                mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();

                            Toast.makeText(RegisterActivity.this, "Registration success!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                        }
                        else{
                            Toast.makeText(RegisterActivity.this, "Register Failure", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();


    }
}
