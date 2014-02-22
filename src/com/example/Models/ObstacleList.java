package com.example.Models;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;

public class ObstacleList
{

	private final ArrayList<Obstacle> mList;
	private final Context mContext;
	private final Random mRandom;

	public ObstacleList(Context context)
	{
		mList = new ArrayList<Obstacle>();
		mContext = context;
		mRandom = new Random();
	}

	public synchronized void addRandomObstacle(int obstacleType)
	{
		Obstacle obs = new Obstacle(mContext, obstacleType);
		int Y = -100;
		int X = mRandom.nextInt(Constants.ScreenWidth);
		obs.y = Y;
		obs.x = X;

        for(int i = 0; i < mList.size(); i++){

            if(Math.abs(X - mList.get(i).x) < obs.width && Math.abs(Y - mList.get(i).y) < obs.height){
                return;
            }
        }
        mList.add(obs);
	}

	public synchronized void update()
	{
		for (int i = 0; i < mList.size(); i++)
		{
			mList.get(i).y += Constants.ScreenSpeed;
			if (mList.get(i).y > Constants.ScreenHeight + 100
					* Constants.ScreenSpeed)
			{
				mList.remove(i);
			}
		}

	}
	
	public int getSize()
	{
		return mList.size();
	}
	
	public Obstacle getObstacle(int index)
	{
		return mList.get(index);
	}

	public void render(Canvas canvas)
	{
		for (int i = 0; i < mList.size(); i++)
		{
			mList.get(i).render(canvas);
		}
	}
}
