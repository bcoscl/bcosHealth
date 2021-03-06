/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bcos.servlet.EnfermedadesCronicas;

import cl.bcos.HttpRequest;
import cl.bcos.entity.statusResponse;
import cl.bcos.utils.ConstantsPath;
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
public class ServletUpdateEnfermedadesCronicas extends HttpServlet {

    private static final Logger Log = Logger.getLogger(ServletUpdateEnfermedadesCronicas.class);
//    private static final String ENDPOINT_PATH = "URLPATH";
//    private static final String PATH = "api.health.bcos.cl";   /* private static final String PATH = System.getenv(ENDPOINT_PATH);*/
//    private static String https = "https://";

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

        /*response.setContentType("text/html;charset=iso-8859-1");*/
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession tokensession = request.getSession(true);

        String empresasession = (String) tokensession.getAttribute("EMPRESA");
        ConstantsPath init = new ConstantsPath();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            String accion = (String) request.getParameter("accion");
            String cronicaRowId = (String) request.getParameter("row");
            String cronica_name = (String) request.getParameter("cronica_name");
            String cronica_obs = (String) request.getParameter("cronica_obs");

            String token = (String) tokensession.getAttribute("token");

            Log.info(request);
            Log.info("accion : " + accion);
            Log.info("row :" + cronicaRowId);
            Log.info("cronica_c_name :" + cronica_name);
            Log.info("cronica_c_obs :" + cronica_obs);

            Log.info("token bearer:" + token);

//            if (PATH.contains("localhost")) {
//                https = "http://";
//            }
            String URL = ConstantsPath.CONTEXT + "/bcos/api/json/updateEnfermedadesCronicas";
            Log.info(" URL :" + URL);
//            try {
            Map<String, String> parameter = new HashMap<String, String>();

            parameter.put("accion", accion);
            parameter.put("cronicaRowId", cronicaRowId);
            parameter.put("cronica_name", cronica_name);
            parameter.put("cronica_obs", cronica_obs);
            parameter.put("empresasession", empresasession);

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
                    Log.info("INSERT_NO_OK");
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
