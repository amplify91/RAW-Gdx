package com.detourgames.raw.AI.steering;

import java.util.ArrayList;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;

public class SimpleRaycastAStar implements IPathFinder{

	int numberOfDirections=4;
	@Override
	public boolean AvoidObstacles(Body physics,
			ArrayList<Fixture> fixturesToIgnore) {
		//divide the circle into n angles
		double[] angles=new double[numberOfDirections];
		for( int i=0;i<numberOfDirections;i++)
		{
			angles[i]=(360.0*i/numberOfDirections);
		}
		return false;
	}

	public void SetNumberOfDirections(int numberOfDirections) throws Exception {
		if(numberOfDirections>0)
		{
			this.numberOfDirections=numberOfDirections;
		}
		else 
			throw new Exception("Cannot have SimpleRycastAStar use fewer than 1 direction");
		
	}
	
}
