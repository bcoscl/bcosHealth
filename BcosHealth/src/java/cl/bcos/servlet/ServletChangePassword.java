/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bcos.servlet;

import cl.bcos.HttpRequest;
import cl.bcos.entity.statusResponse;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
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
public class ServletChangePassword extends HttpServlet {

    private static final Logger Log = Logger.getLogger(ServletChangePassword.class);
private static final String ENDPOINT_PATH = "URLPATH";
private static final String PATH = System.getProperty(ENDPOINT_PATH,System.getenv(ENDPOINT_PATH));
private static String https = "https://";
	

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession tokenReinicio = request.getSession(true);

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            String tokenReset = (String) tokenReinicio.getAttribute("TOKEN_RESET");

            /* TODO output your page here. You may use following sample code. */
            String pass = (String) request.getParameter("pass");

            Log.info(request);
            Log.info("pass : " + pass);
            Log.info("tokenReset : " + tokenReset);

            if(PATH.contains("localhost")){https = "http://";}String URL = https+PATH+"/bcos/api/json/changePassword";
//            try {
            Map<String, String> parameter = new HashMap<String, String>();

            parameter.put("token", tokenReset);
            parameter.put("password", pass);

            String resultHttpRequest = "";
            try {
                resultHttpRequest = HttpRequest.HttpRequesPostMethod(URL, parameter, tokenReset);
                Log.info(resultHttpRequest);
                statusResponse res = new Gson().fromJson(resultHttpRequest, statusResponse.class);
                Log.info("res.message : " + res.getStatus().getMessage());
                Log.info("res.message : " + res.getStatus().getCode());

                if (res.getStatus().getMessage().equalsIgnoreCase("RESET_OK") && res.getStatus().getCode().equalsIgnoreCase("200")) {

                    if (tokenReinicio != null) {
                        tokenReinicio.invalidate();
                    }

                    response.sendRedirect("./pages/base/LoginPage.jsp");
                    return;

                } else if (res.getStatus().getMessage().equalsIgnoreCase("TOKEN_NO_VALIDO") && res.getStatus().getCode().equalsIgnoreCase("401")) {
                    Log.info("TOKEN_NO_VALIDO");
                    if (tokenReinicio != null) {
                        tokenReinicio.invalidate();
                    }
                    response.sendRedirect("./pages/base/401_1.html");

                } else {
                    if (tokenReinicio != null) {
                        tokenReinicio.invalidate();
                    }
                    response.sendRedirect(
                            "./pages/base/404.html");
                }

            } catch (Exception e) {
                Log.error(e);
                if (tokenReinicio != null) {
                    tokenReinicio.invalidate();
                }
                response.sendRedirect(
                        "./pages/base/404.html");
            }

        }
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
        processRequest(request, response);
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
        processRequest(request, response);
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
