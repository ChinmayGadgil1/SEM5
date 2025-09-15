echo "enter no"
read n

factorial=1
i=1

while [ $i -le $n ]
do

factorial=$(( $factorial * $i ))
i=$(( $i + 1 ))

done

echo "Factorial is $factorial"




