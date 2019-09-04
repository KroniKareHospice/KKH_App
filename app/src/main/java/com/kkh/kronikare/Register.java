package com.kkh.kronikare;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    EditText email,name,phone,pwd;
    Button signUp;
    static int attempt=0;
//    ProgressBar progressBar;
    FirebaseAuth mAuth;
    FirebaseDatabase db;
    private FirebaseUser firebaseUser;
    DatabaseReference dbref;
    String uname,uemail,uphone,upwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = (EditText) findViewById(R.id.email);
        name = (EditText) findViewById(R.id.name);
        phone = (EditText) findViewById(R.id.phone);
        pwd = (EditText) findViewById(R.id.pwd);
        signUp = (Button)findViewById(R.id.signUp);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        dbref = db.getReference("KKH/User");
//        progressBar.setVisibility(View.GONE);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

    }



    protected  void registerUser()
    {
        uname = name.getText().toString();
        uemail = email.getText().toString();
        uphone = phone.getText().toString();
        upwd = pwd.getText().toString();
        if(uname.equals(""))
        {
           Toast.makeText(Register.this,"Enter your Name",Toast.LENGTH_SHORT).show();
        }
        else if(uemail.equals(""))
        {
            Toast.makeText(Register.this,"Enter your Email",Toast.LENGTH_SHORT).show();
        }
        else if(uphone.equals(""))
        {
         Toast.makeText(Register.this,"Enter your Phone",Toast.LENGTH_SHORT).show();
        }
        else if(upwd.equals(""))
        {
            Toast.makeText(Register.this,"Enter password",Toast.LENGTH_SHORT).show();
        }
        else if(uphone.length()!=10)
        {
            Toast.makeText(Register.this,"Enter phone number correctly!!",Toast.LENGTH_SHORT).show();
        }
        else if(upwd.length()<8 || upwd.length()>15)
        {
            Toast.makeText(Register.this,"Password length should be atleast 8 characters and max 15",Toast.LENGTH_SHORT).show();
        }
        else
        {
            //progressBar.setVisibility(View.VISIBLE);
            mAuth.createUserWithEmailAndPassword(uemail,upwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
//                    Toast.makeText(Register.this,"Inside OnComplete",Toast.LENGTH_SHORT).show();
                    if(task.isSuccessful())
                    {
                        User user = new User(uname,uphone);

                        String id=dbref.push().getKey();
                        Toast.makeText(Register.this, id+"   "+(++attempt), Toast.LENGTH_SHORT).show();
                        dbref.child(id).setValue(user);
                        firebaseUser=mAuth.getCurrentUser();
                        firebaseUser.sendEmailVerification()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful())
                                        {
                                            Toast.makeText(Register.this,"Email Sent For Verification!! Please Verify",Toast.LENGTH_SHORT).show();
                                        }
                                        else
                                        {
                                            Toast.makeText(Register.this,"Could Not Send Email!!",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                    else
                    {
                        Toast.makeText(Register.this,"Registration Unsuccessful!!"+"   "+(++attempt),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }



}
