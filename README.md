# Overview
This example illustrates how to propagate custom events from the hardware to MicroUI. MicroUI already supports generic events like touch, button and joystick.
Hardware events (e.g. sensors movement) can be used for specific display action upon occurrence.

See the Device Developer's Guide at section **Inputs** (13.5) for more information.

### Requirements
- JRE 7 (or later) x86.
- MicroEJ 4.0 or later.
- Java platform with (at least) EDC-1.2, MICROUI-2.0.

### Project structure
- com.microej.example.customevent
	- src/main/java
		- Java sources
	- src/main/c
		- Entry points (main.c) examples for the given board/RTOS that generates custom events.

### Steps:
1. Create a platform with at least the UI module.
2. Before building this platform, some configuration is required. In the **xxx-configuration project** in the **microui/microui.xml** file, add the following xml element to the eventgenerators 
<code>\<eventgenerator name="CUSTOM" class="com.microej.example.customevent.CustomEventGenerator"\></code>
3. Build your platform.
4. In the [/com.microej.example.customevent/src/main/c](com.microej.example.customevent/src/main/c) folder
4.1 Choose the file corresponding to the board/RTOS used 
4.2 Replace the application entry point in the /xxx-bsp project by the file selected in the previous step
5. In MicroEJ, create a launch configuration for the Java application
5.1. Set the name to "CustomEvent_Build"
5.2. In the **Main** tab
5.2.1. Set the **Project** field to "com.microej.example.customevent"
5.2.2. Set the **Main type** field  to "CustomEventExample"
5.3. In the **Execution** tab
5.3.1. In the **Target** frame, set the JPF to the one built in step #3
5.3.2. In the **Execution** frame, select the **Execute on EmbJPF** radio button
6. Run the launch configuration.
7. Compile, link and flash the xxx-bsp project.


