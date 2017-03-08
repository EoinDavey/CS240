#include <fcntl.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>
main(int argc, char *argv[]) {
    char pipename[] = "my_pipe";
    int namedpipe;
    int max_size = 14;
    char message[max_size+1];
    char buffer[max_size+1];
    bool paramerror= false;
    if(access(pipename, F_OK))
        if(mknod("my_pipe", 010600, 0) == 0)
            printf("Named pipe created succesfully\n");
        else {
            printf("Failed to create named pipe\n");
            exit(0);
        }
    else
        printf("using existing named pipe\n");
    if(argc==2){
        if (strcmp (argv[1], "reader")==0)
            namedpipe = open("my_pipe", O_RDONLY);
        else if (strcmp(argv[1], "writer")==0)
            namedpipe = open("my_pipe", O_WRONLY);
        else
            paramerror = true;
    } else
        paramerror = true;
    if (paramerror)
        printf("Incorrect usage: Use namedpipe <reader | writer> \n");
    else if (namedpipe<0)
        printf("Couldn't open named pipe\n");
    int i;
    if (strcmp(argv[1], "writer")==0)
        for (i=1; i<=10; i++) {
            sprintf(message, "%d", i);
            strcat(message, " message text");
            write(namedpipe, message, strlen(message));
            printf("writer: Sent message <%s> to named pipe\n",message);
        }
    else for (i=1; i<=10; i++) {
        int n = read(namedpipe, buffer, max_size);
        buffer[n]=0;
        printf("reader: Read message <%s> %d characters \n",buffer, n);
    }
    close(namedpipe);
}
