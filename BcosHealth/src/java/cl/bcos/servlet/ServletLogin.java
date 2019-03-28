/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bcos.servlet;

import cl.bcos.entity.LoginJsonResponse;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author aacantero
 */
public class ServletLogin extends HttpServlet {
    
    private static final Logger Log = Logger.getLogger(ServletLogin.class);

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        
        HttpSession tokensession = request.getSession(true);
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String User = request.getParameter("username");
        String Password = request.getParameter("password");
        
        Log.info("User : " + User + " - Password : " + Password);
        //ImplementacionJWT jwt = new ImplementacionJWT();
        //GenerarTokenJWT token = jwt.getGeneraJWT();
        //token.AddItem("user", User);
        //token.AddItem("pass", Password);

        //String tokken = token.generaToken("bcosHealth", "login", "public");
        //Log.info(tokken);
        String URL = "http://localhost:9090/bcos/api/json/SSO?user=" + User + "&pass=" + Password;
        try {
            
            URL url = new URL(URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "application/json");
            
            if (conn.getResponseCode() != 200) {
                Log.info("Failed : HTTP error code : "
                        + conn.getResponseCode());
                
                response.sendRedirect(
                        "./pages/base/401.html");
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            
            String output;
            StringBuilder json = new StringBuilder();
            Log.info("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                Log.info(output);
                json.append(output);                
            }
            conn.disconnect();
            
            LoginJsonResponse res = new Gson().fromJson(json.toString(), LoginJsonResponse.class);
            
            if (res.getStatus().getStatusCode() == 200 && res.getLogin().equalsIgnoreCase("OK")) {
                Log.info("login : " + res.getLogin());
                Log.info("Code status : " + res.getStatus().getStatusCode());
                Log.info("token " + res.getToken());
                
                tokensession.setAttribute("token", res.getToken());// token bearer
                
                Log.info("token de session : " + tokensession.getAttribute("token"));
                response.sendRedirect(
                        "./pages/base/index.jsp");
                
            } else {
                Log.error("Credenciale incorrectas");
                out.println("Credenciale incorrectas");
                
                response.sendRedirect(
                        "./pages/base/LoginPage.html");
            }
            
        } catch (MalformedURLException e) {
            Log.error(e);
            response.sendRedirect(
                    "./pages/base/404.html");
        } catch (IOException e) {
            Log.error(e);
            response.sendRedirect(
                    "./pages/base/404.html");
            
            Log.error(e);
        }

//        try (PrintWriter out = response.getWriter()) {
//
//
//
//
//
//            
//            /* TODO output your page here. You may use following sample code. */
////            out.println("<!DOCTYPE html>");
////            out.println("<html>");
////            out.println("<head>");
////            out.println("<title>Servlet ServletLogin</title>");            
////            out.println("</head>");
////            out.println("<body>");
////            out.println("<h1>Servlet ServletLogin at " + request.getContextPath() + "</h1>");
////            out.println("</body>");
////            out.println("</html>");
//        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
            
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ServletLogin.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
            
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ServletLogin.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
