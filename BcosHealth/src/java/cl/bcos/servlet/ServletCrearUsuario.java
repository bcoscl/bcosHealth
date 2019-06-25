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
public class ServletCrearUsuario extends HttpServlet {

    private static final Logger Log = Logger.getLogger(ServletCrearUsuario.class);
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
            String numuser_user = (String) request.getParameter("numuser_user");
            String nombre_user = (String) request.getParameter("nombre_user");
            String apellido_user = (String) request.getParameter("apellido_user");
            String email_contacto_user = (String) request.getParameter("email_contacto_user");
            String numero_telefono_user = (String) request.getParameter("numero_telefono_user");
            String profesion_select = (String) request.getParameter("profesion_select");
            String textarea_obs = (String) request.getParameter("textarea_obs");
            String sucursal_select = (String) request.getParameter("sucursal_select");
            String roles_select = (String) request.getParameter("roles_select");
            String password = (String) request.getParameter("password");
            String checkbox_activo = (String) request.getParameter("checkbox_activo");

            try{
                email_contacto_user = email_contacto_user.toLowerCase();
            }catch(Exception e ){
                Log.error("no se pudo converir en minusculas el email.");
            }
            
            String token = (String) tokensession.getAttribute("token");
            try {
                if (!sucursal_select.isEmpty() && !sucursal_select.equalsIgnoreCase("")) {
                    sucursal_select = CleanString(sucursal_select);
                }

                if (!roles_select.isEmpty() && !roles_select.equalsIgnoreCase("")) {
                    roles_select = CleanString(roles_select);
                }

                if (!profesion_select.isEmpty() && !profesion_select.equalsIgnoreCase("")) {
                    profesion_select = CleanString(profesion_select);
                }

            } catch (Exception e) {
                Log.error("No se puede limpiar las cadenas");
            }

            Log.info(request);
            Log.info("accion : " + accion);
            Log.info("numuser_user :" + numuser_user);
            Log.info("nombre_user :" + nombre_user);
            Log.info("apellido_user :" + apellido_user);
            Log.info("email_contacto_user :" + email_contacto_user);
            Log.info("numero_telefono_user :" + numero_telefono_user);
            Log.info("profesion_select :" + profesion_select);
            Log.info("textarea_obs :" + textarea_obs);
            Log.info("sucursal_select :" + sucursal_select);
            Log.info("roles_select :" + roles_select);
            Log.info("password :" + password);
            Log.info("checkbox_activo :" + checkbox_activo);

            Log.info("token bearer:" + token);

            if(PATH.contains("localhost")){https = "http://";}String URL = https+PATH+ "/bcos/api/json/crearUsuarios";
//            try {
            Map<String, String> parameter = new HashMap<String, String>();

            parameter.put("accion", accion);
            parameter.put("numuser_user", numuser_user);
            parameter.put("nombre_user", nombre_user);
            parameter.put("apellido_user", apellido_user);
            parameter.put("email_contacto_user", email_contacto_user);
            parameter.put("numero_telefono_user", numero_telefono_user);
            parameter.put("profesion_select", profesion_select);
            parameter.put("textarea_obs", textarea_obs);
            parameter.put("sucursal_select", sucursal_select);
            parameter.put("roles_select", roles_select);
            parameter.put("password", password);
            parameter.put("checkbox_activo", checkbox_activo);
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

                }else if (res.getStatus().getMessage().equalsIgnoreCase("TOKEN_OK") && 
                        res.getStatus().getCode().equalsIgnoreCase("400")) {
                     Log.info("MAXIMO ALCANZADO");
                    
                    response.sendError(400, "../../pages/Users/maximoAlcanzado.jsp");
                    
                    

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

    private String CleanString(String value) {
        Log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

        value = value.replace("][", "|");
        value = value.replace("]", ",");
        value = value.replace("[", ",");
        value = value.replace("|", ",");
        value = value.replace(" ", "");

        String valor[] = value.split(",");

        String valorLimpio = "";
        for (int i = 0; i < valor.length; i++) {
            if (!valor[i].isEmpty()) {
                if (valorLimpio.isEmpty()) {
                    valorLimpio = valor[i];
                } else {
                    valorLimpio = valorLimpio + "," + valor[i];
                }
            }
            //Log.debug(valor[i]);
        }
        return valorLimpio;
    }

}
