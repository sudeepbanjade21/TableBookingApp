package com.example.sudeep.reastroapp;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;

import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class myTableActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    FirebaseAuth firebaseAuth;
    ListView mListView;
    FirebaseUser user;
    ArrayList<String> array;
    ArrayAdapter<String> adapter;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_table);
        user=FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();

        mDatabase = FirebaseDatabase.getInstance().getReference("Users");
        mListView = findViewById(R.id.listview);
        array = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, array);
        mListView.setAdapter(adapter);


        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                UserInformation uInfo=new UserInformation();

                uInfo.setName(dataSnapshot.getValue(UserInformation.class).getName());
                uInfo.setEmail(dataSnapshot.getValue(UserInformation.class).getEmail());
                uInfo.setPhone_Number(dataSnapshot.getValue(UserInformation.class).getPhone_Number());
                uInfo.setTime(dataSnapshot.getValue(UserInformation.class).getTime());
                uInfo.setDate(dataSnapshot.getValue(UserInformation.class).getDate());
                uInfo.setNumber_Of_Person(dataSnapshot.getValue(UserInformation.class).getNumber_Of_Person());


                array.add("Name:" +uInfo.getName()+"\nEmail:"+uInfo.getEmail()+"\nPhone No:"+uInfo.getPhone_Number()+"\nDate:"+uInfo.getDate()+"\nTime:"+uInfo.getTime()+"\nNo Of Persons:"+uInfo.getNumber_Of_Person()+"\n");


                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

