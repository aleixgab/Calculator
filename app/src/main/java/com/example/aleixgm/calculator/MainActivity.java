package com.example.aleixgm.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String num = "";
    private String prevNum = "";
    private enum Operator {
        OPERATOR_NONE,
        OPERATOR_PLUS,
        OPERATOR_MULTIPLY,
        OPERATOR_LESS,
        OPERATOR_SPLIT
    }
    private Operator operator = Operator.OPERATOR_NONE;
    private TextView numView;
    private boolean point = false, resetNums = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numView = findViewById(R.id.curr_num);
        numView.setText(num);
    }

    public void onClickDigit(View view){
        if(resetNums) {
            num = "";
            resetNums = false;
            point = false;
        }
        Button b = (Button)view;
        num += b.getText().toString().charAt(0);
        numView.setText(num);
    }
    public void onClickOperator(View view){
        Button b = (Button)view;
        if (operator != Operator.OPERATOR_NONE)
        {
           operation();
           numView.setText(num);
        }
        else
        {
            numView.setText("");
        }
        switch (b.getId())
        {
            case R.id.btn_less:
                operator = Operator.OPERATOR_LESS;
                break;
            case R.id.btn_multiply:
                operator = Operator.OPERATOR_MULTIPLY;
                break;
            case R.id.btn_plus:
                operator = Operator.OPERATOR_PLUS;
                break;
            case R.id.btn_split:
                operator = Operator.OPERATOR_SPLIT;
                break;
        }
        prevNum = num;
        resetNums = true;
        point = false;
    }
    public void onClickEqual(View view){
        if (!prevNum.isEmpty())
        {
            operation();
        }
            numView.setText(num);
            prevNum = "";
            resetNums = true;
            point = true;
        }

    public void operation() {
        double f_prevnum = Float.parseFloat(prevNum);
        double f_num = Float.parseFloat(num);
        double equal = 0;
        switch (operator) {
            case OPERATOR_NONE:
                break;
            case OPERATOR_LESS:
                equal = f_prevnum - f_num;
                break;
            case OPERATOR_MULTIPLY:
                equal = f_prevnum * f_num;
                break;
            case OPERATOR_PLUS:
                equal = f_prevnum + f_num;
                break;
            case OPERATOR_SPLIT:
                equal = f_prevnum / f_num;
                break;
        }
        num = String.valueOf(equal);
        operator = Operator.OPERATOR_NONE;

    }
        public void onClickPoint(View view){
        if(!point && !num.isEmpty()) {
            point = true;
            num += ".";
            numView.setText(num);
        }
    }
}
