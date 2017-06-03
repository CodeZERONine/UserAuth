package com.example.bobby.userauth;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StatusActivity extends AppCompatActivity {
    private static final String TAG = "Hulu";
    private EditText status;
    private Button verify;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        status = (EditText) findViewById(R.id.status);
        mAuth = FirebaseAuth.getInstance();
        verify = (Button) findViewById(R.id.button4);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                user.sendEmailVerification()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "Email sent.");
                                    Toast.makeText(StatusActivity.this, "Verification Email Sent to your Email", Toast.LENGTH_SHORT).show();
                                    if (user.isEmailVerified()) {
                                        status.setText("Verified:True");
                                    } else {
                                        status.setText("Verified:False");
                                    }
                                }
                            }
                        });
            }
        });

        if (user.isEmailVerified()) {
            status.setText("Verified:True");
        } else {
            status.setText("Verified:False");
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    }
}
