package com.izqisoft.tiantianshuqian;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends Activity{

	
	TextView tv_result;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		tv_result=(TextView)findViewById(R.id.textView1);
		tv_result.setText(getIntent().getStringExtra("mony"));
		
	}
}
