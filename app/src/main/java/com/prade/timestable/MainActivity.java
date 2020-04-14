package com.prade.timestable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView timesTableListView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SeekBar timesTableSeekBar = (SeekBar) findViewById(R.id.timesTableSeekBar);
        timesTableListView = (ListView) findViewById(R.id.timesTableListView);

        timesTableSeekBar.setMax(20);
        timesTableSeekBar.setProgress(10);

        timesTableSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int min = 1;
                int timestable;

                if (progress < min) {
                    timestable = min;
                    timesTableSeekBar.setProgress(min);
                } else {
                    timestable = progress;
                }

                generateTimesTable(timestable);
                Log.i("Seekbar values", Integer.toString(timestable));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        generateTimesTable(10);
    }

    public void generateTimesTable(final int timesTable)
    {

        ArrayList<String> timesTableContent = new ArrayList<String>();
        for(int i=1;i <= 16 ; i++)
        {
            timesTableContent.add(Integer.toString(i)+"*"+Integer.toString(timesTable)+" = "+Integer.toString(i*timesTable));
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,timesTableContent);
        timesTableListView.setAdapter(arrayAdapter);

        timesTableListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
int value = (position+1)*timesTable;
                String text = Integer.toString(position+1)+ " multiplies with " + Integer.toString(timesTable) + " is equal to "+ Integer.toString(value);
                Toast.makeText( MainActivity.this, text,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
