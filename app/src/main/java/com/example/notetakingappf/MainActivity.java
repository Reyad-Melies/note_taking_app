package com.example.notetakingappf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> notes;
    String[] arrOfStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences=this.getSharedPreferences("com.example.notetakingappf", Context.MODE_PRIVATE);
        notes = new ArrayList<>();
        ListView listView = (ListView) findViewById(R.id.listview);
        try {
            notes= (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("notes",ObjectSerializer.serialize(new ArrayList<String>())));
         //   Log.i("wwwwwwwwww", String.valueOf(notes.size()));
        } catch (IOException e) {
     //       Log.i("fffffffffffffff","fffffffffffffff");
            e.printStackTrace();
        }
        if (notes.size()<1){notes.add("Add new note");}
        Intent intent = getIntent();
        if (intent.getStringExtra("notes") != null) {
            arrOfStr = intent.getStringExtra("notes").split("@", 2);
            if (arrOfStr.length!=2) {
                notes.add(intent.getStringExtra("notes"));
                Log.i("fffffffffffffff",intent.getStringExtra("notes"));
            }else {
                Log.i("fffffffffffffff",arrOfStr[0]+arrOfStr[1]);
                notes.set(Integer.parseInt(arrOfStr[1]), arrOfStr[0]);
            }try {
                sharedPreferences.edit().putString("notes",ObjectSerializer.serialize(notes)).apply();
            } catch (IOException e) {
                e.printStackTrace();
            }
   }
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,notes);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),notesData.class);
                if (position==0){
                    intent.putExtra("notes",notes.get(position));
                }
                else {
                    intent.putExtra("notes",notes.get(position)+"@"+position);
                }
                startActivity(intent);
            }
        });
    }
}