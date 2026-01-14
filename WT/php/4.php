<?php
$servername = "localhost";
$username = "root";
$password = "";
$database = "";

$conn = mysqli_connect($servername, $username, $password, $database);

if(!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}
echo "Connected successfully";

$sql ="create database testWT";
if(mysqli_query($conn, $sql)){
    echo "Database created successfully";
} else {
    echo "Error creating database: " . mysqli_error($conn);
}

$sql = "use testWT";
if(mysqli_query($conn, $sql)){
    echo "Using database testWT";
} else {
    echo "Error using database: " . mysqli_error($conn);
}

$sql = "create table student(
    id int(6) primary key,
    name varchar(30) not null,
    age int(3) not null
)";

if(mysqli_query($conn, $sql)){
    echo "Table student created successfully";
} else {
    echo "Error creating table: " . mysqli_error($conn);
}

$sql="insert into student (id, name, age) values (1, 'Alice', 20), (2, 'Bob', 22), (3, 'Charlie', 19)";
if(mysqli_query($conn, $sql)){
    echo "Records inserted successfully";
} else {
    echo "Error inserting records: " . mysqli_error($conn);
}


mysqli_close($conn);
?>