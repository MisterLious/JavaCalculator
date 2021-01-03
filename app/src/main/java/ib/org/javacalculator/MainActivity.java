package ib.org.javacalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    boolean toClear = false;
    boolean seriaObliczen = false;
    boolean pressed = false;
    Calculation calculation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.Display);
        toClear = false;
        seriaObliczen = false;
        pressed = false;
        calculation = new Calculation();

    }

    //


    public void onClick(View view) {
        Button button = findViewById(view.getId());
        String label = button.getText().toString();
        DecimalFormat df = new DecimalFormat("#.#########");
        try {
            switch (label) {
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                case "8":
                case "9":

                    if (toClear) {
                        display.setText("0");
                        toClear = false;
                    }
                    if (display.getText().equals("0")) {
                        display.setText(label);
                    } else {
                        display.setText(display.getText().toString() + label);
                    }
                    pressed = true;
                break;
                case "0":
                    if (toClear) {
                        display.setText("0");
                        toClear = false;
                    }
                    if (Double.parseDouble(display.getText().toString()) % 1 != 0)
                        display.setText(display.getText().toString() + label);
                    else if (display.getText().charAt(0) != '0')
                        display.setText(display.getText().toString() + label);
                    else if (display.getText().charAt(1) == '.')
                        display.setText(display.getText().toString() + label);
                    pressed = true;

            break;
                case ".":
                    if (toClear) {
                        display.setText("0");
                        toClear = false;
                    }
                    if (Double.parseDouble(display.getText().toString()) % 1 == 0)
                        display.setText(display.getText().toString() + label);
                    pressed = true;

                    break;
                case "C":
                    calculation.setNum1(0);
                    calculation.setNum2(0);
                    calculation.setOperation(null);
                    seriaObliczen = false;
                    toClear = false;
                    pressed = false;
                    display.setText("0");

                    break;
                case "+/-":
                    display.setText(df.format(-1 * Double.parseDouble(display.getText().toString())));

                    break;
                case "%":
                    display.setText(df.format(Double.parseDouble(display.getText().toString()) / 100));

                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                    if (!seriaObliczen) {
                        calculation.setOperation(label);
                        calculation.setNum1(Double.parseDouble(display.getText().toString()));
                    } else {
                        display.setText(df.format(calculation.calculate(Double.parseDouble(display.getText().toString()), seriaObliczen)));
                        calculation.setOperation(label);
                    }
                    toClear = true;
                    seriaObliczen = true;
                break;
                case "=":
                    toClear = true;
                    seriaObliczen = false;
                    if (!pressed) {
                        display.setText(df.format(calculation.calculate(Double.parseDouble(display.getText().toString()), false)));
                    } else {
                        display.setText(df.format(calculation.calculate(Double.parseDouble(display.getText().toString()), seriaObliczen)));
                    }
                    break;
                }

        } catch (Exception ignored) {

        }
    }
}
