package assignement.ulrick.ferret.calculator;

/**
 * Created by ulrick on 18/06/2017.
 */

public class Operand extends  Expression {

    int val;

    Operand(int val){
        this.val=val;
    }

    @Override
    int getValue() {
        return val;
    }
}
