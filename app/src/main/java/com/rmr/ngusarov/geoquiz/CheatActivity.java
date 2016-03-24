package com.rmr.ngusarov.geoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

	private Button mCheatButton;
	private TextView mCheatTextView;
	boolean mAnswer = false;
	public static final String ANSWER_IS_SHOWN = "com.rmr.ngusarov.geoquiz.show_answer";

	public void setAnswerShownResult(boolean isAnswerShown) {
		Intent data = new Intent();
		data.putExtra(ANSWER_IS_SHOWN, isAnswerShown);
		setResult(RESULT_OK, data);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cheat_layout);

		mAnswer = getIntent().getBooleanExtra(GeoQuizMainActivity.QUEST_INDEX_TRUE_FALSE_PARAMETR, false);

		mCheatTextView = (TextView) findViewById(R.id.cheat_text_view);

		mCheatButton = (Button) findViewById(R.id.cheat_button);
		mCheatButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d(GeoQuizMainActivity.TAG, ">> on click << is cheater = TRUE");
				mCheatTextView.setText(String.valueOf(mAnswer));
				mAnswer = true;
				setAnswerShownResult(true);
			}
		});

	}
}
