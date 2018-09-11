package com.example.rex_h.d0_light_01;

import android.app.Activity;
import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.SeekBar;
import android.widget.ImageButton;
import android.widget.TextView;
import android.graphics.Color;

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
    private void load_screen_light_layout(){
        setContentView(R.layout.screen_light_layout);
        /*ibtn_back_light=(ImageButton)findViewById(R.id.btn_back_light);
        ibtn_screen_light=(ImageButton)findViewById(R.id.btn_screen_light);
        ibtn_power=(ImageButton)findViewById(R.id.btn_power);
        ibtn_power.setOnClickListener(ibtn_power_Click);
        ibtn_back_light.setOnClickListener(ibtn_back_light_Click);
        ibtn_screen_light.setOnClickListener(ibtn_screen_light_Click);*/


        SeekBar sk_R, sk_G, sk_B;
        TextView show_text;
        sk_R=(SeekBar)findViewById(R.id.seekR);
        sk_G=(SeekBar)findViewById(R.id.seekG);
        sk_B=(SeekBar)findViewById(R.id.seekB);
        sk_R.setProgress(255);
        sk_G.setProgress(255);
        sk_B.setProgress(255);
        show_text=(TextView)findViewById(R.id.show_color);


        //show_text.setBackgroundColor(Color.argb(255,cR,cG,cB));
        //show_text.setText(String.format("%02x", cR)+String.format("%02x", cG)+String.format("%02x", cB));

        sk_R.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int cR=sk_R.getProgress();
            int cG=sk_G.getProgress();
            int cB=sk_B.getProgress();

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int id=seekBar.getId();
                show_text.setBackgroundColor(Color.argb(255,cR,cG,cB));
                show_text.setText(String.format("%02x", cR)+String.format("%02x", cG)+String.format("%02x", cB));
                switch (id){
                    case com.example.rex_h.d0_light_01.R.id.seekR:
                        cR=progress;
                        break;
                    case com.example.rex_h.d0_light_01.R.id.seekG:
                        cG=progress;
                        break;
                    case com.example.rex_h.d0_light_01.R.id.seekB:
                        cB=progress;
                        break;

                }


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {


            }
        });

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
            light_state=3;
            light_behavior(light_state);
        }
    };

    private void light_behavior(int s){
        switch (s){
            case 1:
                ibtn_power.setImageDrawable(getResources().getDrawable(R.mipmap.light_xxxhdpi));
                break;
            case 3:
                load_screen_light_layout();
            case  4:
                ibtn_power.setImageDrawable(getResources().getDrawable(R.mipmap.light_xxxhdpi_0));
                break;
        }
    }
}
