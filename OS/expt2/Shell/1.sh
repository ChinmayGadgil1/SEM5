#!/bin/bash

echo "Enter n"
read n

if [ $((n % 2)) -eq 0 ]
then
  echo "Even"
else
  echo "Odd"
fi

sum=0

while [ $n -gt 0 ]
do
  dig=$(( $n % 10 ))
  sum=$(( $sum + $dig ))
  n=$(( $n / 10 ))
done

echo "Sum is: $sum"
