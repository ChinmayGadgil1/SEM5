leap_count=2000

count=0

while [ $count -lt 20 ]
do
    echo "$leap_count"
    leap_count=$((leap_count+4))
    count=$((count+1))
done

