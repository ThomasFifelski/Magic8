package com.tumblr.andrewfang.magic8;

import java.util.Random;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Shake extends Activity {

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shake);
		// Show the Up button in the action bar.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
		String message = "OH NO!";
		Random rand = new Random();
		switch (Math.abs(rand.nextInt() % 8)) {
			case 0: message = "It is certain.";
					break;
			case 1: message = "Most likely.";
					break;
			case 2: message = "Yes";
					break;
			case 3: message = "Reply hazy, try again.";
					break;
			case 4: message = "Better not tell you now.";
					break;
			case 5: message = "Don't count on it.";
					break;
			case 6: message = "Outlook not so good.";
					break;
			case 7: message = "My sources say no.";
					break;
		}
		LinearLayout ll = new LinearLayout(this);
		TextView textView = new TextView(this);
		textView.setText(message);
		textView.setTextColor(Color.WHITE);
		textView.setTextSize(25);
		ll.addView(textView);
		ll.setBackgroundColor(Color.BLACK);
		ll.setGravity(Gravity.CENTER);
		setContentView(ll);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
