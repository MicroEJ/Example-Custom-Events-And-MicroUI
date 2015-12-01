# Overview
The examples below illustrates some of the MicroUI library features.

## com.microej.example.customevent
The following project illustrates how to make the hardware events visible from MicroUI. MicroUI already supports generic events like touch, button and joystick.
Hardware events (e.g. sensors movement) can be used for specific display action upon this event.

See the User Interface Extension User Manual at section MicroUI->MicroUI Internal (4.3) for more information.

### Requirements
- JRE 7 (or later) x86.
- MicroEJ 3.1 or later.
- Java platform with (at least) EDC-1.2, MICROUI-1.5.0, MICROUI-1.5.0-INTERNAL.

### Project structure
- `src/main/java`
  - Java sources

### Steps:
1. Create a platform with at least the UI module.
2. Before building this platform, some configuration is required. In the xxx-configuration project in the file microui/microui.xml, add the following xml element to the eventgenerators 

'''
		<eventgenerator name="CUSTOM" class="com.microej.example.customevent.CustomEventGenerator"/>
'''

3. Build your platform.
4. At the root of this Github repository in the /BSP_entry_points folder
4.1 Choose the file corresponding to the board/RTOS used 
4.2 Copy/paste the file into your MicroEJ workspace /xxx-bsp project to replace the application entry point.
5. In MicroEJ, create a launch configuration for the Java application
5.1. Set the name to "CustomEvent_Build"
5.2. In the "main tab"
5.2.1. Set the project to "com.microej.example.customevent"
5.2.2. Set the main type to "CustomEventExample"
5.3. In the "Execution tab"
5.3.1. In the "Target" frame, set the JPF to the one built in step #3
5.3.2. In the "Execution" frame, select the "Execute on EmbJPF" radio button
5.4. Add the class com.is2t.example.CustomEventGenerator to the required types (On the main tab, below the main type).

6. Run the launch configuration.

7. Compile, link and flash the xxx-bsp project.

### Troubleshooting
- xxx does not provide implementation for MICROUI-1.5.0-INTERNAL (referenced by project com.microej.example.customevent): in the xxx project rename the file `javaLibs/microui_internal-1.5.0.jar` to `microui-1.5.0-internal.jar` (hint : you can locate it easily using the Ctrl+Shift+R keyboard shortcut).

## BSP_entry_points
- Entry points (main.c) example for the given board/RTOS that generates custom events.

## Changes
- 2015 november: initial release.

## License
See the license file `LICENSE.md` located at the root of this repository.