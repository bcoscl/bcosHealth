/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bcos.servlet;

import cl.bcos.HttpRequest;
import cl.bcos.constants.CommonConstants;
import cl.bcos.entity.S3Response;
import cl.bcos.entity.statusResponse;
import cl.bcos.service.AdmS3;
import cl.bcos.utils.UtilsRandom;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
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
public class ServletCrearSuscripcion extends HttpServlet {

    private static final Logger Log = Logger.getLogger(ServletCrearSuscripcion.class);
    private static final String ENDPOINT_PATH = "URLPATH";
    private static final String PATH = "api.health.bcos.cl";   /* private static final String PATH = System.getenv(ENDPOINT_PATH);*/
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

        /*response.setContentType("text/html;charset=iso-8859-1");*/
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession tokensession = request.getSession(true);

        String empresasession = (String) tokensession.getAttribute("EMPRESA");

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            String accion = (String) request.getParameter("accion");
            String nombre_empresa = (String) request.getParameter("nombre_empresa");
            String contacto_empresa = (String) request.getParameter("contacto_empresa");
            String email_contacto = (String) request.getParameter("email_contacto");
            String numero_telefono = (String) request.getParameter("numero_telefono");
            String fecha_inicio = (String) request.getParameter("fecha_inicio");
            String select_plan_code = (String) request.getParameter("select_plan_code");
            String select_plan_name = (String) request.getParameter("select_plan_name");
            String checkbox_activo = (String) request.getParameter("checkbox_activo");
            String bucketname = "";

            String token = (String) tokensession.getAttribute("token");

            bucketname = nombre_empresa.replaceAll(" ", "-");
            //bucketname = bucketname+"-"+getRandom();
            bucketname = bucketname + "." + UtilsRandom.getRandom();

            Log.info(request);
            Log.info("accion : " + accion);
            Log.info("nombre_empresa :" + nombre_empresa);
            Log.info("contacto_empresa :" + contacto_empresa);
            Log.info("email_contacto :" + email_contacto);
            Log.info("numero_telefono :" + numero_telefono);
            Log.info("fecha_inicio :" + fecha_inicio);
            Log.info("select_plan_code :" + select_plan_code);
            Log.info("select_plan_name :" + select_plan_name);
            Log.info("checkbox_activo :" + checkbox_activo);
            Log.info("bucketname :" + bucketname);

            Log.info("token bearer:" + token);

            if (PATH.contains("localhost")) {
                https = "http://";
            }

            String URL2 = https + PATH + "/bcos/api/json/S3";
            String URL = https + PATH + "/bcos/api/json/crearSuscripcion";
//            try {
            Map<String, String> parameter = new HashMap<String, String>();
            parameter.put("nombre_empresa", nombre_empresa);
            parameter.put("contacto_empresa", contacto_empresa);
            parameter.put("email_contacto", email_contacto);
            parameter.put("numero_telefono", numero_telefono);
            parameter.put("fecha_inicio", fecha_inicio);
            parameter.put("select_plan_code", select_plan_code);
            parameter.put("select_plan_name", select_plan_name);
            parameter.put("checkbox_activo", checkbox_activo);
            parameter.put("empresasession", empresasession);
            parameter.put("bucketName", bucketname);

            parameter.put("token", token);

            String resultHttpRequest = "";
            try {


                /*Crea el Bucket de la suscripcion*/
                Map<String, String> parameter_exa = new HashMap<String, String>();
                parameter_exa.put("accion", accion);
                parameter_exa.put("empresasession", empresasession);
                parameter_exa.put("nombre_empresa", nombre_empresa);
                parameter_exa.put("token", token);

                resultHttpRequest = HttpRequest.HttpRequesPostMethod(URL, parameter, token);
                Log.info(resultHttpRequest);
                statusResponse res = new Gson().fromJson(resultHttpRequest, statusResponse.class);
                Log.info("res.message : " + res.getStatus().getMessage());
                Log.info("res.message : " + res.getStatus().getCode());

                if (res.getStatus().getMessage().equalsIgnoreCase("INSERT_OK") && res.getStatus().getCode().equalsIgnoreCase("200")) {
                    crearBucket(parameter_exa, URL2, token);
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

    private void crearBucket(Map<String, String> parameter_exa, String URL2, String token) throws IOException, Exception {
        /*llamada de imagen*/

        String resultHttpRequest_exa = HttpRequest.HttpRequesPostMethod(URL2, parameter_exa, token);
        Log.info(resultHttpRequest_exa);

        S3Response s3 = new Gson().fromJson(resultHttpRequest_exa, S3Response.class);

        Log.info("S3 res.message : " + s3.getStatus().getMessage());
        Log.info("S3 res.message : " + s3.getStatus().getCode());

        CommonConstants c = new CommonConstants(s3.getS3().getACCESS_KEY_ID(), s3.getS3().getACCESS_SEC_KEY(), s3.getS3().getBUCKETNAME().toLowerCase());
        AdmS3 admS3 = new AdmS3(CommonConstants.ACCESS_KEY_ID, CommonConstants.ACCESS_SEC_KEY);

        admS3.crearBucket(CommonConstants.BUCKET_NAME);
        Log.info(" crea el Bucket " + CommonConstants.BUCKET_NAME + " - OK");

        admS3.crearCarpeta(CommonConstants.BUCKET_NAME, s3.getS3().getFOLDER_NAME_EXAMENES().toLowerCase());
        Log.info(" carpeta :" + s3.getS3().getFOLDER_NAME_EXAMENES() + " - OK");

        admS3.crearCarpeta(CommonConstants.BUCKET_NAME, s3.getS3().getFOLDER_NAME_PROFILE().toLowerCase());
        Log.info(" carpeta :" + s3.getS3().getFOLDER_NAME_PROFILE() + " - OK");

        try {
            admS3.generaPolitica(CommonConstants.BUCKET_NAME, "", s3.getS3().getPOLICY_RULES());
            Log.info(" Politicas OK ");
        } catch (Exception ex) {

            Log.error(ex);
            java.util.logging.Logger.getLogger(ServletCrearSuscripcion.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }

    }

}
