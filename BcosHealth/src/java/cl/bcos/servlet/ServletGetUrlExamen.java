/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bcos.servlet;

import cl.bcos.HttpRequest;
import cl.bcos.constants.CommonConstants;
import cl.bcos.entity.S3Response;
import cl.bcos.service.AdmS3;
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
public class ServletGetUrlExamen extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession tokensession = request.getSession(true);

            String empresasession = (String) tokensession.getAttribute("EMPRESA");
            String accion = (String) request.getParameter("accion");
            String fileName = (String) request.getParameter("file");
            String id = (String) request.getParameter("id");

            Log.info("Session PACIENTE" + (String) tokensession.getAttribute("PACIENTE"));
            String token = (String) tokensession.getAttribute("token");

            /*Crea el Bucket de la suscripcion*/
            Map<String, String> parameter = new HashMap<String, String>();
            parameter.put("accion", "EXAMEN_FILE");
            parameter.put("empresasession", empresasession);
            parameter.put("token", token);

            if (PATH.contains("localhost")) {
                https = "http://";
            }

            String URL2 = https + PATH + "/bcos/api/json/S3";

            String resultHttpRequest_exa = HttpRequest.HttpRequesPostMethod(URL2, parameter, token);
            Log.info(resultHttpRequest_exa);

            S3Response s3 = new Gson().fromJson(resultHttpRequest_exa, S3Response.class);

            Log.info("S3 res.message : " + s3.getStatus().getMessage());
            Log.info("S3 res.message : " + s3.getStatus().getCode());

            CommonConstants c = new CommonConstants(s3.getS3().getACCESS_KEY_ID(), s3.getS3().getACCESS_SEC_KEY(), s3.getS3().getBUCKETNAME().toLowerCase());
            AdmS3 admS3 = new AdmS3(CommonConstants.ACCESS_KEY_ID, CommonConstants.ACCESS_SEC_KEY);

            // Utiliza Part para accesar al objeto que viene de parametro
            Log.info("file name: " + fileName);

            String url = admS3.preUrl(CommonConstants.BUCKET_NAME, fileName, 30);
            Log.info("preURL Input: " + url);

            out.print(url);

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
