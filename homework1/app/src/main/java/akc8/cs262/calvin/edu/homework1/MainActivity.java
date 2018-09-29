package akc8.cs262.calvin.edu.homework1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {


    TextView results;
    EditText value1Edit, value2Edit;
    Button calcButn;
    Spinner operator;

    float result_num;
    int num1, num2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        value1Edit = findViewById(R.id.value1Edit);
        value2Edit = findViewById(R.id.value2Edit);
        operator = findViewById(R.id.Operator_spinner);
        calcButn = findViewById(R.id.calcButn);
        results = findViewById(R.id.results);



        calcButn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = Integer.parseInt(value1Edit.getText().toString());
                num2 = Integer.parseInt(value2Edit.getText().toString());{
                    if (operator.getSelectedItem().toString().equals("+")) {
                        result_num = num1 + num2;
                    } else if (operator.getSelectedItem().toString().equals("−")) {
                        result_num = num1 - num2;
                    } else if (operator.getSelectedItem().toString().equals("÷")) {
                        result_num = num1 / num2;
                    } else if (operator.getSelectedItem().toString().equals("×")) {
                        result_num = num1 * num2;
                    }

                        results.setText(String.valueOf(result_num));
                }

            }
        });
    }
}
