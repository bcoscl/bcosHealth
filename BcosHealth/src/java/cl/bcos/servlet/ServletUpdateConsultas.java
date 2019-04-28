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
public class ServletUpdateConsultas extends HttpServlet {

    private static final Logger Log = Logger.getLogger(ServletUpdateConsultas.class);

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs accion: "CREATE",
     * modal_nombre: $("#modal_nombre").val(), modal_numuser:
     * $("#modal_nombre").val(), modal_mediconumuser: $("#medico_select
     * option:selected").val(), modal_mediconombre: $("#medico_select
     * option:selected").text(), modal_motivo: $("#modal_motivo").val()
     *
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

        response.setContentType("text/html;charset=iso-8859-1");
        HttpSession tokensession = request.getSession(true);

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            String accion = (String) request.getParameter("accion");
            String ConsultaRowId = (String) request.getParameter("row");
            String Consulta_titulo = (String) request.getParameter("consulta_titulo");
            String Consulta_obs = (String) request.getParameter("consulta_obs");


            String token = (String) tokensession.getAttribute("token");

            Log.info(request);
            Log.info("accion : " + accion);
            Log.info("ConsultaRowId :" + ConsultaRowId);
            Log.info("Consulta_titulo :" + Consulta_titulo);
            Log.info("Consulta_obs :" + Consulta_obs);


            Log.info("token bearer:" + token);

            String URL = "http://localhost:9090/bcos/api/json/updateConsultas";
//            try {
            Map<String, String> parameter = new HashMap<String, String>();

            parameter.put("accion", accion);
            parameter.put("ConsultaRowId", ConsultaRowId);
            parameter.put("Consulta_titulo", Consulta_titulo);
            parameter.put("Consulta_obs", Consulta_obs);


            parameter.put("token", token);

            String resultHttpRequest = "";
            try {
                resultHttpRequest = HttpRequest.HttpRequesPostMethod(URL, parameter, token);
                Log.info("Result httpRequest :" + resultHttpRequest);
                statusResponse res = null;
                try {
                    res = new Gson().fromJson(resultHttpRequest, statusResponse.class);
                } catch (Exception e) {
                    Log.error(" NO SE PUEDE PARSEAR LA RESPUESTA : " + e);

                    if (resultHttpRequest.toUpperCase().contains("NOT FOUND")) {
                        Log.error(" API NO EXISTE ");
                    }

                    throw e;
                }
                Log.info("res.message : " + res.getStatus().getMessage());
                Log.info("res.message : " + res.getStatus().getCode());

                if (res.getStatus().getMessage().equalsIgnoreCase("UPDATE_OK")
                        && res.getStatus().getCode().equalsIgnoreCase("200")) {

                    response.setStatus(200);
                } else if (res.getStatus().getMessage().equalsIgnoreCase("TOKEN_NO_VALIDO") && res.getStatus().getCode().equalsIgnoreCase("401")) {
                    Log.info("TOKEN_NO_VALIDO");
                    response.setStatus(401);

                } else {
                    Log.info("UPDATE_NO_OK");
                    response.setStatus(400);  // 400 Bad Request - Registro Duplicado  
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
        Log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        return "Short description";
    }// </editor-fold>

}
