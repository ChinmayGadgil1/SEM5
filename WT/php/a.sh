i=1
while [ $i -lt  6 ] 
do

touch "$i.php"

i=$(( $i + 1 ))
done