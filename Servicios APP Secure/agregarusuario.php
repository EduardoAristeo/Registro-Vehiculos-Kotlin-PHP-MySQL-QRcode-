<?
include_once("database.php");

	if( isset($_POST['PNombre']) && isset($_POST['PApellido']) && isset($_POST['SApellido']) && isset($_POST['Email']) && isset($_POST['Contraseña']) && isset($_POST['Username']))
	{
		$pnom=$_POST['PNombre'];
		$snom=$_POST['SNombre'];
		$pApe=$_POST['PApellido'];
		$sApe=$_POST['SApellido'];
		$email=$_POST['Email'];
		$contra=$_POST['Contraseña'];
		$username=$_POST['Username'];
		$db= new database();

		if($db->agregarusuario($pnom,$snom,$pApe,$sApe,$email,$contra,$username))
		{
			$respuesta['error']=false;
			$respuesta['mensaje']='Usuario agregado con Exito';
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