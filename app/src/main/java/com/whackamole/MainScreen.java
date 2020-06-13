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


public class MainScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
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

        /* Create a listener for the Play button so it will move to the game page */
        findViewById(R.id.game).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this, game.class);
             startActivity(intent);
        }
        });
        /* Create a listener for the High Score button so it will move to the High Score page*/
        findViewById(R.id.highscore).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this, HighScore.class);
                startActivity(intent);
            }
        });
        /* Create a listener for the Instructions button so it will move to the Instructions page*/
        findViewById(R.id.instructions).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this, Instructions.class);
                startActivity(intent);
            }
        });
        /* Create a listener for the Settings button so it will move to the Settings page*/
        findViewById(R.id.settings).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this, Settings.class);
                startActivity(intent);
            }
        });
    }
}
