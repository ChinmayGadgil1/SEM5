echo "Enter a number"
read n
largest=0
smallest=9
while [ $n -gt 0 ]
do
dig=$(( n % 10))
if [ $dig -gt  $largest ]
then 
largest=$dig
fi
if [ $dig -lt $smallest ]
then 
smallest=$dig
fi
n=$((n/10))
done
echo "Largest : $largest"
echo "smallest : $smallest"