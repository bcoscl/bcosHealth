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
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.log4j.Logger;

/**
 *
 * @author aacantero
 */
public class ServletCrearExamenes extends HttpServlet {

    private static final Logger Log = Logger.getLogger(ServletCrearExamenes.class);
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
            String Paciente = (String) tokensession.getAttribute("PACIENTE");
            String examen_name = (String) request.getParameter("examen_name");
            String examen_obs = (String) request.getParameter("examen_obs");
            //String examen = (String) request.getParameter("examen_url");

            String examen_pacientename = (String) request.getParameter("examen_pacientename");
            String examen_pacientename_numuser = (String) request.getParameter("examen_pacientename_numuser");

            if (accion.equalsIgnoreCase("IE")) {
                Paciente = examen_pacientename_numuser;
            }
            Log.info("Session PACIENTE" + (String) tokensession.getAttribute("PACIENTE"));
            String token = (String) tokensession.getAttribute("token");

            Log.info(request);
            Log.info("accion : " + accion);
            Log.info("Paciente :" + Paciente);
            Log.info("examen_name :" + examen_name);
            Log.info("examen_obs :" + examen_obs);
            //Log.info("examen_url :" + examen);
            Log.info("examen_pacientename :" + examen_pacientename);

            Log.info("token bearer:" + token);

            if (PATH.contains("localhost")) {
                https = "http://";
            }
            String URL = https + PATH + "/bcos/api/json/crearExamenes";
            String URL2 = https + PATH + "/bcos/api/json/S3";
//            try {

            String resultHttpRequest = "";
            try {

                //String url = upLoadS3(empresasession, URL2, token, examen);
                //String examen_url = url;
                Map<String, String> parameter = new HashMap<String, String>();
                parameter.put("accion", accion);
                parameter.put("exa_c_numuser_paciente", Paciente);
                parameter.put("exa_c_name", examen_name);
                parameter.put("exa_c_obs", examen_obs);
                //parameter.put("exa_c_url", examen_url);
                parameter.put("examen_pacientename", examen_pacientename);
                parameter.put("empresasession", empresasession);

                parameter.put("token", token);

                resultHttpRequest = HttpRequest.HttpRequesPostMethod(URL, parameter, token);

                Log.info(resultHttpRequest);
                statusResponse res = new Gson().fromJson(resultHttpRequest, statusResponse.class);
                Log.info("res.message : " + res.getStatus().getMessage());
                Log.info("res.message : " + res.getStatus().getCode());

                if (res.getStatus().getMessage().equalsIgnoreCase("INSERT_OK") && res.getStatus().getCode().equalsIgnoreCase("200")) {

                    response.setStatus(200);
                    out.println(res.getStatus().getName());

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

    private String upLoadS3(String empresasession, String URL2, String token, Part filePart) throws IOException {
        /*llamada de imagen*/
        Log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

        InputStream inputStream = null; // input stream of the upload file

        /*Crea el Bucket de la suscripcion*/
        Map<String, String> parameter = new HashMap<String, String>();
        parameter.put("accion", "EXAMEN_FILE");
        parameter.put("empresasession", empresasession);
        parameter.put("token", token);

        String resultHttpRequest_exa = HttpRequest.HttpRequesPostMethod(URL2, parameter, token);
        Log.info(resultHttpRequest_exa);

        S3Response s3 = new Gson().fromJson(resultHttpRequest_exa, S3Response.class);

        Log.info("S3 res.message : " + s3.getStatus().getMessage());
        Log.info("S3 res.message : " + s3.getStatus().getCode());

        CommonConstants c = new CommonConstants(s3.getS3().getACCESS_KEY_ID(), s3.getS3().getACCESS_SEC_KEY(), s3.getS3().getBUCKETNAME().toLowerCase());
        AdmS3 admS3 = new AdmS3(CommonConstants.ACCESS_KEY_ID, CommonConstants.ACCESS_SEC_KEY);

        // Utiliza Part para accesar al objeto que viene de parametro
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        Log.info("file name: " + fileName);
        String fileNamePath = fileName;

        fileName = UtilsRandom.getRandom() + "-" + fileName;

        //fileName = fwe[0] + "." + UtilsRandom.getRandom() + "." + fwe[1];
        if (!"".equals(s3.getS3().getFOLDER_NAME_EXAMENES())) {
            fileName = s3.getS3().getFOLDER_NAME_EXAMENES().toLowerCase() + "/" + fileName;
        }
        Log.info("file Name ruta : " + fileName);

        if (filePart != null) {
            // prints out some information for debugging
            Log.info(filePart.getName());
            Log.info(filePart.getSize());
            Log.info(filePart.getContentType());

            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();

            String url;
            url = admS3.cargarArchivo(CommonConstants.BUCKET_NAME, fileName, inputStream);

            url = admS3.preUrl(CommonConstants.BUCKET_NAME, fileName, 5);
            Log.info("preURL Input: " + url);

        }
        return fileNamePath;
        //response.sendRedirect("./pages/Perfil/userProfile.jsp");

    }

}
