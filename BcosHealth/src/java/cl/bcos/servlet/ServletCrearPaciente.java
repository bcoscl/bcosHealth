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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author aacantero
 */
public class ServletCrearPaciente extends HttpServlet {

    private static final Logger Log = Logger.getLogger(ServletCrearPaciente.class);
    private static final String ENDPOINT_PATH = "URLPATH";
    /*private static final String PATH = "api.bcos.cl";*/    private static final String PATH = System.getenv(ENDPOINT_PATH);
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
        Log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

        response.setContentType("text/html;charset=iso-8859-1");
        HttpSession tokensession = request.getSession(true);

        String empresasession = (String) tokensession.getAttribute("EMPRESA");

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            String accion = (String) request.getParameter("accion");
            String numuser_paciente = (String) request.getParameter("numuser_user");
            String nombre_paciente = (String) request.getParameter("nombre_paciente");
            String apellido_paciente = (String) request.getParameter("apellido_paciente");
            String email_contacto_paciente = (String) request.getParameter("email_contacto_paciente");
            String numero_telefono_paciente = (String) request.getParameter("numero_telefono_paciente");
            String profesion_paciente = (String) request.getParameter("profesion_paciente");
            String estado_civil_paciente = (String) request.getParameter("estado_civil_paciente");
            String fecha_nacimiento_paciente = (String) request.getParameter("fecha_nacimiento_paciente");
            String edad_paciente = (String) request.getParameter("edad_paciente");
            String prevision = (String) request.getParameter("prevision_select");
            String isapre_name_paciente = (String) request.getParameter("isapre_name_paciente");
            String aboutme_obs_paciente = (String) request.getParameter("textarea_obs_paciente");
            String direccion_paciente = (String) request.getParameter("direccion_paciente");
            String sexo = (String) request.getParameter("sexo_paciente");

            if (isapre_name_paciente != null && !isapre_name_paciente.isEmpty()) {
                prevision = prevision + ", " + isapre_name_paciente;
            }

            String token = (String) tokensession.getAttribute("token");

            Log.info(request);
            Log.info("accion : " + accion);
            Log.info("numuser_paciente :" + numuser_paciente);
            Log.info("nombre_paciente :" + nombre_paciente);
            Log.info("apellido_paciente :" + apellido_paciente);
            Log.info("email_contacto_paciente :" + email_contacto_paciente);
            Log.info("numero_telefono_paciente :" + numero_telefono_paciente);
            Log.info("profesion_paciente :" + profesion_paciente);
            Log.info("estado_civil_paciente :" + estado_civil_paciente);
            Log.info("fecha_nacimiento_paciente :" + fecha_nacimiento_paciente);
            Log.info("edad_paciente :" + edad_paciente);
            Log.info("prevision :" + prevision);
            Log.info("isapre_name_paciente :" + isapre_name_paciente);
            Log.info("aboutme_obs_paciente :" + aboutme_obs_paciente);
            Log.info("direccion_paciente :" + direccion_paciente);
            Log.info("sexo :" + sexo);

            Log.info("token bearer:" + token);

            if (PATH.contains("localhost")) {
                https = "http://";
            }
            String URL = https + PATH + "/bcos/api/json/crearPaciente";
//            try {
            Map<String, String> parameter = new HashMap<String, String>();

            parameter.put("accion", accion);
            parameter.put("numuser_paciente", numuser_paciente);
            parameter.put("nombre_paciente", nombre_paciente);
            parameter.put("apellido_paciente", apellido_paciente);
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

                if (res.getStatus().getMessage().equalsIgnoreCase("INSERT_OK")
                        && res.getStatus().getCode().equalsIgnoreCase("200")) {

                    response.setStatus(200);
                } else if (res.getStatus().getMessage().equalsIgnoreCase("TOKEN_NO_VALIDO") && res.getStatus().getCode().equalsIgnoreCase("401")) {
                    Log.info("TOKEN_NO_VALIDO");
                    response.setStatus(401);

                } else if (res.getStatus().getMessage().toUpperCase().contains("_EXISTE") && res.getStatus().getCode().equalsIgnoreCase("200")) {
                    Log.info("RESULTADO DE REGISTRO :" + res.getStatus().getMessage());

                    if (res.getStatus().getMessage().toUpperCase().equalsIgnoreCase("USUARIO_NO_EXISTE")) {
                        out.print("OK");
                    }
                    if (res.getStatus().getMessage().toUpperCase().equalsIgnoreCase("USUARIO_EXISTE")) {
                        out.print("El usuario ya existe");
                    }

                    response.setStatus(200);

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
