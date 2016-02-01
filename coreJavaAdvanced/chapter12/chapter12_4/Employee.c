#include "com_corejava_chapter12_4_Employee.h"
#include <stdio.h>

JNIEXPORT void JNICALL Java_com_corejava_chapter12_14_Employee_raiseSalary(
							JNIEnv * env, jobject obj, jdouble increment)
{
	/* get the class */
	jclass class_Employee = (*env)->GetObjectClass(env, obj);
	
	/* get the field ID */
	jfieldID id_salary = (*env)->GetFieldID(env, class_Employee, "salary", "D");
	
	/* get the field value */
	jdouble salary = (*env)->GetDoubleField(env, obj, id_salary);
	
	salary += increment;
	
	/* set the field value */
	(*env)->SetDoubleField(env, obj, id_salary, salary);
}
