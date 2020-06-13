package com.whackamole;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.google.firebase.database.FirebaseDatabase;


public class Settings extends AppCompatActivity {
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        SharedPreferences sharedpreferences = getSharedPreferences("WhackAMole", Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();

        String value = sharedpreferences.getString("Background","Savana");
        View settingView = findViewById(R.id.setting);
        switch (value) {
            case "Savana":
                settingView.setBackgroundResource(R.drawable.savana);
                break;
            case "Forest":
                settingView.setBackgroundResource(R.drawable.forest);
                break;
            case "White":
                settingView.setBackgroundResource(R.drawable.white);
                break;
        }

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
        editor.putString("Sound", "On");
        editor.commit();
        RadioGroup group = findViewById(R.id.sound);
        group.check(group.getCheckedRadioButtonId());

    }

    public void onClickSoundOff(View view) {
        editor.putString("Sound", "Mute");
        editor.commit();
        RadioGroup group = findViewById(R.id.sound);
        group.check(group.getCheckedRadioButtonId());
    }

    public void onClickEasy(View view) {
        editor.putString("Difficulty", "Easy");
        editor.commit();
        RadioGroup group = findViewById(R.id.difficulty);
        group.check(group.getCheckedRadioButtonId());
    }

    public void onClickNormal(View view) {
        editor.putString("Difficulty", "Normal");
        editor.commit();
        RadioGroup group = findViewById(R.id.difficulty);
        group.check(group.getCheckedRadioButtonId());
    }

    public void onClickHard(View view) {
        editor.putString("Difficulty", "Hard");
        editor.commit();
        RadioGroup group = findViewById(R.id.difficulty);
        group.check(group.getCheckedRadioButtonId());
    }

    public void onClickSavana(View view) {
        editor.putString("Background", "Savana");
        editor.commit();
        View settingView = findViewById(R.id.setting);
        settingView.setBackgroundResource(R.drawable.savana);
        RadioGroup group = findViewById(R.id.background);
        group.check(group.getCheckedRadioButtonId());

    }

    public void onClickForest(View view) {
        editor.putString("Background", "Forest");
        editor.commit();
        View settingView = findViewById(R.id.setting);
        settingView.setBackgroundResource(R.drawable.forest);
        RadioGroup group = findViewById(R.id.background);
        group.check(group.getCheckedRadioButtonId());

    }

    public void onClickWhite(View view) {
        editor.putString("Background", "White");
        editor.commit();
        View settingView = findViewById(R.id.setting);
        settingView.setBackgroundResource(R.drawable.white);
        RadioGroup group = findViewById(R.id.background);
        group.check(group.getCheckedRadioButtonId());
    }
}
