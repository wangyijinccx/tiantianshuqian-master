package com.izqisoft.tiantianshuqian;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menuactivity);
	}

	public void kaishishuqian(View view) {
		Intent intent = new Intent();
		intent.setClass(this, MainActivity.class);
		startActivity(intent);
	}

	public void chakanpaihangbang(View view) {
		Intent intent = new Intent();
		intent.setClass(this, RankListActivity.class);
		startActivity(intent);
	}

	public void gengduoyouxi(View view) {
		Intent intent = new Intent();
		intent.setClass(this, MainActivity.class);
		startActivity(intent);
	}

}
