package com.example.project101;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    TextInputLayout regName, regBalance, regPin, regCardno, regBranch;

    Button regBtn, regToLoginBtn;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);

        regName=findViewById(R.id.reg_name);
        regBalance=findViewById(R.id.reg_balance);
        regBranch=findViewById(R.id.reg_branch);
        regPin=findViewById(R.id.reg_pin);
        regCardno=findViewById(R.id.reg_cardno);
        regBtn=findViewById(R.id.btnreg);
        regToLoginBtn=findViewById(R.id.calllogin);





        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode=FirebaseDatabase.getInstance();
                reference=rootNode.getReference("users");
                String name=regName.getEditText().getText().toString();
                String balance=regBalance.getEditText().getText().toString();
                String branch=regBranch.getEditText().getText().toString();
                String cardno=regCardno.getEditText().getText().toString();
                String pin=regPin.getEditText().getText().toString();


                UserHelperClass helperclass= new UserHelperClass(name,balance,branch,cardno,pin);
                reference.child(cardno).setValue(helperclass);

                Toast.makeText(Register.this, "Registration Confirmed !", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });

        regToLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });


    }
}

