package com.detourgames.raw.Algorithms;

import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.detourgames.raw.Sprite;

public class LineOfSight {
	Sprite from;
	Sprite to;
	
	public LineOfSight(Sprite from,Sprite to)
	{
		this.from=from;
		this.to=to;
	}
	RayCastCallback raycast;
}
