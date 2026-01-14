<?php
function fibonacci($n) {
    $first = 0;
    $second = 1;
    
    echo "Fibonacci Series: ";
    echo $first . " " . $second . " ";
    
    for($i = 2; $i < $n; $i++) {
        $third = $first + $second;
        echo $third . " ";
        $first = $second;
        $second = $third;
    }
}

// Generate first 10 numbers
fibonacci(10);
?>