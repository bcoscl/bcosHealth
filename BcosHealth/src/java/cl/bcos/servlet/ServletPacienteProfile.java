/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bcos.servlet;

import cl.bcos.HttpRequest;
import cl.bcos.entity.PacienteResponse;
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
public class ServletPacienteProfile extends HttpServlet {

    private static final Logger Log = Logger.getLogger(ServletPacienteProfile.class);
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
        String PacieteDetail = (String) tokensession.getAttribute("PACIENTE");
        Log.info("Session PACIENTE " + (String) tokensession.getAttribute("PACIENTE"));
        Log.debug("Session paciente " + PacieteDetail);
        if (PacieteDetail != null) {
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
        String URL = https + PATH + "/bcos/api/json/PacienteProfile";
//            try {
        Map<String, String> parameter = new HashMap<String, String>();
        parameter.put("accion", accion);
        parameter.put("numUser", PacieteDetail);
        parameter.put("token", token);
        parameter.put("empresasession", empresasession);

        String resultHttpRequest = "";
        try {
            resultHttpRequest = HttpRequest.HttpRequesPostMethod(URL, parameter, token);
            Log.info(resultHttpRequest);
            PacienteResponse res = new Gson().fromJson(resultHttpRequest, PacienteResponse.class);

            Log.info("res.message : " + res.getStatus().getMessage());
            Log.info("res.message : " + res.getStatus().getCode());

            if (res.getStatus().getMessage().equalsIgnoreCase("SELECT_OK") && res.getStatus().getCode().equalsIgnoreCase("200")) {
                response.setStatus(200);
                try {

                    //if (accion.equalsIgnoreCase("CP-PERFIL")) {
                    out.println(getPacienteProfilePipe(res));
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

    private String getPacienteProfilePipe(PacienteResponse res) {
        Log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        StringBuilder out = new StringBuilder();

        out.append("<span>");
        out.append(res.getPaciente().getC_pacientename() + " " + res.getPaciente().getC_apellidos());
        out.append("</span>");
        out.append("|");
        out.append("<span>");
        out.append(res.getPaciente().getC_numuser());
        out.append("</span>");
        out.append("|");
        out.append("<i class=\"fa fa-map-marker light-orange bigger-110\"> </i><span>");
        out.append(res.getPaciente().getC_direccion());
        out.append("</span>");
        out.append("|");
        out.append("<span>");
        out.append(res.getPaciente().getN_edad());//edad
        out.append("</span>");
        out.append("|");
        out.append("<span>");
        out.append(res.getPaciente().getD_fechaNacimiento());// fecha de nacimiento
        out.append("</span>");
        out.append("|");
        out.append("<span>");
        out.append(res.getPaciente().getC_prevision());
        out.append("</span>");
        out.append("|");
        out.append("<span>");
        out.append(res.getPaciente().getC_celular());
        out.append("</span>");
        out.append("|");
        out.append("<span>");
        out.append(res.getPaciente().getC_email());
        out.append("</span>");
        out.append("|");
        out.append("<span>");
        out.append(res.getPaciente().getC_profesion());
        out.append("</span>");
        out.append("|");
        out.append("<span>");
        out.append(res.getPaciente().getC_estado_civil());
        out.append("</span>");
        out.append("|");
        out.append("<span>");
        out.append(res.getPaciente().getC_obs());
        out.append("</span>");
        out.append("|");
        //out.append("<span>");
        out.append(res.getPaciente().getC_sexo());
        //out.append("</span>");

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
