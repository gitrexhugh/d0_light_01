package com.example.rex_h.d0_light_01;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraAccessException;
import android.os.Bundle;
import android.support.v4.graphics.drawable.IconCompat;
import android.text.Layout;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toolbar;

public class main_light_01 extends AppCompatActivity {

    private int light_state;// 1: On_back_light_constant, 2: On_back_light_flash, 3: On_screen_light, 4: Off_light
    //以image view宣告圖形按鈕
    private ImageView ibtn_screen_light;
    private ImageView ibtn_back_light;
    private ImageView ibtn_power;
    private TextView show_status;
    private String str_status;
    SeekBar sk_R;
    SeekBar sk_G;
    SeekBar sk_B;
    TextView show_text;
    int cR=255;
    int cG=255;
    int cB=255;
    int sk_id;
   //private ImageView img_glow;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        load_default_layout();

    }
    /*menu選單功能 暫不執行
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }*/

    private void load_default_layout(){
        setContentView(R.layout.deafult_layout);

        ibtn_back_light=(ImageView) findViewById(R.id.btn_back_light);
        ibtn_screen_light=(ImageView)findViewById(R.id.btn_screen_light);
        ibtn_power=(ImageView)findViewById(R.id.btn_power);
        //以下宣告按鈕，Listener內容另外寫
        ibtn_back_light.setOnClickListener(ibtn_back_light_click);// Back light
        ibtn_back_light.setImageResource(R.mipmap.backlight1_xxxhdpi);
        ibtn_screen_light.setOnClickListener(ibtn_screen_light_Click);// Screen Light
        ibtn_screen_light.setImageResource(R.mipmap.screenlight0_xxxhdpi);
        ibtn_power.setOnClickListener(ibtn_power_click);// Power

    }


    private void lightOn(){
        show_status=(TextView)findViewById(R.id.show_status);
        str_status="light_On";
        show_status.setText(str_status+"|status:"+light_state);
        ibtn_power.setImageResource(R.mipmap.light_xxxhdpi);
        //呼叫動畫控制程式- 暫不執行
       // anim_control(light_state);

        //以下為Camera Manager相關，不適用模擬器
        CameraManager mCamera = (CameraManager)getSystemService(Context.CAMERA_SERVICE);
        try{
            String cameraID=mCamera.getCameraIdList()[0];
             mCamera.setTorchMode(cameraID,true);
        }catch (CameraAccessException e){
            e.printStackTrace();
        }
    }
    private void lightOff(){
        str_status="light_Off";

        ibtn_power.setImageResource(R.mipmap.light_xxxhdpi_0);
       // anim_control(light_state); //呼叫動畫控制程式- 暫不執行

        //以下為Camera Manager相關，不適用模擬器
        CameraManager mCamera = (CameraManager)getSystemService(Context.CAMERA_SERVICE);
        try{
            String cameraID=mCamera.getCameraIdList()[0];
            mCamera.setTorchMode(cameraID,false);
        }catch (CameraAccessException e){
            e.printStackTrace();
        }
    }


    /*private void anim_control(int light_state){
        //呼叫xml動畫檔
        Animation scale= AnimationUtils.loadAnimation(main_light_01.this,R.anim.glow_ani);
        img_glow.startAnimation(scale);

        //由Java 控制動畫
        ObjectAnimator glow_alpha=ObjectAnimator.ofFloat(img_glow,"alpha", 1,0);
        glow_alpha.setDuration(1800);
        glow_alpha.setRepeatCount(Animation.INFINITE);
        glow_alpha.setInterpolator(new DecelerateInterpolator());
        ObjectAnimator glow_sacleX=ObjectAnimator.ofFloat(img_glow,"scaleX", 0,2);
        glow_sacleX.setDuration(1800);
        glow_sacleX.setRepeatCount(Animation.INFINITE);
        glow_sacleX.setInterpolator(new DecelerateInterpolator());
        ObjectAnimator glow_sacleY=ObjectAnimator.ofFloat(img_glow,"scaleY", 0,2);
        glow_sacleY.setDuration(1800);
        glow_sacleY.setRepeatCount(Animation.INFINITE);
        glow_sacleY.setInterpolator(new DecelerateInterpolator());

        if (light_state==1){

           //img_glow.startAnimation(scale);

            //glow_alpha.start();
            //glow_sacleX.start();
            //glow_sacleY.start();
        } else {
          //  img_glow.clearAnimation();
            //glow_alpha.end();

        }
        show_status.setText(str_status+"|status:"+light_state);

    }*/

    private void load_screen_light_layout(){
        setContentView(R.layout.screen_light_layout);
        ibtn_back_light=(ImageView) findViewById(R.id.btn_back_light);
        ibtn_screen_light=(ImageView) findViewById(R.id.btn_screen_light);
        //以下宣告按鈕，Listener內容另外寫
        ibtn_back_light.setOnClickListener(ibtn_back_light_click);// Back light
        ibtn_back_light.setImageResource(R.mipmap.backlight0_xxxhdpi);
        ibtn_screen_light.setOnClickListener(ibtn_screen_light_Click);// Screen Light
        ibtn_screen_light.setImageResource(R.mipmap.screenlight1_xxxhdpi);
        light_state=3;
        sk_R=(SeekBar)findViewById(R.id.seekR);
        sk_G=(SeekBar)findViewById(R.id.seekG);
        sk_B=(SeekBar)findViewById(R.id.seekB);

        show_text=(TextView)findViewById(R.id.show_color);
        //screen_bg.setBackgroundColor(Color.argb(255,cR,cG,cB));
        show_text.setBackgroundColor(Color.argb(255,cR,cG,cB));
        show_text.setText("status:"+light_state+";"+String.format("%02x", cR)+String.format("%02x", cG)+String.format("%02x", cB));

        sk_R.setOnSeekBarChangeListener(seekbartracking);
        sk_G.setOnSeekBarChangeListener(seekbartracking);
        sk_B.setOnSeekBarChangeListener(seekbartracking);
        lightOff();


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
    private SeekBar.OnSeekBarChangeListener seekbartracking=new SeekBar.OnSeekBarChangeListener(){

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            sk_id=seekBar.getId();
            switch (sk_id){
                case R.id.seekR:
                    cR=sk_R.getProgress();
                    //cR=progress;
                    break;

                case R.id.seekG:
                    cG=sk_G.getProgress();
                    break;

                case R.id.seekB:
                    cB=sk_B.getProgress();
                    break;

            }
            show_text.setBackgroundColor(Color.argb(255,cR,cG,cB));
            //show_text.setText("status:"+light_state+";"+String.format("%02x", cR)+String.format("%02x", cG)+String.format("%02x", cB));

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

}
