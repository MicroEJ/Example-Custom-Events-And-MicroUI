/*
 * C
 *
 * Copyright 2013-2015 IS2T. All rights reserved.
 * For demonstration purpose only.
 * IS2T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

/* Includes ------------------------------------------------------------------*/

#include <stdio.h>
#include <stdlib.h>
#include "sni.h"
#include "LLMJVM.h"
#include "FreeRTOS.h"
#include "task.h"
#include "microjvm_main.h"
#include "stm32f4xx.h"
#include "stm32f429i_discovery_sdram.h"

#include <LLINPUT.h> // API used to send events to MicroUI.
#include <microui_constants.h> // Constants generated MicroUI initialization. It contains the id of our custom event generator.
/* Defines -------------------------------------------------------------------*/

#define MICROJVM_STACK_SIZE 4096
#define JAVA_TASK_PRIORITY      ( 3 ) /** Should be > tskIDLE_PRIORITY & < configTIMER_TASK_PRIORITY */
#define JAVA_TASK_STACK_SIZE     MICROJVM_STACK_SIZE/4

#define EVENT_STACK_SIZE 512
#define EVENT_TASK_PRIORITY      ( 4 ) /** Should be > JAVA_TASK_PRIORITY  & < configTIMER_TASK_PRIORITY */

/* Private functions ---------------------------------------------------------*/

void xJavaTaskFunction(void * pvParameters)
{
	microjvm_main();
	vTaskDelete( xTaskGetCurrentTaskHandle() );
}

void xEventTaskFunction(void * pvParameters)
{
	for(;;)
		{
			vTaskDelay(2000);
			int event = rand() % 100;
			// Sends the given event to our custom event generator.
			LLINPUT_sendEvent(MICROUI_EVENTGEN_CUSTOM, event);
		}
}

/* Public functions ----------------------------------------------------------*/

/*
 * Generic main function
 */
int main(void)
{
	printf("START\n");

	SDRAM_Init();
	
    // Enable fault handlers
    SCB->SHCSR |= SCB_SHCSR_MEMFAULTENA_Msk;
    SCB->SHCSR |= SCB_SHCSR_BUSFAULTENA_Msk;
    SCB->SHCSR |= SCB_SHCSR_USGFAULTENA_Msk;

	// start the main task
	xTaskCreate( xJavaTaskFunction, NULL, JAVA_TASK_STACK_SIZE, NULL, JAVA_TASK_PRIORITY, NULL );
	
	// start the event task.
	xTaskCreate( xEventTaskFunction, NULL, EVENT_STACK_SIZE, NULL, EVENT_TASK_PRIORITY, NULL );

	vTaskStartScheduler();

	printf("END\n");
}

