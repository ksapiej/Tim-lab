package com.example.webapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // UI references.
    private TextView average_value;
    private EditText number_of_values;
    private ListView listView;

    private ArrayList<Integer> valuesArray;
    private ArrayAdapter adapter;

    Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        number_of_values = (EditText) findViewById(R.id.number_of_values);
        average_value = (TextView) findViewById(R.id.average_value);
        valuesArray = new ArrayList<Integer>();
        listView = (ListView) findViewById(R.id.Listform);
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, valuesArray);
        listView.setAdapter(adapter);
        controller = new Controller(this);
    }

    public void generate(View view) {
        String s = number_of_values.getText().toString();
        if(s.matches("")) {
        }else{
            int value = Integer.parseInt(s);
            adapter.clear();
            controller.start(value);
        }
    }

    public void updateList(){
        valuesArray.addAll(controller.getIntList());
        adapter.notifyDataSetChanged();
    }

    public void average(View view) {
        double av =0;
        for(int i=0; i < valuesArray.size(); i++){
            av += valuesArray.get(i);
        }
        average_value.setText(String.valueOf(av / valuesArray.size()));
    }
}

