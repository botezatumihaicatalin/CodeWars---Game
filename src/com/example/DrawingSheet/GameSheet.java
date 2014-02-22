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
		if (mThread != null)
		{
			Constants.mScreenHeight = height;
			Constants.mScreenWidth = width;
			mThread.setScreenHeight(height);
			mThread.setScreenWidth(width);
		}
	}

	@Override
	public synchronized void surfaceCreated(SurfaceHolder arg0)
	{
		mThread = new GameThread(arg0 , mContext , this.getWidth() , this.getHeight());
		mThread.start();
	}

	@Override
	public synchronized void surfaceDestroyed(SurfaceHolder arg0)
	{
		mThread.setRunning(true);
	}
	
	
	
}
