package ib.org.javacalculator;

public class Calculation {
    double num1;
    double num2;
    double wynik;

    String operation;

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setNum1(double num1) {
        this.num1 = num1;
    }
    public void setNum2(double num2) {
        this.num2 = num2;
    }

    public double calculate(double num2,boolean series){

        if (num1==0){
            num1 = num2;
        }else{
            this.num2 = num2;
        }


        switch (operation) {
            case "+":
                wynik = num1 + this.num2;
                break;
            case "-":
                wynik = num1 - this.num2;
                break;
            case "/":
                {
                if (this.num2 == 0) {
                    wynik = 0;
                } else
                    wynik = num1 / this.num2;
            }
            break;
            case "*":
                wynik = num1 * this.num2;
                break;
        }
        if (!series){
            num1 = 0;
        }else{
            num1 = wynik;
        }

        if (Double.isInfinite(wynik))
            wynik=0;

        return wynik;
    }
}

