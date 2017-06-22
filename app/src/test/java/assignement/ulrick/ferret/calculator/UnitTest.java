package assignement.ulrick.ferret.calculator;

import org.junit.Test;

import java.util.Vector;


import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTest {
    @Test
    public void testExpressionClass() throws Exception {
        int val1 = 5;
        int val2 = 2;
        Operand e1 = new Operand(val1);
        Operand e2 = new Operand(val2);
        //test on Operand class
        assertEquals(val1, e1.getValue());

        //test on Operation class with +
        Operation e3 = new Operation(e1,'+',e2);
        assertEquals(val1 + val2, e3.getValue());

        //test on Operation class with -
        Operation e4 = new Operation(e1,'-',e2);
        assertEquals(val1 - val2, e4.getValue());

        //test on Operation class with *
        Operation e5 = new Operation(e1,'*',e2);
        assertEquals(val1 * val2, e5.getValue());

        //test on Operation class with /
        Operation e6 = new Operation(e1,'/',e2);
        assertEquals(val1 / val2, e6.getValue());

        //test on Operation class with more complicated operation
        Operation e7 = new Operation(e3,'*',e4);
        assertEquals((val1 + val2)*(val1 - val2), e7.getValue());
    }

    @Test
    public void testConverterClass() throws Exception{

        ExpressionCalculator ec = new ExpressionCalculator();
        //test on function wich separate the element of mathematical expression
        Vector<String> vect2 = ec.separateString("(245+3)/11");
        Vector<String> vect1= new Vector<String>();
        vect1.add("(");
        vect1.add("245");
        vect1.add("+");
        vect1.add("3");
        vect1.add(")");
        vect1.add("/");
        vect1.add("11");
        assertEquals(vect1,vect2);



        //test wich take a mathematical expression string and return the result in string
        int valExcepted = (42);
        assertEquals( ""+valExcepted, ec.getResult("(42)"));
        valExcepted = 9751/9491*9494/6565*46464-78+4984*646*65/12-54896;
        assertEquals( ""+valExcepted, ec.getResult("9751/9491*9494/6565*46464-78+4984*646*65/12-54896"));
        valExcepted = 7*8/6+((756*89-6+7)/(4*8+9)*78);
        assertEquals( ""+valExcepted, ec.getResult("7*8/6+((756*89-6+7)/(4*8+9)*78)"));
        valExcepted = ((7+(31))*(4)/(321/(3)+751));
        assertEquals( ""+valExcepted, ec.getResult("((7+(31))*(4)/(321/(3)+751))"));
        //same test with wrong expression
        assertEquals( "wrong expression", ec.getResult(")2("));
        assertEquals( "wrong expression", ec.getResult("((4+1)"));
        assertEquals( "wrong expression", ec.getResult("((4)+(1)))"));
        assertEquals( "wrong expression", ec.getResult("8*-7"));
        assertEquals( "wrong expression", ec.getResult("8-()+4"));
    }



}