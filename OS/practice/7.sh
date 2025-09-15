echo "Enter n"
read n

a=1
b=1
i=1

while [ $i -le $n ]
do
c=$(($a+$b))
printf "$a "
a=$(($b))
b=$(($c))
i=$(($i + 1))
done

