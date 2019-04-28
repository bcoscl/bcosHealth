package cl.bcos.servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import cl.bcos.HttpRequest;
import cl.bcos.entity.LoginJsonResponse;
import com.google.gson.Gson;
import java.io.IOException;
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
public class ServletPlanes extends HttpServlet {

    private static final Logger Log = Logger.getLogger(ServletPlanes.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        response.setContentType("text/html;charset=iso-8859-1");

        HttpSession tokensession = request.getSession(true);

        String planName = (String) request.getParameter("planName");
        String userMax = (String) request.getParameter("userMax");
        String token = (String) tokensession.getAttribute("token");
        int redirect = 0;
        Log.info(request);
        Log.info("planname : " + planName);
        Log.info("User MAX :" + userMax);
        Log.info("token bearer:" + token);

        String URL = "http://localhost:9090/bcos/api/json/planes";
//            try {
        Map<String, String> parameter = new HashMap<String, String>();
        parameter.put("planName", planName);
        parameter.put("userMax", userMax);
        parameter.put("token", token);

        String resultHttpRequest = "";
        try {
            resultHttpRequest = HttpRequest.HttpRequesPostMethod(URL, parameter, token);
            Log.info(resultHttpRequest);
            LoginJsonResponse res = new Gson().fromJson(resultHttpRequest, LoginJsonResponse.class);
            Log.info("res.message : " + res.getStatus().getMessage());
            Log.info("res.message : " + res.getStatus().getCode());

            if (res.getStatus().getMessage().equalsIgnoreCase("INSERT_OK") && res.getStatus().getCode().equalsIgnoreCase("200")) {
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
