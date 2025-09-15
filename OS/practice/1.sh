echo "Enter a number:"
read n

factorial=1
i=$n

while [ $i -gt 0 ]
do
factorial=$(($factorial*$i))
i=$(($i-1))
done

echo $factorial


