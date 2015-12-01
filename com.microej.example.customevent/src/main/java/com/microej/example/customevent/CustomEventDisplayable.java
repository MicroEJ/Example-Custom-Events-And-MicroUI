/*
 * Java
 *
 * Copyright 2015 IS2T. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found at http://www.is2t.com/open-source-bsd-license/.
 */
package com.microej.example.customevent;

import ej.microui.Colors;
import ej.microui.Event;
import ej.microui.io.Display;
import ej.microui.io.Displayable;
import ej.microui.io.GraphicsContext;

/**
 * This displayable allows to show the reception of the custom events.
 */
public class CustomEventDisplayable extends Displayable {

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
	public void performAction(int event) {
		// Filter on the event type to retrieve the custom events.
		if (Event.getType(event) == CustomEventGenerator.CUSTOM) {
			if (!this.hasReceivedEvent) {
				this.hasReceivedEvent = true;
			}
			this.lastCustomEventDataReceived = Event.getData(event);
			this.id = Event.getGeneratorID(event);
			repaint();
		}
	}

}
