package com.example.beth.quizzapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startEvaluation(View view) {
        String[] answers = evaluateGui();

        int result = evaluateQuiz(answers);

        toastResult(result);
    }

    public String[] evaluateGui() {
        String[] ret = new String[5];
        EditText editTextQuestion1 = findViewById(R.id.question_1);

        CheckBox checkBoxQuestion2Ocean_quahog = findViewById(R.id.question_2_Ocean_quahog);
        CheckBox checkBoxQuestion2Sea_urchin = findViewById(R.id.question_2_sea_urchin);
        CheckBox checkBoxQuestion2Galapagos_tortois = findViewById(R.id.question_2_Galapagos_tortois);

        Boolean answerQuestion2 = false;

        if (checkBoxQuestion2Ocean_quahog.isChecked() == true && checkBoxQuestion2Sea_urchin.isChecked() == false && checkBoxQuestion2Galapagos_tortois.isChecked() == false) {
            answerQuestion2 = true;
        }

        CheckBox checkBoxQuestion4Dachshund = findViewById(R.id.question_4_Dachshund);
        CheckBox checkBoxQuestion4Poodle = findViewById(R.id.question_4_Poodle);
        CheckBox checkBoxQuestion4Pomenaria = findViewById(R.id.question_4_Pomenaria);
        CheckBox checkBoxQuestion4Chihuahua = findViewById(R.id.question_4_Chihuahua);

        Boolean answerQuestion4 = false;

        Boolean Dachshund = checkBoxQuestion4Dachshund.isChecked();
        Boolean Poodle = checkBoxQuestion4Poodle.isChecked();
        Boolean Pomenaria = checkBoxQuestion4Pomenaria.isChecked();
        Boolean Chihuahua = checkBoxQuestion4Pomenaria.isChecked();


        if (Dachshund == false && Poodle == false && Pomenaria == false && Chihuahua == true) {
            answerQuestion4 = true;
        }

        ret[0] = editTextQuestion1.getText().toString().toLowerCase();
        ret[1] = Boolean.toString(answerQuestion2);
        ret[2] = evaluateRadioGroup(R.id.radio_group_question_3).toLowerCase();
        ret[3] = Boolean.toString(answerQuestion4);
        ret[4] = evaluateRadioGroup(R.id.radio_group_question_5).toLowerCase();

        return ret;
    }

    public int evaluateQuiz(String[] answers) {
        int result = 0;
        String[] correctAnswers = {"sailfish", "Ocean quahog (clam)", "Cows", "Chihuahua", "Albatross"};

        for (int i = 0; i < correctAnswers.length; i++) {
            if (answers[i].equals(correctAnswers[i])) {
                result++;
            }
        }

        return result;
    }

    public void toastResult(int result) {
        String message = result + " out of 5. ";

        if (result == 0) {
            message += "Poor luck.";
        } else if (result == 1) {
            message += "You could do better.";
        } else if (result == 2) {
            message += "Quite nice.";
        } else if (result == 3) {
            message += "Really nice.";
        } else if (result == 4) {
            message += "Great!";
        } else if (result == 5) {
            message += "Absolutely awesome!";
        }

        Toast reportResult = Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT);
        reportResult.show();
    }

    private String evaluateRadioGroup(int id) {
        RadioGroup radioGroupQuestion;
        RadioButton radioButtonQuestion;

        radioGroupQuestion = findViewById(id);

        int radioButtonId = radioGroupQuestion.getCheckedRadioButtonId();
        radioButtonQuestion = findViewById(radioButtonId);

        if (radioButtonQuestion == null) {
            return "";
        }

        return (String)radioButtonQuestion.getText();
    }

    public void reset(View view) {
        EditText editText = findViewById(R.id.question_1);
        editText.setText("");

        CheckBox checkBox = findViewById(R.id.question_2_Ocean_quahog);
        checkBox.setChecked(false);

        checkBox = findViewById(R.id.question_2_sea_urchin);
        checkBox.setChecked(false);

        checkBox = findViewById(R.id.question_2_Galapagos_tortois);
        checkBox.setChecked(false);

        RadioGroup radioGroup = findViewById(R.id.radio_group_question_3);
        radioGroup.clearCheck();

        checkBox = findViewById(R.id.question_4_Dachshund);
        checkBox.setChecked(false);

        checkBox = findViewById(R.id.question_4_Poodle);
        checkBox.setChecked(false);

        checkBox = findViewById(R.id.question_4_Pomenaria);
        checkBox.setChecked(false);
        checkBox = findViewById(R.id.question_4_Chihuahua);
        checkBox.setChecked(false);

        radioGroup = findViewById(R.id.radio_group_question_5);
        radioGroup.clearCheck();
    }
}