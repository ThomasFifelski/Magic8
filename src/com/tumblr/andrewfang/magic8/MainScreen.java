package com.tumblr.andrewfang.magic8;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainScreen extends Activity implements SensorEventListener{

	private SensorManager sManager;
	long lastUpdate = -1;
	float x, y, z;
	float last_x, last_y, last_z = 0;
	private static final int SHAKE_THRESHOLD = 500;
	
	@Override
	/** Turn on the sensors. */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_screen);
		sManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		sManager.registerListener(this, sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	/** Turn off sensors if the system is on pause. */
	protected void onPause() {
		if (sManager != null) {
			sManager.unregisterListener(this, sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
			sManager = null;
		}
		super.onPause();
	}
	
	public void onAccuracyChanged(Sensor s, int arg1) {
		// Unused
	}
	
	@Override
	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType()==Sensor.TYPE_ACCELEROMETER) {
			long currTime = System.currentTimeMillis(); 
			// only allow one update every 100 ms.
			if ((currTime - lastUpdate) > 80) {
				long diffTime = (currTime - lastUpdate);
				lastUpdate = currTime;
				x = event.values[0];
				y = event.values[1];
				z = event.values[2];
				float speed = Math.abs(x+y+z - last_x - last_y - last_z) / diffTime * 10000;
				if (speed > SHAKE_THRESHOLD) {
					Intent intent = new Intent(this, Shake.class);
					startActivity(intent);
				}
				last_x = x;
				last_y = y;
				last_z = z;
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main_screen, menu);
		return true;
	}
	
	/** Called from the main screen; shakes the ball. */
	public void shake(View view) {
		Intent intent = new Intent(this, Shake.class);
		startActivity(intent);
	}

}
