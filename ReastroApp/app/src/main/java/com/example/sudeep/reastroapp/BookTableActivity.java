package com.example.sudeep.reastroapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.santalu.maskedittext.MaskEditText;

import java.util.HashMap;

public class BookTableActivity extends AppCompatActivity {
    private EditText editTextName,editTextEmail,editTextNumberOfPerson;
    private MaskEditText editTextPhone,editTextTime,editTextDate;
    private Button buttonBook;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_table);
        mDatabase = FirebaseDatabase.getInstance().getReference("Users");

        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextNumberOfPerson = findViewById(R.id.editTextNumberOfPerson);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextTime = findViewById(R.id.editTextTime);
        editTextDate = findViewById(R.id.editTextDate);
        buttonBook = findViewById(R.id.buttonBook);

        buttonBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = editTextName.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String noOfPerson = editTextNumberOfPerson.getText().toString().trim();
                String phone = editTextPhone.getText().toString().trim();
                String time = editTextTime.getText().toString().trim();
                String date = editTextDate.getText().toString().trim();

                HashMap<String, String> dataMap = new HashMap<String, String>();
                dataMap.put("Name", name);
                dataMap.put("Email", email);
                dataMap.put("Phone_Number", phone);
                dataMap.put("Time", time);
                dataMap.put("Date", date);
                dataMap.put("Number_Of_Person", noOfPerson);

                mDatabase.push().setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            new AlertDialog.Builder(BookTableActivity.this)
                                    .setMessage("Your Table has been booked")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            finish();
                                            Intent intent = new Intent(BookTableActivity.this, HomeActivity.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            startActivity(intent);
                                        }

                                    }).show();


                        } else {
                            new AlertDialog.Builder(BookTableActivity.this)
                                    .setMessage("Your Table has not been booked")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            finish();
                                            Intent intent = new Intent(BookTableActivity.this, HomeActivity.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            startActivity(intent);
                                        }

                                    }).show();
                        }

                    }


                });
            }
        });
    }}






