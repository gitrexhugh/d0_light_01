package com.example.rex_h.d0_light_01;

import android.app.Activity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;

public class main_light_01 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        load_default_layout();
    }
    private void load_default_layout(){
        setContentView(R.layout.deafult_layout);
    }
}
