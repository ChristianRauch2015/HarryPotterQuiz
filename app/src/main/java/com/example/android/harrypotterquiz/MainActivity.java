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

   private EditText editText_userName_;
   private CheckBox checkBox_q1_henry_;
   private CheckBox checkBox_q1_james_;
   private CheckBox checkBox_q1_lily_;
   private CheckBox checkBox_q1_elizabeth_;
   private EditText editText_q2_;
   private CheckBox checkBox_q3_incendio_;
   private CheckBox checkBox_q3_avadakedavra_;
   private CheckBox checkBox_q3_imperio_;
   private CheckBox checkBox_q3_crucio_;
   private RadioButton radio_button_q4_mcgonagall_;
   private RadioButton radio_button_q4_snape_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText_userName_ = findViewById(R.id.editText_username);
        checkBox_q1_henry_ = findViewById(R.id.checkBox_q1_henry);
        checkBox_q1_james_ = findViewById(R.id.checkBox_q1_james);
        checkBox_q1_lily_ = findViewById(R.id.checkBox_q1_lily);
        checkBox_q1_elizabeth_ = findViewById(R.id.checkBox_q1_elizabeth);
        editText_q2_ = findViewById(R.id.editText_q2);
        checkBox_q3_incendio_ = findViewById(R.id.checkBox_q3_incendio);
        checkBox_q3_avadakedavra_ = findViewById(R.id.checkBox_q3_avadakedavra);
        checkBox_q3_imperio_ = findViewById(R.id.checkBox_q3_imperio);
        checkBox_q3_crucio_= findViewById(R.id.checkBox_q3_crucio);
        radio_button_q4_mcgonagall_ = findViewById(R.id.radio_button_q4_mcgonagall);
        radio_button_q4_snape_ = findViewById(R.id.radio_button_q4_snape);
    }

    /**
     * This method is called when the submit button is clicked.
     */
    public void submit(View view) {
        String name = editText_userName_.getText().toString();
        if (name.isEmpty())
            name = "Anonymous";
        int num_questions = 4;
        boolean isWellDone = false;
        int num_correct = 0;
        if (checkQuestion1())
            num_correct += 1;
        if (checkQuestion2())
            num_correct += 1;
        if (checkQuestion3())
            num_correct += 1;
        if (checkQuestion4())
            num_correct += 1;
        if (num_correct >= 3)
            isWellDone = true;
        displayMessage(createOutputText(name, num_correct, num_questions,
                isWellDone));
    }

    /**
     * This method is called when checking the first question.
     *
     * @return whether question was anwered correctly
     */
    private boolean checkQuestion1() {
        if (checkBox_q1_elizabeth_.isChecked() || checkBox_q1_henry_.isChecked()) {
            return false;
        }
        return checkBox_q1_james_.isChecked() && checkBox_q1_lily_.isChecked();
    }

    /**
     * This method is called when checking the second question.
     *
     * @return whether question was anwered correctly
     */
    private boolean checkQuestion2() {
        String answer_q2 = editText_q2_.getText().toString();
        return answer_q2.equalsIgnoreCase("muggle");
    }

    /**
     * This method is called when checking the third question.
     *
     * @return whether question was anwered correctly
     */
    private boolean checkQuestion3() {
        if (checkBox_q3_incendio_.isChecked()) {
            return false;
        }
        return (checkBox_q3_crucio_.isChecked() && checkBox_q3_imperio_.isChecked()) &&
                checkBox_q3_avadakedavra_.isChecked();
    }

    /**
     * This method is called when checking the fourth question.
     *
     * @return whether question was anwered correctly
     */
    private boolean checkQuestion4() {
        if (radio_button_q4_snape_.isChecked()) {
            return false;
        }
        return radio_button_q4_mcgonagall_.isChecked();
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
