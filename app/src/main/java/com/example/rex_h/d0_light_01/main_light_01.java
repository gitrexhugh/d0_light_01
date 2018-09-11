package com.example.rex_h.d0_light_01;

import android.app.Activity;
import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;

public class main_light_01 extends Activity {
    private ImageButton ibtn_back_light, ibtn_screen_light, ibtn_power;
    private int light_state;// 1: On_back_light_constant, 2: On_back_light_flash, 3: On_screen_light, 4: Off_light

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        load_default_layout();
    }
    private void load_default_layout(){

        setContentView(R.layout.deafult_layout);

        ibtn_back_light=(ImageButton)findViewById(R.id.btn_back_light);
        ibtn_screen_light=(ImageButton)findViewById(R.id.btn_screen_light);
        ibtn_power=(ImageButton)findViewById(R.id.btn_power);

        ibtn_power.setOnClickListener(ibtn_power_Click);
        ibtn_back_light.setOnClickListener(ibtn_back_light_Click);
        ibtn_screen_light.setOnClickListener(ibtn_screen_light_Click);
        light_state=1;
        light_behavior(light_state);

    }
    private View.OnClickListener ibtn_power_Click= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (light_state==1){
                light_state=4;
            }else {
                light_state=1;
            }
            light_behavior(light_state);
        }
    };

    private View.OnClickListener ibtn_back_light_Click= new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    private View.OnClickListener ibtn_screen_light_Click= new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    private void light_behavior(int s){
        switch (s){
            case 1:
                ibtn_power.setImageDrawable(getResources().getDrawable(R.mipmap.light_xxxhdpi));
                break;
            case  4:
                ibtn_power.setImageDrawable(getResources().getDrawable(R.mipmap.light_xxxhdpi_0));
                break;
        }
    }
}
