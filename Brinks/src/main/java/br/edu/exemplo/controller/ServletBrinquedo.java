package br.edu.exemplo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import br.edu.exemplo.dao.BrinquedoDAO;
import br.edu.exemplo.model.Brinquedo;
import br.edu.exemplo.model.User;
import br.edu.exemplo.dao.UserDAO;

@WebServlet("/ServletBrinquedo")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1024 * 1024 * 50, maxRequestSize = 1024 * 1024 * 50)
public class ServletBrinquedo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {  
    	
    	String cmd = request.getParameter("cmd");
        
    	// criação e autenticação de usuários
    	try {
            if (cmd.equals("login")) {
                String username = request.getParameter("username");
                String password = request.getParameter("password");

                UserDAO userDAO = new UserDAO();
                User user = userDAO.getUserByUsername(username);

                if (user != null && user.getPassword().equals(password)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);

                    response.sendRedirect("ServletBrinquedo?cmd=listar");
                } else {
                    response.sendRedirect("html/login.html");
                }
            } else if (cmd.equals("register")) {
                String username = request.getParameter("newUsername");
                String password = request.getParameter("newPassword");

                User newUser = new User();
                newUser.setUsername(username);
                newUser.setPassword(password);

                UserDAO userDAO = new UserDAO();
                boolean registrationSuccess = userDAO.insertUser(newUser);

                if (registrationSuccess) {
                    response.sendRedirect("html/login.html");
                } else {
                    response.sendRedirect("html/register.html");
                }
            }
    	}catch (Exception e) {
	        e.printStackTrace();
    	}      

            

            RequestDispatcher rdU = null;
        	User user = new User();   
        	UserDAO daoU;
            // listar usuários
           try {	
            daoU = new UserDAO();
        	   
	           if (cmd.equalsIgnoreCase("listarUsuarios")) {         
	           List<User> usuariosList = daoU.todosUsuarios();
	           request.setAttribute("usuariosList", usuariosList);
	           rdU = request.getRequestDispatcher("jsp/admUsers.jsp");	
	           }
                        
            // atualizar
            else if (cmd.equalsIgnoreCase("atuU")) {
                user.setCod(Integer.parseInt(request.getParameter("cod")));
                user.setUsername(request.getParameter("username"));
                user.setPassword(request.getParameter("password"));                
            	user = daoU.procurarUsuario(user.getCod());

                // checar ID existe
                if (user != null) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("user", user);
                    rdU = request.getRequestDispatcher("jsp/atualizarUsuario.jsp");
                } else {
                    // ID não existe
                    response.sendRedirect("index.html");
                    return;
                }
                
            // atualizar form
            } else if (cmd.equalsIgnoreCase("atualizarU")) {
                user.setCod(Integer.parseInt(request.getParameter("cod")));
                user.setUsername(request.getParameter("username"));
                user.setPassword(request.getParameter("password"));            	
                daoU.atualizarU(user);
                rdU = request.getRequestDispatcher("ServletBrinquedo?cmd=listarUsuarios");
            }
            
            // excluir	
            else if (cmd.equalsIgnoreCase("excU")) {
                user.setCod(Integer.parseInt(request.getParameter("cod")));
                user.setUsername(request.getParameter("username"));
                user.setPassword(request.getParameter("password"));              	
                user = daoU.procurarUsuario(user.getCod());

                // checar ID existe
                if (user != null) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("user", user);
                    rdU = request.getRequestDispatcher("jsp/excluirUsuario.jsp");
                } else {
                    // ID não existe
                    response.sendRedirect("index.html");
                    return;
                }
            } 
            
            // excluir form
            else if (cmd.equalsIgnoreCase("excluirU")) {
                user.setCod(Integer.parseInt(request.getParameter("cod")));
                user.setUsername(request.getParameter("username"));
                user.setPassword(request.getParameter("password"));            	
                daoU.excluirU(user);
                rdU = request.getRequestDispatcher("ServletBrinquedo?cmd=listarUsuarios");   
            }
	           
            rdU.forward(request, response);            
        } catch (Exception e) {
	        e.printStackTrace();
    }      
            
        //métodos brinquedos    
        Brinquedo brinquedo = new Brinquedo();
        BrinquedoDAO dao;

        // request de todos parâmetros
        try {
            if (cmd.equals("incluir") || cmd.equals("atualizar")) {
                brinquedo.setDescricao(request.getParameter("txtDescricao"));
                brinquedo.setCategoria(request.getParameter("txtCategoria"));
                brinquedo.setMarca(request.getParameter("txtMarca"));
                brinquedo.setValor(Integer.parseInt(request.getParameter("txtValor")));
                brinquedo.setDetalhes(request.getParameter("txtDetalhes"));
                
	            Part imagemFile = request.getPart("imagemFile");
	            String fileName = extractFileName(imagemFile);
	            String savePath = "C:\\CHANGE PATH HERE\\Brinks\\src\\main\\webapp\\images\\uploads";
	            String filePath = savePath + File.separator + fileName;
	            imagemFile.write(filePath);	            
	            String base64Image = encodeImageToBase64(filePath);
	            brinquedo.setImagem(base64Image);

            } else {
                brinquedo.setId(Integer.parseInt(request.getParameter("txtId")));
            }
        } catch (Exception e) {
	        e.printStackTrace();
        }
            
        RequestDispatcher rd = null;
        
        try {
            dao = new BrinquedoDAO();
            
            // incluir
            if (cmd.equalsIgnoreCase("incluir")) {
                dao.salvar(brinquedo);
                rd = request.getRequestDispatcher("ServletBrinquedo?cmd=listar");
            } 
            
            // listar
            else if (cmd.equalsIgnoreCase("listar")) {
                List<Brinquedo> brinquedosList = dao.todosBrinquedos();
                request.setAttribute("brinquedosList", brinquedosList);
                rd = request.getRequestDispatcher("jsp/adm.jsp");	
            }
            
            // listar destaques
            else if (cmd.equalsIgnoreCase("listarDestaques")) {
                List<Brinquedo> destaquesList = dao.destaqueBrinquedos();
                request.setAttribute("destaquesList", destaquesList);
                rd = request.getRequestDispatcher("index.jsp");	
            }             
            
            // atualizar
            else if (cmd.equalsIgnoreCase("atu")) {
                brinquedo = dao.procurarBrinquedo(brinquedo.getId());

                // checar ID existe
                if (brinquedo != null) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("brinquedo", brinquedo);
                    rd = request.getRequestDispatcher("jsp/atualizarBrinquedo.jsp");
                } else {
                    // ID não existe
                    response.sendRedirect("index.html");
                    return;
                }
                
            // atualizar form
            } else if (cmd.equalsIgnoreCase("atualizar")) {
            	brinquedo.setId(Integer.parseInt(request.getParameter("txtId")));
                dao.atualizar(brinquedo);
                rd = request.getRequestDispatcher("ServletBrinquedo?cmd=listar");
            }
            
            // excluir	
            else if (cmd.equalsIgnoreCase("exc")) {
                brinquedo = dao.procurarBrinquedo(brinquedo.getId());

                // checar ID existe
                if (brinquedo != null) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("brinquedo", brinquedo);
                    rd = request.getRequestDispatcher("jsp/excluirBrinquedo.jsp");
                } else {
                    // ID não existe
                    response.sendRedirect("index.html");
                    return;
                }
            } 
            
            // excluir form
            else if (cmd.equalsIgnoreCase("excluir")) {
                dao.excluir(brinquedo);
                rd = request.getRequestDispatcher("ServletBrinquedo?cmd=listar");
                
            }
            
            // listar categorias
            else if (cmd.equalsIgnoreCase("listarPorCategoria")) {
			    String selectedCategory = request.getParameter("txtCategoria");
			    List<Brinquedo> categoriasList = getBrinquedosCategoria(selectedCategory);
			    request.setAttribute("categoriasList", categoriasList);
			    rd = request.getRequestDispatcher("jsp/categoriaBrinquedo.jsp");	
            }
            
            // mostrar brinquedo
            else if (cmd.equalsIgnoreCase("mostrarBrinquedo")) {
            	brinquedo.setId(Integer.parseInt(request.getParameter("txtId")));			    
            	String selectedBrinquedo = request.getParameter("txtId");
			    List<Brinquedo> brinquedoList = showBrinquedo(selectedBrinquedo);
			    request.setAttribute("brinquedoList", brinquedoList);
			    rd = request.getRequestDispatcher("jsp/mostrarBrinquedo.jsp");	
            	}
            rd.forward(request, response);
        } catch (Exception e) {
	        e.printStackTrace();
    }      
   }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    // extrair nome arquivo
    private String extractFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] items = contentDisposition.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }
    
    // codificar img>base64
	private String encodeImageToBase64(String filePath) throws IOException {
	    File file = new File(filePath);
	    byte[] imageBytes = Files.readAllBytes(file.toPath());
	    return Base64.getEncoder().encodeToString(imageBytes);
	}    
    
	// classe lista categoria
	private List<Brinquedo> getBrinquedosCategoria(String selectedCategory) {
	    try {
	        BrinquedoDAO dao = new BrinquedoDAO();
	        return dao.categoriaBrinquedos(selectedCategory);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ArrayList<Brinquedo>();
	    }
	}
	
	// classe mostrar brinquedo
	private List<Brinquedo> showBrinquedo(String selectedBrinquedo) {
	    try {
	        BrinquedoDAO dao = new BrinquedoDAO();
	        return dao.mostrarBrinquedo(selectedBrinquedo);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ArrayList<Brinquedo>();
	    }
	}	
}