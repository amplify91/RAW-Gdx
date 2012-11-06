package com.detourgames.raw.steering;

import java.util.ArrayList;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.detourgames.raw.GameManager;
import com.detourgames.raw.ContactListenerInterfaces.BeginContactInterface;

public class SteeringSensor extends com.badlogic.gdx.physics.box2d.Fixture implements BeginContactInterface{
	
	
	public SteeringSensor(Body body, long addr)
	{
		super(body, addr);
		this.setSensor(true);  
		
		
	}
	
	public void beginContact(Contact contact){
		for(SteeringInterface steerer:listeners)
		{
			steerer.On_Sensor_Making_Contact(this, contact);
		}
	}

	private ArrayList<SteeringInterface> listeners=new ArrayList<SteeringInterface>();
	
	public void Subscribe(SteeringInterface steerer)
	{
		listeners.add(steerer);
	}
	
	public interface SteeringInterface
	{
		void On_Sensor_Making_Contact(SteeringSensor sensor, Contact contact);
	}
}
