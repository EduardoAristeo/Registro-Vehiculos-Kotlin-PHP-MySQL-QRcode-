<?
	include_once("database.php");
	if( isset($_POST['usuario']) && isset($_POST['password'] ))
	{
		$user=$_POST['usuario'];
		$pass=$_POST['password'];
		$db= new database();

		if($db->autentificarU($user,$pass))
		{
			$respuesta['error']=false;
			$respuesta['mensaje']=$db->sacarID($user);
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