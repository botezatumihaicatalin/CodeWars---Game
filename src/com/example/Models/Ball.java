package com.example.Models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.Log;

import com.example.game.R;

public class Ball
{
	public float my_radius;
	public Bitmap mBallImage;
	public float speedX, speedY;
	public PointF my_position;
	private Paint mDrawingPaint;
	public int rotation = 0;
	
	
	public Ball(Context context)
	{

		my_radius = 60.0f;

		// bmp image
		mBallImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.man);
		mBallImage = Bitmap.createScaledBitmap(mBallImage, 30, 60 , true);
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
		
		Matrix matrix = new Matrix();
		if (speedY != 0.0)
		{
			double angle = Math.atan((double)(speedX / speedY)) * 180 / Math.PI;
			if (angle > 60)
				angle = 60;			
			if (angle < -60)
				angle = -60;
			matrix.postRotate(-1 * (float)angle);	
		}
		Bitmap newBitmap = Bitmap.createBitmap(mBallImage, 0, 0, 30, 60, matrix, true);
		canvas.drawBitmap(newBitmap, my_position.x - 15, my_position.y - 30, mDrawingPaint);		
	}


}
