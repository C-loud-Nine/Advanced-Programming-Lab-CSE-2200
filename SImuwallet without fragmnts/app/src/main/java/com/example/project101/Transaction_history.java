package com.example.project101;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.WindowManager;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Transaction_history extends AppCompatActivity {
    ListView transactionListView;
    TransactionHistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_transaction_history);

        transactionListView = findViewById(R.id.transactionListView);
        SharedPreferences sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
        String cardno = sharedPreferences.getString("cardno", "");
        // Retrieve the user's transaction history from Firebase
        DatabaseReference historyRef = FirebaseDatabase.getInstance().getReference("users/" + cardno + "/transactionHistory");

        historyRef.addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    ArrayList<TransactionHistoryItem> transactionHistory = new ArrayList<>();

                    for (DataSnapshot transactionSnapshot : dataSnapshot.getChildren()) {
                        String amount = transactionSnapshot.child("amount").getValue(String.class);
                        String date = transactionSnapshot.child("date").getValue(String.class);
                        String type = transactionSnapshot.child("type").getValue(String.class);
                        TransactionHistoryItem item = new TransactionHistoryItem(amount, date, type);
                        transactionHistory.add(item);
                    }

                    // Set up the adapter to display the transaction history
                    adapter = new TransactionHistoryAdapter(Transaction_history.this, transactionHistory);
                    transactionListView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle database errors if needed
            }
        });
    }
}
