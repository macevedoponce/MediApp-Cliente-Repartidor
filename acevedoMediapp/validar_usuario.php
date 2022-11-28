
<?php

include 'conexion.php';

$json=array();

if(isset($_GET["correo"])){

    $correo=$_GET['correo'];
    $password=$_GET['password'];
    $tipo_user=$_GET['tipo_user'];

    $sentencia=$conexion->prepare("SELECT * FROM user WHERE correo=? and tipo_user=?");
    $sentencia->bind_param('ss',$correo,$tipo_user);
    $sentencia->execute();
    $resultado = $sentencia->get_result();
    $fila = $resultado->fetch_assoc();
    if($fila && password_verify($password,$fila['password'])){
       //echo json_encode($fila,JSON_UNESCAPED_UNICODE);
       $json['usuario'][]=$fila;
      }
    header('Content-Type: application/json');
    echo json_encode($json);

    $sentencia->close();
    $conexion->close();
} 


?>