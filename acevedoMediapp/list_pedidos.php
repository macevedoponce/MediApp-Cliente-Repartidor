<?PHP
include 'conexion.php';

$json=array();
if(isset($_GET["estado"])){

		$url="http://192.168.1.46/acevedoMediapp/";

        $estado = $_GET['estado'];
        
		$consulta="SELECT * FROM venta V inner JOIN det_venta D, productos P, pedidos_entregados E
        where V.id = D.id_pedido 
        and D.id_producto = P.id 
        and V.id = E.id_pedido
        and E.estado = '{$estado}'";
		
		$resultado=mysqli_query($conexion,$consulta);
		
		while($registro=mysqli_fetch_array($resultado)){
			$result["id"]=$registro['id_pedido'];
			$result["longitud"]=$registro['longitud'];
			$result["latitud"]=$registro['latitud'];
			$result["persona_recepcion"]=$registro['persona_recepcion'];
			$result["celular_persona_recepcion"]=$registro['celular'];
			$result["total"]=$registro['total'];
			$result["fecha"]=$registro['fecha'];
			$result["cantidad"]=$registro['cantidad'];
			$result["pre_unit"]=$registro['previo_venta'];
			$result["nombreProducto"]=$registro['nombre'];
            $result["estado"]=$registro['estado'];
			$result["img_url"]=$url.$registro['ruta_imagen'];
			$json['pedidos'][]=$result;
			//echo $registro['id'].' - '.$registro['nombre'].'<br/>';
		}
		mysqli_close($conexion);
		header('Content-Type: application/json');
		echo json_encode($json);
    }else{
        $resulta["id"]=0;
        $resulta["pedidos_cliente"]='No registra';
        header('Content-Type: application/json');
        echo json_encode($json);
    }
?>