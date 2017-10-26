package com.huangmc3.caculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Stack;
import java.lang.String;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button add;
    private Button subtract;
    private Button mutiply;
    private Button divide;
    private Button equal;
    private Button clear;
    private Button delete;
    private Button point;
    private Button parentheses_left;
    private Button parentheses_right;

    private Button zero;
    private Button one;
    private Button two;
    private Button three;
    private Button four;
    private Button five;
    private Button six;
    private Button seven;
    private Button eight;
    private Button nine;

    private EditText showText;
    private String Operatename = "";
    private String ReversePolish = "";
    private Stack<String> expressionconvert = new Stack<>();
    private int lenth = 0,stackLenth = 0;
    private double Sum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        zero = (Button) findViewById(R.id.zero);
        one = (Button) findViewById(R.id.one);
        two = (Button) findViewById(R.id.two);
        three = (Button) findViewById(R.id.three);
        four = (Button) findViewById(R.id.four);
        five = (Button) findViewById(R.id.five);
        six = (Button) findViewById(R.id.six);
        seven = (Button) findViewById(R.id.seven);
        eight = (Button) findViewById(R.id.eight);
        nine = (Button) findViewById(R.id.nine);

        add = (Button) findViewById(R.id.add);
        subtract = (Button) findViewById(R.id.subtract);
        mutiply = (Button) findViewById(R.id.multiply);
        divide = (Button) findViewById(R.id.divide);
        equal = (Button) findViewById(R.id.equal);
        clear = (Button) findViewById(R.id.clear);
        delete = (Button) findViewById(R.id.delete);
        point = (Button) findViewById(R.id.point);
        parentheses_left = (Button) findViewById(R.id.parentheses_left);
        parentheses_right = (Button) findViewById(R.id.parentheses_right);
        showText =(EditText)findViewById(R.id.showText);
        showText.setCursorVisible(false);

        zero.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        add.setOnClickListener(this);
        subtract.setOnClickListener(this);
        mutiply.setOnClickListener(this);
        divide.setOnClickListener(this);
        equal.setOnClickListener(this);
        clear.setOnClickListener(this);
        delete.setOnClickListener(this);
        point.setOnClickListener(this);
        parentheses_left.setOnClickListener(this);
        parentheses_right.setOnClickListener(this);
        showText.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.zero:
                Operatename = AddSum('0');
                showText.setText(Operatename);
                break;
            case R.id.one:
                Log.e("huangmc3:","R.id.one");
                Operatename = AddSum('1');
                showText.setText(Operatename);
                break;
            case R.id.two:
                Log.e("huangmc3:","R.id.two");
                Operatename = AddSum('2');
                showText.setText(Operatename);
                break;
            case R.id.three:
                Operatename = AddSum('3');
                showText.setText(Operatename);
                break;
            case R.id.four:
                Operatename = AddSum('4');
                showText.setText(Operatename);
                break;
            case R.id.five:
                Operatename = AddSum('5');
                showText.setText(Operatename);
                break;
            case R.id.six:
                Operatename = AddSum('6');
                showText.setText(Operatename);
                break;
            case R.id.seven:
                Operatename = AddSum('7');
                showText.setText(Operatename);
                break;
            case R.id.eight:
                Operatename = AddSum('8');
                showText.setText(Operatename);
                break;
            case R.id.nine:
                Operatename = AddSum('9');
                showText.setText(Operatename);
                break;
            case R.id.add:
                Log.e("huangmc3:","R.id.add");
                Operatename = AddSum('+');
                showText.setText(Operatename);
                break;
            case R.id.subtract:
                Operatename = AddSum('-');
                showText.setText(Operatename);
                break;
            case R.id.multiply:
                Operatename = AddSum('*');
                showText.setText(Operatename);
                break;
            case R.id.divide:
                Operatename = AddSum('/');
                showText.setText(Operatename);
                break;
            case R.id.clear:
                Operatename = "";
                ReversePolish = "";
                showText.setText(Operatename);
                break;
            case R.id.delete:
                if (Operatename.length() >= 1) {
                    Operatename = Operatename.substring(0, Operatename.length() - 1);
                }
                showText.setText(Operatename);
                break;
            case R.id.point:
                Operatename = AddSum('.');
                showText.setText(Operatename);
                break;
            case R.id.parentheses_left:
                Operatename = AddSum('(');
                showText.setText(Operatename);
                break;
            case R.id.parentheses_right:
                Operatename = AddSum(')');
                showText.setText(Operatename);
                break;
            case R.id.equal:
                Log.e("huangmc3:","R.id.equal");
                lenth=Operatename.length();
                ConvertToReversePolish(Operatename,lenth);
                CaculateResult(ReversePolish);
                Operatename=Operatename+"="+String.valueOf(Sum);
                showText.setText(Operatename);
                break;
        }

    }

    public String AddSum(char c) {
        Operatename = Operatename + String.valueOf(c);
        return Operatename;
    }


    public void ConvertToReversePolish(String a,int lenth) {
        Log.e("huangmc3:","ConvertToReversePolish");
        //Stack<String> s1 = new Stack<String>();
        Stack<String> s2 = new Stack<String>();
        String result = "";
        for(int i=0;i<lenth;i++){
            if(a.charAt(i) == '+' ||
                    a.charAt(i) == '-' ||
                    a.charAt(i) == '*' ||
                    a.charAt(i) == '/' ||
                    a.charAt(i) == '(' ||
                    a.charAt(i) == ')'
                    )
                result += " "+ a.charAt(i) + " ";
            else
                result += a.charAt(i);	// result 10 + 2 * 3
        }
        Log.e("huangmc3 operatename add blank:",result);
        String[] operatenameconvert=result.split(" "); // 10+2*3
        int lenth1=operatenameconvert.length;
        Log.e("huangmc3 lenth1=", String.valueOf(lenth1));
        try {
            for (int i = 0; i < lenth1; i++) {
                if (!operatenameconvert[i].equals("(") &&
                        !operatenameconvert[i].equals(")") &&
                        !operatenameconvert[i].equals("+") &&
                        !operatenameconvert[i].equals("-") &&
                        !operatenameconvert[i].equals("*") &&
                        !operatenameconvert[i].equals("/")) {
                    expressionconvert.push(operatenameconvert[i]);
                    stackLenth++;
                    Log.e("huangmc3 stackLenth=", String.valueOf(stackLenth));
                    Log.e("huangmc3 operatenameconvert expressionconvert.push:",expressionconvert.peek());
                    Log.e("huangmc3 operatenameconvert:",operatenameconvert[i]);
                }
                switch (operatenameconvert[i]) {
                    case "(":
                        s2.push("(");
                        break;

                    case ")":
                        if (!s2.empty()) {
                            while (!s2.peek().equals("("))
                                expressionconvert.push(s2.pop());
                            stackLenth++;
                            Log.e("huangmc3 stackLenth=", String.valueOf(stackLenth));
                        }
                        s2.pop();
                        break;
                    case "+":

                        while (!s2.empty() && (proprity("+") < proprity(s2.peek()))) {
                            expressionconvert.push(s2.pop());
                            stackLenth++;
                            Log.e("huangmc3 stackLenth=", String.valueOf(stackLenth));
                        }
                            s2.push("+");
                        if (s2.empty())
                            s2.push("+");

                        Log.e("huangmc3 case + s2.peek", s2.peek());
                        Log.e("huangmc3 case + expressionconvert.peek", expressionconvert.peek());
                        break;
                    case "-":

                        while (!s2.empty() && (proprity("-") < proprity(s2.peek())))
                        {   expressionconvert.push(s2.pop());
                        stackLenth++;
                        Log.e("huangmc3 stackLenth=", String.valueOf(stackLenth));}
                        s2.push("-");

                        if (s2.empty())
                            s2.push("-");
                        break;
                    case "*":

                        while (!s2.empty() && (proprity("*") < proprity(s2.peek())))
                        {   expressionconvert.push(s2.pop());
                        stackLenth++;
                        Log.e("huangmc3 stackLenth=", String.valueOf(stackLenth));}
                        s2.push("*");
                        if (s2.empty())
                            s2.push("*");
                        break;
                    case "/":

                        while (!s2.empty() && (proprity("/") < proprity(s2.peek())))
                        {   expressionconvert.push(s2.pop());
                        stackLenth++;
                        Log.e("huangmc3 stackLenth=", String.valueOf(stackLenth));}
                        s2.push("/");
                        if (s2.empty())
                            s2.push("/");
                        break;

                }
            }
            while(!s2.empty()){

                expressionconvert.push(s2.pop());
                stackLenth++;
                Log.e("huangmc3 stackLenth=", String.valueOf(stackLenth));
                Log.e("huangmc3 expressionconvert.peek()", expressionconvert.peek());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e("huangmc3 stackLenth=", String.valueOf(stackLenth));
        Log.e("huangmc3 lenth1=", String.valueOf(lenth1));
        for(int i=0;i<lenth1;i++) {
            ReversePolish = ReversePolish + expressionconvert.get(i);
            Log.e("huangmc3 ConvertToReversePolish expressionconvert=", expressionconvert.get(i));
        }
        Log.e("huangmc3 ReversePolish=", ReversePolish);
    }


    public double CaculateResult(String b) {
        double Num1, Num2;
        Stack<String> c = new Stack<String>();
        Log.e("huangmc3 CaculateResult stackLenth=", String.valueOf(stackLenth));
        for (int i = 0; i < stackLenth; i++) {
            try {
                if (!expressionconvert.get(i).equals("(") &&
                        !expressionconvert.get(i).equals(")") &&
                        !expressionconvert.get(i).equals("+") &&
                        !expressionconvert.get(i).equals("-") &&
                        !expressionconvert.get(i).equals("*") &&
                        !expressionconvert.get(i).equals("/")) {
                    c.push(expressionconvert.get(i));
                    Log.e("huangmc3 CaculateResult:", expressionconvert.get(i));
                }
                switch (expressionconvert.get(i)) {
                    case "+":
                        Num1 = Double.parseDouble(c.pop());
                        Num2 = Double.parseDouble(c.pop());
                        Sum = Num2+Num1;
                        c.push(String.valueOf(Sum));
                        Log.e("huangmc3 Sum + =", String.valueOf(Sum));
                        break;
                    case "-":
                        Num1 = Double.parseDouble(c.pop());
                        Num2 = Double.parseDouble(c.pop());
                        Sum = Num2-Num1;
                        c.push(String.valueOf(Sum));
                        Log.e("huangmc3 Sum - =", String.valueOf(Sum));
                        break;
                    case "*":
                        Num1 = Double.parseDouble(c.pop());
                        Num2 = Double.parseDouble(c.pop());
                        Sum = Num2*Num1;
                        c.push(String.valueOf(Sum));
                        Log.e("huangmc3 Sum * =", String.valueOf(Sum));
                        break;
                    case "/":
                        Num1 = Double.parseDouble(c.pop());
                        Num2 = Double.parseDouble(c.pop());
                        Sum = Num2/Num1;
                        c.push(String.valueOf(Sum));
                        Log.e("huangmc3 Sum / =", String.valueOf(Sum));
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return Sum;
    }

    public int proprity(String c) {
        int order=0;
        switch (c) {
            case "(":
                order = 0;
                break;
            case "+":
                order = 1;
                break;
            case "-":
                order = 2;
                break;
            case "/":
                order = 3;
                break;
            case "*":
                order = 4;
                break;
            case ")":
                order = 5;
                break;   //order：(,+-,*/)
        }
        Log.e("huangmc3", String.valueOf(order));
        return order;
    }
}
