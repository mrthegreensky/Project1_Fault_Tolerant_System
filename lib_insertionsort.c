#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]) {

	char *line = NULL;
	int num = 0;

	if(argc != 5) {
		perror("Incorrect number of arguments. Input data name, output data name, hazard and time");
		exit(EXIT_FAILURE);
	}

	FILE *fp;
	fp = fopen(argv[1], "r");

	if(fp  == NULL) {
		perror("Error opening file.\n");
		exit(EXIT_FAILURE);
	}

	int *list, *temp;
	int size = 0;

	list = malloc(sizeof(int));
	if(list == NULL) {
		perror("Error mallocing memory");
		exit(EXIT_FAILURE);
	}

	while(!feof(fp)) {

		fscanf(fp, "%d", &num);
		list[size] = num;

		temp = realloc(list, (size+2)*sizeof(int));
		if(temp != NULL) {
			list = temp;
			size++;
		} else {
			perror("Error reallocing memory");
			free(list);
			exit(EXIT_FAILURE);
		}
	}
	size--; //To fix the offset of size increase

    
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

	for(iter = 0; iter < size; iter++) {
		printf("%i\n", list[iter]);
	}

	free(list);

	return(0);
}