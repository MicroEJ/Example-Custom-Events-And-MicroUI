/*
 * C
 *
 * Copyright 2014 IS2T. All rights reserved.
 * For demonstration purpose only.
 * IS2T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

/* Includes ------------------------------------------------------------------*/

#include <stdio.h>
#include <stdlib.h>
#include "sni.h"
#include "microjvm_main.h"
#include <RTL.h>

#include "stm32f4xx.h"
#include "stm32f429i_discovery_sdram.h"

#include <LLINPUT.h> // API used to send events to MicroUI.
#include <microui_constants.h> // Constants generated MicroUI initialization. It contains the id of our custom event generator.

/* Defines -------------------------------------------------------------------*/

#define MICROJVM_STACK_SIZE 2048

/* Globals -------------------------------------------------------------------*/

static U64 javaTaskStack[(MICROJVM_STACK_SIZE)/8];
 
/* Private functions ---------------------------------------------------------*/

__task void eventTask (void)
{
	for(;;)
	{
		os_dly_wait(200);
		int event = rand() % 100;
		// Sends the given event to our custom event generator.
		LLINPUT_sendEvent(MICROUI_EVENTGEN_CUSTOM, event);
	}
} 

__task void javaTask (void)
{
	os_tsk_create(eventTask, 0);
	microjvm_main();
	os_tsk_delete_self();
} 

/* Public functions ----------------------------------------------------------*/

int main(void)
{
	printf("START\n");
	
	SDRAM_Init();
	
	// start the main task
	os_sys_init_user(javaTask, 10, &javaTaskStack, sizeof(javaTaskStack));
	
	printf("END\n");
}
