n=9

count=1
while [ $count -le 10 ]
do 
    printf "$((n*count)) "
    count=$((count+1))
done
printf "\n"
