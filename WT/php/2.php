<?php
// This is a single-line comment

/*
  This is a multi-line comment
  Structure of PHP program
*/

// 1. PHP Opening tag
// 2. Variable declaration
$student_name = "John Doe";
$age = 20;
$grade = "A";

// 3. Function definition
function displayStudentInfo($name, $age, $grade) {
    echo "<h2>Student Information</h2>";
    echo "Name: " . $name . "<br>";
    echo "Age: " . $age . "<br>";
    echo "Grade: " . $grade . "<br>";
}

// 4. Function call
displayStudentInfo($student_name, $age, $grade);

// 5. Conditional statement
if ($age >= 18) {
    echo "<p>Student is an adult.</p>";
} else {
    echo "<p>Student is a minor.</p>";
}

// 6. Loop example
echo "<p>Counting from 1 to 3:</p>";
for ($i = 1; $i <= 3; $i++) {
    echo $i . " ";
}

// 7. PHP closing tag (optional in pure PHP files)
?>