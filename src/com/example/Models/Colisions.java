package com.example.Models;

import android.graphics.PointF;

public class Colisions
{

	public static boolean isLeft(PointF first_point, PointF second_point, PointF a)
	{
		return ((second_point.x - first_point.x) * (a.y - first_point.y) - (second_point.y - first_point.y)
				* (a.x - first_point.x)) > 0;
	}

	public static boolean isRight(PointF first_point, PointF second_point, PointF a)
	{
		return ((second_point.x - first_point.x) * (a.y - first_point.y) - (second_point.y - first_point.y)
				* (a.x - first_point.x)) < 0;
	}

	public static boolean colision(Ball mBall, Road mRoad)
	{

		int i;
		boolean ok_left_side, ok_right_side;

		for (i = 0; i < mRoad.segments.size() - 1; ++i)
		{
			ok_left_side = isLeft(mRoad.segments.get(i).right,
					mRoad.segments.get(i + 1).right, mBall.my_position);
			if (ok_left_side == false)
				return false;
			ok_right_side = isRight(mRoad.segments.get(i).left,
					mRoad.segments.get(i + 1).left, mBall.my_position);
			if (ok_right_side == false)
				return false;
		}

		return true;
	}
}
