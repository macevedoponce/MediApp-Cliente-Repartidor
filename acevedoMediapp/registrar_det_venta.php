<?php

include 'conexion.php';
$json=array();

if(isset($_GET["id_pedido"])){

    $id_pedido=$_GET['id_pedido'];
    $id_producto=$_GET['id_producto'];
    $cantidad=$_GET['cantidad'];


    $consulta = "INSERT INTO det_venta (`id`, `id_pedido`, `id_producto`, `cantidad`) VALUES 
    (NULL, '".$id_pedido."', '".$id_producto."', '".$cantidad."')";
    $resultado_insert=mysqli_query($conexion,$consulta);

    if($resultado_insert){
        $consulta="SELECT * FROM det_venta WHERE id_pedido = '{$id_pedido}'";
        $resultado=mysqli_query($conexion,$consulta);
        
        if($registro=mysqli_fetch_array($resultado)){
            $json['det_venta'][]=$registro;
        }
        mysqli_close($conexion);
        header('Content-Type: application/json');
        echo json_encode($json);

    }
    else{
        $resulta["id"]=0;
        $resulta["det_venta"]='No registra';
        header('Content-Type: application/json');
        echo json_encode($json);

    }
}else{
    $resulta["id"]=0;
    $resulta["det_venta"]='No registra';
    header('Content-Type: application/json');
    echo json_encode($json);

}
?>