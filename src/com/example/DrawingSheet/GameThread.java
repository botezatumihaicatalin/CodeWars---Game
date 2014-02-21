package com.example.DrawingSheet;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;

public class GameThread extends Thread
{
	private SurfaceHolder mHolder;
	private boolean mIsRunning = true;
	private int mScreenWidth , mScreenHeight;
	
	public GameThread(SurfaceHolder surfaceHolder , int width , int height)
	{
		mHolder = surfaceHolder;
		setScreenWidth(width);
		setScreenHeight(height);
	}
	
	
	public void run()
	{
		mIsRunning = true;
		while (mIsRunning)
		{
			try
			{
				Canvas canvas = mHolder.lockCanvas();
				
				// Do the drawing here
				
				canvas.drawColor(Color.RED);
				
				mHolder.unlockCanvasAndPost(canvas);
				Thread.sleep(20);

			} catch (Exception e)
			{
				mIsRunning = false;
			}
		}
	}
	
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
