package com.example.Models;

import java.util.ArrayList;
import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by arthur on 2/22/14.
 */
public class Road
{

	private static final int SEGMENTS_NO = 80;
	private static final int SEGMENT_DIST = 30;// px
	private static final int SEGMENT_SIZE = 80;// px
	public ArrayList<SegmentRoad> segments;
	private static final int DISP = 30;
	private static int OK = 0;
	private Paint mDrawingPaint;
	private Paint mSegmentPaint;
	private Path mRoadPath;

	public Road()
	{
		segments = new ArrayList<SegmentRoad>();
		mRoadPath = new Path();
		mDrawingPaint = new Paint(Color.WHITE);
		mSegmentPaint = new Paint(Color.BLACK);
	}

	public synchronized void init()
	{
		SegmentRoad seg = new SegmentRoad();
		seg.right.x = 60;
		segments.add(seg);

		for (int i = 1; i < SEGMENTS_NO; i++)
		{
			seg = new SegmentRoad();

			seg.left.y = seg.right.y = SEGMENT_DIST * i;

			int deviation = new Random().nextInt() % DISP;

			seg.left.x = Math.abs(segments.get(i - 1).left.x + deviation)
					% (Constants.ScreenWidth);

			if (seg.left.x < 0)
				seg.left.x = segments.get(i - 1).left.x;

			if (seg.left.x > Constants.ScreenWidth)
				seg.left.x = segments.get(i - 1).left.x;

			seg.right.x = seg.left.x + SEGMENT_SIZE;
			segments.add(seg);
		}
	}

	public void updateSegments()
	{
		for (int i = 0; i < segments.size(); i++)
		{
			segments.get(i).left.y++;
			segments.get(i).right.y++;

			if (segments.get(i).left.y > Constants.ScreenHeight + 100)
				segments.remove(i);
		}

		if (OK % SEGMENT_DIST == 0)
		{
			SegmentRoad seg = new SegmentRoad();

			int deviation = new Random().nextInt() % DISP;

			seg.left.y = -50;
			seg.right.y = -50;
			seg.left.x = segments.get(0).left.x + deviation;

			if (seg.left.x < 0)
				seg.left.x = segments.get(0).left.x;

			if (seg.left.x > Constants.ScreenWidth)
				seg.left.x = segments.get(0).left.x;

			seg.right.x = seg.left.x + SEGMENT_SIZE;

			segments.add(0, seg);
			segments.get(0).left = seg.left;
			segments.get(0).right = seg.right;
		}

		OK++;
	}

	public void render(Canvas canvas)
	{
		mRoadPath.rewind();
		mRoadPath.moveTo(segments.get(0).left.x, segments.get(0).left.y);
		for (int i = 0; i < segments.size(); i++)
		{
			mRoadPath.lineTo(segments.get(i).right.x, segments.get(i).right.y);
		}

		for (int i = segments.size() - 1; i >= 0; i--)
		{
			mRoadPath.lineTo(segments.get(i).left.x, segments.get(i).left.y);
		}

		canvas.drawPath(mRoadPath, mDrawingPaint);
		for (int i = 0; i < segments.size() - 1; i++)
		{
			canvas.drawLine(segments.get(i).left.x, segments.get(i).left.y,
					segments.get(i + 1).left.x, segments.get(i + 1).left.y,
					mSegmentPaint);

			canvas.drawLine(segments.get(i).right.x, segments.get(i).right.y,
					segments.get(i + 1).right.x, segments.get(i + 1).right.y,
					mSegmentPaint);

		}
	}
}
