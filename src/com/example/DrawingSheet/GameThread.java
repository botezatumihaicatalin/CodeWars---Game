package com.example.DrawingSheet;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Vibrator;
import android.util.Log;
import android.view.SurfaceHolder;

import com.example.Models.Ball;
import com.example.Models.CoinsList;
import com.example.Models.Colisions;
import com.example.Models.Constants;
import com.example.Models.Obstacle;
import com.example.Models.ObstacleList;
import com.example.Models.Road;

public class GameThread extends Thread
{
	private final SurfaceHolder mHolder;
	private boolean mIsRunning = true;
	private int mLife;
	private Ball mBall;
	private Road mRoad;
	private ObstacleList mOList;
	private CoinsList mCList;
	private final Context mContext;
	private final SensorManager mSensorManager;
	private final Vibrator mVibrator;

	public GameThread(SurfaceHolder surfaceHolder, Context context)
	{
		mContext = context;
		mHolder = surfaceHolder;

		mLife = 1000;

		mCList = new CoinsList(mContext);

		mOList = new ObstacleList(mContext);

		mRoad = new Road();
		mRoad.init();

		mBall = new Ball(context);
		mBall.my_position.x = Constants.ScreenWidth / 2;
		mBall.my_position.y = Constants.ScreenHeight / 2;
		mSensorManager = (SensorManager) mContext
				.getSystemService(Context.SENSOR_SERVICE);
		mSensorManager.registerListener(mSensorListener,
				mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);
		mVibrator = (Vibrator) mContext
				.getSystemService(Context.VIBRATOR_SERVICE);

	}

	public void run()
	{
		mIsRunning = true;
		Canvas canvas;
		int framesPassed = 0;
		Paint textPaint = new Paint();
		textPaint.setTextSize(30);
		Constants.ScreenSpeed = 8;
		while (mIsRunning)
		{
			try
			{
				if (mLife <= 0)
				{
					mIsRunning = false;
				}
				/* Game logic here */
				framesPassed++;
				framesPassed %= 1001;
				 if (framesPassed == 1000)
				 Constants.ScreenSpeed += 1;

				if (framesPassed % 5 == 0)
					mOList.addRandomObstacle(Obstacle.BRAD);

				if (framesPassed % 500 == 0)
				{
					mCList.addNewRandomCoin((mRoad.segments.get(0).right.x + mRoad.segments.get(0).left.x) / 2, -100);
				}

				mOList.update();
				mCList.update();
				mBall.updatePosition();
				mRoad.updateSegments();
				mBall.my_position.y += Constants.ScreenSpeed;

				/*----*/
				if (!Colisions.colision(mBall, mRoad))
				{
					mVibrator.vibrate(1);
					mLife--;
				}

				int collisionObj = Colisions.colision(mBall, mOList);
				int collisionCoinIndex = Colisions.colision(mBall, mCList);
				if (collisionObj != -1)
				{
					mVibrator.vibrate(1);
					mLife -= 2;
				}

				if (collisionCoinIndex != -1)
				{
					mLife += mCList.getCoin(collisionCoinIndex).value;
					mCList.removeCoin(collisionCoinIndex);
				}

				canvas = mHolder.lockCanvas();

				// Do the drawing here

				canvas.drawARGB(255, 226, 225, 227);
				mRoad.render(canvas);
				mOList.render(canvas);
				mBall.render(canvas);
				mCList.render(canvas);
				
				if (mLife > 200)
				{
					textPaint.setColor(Color.BLACK);
					textPaint.setTextSize(30);
					canvas.drawText("Life " + mLife, Constants.ScreenWidth - 150,
							Constants.ScreenHeight - 31, textPaint);
				}
				else
				{
					textPaint.setColor(Color.RED);
					textPaint.setTextSize(33);
					canvas.drawText("Life " + mLife, Constants.ScreenWidth - 150,
							Constants.ScreenHeight - 35, textPaint);
				}
				mHolder.unlockCanvasAndPost(canvas);
				Thread.sleep(5);

			} catch (Exception e)
			{
				Log.d("Game Testing", "Error! ");
				e.printStackTrace();
				mIsRunning = false;
			}
		}
		mSensorManager.unregisterListener(mSensorListener);
	}

	final SensorEventListener mSensorListener = new SensorEventListener()
	{

		@Override
		public void onAccuracyChanged(Sensor arg0, int arg1)
		{

		}

		@Override
		public void onSensorChanged(SensorEvent event)
		{
			mBall.speedX = (-1) * event.values[0]
					* (Constants.ScreenSpeed / 5 + 1);
			mBall.speedY = event.values[1] * (Constants.ScreenSpeed / 5 + 1);
			if (mBall.speedY > 0)
			{
				mBall.speedY = 0;
			}
		}

	};

	public synchronized void setRunning(boolean value)
	{
		mIsRunning = value;
	}

	public synchronized boolean getRunning()
	{
		return mIsRunning;
	}
}
