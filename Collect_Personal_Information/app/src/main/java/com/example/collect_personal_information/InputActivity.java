package com.example.collect_personal_information;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class InputActivity extends AppCompatActivity {
    public static final int New_PerInfo = 112;
    public static final int New_Hobby = 113;
    public static final int New_ContInfo = 114;
    public static final int New_Work = 115;
    public static final int Save = 116;

    SQLiteDatabase db;
    Button btnPerInfo, btnHobby, btnContInfo, btnWork, btnSave;
    String id = "", name = "", dateOfBirth= "", gender= "", identity= "", music= "", sport= "", movie= "", pet= "", phone= "", email= "", home= "", job= "", position= "", workplace= "", salary= "";
    int check = 0, key = 0, i = 0, idChecked;
    byte[] image;
    Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        btnPerInfo = (Button) findViewById(R.id.btnPerInfo);
        btnHobby = (Button) findViewById(R.id.btnHobby);
        btnContInfo = (Button) findViewById(R.id.btnContInfo);
        btnWork = (Button) findViewById(R.id.btnWork);
        btnSave = (Button) findViewById(R.id.btnSave);

        //Show data to Output
        try {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            person = (Person) bundle.getSerializable("ps");
            id = person.getId_person();
            name = person.getName();
            dateOfBirth = person.getDateOfBirth();
            gender = person.getGender();
            identity = person.getIdentityCard();
            image = person.getImage();
            music = person.getMusic();
            sport = person.getSport();
            movie = person.getMovie();
            pet = person.getPet();
            phone = person.getPhoneNumber();
            email = person.getEmail();
            home = person.getHomeAddress();
            job = person.getJob();
            position = person.getPosition();
            workplace = person.getWorkplaceAddress();
            salary = person.getSalary();
            check = 1;
            key = 1;
        }
        catch (Exception e) {

        }

        btnPerInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bdl = new Bundle();
                bdl.putString("name", name);
                bdl.putString("dateOfBirth", dateOfBirth);
                bdl.putString("gender", gender);
                bdl.putString("identity", identity);
                bdl.putByteArray("image", image);
                Intent intentPerInfo = new Intent(InputActivity.this, PersonalInformationActivity.class);
                intentPerInfo.putExtras(bdl);
                startActivityForResult(intentPerInfo, New_PerInfo);
            }
        });

        btnHobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bdl = new Bundle();
                bdl.putString("music", music);
                bdl.putString("sport", sport);
                bdl.putString("movie", movie);
                bdl.putString("pet", pet);
                bdl.putInt("key", key);
                Intent intentHobby = new Intent(InputActivity.this, HobbyActivity.class);
                intentHobby.putExtras(bdl);
                startActivityForResult(intentHobby, New_Hobby);
            }
        });

        btnContInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bdl = new Bundle();
                bdl.putString("phone", phone);
                bdl.putString("email", email);
                bdl.putString("home", home);
                Intent intentContInfo = new Intent(InputActivity.this, ContactInformationActivity.class);
                intentContInfo.putExtras(bdl);
                startActivityForResult(intentContInfo, New_ContInfo);
            }
        });

        btnWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bdl = new Bundle();
                bdl.putString("job", job);
                bdl.putString("position", position);
                bdl.putString("workplace",workplace);
                bdl.putString("salary", salary);
                Intent intentWork = new Intent(InputActivity.this, WorkActivity.class);
                intentWork.putExtras(bdl);
                startActivityForResult(intentWork, New_Work);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (check != 0) {
                    if (name.length()>0){
                        if (id.equals("")) {
                            if (savePerson() == 1) {
                                Bundle bdl = new Bundle();
                                Person person = new Person(name, dateOfBirth, gender, identity, image, music, sport, movie, pet, phone, email, home, job, position, workplace, salary);
                                bdl.putSerializable("person", person);
                                Intent intent = new Intent(InputActivity.this, MainActivity.class);
                                intent.putExtra("DATA", bdl);
                                setResult(MainActivity.RESULT_CODE, intent);
                                finish();
                            } else {
                                Toast.makeText(getApplication(), "Fail saved!!!", Toast.LENGTH_LONG).show();
                            }
                        } else{
                            if (updatePerson() == 1){
                                Bundle bdl = new Bundle();
                                Person person = new Person(name, dateOfBirth, gender, identity, image, music, sport, movie, pet, phone, email, home, job, position, workplace, salary);
                                bdl.putSerializable("person", person);
                                Intent intent = new Intent(InputActivity.this, MainActivity.class);
                                intent.putExtra("DATA", bdl);
                                setResult(MainActivity.RESULT_CODE, intent);
                                finish();
                            }else {
                                Toast.makeText(getApplication(), "Fail update!!!", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Name is empty!", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Information is empty!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case InputActivity.New_PerInfo:
                if (resultCode == InputActivity.Save) {
                    Bundle bdlPerInfo = data.getBundleExtra("DATA");
                    name = bdlPerInfo.getString("name");
                    dateOfBirth = bdlPerInfo.getString("dateOfBirth");
                    gender = bdlPerInfo.getString("gender");
                    identity = bdlPerInfo.getString("identity");
                    image =  bdlPerInfo.getByteArray("image");
                    check = 1;
                }
                break;

            case InputActivity.New_Hobby:
                if (resultCode == InputActivity.Save) {
                    Bundle bdlHobby = data.getBundleExtra("DATA");
                    music = bdlHobby.getString("music");
                    sport = bdlHobby.getString("sport");
                    movie = bdlHobby.getString("movie");
                    pet = bdlHobby.getString("pet");
                    check = 1;
                }
                break;

            case InputActivity.New_ContInfo:
                if (resultCode == InputActivity.Save) {
                    Bundle bdlContInfo = data.getBundleExtra("DATA");
                    phone = bdlContInfo.getString("phone");
                    email = bdlContInfo.getString("email");
                    home = bdlContInfo.getString("home");
                    check = 1;
                }
                break;

            case InputActivity.New_Work:
                if (resultCode == InputActivity.Save) {
                    Bundle bdlWork = data.getBundleExtra("DATA");
                    job = bdlWork.getString("job");
                    position = bdlWork.getString("position");
                    workplace = bdlWork.getString("workplace");
                    salary = bdlWork.getString("salary");
                    check = 1;
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        setResult(Activity.RESULT_CANCELED);
        super.onBackPressed();
    }

    private long savePerson(){
        db = openOrCreateDatabase(LoginActivity.DATABASE_NAME, MODE_PRIVATE, null);
        try {
            String sql = "insert into tblPerson(name, dateOfBirth, gender, identity_card, image, music, sport, movie, pet, phone, email, home_address, job, position, workplace_address, salary) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            SQLiteStatement p = db.compileStatement(sql);
            p.bindString(1, name);
            p.bindString(2, dateOfBirth);
            p.bindString(3, gender);
            p.bindString(4, identity);
            p.bindBlob(5, image);
            p.bindString(6, music);
            p.bindString(7, sport);
            p.bindString(8, movie);
            p.bindString(9, pet);
            p.bindString(10, phone);
            p.bindString(11, email);
            p.bindString(12, home);
            p.bindString(13, job);
            p.bindString(14, position);
            p.bindString(15, workplace);
            p.bindString(16, salary);
            p.execute();
            return 1;
        }
        catch (Exception ex){
            Toast.makeText(getApplication(), "Error: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
        return -1;
    }

    private long updatePerson(){
        db = openOrCreateDatabase(LoginActivity.DATABASE_NAME, MODE_PRIVATE, null);
        try {
            String sql = "update tblPerson set name= ?, dateOfBirth= ?, gender= ?, identity_card= ?, image= ?, music= ?, sport= ?, movie= ?, pet= ?, phone= ?, email= ?, home_address= ?, job= ?, position= ?, workplace_address= ?, salary= ? where id_person = ?";
            SQLiteStatement p = db.compileStatement(sql);
            p.bindString(1, name);
            p.bindString(2, dateOfBirth);
            p.bindString(3, gender);
            p.bindString(4, identity);
            p.bindBlob(5, image);
            p.bindString(6, music);
            p.bindString(7, sport);
            p.bindString(8, movie);
            p.bindString(9, pet);
            p.bindString(10, phone);
            p.bindString(11, email);
            p.bindString(12, home);
            p.bindString(13, job);
            p.bindString(14, position);
            p.bindString(15, workplace);
            p.bindString(16, salary);
            p.bindString(17, id);
            p.execute();
            return 1;
        }
        catch (Exception ex){
            Toast.makeText(getApplication(), "Error: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
        return -1;
    }
}