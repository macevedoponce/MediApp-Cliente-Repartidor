<?PHP
include 'conexion.php';

$json=array();

		$url="http://192.168.1.46/acevedoMediapp/";

		$consulta="select * from categorias";
		$resultado=mysqli_query($conexion,$consulta);
		
		while($registro=mysqli_fetch_array($resultado)){
			$result["id"]=$registro['id'];
			$result["nombre"]=$registro['nombre'];
			$result["estado"]=$registro['estado'];
			$result["ruta_imagen"]=$url.$registro['imagen_url'];
			$json['categorias'][]=$result;
			//echo $registro['id'].' - '.$registro['nombre'].'<br/>';
		}
		mysqli_close($conexion);
		header('Content-Type: application/json');
		echo json_encode($json);
		
?>