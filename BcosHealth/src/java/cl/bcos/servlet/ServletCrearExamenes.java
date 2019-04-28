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
public class ServletCrearExamenes extends HttpServlet {

    private static final Logger Log = Logger.getLogger(ServletCrearExamenes.class);

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
        response.setContentType("text/html;charset=iso-8859-1");
        HttpSession tokensession = request.getSession(true);

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            String accion = (String) request.getParameter("accion");
            String Paciente = (String) tokensession.getAttribute("PACIENTE");
            String examen_name = (String) request.getParameter("examen_name");
            String examen_obs = (String) request.getParameter("examen_obs");
            String examen_url = (String) request.getParameter("examen_url");
            String examen_pacientename = (String) request.getParameter("examen_pacientename");
            String examen_pacientename_numuser = (String) request.getParameter("examen_pacientename_numuser");
            
            if (accion.equalsIgnoreCase("IE")) {
                Paciente = examen_pacientename_numuser;
            }
            Log.info("Session PACIENTE"+(String) tokensession.getAttribute("PACIENTE"));
            String token = (String) tokensession.getAttribute("token");

            Log.info(request);
            Log.info("accion : " + accion);
            Log.info("Paciente :" + Paciente);
            Log.info("examen_name :" + examen_name);
            Log.info("examen_obs :" + examen_obs);
            Log.info("examen_url :" + examen_url);
            Log.info("examen_pacientename :" + examen_pacientename);

            Log.info("token bearer:" + token);

            String URL = "http://localhost:9090/bcos/api/json/crearExamenes";
//            try {
            Map<String, String> parameter = new HashMap<String, String>();
            parameter.put("accion", accion);
            parameter.put("exa_c_numuser_paciente", Paciente);
            parameter.put("exa_c_name", examen_name);
            parameter.put("exa_c_obs", examen_obs);
            parameter.put("exa_c_url", examen_url);
            parameter.put("examen_pacientename", examen_pacientename);

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
