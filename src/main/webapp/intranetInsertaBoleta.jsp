<jsp:include page="intranetValida.jsp" />
<!DOCTYPE html>
<html lang="esS" >
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrapValidator.js"></script>
<script type="text/javascript" src="js/global.js"></script>
<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="css/dataTables.bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrapValidator.css" />

<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/dataTables.bootstrap.min.js"></script>
<link rel="stylesheet" href="css/dataTables.bootstrap.min.css"/>


<title>Sistemas - Jorge Jacinto Gutarra</title>
</head>
<body>
<jsp:include page="intranetCabecera.jsp" />
<div class="container">&nbsp;<br>&nbsp;<br>&nbsp;<br></div>

<div class="container">
	<h3>BOLETA - JORGE JACINTO</h3>	
	
			<input type="hidden" id="id_metodo"  class="form-control"/>
			<input type="hidden" id="id_elimina" class="form-control"/>
			<input type="hidden" id="id_cliente_id" class="form-control"/>
			<input type="hidden" id="id_producto_id" class="form-control"/>
	
			<div class="panel-group" id="steps">
				<div class="panel panel-default">
					<div class="panel-heading">CLIENTE</div>
						<div class="panel-body">
							<form  class="form-horizontal">
							<div class="form-group">
									<label class="col-lg-3 control-label">Buscar Cliente</label>
									<div class="col-lg-5">
										<button type="button" id="id_btnCliente" data-toggle='modal' class='btn btn-success' >....</button>
									</div>
							</div>
							<div class="form-group">
								<label class="col-lg-3 control-label">Apellido</label>
								<div class="col-lg-5">
									<input type="text"  id="id_cliente_apellido" class="form-control" readonly="readonly"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-3 control-label">Nombre</label>
								<div class="col-lg-5">
									<input type="text"id="id_cliente_nombre"  class="form-control"	readonly="readonly"/>
								</div>
							</div>
							</form>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">PRODUCTO</div>
						<div class="panel-body">
							<form  class="form-horizontal">
							<div class="form-group">
								<label class="col-lg-3 control-label">Buscar Producto</label>
								<div class="col-lg-5">
									<button type="button" id="id_btnProducto" data-toggle='modal' class='btn btn-success' >....</button>
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-3 control-label">Nombre</label>
								<div class="col-lg-5">
									<input type="text" name="nombreProducto" id="id_producto_nombre" class="form-control" readonly="readonly"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-3 control-label">Precio</label>
								<div class="col-lg-3">
									<input type="text" name="precio" id="id_producto_precio" class="form-control" readonly="readonly"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-3 control-label">Stock</label>
								<div class="col-lg-3">
									<input type="text" name="stock" id="id_producto_stock" class="form-control" readonly="readonly"/>
								</div>
							</div>							
							<div class="form-group">
								<label class="col-lg-3 control-label" for="id_reg_estado">Cantidad</label>
								<div class="col-lg-3">
									<input type="text" name="cantidad" id="id_cantidad" class="form-control"	placeholder="Ingrese la cantidad" onkeypress="return validarSoloNumerosEnteros(event);" />
								</div>
							</div>
							</form>
						</div>
				</div>
			</div>
			
			
			<div class="panel panel-default">
					<div class="panel-heading">BOLETA</div>
						<div class="panel-body">
							<div class="form-group">
								<div class="col-lg-9 col-lg-offset-3">
									<button type="button" id="id_btnAgregar" class="btn btn-primary">AGREGAR</button>
									<button type="button" id="id_btnRegistrar"  class="btn btn-primary">REGISTRA BOLETA</button>
								</div>
							</div>
							<div class="form-group">
								<div class="col-lg-12">
									<table id="id_table" class="table table-striped table-bordered">
										<thead>
											<tr>
												<th>ID</th>
												<th>Nombre</th>
												<th>Precio</th>
												<th>Cantidad</th>
												<th></th>
											</tr>
										</thead>
										<tbody>
											
										</tbody>
									</table>
								</div>
							</div>
					</div>
				</div>


<!-- El formulario de busqueda de Cliente (Modal) -->

  	 <div class="modal fade" id="idBuscaCliente" >
			<div class="modal-dialog" style="width: 60%; height: 60%">

				<div class="modal-content">
				<div class="modal-header" style="padding: 35px 50px">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4><span class="glyphicon glyphicon-ok-sign"></span> Busqueda de Cliente</h4>
				</div>
				<div class="modal-body" style="padding: 20px 10px;">
						    <div class="panel-group" id="steps">
		                        <div class="panel panel-default">
		                        			<form  class="form-horizontal">
											<div class="form-group">
													<label class="col-lg-3 control-label">
														Cliente 
													</label>   
													<div class="col-lg-5">
														<input id="id_txtCliente" class="form-control" name="cliente" type="text" />
													</div>
											</div>
											<div class="form-group">
												<div class="col-lg-12">
												<table id="id_table_cliente" class="table table-striped table-bordered">
														<thead>
															<tr>
																<th style="width: 15%">Id</th>
																<th style="width: 40%">Nombre</th>
																<th style="width: 35%">Apellido</th>
																<th style="width: 10%"></th>
															</tr>
														</thead>
														<tbody>

														</tbody>				
												</table>
												</div>
											</div>
											</form>
			                        </div>
		                    </div>
					</div>
			</div>
			</div>
		</div>

<!-- El formulario de busqueda de Producto (Modal) -->

  	 <div class="modal fade" id="idBuscaProducto" >
			<div class="modal-dialog" style="width: 60%">

				<div class="modal-content">
				<div class="modal-header" style="padding: 35px 50px">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4><span class="glyphicon glyphicon-ok-sign"></span> Busqueda de Producto</h4>
				</div>
				<div class="modal-body" style="padding: 20px 10px;">
						    <div class="panel-group" id="steps">
		                        <div class="panel panel-default">
		                        		<form  class="form-horizontal">
											<div class="form-group">
													<label class="col-lg-3 control-label">
														Producto 
													</label>   
													<div class="col-lg-5">
														<input id="id_txtProducto" class="form-control" name="producto" type="text" />
													</div>
											</div>
											<div class="form-group">
												<div class="col-lg-12">
												<table id="id_table_producto" class="table table-striped table-bordered" >
														<thead>
															<tr>
																<th style="width: 15%">Id</th>
																<th style="width: 45%">Nombre</th>
																<th style="width: 15%">Precio</th>
																<th style="width: 15%">Stock</th>
															</tr>
														</thead>
														<tbody>

														</tbody>				
												</table>
												</div>
											</div>
										</form>	
		                        </div>
		                    </div>
				</div>
			</div>
			</div>
		</div>
</div>
<script type="text/javascript">
	
	//Al pulsar el botón cliente
	$("#id_btnCliente").click(function (){
		$("#idBuscaCliente").modal("show");
	});

	//Al pulsar el botón producto
	$("#id_btnProducto").click(function (){
		$("#idBuscaProducto").modal("show");
	});
	
	//Al escribir en la caja de texto del cliente
	$("#id_txtCliente").keyup( function (e){
		var var_cliente = $("#id_txtCliente").val();
		console.log(">> " + var_cliente);
		
		//limpiar la tabla
		$("#id_table_cliente tbody").remove();
		
		//Se añade los clientes a la tabla
		$.getJSON("buscaCliente",{"filtro":var_cliente}, function (data){
				
		});
		
	});
	
	
	//Al escribir en la caja de texto del cliente
	$("#id_txtProducto").keyup( function (e){
		var var_producto = $("#id_txtProducto").val();
		console.log(">> " + var_producto);
		
		//limpiar la tabla
		$("#id_table_producto tbody").remove();
		
		//Se añade los clientes a la tabla
		$.getJSON("buscaProducto",{"filtro":var_producto}, function (data){
				
		});
		
	});
	
	
	//Se anula el enter
	$(document).on("keypress", "form", function(event) { 
	    return event.keyCode != 13;
	});
	
	//Al pulsar selecciona cliente
	function f_seleccione_cliente(id,nombre,apellido){
		$("#id_cliente_id").val(id);
		$("#id_cliente_nombre").val(nombre);
		$("#id_cliente_apellido").val(apellido);
		$("#idBuscaCliente").modal("hide");
	}
	
	//Al pulsar selecciona producto
	function f_seleccione_producto(id,nombre,precio,stock){
		$("#id_producto_id").val(id);
		$("#id_producto_nombre").val(nombre);
		$("#id_producto_precio").val(precio);
		$("#id_producto_stock").val(stock);
		$("#idBuscaProducto").modal("hide");
	}
	
	$("#id_btnAgregar").click(function() {
		var id = $("#id_producto_id").val();
		var nom = $("#id_producto_nombre").val();
		var pre = $("#id_producto_precio").val();
		var can = $("#id_cantidad").val();
        
	});

	$("#id_btnRegistrar").click(function() {
		var id = $("#id_cliente_id").val();
        
	});
	
	//Al pulsar el botón eliminar
	function f_elimina_seleccion(id){	
		

	}
	
	
	//Solo numeros en caja de texto
	function validarSoloNumerosEnteros(e) { // 1
		tecla = (document.all) ? e.keyCode : e.which; // 2
		if (tecla == 8)	return true; // 3
		patron = /[0-9]/;// Solo acepta números
		te = String.fromCharCode(tecla); // 5
		return patron.test(te); // 6
	}

	function agregarGrilla(lista){
		 $('#id_table').DataTable().clear();
		 $('#id_table').DataTable().destroy();
		 $('#id_table').DataTable({
				data: lista,
				language: IDIOMA,
				searching: true,
				ordering: true,
				processing: true,
				pageLength: 10,
				lengthChange: false,
				info:true,
				scrollY: 305,
		        scroller: {
		            loadingIndicator: true
		        },
				columns:[
					{data: "idProducto",className:'text-center'},
					{data: "nombre",className:'text-center'},
					{data: "precio",className:'text-center'},
					{data: "cantidad",className:'text-center'},
					{data: function(row, type, val, meta){
						return '<button type="button" class="btn btn-danger btn-sm"  onClick="f_elimina_seleccion(\'' + row.idProducto +'\');" >Eliminar</button>';
					},className:'text-center'},
				]                                     
		    });
	}
	
</script>
</body>
</html>



