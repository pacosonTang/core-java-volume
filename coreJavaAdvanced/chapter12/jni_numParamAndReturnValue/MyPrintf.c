#include "com_corejava_chapter12_2_MyPrintf.h"
#include <stdio.h>

JNIEXPORT jint JNICALL Java_com_corejava_chapter12_12_MyPrintf_print
  (JNIEnv* env, jclass cl, jint width, jint precision, jdouble x)
{
	char fmt[30];
    jint ret;
    sprintf(fmt, "%%%d.%df", width, precision);
    ret = printf(fmt, x);
    fflush(stdout);
    return ret;
}