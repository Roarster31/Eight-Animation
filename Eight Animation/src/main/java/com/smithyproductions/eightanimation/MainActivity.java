package com.smithyproductions.eightanimation;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		final AnimatableEight animation = (AnimatableEight) findViewById(R.id.animation);
		Button playButton = (Button) findViewById(R.id.playButton);
		Button setButton = (Button) findViewById(R.id.setButton);
		Button otherButton = (Button) findViewById(R.id.otherButton);

		playButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				animation.playAnimation();
			}
		});
		setButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				animation.setPlayed();
			}
		});
		otherButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				animation.animateSecondary();
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
