package com.example.Models;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

public class Ball
{

	public Ball()
	{

		my_radius = 5.0f;

		// bmp image
		my_image = "Your image here";

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
		Paint p = new Paint();
		p.setColor(Color.YELLOW);
		canvas.drawCircle(my_position.x, my_position.y, my_radius , p);

	}

	public float my_radius;
	public String my_image;
	public float speedX, speedY;
	public PointF my_position;

}
