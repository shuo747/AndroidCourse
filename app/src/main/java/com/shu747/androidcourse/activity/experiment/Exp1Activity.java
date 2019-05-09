package com.shu747.androidcourse.activity.experiment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.shu747.androidcourse.R;
import com.shu747.androidcourse.activity.MyActivity;
import com.shu747.androidcourse.activity.experiment.util.Calculator;

public class Exp1Activity extends MyActivity implements View.OnClickListener {

    private StringBuffer stringBuffer = new StringBuffer();
    private GridLayout gl_exp1;
    private TextView tv_exp1_expression;
    private TextView tv_exp1_result;
    private Button bt_exp1_cancel;
    private Button bt_exp1_symbol;
    private Button bt_exp1_no1;
    private Button bt_exp1_clear;
    private Button bt_exp1_num_7;
    private Button bt_exp1_num_8;
    private Button bt_exp1_num_9;
    private Button bt_exp1_divide;
    private Button bt_exp1_delivery;
    private Button bt_exp1_num_4;
    private Button bt_exp1_num_5;
    private Button bt_exp1_num_6;
    private Button bt_exp1_multiply;
    private Button bt_exp1_sqrt;
    private Button bt_exp1_num_1;
    private Button bt_exp1_num_2;
    private Button bt_exp1_num_3;
    private Button bt_exp1_num_minus;
    private Button bt_exp1_num_solve;
    private Button bt_exp1_num_zero;
    private Button bt_exp1_point;
    private Button bt_exp1_add;
    private Button bt_exp1_parentheses_left;
    private Button bt_exp1_parentheses_right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exp1);
        initView();
    }


    private void initView() {
        gl_exp1 = (GridLayout) findViewById(R.id.gl_exp1);
        int columnCount = gl_exp1.getColumnCount();
        int screenWidth = this.getWindowManager().getDefaultDisplay().getWidth();
        int size = (int) (screenWidth / 5);
        //Log.e(TAG, "column:" + columnCount + ";  screenwidth:" + screenWidth);
        for (int i = 0; i < gl_exp1.getChildCount(); i++) {
            Button button = (Button) gl_exp1.getChildAt(i);
            button.setWidth(size);
            button.setHeight(size);
            button.setTextSize(24);
        }

        tv_exp1_expression = (TextView) findViewById(R.id.tv_exp1_expression);
        tv_exp1_expression.setOnClickListener(this);
        tv_exp1_result = (TextView) findViewById(R.id.tv_exp1_result);
        tv_exp1_result.setOnClickListener(this);
        bt_exp1_cancel = (Button) findViewById(R.id.bt_exp1_cancel);
        bt_exp1_cancel.setOnClickListener(this);
        bt_exp1_symbol = (Button) findViewById(R.id.bt_exp1_symbol);
        bt_exp1_symbol.setOnClickListener(this);
        bt_exp1_no1 = (Button) findViewById(R.id.bt_exp1_no1);
        bt_exp1_no1.setOnClickListener(this);
        bt_exp1_clear = (Button) findViewById(R.id.bt_exp1_clear);
        bt_exp1_clear.setOnClickListener(this);
        bt_exp1_num_7 = (Button) findViewById(R.id.bt_exp1_num_7);
        bt_exp1_num_7.setOnClickListener(this);
        bt_exp1_num_8 = (Button) findViewById(R.id.bt_exp1_num_8);
        bt_exp1_num_8.setOnClickListener(this);
        bt_exp1_num_9 = (Button) findViewById(R.id.bt_exp1_num_9);
        bt_exp1_num_9.setOnClickListener(this);
        bt_exp1_divide = (Button) findViewById(R.id.bt_exp1_divide);
        bt_exp1_divide.setOnClickListener(this);
        bt_exp1_delivery = (Button) findViewById(R.id.bt_exp1_delivery);
        bt_exp1_delivery.setOnClickListener(this);
        bt_exp1_num_4 = (Button) findViewById(R.id.bt_exp1_num_4);
        bt_exp1_num_4.setOnClickListener(this);
        bt_exp1_num_5 = (Button) findViewById(R.id.bt_exp1_num_5);
        bt_exp1_num_5.setOnClickListener(this);
        bt_exp1_num_6 = (Button) findViewById(R.id.bt_exp1_num_6);
        bt_exp1_num_6.setOnClickListener(this);
        bt_exp1_multiply = (Button) findViewById(R.id.bt_exp1_multiply);
        bt_exp1_multiply.setOnClickListener(this);
        bt_exp1_sqrt = (Button) findViewById(R.id.bt_exp1_sqrt);
        bt_exp1_sqrt.setOnClickListener(this);
        bt_exp1_num_1 = (Button) findViewById(R.id.bt_exp1_num_1);
        bt_exp1_num_1.setOnClickListener(this);
        bt_exp1_num_2 = (Button) findViewById(R.id.bt_exp1_num_2);
        bt_exp1_num_2.setOnClickListener(this);
        bt_exp1_num_3 = (Button) findViewById(R.id.bt_exp1_num_3);
        bt_exp1_num_3.setOnClickListener(this);
        bt_exp1_num_minus = (Button) findViewById(R.id.bt_exp1_num_minus);
        bt_exp1_num_minus.setOnClickListener(this);
        bt_exp1_num_solve = (Button) findViewById(R.id.bt_exp1_num_solve);
        bt_exp1_num_solve.setOnClickListener(this);
        bt_exp1_num_zero = (Button) findViewById(R.id.bt_exp1_num_zero);
        bt_exp1_num_zero.setOnClickListener(this);
        bt_exp1_point = (Button) findViewById(R.id.bt_exp1_point);
        bt_exp1_point.setOnClickListener(this);
        bt_exp1_add = (Button) findViewById(R.id.bt_exp1_add);
        bt_exp1_add.setOnClickListener(this);
        /*bt_exp1_parentheses_left = (Button) findViewById(R.id.bt_exp1_parentheses_left);
        bt_exp1_parentheses_left.setOnClickListener(this);
        bt_exp1_parentheses_right = (Button) findViewById(R.id.bt_exp1_parentheses_right);
        bt_exp1_parentheses_right.setOnClickListener(this);*/
    }

    @Override
    public void onClick(View v) {
        int len = stringBuffer.length();
        switch (v.getId()) {
            case R.id.bt_exp1_cancel:
                if (len > 0) stringBuffer.deleteCharAt(len - 1);
                break;
            case R.id.bt_exp1_symbol:
                if (len > 0 && stringBuffer.charAt(len - 1) == '-'){
                    stringBuffer.deleteCharAt(len-1);
                    break;
                }
                if (len > 0 && stringBuffer.charAt(len - 1) == '+'){
                        stringBuffer.deleteCharAt(len-1);
                        stringBuffer.append("-");
                        break;
                }
                stringBuffer.append("-");
                break;
            case R.id.bt_exp1_no1:

                if (len > 0 && Character.isDigit(stringBuffer.charAt(len - 1))){
                    int p = len-1;
                    while (p>=0){
                       if(!Character.isDigit(stringBuffer.charAt(p)))
                           break;
                       p--;
                    }
                    ++p;
                    //Log.e("-----================---",stringBuffer.substring(p,len));
                    //System.out.println();
                    String temp = "(1/"+stringBuffer.substring(p,len)+")";
                    stringBuffer.delete(p,len);
                    stringBuffer.append(temp);
                }
                break;
            case R.id.bt_exp1_clear:
                stringBuffer = new StringBuffer();
                tv_exp1_result.setText("0");
                break;
            case R.id.bt_exp1_num_7:
                stringBuffer.append("7");
                break;
            case R.id.bt_exp1_num_8:
                stringBuffer.append("8");
                break;
            case R.id.bt_exp1_num_9:
                stringBuffer.append("9");
                break;
            case R.id.bt_exp1_divide:
                stringBuffer.append("/");
                break;
            case R.id.bt_exp1_delivery:
                stringBuffer.append("%");
                break;
            case R.id.bt_exp1_num_4:
                stringBuffer.append("4");
                break;
            case R.id.bt_exp1_num_5:
                stringBuffer.append("5");
                break;
            case R.id.bt_exp1_num_6:
                stringBuffer.append("6");
                break;
            case R.id.bt_exp1_multiply:
                stringBuffer.append("*");
                break;
            case R.id.bt_exp1_sqrt:
                stringBuffer.append("√");
                break;
            case R.id.bt_exp1_num_1:
                stringBuffer.append("1");
                break;
            case R.id.bt_exp1_num_2:
                stringBuffer.append("2");
                break;
            case R.id.bt_exp1_num_3:
                stringBuffer.append("3");
                break;
            case R.id.bt_exp1_num_minus:
                stringBuffer.append("-");
                break;
            case R.id.bt_exp1_num_solve:
                solve();
                break;
            case R.id.bt_exp1_num_zero:
                stringBuffer.append("0");
                break;
            case R.id.bt_exp1_point:
                stringBuffer.append(".");
                break;
            case R.id.bt_exp1_add:
                stringBuffer.append("+");
                break;
           /* case R.id.bt_exp1_parentheses_left:
                stringBuffer.append("(");
                break;
            case R.id.bt_exp1_parentheses_right:
                stringBuffer.append(")");
                break;*/

        }

        displayExpression();
    }

    private void solve() {
        double result = Calculator.conversion(stringBuffer.toString());
        tv_exp1_result.setText("=" + result);
    }

    private void displayExpression() {
        tv_exp1_expression.setText(stringBuffer);
    }
}
