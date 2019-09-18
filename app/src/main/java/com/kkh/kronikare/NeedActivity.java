package com.kkh.kronikare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class NeedActivity extends AppCompatActivity {
    Service service;
    String[] cityArray={"Pune","Mumbai","Delhi","Banglore"};
    String[] relArray={"Father","Mother","Self","Other"};
    Spinner spinner2;
    Spinner spinner;
    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_need);
        spinner = (Spinner) findViewById(R.id.spinnerRelation);
        spinner2 = (Spinner) findViewById(R.id.spinnerCity);
        button=(Button)findViewById(R.id.buttonNext);
        editText=(EditText)findViewById(R.id.editTextRelation);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        R.array.relation_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.city_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        service=Service.getService();


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==3)
                {
                    editText.setVisibility(View.VISIBLE);
                    editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                        @Override
                        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                            service.setRelation(editText.getText().toString());
                            return true;
                        }
                    });
                }
                else {
                    editText.setVisibility(View.INVISIBLE);
                    service.setRelation(relArray[i]);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                editText.setVisibility(View.INVISIBLE);
                service.setRelation(null);
            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                service.setCity(cityArray[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                service.setCity(null);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(service.getRelation()==null||service.getRelation().equals(""))
                {
                    Toast.makeText(NeedActivity.this,"Please select whom do you need care for",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(service.getCity()==null)
                {
                    Toast.makeText(NeedActivity.this,"Please select your city.",Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(NeedActivity.this,"Next Activity",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
