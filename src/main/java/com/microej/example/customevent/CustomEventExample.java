/*
 * Java
 *
 * Copyright 2015 IS2T. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found at http://www.is2t.com/open-source-bsd-license/.
 */
package com.microej.example.customevent;

import ej.microui.MicroUI;
import ej.microui.display.Display;
import ej.microui.display.Displayable;

/**
 * This example illustrates the use of the custom events.
 */
public class CustomEventExample {

	// Prevents initialization.
	private CustomEventExample() {
	}

	/**
	 * Example entry point.
	 *
	 * @param args
	 *            not used.
	 */
	public static void main(String[] args) {
		MicroUI.start();
		Display display = Display.getDefaultDisplay();
		Displayable displayable = new CustomEventDisplayable(display);
		displayable.show();
	}

}
