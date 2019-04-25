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
public class ServletCrearConfiguraciones extends HttpServlet {

    private static final Logger Log = Logger.getLogger(ServletCrearConfiguraciones.class);

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
        Log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

        response.setContentType("text/html;charset=UTF-8");
        HttpSession tokensession = request.getSession(true);

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            String accion = (String) request.getParameter("accion");
            String params_grupo = (String) request.getParameter("params_grupo");
            String params_subgrupo = (String) request.getParameter("params_subgrupo");
            String params_param1 = (String) request.getParameter("params_param1");
            String params_param2 = (String) request.getParameter("params_param2");
            String params_param3 = (String) request.getParameter("params_param3");
            String params_param4 = (String) request.getParameter("params_param4");

            String token = (String) tokensession.getAttribute("token");

            Log.info(request);
            Log.info("accion : " + accion);
            Log.info("params_grupo :" + params_grupo);
            Log.info("params_subgrupo :" + params_subgrupo);
            Log.info("params_param1 :" + params_param1);
            Log.info("params_param2 :" + params_param2);
            Log.info("params_param3 :" + params_param3);
            Log.info("params_param4 :" + params_param4);

            Log.info("token bearer:" + token);

            String URL = "http://localhost:9090/bcos/api/json/crearParam";
//            try {
            Map<String, String> parameter = new HashMap<String, String>();
            parameter.put("params_grupo", params_grupo);
            parameter.put("params_subgrupo", params_subgrupo);
            parameter.put("params_param1", params_param1);
            parameter.put("params_param2", params_param2);
            parameter.put("params_param3", params_param3);
            parameter.put("params_param4", params_param4);

            parameter.put("token", token);

            String resultHttpRequest = "";
            try {
                resultHttpRequest = HttpRequest.HttpRequesPostMethod(URL, parameter, token);
                Log.info(resultHttpRequest);
                statusResponse res = new Gson().fromJson(resultHttpRequest, statusResponse.class);
                Log.info("res.message : " + res.getStatus().getMessage());
                Log.info("res.message : " + res.getStatus().getCode());

                if (res.getStatus().getMessage().equalsIgnoreCase("INSERT_OK") && res.getStatus().getCode().equalsIgnoreCase("200")) {
                    response.setStatus(200);
                } else if (res.getStatus().getMessage().equalsIgnoreCase("TOKEN_NO_VALIDO") && res.getStatus().getCode().equalsIgnoreCase("401")) {
                    Log.info("TOKEN_NO_VALIDO");
                    response.setStatus(401);

                } else {
                    Log.info("INSERT_NO_OK");
                    response.setStatus(400);  // 400 Bad Request - no se eejcuto el insert  
                }

            } catch (Exception e) {
                Log.error(e);
                response.setStatus(404);
            }
            // Log.info("Result Call Api - " + resultHttpRequest);

//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ServletCrearSuscripcion</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ServletCrearSuscripcion at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
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
        Log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
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
        Log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
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
