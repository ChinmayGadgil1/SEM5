<?php
// Example 1: foreach with indexed array
$colors = array("Red", "Green", "Blue", "Yellow");

echo "Colors:<br>";
foreach ($colors as $color) {
    echo $color . "<br>";
}

echo "<br>";

// Example 2: foreach with associative array
$student = array(
    "name" => "John Doe",
    "age" => 20,
    "course" => "Computer Science",
    "grade" => "A"
);

echo "Student Information:<br>";
foreach ($student as $key => $value) {
    echo ucfirst($key) . ": " . $value . "<br>";
}

echo "<br>";

// Example 3: foreach with multidimensional array
$employees = array(
    array("name" => "Alice", "salary" => 50000),
    array("name" => "Bob", "salary" => 55000),
    array("name" => "Charlie", "salary" => 60000)
);

echo "Employee Salaries:<br>";
foreach ($employees as $employee) {
    echo $employee['name'] . " - $" . $employee['salary'] . "<br>";
}
?>