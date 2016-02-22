#include <jni.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "MyInsertionSort.h"

jint* insertionSort(jint *list);


JNIEXPORT jintArray JNICALL Java_MyInsertionSort_insertionSort
(JNIEnv *env, jobject object, jintArray list){
    jsize len;
    jint *myCopy;
    jint *result;
    jboolean *is_copy = 0;
    
    len = (*env)->GetArrayLength(env, list);
    myCopy = (jint *) (*env)->GetIntArrayElements(env, list, is_copy);
    if (myCopy == NULL){
        printf("Cannot obtain array from JVM\n");
        exit(0);
    }
    
    myCopy = insertionSort(myCopy);
    
    return myCopy;
    
}


jint* insertionSort(jint *old) {

	int num = 0;

	int *temp;
	int size = sizeof(old);

	int* list = malloc(sizeof(int)*size);
	memcpy(list, old, sizeof(int)*size);

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
    
    /*
	for(iter = 0; iter < size; iter++) {
		printf("%i\n", list[iter]);
	}
     */
	
	return list;
}
