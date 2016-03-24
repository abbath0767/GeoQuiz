package com.rmr.ngusarov.geoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GeoQuizMainActivity extends AppCompatActivity {

    public static final String TAG = "myTag";
    public static final String KEY_INDEX = "key_index";
	public static final String QUEST_INDEX_TRUE_FALSE_PARAMETR = "com.rmr.ngusarov.geoquiz.cheat";

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mBackButton;
    private Button mCheatButton;
    private TextView mTextView;
    private static int counter = 0;
    private static TrueFalse[] questArr;
	private static boolean isCheater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, ">> on create <<");
        setContentView(R.layout.activity_geo_quiz_main);

		isCheater = false;

        if (savedInstanceState != null)
            counter = savedInstanceState.getInt(KEY_INDEX);

        questArr = new TrueFalse[]{new TrueFalse(R.string.russia_question1, true),
                new TrueFalse(R.string.russia_question2, false),
                new TrueFalse(R.string.russia_question3, true),
                new TrueFalse(R.string.russia_question4, true),
                new TrueFalse(R.string.russia_question5, false),
                new TrueFalse(R.string.russia_question6, true)};

        Log.d(TAG, "quastArr length = ?" + questArr.length + ", counter = " + counter);
        Log.d(TAG, "getQuestId " + questArr[0].getQuestionId());

        mTextView = (TextView) findViewById(R.id.text_view_question);
        mTextView.setText(questArr[counter].getQuestionId());

        mCheatButton = (Button) findViewById(R.id.cheat);
		mCheatButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(GeoQuizMainActivity.this, CheatActivity.class);
				i.putExtra(QUEST_INDEX_TRUE_FALSE_PARAMETR, questArr[counter].isQuestionResult());
				startActivityForResult(i, 0);
			}
		});

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                counter%=questArr.length;
				isCheater = false;
                refreshQuest();
            }
        });

        mBackButton = (Button) findViewById(R.id.back_button);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View v) {
                counter--;
                counter = counter < 0 ? questArr.length - 1: counter;
				isCheater = false;
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
		if (isCheater) {
			Toast.makeText(this, R.string.cheting_is_wrong, Toast.LENGTH_SHORT).show();
			return;
		}
        if (questArr[counter].isQuestionResult() && v.getId() == R.id.true_button)
            answerId = R.string.correct_toast;
        else if (!questArr[counter].isQuestionResult() && v.getId() == R.id.false_button)
            answerId = R.string.correct_toast;
        else
            answerId = R.string.incorrect_toast;

        Toast.makeText(this, answerId, Toast.LENGTH_SHORT).show();
    }

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (data == null) {
			isCheater = false;
			return;
		}
		isCheater = data.getBooleanExtra(QUEST_INDEX_TRUE_FALSE_PARAMETR, false);
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
