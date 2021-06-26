package com.example.collect_personal_information;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class OutputActivity extends AppCompatActivity {
    TextView txtName, txtDateOfBirth, txtGender, txtIdentity, txtMusic, txtSport, txtMovie, txtPet, txtPhone, txtEmail, txtHome,
            txtJob, txtPosition, txtWorkplace, txtSalary;
    ImageView image;
    Button btnOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);

        image = (ImageView) findViewById(R.id.imageView);
        txtName = (TextView) findViewById(R.id.txtName);
        txtDateOfBirth = (TextView) findViewById(R.id.txtDateOfBirth);
        txtGender = (TextView) findViewById(R.id.txtGender);
        txtIdentity = (TextView) findViewById(R.id.txtIdentityCard);
        txtMusic = (TextView) findViewById(R.id.txtMusic);
        txtSport = (TextView) findViewById(R.id.txtSport);
        txtMovie = (TextView) findViewById(R.id.txtMovie);
        txtPet = (TextView) findViewById(R.id.txtPet);
        txtPhone = (TextView) findViewById(R.id.txtPhone);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtHome = (TextView) findViewById(R.id.txtHome);
        txtJob = (TextView) findViewById(R.id.txtJob);
        txtPosition = (TextView) findViewById(R.id.txtPosition);
        txtWorkplace = (TextView) findViewById(R.id.txtWorkplace);
        txtSalary = (TextView) findViewById(R.id.txtSalary);

        btnOK = (Button) findViewById(R.id.btnOK);

        Intent intent = getIntent();
        Bundle bdl = intent.getExtras();
        Person ps = (Person) bdl.getSerializable("ps");
        txtName.setText(ps.getName());
        txtDateOfBirth.setText(ps.getDateOfBirth());
        txtGender.setText(ps.getGender());
        txtIdentity.setText(ps.getIdentityCard());
        txtMusic.setText(ps.getMusic());
        txtSport.setText(ps.getSport());
        txtMovie.setText(ps.getMovie());
        txtPet.setText(ps.getPet());
        txtPhone.setText(ps.getPhoneNumber());
        txtEmail.setText(ps.getEmail());
        txtHome.setText(ps.getHomeAddress());
        txtJob.setText(ps.getJob());
        txtPosition.setText(ps.getPosition());
        txtWorkplace.setText(ps.getWorkplaceAddress());
        txtSalary.setText(ps.getSalary());
        byte[] dt = ps.getImage();
        image.setImageBitmap(BitmapFactory.decodeByteArray(dt, 0, dt.length));

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}