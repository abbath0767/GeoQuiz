package com.rmr.ngusarov.geoquiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GeoQuizMainActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mTextView;
    private static int counter;
    private static TrueFalse[] questArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo_quiz_main);

        counter = 0;

        questArr = new TrueFalse[]{new TrueFalse(R.string.russia_question1, true),
                new TrueFalse(R.string.russia_question2, false),
                new TrueFalse(R.string.russia_question3, true),
                new TrueFalse(R.string.russia_question4, true),
                new TrueFalse(R.string.russia_question5, false),
                new TrueFalse(R.string.russia_question6, true)};

        mTextView = (TextView) findViewById(R.id.text_view_question);
        mTextView.setText(questArr[counter].getQuestionId());

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                counter%=questArr.length;
                refreshQuest();
            }
        });

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAnswer(mTrueButton);
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAnswer(mFalseButton);
            }
        });
    }

    private void refreshQuest() {
        mTextView.setText(questArr[counter].getQuestionId());
    }

    private void validateAnswer(View v) {
        if (questArr[counter].isQuestionResult() && v.getId() == R.id.true_button)
            Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
        else if (!questArr[counter].isQuestionResult() && v.getId() == R.id.false_button)
            Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        return true;
//    }
}
