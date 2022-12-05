<?php

include 'conexion.php';
$json=array();

if(isset($_GET["id_cliente"])){

    $id_cliente=$_GET['id_cliente'];
    $longitud=$_GET['longitud'];
    $latitud=$_GET['latitud'];
    $persona_recepcion=$_GET['persona_recepcion'];
    $celular=$_GET['celular'];
    $total=$_GET['total'];
    $id_producto=$_GET['id_producto'];
    $cantidad=$_GET['cantidad'];
    $fecha=date("Y-m-d");
    $hora=date("H:i:s");

    $consulta = "INSERT INTO venta(id_cliente, longitud, latitud, persona_recepcion, celular, total, fecha, hora) VALUES 
    ('{$id_cliente}', '{$longitud}', '{$latitud}', '{$persona_recepcion}', '{$celular}', '{$total}', '{$fecha}', '{$hora}')";
    $resultado_insert=mysqli_query($conexion,$consulta);

    if($resultado_insert){
        $sql="SELECT LAST_INSERT_ID() AS venta";
        $nuevo_resultado = mysqli_query($conexion,$sql);
        if($registro=mysqli_fetch_array($nuevo_resultado)){
            $id_pedido = $registro['venta'];
        }

        $consulta = "INSERT INTO det_venta (`id`, `id_pedido`, `id_producto`, `cantidad`) VALUES (NULL, '".$id_pedido."', '".$id_producto."', '".$cantidad."')";
        $resultado=mysqli_query($conexion,$consulta);

        $consulta="SELECT * FROM det_venta WHERE id_pedido = '{$id_pedido}'";
            $resultado=mysqli_query($conexion,$consulta);
            
            if($registro=mysqli_fetch_array($resultado)){

                //agrega una lista de espera de entrega de pedidos
                $consulta1 = "INSERT INTO `pedidos_entregados` (`id`, `id_pedido`, `id_repartidor`, `img_url`, `estado`) VALUES (NULL, '$id_pedido', NULL, NULL, '0')";
                mysqli_query($conexion,$consulta1);
                //fin de lista de espera de entrega de pedidos

                $json['det_venta'][]=$registro;
            }
            mysqli_close($conexion);
            echo json_encode($json);

    }
    else{
        $resulta["id"]=0;
        $resulta["venta"]='No registra';
        header('Content-Type: application/json');
        echo json_encode($json);

    }
}else{
    $resulta["id"]=0;
    $resulta["venta"]='No registra';
    header('Content-Type: application/json');
    echo json_encode($json);

}
?>