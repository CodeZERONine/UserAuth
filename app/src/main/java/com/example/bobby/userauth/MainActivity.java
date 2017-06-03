package com.example.bobby.userauth;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.stats.StatsEvent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private static final String TAG ="Success" ;
    private EditText et1;
    private EditText et2;
    private Button bt1;
    private Button bt2;
    private FirebaseAuth mAuth;
    private FirebaseUser user=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth=FirebaseAuth.getInstance();

        et1=(EditText)findViewById(R.id.editText1);
        et2=(EditText)findViewById(R.id.editText2);
        bt1=(Button)findViewById(R.id.button1);
        bt2=(Button)findViewById(R.id.button2);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et1.getText().toString().trim();
                String password = et2.getText().toString().trim();

                mAuth.signInWithEmailAndPassword(username, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    Toast.makeText(MainActivity.this, "LOGIN SUCCESS !", Toast.LENGTH_LONG).show();
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    startActivity(new Intent(MainActivity.this,StatusActivity.class));
                                    //updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(MainActivity.this, "Sign In Fail", Toast.LENGTH_SHORT).show();

                                   // updateUI(null);
                                }

                                // ...
                            }
                        });
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent up=new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(up);
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }
}
