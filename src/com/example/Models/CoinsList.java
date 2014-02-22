package com.example.Models;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;

public class CoinsList
{
	private final ArrayList<Coin> mList;
	private final Random mRandom;
	private final Context mContext;

	public CoinsList(Context context)
	{
		mList = new ArrayList<Coin>();
		mRandom = new Random();
		mContext = context;
	}

	public void update()
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

	public void addNewRandomCoin(float x, float y)
	{
		Coin coin = new Coin(mContext , mRandom.nextInt(4) + 1);
		coin.x = x;
		coin.y = y;
		mList.add(coin);
	}

	public int getSize()
	{
		return mList.size();
	}

	public Coin getCoin(int index)
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
	
	public void removeCoin(int index)
	{
		mList.remove(index);
	}
}
