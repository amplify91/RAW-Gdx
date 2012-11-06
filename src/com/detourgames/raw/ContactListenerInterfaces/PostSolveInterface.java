package com.detourgames.raw.ContactListenerInterfaces;

import com.badlogic.gdx.physics.box2d.Contact;

public interface PostSolveInterface {
	void postSolve(Contact contact);
}
