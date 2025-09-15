echo "Enter n:"
read n

rev=0
sum=0
original=$n

while [ $n -gt 0 ]
do
    dig=$(( $n % 10 ))
    sum=$(( $sum + $dig ))
    rev=$(( $rev * 10 + $dig ))
    n=$(( $n / 10 ))
done

echo "Reverse of $original is $rev"
echo "Sum of digits is $sum"