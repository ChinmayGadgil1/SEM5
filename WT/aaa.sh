i=16
while [ $i -lt 26 ] 
do

touch "$i.html"

i=$(( $i + 1 ))
done