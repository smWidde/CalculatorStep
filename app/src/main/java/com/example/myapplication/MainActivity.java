package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivity that = this;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                that.findViewById(R.id.button79).setOnClickListener(this::testClick);
                that.findViewById(R.id.editTextNumberDecimal).setOnClickListener(this::textClick);
            }
            public void testClick(View View)
            {
                Toast.makeText(that, "You god damn right!", Toast.LENGTH_SHORT).show();
            }
            public void textClick(View view)
            {
                InputMethodManager imm = (InputMethodManager) that.getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        };
        runnable.run();
    }
}