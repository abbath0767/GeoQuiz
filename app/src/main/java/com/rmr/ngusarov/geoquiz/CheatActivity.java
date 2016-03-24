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
	static boolean isCheater;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cheat_layout);

		isCheater = false;

		Intent i = getIntent();
		final boolean answer = i.getBooleanExtra(GeoQuizMainActivity.QUEST_INDEX_TRUE_FALSE_PARAMETR, false);

		mCheatTextView = (TextView) findViewById(R.id.cheat_text_view);

		mCheatButton = (Button) findViewById(R.id.cheat_button);
		mCheatButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d(GeoQuizMainActivity.TAG, "is cheater! = TRUE");
				isCheater();
				mCheatTextView.setText(String.valueOf(answer));
			}
		});

		makeIntent();

	}

	public void isCheater() {
		isCheater = true;
	}



	public void makeIntent() {
		Intent i = new Intent();
		Log.d(GeoQuizMainActivity.TAG, "set cheating = " + isCheater);
		i.putExtra(GeoQuizMainActivity.QUEST_INDEX_TRUE_FALSE_PARAMETR, isCheater);
		setResult(RESULT_OK, i);
	}
}
