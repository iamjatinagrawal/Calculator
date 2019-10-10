package com.example.calculator;

import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.math.*;

public class MainActivity extends AppCompatActivity {

    boolean oprState = false;
    double pNum,cNum,reserveNum,result = 0; //pnum = previous number, cNum = current number, result is to calculate result.
    BigDecimal bdPrevious,bdCurrent,bdresult;
    String currentOpr = ""; //to check if an operation is pressed so that a new input can be stored.
    boolean addPlus,subMinus,product,division,decimalPressed,isnegativeNum= false;
    int startIndex,endIndex;

    public void add(){
        TextView textView = (TextView) findViewById(R.id.textView);
        TextView input = (TextView) findViewById(R.id.input);
        Button plus = findViewById(R.id.plus); //declaring a button for addition and assigning to the button with the id "plus"
        Button minus = findViewById(R.id.minus); //declaring a button for addition and assigning to the button with the id "plus"
        Button zero = findViewById(R.id.zero);
        Button multiply = findViewById(R.id.multiply);
        Button divide = findViewById(R.id.divide);

        if(addPlus==true){

            cNum = Double.parseDouble(input.getText().toString());
            bdCurrent = BigDecimal.valueOf(cNum);
            bdPrevious = BigDecimal.valueOf(pNum);
            bdresult = bdPrevious.add(bdCurrent);
            result = bdresult.doubleValue();
            cNum = 0;
            pNum = result;
            addPlus=true;
            product=false;
            subMinus = false;
            division = false;
            display_result();
        }

        else if(subMinus==true){
            if(input.getText().toString()==zero.getText().toString()){
                textView.setText(textView.getText().toString().substring(0,textView.getText().toString().length()-2)+" "+plus.getText().toString());
                subMinus=false;
                product=false;
                division = false;
                addPlus=true;
            }
            else{
                cNum = Double.parseDouble(input.getText().toString());
                bdCurrent = BigDecimal.valueOf(cNum);
                bdPrevious = BigDecimal.valueOf(pNum);
                bdresult = bdPrevious.subtract(bdCurrent);
                result = bdresult.doubleValue();
                cNum = 0;
                pNum = result;
                addPlus=true;
                subMinus=false;
                product=false;
                division = false;
                display_result();
            }


        }

        else if(product==true){
            if(input.getText().toString()==zero.getText().toString()){
                textView.setText(textView.getText().toString().substring(0,textView.getText().toString().length()-2)+" "+plus.getText().toString());
                addPlus=true;
                subMinus=false;
                product=false;
                division  =false;
            }
            else{
                cNum = Double.parseDouble(input.getText().toString());
                bdCurrent = BigDecimal.valueOf(cNum);
                bdPrevious = BigDecimal.valueOf(pNum);
                bdresult = bdPrevious.multiply(bdCurrent);
                result = bdresult.doubleValue();
                cNum = 0;
                pNum = result;
                addPlus=true;
                subMinus=false;
                product=false;
                division  =false;
                display_result();
            }
        }

        else if(division==true){
            if(input.getText().toString()==zero.getText().toString()){
                textView.setText(textView.getText().toString().substring(0,textView.getText().toString().length()-2)+" "+plus.getText().toString());
                addPlus=true;
                subMinus=false;
                product=false;
                division = false;
            }
            else{
                cNum = Double.parseDouble(input.getText().toString());
                bdCurrent = BigDecimal.valueOf(cNum);
                bdPrevious = BigDecimal.valueOf(pNum);
                bdresult = bdPrevious.divide(bdCurrent, MathContext.DECIMAL32);
                result = bdresult.doubleValue();
                cNum = 0;
                pNum = result;
                addPlus=true;
                subMinus=false;
                product=false;
                division = false;
                display_result();
            }
        }

        else{
            if(input.getText().toString()!=zero.getText().toString()){
                pNum = Double.parseDouble(input.getText().toString());
                if(Math.ceil(pNum)==pNum){
                    long long_pnum = (long) pNum;
                    textView.setText(""+long_pnum+" "+plus.getText().toString());
                }
                else{
                    textView.setText(input.getText().toString()+" "+plus.getText().toString());
                }
                addPlus=true;
            }
        }

        oprState=true;
        decimalPressed=false;
        input.setText(zero.getText().toString());
    }

    public void minus(){
        TextView textView = (TextView) findViewById(R.id.textView);
        TextView input = (TextView) findViewById(R.id.input);
        Button minus = findViewById(R.id.minus); //declaring a button for addition and assigning to the button with the id "plus"
        Button plus = findViewById(R.id.plus);
        Button zero = findViewById(R.id.zero);

        if(addPlus==true){
            if(input.getText().toString()==zero.getText().toString()){
                textView.setText(textView.getText().toString().substring(0,textView.getText().toString().length()-2)+" "+minus.getText().toString());
                addPlus=false;
                product=false;
                division=false;
                subMinus=true;
            }
            else {
                cNum = Double.parseDouble(input.getText().toString());
                bdCurrent = BigDecimal.valueOf(cNum);
                bdPrevious = BigDecimal.valueOf(pNum);
                bdresult = bdPrevious.add(bdCurrent);
                result = bdresult.doubleValue();
                cNum = 0;
                pNum = result;
                subMinus = true;
                addPlus=false;
                product=false;
                division=false;
                display_result();
            }

        }

        else if(subMinus==true){
            cNum = Double.parseDouble(input.getText().toString());
            bdCurrent = BigDecimal.valueOf(cNum);
            bdPrevious = BigDecimal.valueOf(pNum);
            bdresult = bdPrevious.subtract(bdCurrent);
            result = bdresult.doubleValue();
            cNum = 0;
            pNum = result;
            subMinus=true;
            display_result();
        }

        else if(product==true){
            if(input.getText().toString()==zero.getText().toString()){
                textView.setText(textView.getText().toString().substring(0,textView.getText().toString().length()-2)+" "+minus.getText().toString());
                subMinus=true;
                product=false;
                division = false;
                addPlus = false;
            }
            else{
                cNum = Double.parseDouble(input.getText().toString());
                bdCurrent = BigDecimal.valueOf(cNum);
                bdPrevious = BigDecimal.valueOf(pNum);
                bdresult = bdPrevious.multiply(bdCurrent);
                result = bdresult.doubleValue();
                cNum = 0;
                pNum = result;
                addPlus=false;
                subMinus=true;
                product=false;
                division = false;
                display_result();
            }
        }

        else if(division==true){
            if(input.getText().toString()==zero.getText().toString()){
                textView.setText(textView.getText().toString().substring(0,textView.getText().toString().length()-2)+" "+minus.getText().toString());
                addPlus=false;
                subMinus=true;
                product=false;
                division = false;
            }
            else{
                cNum = Double.parseDouble(input.getText().toString());
                bdCurrent = BigDecimal.valueOf(cNum);
                bdPrevious = BigDecimal.valueOf(pNum);
                bdresult = bdPrevious.divide(bdCurrent, MathContext.DECIMAL32);
                result = bdresult.doubleValue();
                cNum = 0;
                pNum = result;
                addPlus=false;
                subMinus=true;
                product=false;
                division = false;
                display_result();
            }
        }

        else{
            pNum = Double.parseDouble(input.getText().toString());
            if (Math.ceil(pNum) == pNum) {
                long long_pnum = (long) pNum;
                textView.setText("" + long_pnum + " " + minus.getText().toString());
            }
            else {
                textView.setText(input.getText().toString() + " " + minus.getText().toString());
            }
            subMinus = true;
        }

        oprState=true;
        decimalPressed = false;
        input.setText(zero.getText().toString());
    }

    public void multiply(){
        TextView textView = (TextView) findViewById(R.id.textView);
        TextView input = (TextView) findViewById(R.id.input);
        Button minus = findViewById(R.id.minus); //declaring a button for addition and assigning to the button with the id "plus"
        Button plus = findViewById(R.id.plus);
        Button multiply = findViewById(R.id.multiply);
        Button zero = findViewById(R.id.zero);

        if(addPlus==true){
            if(input.getText().toString()==zero.getText().toString()){
                textView.setText(textView.getText().toString().substring(0,textView.getText().toString().length()-2)+" "+multiply.getText().toString());
                product = true;
                addPlus=false;
                subMinus=false;
                division = false;
            }
            else{
                cNum = Double.parseDouble(input.getText().toString());
                bdCurrent = BigDecimal.valueOf(cNum);
                bdPrevious = BigDecimal.valueOf(pNum);
                bdresult = bdPrevious.add(bdCurrent);
                result = bdresult.doubleValue();
                cNum = 0;
                pNum = result;
                product=true;
                addPlus=false;
                subMinus=false;
                division = false;
                display_result();
            }
        }

        else if(subMinus==true){
            if(input.getText().toString()==zero.getText().toString()){
                textView.setText(textView.getText().toString().substring(0,textView.getText().toString().length()-2)+" "+multiply.getText().toString());
                addPlus=false;
                subMinus=false;
                product=true;
                division = false;
            }
            else{
                cNum = Double.parseDouble(input.getText().toString());
                bdCurrent = BigDecimal.valueOf(cNum);
                bdPrevious = BigDecimal.valueOf(pNum);
                bdresult = bdPrevious.subtract(bdCurrent);
                result = bdresult.doubleValue();
                cNum = 0;
                pNum = result;
                addPlus=false;
                subMinus=false;
                product=true;
                division = false;
                display_result();
            }
        }

        else if(product==true){
            if(input.getText().toString()!=zero.getText().toString()){
                cNum = Double.parseDouble(input.getText().toString());
                bdCurrent = BigDecimal.valueOf(cNum);
                bdPrevious = BigDecimal.valueOf(pNum);
                bdresult = bdPrevious.multiply(bdCurrent);
                result = bdresult.doubleValue();
                cNum = 0;
                pNum = result;
                addPlus=false;
                subMinus=false;
                product=true;
                division = false;
                display_result();
            }
        }

        else if(division==true){
            if(input.getText().toString()==zero.getText().toString()){
                textView.setText(textView.getText().toString().substring(0,textView.getText().toString().length()-2)+" "+multiply.getText().toString());
                addPlus=false;
                subMinus=false;
                product=true;
                division = false;
            }
            else{
                cNum = Double.parseDouble(input.getText().toString());
                bdCurrent = BigDecimal.valueOf(cNum);
                bdPrevious = BigDecimal.valueOf(pNum);
                bdresult = bdPrevious.divide(bdCurrent, MathContext.DECIMAL32);
                result = bdresult.doubleValue();
                cNum = 0;
                pNum = result;
                addPlus=false;
                subMinus=false;
                product=true;
                division = false;
                display_result();
            }
        }

        else{
            if(input.getText().toString()!=zero.getText().toString()){
                pNum = Double.parseDouble(input.getText().toString());
                if(Math.ceil(pNum)==pNum){
                    long long_pnum = (long) pNum;
                    textView.setText(""+long_pnum+" "+multiply.getText().toString());
                }
                else{
                    textView.setText(input.getText().toString()+" "+multiply.getText().toString());
                }
                product=true;
            }
        }

        oprState=true;
        decimalPressed = false;
        input.setText(zero.getText().toString());

    }

    public void divide(){
        TextView textView = (TextView) findViewById(R.id.textView);
        TextView input = (TextView) findViewById(R.id.input);
        Button minus = findViewById(R.id.minus); //declaring a button for addition and assigning to the button with the id "plus"
        Button plus = findViewById(R.id.plus);
        Button multiply = findViewById(R.id.multiply);
        Button zero = findViewById(R.id.zero);
        Button divide = findViewById(R.id.divide);

        if(addPlus==true){
            if(input.getText().toString()==zero.getText().toString()){
                textView.setText(textView.getText().toString().substring(0,textView.getText().toString().length()-2)+" "+divide.getText().toString());
                product = false;
                addPlus=false;
                subMinus=false;
                division = true;
            }
            else{
                cNum = Double.parseDouble(input.getText().toString());
                bdCurrent = BigDecimal.valueOf(cNum);
                bdPrevious = BigDecimal.valueOf(pNum);
                bdresult = bdPrevious.add(bdCurrent);
                result = bdresult.doubleValue();
                cNum = 0;
                pNum = result;
                product=false;
                addPlus=false;
                subMinus=false;
                division=true;
                display_result();
            }
        }

        else if(subMinus==true){
            if(input.getText().toString()==zero.getText().toString()){
                textView.setText(textView.getText().toString().substring(0,textView.getText().toString().length()-2)+" "+multiply.getText().toString());
                division=true;
            }
            else{
                cNum = Double.parseDouble(input.getText().toString());
                bdCurrent = BigDecimal.valueOf(cNum);
                bdPrevious = BigDecimal.valueOf(pNum);
                bdresult = bdPrevious.subtract(bdCurrent);
                result = bdresult.doubleValue();
                cNum = 0;
                pNum = result;
                addPlus=false;
                subMinus=false;
                product=false;
                division = true;
                display_result();
            }
        }

        else if(product==true){
            if(input.getText().toString()==zero.getText().toString()){
                textView.setText(textView.getText().toString().substring(0,textView.getText().toString().length()-2)+" "+divide.getText().toString());
                addPlus=false;
                subMinus=false;
                product=false;
                division = true;
            }
            else{
                cNum = Double.parseDouble(input.getText().toString());
                bdCurrent = BigDecimal.valueOf(cNum);
                bdPrevious = BigDecimal.valueOf(pNum);
                bdresult = bdPrevious.multiply(bdCurrent);
                result = bdresult.doubleValue();
                cNum = 0;
                pNum = result;
                addPlus=false;
                subMinus=false;
                product=false;
                division = true;
                display_result();
            }
        }

        else if(division==true){

            if(input.getText().toString()!=zero.getText().toString()) {
                cNum = Double.parseDouble(input.getText().toString());
                bdCurrent = BigDecimal.valueOf(cNum);
                bdPrevious = BigDecimal.valueOf(pNum);
                bdresult = bdPrevious.divide(bdCurrent, MathContext.DECIMAL32);
                Log.i("yo",""+bdresult);
                result = bdresult.doubleValue();
                cNum = 0;
                pNum = result;
                addPlus = false;
                product = false;
                subMinus = false;
                division = true;
                display_result();
            }
        }

        else{
            if(input.getText().toString()!=zero.getText().toString()){
                pNum = Double.parseDouble(input.getText().toString());
                if(Math.ceil(pNum)==pNum){
                    long long_pnum = (long) pNum;
                    textView.setText(""+long_pnum+" "+divide.getText().toString());
                }
                else{
                    textView.setText(input.getText().toString()+" "+divide.getText().toString());
                }
                division=true;
            }
        }

        oprState=true;
        decimalPressed = false;
        input.setText(zero.getText().toString());

    }

    public void equal(){

        TextView textView = (TextView) findViewById(R.id.textView);
        TextView input = (TextView) findViewById(R.id.input);
        Button minus = findViewById(R.id.minus); //declaring a button for addition and assigning to the button with the id "plus"
        Button plus = findViewById(R.id.plus);
        Button zero = findViewById(R.id.zero);

        if(addPlus==true){
            cNum = Double.parseDouble(input.getText().toString());
            bdCurrent = BigDecimal.valueOf(cNum);
            bdPrevious = BigDecimal.valueOf(pNum);
            bdresult = bdPrevious.add(bdCurrent);
            result = bdresult.doubleValue();
            cNum = 0;
            pNum = result;
            textView.setText("");
            if(Math.ceil(result)==result){
                long long_result = (long) result;
                if(String.valueOf(long_result).length()>15){
                    input.setTextSize(30);
                    input.setText(""+long_result);
                }
                else{
                    input.setText(""+long_result);
                }
            }
            else{
                if(String.valueOf(result).length()>15){
                    input.setTextSize(30);
                    input.setText(""+bdresult);
                }
            }
            addPlus=false;
        }

        else if(subMinus==true){
            cNum = Double.parseDouble(input.getText().toString());
            bdCurrent = BigDecimal.valueOf(cNum);
            bdPrevious = BigDecimal.valueOf(pNum);
            bdresult = bdPrevious.subtract(bdCurrent);
            result = bdresult.doubleValue();
            cNum = 0;
            pNum = result;
            textView.setText("");
            if(Math.ceil(result)==result){
                long long_result = (long) result;
                if(String.valueOf(long_result).length()>15){
                    input.setTextSize(30);
                    input.setText(""+long_result);
                }
                else{
                    input.setText(""+long_result);
                }
            }
            else{
                if(String.valueOf(result).length()>15){
                    input.setTextSize(30);
                    input.setText(""+bdresult);
                }
            }
            subMinus = false;
        }

        else if(product==true){
            if(input.getText().toString()!=zero.getText().toString()){
                cNum = Double.parseDouble(input.getText().toString());
                bdCurrent = BigDecimal.valueOf(cNum);
                bdPrevious = BigDecimal.valueOf(pNum);
                bdresult = bdPrevious.multiply(bdCurrent);
                result = bdresult.doubleValue();
                cNum = 0;
                pNum = result;
                textView.setText("");
                if(Math.ceil(result)==result){
                    long long_result = (long) result;
                    if(String.valueOf(long_result).length()>15){
                        input.setTextSize(30);
                        input.setText(""+long_result);
                    }
                    else{
                        input.setText(""+long_result);
                    }
                }
                else{
                    if(String.valueOf(result).length()>15){
                        input.setTextSize(30);
                        input.setText(""+bdresult);
                    }
                }
            }
            else{
                String inputValue = textView.getText().toString().substring(0,textView.getText().toString().length()-2);
                if(inputValue.length()>15){
                    input.setTextSize(30);
                    input.setText(""+inputValue);
                }
                else{
                    input.setText(""+inputValue);
                }
                textView.setText("");
            }
            product = false;
        }

        else if(division==true){
            if(input.getText().toString()!=zero.getText().toString()){
                cNum = Double.parseDouble(input.getText().toString());
                bdCurrent = BigDecimal.valueOf(cNum);
                bdPrevious = BigDecimal.valueOf(pNum);
                bdresult = bdPrevious.divide(bdCurrent, MathContext.DECIMAL64);
                result = bdresult.doubleValue();
                cNum = 0;
                pNum = result;
                textView.setText("");
                if(Math.ceil(result)==result){
                    long long_result = (long) result;
                    if(String.valueOf(long_result).length()>15){
                        input.setTextSize(30);
                        input.setText(""+long_result);
                    }
                    else{
                        input.setText(""+long_result);
                    }
                }
                else{
                    if(String.valueOf(result).length()>15){
                        input.setTextSize(30);
                        input.setText(""+bdresult);
                    }
                }
            }
            else{
                String inputValue = textView.getText().toString().substring(0,textView.getText().toString().length()-2);
                if(inputValue.length()>15){
                    input.setTextSize(30);
                    input.setText(""+inputValue);
                }
                else{
                    input.setText(""+inputValue);
                }
                textView.setText("");
            }
            division = false;
        }

        else{

            if(textView.getText()!=""){
                input.setText(textView.getText().toString().substring(0,textView.getText().toString().length()-2));
                textView.setText("");
            }
            else{

            }
        }
    }

    public void delete(){
        TextView input = (TextView) findViewById(R.id.input);
        Button zero = (Button) findViewById(R.id.zero);
        if(input.getText().toString()!=zero.getText().toString()){
            if(input.getText().toString().length()==1){
                input.setText(zero.getText().toString());
            }
            else{
                if(input.getText().toString().endsWith(".")){
                    decimalPressed=false;
                }
                input.setText(input.getText().toString().substring(0,input.getText().toString().length()-1));
            }
        }

    }

    public void percentNum(){
        TextView input = (TextView) findViewById(R.id.input);
        Button zero = (Button) findViewById(R.id.zero);

        if(Double.parseDouble(input.getText().toString())!=0){
            if(pNum==0){
                input.setText(""+Double.parseDouble(input.getText().toString())/100);
            }
            else{
                //DecimalFormat df = new DecimalFormat("0.000");
                double perNum = pNum*(Double.parseDouble(input.getText().toString())/100);
                BigDecimal bdperNum = new BigDecimal(perNum,MathContext.DECIMAL64);
                String newPerNum = bdperNum.toPlainString();
                if(bdperNum.toPlainString().endsWith("0")){
                    while(true){
                        newPerNum = newPerNum.substring(0,newPerNum.length()-1);
                        if(newPerNum.endsWith("0")){

                        }
                        else{
                            break;
                        }
                    }

                    if(newPerNum.length()>15){
                        input.setTextSize(30);
                        input.setText(""+newPerNum);
                    }
                    else{
                        input.setText(""+newPerNum);
                    }
                }
                else{
                    if(newPerNum.length()>15){
                        input.setTextSize(30);
                        input.setText(""+newPerNum);
                    }
                    else{
                        input.setText(""+newPerNum);
                    }
                }

            }
        }
    }

    public void negNumber(){

        TextView input = (TextView) findViewById(R.id.input);
        Button zero = (Button) findViewById(R.id.zero);
        double negPosNumber;

        if(input.getText().toString()!=zero.getText().toString()) {

            if(input.getText().toString().endsWith(".")){
                if (isnegativeNum == false) {
                    negPosNumber = -1 * Double.parseDouble(input.getText().toString());
                    isnegativeNum = true;
                } else {
                    negPosNumber = -1 * Double.parseDouble(input.getText().toString());
                    isnegativeNum = false;
                }

                long long_negPosNumber = (long) negPosNumber;
                input.setText("" +long_negPosNumber+".");

            }

            else{
                if (isnegativeNum == false) {
                    negPosNumber = -1 * Double.parseDouble(input.getText().toString());
                    isnegativeNum = true;
                } else {
                    negPosNumber = -1 * Double.parseDouble(input.getText().toString());
                    isnegativeNum = false;
                }

                if(Math.ceil(negPosNumber)==negPosNumber){
                    long long_negPosNumber = (long) negPosNumber;
                    input.setText("" +long_negPosNumber);
                }
                else{
                    input.setText(""+negPosNumber);
                }

            }
        }
        decimalPressed = false;
    }


    public void all_clear(){
        TextView textView = (TextView) findViewById(R.id.textView);
        TextView input = (TextView) findViewById(R.id.input);
        Button zero = findViewById(R.id.zero); //declaring a button for number 0 and assigning to the button with the id "zero"
        textView.setText("");
        input.setText(zero.getText().toString());
        boolean oprState = false;
        pNum = 0;
        cNum = 0;
        result = 0;
        BigDecimal bdPrevious,bdCurrent;
        String currentOpr = "";
        addPlus = false;
        product = false;
        subMinus= false;
        division = false;
        decimalPressed = false;
        isnegativeNum = false;
        input.setTextSize(40);
    }

    public void display_result(){
        TextView textView = (TextView) findViewById(R.id.textView);
        TextView input = (TextView) findViewById(R.id.input);
        Button plus = (Button) findViewById(R.id.plus);
        Button minus = (Button) findViewById(R.id.minus);
        Button multiply = (Button) findViewById(R.id.multiply);
        Button divide = (Button) findViewById(R.id.divide);

        input.setTextSize(40);

            if(Math.ceil(result)==result){
                long long_result = (long) result;
                long long_range = 9223372036854775806L;

                if(addPlus==true){
                    textView.setText(""+long_result+" "+plus.getText().toString());
                }
                else if(subMinus==true){
                    if(long_result>long_range){
                        Toast.makeText(this, "Result is too large to be displayed", Toast.LENGTH_LONG).show();
                    }
                    else{
                        textView.setText(""+long_result+" "+minus.getText().toString());
                    }
                }

                else if(product==true){
                    if(long_result>long_range){
                        Toast.makeText(this, "Result is too large to be displayed", Toast.LENGTH_LONG).show();
                    }
                    else{
                        textView.setText(""+long_result+" "+multiply.getText().toString());
                    }
                }

                else if(division==true){
                    if(long_result>long_range){
                        Toast.makeText(this, "Result is too large to be displayed", Toast.LENGTH_LONG).show();
                    }
                    else{
                        textView.setText(""+long_result+" "+divide.getText().toString());
                    }
                }

                else{

                }

            }
            else {
                textView.setText("");
                if(addPlus==true){
                    String strbdresult = bdresult.toPlainString();
                    if(strbdresult.endsWith("0")){
                        textView.setText(""+strbdresult.substring(0,strbdresult.length()-1)+" "+plus.getText().toString());
                    }
                    else{
                        textView.setText(""+strbdresult+" "+plus.getText().toString());
                    }
                }
                else if(subMinus==true){
                    String strbdresult = bdresult.toPlainString();
                    if(strbdresult.endsWith("0")){
                        textView.setText(""+strbdresult.substring(0,strbdresult.length()-1)+" "+minus.getText().toString());
                    }
                    else{
                        textView.setText(""+strbdresult+" "+minus.getText().toString());
                    }
                }
                else if(product==true){
                    String strbdresult = bdresult.toPlainString();
                    if(strbdresult.endsWith("0")){
                        textView.setText(""+strbdresult.substring(0,strbdresult.length()-1)+" "+multiply.getText().toString());
                    }
                    else{
                        textView.setText(""+strbdresult+" "+multiply.getText().toString());
                    }
                }
                else if(division==true){
                    String strbdresult = bdresult.toPlainString();
                    if(strbdresult.endsWith("0")){
                        textView.setText(""+strbdresult.substring(0,strbdresult.length()-1)+" "+divide.getText().toString());
                    }
                    else{
                        textView.setText(""+strbdresult+" "+divide.getText().toString());
                    }
                }
                else{

                }
            }
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = (TextView) findViewById(R.id.textView);
        final TextView input = (TextView) findViewById(R.id.input);

        final MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.calc_button_click);

        final Button one = findViewById(R.id.one); //declaring a button for number 1 and assigning to the button with the id "one"
        final Button two = findViewById(R.id.two); //declaring a button for number 2 and assigning to the button with the id "two"
        final Button three = findViewById(R.id.three); //declaring a button for number 3 and assigning to the button with the id "three"
        final Button four = findViewById(R.id.four); //declaring a button for number 4 and assigning to the button with the id "four"
        final Button five = findViewById(R.id.five); //declaring a button for number 5 and assigning to the button with the id "five"
        final Button six = findViewById(R.id.six); //declaring a button for number 6 and assigning to the button with the id "six"
        final Button seven = findViewById(R.id.seven); //declaring a button for number 7 and assigning to the button with the id "seven"
        final Button eight = findViewById(R.id.eight); //declaring a button for number 8 and assigning to the button with the id "eight"
        final Button nine = findViewById(R.id.nine); //declaring a button for number 9 and assigning to the button with the id "nine"
        final Button zero = findViewById(R.id.zero); //declaring a button for number 0 and assigning to the button with the id "zero"
        final Button plus = findViewById(R.id.plus); //declaring a button for addition and assigning to the button with the id "plus"
        final Button minus = findViewById(R.id.minus); //declaring a button for subtraction and assigning to the button with the id "minus"
        final Button equal = findViewById(R.id.equal); //declaring a button for equals and assigning to the button with the id "equal"
        final Button multiply = findViewById(R.id.multiply);//declaring a button for multiplication and assigning to the button with the id "multiply"
        final Button divide = findViewById(R.id.divide);//declaring a button for divison and assigning to the button with the id "divide"
        final Button delete = findViewById(R.id.delete);//declaring a button for deletion and assigning to the button with the id "delete"
        final Button ac = findViewById(R.id.all_clear);//declaring a button for clearing all values and assigning to the button with the id "all_clear"
        final Button decimal = findViewById(R.id.decimal);//declaring a button for decimal and assigning to the button with the id "decimal"
        final Button percentbutton = findViewById(R.id.percent);//declaring a button for percentage and assigning to the button with the id "percent"
        final Button negativeNumber = findViewById(R.id.negativenum);


        View.OnClickListener calcListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (view.getId()){

                    case R.id.one:
                        mediaPlayer.start();
                        if(input.getText().toString().length()<9){
                            input.setTextSize(40);
                            if(input.getText().toString()==zero.getText().toString()){
                                input.setText(one.getText().toString());
                                if(oprState==true){
                                    oprState=false;
                                }
                            }
                            else{
                                if(oprState==true){
                                    input.setText("");
                                    input.setText(""+one.getText().toString());
                                    oprState=false;
                                }
                                else{
                                    input.setText(input.getText().toString()+one.getText().toString());
                                }
                            }
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Limit of characters reached", Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case R.id.two:
                        mediaPlayer.start();
                        if(input.getText().toString().length()<9) {
                            input.setTextSize(40);
                            if (input.getText().toString() == zero.getText().toString()) {
                                input.setText(two.getText().toString());
                                if (oprState == true) {
                                    oprState = false;
                                }
                            } else {
                                if (oprState == true) {
                                    input.setText("");
                                    input.setText("" + two.getText().toString());
                                    oprState = false;
                                } else {
                                    input.setText(input.getText().toString() + two.getText().toString());
                                }
                            }
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Limit of characters reached", Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case R.id.three:
                        mediaPlayer.start();
                        if(input.getText().toString().length()<9) {
                            input.setTextSize(40);
                            if (input.getText().toString() == zero.getText().toString()) {
                                input.setText(three.getText().toString());
                                if (oprState == true) {
                                    oprState = false;
                                }
                            } else {
                                if (oprState == true) {
                                    input.setText("");
                                    input.setText("" + three.getText().toString());
                                    oprState = false;
                                } else {
                                    input.setText(input.getText().toString() + three.getText().toString());
                                }
                            }
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Limit of characters reached", Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case R.id.four:
                        mediaPlayer.start();
                        if(input.getText().toString().length()<9) {
                            input.setTextSize(40);
                            if (input.getText().toString() == zero.getText().toString()) {
                                input.setText(four.getText().toString());
                                if (oprState == true) {
                                    oprState = false;
                                }
                            } else {
                                if (oprState == true) {
                                    input.setText("");
                                    input.setText("" + four.getText().toString());
                                    oprState = false;
                                } else {
                                    input.setText(input.getText().toString() + four.getText().toString());
                                }
                            }
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Limit of characters reached", Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case R.id.five:
                        mediaPlayer.start();
                        if(input.getText().toString().length()<9) {
                            input.setTextSize(40);
                            if (input.getText().toString() == zero.getText().toString()) {
                                input.setText(five.getText().toString());
                                if (oprState == true) {
                                    oprState = false;
                                }
                            } else {
                                if (oprState == true) {
                                    input.setText("");
                                    input.setText("" + five.getText().toString());
                                    oprState = false;
                                } else {
                                    input.setText(input.getText().toString() + five.getText().toString());
                                }
                            }
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Limit of characters reached", Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case R.id.six:
                        mediaPlayer.start();
                        if(input.getText().toString().length()<9) {
                            input.setTextSize(40);
                            if (input.getText().toString() == zero.getText().toString()) {
                                input.setText(six.getText().toString());
                                if (oprState == true) {
                                    oprState = false;
                                }
                            } else {
                                if (oprState == true) {
                                    input.setText("");
                                    input.setText("" + six.getText().toString());
                                    oprState = false;
                                } else {
                                    input.setText(input.getText().toString() + six.getText().toString());
                                }
                            }
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Limit of characters reached", Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case R.id.seven:
                        mediaPlayer.start();
                        if(input.getText().toString().length()<9) {
                            input.setTextSize(40);
                            if (input.getText().toString() == zero.getText().toString()) {
                                input.setText(seven.getText().toString());
                                if (oprState == true) {
                                    oprState = false;
                                }
                            } else {
                                if (oprState == true) {
                                    input.setText("");
                                    input.setText("" + seven.getText().toString());
                                    oprState = false;
                                } else {
                                    input.setText(input.getText().toString() + seven.getText().toString());
                                }
                            }
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Limit of characters reached", Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case R.id.eight:
                        mediaPlayer.start();
                        if(input.getText().toString().length()<9) {
                            input.setTextSize(40);
                            if (input.getText().toString() == zero.getText().toString()) {
                                input.setText(eight.getText().toString());
                                if (oprState == true) {
                                    oprState = false;
                                }
                            } else {
                                if (oprState == true) {
                                    input.setText("");
                                    input.setText("" + eight.getText().toString());
                                    oprState = false;
                                } else {
                                    input.setText(input.getText().toString() + eight.getText().toString());
                                }
                            }
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Limit of characters reached", Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case R.id.nine:
                        mediaPlayer.start();
                        if(input.getText().toString().length()<9) {
                            input.setTextSize(40);
                            if (input.getText().toString() == zero.getText().toString()) {
                                input.setText(nine.getText().toString());
                                if (oprState == true) {
                                    oprState = false;
                                }
                            } else {
                                if (oprState == true) {
                                    input.setText("");
                                    input.setText("" + nine.getText().toString());
                                    oprState = false;
                                } else {
                                    input.setText(input.getText().toString() + nine.getText().toString());
                                }
                            }
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Limit of characters reached", Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case R.id.zero:
                        mediaPlayer.start();
                        if(input.getText().toString().length()<9) {
                            input.setTextSize(40);
                            if (input.getText().toString() == zero.getText().toString()) {

                            } else {
                                if (oprState == true) {
                                    if (input.getText().toString() == zero.getText().toString()) {
                                        input.setText(input.getText().toString() + zero.getText().toString());
                                    } else {

                                    }
                                    oprState = false;
                                } else {
                                    input.setText(input.getText().toString() + zero.getText().toString());
                                }
                            }
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Limit of characters reached", Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case R.id.decimal:
                        mediaPlayer.start();
                        if(input.getText().toString().length()<9) {
                            input.setTextSize(40);
                            if (decimalPressed == false) {
                                decimalPressed = true;
                                input.setText(input.getText().toString() + decimal.getText().toString());
                                if (oprState == true) {
                                    oprState = false;
                                }
                            }
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Limit of characters reached", Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case R.id.plus:
                        mediaPlayer.start();
                        add();
                        break;

                    case R.id.minus:
                        mediaPlayer.start();
                        minus();
                        break;

                    case R.id.multiply:
                        mediaPlayer.start();
                        multiply();
                        break;

                    case R.id.divide:
                        mediaPlayer.start();
                        divide();
                        break;

                    case R.id.all_clear:
                        mediaPlayer.start();
                        all_clear();
                        break;

                    case R.id.equal:
                        mediaPlayer.start();
                        equal();
                        break;

                    case R.id.delete:
                        mediaPlayer.start();
                        delete();
                        break;

                    case R.id.percent:
                        mediaPlayer.start();
                        percentNum();
                        break;

                    case R.id.negativenum:
                        mediaPlayer.start();
                        negNumber();
                        break;
                }

            }
        };

        one.setOnClickListener(calcListener);
        two.setOnClickListener(calcListener);
        three.setOnClickListener(calcListener);
        four.setOnClickListener(calcListener);
        five.setOnClickListener(calcListener);
        six.setOnClickListener(calcListener);
        seven.setOnClickListener(calcListener);
        eight.setOnClickListener(calcListener);
        nine.setOnClickListener(calcListener);
        zero.setOnClickListener(calcListener);
        plus.setOnClickListener(calcListener);
        minus.setOnClickListener(calcListener);
        equal.setOnClickListener(calcListener);
        divide.setOnClickListener(calcListener);
        multiply.setOnClickListener(calcListener);
        delete.setOnClickListener(calcListener);
        ac.setOnClickListener(calcListener);
        decimal.setOnClickListener(calcListener);
        percentbutton.setOnClickListener(calcListener);
        negativeNumber.setOnClickListener(calcListener);


    }
}
