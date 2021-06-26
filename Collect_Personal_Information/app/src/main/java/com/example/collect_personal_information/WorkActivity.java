package com.example.collect_personal_information;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class WorkActivity extends AppCompatActivity {
    EditText txtJob, txtPosition, txtWorkplace, txtSalary;
    Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);

        txtJob = (EditText) findViewById(R.id.txtJob);
        txtPosition = (EditText) findViewById(R.id.txtPosition);
        txtWorkplace = (EditText) findViewById(R.id.txtWorkplace);
        txtSalary = (EditText) findViewById(R.id.txtSalary);
        btnConfirm = (Button) findViewById(R.id.btnConfirm);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String job = txtJob.getText().toString();
                String position = txtPosition.getText().toString();
                String workplace = txtWorkplace.getText().toString();
                String salary = txtSalary.getText().toString();

                Bundle bundle = new Bundle();
                bundle.putString("job", job);
                bundle.putString("position", position);
                bundle.putString("workplace", workplace);
                bundle.putString("salary", salary);

                Intent intent = new Intent(WorkActivity.this, InputActivity.class);
                intent.putExtra("DATA", bundle);
                setResult(InputActivity.Save, intent);
                finish();
            }
        });

        try {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            txtJob.setText(bundle.getString("job"));
            txtPosition.setText(bundle.getString("position"));
            txtWorkplace.setText(bundle.getString("workplace"));
            txtSalary.setText(bundle.getString("salary"));
        }
        catch (Exception e) {
        }
    }

    @Override
    public void onBackPressed() {
        setResult(Activity.RESULT_CANCELED);
        super.onBackPressed();
    }
}