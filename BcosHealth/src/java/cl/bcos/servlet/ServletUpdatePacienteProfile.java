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
public class ServletUpdatePacienteProfile extends HttpServlet {

    private static final Logger Log = Logger.getLogger(ServletUpdatePacienteProfile.class);

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
            //String numuser_paciente = (String) request.getParameter("paciente_numuser");
            //String nombre_paciente = (String) request.getParameter("paciente_nombre");
            //String apellido_paciente = (String) request.getParameter("apellido_paciente");
            String email_contacto_paciente = (String) request.getParameter("paciente_email");
            String numero_telefono_paciente = (String) request.getParameter("paciente_telefono");
            String profesion_paciente = (String) request.getParameter("paciente_profesion");
            String estado_civil_paciente = (String) request.getParameter("paciente_estado_civil");
            String fecha_nacimiento_paciente = (String) request.getParameter("paciente_fecha_nacimiento");
            String edad_paciente = (String) request.getParameter("paciente_edad");
            String prevision = (String) request.getParameter("paciente_prevision");

            String aboutme_obs_paciente = (String) request.getParameter("paciente_obs");
            String direccion_paciente = (String) request.getParameter("paciente_direccion");
            String sexo = (String) request.getParameter("paciente_sexo");

            String token = (String) tokensession.getAttribute("token");
            String numuser_paciente = (String) tokensession.getAttribute("PACIENTE");
            Log.info("Session PACIENTE " + (String) tokensession.getAttribute("PACIENTE"));

            Log.info(request);
            Log.info("accion : " + accion);
            Log.info("numuser_paciente :" + numuser_paciente);
            //Log.info("nombre_paciente :" + nombre_paciente);
            //Log.info("apellido_paciente :" + apellido_paciente);
            Log.info("email_contacto_paciente :" + email_contacto_paciente);
            Log.info("numero_telefono_paciente :" + numero_telefono_paciente);
            Log.info("profesion_paciente :" + profesion_paciente);
            Log.info("estado_civil_paciente :" + estado_civil_paciente);
            Log.info("fecha_nacimiento_paciente :" + fecha_nacimiento_paciente);
            Log.info("edad_paciente :" + edad_paciente);
            Log.info("prevision :" + prevision);

            Log.info("aboutme_obs_paciente :" + aboutme_obs_paciente);
            Log.info("direccion_paciente :" + direccion_paciente);
            Log.info("sexo :" + sexo);

            Log.info("token bearer:" + token);

            String URL = "http://localhost:9090/bcos/api/json/updatePacientes";
//            try {
            Map<String, String> parameter = new HashMap<String, String>();

            parameter.put("accion", accion);
            parameter.put("numuser_paciente", numuser_paciente);
            //parameter.put("nombre_paciente", nombre_paciente);
            //parameter.put("apellido_paciente", apellido_paciente);
            parameter.put("email_contacto_paciente", email_contacto_paciente);
            parameter.put("numero_telefono_paciente", numero_telefono_paciente);
            parameter.put("profesion_paciente", profesion_paciente);
            parameter.put("estado_civil_paciente", estado_civil_paciente);
            parameter.put("fecha_nacimiento_paciente", fecha_nacimiento_paciente);
            parameter.put("edad_paciente", edad_paciente);
            parameter.put("prevision", prevision);
            parameter.put("aboutme_obs_paciente", aboutme_obs_paciente);
            parameter.put("direccion_paciente", direccion_paciente);
            parameter.put("sexo", sexo);

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
