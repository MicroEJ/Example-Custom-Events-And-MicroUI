/*
 * Java
 *
 * Copyright 2015 IS2T. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found at http://www.is2t.com/open-source-bsd-license/.
 */
package com.microej.example.customevent;

import ej.microui.event.Event;
import ej.microui.event.generator.GenericEventGenerator;

/**
 * Event generator used to received the custom events.
 */
public class CustomEventGenerator extends GenericEventGenerator {

	public static final int CUSTOM = 0x00;

	@Override
	protected void eventReceived(int event) {
		event = Event.buildEvent(getEventType(), this, event);
		getEventHandler().handleEvent(event);
	}

	@Override
	public void setProperty(String name, String value) {
		// TODO Auto-generated method stub
		
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
