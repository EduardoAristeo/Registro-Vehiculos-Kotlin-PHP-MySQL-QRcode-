<?
include_once("database.php");

	if(isset($_POST['idUsuario']) && isset($_POST['Marca']) && isset($_POST['Color']) && isset($_POST['Caracteristicas']) && isset($_POST['MatriculaQR']))
	{
		$idUsuario=$_POST['idUsuario'];
		$Marca=$_POST['Marca'];
		$Color=$_POST['Color'];
		$Caracteristicas=$_POST['Caracteristicas'];
		$MatriculaQR=$_POST['MatriculaQR'];
		$db= new database();

		if($db->insertVehiculo($idUsuario,$Marca,$Color,$Caracteristicas,$MatriculaQR))
		{
			$respuesta['error']=false;
			$respuesta['mensaje']='Vehiculo agregado con Exito';
		}
		else{
			$respuesta['error']=true;
			$respuesta['mensaje']='Error en la insersion';	
		}
	}
	else{
		$respuesta['error']=true;
		$respuesta['mensaje']='Faltan parametros';
	}

	echo json_encode($respuesta);
?>