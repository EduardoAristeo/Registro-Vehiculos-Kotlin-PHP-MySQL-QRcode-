<?

	class database
	{
		protected $server="37.60.232.104"; 
		protected $db="jpsistem_mgf"; 
		protected $user="jpsistem_mgf"; 	
		protected $pass="Moviles*79";
		protected $cn=null; 
		function __construct()
		{
		$this->cn=new mysqli($this->server,$this->user,$this->pass);
		mysqli_select_db($this->cn,$this->db);
	}

	function sacarID($username){
		$stmt=$this->cn->prepare(" select idUsuario from Usuario where "." username=? ");
		$stmt->bind_param("s",$username);
		$stmt->execute();
		#$result = $stmt->get_result();
		$renglon2=mysqli_fetch_array($stmt->get_result());
		return $renglon2[0];
	}

	function agregarusuario($pnom,$snom,$pApe,$sApe,$email,$contra,$username)
	{		
		$stmt=$this->cn->prepare(" insert into Usuario (primerNombre,segundoNombre,primerApellido,segundoApellido,email,contraseña,username) values ( "." ?, ". " ?, "." ?, "." ?, "." ?, "." ?, "." ?) ");
		$stmt->bind_param("sssssss",$pnom,$snom,$pApe,$sApe,$email,$contra,$username);
		if($stmt->execute())
		return true;
		return false;
	}

	function autentificarV($user,$pass)
	{
		$stmt=$this->cn->prepare(" select Usuario,Contraseña from Vigilante where "." Usuario=? "." and "." Contraseña=? ");
		$stmt->bind_param("ss",$user,$pass);
		$stmt->execute();
		$result = $stmt->get_result();
		if($result->num_rows>0)
			return true;
		return false;
	}

	function autentificarid($username){
		$stmt=$this->cn->prepare(" Select primerNombre,primerApellido,segundoApellido from Usuario where "."idUsuario=? ");
		$stmt->bind_param("s",$username);
		$stmt->execute();
	$result = $stmt->get_result();
		if($result->num_rows>0)
			return true;
		return false;
	}

	function sacarPrimerNombre($username){
		$stmt=$this->cn->prepare(" select primerNombre from Usuario where "." idUsuario=? ");
		$stmt->bind_param("s",$username);
		$stmt->execute();
		#$result = $stmt->get_result();
		$renglon2=mysqli_fetch_array($stmt->get_result());
		return $renglon2[0];
	}

	function sacarPrimerApellido($username){
		$stmt=$this->cn->prepare(" select primerApellido from Usuario where "." idUsuario=? ");
		$stmt->bind_param("s",$username);
		$stmt->execute();
		#$result = $stmt->get_result();
		$renglon2=mysqli_fetch_array($stmt->get_result());
		return $renglon2[0];
	}

	function sacarSegundoApellido($username){
		$stmt=$this->cn->prepare(" select primerApellido from Usuario where "." idUsuario=? ");
		$stmt->bind_param("s",$username);
		$stmt->execute();
		#$result = $stmt->get_result();
		$renglon2=mysqli_fetch_array($stmt->get_result());
		return $renglon2[0];
	}

function autentificarU($user,$pass)
	{
		$stmt=$this->cn->prepare("select username,contraseña from Usuario where username=? and
		contraseña=?");
		$stmt->bind_param("ss",$user,$pass);
		$stmt->execute();
		$result = $stmt->get_result();
		if($result->num_rows>0)
			return true;
		return false;
	}

function insertVehiculo($idUsuario,$Marca,$Color,$Caracteristicas,$MatriculaQR){
	$stmt=$this->cn->prepare(" insert into Vehiculo ( idUsuario,marca,color,caracteristicas,matriculaQR) values ( "." ?, "." ?, "." ?, "." ?, "."?) ");
		$stmt->bind_param("sssss",$idUsuario,$Marca,$Color,$Caracteristicas,$MatriculaQR);
		if($stmt->execute())
		return true;
		return false; 
}

function DatosVehiculo($MatriculaQR)
	{
		$stmt=$this->cn->prepare(" select * from Vehiculo where matriculaQR=? ");
		$stmt->bind_param("s",$MatriculaQR);
		$stmt->execute();
		$result = $stmt->get_result();
		if($result->num_rows>0)
			return true;
		return false;
	}

function SacarMarca($MatriculaQR){
		$stmt=$this->cn->prepare(" select marca from Vehiculo where matriculaQR=? ");
		$stmt->bind_param("s",$MatriculaQR);
		$stmt->execute();
		#$result = $stmt->get_result();
		$renglon2=mysqli_fetch_array($stmt->get_result());
		return $renglon2[0];
}

function SacarColor($MatriculaQR){
		$stmt=$this->cn->prepare(" select color from Vehiculo where  matriculaQR=? ");
		$stmt->bind_param("s",$MatriculaQR);
		$stmt->execute();
		#$result = $stmt->get_result();
		$renglon2=mysqli_fetch_array($stmt->get_result());
		return $renglon2[0];
}

function SacarCaracteristicas($MatriculaQR){
		$stmt=$this->cn->prepare(" select caracteristicas from Vehiculo where matriculaQR=? ");
		$stmt->bind_param("s",$MatriculaQR);
		$stmt->execute();
		#$result = $stmt->get_result();
		$renglon2=mysqli_fetch_array($stmt->get_result());
		return $renglon2[0];
}

function SacarNombre($MatriculaQR){
		$stmt=$this->cn->prepare(" Select primerNombre a 
									from Vehiculo a
									INNER JOIN Usuario b
									on(a.idUsuario=b.idUsuario)
									where matriculaQR=? ");

		$stmt->bind_param("s",$MatriculaQR);
		$stmt->execute();
		#$result = $stmt->get_result();
		$renglon2=mysqli_fetch_array($stmt->get_result());
		return $renglon2[0];
}

function SacarApellido($MatriculaQR){
		$stmt=$this->cn->prepare(" Select primerApellido a 
									from Vehiculo a
									INNER JOIN Usuario b
									on(a.idUsuario=b.idUsuario)
									where matriculaQR=? ");

		$stmt->bind_param("s",$MatriculaQR);
		$stmt->execute();
		#$result = $stmt->get_result();
		$renglon2=mysqli_fetch_array($stmt->get_result());
		return $renglon2[0];

}

/*function arregloVehiculo($idUsuario){
	$stmt=$this->cn->prepare(" select Marca from Vehiculo where idUsuario= "." ?")
		$stmt->bind_param("s",$idUsuario);
		$stmt->execute();
		$result=$stmt->get_result();
		return $stmt->fetchAll($result)
}*/

	public function desconectar()
	{
		mysqli_close($this->ln);
	}

	}
?>