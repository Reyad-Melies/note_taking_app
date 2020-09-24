package com.example.notetakingappf;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
public class notesData extends AppCompatActivity {
    TextView textView;
    String[] arrOfStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_data);
        textView=findViewById(R.id.editTextTextMultiLine);
        Intent intent = getIntent();
         if (intent.getStringExtra("notes") != null) {
             arrOfStr = intent.getStringExtra("notes").split("@", 2);
             if(arrOfStr.length==2){
            textView.setText(arrOfStr[0]);
        }
    }
    }
    public void save(View view){

        ArrayList<String> notes=new ArrayList<String>();
        notes.add(textView.getText().toString());
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        if (arrOfStr.length==2) {
            intent.putExtra("notes",textView.getText()+"@"+arrOfStr[1]);
            Log.i("llllllllllll",textView.getText()+"@"+arrOfStr[1]);
        } else{
            intent.putExtra("notes",notes.get(0));
        Log.i("llllllllllll",notes.get(0));
        }
        startActivity(intent);
    }
}