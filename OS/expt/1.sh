echo "Enter a number:"
read n
larg_dig=0
small_dig=9
while [ $n -gt 0 ]
do
    digit=$((n % 10))
    if [ $digit -gt $larg_dig ]
    then
        larg_dig=$digit
    fi
    if [ $digit -lt $small_dig ]
    then
        small_dig=$digit
    fi
    n=$((n / 10))
done
echo "Largest digit: $larg_dig"
echo "Smallest digit: $small_dig"
echo "Difference: $((larg_dig - small_dig))"