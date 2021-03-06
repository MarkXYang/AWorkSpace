package com.example.first;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText text;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		text = (EditText) findViewById(R.id.main_input);
		RadioGroup group1 = (RadioGroup) findViewById(R.id.orientation);
		
		group1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.horizontal:
					group.setOrientation(LinearLayout.HORIZONTAL);
					break;
				case R.id.vertical:
					group.setOrientation(LinearLayout.VERTICAL);
					break;
					
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onClick(View view) {
		Toast.makeText(this, text.getText().toString(), Toast.LENGTH_LONG).show();
	}
	


}
