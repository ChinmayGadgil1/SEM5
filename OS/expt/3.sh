num=1

while [ $num -le 20 ]
do
    square=$((num*num))
    echo "$square"
    num=$((num+1))
done
