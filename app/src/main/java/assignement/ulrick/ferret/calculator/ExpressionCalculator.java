package assignement.ulrick.ferret.calculator;
import java.util.Vector;


/**
 * Created by ulrick on 18/06/2017.
 */

public class ExpressionCalculator {

    public String getResult(String expression){
        Vector<String> expr = separateString(expression);
        if(isValide(expr)){
            int res = getExpression(expr).getValue();
            return ""+res;
        }
        else
            return "wrong expression";
    }


    public Vector<String> separateString(String s){

        Vector<String> res=new Vector<String>();

        boolean onDigit = false;
        String temp = "";

        for(int i=0;i<s.length();i++){
            if(!onDigit && !Character.isDigit(s.charAt(i))) {
                res.add("" + s.charAt(i));
            }
            else if(!onDigit && Character.isDigit(s.charAt(i))){
                onDigit = true;
                temp = temp + s.charAt(i);
            }
            else if(onDigit && Character.isDigit(s.charAt(i))){
                temp = temp + s.charAt(i);
            }
            else {
                onDigit = false;
                res.add(temp);
                temp = "";
                res.add("" + s.charAt(i));
            }
        }
        if(onDigit){
            res.add(temp);
        }

        return res;
    }

    public boolean isValide(Vector<String> expr){


        if(expr.size() == 0){
            return false;
        }
        else if(expr.size() == 1){
            return isNumber(expr.lastElement());
        }
        else if (expr.size() == 2){
            return false;
        }
        else if(expr.size() == 3){
            return (isNumber(expr.elementAt(0)) && isNumber(expr.elementAt(2)) && isOprerator(expr.elementAt(1)))
                    || (expr.elementAt(0).equals("(") && expr.elementAt(2).equals(")") && isNumber(expr.elementAt(1)));
        }
        else{
            boolean res = expr.elementAt(0).contains("(") || isNumber(expr.elementAt(0));
            res = res && (isNumber(expr.lastElement()) || expr.lastElement().contains(")") );
            for(int i=0;i<expr.size()-1;i++){
                res = res && isPossibleNext(expr.elementAt(i),expr.elementAt(i+1));
            }
            int counter = 0;
            for(int i=0;i<expr.size();i++){
                if(expr.elementAt(i).equals("(")) counter++;
                if(expr.elementAt(i).equals(")")){
                    counter--;
                    if(counter < 0) res = false;
                }
            }
            if(counter != 0) res = false;
            return res;
        }
    }

    public Expression getExpression(Vector<String> expr){
        if(isExpressionInParanthesis(expr)){
            Vector<String> expr2 = copyPartialVector(expr,1,expr.size()-2);
            return getExpression(expr2);
        }
        else if(expr.size()==1) {
            int val = Integer.parseInt(expr.lastElement());
            Operand res = new Operand(val);
            return res;
        }
        else {
            int indDiv = getPositionOperator(expr,"/");
            int indMult = getPositionOperator(expr,"*");
            int indMinus = getPositionOperator(expr,"-");
            int indPlus = getPositionOperator(expr,"+");
            if(indPlus != -1){
                Vector<String> expr1 = copyPartialVector(expr,0,indPlus-1);
                Vector<String> expr2 = copyPartialVector(expr,indPlus+1,expr.size()-1);
                Expression e1 = getExpression(expr1);
                Expression e2 = getExpression(expr2);
                Operation res = new Operation(e1,'+',e2);
                return res;
            }
            else if(indMinus != -1){
                Vector<String> expr1 = copyPartialVector(expr,0,indMinus-1);
                Vector<String> expr2 = copyPartialVector(expr,indMinus+1,expr.size()-1);
                Expression e1 = getExpression(expr1);
                Expression e2 = getExpression(expr2);
                Operation res = new Operation(e1,'-',e2);
                return res;
            }
            else if(indMult != -1 && indDiv != -1){
                int ind;
                char op;
                if(indMult > indDiv) {
                    ind = indMult;
                    op = '*';
                }
                else {
                    ind = indDiv;
                    op = '/';
                }
                Vector<String> expr1 = copyPartialVector(expr,0,ind-1);
                Vector<String> expr2 = copyPartialVector(expr,ind+1,expr.size()-1);
                Expression e1 = getExpression(expr1);
                Expression e2 = getExpression(expr2);
                Operation res = new Operation(e1,op,e2);
                return res;
            }
            else if(indMult != -1){
                Vector<String> expr1 = copyPartialVector(expr,0,indMult-1);
                Vector<String> expr2 = copyPartialVector(expr,indMult+1,expr.size()-1);
                Expression e1 = getExpression(expr1);
                Expression e2 = getExpression(expr2);
                Operation res = new Operation(e1,'*',e2);
                return res;
            }
            else {
                Vector<String> expr1 = copyPartialVector(expr, 0, indDiv - 1);
                Vector<String> expr2 = copyPartialVector(expr, indDiv + 1, expr.size() - 1);
                Expression e1 = getExpression(expr1);
                Expression e2 = getExpression(expr2);
                Operation res = new Operation(e1, '/', e2);
                return res;
            }
        }
    }



    public boolean isNumber(String string) {
        try {
            Long.parseLong(string);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean isOprerator(String s){
        return s.equals("*") || s.equals("/") || s.equals("-") || s.equals("+");
    }

    public  boolean isPossibleNext(String s1,String s2){
        if(isNumber(s1)){
            return isOprerator(s2) || s2.equals(")");
        }
        else if(isOprerator(s1)){
            return  isNumber(s2) || s2.equals("(");
        }
        else if(s1.equals("(")){
            return isNumber(s2) || s2.equals("(");
        }
        else if(s1.equals(")")){
            return isOprerator(s2) || s2.equals(")");
        }
        return false;
    }


    public boolean isExpressionInParanthesis(Vector<String> expr){
        boolean res = expr.elementAt(0).equals("(") &&  expr.lastElement().equals(")");

        int counter = 0;
        for(int i=0;i<expr.size();i++){
            if(expr.elementAt(i).equals("(")) counter++;
            else if(expr.elementAt(i).equals(")")) counter--;
            if(counter == 0 && i!=(expr.size()-1)) return false;
        }
        return  res;
    }


    public int getPositionOperator(Vector<String> expr, String op){
        int counter = 0;
        for(int i=0;i<expr.size();i++){
            if(expr.elementAt(i).equals("(")) counter++;
            else if(expr.elementAt(i).equals(")")) counter--;
            if(counter == 0 && expr.elementAt(i).equals(op)) return i;
        }
        return  -1;
    }


    public Vector<String> copyPartialVector(Vector<String> expr, int i1, int i2){
        Vector<String> res = new Vector<String>();
        for(int i=i1;i<=i2;i++){
            res.add(expr.elementAt(i));
        }
        return res;
    }

    public  void displayVectString(Vector<String> s){
        for(int i=0;i<s.size();i++){
            System.out.print(s.elementAt(i));
        }
        System.out.println();
    }


}
