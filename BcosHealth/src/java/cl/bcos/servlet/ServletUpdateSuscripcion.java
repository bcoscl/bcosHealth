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
public class ServletUpdateSuscripcion extends HttpServlet {

    private static final Logger Log = Logger.getLogger(ServletUpdateSuscripcion.class);

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

        String empresasession = (String) tokensession.getAttribute("EMPRESA");

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            String accion = (String) request.getParameter("accion");
            String id = (String) request.getParameter("id");

            String nombre_empresa = (String) request.getParameter("nombre_empresa");
            String contacto_empresa = (String) request.getParameter("contacto_empresa");
            String email_contacto = (String) request.getParameter("email_contacto");
            String numero_telefono = (String) request.getParameter("numero_telefono");
            String fecha_inicio = (String) request.getParameter("fecha_inicio");
            String select_plan_code = (String) request.getParameter("select_plan_code");
            String select_plan_name = (String) request.getParameter("select_plan_name");
            String checkbox_activo = (String) request.getParameter("checkbox_activo");
            String estado = "";

            String token = (String) tokensession.getAttribute("token");

            Log.info(request);
            Log.info("accion : " + accion);
            Log.info("id :" + id);
            Log.info("nombre_empresa :" + nombre_empresa);
            Log.info("contacto_empresa :" + contacto_empresa);
            Log.info("email_contacto :" + email_contacto);
            Log.info("numero_telefono :" + numero_telefono);
            Log.info("fecha_inicio :" + fecha_inicio);
            Log.info("select_plan_code :" + select_plan_code);
            Log.info("select_plan_name :" + select_plan_name);
            Log.info("checkbox_activo :" + checkbox_activo);

            Log.info("token bearer:" + token);

            String URL = "http://localhost:9090/bcos/api/json/updateSuscripcion";
//            try {
            Map<String, String> parameter = new HashMap<String, String>();
            if (accion.equalsIgnoreCase("LS-ACTIVAR")) {
                estado = "true";
                accion = "ESTADO";

            }
            if (accion.equalsIgnoreCase("LS-DESACTIVAR")) {
                estado = "false";
                accion = "ESTADO";
            }

            parameter.put("id", id);
            parameter.put("accion", accion);
            parameter.put("nombre_empresa", nombre_empresa);
            parameter.put("contacto_empresa", contacto_empresa);
            parameter.put("email_contacto", email_contacto);
            parameter.put("numero_telefono", numero_telefono);
            parameter.put("fecha_inicio", fecha_inicio);
            parameter.put("select_plan_code", select_plan_code);
            parameter.put("select_plan_name", select_plan_name);

            parameter.put("checkbox_activo", estado);
            parameter.put("token", token);
            parameter.put("empresasession", empresasession);

            String resultHttpRequest = "";
            try {
                resultHttpRequest = HttpRequest.HttpRequesPostMethod(URL, parameter, token);
                Log.info(resultHttpRequest);
                statusResponse res = new Gson().fromJson(resultHttpRequest, statusResponse.class);

                Log.info("res.message : " + res.getStatus().getMessage());
                Log.info("res.message : " + res.getStatus().getCode());

                if (res.getStatus().getMessage().equalsIgnoreCase("UPDATE_OK") && res.getStatus().getCode().equalsIgnoreCase("200")) {
                    response.setStatus(200);
                } else if (res.getStatus().getMessage().equalsIgnoreCase("TOKEN_NO_VALIDO") && res.getStatus().getCode().equalsIgnoreCase("401")) {
                    Log.info("TOKEN_NO_VALIDO");
                    response.setStatus(401);

                } else {
                    Log.info("UPDATE_NO_OK");
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
