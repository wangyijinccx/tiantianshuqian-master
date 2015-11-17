package com.izqisoft.view;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.izqisoft.tiantianshuqian.R;

public class GameView extends RelativeLayout implements OnClickListener {

	private TimeInterface jiekou;
	private TextView tv_addtime;
	private boolean play_music = true;
	private float userMony = 0.0F;

	public void setInterface(TimeInterface _jiekou) {
		this.jiekou = _jiekou;
	}

	public boolean setMusicState() {
		play_music = !play_music;
		if (play_music == false) {

			if (mp != null && mp.isPlaying()) {
				mp.pause();
			}
		} else {
			if (mp != null) {
				mp.reset();
				mp.release();
			}
			mp = MediaPlayer.create(mContext, R.raw.beijing);
			mp.setLooping(true);
			try {
				mp.prepare();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mp.start();
		}

		return play_music;
	}

	private Techniques[] anim = new Techniques[] { Techniques.ZoomOutRight,
			Techniques.ZoomOutLeft, Techniques.ZoomOutUp,
			Techniques.ZoomOutDown, Techniques.SlideOutUp, Techniques.FadeOut,
			Techniques.FadeOutRight, Techniques.RollOut,
			Techniques.RotateOutDownLeft, Techniques.TakingOff };
	private ImageView img_1, img_2, img_3, img_4;
	private Context mContext;
	private LayoutInflater inflater;
	private Matrix matrix = new Matrix();
	private MediaPlayer mp;
	private MediaPlayer mp_great;
	private MediaPlayer mp_error;
	private float[] price = new float[] { 1.0F, 10.0F, 100.0F, 20.0F, 5.0F,
			50.0F, 0.5F, 1.0F, 0.5F, 0.1F, 0.1F, 10.0F, 0.2F, 10.0F };
	private int[] picID = new int[] { R.drawable.kagaz_1, R.drawable.kagaz_10,
			R.drawable.kagaz_100, R.drawable.kagaz_20, R.drawable.kagaz_5,
			R.drawable.kagaz_50, R.drawable.kagaz_5mo, R.drawable.kagaz_kona_1,
			R.drawable.kagaz_5motatur, R.drawable.kagaz_1mo,
			R.drawable.kagaz_1motatur, R.drawable.kagaz_kona10,
			R.drawable.kagaz_2mo, R.drawable.kagaz_10tatur };
	private float allPrice = 0.0f;
	private int TIME = 45 * 1000;
	Timer mTimer;
	MyTimerTask mTimerTask;
	private TextView tv1, tv2, tv3, tv4;
	private ImageView img_good;

	@SuppressLint("NewApi")
	public GameView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.mContext = context;
		initView();
	}

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.mContext = context;
		initView();
	}

	public GameView(Context context) {
		super(context);
		this.mContext = context;
		initView();

	}

	class MyTimerTask extends TimerTask {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			mhandler.sendEmptyMessage(-1);

		}

	}

	Handler mhandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {

			try {
				if (msg.what == 1) {
					jiekou.gameOver(userMony);

				} else {
					TIME = TIME - 246;
					if (TIME <= 0) {
						jiekou.updateTime(0);
						mhandler.removeMessages(-1);
						mhandler.sendEmptyMessage(1);
						return;
					}
					jiekou.updateTime(TIME);
					mTimer = new Timer();
					// game over...
					if (mTimer != null) {
						if (mTimerTask != null) {
							mTimerTask.cancel(); // 将原任务从队列中移除
						}

						mTimerTask = new MyTimerTask(); // 新建一个任务
						mTimer.schedule(mTimerTask, 246);
					}
				}
			} catch (Exception ex) {

			}

		}

	};

	private void initView() {
		userMony = 0.0F;
		LayoutInflater.from(mContext).inflate(R.layout.gameview, this, true);
		mp = MediaPlayer.create(mContext, R.raw.beijing);
		mp_great = MediaPlayer.create(mContext, R.raw.great);
		mp_error = MediaPlayer.create(mContext, R.raw.shibai);
		mp.setLooping(true);
		try {
			mp.prepare();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			mp_great.prepare();
		} catch (Exception ex) {

		}
		try {
			mp_error.prepare();
		} catch (Exception ex) {

		}

		 mp.start();
		img_1 = (ImageView) findViewById(R.id.img_1);
		img_2 = (ImageView) findViewById(R.id.img_2);
		img_3 = (ImageView) findViewById(R.id.img_3);
		img_4 = (ImageView) findViewById(R.id.img_4);
		tv1 = (TextView) findViewById(R.id.tv1);
		tv2 = (TextView) findViewById(R.id.tv2);
		tv3 = (TextView) findViewById(R.id.tv3);
		tv4 = (TextView) findViewById(R.id.tv4);
		tv1.setOnClickListener(this);
		tv2.setOnClickListener(this);
		tv3.setOnClickListener(this);
		tv4.setOnClickListener(this);

		img_good = (ImageView) findViewById(R.id.img_good);
		tv_addtime = (TextView) findViewById(R.id.txt_addtime);
		img_good.setVisibility(View.INVISIBLE);
		tv_addtime.setVisibility(View.INVISIBLE);
		CreateNewGameView(false);
	}

	public void CreateNewGameView(boolean isOk) {
		if (play_music && isOk) {
			mp_great.start();
		} else if (play_music && !isOk) {
			mp_error.start();
		}

		Random r = new Random();
		Random r2 = new Random();
		int MaxValue = 10;
		int index = 0;
		allPrice = 0.0f;
		index = r.nextInt(picID.length);
		Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(
				picID[index])).getBitmap();
		allPrice += price[index];
		// 设置旋转角度
		int r2_value = r2.nextInt(MaxValue);
		if (r2_value % 2 == 0) {
			r2_value = r2_value * (-1);
		}
		matrix.setRotate(r2_value);
		// 重新绘制Bitmap

		bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
				bitmap.getHeight(), matrix, true);
		img_1.setImageBitmap(bitmap);
		index = r.nextInt(picID.length);
		bitmap = ((BitmapDrawable) getResources().getDrawable(picID[index]))
				.getBitmap();
		allPrice += price[index];

		// 设置旋转角度
		r2_value = r2.nextInt(MaxValue);
		if (r2_value % 2 == 0) {
			r2_value = r2_value * (-1);
		}
		matrix.setRotate(r2_value);
		// 重新绘制Bitmap
		bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
				bitmap.getHeight(), matrix, true);
		img_2.setImageBitmap(bitmap);
		index = r.nextInt(picID.length);
		bitmap = ((BitmapDrawable) getResources().getDrawable(picID[index]))
				.getBitmap();
		allPrice += price[index];
		// 设置旋转角度
		r2_value = r2.nextInt(MaxValue);
		if (r2_value % 2 == 0) {
			r2_value = r2_value * (-1);
		}
		matrix.setRotate(r2_value);
		// 重新绘制Bitmap
		bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
				bitmap.getHeight(), matrix, true);
		img_3.setImageBitmap(bitmap);
		index = r.nextInt(picID.length);
		bitmap = ((BitmapDrawable) getResources().getDrawable(picID[index]))
				.getBitmap();
		allPrice += price[index];
		// 设置旋转角度
		r2_value = r2.nextInt(MaxValue);
		if (r2_value % 2 == 0) {
			r2_value = r2_value * (-1);
		}
		matrix.setRotate(r2_value);
		// 重新绘制Bitmap
		bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
				bitmap.getHeight(), matrix, true);
		img_4.setImageBitmap(bitmap);
		mhandler.sendEmptyMessageDelayed(-1, 500);
		System.out.println("All Price is:" + allPrice);
		ArrayList<String> result = getPrice(allPrice);
		// allPrice=(float)(Math.round(allPrice*100)/100);
		int tv_r_index = r2.nextInt(4);
		if (tv_r_index == 0) {
			tv1.setText(result.get(0) + "元");
			tv2.setText(result.get(1) + "元");
			tv3.setText(result.get(2) + "元");
			tv4.setText(result.get(3) + "元");
		} else if (tv_r_index == 1) {
			tv1.setText(result.get(1) + "元");
			tv2.setText(result.get(0) + "元");
			tv3.setText(result.get(2) + "元");
			tv4.setText(result.get(3) + "元");
		} else if (tv_r_index == 2) {
			tv1.setText(result.get(2) + "元");
			tv2.setText(result.get(1) + "元");
			tv3.setText(result.get(0) + "元");
			tv4.setText(result.get(3) + "元");
		} else if (tv_r_index == 3) {
			tv1.setText(result.get(3) + "元");
			tv2.setText(result.get(1) + "元");
			tv3.setText(result.get(2) + "元");
			tv4.setText(result.get(0) + "元");
		}

		// Random r2=new Random();

		if (isOk) {
			img_good.setVisibility(View.VISIBLE);
			tv_addtime.setVisibility(View.VISIBLE);
			index = r2.nextInt(anim.length);
			YoYo.with(anim[index]).duration(800).playOn(img_good);
			tv_addtime.setText("+1750");
			YoYo.with(Techniques.SlideOutUp).duration(1000).playOn(tv_addtime);
		} else {
			if (tv_addtime.getVisibility() != View.INVISIBLE) {
				tv_addtime.setText("-750");
				YoYo.with(Techniques.SlideOutUp).duration(1000)
						.playOn(tv_addtime);
			}
		}
	}

	private ArrayList<String> getPrice(float price) {

		float price4 = price - 0.1F;
		ArrayList<String> result = new ArrayList<String>();
		DecimalFormat decimalFormat = new DecimalFormat(".00");// 构造方法的字符格式这里如果小数不足2位,会以0补足.
		String p = decimalFormat.format(price);// format 返回的是字符串
		System.out.println(p);
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < p.length(); i++) {
			if (p.charAt(i) != '.' && p.charAt(i) != '0') {
				buf.append(p.charAt(i));
			}
		}
		result.add(p);
		String newPrice = buf.toString();
		float newfloat = Float.parseFloat(newPrice);
		if (newfloat > 400.00F) {
			newfloat = newfloat / 100.0F;
			newPrice = decimalFormat.format(newfloat);
		}
		result.add(newPrice);
		newPrice = "";
		if (buf.length() == 3) {
			try {
				newPrice = buf.substring(1, 1) + buf.substring(2)
						+ buf.substring(0, 1) + "." + buf.substring(0, 1) + "0";
			} catch (Exception ex) {
				System.out.println(ex.getLocalizedMessage());
			}
		} else if (buf.length() == 2) {
			newPrice = buf.substring(1) + (new Random()).nextInt(9)
					+ buf.substring(0, 1) + "." + buf.substring(1) + "0";
		} else if (buf.length() == 4) {
			newPrice = buf.substring(3) + buf.toString().substring(2, 3)
					+ buf.substring(0, 1) + "." + buf.substring(1, 1) + "0";
		} else {
			newPrice = buf.substring(0, 1) + (new Random()).nextInt(9) + "."
					+ (new Random()).nextInt(9) + "0";
		}
		newfloat = Float.parseFloat(newPrice);
		if (newfloat > 400.00F) {
			newfloat = newfloat / 100.0F;
			newPrice = decimalFormat.format(newfloat);
		}
		result.add(newPrice);
		String p4 = decimalFormat.format(price4);
		result.add(p4);
		return result;
	}

	public void DestreyView() {
		try {
		
			mhandler.removeMessages(-1);
			if(mTimerTask!=null)
			{
				mTimerTask.cancel();
			}
			if (mp != null) {
				mp.reset();
				mp.release();
			}
			if (mp_great != null) {
				mp_great.release();
				mp_great = null;
			}
		} catch (Exception ex) {

		}
	}

	@Override
	public void onClick(View arg0) {

		DecimalFormat decimalFormat = new DecimalFormat(".00");// 构造方法的字符格式这里如果小数不足2位,会以0补足.
		String p = decimalFormat.format(allPrice);
		String selectText = "";
		int id = arg0.getId();
		if (id > 0) {
			switch (id) {
			case R.id.tv1:
				selectText = tv1.getText().toString().trim().replace("元", "");
				break;
			case R.id.tv2:
				selectText = tv2.getText().toString().trim().replace("元", "");
				break;
			case R.id.tv3:
				selectText = tv3.getText().toString().trim().replace("元", "");
				break;
			case R.id.tv4:
				selectText = tv4.getText().toString().trim().replace("元", "");
				break;
			default:
				break;
			}
			float newfloat = Float.parseFloat(selectText);
			float current = Float.parseFloat(p);
			if (newfloat == current) {
				CreateNewGameView(true);
				TIME += 1750;
				userMony += current;
				jiekou.updateMony(userMony);
			} else {
				CreateNewGameView(false);
				TIME -= 750;
			}
		}

	}

}
