/*
 * Java
 *
 * Copyright 2015 IS2T. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found at http://www.is2t.com/open-source-bsd-license/.
 */
package com.microej.example.customevent;

import com.is2t.microui.io.EventGenerator;

import ej.microui.Event;

/**
 * Event generator used to received the custom events.
 */
public class CustomEventGenerator extends EventGenerator {

	public static final int CUSTOM = 42;

	@Override
	public void setProperty(String name, String value) {
		// Nothing to do.
	}

	@Override
	protected void eventReceived(int event) {
		event = Event.buildEvent(getEventType(), this, event);
		// Dispatches the event to the listener.
		getListener().performAction(event);
	}

	@Override
	protected void eventsReceived(int[] events) {
		for (int event : events) {
			eventReceived(event);
		}
	}

	@Override
	public int getEventType() {
		return CUSTOM;
	}
}
