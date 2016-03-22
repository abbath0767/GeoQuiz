package com.rmr.ngusarov.geoquiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GeoQuizMainActivity extends AppCompatActivity {

    public static final String TAG = "my debug tag";
    public static final String KEY_INDEX = "key_index";

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mBackButton;
    private TextView mTextView;
    private static int counter;
    private static TrueFalse[] questArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, ">> on create <<");
        setContentView(R.layout.activity_geo_quiz_main);

        if (savedInstanceState == null)
            counter = 0;
        else
            counter = savedInstanceState.getInt(KEY_INDEX);

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

        mBackButton = (Button) findViewById(R.id.back_button);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View v) {
                counter--;
                counter = counter < 0 ? questArr.length : counter;
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
        int answerId;
        if (questArr[counter].isQuestionResult() && v.getId() == R.id.true_button)
            answerId = R.string.correct_toast;
        else if (!questArr[counter].isQuestionResult() && v.getId() == R.id.false_button)
            answerId = R.string.correct_toast;
        else
            answerId = R.string.incorrect_toast;

        Toast.makeText(this, answerId, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, ">> save instance state coming, save int = " + counter);
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_INDEX, counter);
    }

    @Override
    protected void onStop() {
        Log.d(TAG, ">> on stop <<");
        super.onStop();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, ">> on pause <<");
        super.onPause();
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, ">> on restart <<");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, ">> on destroy <<");
        super.onDestroy();
    }

    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        return true;
//    }
}
