<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
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
	        
	        .sidebar a[href="html/categorias.html"] {
            	background-color: #2d94f5;
	        }
	
	        .content {
	            margin-left: 300px;
	            margin-top: 100px;
	            padding: 20px;
	           	text-decoration: none;
	            font-family: Arial, Helvetica, sans-serif;
	        }
	        
	     	.content h2 {
	            font-size: 26px;
	        }
	        
	        .content-new {
	            color:blue;
	            font-size: 22px;
	            text-decoration: underline;
				cursor: pointer;
				position: fixed;					
				top: 870px;					
				left: 330px;
	        }
	        
	        .content-neww {
	            color:blue;
	            font-size: 22px;
	            text-decoration: underline;
				cursor: pointer;
				position: fixed;					
				top: 870px;					
				left: 530px;
	        }
	        
		    table img {
			    height: 350px;
			    width: auto;
				border: 3px;
				border-style: solid;
				border-color: #222;
	 			border-radius: 7px;				    
			}
			
			.top-left {
				margin-top: 25px;				
				display: inline-block;					
			}
			
			.top-right {
				position: absolute;
				margin-top: 25px;				
				margin-left: 25px;
				display: inline-block;
				text-align: left;		
			}
			
			.top-right1 {
				font-size: 15px;
				color: grey;
			}
			
			.top-right2 {
				padding-top: 70px;
				font-size: 48px;
				font-weight: bold;
				width: 700px;
			}
			
			.top-right3 {
				position: fixed;
				top: 550px;
				font-size: 28px;
			}						
			
			
			.bottom {
				margin-top: 25px;
				height: 270px;
				width: 100%;
				display: inline-block;
	 			font-size: 20px;
	 			text-align: justify;				
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
	        <a href="html/login.html">Administração</a>
	        <a href="html/sobre.html">Sobre a Equipe</a>
	    </div>
	    
	    <div class="content">    		
	    		<%
			    List<Brinquedo> lista = new ArrayList<Brinquedo>();
			    lista = (ArrayList) request.getAttribute("brinquedoList");
			    %>	
			    	
	    	<h2>Catálogo de Brinquedos :: <%=lista.get(0).getCategoria()%> :: <%=lista.get(0).getDescricao()%></h2>
	    	<hr>
	    	
	        <%
	        for (Brinquedo a : lista) {
	        %>	 
	           					    	     			   	
		  	<div class="top-left">
			  	<table>
	            <tr>
				      <td>
				      <img src="data:image/jpeg;base64, <%=a.getImagem()%>"/></td>
				</tr>
	            </table>
	        </div>
	        
			<div class="top-right">
				<table>
					<tr>
					      <td class="top-right1"><a>Código do Brinquedo: <%=a.getId()%></a></td>
					</tr>
					<tr>
					      <td class="top-right2"><a><%=a.getDescricao()%></a>
						  </td>
					</tr>
					<tr>	      
					      <td class="top-right3"><a>R$ <%=a.getValor()%></a></td>
		            </tr>
		        </table>    
			</div>	        
	       
	        <div class="bottom">
	            <table>
	            <tr>
	            	<td>
	            	<p><%=a.getDetalhes()%></p></td>  
	            </tr>        
				</table>
			</div>
			
			<%
			}
			%>  
		</div> 	
	</body>
</html>

