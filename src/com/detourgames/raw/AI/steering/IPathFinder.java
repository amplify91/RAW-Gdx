package com.detourgames.raw.AI.steering;

import java.util.ArrayList;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;

public abstract interface IPathFinder {
	boolean AvoidObstacles(Body physics,ArrayList<Fixture>fixturesToIgnore);
}
