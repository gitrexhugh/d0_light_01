package com.example.rex_h.d0_light_01;

import android.app.Activity;
import android.content.Context;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraAccessException;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Color;


public class main_light_01 extends Activity {

    private int light_state;// 1: On_back_light_constant, 2: On_back_light_flash, 3: On_screen_light, 4: Off_light
    //以image button宣告圖形按鈕

    private ImageButton ibtn_screen_light;
    private ImageButton ibtn_back_light;
    private ImageButton ibtn_power;
    //以image view宣告圖形按鈕
    /*
    private ImageView ibtn_screen_light;
    private ImageView ibtn_back_light;
    private ImageView ibtn_power;*/

    private TextView show_status;
    private String str_status;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        load_default_layout();
        /*
        ibtn_back_light=(ImageButton) findViewById(R.id.btn_back_light);
        ibtn_screen_light=(ImageButton)findViewById(R.id.btn_screen_light);
        ibtn_power=(ImageButton)findViewById(R.id.btn_power);
        //以下宣告按鈕，Listener內容另外寫
        ibtn_back_light.setOnClickListener(ibtn_back_light_click);// Back light
        ibtn_screen_light.setOnClickListener(ibtn_screen_light_Click);// Screen Light
        ibtn_power.setOnClickListener(ibtn_power_click);// Power*/


        //以下將Listener Method寫在宣告內容中
        /*
        ibtn_back_light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (light_state == 3) {
                    load_screen_light_layout();
                } else {
                    light_state = 1;
                    lightOn();
                }
            }
        });
        ibtn_screen_light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                light_state=3;
                load_screen_light_layout();
            }
        });

        ibtn_power.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (light_state==1){
                    light_state=4;
                    lightOff();
                }else {
                    light_state=1;
                    lightOn();
                }
            }
        });*/

    }
    private void load_default_layout(){
        setContentView(R.layout.deafult_layout);

        ibtn_back_light=(ImageButton) findViewById(R.id.btn_back_light);
        ibtn_screen_light=(ImageButton)findViewById(R.id.btn_screen_light);
        ibtn_power=(ImageButton)findViewById(R.id.btn_power);
        //以下宣告按鈕，Listener內容另外寫
        ibtn_back_light.setOnClickListener(ibtn_back_light_click);// Back light
        ibtn_screen_light.setOnClickListener(ibtn_screen_light_Click);// Screen Light
        ibtn_power.setOnClickListener(ibtn_power_click);// Power

        //lightOn();
        //light_state=1;
        //light_behavior(light_state);
    }


    private void lightOn(){
        show_status=(TextView)findViewById(R.id.show_status);
        str_status="light_On";
        show_status.setText(str_status);
        ibtn_power.setImageResource(R.mipmap.light_xxxhdpi);

        //以下為Camera Manager相關，不適用模擬器
        /*
        CameraManager mCamera = (CameraManager)getSystemService(Context.CAMERA_SERVICE);
        try{
            String cameraID=mCamera.getCameraIdList()[0];
             mCamera.setTorchMode(cameraID,true);
        }catch (CameraAccessException e){
            e.printStackTrace();
        }*/
    }
    private void lightOff(){
        str_status="light_Off";
        show_status.setText(str_status);
        ibtn_power.setImageResource(R.mipmap.light_xxxhdpi_0);
        //以下為Camera Manager相關，不適用模擬器
        /*
        CameraManager mCamera = (CameraManager)getSystemService(Context.CAMERA_SERVICE);
        try{
            String cameraID=mCamera.getCameraIdList()[0];
            mCamera.setTorchMode(cameraID,false);
        }catch (CameraAccessException e){
            e.printStackTrace();
        }*/
    }

    private void load_screen_light_layout(){
        setContentView(R.layout.screen_light_layout);

        /*ibtn_back_light=(ImageButton)findViewById(R.id.btn_back_light);
        ibtn_screen_light=(ImageButton)findViewById(R.id.btn_screen_light);
        ibtn_power=(ImageButton)findViewById(R.id.btn_power);*/
        //以下宣告按鈕，Listener內容另外寫
        ibtn_back_light.setOnClickListener(ibtn_back_light_click);// Back light
        ibtn_screen_light.setOnClickListener(ibtn_screen_light_Click);// Screen Light

        light_state=3;
        //light_behavior(light_state);
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

//以下為Listener Method
   private View.OnClickListener ibtn_screen_light_Click= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            light_state=3;
            load_screen_light_layout();
        }
    };

    private View.OnClickListener ibtn_back_light_click=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            light_state = 1;
            load_default_layout();

        }
    };

    private View.OnClickListener ibtn_power_click=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (light_state==1){
                light_state=4;
                lightOff();
            }else {
                light_state=1;
                lightOn();
            }
        }
    };

    /*private void light_behavior(int s){
        switch (s){
            case 1:
                ibtn_power.setImageDrawable(getResources().getDrawable(R.mipmap.light_xxxhdpi));
                lightOn();
                break;
            case 2:
                ibtn_power.setImageDrawable(getResources().getDrawable(R.mipmap.light_xxxhdpi));
                break;
            case 3:
                load_screen_light_layout();
            case  4:
                ibtn_power.setImageDrawable(getResources().getDrawable(R.mipmap.light_xxxhdpi_0));
                lightOff();
                break;
        }
    }*/
}
