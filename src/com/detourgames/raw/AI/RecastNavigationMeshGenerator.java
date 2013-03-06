package com.detourgames.raw.AI;

public class RecastNavigationMeshGenerator {
	public static native float rcSqrt(float f);
	
	static{
		System.loadLibrary("SDL");
		System.loadLibrary("Recast");
	}
}
