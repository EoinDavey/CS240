#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
main() {
    int fdptr[2], n, buffersize=21;
    char strbuff[buffersize+1];
    char message[] = "Welcome to Unix pipes";

    pipe(fdptr);
    int pid = fork();

    if (pid == 0) {
        while (true) {
            n = read(fdptr[0],strbuff, buffersize);
            strbuff[n]=0;
            printf("Child read: %s\n", strbuff);
            sleep(5);
        }
    }
    else {
        while (true) {
            printf("Parent writing: %s\n", message);
            write(fdptr[1], message, strlen(message));
            sleep(5);
        }
    }
}

