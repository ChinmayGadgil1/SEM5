menu="1. date
2. ls
3. pwd
4. tree
5. exit menu
"
while [ true ]
do
    echo "Menu:"
    echo "$menu"
    echo "Enter your choice:"
    read choice
    case $choice in
        1) date ;;
        2) ls ;;
        3) pwd ;;
        4) tree ;;
        5) exit ;;
        *) echo "Invalid choice. Please try again." ;;
    esac
done