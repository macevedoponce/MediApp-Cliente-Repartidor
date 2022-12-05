<?php

include 'conexion.php';
$json=array();
  
    if($_POST['id_repartidor']){
        $id_repartidor = $_POST['id_repartidor'];
        $id_pedido = $_POST['id_pedido'];
        $estado=$_POST['estado'];
    
        $consulta = "UPDATE pedidos_entregados SET 
        id_repartidor = '$id_repartidor', 
        estado = '$estado'
        WHERE id_pedido = $id_pedido ";
        
    }else{

        $id_pedido = $_POST['id_pedido'];
        $estado=$_POST['estado'];
        $img_url=$_POST['imagen'];

        $path ="imagenes/entregas/$id_pedido.jpg";
        $imagen = $path;
    
        file_put_contents($path,base64_decode($img_url));
        $byteArchivo = file_get_contents($path);

        $consulta = "UPDATE pedidos_entregados SET img_url = '$path', estado = '$estado'  WHERE id_pedido = '$id_pedido' ";
    }

    $resultado_insert=mysqli_query($conexion,$consulta);

    

    if($resultado_insert){
        $consulta="SELECT * FROM pedidos_entregados WHERE id_pedido = '$id_pedido' ";
        $resultado=mysqli_query($conexion,$consulta);
        
        if($registro=mysqli_fetch_array($resultado)){
            $json['pedido'][]=$registro;
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