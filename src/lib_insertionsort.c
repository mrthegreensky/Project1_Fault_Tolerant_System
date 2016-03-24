#include <jni.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include "MyInsertionSort.h"

int* insertionSort(jint *list, int size, double hazard);


JNIEXPORT jintArray JNICALL Java_MyInsertionSort_insertionSort
(JNIEnv *env, jobject object, jintArray list, jdouble hazard){
	jsize len;
	jint *myCopy;
	jintArray result;
	jboolean *is_copy = 0;
	
	len = (*env)->GetArrayLength(env, list);
	myCopy = (jint *) (*env)->GetIntArrayElements(env, list, is_copy);
	if (myCopy == NULL){
		printf("Cannot obtain array from JVM\n");
		exit(1);
	}
	

	/*Creating NewIntArray -- Passing Array Of Primitives
	Code modified from: https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaNativeInterface.html
	*/
	result = (*env)->NewIntArray(env, len);
	if(result == NULL) {
	   printf("Cannot create array\n");
	   exit(1);
	}

	jint* temp = insertionSort(myCopy, len, hazard);
	if(temp == NULL) {
		printf("Insertion Sort has failed\n");
		exit(1);
	}

	(*env)->SetIntArrayRegion(env, result, 0, len, temp);

	return result;
	
}


jint* insertionSort(jint *list, int size, double hazard) {

	int numAccesses = 0;

	int *temp;
	
	/* insertion sort 
	code modified from http://www.programmingsimplified.com/c/source-code/c-program-insertion-sort
	*/
	int current, tmp, iter;
	for(iter = 0; iter < size; iter++) {
		current = iter;
		numAccesses += 2;
		while(current > 0 && list[current] < list[current-1]) {
			tmp = list[current];
			list[current] = list[current-1];
			list[current-1] = tmp;

			current--;
			numAccesses += 7;
		}
	}
	srand(time(NULL));
	double random = (double)rand()/(double)RAND_MAX;
	if((random >= 0.5) && (random <= (0.5+(hazard*numAccesses)))) {
		return NULL;
	}
	return list;
}
