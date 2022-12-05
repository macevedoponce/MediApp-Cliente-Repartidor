<?php

include 'conexion.php';
$json=array();



if(isset($_GET["numDocumento"])){
    $correo=$_GET['correo'];
    $password=$_GET['password'];
    $tipo_user=$_GET['tipo_user'];
    $nombres=$_GET['nombres'];
    $apPat=$_GET['apPat'];
    $apMat=$_GET['apMat'];
    $celular=$_GET['celular'];
    $f_nacimiento=$_GET['f_nacimiento'];
    $genero=$_GET['genero'];
    $tipo_documento=$_GET['tipo_documento'];
    $numDocumento=$_GET['numDocumento'];
    $img_url=$_GET['imagen'];

    $options = [
        'cost' => 12,
    ];
 
    $passHash =  password_hash($password,PASSWORD_BCRYPT,$options);
    $url="http://192.168.1.46/acevedoMediapp/";
    $imagen = $url.$img_url;

    $consulta = "INSERT INTO user (`id`, `documento`, `nombres`, `apPaterno`, `apMaterno`, `celular`, `f_nacimiento`, `genero`, `correo`, `password`, `tipo_user`, `tipo_documento`,`img_url`) VALUES (NULL, '".$numDocumento."', '".$nombres."', '".$apPat."', '".$apMat."', '".$celular."', '".$f_nacimiento."', '".$genero."', '".$correo."', '".$passHash."', '".$tipo_user."', '".$tipo_documento."', '$imagen')";
    $resultado_insert=mysqli_query($conexion,$consulta);

    if($resultado_insert){
        $consulta="SELECT * FROM user WHERE documento = '{$numDocumento}'";
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
}else{
    $resulta["id"]=0;
    $resulta["user"]='No registra';
    header('Content-Type: application/json');
    echo json_encode($json);

}
?>