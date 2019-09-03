/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bcos.servlet.Evaluacion;

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
public class ServletCrearEvaluacion extends HttpServlet {

    private static final Logger Log = Logger.getLogger(ServletCrearEvaluacion.class);

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

        ConstantsPath init = new ConstantsPath();

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
           
            String accion = (String) request.getParameter("accion");
            //String row = (String) tokensession.getAttribute("row");
            String eva_paciente = (String) request.getParameter("eva_name");
            String eva_fecha = (String) request.getParameter("eva_fecha");
            String eva_talla = (String) request.getParameter("eva_talla");
            String eva_peso = (String) request.getParameter("eva_peso");
            String eva_fat = (String) request.getParameter("eva_fat");
            String eva_fatv = (String) request.getParameter("eva_fatv");
            String eva_musc = (String) request.getParameter("eva_musc");
            String eva_obs = (String) request.getParameter("eva_obs");
            String eva_imc = (String) request.getParameter("eva_imc");
            
            

            String token = (String) tokensession.getAttribute("token");
            Log.info("Session PACIENTE" + (String) tokensession.getAttribute("PACIENTE"));
            eva_paciente = (String) tokensession.getAttribute("PACIENTE");
            
            Log.info(request);
            Log.info("accion : " + accion);
            Log.info("Paciente :" + eva_paciente);
            Log.info("eva_fecha :" + eva_fecha);
            Log.info("eva_talla :" + eva_talla);
            Log.info("eva_peso :" + eva_peso);
            Log.info("eva_fat :" + eva_fat);
            Log.info("eva_fatv :" + eva_fatv);
            Log.info("eva_musc :" + eva_musc);
            Log.info("eva_obs :" + eva_obs);
            Log.info("eva_imc :" + eva_imc);
            
            Log.info("token bearer:" + token);

            String URL = ConstantsPath.CONTEXT + "/bcos/api/json/crearEvaluacion";
            Log.info(" URL :" + URL);
//            try {
            Map<String, String> parameter = new HashMap<String, String>();
            parameter.put("accion", accion);
            parameter.put("eva_paciente", eva_paciente);
            parameter.put("eva_fecha", eva_fecha);
            parameter.put("eva_talla", eva_talla);
            parameter.put("eva_peso", eva_peso);
            parameter.put("eva_fat", eva_fat);
            parameter.put("eva_fatv", eva_fatv);
            parameter.put("eva_musc", eva_musc);
            parameter.put("eva_obs", eva_obs);
            parameter.put("eva_imc", eva_imc);
            
            parameter.put("empresasession", empresasession);

            parameter.put("token", token);

            String resultHttpRequest = "";
            try {
                resultHttpRequest = HttpRequest.HttpRequesPostMethod(URL, parameter, token);
                Log.info(resultHttpRequest);
                statusResponse res = new Gson().fromJson(resultHttpRequest, statusResponse.class);
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
