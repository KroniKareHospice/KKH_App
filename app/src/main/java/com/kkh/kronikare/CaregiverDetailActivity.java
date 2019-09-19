package com.kkh.kronikare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class CaregiverDetailActivity extends AppCompatActivity {
    Service service;
    RadioGroup radioGroupGender;
    RadioGroup radioGroupLive;
    EditText langInput;
    ImageButton langAdd;
    TextView langDisplay;
    TextView langReset;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service=Service.getService();
        setContentView(R.layout.activity_caregiver_detail);
        radioGroupGender=(RadioGroup)findViewById(R.id.radioGroupGender);
        radioGroupLive=(RadioGroup)findViewById(R.id.radioGroupLive);
        langInput=(EditText)findViewById(R.id.langInput);
        langAdd=(ImageButton)findViewById(R.id.langAdd);
        langDisplay=(TextView)findViewById(R.id.langDisplay);
        langReset=(TextView)findViewById(R.id.textViewReset);
        next=(Button)findViewById(R.id.buttonNext);

        langDisplay.setText("Selected Languages :"+service.getLanguages().toString());

        radioGroupGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.radioButtonMale)
                    service.setCaretakerGender("Male");
                else if(i==R.id.radioButtonFemale)
                    service.setCaretakerGender("Female");
                else if(i==R.id.radioButtonAny)
                    service.setCaretakerGender("Any");
                Toast.makeText(CaregiverDetailActivity.this,service.getCaretakerGender(),Toast.LENGTH_SHORT).show();
            }
        });
        radioGroupLive.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.radioButtonLiveIn)
                    service.setType("LiveIn");
                if(i==R.id.radioButtonLiveOut)
                    service.setType("LiveOut");
                Toast.makeText(CaregiverDetailActivity.this,service.getType(),Toast.LENGTH_SHORT).show();
            }
        });
        langAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lang=langInput.getText().toString();
                if(!lang.equals("")&&!service.getLanguages().contains(lang))
                {
                    service.getLanguages().add(lang);
                    langDisplay.setText("Selected Languages :"+service.getLanguages().toString());
                }
                langInput.setText("");
            }
        });

        langReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                service.getLanguages().clear();
                langDisplay.setText("Selected Languages :"+service.getLanguages().toString());
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(service.getCaretakerGender()==null||service.getType()==null)
                {
                    Toast.makeText(CaregiverDetailActivity.this,"Please select a choice for above questions.",Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(CaregiverDetailActivity.this,Register.class);
                startActivity(intent);
            }
        });
    }
}
