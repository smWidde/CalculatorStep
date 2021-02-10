package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.media.audiofx.Equalizer;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    boolean isDotAdded = false;
    String memorisedNumber = "0";
    String firstNumber = "";
    String action = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void AddNumber(View v)
    {
        Integer num = Integer.parseInt(((Button)v).getText().toString());
        if(num>=1&&num<=9)
        {
            TextView et = findViewById(R.id.numberText);
            String text = et.getText().toString();
            if(text.length()<14)
            {
                if(et.getText().toString().equals("0"))
                    et.setText(num.toString());
                else
                    et.setText(text+num.toString());
            }
        }
    }
    public void AddZero(View view)
    {
        TextView et = findViewById(R.id.numberText);
        String text = et.getText().toString();
        if(isDotAdded)
        {
            if(text.length()<14)
                et.setText(text+"0");
        }
        else
        {
            if(!et.getText().toString().equals("0")&&text.length()<14)
            {
                et.setText(text+"0");
            }
        }
    }
    public void AddDot(View view)
    {
        TextView et = findViewById(R.id.numberText);
        if(!isDotAdded&&et.getText().toString().length()<13)
        {
            et.setText(et.getText().toString()+".");
            isDotAdded = true;
        }
    }
    public void Clear(View view)
    {
        TextView et = findViewById(R.id.numberText);
        et.setText("0");
        isDotAdded = false;
        firstNumber="";
        action="";
        TextView et2 = findViewById(R.id.textView);
        et2.setText(firstNumber);
    }
    public void Backspace(View view)
    {
        TextView et = findViewById(R.id.numberText);
        String text = et.getText().toString();
        if(text.length()>0)
        {
            et.setText(text.substring(0,text.length()-1));
        }
        text = et.getText().toString();
        if(!text.contains("."))
        {
            isDotAdded = false;
        }
        if(text.length()==0)
        {
            Clear(view);
        }
    }
    public void MemoryClear(View view)
    {
        memorisedNumber = "0";
    }
    public void MemoryRecall(View view)
    {
        TextView et = findViewById(R.id.numberText);
        et.setText(memorisedNumber);
        isDotAdded = et.getText().toString().contains(".");
    }
    public void MemoryStore(View view)
    {
        TextView et = findViewById(R.id.numberText);
        memorisedNumber = et.getText().toString();
        Clear(view);
    }
    public void AddAction(View view)
    {
        if(firstNumber.equals(""))
        {
            action = ((Button)view).getText().toString();
            firstNumber = ((TextView)findViewById(R.id.numberText)).getText().toString();
        }
        else
        {
            firstNumber = Calculate();
            action = ((Button)view).getText().toString();
        }
        TextView et2 = findViewById(R.id.textView);
        et2.setText(firstNumber);
        TextView et = findViewById(R.id.numberText);
        et.setText("0");
        isDotAdded = false;
    }
    public void Equal(View view)
    {
        TextView et = findViewById(R.id.numberText);
        String text = Calculate();
        et.setText(text);
        TextView et2 = findViewById(R.id.textView);
        action="";
        firstNumber = "";
        isDotAdded = true;
        et2.setText(firstNumber);
    }
    public String Calculate()
    {
        Double numberOne = Double.parseDouble(firstNumber);
        TextView et = findViewById(R.id.numberText);
        Double numberTwo = Double.parseDouble(et.getText().toString());
        String tmp_act = action;
        action="";
        switch (tmp_act)
        {
            case "÷":
            {
                if(numberTwo==0)
                {
                    Toast.makeText(this, "Can`t divide by zero", Toast.LENGTH_SHORT);
                }
                else
                {
                    return ((Double)(numberOne/numberTwo)).toString();
                }
            }
            case "×":
            {
                return ((Double)(numberOne*numberTwo)).toString();
            }
            case "-":
            {
                return ((Double)(numberOne-numberTwo)).toString();
            }
            case "+":
            {
                return ((Double)(numberOne+numberTwo)).toString();
            }
        }
        return "error";
    }
}