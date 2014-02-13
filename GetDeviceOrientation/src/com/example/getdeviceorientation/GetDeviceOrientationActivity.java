package com.example.getdeviceorientation;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class GetDeviceOrientationActivity extends Activity implements SensorEventListener{
	private SensorManager mySensorManager;
	private Sensor myGravitySensor;
	
	TextView textFace, textZValue, textStandardGravity, textThreshold;
	
	float standardGravity;
	float thresholdGraqvity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_device_orientation);
		textFace = (TextView)findViewById(R.id.face);
		textZValue = (TextView)findViewById(R.id.zvalue);
		textStandardGravity = (TextView)findViewById(R.id.standardgravity);
		textThreshold = (TextView)findViewById(R.id.threshold);

		standardGravity = SensorManager.STANDARD_GRAVITY;
		thresholdGraqvity = standardGravity/2;

		textFace.setText("");
		textZValue.setText("");
		textStandardGravity.setText("Standard Gravity = " + standardGravity);
		textThreshold.setText("Threshold = " + thresholdGraqvity);

		mySensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
		myGravitySensor = mySensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.get_device_orientation, menu);
		return true;
	}

	public void onAccuracyChanged(Sensor arg0, int arg1) {
	}

	public void onSensorChanged(SensorEvent event) {
		Sensor source = event.sensor;
		float z = event.values[2];

		if(source.getType() == Sensor.TYPE_GRAVITY) {
			textZValue.setText("Z value = " + z);
			
			if (z >= thresholdGraqvity) {
				textFace.setText("Face UP");
			} else if(z <= -thresholdGraqvity) {
				textFace.setText("Face Down");
			} else {
				textFace.setText("");
			}
		}
	}

	protected void onPause() {
		super.onPause();
		mySensorManager.unregisterListener(this);
	}

	protected void onResume() {
		super.onResume();
		mySensorManager.registerListener(this, myGravitySensor,
				SensorManager.SENSOR_DELAY_NORMAL);
	}

}
