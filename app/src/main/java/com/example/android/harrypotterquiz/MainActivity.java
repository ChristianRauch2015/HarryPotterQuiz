package com.example.android.harrypotterquiz;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

   private EditText mEditTextUserName;
   private CheckBox mCheckBoxQ1Henry;
   private CheckBox mCheckBoxQ1James;
   private CheckBox mCheckBoxQ1Lily;
   private CheckBox mCheckBoxQ1Elizabeth;
   private EditText mEditTextQ2;
   private CheckBox mCheckBoxQ3Incendio;
   private CheckBox mCheckBoxQ3Avadakedavra;
   private CheckBox mCheckBoxQ3Imperio;
   private CheckBox mCheckBoxQ3Crucio;
   private RadioButton mRadioButtonQ4Mcgonagall;
   private RadioButton mRadioButtonQ4Snape;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditTextUserName = findViewById(R.id.edit_username);
        mCheckBoxQ1Henry = findViewById(R.id.check_q1_henry);
        mCheckBoxQ1James = findViewById(R.id.check_q1_james);
        mCheckBoxQ1Lily = findViewById(R.id.check_q1_lily);
        mCheckBoxQ1Elizabeth = findViewById(R.id.check_q1_elizabeth);
        mEditTextQ2 = findViewById(R.id.edit_q2);
        mCheckBoxQ3Incendio = findViewById(R.id.check_q3_incendio);
        mCheckBoxQ3Avadakedavra = findViewById(R.id.check_q3_avadakedavra);
        mCheckBoxQ3Imperio = findViewById(R.id.check_q3_imperio);
        mCheckBoxQ3Crucio= findViewById(R.id.check_q3_crucio);
        mRadioButtonQ4Mcgonagall = findViewById(R.id.radio_q4_mcgonagall);
        mRadioButtonQ4Snape = findViewById(R.id.radio_q4_snape);
    }

    /**
     * This method is called when the submit button is clicked.
     */
    public void submit(View view) {
        String name = mEditTextUserName.getText().toString();
        if (name.isEmpty()) {
            name = "Anonymous";
        }
        int numQuestions = 4;
        boolean isWellDone = false;
        int numCorrect = 0;
        if (checkQuestion1()) {
            numCorrect += 1;
        }
        if (checkQuestion2()) {
            numCorrect += 1;
        }
        if (checkQuestion3()) {
            numCorrect += 1;
        }
        if (checkQuestion4()) {
            numCorrect += 1;
        }
        if (numCorrect >= 3) {
            isWellDone = true;
        }
        displayMessage(createOutputText(name, numCorrect, numQuestions,
                isWellDone));
    }

    /**
     * This method is called when checking the first question.
     *
     * @return whether question was anwered correctly
     */
    private boolean checkQuestion1() {
        if (mCheckBoxQ1Elizabeth.isChecked() || mCheckBoxQ1Henry.isChecked()) {
            return false;
        }
        return mCheckBoxQ1James.isChecked() && mCheckBoxQ1Lily.isChecked();
    }

    /**
     * This method is called when checking the second question.
     *
     * @return whether question was anwered correctly
     */
    private boolean checkQuestion2() {
        String answerQ2 = mEditTextQ2.getText().toString();
        return answerQ2.equalsIgnoreCase("muggle");
    }

    /**
     * This method is called when checking the third question.
     *
     * @return whether question was anwered correctly
     */
    private boolean checkQuestion3() {
        if (mCheckBoxQ3Incendio.isChecked()) {
            return false;
        }
        return (mCheckBoxQ3Crucio.isChecked() && mCheckBoxQ3Imperio.isChecked()) &&
                mCheckBoxQ3Avadakedavra.isChecked();
    }

    /**
     * This method is called when checking the fourth question.
     *
     * @return whether question was anwered correctly
     */
    private boolean checkQuestion4() {
        if (mRadioButtonQ4Snape.isChecked()) {
            return false;
        }
        return mRadioButtonQ4Mcgonagall.isChecked();
    }

    /**
     * This method creates an output text for the submission.
     *
     * @param name        of the app user
     * @param num_correct the number of correct answers
     * @param num_total   number of questions
     * @param isWellDone  whether the user has reached a good score
     * @return resultMessage
     */
    private String createOutputText(String name, int num_correct, int num_total,
                                    boolean isWellDone) {
        String message;
        Resources res = getResources();
        if (isWellDone) {
            message = "Well done!";
        } else {
            message = "Keep trying!";
        }
        String resultMessage = res.getString(R.string.result, name, num_correct, num_total,
                message);
        return resultMessage;
    }

    /**
     * This method displays the given text
     *
     * @param message to be displayed
     */

    private void displayMessage(String message) {
        Toast.makeText(this, message,
                Toast.LENGTH_LONG).show();
    }
}
