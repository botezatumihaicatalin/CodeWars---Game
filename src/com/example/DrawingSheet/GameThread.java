package com.example.DrawingSheet;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.SurfaceHolder;

import com.example.Models.Ball;
import com.example.Models.Colisions;
import com.example.Models.Road;

public class GameThread extends Thread
{
	private final SurfaceHolder mHolder;
	private boolean mIsRunning = true;
	private int mScreenWidth, mScreenHeight;
	private Ball mBall;
	private Road mRoad;
	private final Context mContext;
	private final SensorManager mSensorManager;

	public GameThread(SurfaceHolder surfaceHolder, Context context, int width,
			int height)
	{
		mHolder = surfaceHolder;
		setScreenWidth(width);
		setScreenHeight(height);
		mRoad = new Road();
		mRoad.init();

		mBall = new Ball();
		mBall.my_position.x = width / 2;
		mBall.my_position.y = height / 2;
		mContext = context;
		mSensorManager = (SensorManager) mContext
				.getSystemService(Context.SENSOR_SERVICE);
		mSensorManager.registerListener(mSensorListener,
				mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);

	}

	public void run()
	{
		mIsRunning = true;
		while (mIsRunning)
		{
			try
			{

				mBall.updatePosition();
				mRoad.updateSegments();
				Log.d("Game Testing", Colisions.colision(mBall, mRoad) + "");

				Canvas canvas = mHolder.lockCanvas();

				// Do the drawing here

				canvas.drawColor(Color.RED);
				mBall.render(canvas);

				mRoad.render(canvas);

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
			mBall.speedX = (-1) * event.values[0] * 2;
			mBall.speedY = event.values[1] * 2;
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

	public synchronized int getScreenWidth()
	{
		return mScreenWidth;
	}

	public synchronized void setScreenWidth(int mScreenWidth)
	{
		this.mScreenWidth = mScreenWidth;
	}

	public synchronized int getScreenHeight()
	{
		return mScreenHeight;
	}

	public synchronized void setScreenHeight(int mScreenHeight)
	{
		this.mScreenHeight = mScreenHeight;
	}
}
