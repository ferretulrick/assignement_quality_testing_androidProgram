package assignement.ulrick.ferret.calculator;

/**
 * Created by ulrick on 18/06/2017.
 */

public class Operation extends Expression{

    Expression e1;
    Expression e2;
    char operator;

    public Operation(Expression e1, char operator, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
        this.operator = operator;
    }

    @Override
    int getValue() {
        switch (operator) {
            case '+':  return e1.getValue() + e2.getValue();
            case '-':  return e1.getValue() - e2.getValue();
            case '*':  return e1.getValue() * e2.getValue();
            case '/':  return e1.getValue() / e2.getValue();
            default: return 0;
        }
    }

}
