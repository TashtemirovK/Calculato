package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Double first, second, result;
    private Boolean isOperationClick;
    private String operation = "";
    private MaterialButton next;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text_view);
        next = findViewById(R.id.next_menu);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                String resultNum = ((TextView) findViewById(R.id.text_view)).getText().toString();
                intent.putExtra("result", resultNum);
                startActivity(intent);
            }
        });
    }

    public void onNumberClick(View view) {
        if (view.getId() == R.id.btn_one) {
            appendNumber("1");
        } else if (view.getId() == R.id.btn_two) {
            appendNumber("2");
        } else if (view.getId() == R.id.bnt_three) {
            appendNumber("3");
        } else if (view.getId() == R.id.btn_four) {
            appendNumber("4");
        } else if (view.getId() == R.id.btn_five) {
            appendNumber("5");
        } else if (view.getId() == R.id.btn_six) {
            appendNumber("6");
        } else if (view.getId() == R.id.btn_seven) {
            appendNumber("7");
        } else if (view.getId() == R.id.btn_eight) {
            appendNumber("8");
        } else if (view.getId() == R.id.btn_nine) {
            appendNumber("9");
        } else if (view.getId() == R.id.btn_zero) {
            appendNumber("0");
        }
    }

    public void onOperationClick(View view) {
        if (view.getId() == R.id.btn_clear) {
            textView.setText("0");
            first = 0.0;
            second = 0.0;
        } else if (view.getId() == R.id.btn_equal) {
            second = Double.parseDouble(textView.getText().toString());
            if (operation.equals("%")) {
                result = (first / 100) * second;
                textView.setText(canselDouble(result));
            } else if (operation.equals("*")) {
                result = first * second;
                textView.setText(canselDouble(result));
            } else if (operation.equals("+")) {
                result = first + second;
                textView.setText(canselDouble(result));
            } else if (operation.equals("-")) {
                result = first - second;
                textView.setText(canselDouble(result));
            } else if (operation.equals("/")) {
                if (second == 0) {
                    textView.setText("на ноль делить нельзя");
                } else {
                    result = first / second;
                    textView.setText(canselDouble(result));
                }
                isOperationClick = true;
            }
        } else if (view.getId() == R.id.btn_plus) {
            operation = "+";
            first = Double.parseDouble(textView.getText().toString());
            isOperationClick = true;
        } else if (view.getId() == R.id.btn_minus) {
            operation = "-";
            first = Double.parseDouble(textView.getText().toString());
            isOperationClick = true;
        } else if (view.getId() == R.id.btn_multiplication) {
            operation = "*";
            first = Double.parseDouble(textView.getText().toString());
            isOperationClick = true;
        } else if (view.getId() == R.id.btn_percent) {
            operation = "%";
            first = Double.parseDouble(textView.getText().toString());
            isOperationClick = true;
        } else if (view.getId() == R.id.btn_devide) {
            operation = "/";
            first = Double.parseDouble(textView.getText().toString());
            isOperationClick = true;
        }
        next.setVisibility(view.getVisibility());
    }

    public String canselDouble(Double result) {
        String text = result.toString();
        if (text.substring(text.length() - 2).equals(".0")) {
            return text.substring(0, text.length() - 2);
        } else {
            return result.toString();
        }
    }

    public void appendNumber(String number) {
        next.setVisibility(View.VISIBLE);
        if (textView.getText().toString().equals("0")) {
            textView.setText(number);
        } else if (isOperationClick) {
            textView.setText(number);
        } else {
            textView.append(number);
        }
        isOperationClick = false;
    }
}