    <?php
    include_once 'conexion.php';

    $idUsuario = $_POST["usuario"];
    $respuesta = array();

    
    
    // Conectarse al servidor y seleccionar base de datos.
    $con = mysqli_connect("$host", "$username", "$password")or die("cannot connect server ");
    mysqli_select_db($con,"$db_name")or die("cannot select DB");
    $sql="SELECT * FROM Vehiculo where matriculaQR=".$idUsuario;
    $result=mysqli_query($con,$sql);
    $row = mysqli_fetch_array($result)
    // Array temporal para crear una sola categoría
    
     $respuesta["Marca"]=$row[2];
     $respuesta["Color"]=$row[3];
     $respuesta["Caracteristicas"]=$row[4];

    // Push categoría a final json array
    
    // Mantener el encabezado de respuesta a json
    #header('Content-Type: application/json');
    //Escuchando el resultado de json
    echo json_encode($respuesta);
    ?>