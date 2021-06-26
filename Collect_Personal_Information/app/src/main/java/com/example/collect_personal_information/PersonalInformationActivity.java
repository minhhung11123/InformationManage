package com.example.collect_personal_information;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class PersonalInformationActivity extends AppCompatActivity {
    Calendar calendar;
    EditText txtName, txtDateOfBirth, txtIdentity;
    RadioGroup rdgGender;
    ImageView image;
    RadioButton rdbMale, rdbFemale;
    Button btnDate, btnImage, btnConfirm;
    int day, month, year, idChecked;
    Uri currImageURI;
    byte[] dt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);

        txtName = (EditText) findViewById(R.id.txtName);
        txtDateOfBirth = (EditText) findViewById(R.id.txtDateOfBirth);
        rdgGender = (RadioGroup) findViewById(R.id.rdgGender);
        rdbMale = (RadioButton) findViewById(R.id.rdbMale);
        rdbFemale = (RadioButton) findViewById(R.id.rdbFemale);
        txtIdentity = (EditText) findViewById(R.id.txtIdentityCard);
        image = (ImageView) findViewById(R.id.Image);
        btnDate = (Button) findViewById(R.id.btnChoose);
        btnImage = (Button) findViewById(R.id.btnChoose1);
        btnConfirm = (Button) findViewById(R.id.btnConfirm);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month + 1, day);

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(1);
            }
        });

        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),1);
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currImageURI == null) {
                    Toast.makeText(getApplication(), "Choose Image !", Toast.LENGTH_SHORT).show();
                }else{
                    String name = txtName.getText().toString();
                    String dateOfBirth = txtDateOfBirth.getText().toString();

                    String gender;
                    idChecked = rdgGender.getCheckedRadioButtonId();
                    if (idChecked == R.id.rdbMale)
                        gender = "Male";
                    else if (idChecked == R.id.rdbFemale)
                        gender = "Female";
                    else
                        gender = "";

                    String identity = txtIdentity.getText().toString();
                    Bundle bundle = new Bundle();
                    bundle.putString("name", name);
                    bundle.putString("dateOfBirth", dateOfBirth);
                    bundle.putString("gender", gender);
                    bundle.putString("identity", identity);
                    bundle.putByteArray("image", dt);

                    Intent intent = new Intent(PersonalInformationActivity.this, InputActivity.class);
                    intent.putExtra("DATA", bundle);
                    setResult(InputActivity.Save, intent);
                    finish();
                }
            }
        });

        try {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            txtName.setText(bundle.getString("name"));
            txtDateOfBirth.setText(bundle.getString("dateOfBirth"));
            if (bundle.getString("gender").equals("Male"))
                rdbMale.setChecked(true);
            else if (bundle.getString("gender").equals("Female")) {
                rdbFemale.setChecked(true);
            }
            txtIdentity.setText(bundle.getString("identity"));
            dt = bundle.getByteArray("image");
            image.setImageBitmap(BitmapFactory.decodeByteArray(dt, 0, dt.length));
        }
        catch (Exception e){ }
    }

    @Override
    protected Dialog onCreateDialog(int id){
        if (id == 1)
            return new DatePickerDialog(this, dateSetListener, year, month, day);
        return null;
    }

    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            showDate(year, month + 1, dayOfMonth);
        }
    };

    private void showDate (int year, int month, int day){
        txtDateOfBirth.setText(new StringBuilder().append(day > 9 ? day : "0" + day).append("/").append(month > 9 ? month : "0" + month).append("/").append(year));
    }

    @Override
    public void onBackPressed() {
        setResult(Activity.RESULT_CANCELED);
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                currImageURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), currImageURI);
                    dt = getBitmapAsByteArray(bitmap);
                    image.setImageBitmap(BitmapFactory.decodeByteArray(dt, 0, dt.length));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }
}