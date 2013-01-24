package com.detourgames.raw.AI;

public class RecastNavigationMeshGenerator {
	public static native void displayHelloWorld();
	
	static{
		System.loadLibrary("Recast");
	}
}
