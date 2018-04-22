package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PaisTO;
import service.PaisService;


@WebServlet("/ManterPais.do")
public class ManterPaisController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pNome = request.getParameter("nome");
        String pPopulacao = request.getParameter("populacao");
        String pArea = request.getParameter("area");
        
        //instanciar o javabean
        PaisTO pais = new PaisTO();
        pais.setNome(pNome);
        pais.setPopulacao(Long.parseLong(pPopulacao));
        pais.setArea(Double.parseDouble(pArea));
        
        //instanciar o service
        PaisService ps = new PaisService();
        ps.createPais(pais);
        pais = ps.loadPais(pais);
        
        //enviar para o jsp
        request.setAttribute("pais", pais);
        
        RequestDispatcher view = 
        request.getRequestDispatcher("Pais.jsp");
        view.forward(request, response);
        
    }
    
}
