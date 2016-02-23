#include <jni.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "MyInsertionSort.h"

int* insertionSort(jint *list);


JNIEXPORT jintArray JNICALL Java_MyInsertionSort_insertionSort
(JNIEnv *env, jobject object, jintArray list){
    jsize len;
    jint *myCopy;
    jintArray result;
    jboolean *is_copy = 0;
    
    len = (*env)->GetArrayLength(env, list);
    myCopy = (jint *) (*env)->GetIntArrayElements(env, list, is_copy);
    if (myCopy == NULL){
        printf("Cannot obtain array from JVM\n");
        exit(EXIT_FAILURE);
    }
    

    /*Creating NewIntArray -- Passing Array Of Primitives
    Code modified from: https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaNativeInterface.html
    */
    result = (*env)->NewIntArray(env, len);
    if(result == NULL) {
	   printf("Cannot create array\n");
	   exit(EXIT_FAIURE);
    }

    jint* temp = insertionSort(myCopy);

    (*env)->SetIntArrayRegion(env, result, 0, len, temp);

    return result;
    
}


jint* insertionSort(jint *list) {

	int num = 0;

	int *temp;
	int size = sizeof(list);

    /* insertion sort 
    code modified from http://www.programmingsimplified.com/c/source-code/c-program-insertion-sort
    */
	int current, tmp, iter;
	for(iter = 0; iter < size; iter++) {
        current = iter;

		while(current > 0 && list[current] < list[current-1]) {
			tmp = list[current];
			list[current] = list[current-1];
			list[current-1] = tmp;

			current--;
		}
	}
	
	return list;
}