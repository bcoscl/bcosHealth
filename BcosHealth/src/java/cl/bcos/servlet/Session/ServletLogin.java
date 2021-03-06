/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bcos.servlet.Session;

import cl.bcos.HttpRequest;
import cl.bcos.entity.LoginJsonResponse;
import cl.bcos.utils.ConstantsPath;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
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
//    private static final String ENDPOINT_PATH = "URLPATH";
//    private static final String PATH = "api.health.bcos.cl";   /* private static final String PATH = System.getenv(ENDPOINT_PATH);*/
//
//    private static String https = "https://";

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
        Log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        HttpSession tokensession = request.getSession(true);

        /*response.setContentType("text/html;charset=iso-8859-1");*/
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        ConstantsPath init = new ConstantsPath();
        PrintWriter out = response.getWriter();

        String User = request.getParameter("username");
        String Password = request.getParameter("password");
        String accion = request.getParameter("accion");

        tokensession.setAttribute("MENU", null);

        Log.info("User : " + User + " - Password : " + Password);
        //ImplementacionJWT jwt = new ImplementacionJWT();
        //GenerarTokenJWT token = jwt.getGeneraJWT();
        //token.AddItem("user", User);
        //token.AddItem("pass", Password);
        String resultHttpRequest = "";
        //String tokken = token.generaToken("bcosHealth", "login", "public");
        //Log.info(tokken);
//        if (PATH.contains("localhost")) {
//            https = "http://";
//        }

        String URL = ConstantsPath.CONTEXT + "/bcos/api/json/SSO";
        Log.info("API: " + URL);

        Map<String, String> parameter = new HashMap<String, String>();
        parameter.put("User", User);
        parameter.put("Password", Password);
        parameter.put("accion", accion);
        //parameter.put("token", token);

        try {

            resultHttpRequest = HttpRequest.HttpRequesPostMethod(URL, parameter, "");
            Log.info("Response Api : " + resultHttpRequest);
            LoginJsonResponse res = new Gson().fromJson(resultHttpRequest, LoginJsonResponse.class);

            Log.info("res.message : " + res.getStatus().getMessage());
            Log.info("res.message : " + res.getStatus().getCode());

            if (res.getStatus().getStatusCode() != 200) {
                Log.info("Failed : HTTP error code : "
                        + res.getStatus().getStatusCode());

                response.sendRedirect(
                        "./pages/base/401.html");
            }

            if (res.getStatus().getStatusCode() == 200 && res.getLogin().equalsIgnoreCase("OK")) {
                Log.info("login : " + res.getLogin());
                Log.info("Code status : " + res.getStatus().getStatusCode());
                Log.info("setea token session" + res.getToken());
                Log.info("setea Empresa session" + res.getStatus().getMessage());

                tokensession.setAttribute("token", res.getToken());// token bearer
                tokensession.setAttribute("EMPRESA", res.getStatus().getMessage());// empresa de la session

                Log.info("token de session : " + tokensession.getAttribute("token"));
                response.sendRedirect(
                        "./pages/base/index.jsp");

            } else {
                Log.error("Credenciale incorrectas");
                out.println("Credenciale incorrectas");

                response.sendRedirect(
                        "./pages/base/LoginPage.jsp");
            }

        } catch (MalformedURLException e) {
            Log.error(e);
            response.sendRedirect(
                    "./pages/base/404.html");
        } catch (Exception e) {
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
        Log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
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
        Log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        try {
            processRequest(request, response);

        } catch (Exception ex) {
            Log.error(ex.getStackTrace());
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
        Log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        return "Short description";
    }// </editor-fold>

}
