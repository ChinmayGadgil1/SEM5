#!/bin/bash

echo "Enter a fruit name:"
read fruit

case $fruit in
  apple)
    echo "Apples are red or green."
    ;;
  banana)
    echo "Bananas are yellow."
    ;;
  mango)
    echo "Mangoes are sweet and tropical."
    ;;
  *)
    echo "I don't know about that fruit."
    ;;
esac
