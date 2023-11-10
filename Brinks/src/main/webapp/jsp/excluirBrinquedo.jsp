<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="br.edu.exemplo.model.Brinquedo"%>
<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<meta charset="UTF-8">
		<title>Brinks</title>
		<style>
			.topnav {
				background-color:#fc324e;		
				position: fixed;
    			top: 0;
    			left: 0;
    			height: 100px;
    			width: 100%;
    			outline: 4px solid #000;
			}	

        
	        .topnav img {
	            position: fixed;
	            top: -25px;
	            left: 60px;
	            height: 150px;
	            width: auto;
	        }
	        
	        .topnav h1 {           
	            color: white;
	            text-align: center;
	            text-shadow: 3px 2px 0 #000;	            
	            font-family: 'Courier New', Courier, monospace;
	            font-size: 35px;
	            padding: 10px;
	        }
	        
	        .sidebar {
	            background: linear-gradient(to bottom, #fc324e, #2d94f5); 
	            position: fixed;
	            top: 102px;
	            left: 0;
	            height: 100%;
	            width: 275px;
	            outline: 2px solid #000;
	            overflow-x: hidden;
	        }
	        
	        .sidebar h2 {
	            padding: 15px 25px;
	            margin-top: 2px;
	            margin-bottom: 0px;
	            text-align: center;
	            text-shadow: 3px 2px 0 #000;
	            font-size: 30px;
	          	font-family: Arial, Helvetica, sans-serif;
	            color: #fff;
	            background-color: #ffd351;
	            outline: 3px solid #000;
	        }
	        
	        .sidebar a {
	            padding: 15px 25px;
	            text-decoration: none;
	            text-shadow: 2px 2px 0 #000;	            
	            font-size: 20px;
	            font-family: Arial, Helvetica, sans-serif;
	            color: white;
	            display: block;
	            outline: 1px solid #000;	            
	        }
	        
	        .sidebar a:hover {
	            background-color: #2d94f5;
	        }
	        
	        .sidebar a[href="ServletBrinquedo?cmd=listar"] {
            	background-color: #2d94f5;
	        }
	
	        .content {
	            margin-left: 300px;
	            margin-top: 100px;
	            padding: 20px;
	           	text-decoration: none;
	            font-family: Arial, Helvetica, sans-serif;
	        }
	        
	     	.content h2{
	            font-size: 26px;
	        }
	
			table {
	            border-collapse: collapse;
	            width: 100%;
	            margin-top: 40px;
	        }
	
	        table th {
	            background-color: #fff;
	            text-align: center;
	            font-size: 30px;
	            padding: 20px;
	        }
	        
	        table td {
	        	height: 50px;
	        	padding: 10px;
	        	font-size: 15px;
	        	font-weight: bold;
	        }
	        
			table tr:nth-child(odd) td {
			    background:#eeeeee;
			}
			table tr:nth-child(even) td {
			    background:#fff;
			}
			
	        input[type=text] {
				margin-left: 10px;
				width: 97%;
				font-size: 18px;
			}
			
			input[type=number] {
				margin-left: 40px;
				width: 95%;
				font-size: 18px;
			}
					        
			input[type=file] {
				margin-left: 10px;
				font-size: 18px;
			}
			
	        input[type=submit] {
				font-size: 18px;
	        	padding: 10px 30px;
	        	background: #ffd351;
	        	border: 0 none;
		        cursor: pointer;
		        border-radius: 5px;
	      	}				
			
			select {
				margin-left: 10px;
				font-size: 18px;
			}
			
			.td-id {
				color:blue;
				font-weight:bold;
				margin-left: 10px;
				width: 97%;
				font-size: 18px;
			}								
				
		</style>
	</head>
	
	<body>
		<div class="topnav">
			<img src="images/logo.png" alt="Logo">
			<h1>Brinks</h1>
		</div>
	
	    <div class="sidebar">
	        <h2>Menu Principal</h2>
	        <a href="ServletBrinquedo?cmd=listarDestaques">Home</a>
	        <a href="html/categorias.html">Catálogo de Brinquedos</a>
	        <a href="ServletBrinquedo?cmd=listar">Administração</a>
	        <a href="html/sobre.html">Sobre a Equipe</a>
	    </div>
	    
	    <div class="content">
	    	<h2>Catálogo de Brinquedos :: Excluir Brinquedo</h2>
	    	<hr>
		
		<jsp:useBean id="brinquedo" scope="session" class="br.edu.exemplo.model.Brinquedo" />
		<form action="ServletBrinquedo?cmd=excluir" method="post" enctype="multipart/form-data">
				<table>
					<tr>
						<td>CÓDIGO:</td>
						<td><input type="text" class="td-id" size="60" name="txtId"
							value="<%=brinquedo.getId()%>" readonly="readonly" /></td>
					</tr>
					<tr>
						<td>DESCRIÇÃO:</td>
						<td><input type="text" size="60" name="txtDescricao"
							value="<%=brinquedo.getDescricao()%>" readonly="readonly" /></td>
					</tr>
					<tr>
						<td>CATEGORIA:</td>
						<td><input type="text" size="60" name="txtCategoria"
							value="<%=brinquedo.getCategoria()%>" readonly="readonly" /></td>
					</tr>
					<tr>
						<td>MARCA:</td>
						<td><input type="text" size="60" name="txtMarca" 
							value="<%=brinquedo.getMarca()%>" readonly="readonly" /></td>
					</tr>
		
					<tr>
						<td>IMAGEM:</td>
						<td><input type="text" size="60" style="display:none;" name="txtImagem"
							value="<%=brinquedo.getImagem()%>" readonly="readonly" /><a>imagem atribuída</a></td>
					</tr>
					<tr>
						<td>VALOR:</td>
						<td>
						<span style="position: absolute; margin-left: 15px; margin-top: 5px; font-weight: normal; font-size: 18px;">R$</span>
						<input type="number" size="60" name="txtValor" readonly="readonly"
							value="<%=brinquedo.getValor()%>" />
						</td>
					</tr>
					<tr>
						<td>DETALHES:</td>
						<td><input type="text" size="60" name="txtDetalhes" 
							value="<%=brinquedo.getDetalhes()%>" readonly="readonly" /></td>
					</tr>
				<tr>
					<th colspan="2"><input type="submit" value="CONFIRMAR EXCLUSÃO" /></th>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
