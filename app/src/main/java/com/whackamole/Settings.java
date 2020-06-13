package com.whackamole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Settings extends AppCompatActivity {
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        final DatabaseReference backRef = database.getReference("Background");
        backRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                String value = dataSnapshot.getValue(String.class);
                View view =getWindow().getDecorView();
                switch (value) {
                    case "Savana":
                        view.setBackgroundResource(R.drawable.savana);
                        break;
                    case "Forest":
                        view.setBackgroundResource(R.drawable.forest);
                        break;
                    case "White":
                        view.setBackgroundResource(R.drawable.white);
                        break;
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Failed to read value." + error.toException(), Toast.LENGTH_SHORT).show();
            }
        });

        /* Create a listener for the Menu button so it will move to the Main page */
        findViewById(R.id.menu).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this, MainScreen.class);
                startActivity(intent);
            }
        });
    }

    public void onClickSoundOn(View view) {
        database =  FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Sound");
        myRef.setValue("On");

    }

    public void onClickSoundOff(View view) {
        database =  FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Sound");
        myRef.setValue("Mute");
    }

    public void onClickEasy(View view) {
        database =  FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Difficulty");
        myRef.setValue("Easy");
    }

    public void onClickNormal(View view) {
        database =  FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Difficulty");
        myRef.setValue("Normal");
    }

    public void onClickHard(View view) {
        database =  FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Difficulty");
        myRef.setValue("Hard");
    }

    public void onClickSavana(View view) {
        database =  FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Background");
        myRef.setValue("Savana");
    }

    public void onClickForest(View view) {
        database =  FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Background");
        myRef.setValue("Forest");
    }

    public void onClickWhite(View view) {
        database =  FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Background");
        myRef.setValue("White");
    }
}
