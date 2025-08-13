#!/bin/bash

menu="1. date
2. ls
3. pwd
4. tree
5. exit menu
6. chmod (numeric mode)
"

while true
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
        6) 
            echo "Enter the filename:"
            read filename
            if [ -e "$filename" ]; then
                echo "Enter permission number (e.g., 755, 644, 765):"
                read perms
                chmod "$perms" "$filename"
                echo "Permissions set to $perms for '$filename'"
            else
                echo "File '$filename' does not exist."
            fi
            ;;
        *) echo "Invalid choice. Please try again." ;;
    esac
done
