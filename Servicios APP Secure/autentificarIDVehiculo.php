<?
	include_once("database.php");
	if( isset($_POST['idUsuario']))
	{
		$user=$_POST['idUsuario'];
		
		$db= new database();

		if($db->autentificarid($user))
		{
			$respuesta['error']=false;

			$respuesta['primerNombre']=$db->sacarPrimerNombre($user);
			$respuesta['primerApellido']=$db->sacarPrimerApellido($user);
			$respuesta['segundoApellido']=$db->sacarSegundoApellido($user);
			echo  json_encode($respuesta);
		}
		else{
			$respuesta['error']=true;
			$respuesta['mensaje']='Error en Usuario';	
		}
	}
	else{
		$respuesta['error']=true;
		$respuesta['mensaje']='Faltan parametros';
	}

	echo  json_encode($respuesta);

?>