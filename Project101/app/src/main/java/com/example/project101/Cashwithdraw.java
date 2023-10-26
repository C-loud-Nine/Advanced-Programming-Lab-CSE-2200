package com.example.project101;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Cashwithdraw extends AppCompatActivity {

    TextInputLayout amount;
    Button withdrawbtn,backwithdraw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_cashwithdraw);

        amount = findViewById(R.id.withdrawamount);
        withdrawbtn = findViewById(R.id.btncashwithdraw);
        backwithdraw= findViewById(R.id.btn_cashwithdraw_back);


        withdrawbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amountUser(view);
            }
        });

        backwithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Cashwithdraw.this, Dashboard.class);
                startActivity(intent);
            }
        });


    }

    private Boolean amount_valid() {
        String val = amount.getEditText().toString();
        if (val.isEmpty()) {
            amount.setError("Field Cannot be Empty");
            return false;
        }
        else {
            amount.setError(null);
            amount.setErrorEnabled(false);
            return true;
        }
    }

    public void amountUser(View view) {
        if (!amount_valid()) {
            return;
        } else {
            okAmount();
        }
    }

    private boolean iswithdrawn() {
        final String enteredAmount = amount.getEditText().getText().toString().trim();

        SharedPreferences sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
        String cardno = sharedPreferences.getString("cardno", "");
        String balance = sharedPreferences.getString("balance", "");


        int int_enteredAmount = Integer.parseInt(enteredAmount);
        int int_balance = Integer.parseInt(balance);


        if (int_balance>=int_enteredAmount){

            int int_newbalance=int_balance-int_enteredAmount;
            String newBalanceStr = Integer.toString(int_newbalance);

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
            reference.child(cardno).child("balance").setValue(newBalanceStr);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("balance", newBalanceStr);
            editor.apply();
            return true;
        } else {
            Toast.makeText(this, "Insufficient Balance", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    public void okAmount(){
        if(iswithdrawn()){
            Intent intent = new Intent(Cashwithdraw.this, UserProfile.class);
            startActivity(intent);
        } else {
            amount.setError("Wrong Amount");
            amount.requestFocus();
        }
    }

}
