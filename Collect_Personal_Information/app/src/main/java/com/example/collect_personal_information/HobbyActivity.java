package com.example.collect_personal_information;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class HobbyActivity extends AppCompatActivity {
    Spinner spinnerMusic, spinnerSport, spinnerMovie, spinnerPet;
    Button btnConfirm;
    EditText music, sport, movie, pet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hobby);

        spinnerMusic = (Spinner) findViewById(R.id.spnMusic);
        spinnerSport = (Spinner) findViewById(R.id.spnSport);
        spinnerMovie = (Spinner) findViewById(R.id.spnMovie);
        spinnerPet = (Spinner) findViewById(R.id.spnPet);
        btnConfirm = (Button) findViewById(R.id.btnConfirm);
        music = (EditText) findViewById(R.id.txtMusic1);
        sport = (EditText) findViewById(R.id.txtSport1);
        movie = (EditText) findViewById(R.id.txtMovie1);
        pet = (EditText) findViewById(R.id.txtPet1);

        music.setVisibility(View.GONE);
        sport.setVisibility(View.GONE);
        movie.setVisibility(View.GONE);
        pet.setVisibility(View.GONE);

        music.setVisibility(View.GONE);
        ArrayList<String> arrMusic = new ArrayList<>();
        arrMusic.add("Rap");
        arrMusic.add("Country music");
        arrMusic.add("Other");

        ArrayAdapter arrAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrMusic);
        arrAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMusic.setAdapter(arrAdapter);
        spinnerMusic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 2)
                    music.setVisibility(View.VISIBLE);
                else
                    music.setVisibility(View.GONE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayList<String> arrSport = new ArrayList<>();
        arrSport.add("Soccer");
        arrSport.add("Table tennis");
        arrSport.add("Other");
        ArrayAdapter arrAdapter1 = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrSport);
        arrAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSport.setAdapter(arrAdapter1);
        spinnerSport.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 2)
                    sport.setVisibility(View.VISIBLE);
                else
                    sport.setVisibility(View.GONE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayList<String> arrMovie = new ArrayList<>();
        arrMovie.add("Action");
        arrMovie.add("Comedies");
        arrMovie.add("Other");
        ArrayAdapter arrAdapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrMovie);
        arrAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMovie.setAdapter(arrAdapter2);
        spinnerMovie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 2)
                    movie.setVisibility(View.VISIBLE);
                else
                    movie.setVisibility(View.GONE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayList<String> arrPet = new ArrayList<>();
        arrPet.add("Dog");
        arrPet.add("Cat");
        arrPet.add("Other");
        ArrayAdapter arrAdapter3 = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrPet);
        arrAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPet.setAdapter(arrAdapter3);
        spinnerPet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 2)
                    pet.setVisibility(View.VISIBLE);
                else
                    pet.setVisibility(View.GONE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sportStr;
                String movieStr;
                String petStr;
                String musicStr;
                if (spinnerMusic.getSelectedItem().toString().equals("Other")) {
                    musicStr = music.getText().toString();
                }
                else {
                    musicStr = spinnerMusic.getSelectedItem().toString();
                }
                if (spinnerSport.getSelectedItem().toString().equals("Other")){
                    sportStr = sport.getText().toString();
                }
                else{
                    sportStr = spinnerSport.getSelectedItem().toString();
                }
                if (spinnerMovie.getSelectedItem().toString().equals("Other")){
                    movieStr = movie.getText().toString();
                }
                else{
                    movieStr = spinnerMovie.getSelectedItem().toString();
                }
                if (spinnerPet.getSelectedItem().toString().equals("Other")){
                    petStr = pet.getText().toString();
                }
                else{
                    petStr = spinnerPet.getSelectedItem().toString();
                }
                Bundle bundle = new Bundle();
                bundle.putString("music", musicStr);
                bundle.putString("sport", sportStr);
                bundle.putString("movie", movieStr);
                bundle.putString("pet", petStr);

                Intent intent = new Intent(HobbyActivity.this, InputActivity.class);
                intent.putExtra("DATA", bundle);
                setResult(InputActivity.Save, intent);
                finish();
            }
        });

        try{
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            if (bundle.getInt("key")==1) {
                String ms, sp, mv, p;
                ms = bundle.getString("music");
                sp = bundle.getString("sport");
                mv = bundle.getString("movie");
                p = bundle.getString("pet");
                if (spinnerMusic.getItemAtPosition(0).toString().equals(ms)) {
                    spinnerMusic.setSelection(0);
                } else if (spinnerMusic.getItemAtPosition(1).toString().equals(ms)) {
                    spinnerMusic.setSelection(1);
                } else {
                    spinnerMusic.setSelection(2);
                    music.setVisibility(View.VISIBLE);
                    music.setText(ms);
                }

                if (spinnerSport.getItemAtPosition(0).toString().equals(sp)) {
                    spinnerSport.setSelection(0);
                } else if (spinnerSport.getItemAtPosition(1).toString().equals(sp)) {
                    spinnerSport.setSelection(1);
                } else {
                    spinnerSport.setSelection(2);
                    sport.setVisibility(View.VISIBLE);
                    sport.setText(sp);
                }

                if (spinnerMovie.getItemAtPosition(0).toString().equals(mv)) {
                    spinnerMovie.setSelection(0);
                } else if (spinnerMovie.getItemAtPosition(1).toString().equals(mv)) {
                    spinnerMovie.setSelection(1);
                } else {
                    spinnerMovie.setSelection(2);
                    movie.setVisibility(View.VISIBLE);
                    movie.setText(mv);
                }

                if (spinnerPet.getItemAtPosition(0).toString().equals(p)) {
                    spinnerPet.setSelection(0);
                } else if (spinnerPet.getItemAtPosition(1).toString().equals(p)) {
                    spinnerPet.setSelection(1);
                } else {
                    spinnerPet.setSelection(2);
                    pet.setVisibility(View.VISIBLE);
                    pet.setText(p);
                }
            }
        }
        catch (Exception e){ }
    }

    @Override
    public void onBackPressed() {
        setResult(Activity.RESULT_CANCELED);
        super.onBackPressed();
    }
}