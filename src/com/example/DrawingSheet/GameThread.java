package com.example.DrawingSheet;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.SurfaceHolder;

public class GameThread extends Thread
{
	private final SurfaceHolder mHolder;
	private boolean mIsRunning = true;
	private int mScreenWidth , mScreenHeight;
	private PointF mAuxPoint;
	private float sx , sy;
	private final Context mContext;
	private final SensorManager mSensorManager;
	
	public GameThread(SurfaceHolder surfaceHolder , Context context , int width , int height)
	{
		mHolder = surfaceHolder;
		setScreenWidth(width);
		setScreenHeight(height);
		mAuxPoint = new PointF();
		mAuxPoint.x = width / 2;
		mAuxPoint.y = height / 2;
		mContext = context;
		mSensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
		mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	
	public void run()
	{ 
		mIsRunning = true;
		while (mIsRunning)
		{
			try
			{
				
				Canvas canvas = mHolder.lockCanvas();
				
				mAuxPoint.x += sx;
				mAuxPoint.y += sy;
				// Do the drawing here
				canvas.drawColor(Color.RED);
				canvas.drawCircle(mAuxPoint.x, mAuxPoint.y, 10, new Paint(Color.BLUE));
				
				mHolder.unlockCanvasAndPost(canvas);
				Thread.sleep(20);

			} catch (Exception e)
			{
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
			sx = (-1) * event.values[0]  * 2;
			sy = event.values[1] * 2;		
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
