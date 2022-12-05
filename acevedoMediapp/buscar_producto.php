<?PHP
include 'conexion.php';

$json=array();
if(isset($_GET["texto"])){
		$url="http://192.168.1.46/acevedoMediapp/";
        $texto = $_GET['texto'];
		$consulta="select * from productos where nombre like '%{$texto}%' ";
		$resultado=mysqli_query($conexion,$consulta);
		
		while($registro=mysqli_fetch_array($resultado)){
			$result["id"]=$registro['id'];
			$result["id_categoria"]=$registro['id_categoria'];
			$result["codigo"]=$registro['codigo'];
			$result["nombre"]=$registro['nombre'];
			$result["precio_venta"]=$registro['previo_venta'];
			$result["stock"]=$registro['stock'];
			$result["presentacion"]=$registro['presentacion'];
			$result["estado"]=$registro['estado'];
			$result["descripcion"]=$registro['descripcion'];
			$result["ruta_imagen"]=$url.$registro['ruta_imagen'];
			$json['productos'][]=$result;
			//echo $registro['id'].' - '.$registro['nombre'].'<br/>';
		}
		mysqli_close($conexion);
		header('Content-Type: application/json');
		echo json_encode($json);
    }else{
        $resulta["id"]=0;
        $resulta["productos"]='No registra';
        header('Content-Type: application/json');
        echo json_encode($json);
    }
?>