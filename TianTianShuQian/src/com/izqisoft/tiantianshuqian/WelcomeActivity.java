package com.izqisoft.tiantianshuqian;

import java.util.Random;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class WelcomeActivity extends Activity{

	ImageView myView;
	TextView tv_text;
	ObjectAnimator fadein;
	ObjectAnimator mover;
	private String []str={"秋风送爽心舒畅，财源滚滚到身旁","送你招财猫，天天进钞票","送你摇钱树，好运挡不住","送你宝葫芦，生活多福禄","518，我要发，祝君发财"};
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.welcomeactivity);
		myView = (ImageView) findViewById(R.id.welcomeimage);
		tv_text = (TextView) findViewById(R.id.text);
		ObjectAnimator fadeOut = ObjectAnimator.ofFloat(myView, "alpha", .2f,
				1f);
		int index=(new Random()).nextInt(str.length);
		tv_text.setText(str[index]);
		tv_text.setVisibility(View.INVISIBLE);
		fadein = ObjectAnimator.ofFloat(tv_text, "alpha", 0.0f, 1.0f);
		fadeOut.setDuration(2500);
		fadein.setDuration(1500);
		final AnimatorSet mAnimationSet = new AnimatorSet();

		final AnimatorSet mAnimationSet2 = new AnimatorSet();
		mAnimationSet.play(fadeOut);
		mover = ObjectAnimator.ofFloat(myView, "translationY", -150f);
		mover.setDuration(1500);
		mAnimationSet2.play(mover);
		mAnimationSet.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				super.onAnimationEnd(animation);
				// mAnimationSet.start();

				// myView.animate().translationY(-150).setDuration(1000).start();
				mAnimationSet2.start();
				fadein.start();
				tv_text.setVisibility(View.VISIBLE);
			}
		});

		mAnimationSet2.addListener(new AnimatorListenerAdapter() {

			@Override
			public void onAnimationEnd(Animator arg0) {

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Intent intent = new Intent();
				intent.setClass(WelcomeActivity.this, MenuActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.activity_open_translate,
						R.anim.activity_close_scale);
				finish();
			}

		});
		mAnimationSet.start();
	}



	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}
}
