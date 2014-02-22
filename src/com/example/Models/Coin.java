package com.example.Models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.game.R;

public class Coin
{
	public int width , height , type;
	public float x, y;
	public int value;
	private Bitmap mBitmapImage;

	public Coin(Context context, int type)
	{
		this.type = type;
		if (type == 1)
		{
			mBitmapImage = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.page_shop_price_1);
			width = mBitmapImage.getWidth();
			height = mBitmapImage.getHeight();
		} else if (type == 2)
		{
			mBitmapImage = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.page_shop_price_2);
			width = mBitmapImage.getWidth();
			height = mBitmapImage.getHeight();
		} else if (type == 3)
		{
			mBitmapImage = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.page_shop_price_3);
			width = mBitmapImage.getWidth();
			height = mBitmapImage.getHeight();
		} else if (type == 4)
		{
			mBitmapImage = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.page_shop_price_4);
			width = mBitmapImage.getWidth();
			height = mBitmapImage.getHeight();
		}
		value = type * 10;
	}

	public void render(Canvas canvas)
	{
		canvas.drawBitmap(mBitmapImage, x - width / 2, y - height / 2 , null);
	}
}
