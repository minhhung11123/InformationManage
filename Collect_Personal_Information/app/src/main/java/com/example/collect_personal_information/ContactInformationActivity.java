package com.example.collect_personal_information;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ContactInformationActivity extends AppCompatActivity {
    EditText txtPhone, txtEmail, txtHome;
    Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_information);

        txtPhone = (EditText) findViewById(R.id.txtPhone);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtHome = (EditText) findViewById(R.id.txtHome);
        btnConfirm = (Button) findViewById(R.id.btnConfirm);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = txtPhone.getText().toString();
                String email = txtEmail.getText().toString();
                String home = txtHome.getText().toString();

                Bundle bundle = new Bundle();
                bundle.putString("phone", phone);
                bundle.putString("email", email);
                bundle.putString("home", home);

                Intent intent = new Intent(ContactInformationActivity.this, InputActivity.class);
                intent.putExtra("DATA", bundle);
                setResult(InputActivity.Save, intent);
                finish();
            }
        });
        try {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            txtPhone.setText(bundle.getString("phone"));
            txtEmail.setText(bundle.getString("email"));
            txtHome.setText(bundle.getString("home"));
        }
        catch (Exception e){

        }
    }

    @Override
    public void onBackPressed() {
        setResult(Activity.RESULT_CANCELED);
        super.onBackPressed();
    }
}