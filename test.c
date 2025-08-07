# include<stdio.h>



int main(){

    int n=8;
for(int i=1;i<=n;i++){
    for(int k=1;k<=n-i;k++){
        printf(" ");
    }
    for(int j=i;j>0;j--){
        printf(" *");
    }
    printf("\n");
}

return 0;
}