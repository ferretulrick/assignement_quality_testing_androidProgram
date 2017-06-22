package assignement.ulrick.ferret.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ExpressionCalculator expressionCalculator;
    boolean newExpr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expressionCalculator = new ExpressionCalculator();
        newExpr = true;
    }

    public void clickTouch(View v){
        TextView result = (TextView) findViewById(R.id.result);
        String text = "";
        if(newExpr) {
            text = result.getText().toString() + ((Button)v).getText().toString();
        }
        else {
            text = ((Button)v).getText().toString();
            newExpr = true;
        }
        result.setText(text.toCharArray(),0,text.toCharArray().length);
    }

    public void calculate(View v){
        TextView result = (TextView) findViewById(R.id.result);
        String text = expressionCalculator.getResult(result.getText().toString());
        result.setText(text.toCharArray(),0,text.toCharArray().length);
        newExpr = false;
    }

    public void clear(View v){
        TextView result = (TextView) findViewById(R.id.result);
        String text = "";
        result.setText(text.toCharArray(),0,text.toCharArray().length);
        newExpr = true;
    }
}
