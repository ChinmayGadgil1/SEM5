echo "Enter n:"
read n

a=0
b=1

while [ $n -gt 0 ]
do
    printf "$a "
    c=$(($a + $b))
    a=$(($b))
    b=$(($c))

    n=$(($n - 1 ))
done

