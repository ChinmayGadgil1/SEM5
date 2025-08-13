num=1

while [ $num -le 20 ]
do
    square=$((num*num))
    printf "$square "
    num=$((num+1))
done
echo ""