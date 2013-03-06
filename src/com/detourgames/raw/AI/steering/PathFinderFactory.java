package com.detourgames.raw.AI.steering;

public class PathFinderFactory {
	
	//RaycastSteerer
	private static RaycastSteerer raycastSteerer;
	public static RaycastSteerer GetRaycastSteerer(){
		if(raycastSteerer==null)
			raycastSteerer=new RaycastSteerer();
		return raycastSteerer;
	}
	
	//SimpleRaycastAStar
	private static SimpleRaycastAStar simpleRaycastAStar;
	public static SimpleRaycastAStar GetSimpleRaycastAStar()
	{
		if(simpleRaycastAStar==null)
			simpleRaycastAStar=new SimpleRaycastAStar();
		return simpleRaycastAStar;
	}
	public static SimpleRaycastAStar GetSimpleRaycastAStar(int numberOfDirections)
	{
		SimpleRaycastAStar astar=GetSimpleRaycastAStar();
		try {
			astar.SetNumberOfDirections(numberOfDirections);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return astar;
	}
	
}
