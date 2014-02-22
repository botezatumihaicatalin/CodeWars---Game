package com.example.Models;

import java.util.ArrayList;
import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by arthur on 2/22/14.
 */
public class Road
{

	private static final int SEGMENTS_NO = 80;
	private static final int SEGMENT_DIST = 5;// px
	private static final int SCREEN_DIM = 300;// px
	public ArrayList<SegmentRoad> segments;

	public Road()
	{
		segments = new ArrayList<SegmentRoad>();
	}

	public void init()
	{
		// generate SEGMENTS_NO from 20px to 20px
		SegmentRoad seg = new SegmentRoad();
		segments.add(seg);

		for (int i = 1; i <= SEGMENTS_NO; i++)
		{
			seg = new SegmentRoad();

			seg.left.y = seg.right.y = SEGMENT_DIST * i;
			seg.left.x = Math.abs((new Random().nextInt() % 20 + segments
					.get(i - 1).left.x) % SCREEN_DIM);
			seg.right.x = seg.left.x + 100;
			segments.add(seg);
		}
	}

	public void updateSegments()
	{
	}

	public void render(Canvas canvas)
	{
		for (int i = 0; i < segments.size() - 1; i++)
		{
			canvas.drawLine(segments.get(i).left.x, segments.get(i).left.y,
					segments.get(i + 1).left.x, segments.get(i + 1).left.y,
					new Paint(Color.BLACK));

			canvas.drawLine(segments.get(i).right.x, segments.get(i).right.y,
					segments.get(i + 1).right.x, segments.get(i + 1).right.y,
					new Paint(Color.BLACK));
		}
	}
}
