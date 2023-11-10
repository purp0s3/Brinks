<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="br.edu.exemplo.model.User"%>

<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<meta charset="UTF-8">
		<title>Brinks - Administração</title>
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
	        
	        .content-new{
	            color:blue;
	            font-size: 22px;
	            text-decoration: underline;
				cursor: pointer;
				position: fixed;					
				top: 870px;					
				left: 330px;
	        }
	        
	        .content-neww{
	            color:blue;
	            font-size: 22px;
	            text-decoration: underline;
				cursor: pointer;
				position: fixed;					
				top: 870px;					
				left: 530px;
	        }	
	        		
			table {
	            border-collapse: collapse;
	         	text-align: center;
	            width: 100%;
	            margin-top: 40px;
	        }
	
	        table, th, td {
	            border: 2px solid #000;
	        }
	
	        table th {
	            background-color: #cccccc;
	            font-size: 20px;
	            padding: 10px;
	            height: 40px;
	        }
	        
	        table td {
	        	height: 40px;
	        	font-size: 18px;
	        }
	        
			table tr:nth-child(odd) td {
			    background:#eeeeee;
			}
			table tr:nth-child(even) td { 
			    background:#fff;
			}
	        
	        input[type=submit] {
	        	border: none;
	        	background: none;
	        	color: blue;
				text-decoration: underline;
				font-size: 18px;
				cursor: pointer;
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
	    	<h2>Catálogo de Brinquedos :: Administração</h2>
	    	<hr>

		  	<table>
			    <tr>
			      <th>Código</th>
			      <th>Usuário</th>
			      <th>Senha</th>
			      <th colspan="2">Controles</th>
			    </tr>
			    <%
			    List<User> lista = new ArrayList<User>();
			    lista = (ArrayList) request.getAttribute("usuariosList");
			    for (User a : lista) {
			    %>
			    <tr>
			      <td><%=a.getCod()%></td>
			      <td><%=a.getUsername()%></td>
			      <td><%=a.getPassword()%></td>
			      <td>
			        <form action="ServletBrinquedo" method="get">
			          <input type="hidden" name="cmd" value="atuU">
			          <input type="hidden" name="cod" value="<%=a.getCod()%>">
			          <input type="submit" value="Editar">
			        </form>
			      </td>
			      <td>
			        <form action="ServletBrinquedo" method="post">
			          <input type="hidden" name="cmd" value="excU">
			          <input type="hidden" name="cod" value="<%=a.getCod()%>">
					  <input type="submit" value="Excluir">
			        </form>
			      </td>
			    </tr>
			    <%
			    }
			    %>
			</table>
		</div>	
	</body>
</html>

