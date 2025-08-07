
#!/bin/bash
echo "Enter a number:"
read n

larg_dig=-1
sec_larg_dig=-1
small_dig=10
sec_small_dig=10
while [ $n -gt 0 ]
do
    digit=$((n % 10))
    if [ $digit -gt $larg_dig ]
    then
        sec_larg_dig=$larg_dig
        larg_dig=$digit
    elif [ $digit -gt $sec_larg_dig ] && [ $digit -lt $larg_dig ]
    then
        sec_larg_dig=$digit
    fi
    if [ $digit -lt $small_dig ]
    then
        sec_small_dig=$small_dig
        small_dig=$digit
    elif [ $digit -lt $sec_small_dig ] && [ $digit -gt $small_dig ]
    then
        sec_small_dig=$digit
    fi
    n=$((n / 10))
done

echo "Second largest digit: $sec_larg_dig"
echo "Second smallest digit: $sec_small_dig"
echo "Sum: $((sec_larg_dig + sec_small_dig))"