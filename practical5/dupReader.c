#include <fcntl.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>

int main(){
    char pipename[] = "fifo";
    if(access(pipename,F_OK)){
        fprintf(stderr,"error reading pipe\n");
        exit(0);
    }
    int i = open(pipename,O_RDONLY);
    char buffer[11];
    int n = read(i, buffer,10);
    buffer[n]=0;
    printf("%s",buffer);
    return 0;
}
