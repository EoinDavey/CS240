#include <fcntl.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>

int main(){
    char pipename[] = "fifo";
    if(access(pipename,F_OK))
        if(mknod(pipename,010600,0)!=0)
            fprintf(stderr, "Error creating pipe\n");
    int i = open(pipename, O_WRONLY);
    if(i==-1){
        fprintf(stderr,"Error opening pipe\n");
        exit(0);
    }
    close(1);
    int out = dup(i);
    printf("EoinD\n");
    return 0;
}
