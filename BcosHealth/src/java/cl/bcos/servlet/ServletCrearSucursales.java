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
public class ServletCrearSucursales extends HttpServlet {

    private static final Logger Log = Logger.getLogger(ServletListarSuscripciones.class);

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

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            String accion = (String) request.getParameter("accion");
            String nombre_sucursal = (String) request.getParameter("nombre_sucursal");
            String comuna_sucursal = (String) request.getParameter("comuna_sucursal");
            String numero_telefono = (String) request.getParameter("numero_telefono");
            String correo_sucursal = (String) request.getParameter("correo_sucursal");
            String contacto_sucursal = (String) request.getParameter("contacto_sucursal");
            String select_empresa = (String) request.getParameter("select_empresa");// codigo
            String select_empresa_name = (String) request.getParameter("select_empresa_name");
            String checkbox_activo = (String) request.getParameter("checkbox_activo");

            String token = (String) tokensession.getAttribute("token");

            Log.info(request);
            Log.info("accion : " + accion);
            Log.info("nombre_sucursal :" + nombre_sucursal);
            Log.info("comuna_sucursal :" + comuna_sucursal);
            Log.info("numero_telefono :" + numero_telefono);
            Log.info("correo_sucursal :" + correo_sucursal);
            Log.info("contacto_sucursal :" + contacto_sucursal);
            Log.info("select_empresa :" + select_empresa);
            Log.info("select_empresa_name :" + select_empresa_name);
            Log.info("checkbox_activo :" + checkbox_activo);

            Log.info("token bearer:" + token);

            String URL = "http://localhost:9090/bcos/api/json/crearSucursales";
//            try {
            Map<String, String> parameter = new HashMap<String, String>();
            parameter.put("nombre_sucursal", nombre_sucursal);
            parameter.put("comuna_sucursal", comuna_sucursal);
            parameter.put("numero_telefono", numero_telefono);
            parameter.put("correo_sucursal", correo_sucursal);
            parameter.put("contacto_sucursal", contacto_sucursal);
            parameter.put("select_empresa", select_empresa);
            parameter.put("select_empresa_name", select_empresa_name);
            parameter.put("checkbox_activo", checkbox_activo);
            parameter.put("accion", accion);

            parameter.put("token", token);

            String resultHttpRequest = "";
            try {
                resultHttpRequest = HttpRequest.HttpRequesPostMethod(URL, parameter, token);
                Log.debug(resultHttpRequest);
                statusResponse res = new Gson().fromJson(resultHttpRequest, statusResponse.class);
                Log.debug("res.message : " + res.getStatus().getMessage());
                Log.debug("res.message : " + res.getStatus().getCode());

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

}
