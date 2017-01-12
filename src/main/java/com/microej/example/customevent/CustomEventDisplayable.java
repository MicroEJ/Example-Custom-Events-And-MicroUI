/*
 * Java
 *
 * Copyright 2015 IS2T. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found at http://www.is2t.com/open-source-bsd-license/.
 */
package com.microej.example.customevent;

import ej.microui.display.Colors;
import ej.microui.display.Display;
import ej.microui.display.Displayable;
import ej.microui.display.GraphicsContext;
import ej.microui.event.Event;
import ej.microui.util.EventHandler;

/**
 * This displayable allows to show the reception of the custom events.
 */
public class CustomEventDisplayable extends Displayable implements EventHandler {

	private static final int BACKGROUND_COLOR = Colors.NAVY;
	private static final int FOREGROUND_COLOR = Colors.WHITE;

	private final int displayWidth;
	private final int displayHeight;
	private int lastCustomEventDataReceived;
	private boolean hasReceivedEvent;
	private int id;

	/**
	 * Creates a new custom event displayable for the given display.
	 *
	 * @param display
	 *            the display used by the displayable.
	 */
	public CustomEventDisplayable(Display display) {
		super(display);
		this.displayWidth = display.getWidth();
		this.displayHeight = display.getHeight();
		this.hasReceivedEvent = false;
	}

	@Override
	public void paint(GraphicsContext g) {
		int displayWidth = this.displayWidth;
		int displayHeight = this.displayHeight;

		// Fills the background.
		g.setColor(BACKGROUND_COLOR);
		g.fillRect(0, 0, displayWidth, displayHeight);

		// Draws the last custom event data received.
		String eventText;
		if (this.hasReceivedEvent) {
			eventText = "Event " + this.lastCustomEventDataReceived + " - " + this.id;
		} else {
			eventText = "No event";
		}
		g.setColor(FOREGROUND_COLOR);
		g.drawString(eventText, displayWidth / 2, displayHeight / 2, GraphicsContext.HCENTER | GraphicsContext.VCENTER);
	}

	@Override
	public EventHandler getController() {
		return this;
	}

	@Override
	public boolean handleEvent(int event) {
		boolean eventProcessed = false;

		// Filter on the event type to retrieve the custom events.
		if (Event.getType(event) == CustomEventGenerator.CUSTOM) {
			if (!this.hasReceivedEvent) {
				this.hasReceivedEvent = true;
			}
			this.lastCustomEventDataReceived = Event.getData(event);

			this.id = Event.getGeneratorID(event);
			eventProcessed = true;
			repaint();
		}
		
		if (Event.getType(event) == Event.POINTER) {
			System.out.println("CustomEventDisplayable.handleEvent() Pointer event ID : " + Event.getGeneratorID(event));
		}
		return eventProcessed;
	}

}
