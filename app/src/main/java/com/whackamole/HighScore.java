package com.whackamole;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.String;
import java.util.ArrayList;
import java.util.List;


import static com.whackamole.R.*;


public class HighScore extends AppCompatActivity {

    TableLayout table;
    private static final int TABLE_WIDTH = 4;
    private static final int TABLE_HEIGHT = 6;
    private static final String[] titles = {
            "rank","name","score", "difficulty"};
    private static List<Score> scoreList = new ArrayList<Score>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_high_score);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference scoreRef = database.getReference("Score");
        scoreRef.orderByChild("Score").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                long points;
                String name, difficulty;
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    points = Long.parseLong(ds.child("points").getValue().toString());
                    name = ds.child("name").getValue().toString();
                    difficulty = ds.child("difficulty").getValue().toString();
                    Score score = new Score(points, name, difficulty);
                    scoreList.add(score);
                }
                CreateTable();
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Failed to read value." + error.toException(), Toast.LENGTH_SHORT).show();
            }
        });


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
        findViewById(id.menu).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HighScore.this, MainScreen.class);
                startActivity(intent);
            }
        });


    }

    private void CreateTable() {
        table = (TableLayout) findViewById(id.scores);

        // Populate the table with stuff
        for (int y = 0; y < TABLE_HEIGHT; y++) {
            TableRow r = new TableRow(this);
            table.addView(r);
            for (int x = 0; x < TABLE_WIDTH; x++) {
                TextView v = new TextView(this);
                v.setMinWidth(200);
                v.setTextSize(20);
                v.setTypeface(null, Typeface.BOLD);
                v.setBackgroundResource(R.drawable.back);
                v.setTextColor(getResources().getColor(color.black));
                if(y == 0)
                    v.setText(titles[x]);
                else if(x == 0)
                    v.setText(String.valueOf(y));
                else if(scoreList.size() >= y && x ==1)
                    v.setText(scoreList.get(y-1).name);
                else if(scoreList.size() >= y && x ==2)
                    v.setText(String.valueOf(scoreList.get(y-1).points));
                else if(scoreList.size() >= y && x ==3)
                    v.setText(scoreList.get(y-1).difficulty);
                else
                    v.setText("0");
                r.addView(v);
            }
        }
    }


}
