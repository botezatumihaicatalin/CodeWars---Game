package com.example.Models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import com.example.game.R;

public class Ball
{
	public float my_radius;
	public Bitmap mBallImage;
	public float speedX, speedY;
	public PointF my_position;
	private Paint mDrawingPaint;

	public Ball(Context context)
	{

		my_radius = 20.0f;

		// bmp image
		mBallImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball);
		mBallImage = Bitmap.createScaledBitmap(mBallImage, 20, 20, true);
		mDrawingPaint = new Paint();
		// getting display size
		my_position = new PointF(0.0f, 0.0f);
		speedX = 0.0f;
		speedY = 0.0f;
	}

	public void updatePosition()
	{
		my_position.x += speedX;
		my_position.y += speedY;
	}

	public void render(Canvas canvas)
	{
		canvas.drawBitmap(mBallImage, my_position.x - 10, my_position.y - 10 , mDrawingPaint);		
	}


}
