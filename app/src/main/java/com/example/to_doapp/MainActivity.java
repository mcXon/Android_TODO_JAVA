package com.example.to_doapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button todo_btn;
    private EditText todo_et;
    private ListView todo_lv;

    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todo_btn = findViewById(R.id.btn_todo_save);
        todo_et = findViewById(R.id.et_todo_item);
        todo_lv = findViewById(R.id.lv_todo_items);

        todo_btn.setOnClickListener(this);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        todo_lv.setAdapter(adapter);

        //long press for the list view items
        todo_lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                Object toBeRemoved = adapter.getItem(position);
                adapter.remove(toBeRemoved);


                Toast.makeText(MainActivity.this, "To do item \" " + toBeRemoved.toString() + " \" deleted", Toast.LENGTH_SHORT).show();
                return true; //return true if the even is consumed
            }
        });
    }


    @Override
    public void onClick(View view) {
        String todo_text = todo_et.getText().toString();
        if(todo_text.isEmpty()){
            Toast.makeText(this, "Empty to do item", Toast.LENGTH_LONG);
        }else{
            insertTodoItem(todo_text);
            // clear the input field
            todo_et.getText().clear();
        }
    }

    private void insertTodoItem(String todo_text) {
        adapter.add(todo_text);
    }
}