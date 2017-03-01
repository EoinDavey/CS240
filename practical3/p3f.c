#include <stdio.h>
#include <fcntl.h>
main()
{
    char fbuffer[15];
    printf("Enter file name: ");
    scanf("%s",fbuffer);
    char buffer[10];
    int fd, n;
    fd = open(fbuffer,O_RDONLY);
    if(fd==-1){
        printf("file not found\n");
        return;
    }
    do {
        n = read(fd, buffer, 10);
        write(1,buffer, n);
    } while(n==10);
    close(fd);
    printf("\n");
}

