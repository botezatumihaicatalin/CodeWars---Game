package com.example.Models;

import android.graphics.PointF;

public class Colisions
{

	public static float distance(float x1, float y1, float x2, float y2)
	{
		return (float) Math.sqrt((double) ((x1 - x2) * (x1 - x2) + (y1 - y2)
				* (y1 - y2)));
	}

	public static boolean isLeft(PointF first_point, PointF second_point,
			PointF a)
	{
		return ((second_point.x - first_point.x) * (a.y - first_point.y) - (second_point.y - first_point.y)
				* (a.x - first_point.x)) > 0;
	}

	public static boolean isRight(PointF first_point, PointF second_point,
			PointF a)
	{
		return ((second_point.x - first_point.x) * (a.y - first_point.y) - (second_point.y - first_point.y)
				* (a.x - first_point.x)) < 0;
	}
	
	public static int colision(Ball ball, CoinsList cList)
	{
		for (int i = 0; i < cList.getSize(); i++)
		{
			Coin obs = cList.getCoin(i);
			if (distance(ball.my_position.x, ball.my_position.y, obs.x, obs.y) <= obs.width)
				return i;
		}
		return -1;
	}


	public static int colision(Ball ball, ObstacleList oList)
	{
		for (int i = 0; i < oList.getSize(); i++)
		{
			Obstacle obs = oList.getObstacle(i);
			if (distance(ball.my_position.x, ball.my_position.y, obs.x, obs.y) <= obs.width)
				return i;
		}
		return -1;
	}

	public static boolean colision(Ball mBall, Road mRoad)
	{

		int i;
		boolean ok_left_side, ok_right_side;
		boolean is_on_track = false;

		for (i = 0; i < mRoad.segments.size() - 1; ++i)
		{

			if (mRoad.segments.get(i).left.y <= mBall.my_position.y
					&& mRoad.segments.get(i + 1).left.y >= mBall.my_position.y)
			{
				is_on_track = true;
				ok_left_side = isLeft(mRoad.segments.get(i).right,
						mRoad.segments.get(i + 1).right, mBall.my_position);
				if (ok_left_side == false)
					return false;
				ok_right_side = isRight(mRoad.segments.get(i).left,
						mRoad.segments.get(i + 1).left, mBall.my_position);
				if (ok_right_side == false)
					return false;
			}
		}

		return true && is_on_track;
	}
}
