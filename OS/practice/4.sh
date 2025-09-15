echo "enter n:"
read n

a=1
b=1
i=2
while [ $i -lt $n ]
do
c=$((a+b))
a=$b
b=$c
i=$((i+1))
done

echo "$c"