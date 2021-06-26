package com.example.collect_personal_information;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    public static final String DATABASE_NAME = "QLThongTin.db";
    SQLiteDatabase db;
    EditText user, pass;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = (EditText) findViewById(R.id.txtUser);
        pass = (EditText) findViewById(R.id.txtPass);
        login = (Button) findViewById(R.id.btnLogin);
        initDB();

        user.requestFocus();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = user.getText().toString();
                String password = pass.getText().toString();

                if (username.isEmpty()){
                    Toast.makeText(getApplication(), "Please enter username", Toast.LENGTH_LONG).show();
                    user.requestFocus();
                }
                else if (password.isEmpty()){
                    Toast.makeText(getApplication(), "Please enter password", Toast.LENGTH_LONG).show();
                    pass.requestFocus();
                }
                else if (isUser(user.getText().toString(), pass.getText().toString())){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplication(), "Username or password does not exist!!!", Toast.LENGTH_LONG).show();
                    user.requestFocus();
                }
            }
        });
    }

    private void initDB(){
        db = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        String sql;
        try{
            if(!isTableExists(db, "tblUser")){
                sql = "CREATE TABLE tblUser(id_user INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,";
                sql += "username TEXT NOT NULL,";
                sql += "password TEXT NOT NULL)";
                db.execSQL(sql);
                sql = "insert into tblUser(username,password) values ('admin', '123')";
                db.execSQL(sql);
            }
            if(!isTableExists(db, "tblPerson")){
                sql = "CREATE TABLE tblPerson (id_person INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,";
                sql += "name TEXT NOT NULL,";
                sql += "dateOfBirth TEXT,";
                sql += "gender TEXT,";
                sql += "identity_card TEXT,";
                sql += "image TEXT,";
                sql += "music TEXT,";
                sql += "sport TEXT,";
                sql += "movie TEXT,";
                sql += "pet TEXT,";
                sql += "phone TEXT,";
                sql += "email TEXT,";
                sql += "home_address TEXT,";
                sql += "job TEXT,";
                sql += "position TEXT,";
                sql += "workplace_address TEXT,";
                sql += "salary TEXT);";
                db.execSQL(sql);
            }
        }
        catch (Exception ex){
            Toast.makeText(LoginActivity.this, "Khởi tạo cơ sở dữ liệu không thành công", Toast.LENGTH_LONG).show();
        }
    }

    private boolean isTableExists(SQLiteDatabase database, String tableName) {
        Cursor cursor = database.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name " + "= '" + tableName +"'",null);
        if(cursor != null){
            if (cursor.getCount() > 0) {
                cursor.close();
                return true;
            }
            cursor.close();
        }
        return false;
    }

    private boolean isUser(String username, String password){
        try {
            db = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
            Cursor c = db.rawQuery("select * from tblUser where username = ? and password = ?",
                    new String[] {username, password});
            c.moveToFirst();
            if (c.getCount() > 0)
                return true;
        }
        catch (Exception ex){
            Toast.makeText(this, "Lỗi hệ thống", Toast.LENGTH_LONG).show();
        }
        return false;
    }
}