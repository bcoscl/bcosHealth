/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. ServletUploadArchivoExamen
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
import javax.servlet.annotation.MultipartConfig;
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
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 10 MB 
        maxFileSize = 1024 * 1024 * 50, // 50 MB
        maxRequestSize = 1024 * 1024 * 100)   	// 100 MB
public class ServletUploadArchivoExamen extends HttpServlet {

    private static final Logger Log = Logger.getLogger(ServletUploadArchivoExamen.class);
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
        try (PrintWriter out = response.getWriter()) {

            InputStream inputStream = null; // input stream of the upload file
            HttpSession tokensession = request.getSession(true);

            String empresasession = (String) tokensession.getAttribute("EMPRESA");
            String token = (String) tokensession.getAttribute("token");

            Part filePart = request.getPart("upload-file-examen");
            String id = (String) request.getParameter("examenid");
            String accion = (String) request.getParameter("accion");
            Log.info("id: " + id);

            try {
                if (PATH.contains("localhost")) {
                    https = "http://";
                }

                /*Crea el Bucket de la suscripcion*/
                Map<String, String> parameter = new HashMap<String, String>();
                parameter.put("accion", "EXAMEN_FILE");
                parameter.put("empresasession", empresasession);
                parameter.put("token", token);

                String URL2 = https + PATH + "/bcos/api/json/S3";

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

                    /*Actualizar el Perfil con el nombre de la imagen fileNameProfile */
                    if (PATH.contains("localhost")) {
                        https = "http://";
                    }
                    String URL = https + PATH + "/bcos/api/json/updateExamenFile";
//            try {
                    Map<String, String> parameter2 = new HashMap<String, String>();

                    parameter2.put("token", token);
                    parameter2.put("empresasession", empresasession);
                    parameter2.put("examenUrl", fileName);
                    parameter2.put("id", id);

                    String resultHttpRequest = "";
                    try {
                        resultHttpRequest = HttpRequest.HttpRequesPostMethod(URL, parameter2, token);
                        Log.info(resultHttpRequest);
                        statusResponse res = new Gson().fromJson(resultHttpRequest, statusResponse.class);

                        Log.info("res.message : " + res.getStatus().getMessage());
                        Log.info("res.message : " + res.getStatus().getCode());

                        if (res.getStatus().getMessage().equalsIgnoreCase("UPDATE_OK") && res.getStatus().getCode().equalsIgnoreCase("200")) {
                            response.setStatus(200);

                        } else if (res.getStatus().getMessage().equalsIgnoreCase("TOKEN_NO_VALIDO") && res.getStatus().getCode().equalsIgnoreCase("401")) {
                            Log.info("TOKEN_NO_VALIDO");
                            //response.setStatus(401);
                            response.sendRedirect("./pages/base/sorry.html");

                        } else {
                            Log.info("UPDATE_NO_OK");
                            response.setStatus(400);  // 400 Bad Request - no se eejcuto el insert  
                        }

                    } catch (Exception e) {
                        Log.error(e);
                        response.setStatus(404);
                    }

                }
                if (accion.equalsIgnoreCase("EXAMENFICHA")) {
                    response.sendRedirect("./pages/Fichas/listarFichasTab.jsp");
                } else if (accion.equalsIgnoreCase("EXAMEN")) {
                    response.sendRedirect("./pages/Examenes/crearExamenes.jsp");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
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
