echo "Enter n:"
read n

for ((i=1; i<=n; i++))
do
  for ((k=1; k<=n-i; k++))
  do
    printf " "
  done

  for ((j=i; j>0; j--))
  do
    printf " *"
  done

  printf "\n"
done
