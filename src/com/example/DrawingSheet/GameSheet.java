package com.example.DrawingSheet;

import android.content.Context;
import com.example.Models.Constants;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameSheet extends SurfaceView implements SurfaceHolder.Callback
{
	private final SurfaceHolder mHolder;
	private final Context mContext;
	private GameThread mThread;
	
	public GameSheet(Context context)
	{
		super(context);
		mContext = context;
		mHolder = this.getHolder();
		mHolder.addCallback(this);
	}

	@Override
	public synchronized void surfaceChanged(SurfaceHolder arg0, int format, int width, int height)
	{		
		Constants.ScreenHeight = height;
		Constants.ScreenWidth = width;
	}

	@Override
	public synchronized void surfaceCreated(SurfaceHolder arg0)
	{
		Constants.ScreenHeight = this.getHeight();
		Constants.ScreenWidth = this.getWidth();
		mThread = new GameThread(arg0 , mContext);
		
		mThread.start();
	}

	@Override
	public synchronized void surfaceDestroyed(SurfaceHolder arg0)
	{
		mThread.setRunning(true);
	}
	
	
	
}
