package com.detourgames.raw;

import com.badlogic.gdx.physics.box2d.World;

public interface IReusable {

	public void spawn(World world);

	public void recycle();

}
