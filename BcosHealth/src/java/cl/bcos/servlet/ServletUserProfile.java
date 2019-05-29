/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bcos.servlet;

import cl.bcos.HttpRequest;
import cl.bcos.constants.CommonConstants;
import cl.bcos.entity.ProfileResponse;
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
public class ServletUserProfile extends HttpServlet {

    private static final Logger Log = Logger.getLogger(ServletUserProfile.class);
    private static final String ENDPOINT_PATH = "URLPATH";
    private static final String PATH = "api.bcos.cl";/*    private static final String PATH = System.getenv(ENDPOINT_PATH);*/
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

        PrintWriter out = response.getWriter();

        //String planName = (String) request.getParameter("planName");
        //String userMax = (String) request.getParameter("userMax");
        String token = (String) tokensession.getAttribute("token");
        String accion = (String) request.getParameter("accion");
        String userDetail = (String) tokensession.getAttribute("USER");

        if (userDetail != null) {
            Log.debug("Session user vacia");
            accion = "CP-BYUSER";

        }

        Log.info("accion :" + accion);
        Log.info(request);
        //Log.info("planname : " + planName);
        //Log.info("User MAX :" + userMax);
        Log.info("token bearer:" + token);

        if (PATH.contains("localhost")) {
            https = "http://";
        }
        String URL = https + PATH + "/bcos/api/json/Profile";
//            try {
        Map<String, String> parameter = new HashMap<String, String>();
        parameter.put("accion", accion);
        parameter.put("numUser", userDetail);
        parameter.put("token", token);
        parameter.put("empresasession", empresasession);

        String resultHttpRequest = "";
        try {
            resultHttpRequest = HttpRequest.HttpRequesPostMethod(URL, parameter, token);
            Log.info(resultHttpRequest);
            ProfileResponse res = new Gson().fromJson(resultHttpRequest, ProfileResponse.class);

            Log.info("res.message : " + res.getStatus().getMessage());
            Log.info("res.message : " + res.getStatus().getCode());

            if (res.getStatus().getMessage().equalsIgnoreCase("SELECT_OK") && res.getStatus().getCode().equalsIgnoreCase("200")) {
                response.setStatus(200);
                try {

                    //if (accion.equalsIgnoreCase("CP-PERFIL")) {
                    out.println(getUserProfilePipe(res, empresasession, token));
                    //}
//                        else if (accion.equalsIgnoreCase("LP-SELECT")) {
//                            out.println(getPlanesSelect(res));
//                        }

                } catch (Exception e) {
                    Log.error(" NO Existe Informacion : " + e);

                    out.println("Imposible recuperar la informacion");
                    response.setStatus(404);

                }

            } else if (res.getStatus().getMessage().equalsIgnoreCase("TOKEN_NO_VALIDO") && res.getStatus().getCode().equalsIgnoreCase("401")) {
                Log.info("TOKEN_NO_VALIDO");
                response.setStatus(401);

            } else {
                Log.info("SELECT_NO_OK");
                response.setStatus(400);  // 400 Bad Request - no se eejcuto el insert  
            }

        } catch (Exception e) {
            Log.error(e);
            response.setStatus(404);
        }
        // Log.info("Result Call Api - " + resultHttpRequest);

        /* TODO output your page here. You may use following sample code. */
    }

    private String getUserProfilePipe(ProfileResponse res, String empresasession, String token) throws IOException {
        Log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        StringBuilder out = new StringBuilder();

        /*Crea el Bucket de la suscripcion*/
        Map<String, String> parameter = new HashMap<String, String>();
        parameter.put("accion", "USER_PROFILE_FILE");
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
        
        String url = admS3.preUrl(CommonConstants.BUCKET_NAME, res.getProfile().getImgUrl(), 15);
        Log.info("preURL Input: " + url);

        out.append("<span>");
        out.append(res.getProfile().getName() + " " + res.getProfile().getLastName());
        out.append("</span>");
        out.append("|");
        out.append("<span>");
        out.append(res.getProfile().getNumUser());
        out.append("</span>");
        out.append("|");
        out.append("<span>");
        out.append(res.getProfile().getEmpresaName());
        out.append("</span>");
        out.append("|");
        out.append("<span>");
        out.append(res.getProfile().getProfesion());
        out.append("</span>");
        out.append("|");
        out.append("<span>");
        out.append(res.getProfile().getCelular());
        out.append("</span>");
        out.append("|");
        out.append("<span>");
        out.append(res.getProfile().getEmail());
        out.append("</span>");
        out.append("|");
        out.append("<span>");
        out.append(res.getProfile().getRoles());
        out.append("</span>");
        out.append("|");
        out.append("<span>");
        out.append(res.getProfile().getAboutMe());
        out.append("</span>");
        out.append("|");

        out.append(url);
         out.append("|");

        out.append(res.getProfile().getUserId());

        Log.debug(out.toString());
        return out.toString();

    }

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

}
