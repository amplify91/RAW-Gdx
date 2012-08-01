package com.detourgames.raw.turretElement;
import com.badlogic.gdx.math.Vector2;
import com.detourgames.raw.ControllerComponent;
import com.detourgames.raw.GameManager;
import com.detourgames.raw.PhysicsComponent;
import com.detourgames.raw.StateComponent;

public class TurretController extends ControllerComponent {

	@Override
	public void update(StateComponent state, PhysicsComponent physics){
		GetNextAction((TurretComponent)physics);
	}
	public void GetNextAction(TurretComponent turret)
	{
		
		//find target (RAW)
		float targetX=GameManager.getGameManager().getLevel().getHero().getX();
		float targetY=GameManager.getGameManager().getLevel().getHero().getY();
		//shoot target
		if(turret!=null)
			turret.StartAttackingLocation(new Vector2(targetX,targetY));
	}
	//singleton pattern
	private static TurretController instance=new TurretController();
	public static TurretController GetInstance(){return instance;}
	
	public TurretComponent turret;
	
}
