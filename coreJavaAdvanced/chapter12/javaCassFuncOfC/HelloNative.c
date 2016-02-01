#include "com_corejava_chapter12_HelloNative.h"
#include <stdio.h>

JNIEXPORT void JNICALL Java_com_corejava_chapter12_HelloNative_greeting(JNIEnv* env, jclass c1)
{
	printf("hello native world ,from xiaotangtang!");
}

