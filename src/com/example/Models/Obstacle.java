package com.example.Models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import com.example.game.R;

/**
 * Created by arthur on 2/22/14.
 */
public class Obstacle
{
	public static final int BRAD = 1;
	public static final int SNOWMAN = 2;
	public static final int BUNNY = 3;

	public static final int BRAD_RADIUS = 4;
	public static final int SNOWMAN_RADIUS = 3;
	public static final int BUNNY_RADIUS = 1;

	public int type;
	private Bitmap mImage;
	public int x , y , width, height;
	private Paint drawingPaint;

	public Obstacle(Context context, int obstacleType)
	{
		if (obstacleType == BRAD)
		{
			mImage = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.tree1);
			width = mImage.getWidth() / 2;
			height = mImage.getHeight();
		}
		x = 0;
		y = 0;
		drawingPaint = new Paint();
	}

	public void render(Canvas canvas)
	{
		canvas.drawBitmap(mImage, x - width / 2, y - height / 2, drawingPaint);
	}
}
