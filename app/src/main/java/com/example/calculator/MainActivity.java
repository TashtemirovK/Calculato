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
    }

    @Override
    protected void onStart() {
        super.onStart();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                String resultNum = textView.getText().toString();
                intent.putExtra("result", resultNum);
                startActivity(intent);
            }
        });
    }

    public void onNumberClick(View view) {
        if (view.getId() == R.id.btn_one) {
            appendNumber("1");
        }
        if (view.getId() == R.id.btn_two) {
            appendNumber("2");
        }
        if (view.getId() == R.id.bnt_three) {
            appendNumber("3");
        }
        if (view.getId() == R.id.btn_four) {
            appendNumber("4");
        }
        if (view.getId() == R.id.btn_five) {
            appendNumber("5");
        }
        if (view.getId() == R.id.btn_six) {
            appendNumber("6");
        }
        if (view.getId() == R.id.btn_seven) {
            appendNumber("7");
        }
        if (view.getId() == R.id.btn_eight) {
            appendNumber("8");
        }
        if (view.getId() == R.id.btn_nine) {
            appendNumber("9");
        }
        isOperationClick = false;
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
        next.setVisibility(view.getId());

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
        if (textView.getText().toString().equals("0") || isOperationClick) {
            textView.setText(number);
        } else {
            textView.append(number);
        }
    }
}