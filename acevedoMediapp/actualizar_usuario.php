<?php

include 'conexion.php';
$json=array();

/*if(isset($_POST["numDocumento"])){*/
    $id = $_POST['id'];
    $correo=$_POST['email'];
    $nombres=$_POST['nombres'];
    $apPat=$_POST['apPaterno'];
    $apMat=$_POST['apMaterno'];
    $celular=$_POST['celular'];
    $f_nacimiento=$_POST['f_nacimiento'];
    $genero=$_POST['genero'];
    $tipo_documento=$_POST['t_documento'];
    $numDocumento=$_POST['numDocumento'];
    $img_url=$_POST['imagen'];



    $url="http://192.168.1.46/acevedoMediapp/";
    $path ="imagenes/usuarios/$numDocumento.jpg";
    $imagen = $url.$path;

    file_put_contents($path,base64_decode($img_url));
    $byteArchivo = file_get_contents($path);

    $consulta = "UPDATE user SET 
    documento = '$numDocumento', 
    nombres = '$nombres', 
    apPaterno = '$apPat', 
    apMaterno = '$apMat', 
    celular = '$celular', 
    f_nacimiento =' $f_nacimiento', 
    genero = '$genero', 
    correo = '$correo', 
    tipo_documento = '$tipo_documento',
    img_url = '$imagen' 
    WHERE id = '$id' ";
    
    echo $consulta;
    $resultado_insert=mysqli_query($conexion,$consulta);

    

    if($resultado_insert){
        $consulta="SELECT * FROM user WHERE id = '$id' ";
        $resultado=mysqli_query($conexion,$consulta);
        
        if($registro=mysqli_fetch_array($resultado)){
            $json['User'][]=$registro;
        }
        mysqli_close($conexion);
        header('Content-Type: application/json');
        echo json_encode($json);

    }
    else{
        $resulta["id"]=0;
        $resulta["user"]='No registra';
        header('Content-Type: application/json');
        echo json_encode($json);

    }


?>