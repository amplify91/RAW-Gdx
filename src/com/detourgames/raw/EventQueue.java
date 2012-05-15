package com.detourgames.raw;

import com.badlogic.gdx.utils.Array;

public class EventQueue {
	// Supposedly, I'm going to implement the Command design pattern to better
	// manage input.

	private static EventQueue mEventQueue = new EventQueue();

	private Array<Event> mQueue = new Array<Event>();

	private EventQueue() {

	}

	public static EventQueue getEventQueue() { // TODO synchronized?
		return mEventQueue;
	}

	public void queue(Event event) {
		mQueue.add(event);
	}

	public Event getAndRemoveNextEvent() {
		return mQueue.removeIndex(0);
	}

	public void processAndRemoveAllEvents() {
		for (Event event : mQueue) {
			event.executeEvent();
		}
		mQueue.clear();
	}

}
