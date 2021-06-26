package com.example.collect_personal_information;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_EDIT = 123;
    private static final int REQUEST_CODE_ADD = 124;
    public static final int RESULT_CODE = 125;

    SQLiteDatabase db;
    ListView person;
    ImageButton exit;
    Button add;
    int posselected = -1;
    ArrayList<Person> arrPerson = new ArrayList<Person>();
    ArrayList<String> arrName = new ArrayList<String>();
    ArrayAdapter<String> adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        person = (ListView) findViewById(R.id.lvPerson);
        add = (Button) findViewById(R.id.btnAdd);
        exit = (ImageButton) findViewById(R.id.btnExit);

        getPersonList();

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        person.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int pos, long id) {
                posselected = pos;
                return false;
            }
        });
        registerForContextMenu(person);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentInput = new Intent(MainActivity.this, InputActivity.class);
                startActivityForResult(intentInput, REQUEST_CODE_ADD);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuOutput:
                doPrint();
                break;
            case R.id.menuEdit:
                doStartEdit();
                break;
            case R.id.menuDelete:
                doDelete();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void doDelete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Do you want to delete this person?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                db = openOrCreateDatabase(LoginActivity.DATABASE_NAME, MODE_PRIVATE, null);
                String id_person = arrPerson.get(posselected).getId_person();
                if (db.delete("tblPerson", "id_person=?", new String[]{id_person}) != -1) {
                    arrPerson.remove(posselected);
                    arrName.remove(posselected);
                    adapter.notifyDataSetChanged();
                }
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    private void doStartEdit() {
        Bundle bdl = new Bundle();
        Person ps = arrPerson.get(posselected);
        bdl.putSerializable("ps", ps);
        Intent intentInput = new Intent(MainActivity.this, InputActivity.class);
        intentInput.putExtras(bdl);
        startActivityForResult(intentInput, REQUEST_CODE_EDIT);
    }

    private void doPrint() {
        Bundle bdl = new Bundle();
        Person ps = arrPerson.get(posselected);
        bdl.putSerializable("ps", ps);
        Intent intentOutput = new Intent(MainActivity.this, OutputActivity.class);
        intentOutput.putExtras(bdl);
        startActivity(intentOutput);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case MainActivity.REQUEST_CODE_ADD:
                if (resultCode == MainActivity.RESULT_CODE) {
                    adapter.clear();
                    getPersonList();
                    Toast.makeText(getApplication(), "Add completed", Toast.LENGTH_SHORT).show();
                }
                break;
            case MainActivity.REQUEST_CODE_EDIT:
                if (resultCode == MainActivity.RESULT_CODE) {
                    adapter.clear();
                    getPersonList();
                    Toast.makeText(getApplication(), "Update completed", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void getPersonList(){
        db = openOrCreateDatabase(LoginActivity.DATABASE_NAME, MODE_PRIVATE, null);
        Cursor c = db.rawQuery("select * from tblPerson", null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            arrPerson.add(new Person(c.getString(0), c.getString(1),
                    c.getString(2), c.getString(3), c.getString(4),
                    c.getBlob(5), c.getString(6), c.getString(7),
                    c.getString(8), c.getString(9), c.getString(10),
                    c.getString(11), c.getString(12), c.getString(13),
                    c.getString(14), c.getString(15), c.getString(16)));
            arrName.add(c.getString(1));
            c.moveToNext();
        }
        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, arrName);
        person.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}