<?
	include_once("database.php");
	if( isset($_POST['matriculaQR']))
	{
		$MatriculaQR=$_POST['matriculaQR'];
		
		$db= new database();

		if($db->DatosVehiculo($MatriculaQR))
		{
			$respuesta['error']=false;
			$respuesta['mensaje']="Caracteristicas encotradas";

			$respuesta['marca']=$db->SacarMarca($MatriculaQR);
			$respuesta['color']=$db->SacarColor($MatriculaQR);
			$respuesta['caracteristicas']=$db->SacarCaracteristicas($MatriculaQR);
			$respuesta["nombre"]=$db->SacarNombre($MatriculaQR);
			$respuesta["Apellido"]=$db->SacarApellido($MatriculaQR);
			echo  json_encode($respuesta);
		}
		else{
			$respuesta['error']=true;
			$respuesta['mensaje']='Error en MatriculaQR';	
		}
	}
	else{
		$respuesta['error']=true;
		$respuesta['mensaje']='Faltan parametros';
	}

	echo  json_encode($respuesta);

?>