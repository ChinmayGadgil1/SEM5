leap_count=2000
count=0

while [ $count -lt 20 ]
do
    if (( leap_count % 4 == 0 && leap_count % 100 != 0 || leap_count % 400 == 0 ))
    then
        printf "$leap_count "
        count=$((count+1))
    fi
    leap_count=$((leap_count+1))
done
echo ""