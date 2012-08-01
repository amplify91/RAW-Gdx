package com.detourgames.raw.turretElement;
import com.badlogic.gdx.math.Vector2;
import com.detourgames.raw.ControllerComponent;
import com.detourgames.raw.EventQueue;
import com.detourgames.raw.FireProjectileEvent;
import com.detourgames.raw.PhysicsComponent;
import com.detourgames.raw.StateComponent;
public class TurretComponent extends PhysicsComponent{

	@Override
	public void update() {
		if(state==StateComponent.STATE_SHOOTING)
		{
			if(target!=null)
			{
				FireMissileAtTarget();
			}
			state=StateComponent.STATE_IDLE;
		}
		if(controller!=null)
		{
			controller.update(null,this);
		}
		
	}
	ControllerComponent controller=TurretController.GetInstance();
	Vector2 target;
	int state = StateComponent.STATE_IDLE;
	
	public void StartAttackingLocation(Vector2 location)
	{
		state=StateComponent.STATE_SHOOTING;
		target=location;
	}
	public void FireMissileAtTarget()
	{
		System.out.println("Shooting at "+target.x +", "+target.y);
		EventQueue.getEventQueue().queue(new FireProjectileEvent(new Vector2(target.x,target.y),this.mParent));
	}
}
