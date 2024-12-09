package com.example.fibonacci;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView appTitle, resultTitle, textBoxResult;
    private Button submitButton;
    private EditText inputField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        appTitle = findViewById(R.id.app_title);
        appTitle.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        resultTitle = findViewById(R.id.result_title);
        resultTitle.setVisibility(View.INVISIBLE);
        resultTitle.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        textBoxResult = findViewById(R.id.result_text_box);
        textBoxResult.setVisibility(View.INVISIBLE);
        textBoxResult.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        submitButton = findViewById(R.id.submit_button);
        submitButton.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        inputField = findViewById(R.id.value_field_input);
        inputField.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        submitButton.setOnClickListener(x -> runCalc());
    }

    private void runCalc()
    {
        resultTitle.setVisibility(View.INVISIBLE);
        textBoxResult.setVisibility(View.INVISIBLE);
        int value = 0;

        try
        {
            value = Integer.parseInt(inputField.getText().toString());
        }
        catch (Exception ex)
        {
            Toast.makeText(this, "O valor deve ser um NÚMERO maior que 1 e menor que 20", Toast.LENGTH_SHORT).show();
            inputField.setText("");
            return;
        }

        if (Integer.parseInt(inputField.getText().toString()) <= 0 || Integer.parseInt(inputField.getText().toString()) >= 20)
        {
            Toast.makeText(this, "O valor deve ser maior que 1 e menor que 20", Toast.LENGTH_SHORT).show();
            inputField.setText("");
            return;
        }

        textBoxResult.setText(fibonacciCalc(value));
        inputField.setText("");
        resultTitle.setVisibility(View.VISIBLE);
        textBoxResult.setVisibility(View.VISIBLE);
    }

    private String fibonacciCalc(int value)
    {
        StringBuffer result = new StringBuffer();

        if (value <= 1) {
            return Integer.toString(value);
        }

        int current = 0;
        int previous = 0;

        for (int i = 1; i <= value; i++) {

            if (i == 1) {
                current = 1;
                previous = 0;
            } else {
                current += previous;
                previous = current - previous;
            }

            result.append(current);
            result.append(" - ");
        }

        result.delete(result.length() - 2, result.length());
        return result.toString();
    }
}