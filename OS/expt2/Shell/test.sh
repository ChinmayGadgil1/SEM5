echo "Enter n"
read n

i=2

while [ $i -lt $n ]
do
    j=$(( $n % $i ))
if [ $j -eq 0 ]
then
    echo "Not prime"
    exit
fi

i=$(( $i + 1 ))

done

echo "Prime"