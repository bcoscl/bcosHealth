/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bcos.servlet;

import cl.bcos.HttpRequest;
import cl.bcos.entity.Rol;
import cl.bcos.entity.RolList;
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
public class ServletListarMenu extends HttpServlet {

    private static final Logger Log = Logger.getLogger(ServletListarMenu.class);

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

        HttpSession tokensession = request.getSession(true);
        PrintWriter out = response.getWriter();

        String MENU = (String) tokensession.getAttribute("MENU");
        String token = (String) tokensession.getAttribute("token");
        String accion = (String) request.getParameter("accion");

        Log.info("token bearer:" + token);
        Log.info("accion :" + accion);
        Log.info(request);

        if (token == null) {
            response.setStatus(401);
        } else {

            Log.info("MENU:" + MENU);

//            if (MENU != null && !MENU.isEmpty()) {
//                Log.info("Menu desde session");
//                out.println(MENU);
//
//            } else {
            Log.info("NO EXISTE MENU, SE CARGA SEGUN ACCESOS");

            String URL = "http://localhost:9090/bcos/api/json/listarMenu";

            Map<String, String> parameter = new HashMap<String, String>();

            parameter.put("accion", accion);
            parameter.put("token", token);

            String resultHttpRequest = "";
            try {
                resultHttpRequest = HttpRequest.HttpRequesPostMethod(URL, parameter, token);
                Log.info(resultHttpRequest);
                RolList res = new Gson().fromJson(resultHttpRequest, RolList.class);

                Log.info("res.getStatus().getMessage() : " + res.getStatus().getMessage());
                Log.info("res.getStatus().getCode() : " + res.getStatus().getCode());

                if (res.getStatus().getCode().equalsIgnoreCase("401")) {
                    response.setStatus(401);
                }

//
                if (res.getStatus().getMessage().equalsIgnoreCase("SELECT_OK") && res.getStatus().getCode().equalsIgnoreCase("200")) {
                    response.setStatus(200);
                    try {
                        if (res.getRoles().size() > 0) {

                            String menuRequest = getMenu(res);
                            menuRequest = menuRequest + "|" + res.getNOMBRE();
                            tokensession.setAttribute("MENU", menuRequest);

                            out.println(menuRequest);

                        }
                    } catch (Exception e) {
                        Log.error(" NO Existen Regisros");

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
                response.sendRedirect(
                        "./pages/base/404.html");
            }
            // Log.info("Result Call Api - " + resultHttpRequest);

            /* TODO output your page here. You may use following sample code. */
            //}
        }
    }

    private String getMenu(RolList res) {

        Log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        StringBuilder out = new StringBuilder();
        StringBuilder menu = new StringBuilder();

        menu.append("<ul class=\"nav\"> ");

        for (Rol str : res.getRoles()) {

            String rol = str.getRol();

            if (rol.equalsIgnoreCase("SUPER-ADMIN")) {

                menu.append(getOpcHome());
                menu.append(getOpcAvanzadas());
                menu.append(getOpcAdministracion());
                menu.append(getOpcAtencion());
                menu.append(getOpcRecepcion());
                menu.append(getOpcFicha());
                menu.append(getOpcExamenes());
                break;
            } else if (rol.equalsIgnoreCase("ADMIN")) {
                menu.append(getOpcHome());
                menu.append(getOpcAdministracion());
                menu.append(getOpcModulos());
                menu.append(getOpcAtencion());
                menu.append(getOpcRecepcion());
                menu.append(getOpcFicha());
                menu.append(getOpcExamenes());
                break;

            }
            if (rol.equalsIgnoreCase("MEDICO")) {
                menu.append(getOpcHome());
                menu.append(getOpcModulos());
                menu.append(getOpcAtencion());
                menu.append(getOpcFicha());
                menu.append(getOpcExamenes());
                break;

            } else if (rol.equalsIgnoreCase("RECEPCION")) {
                menu.append(getOpcHome());
                menu.append(getOpcModulos());
                menu.append(getOpcRecepcion());
                break;

            }
        }
        menu.append("</ul> ");

        return menu.toString();

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

    private String getOpcAvanzadas() {
        StringBuilder out = new StringBuilder();
        out.append("                <li class=\"nav-title\">SUPER ADMIN</li> ");
        out.append("                <li class=\"nav-item nav-dropdown\">			 ");
        out.append("                    <a class=\"nav-link nav-dropdown-toggle\" href=\"#\"> ");
        out.append("                        <i class=\"nav-icon icon-settings\"></i> Advanced</a> ");
        out.append("                    <ul class=\"nav-dropdown-items\"> ");
        out.append("                        <li class=\"nav-item nav-dropdown\">		 ");
        out.append("                            <a class=\"nav-link nav-dropdown-toggle ng-scope md-default-theme\" href=\"#\"> ");
        out.append("                                <i class=\"nav-icon icon-puzzle\"></i>Configuraciones</a> ");
        out.append("                            <ul class=\"nav-dropdown-items\"> ");
        out.append("                                <li class=\"nav-item ng-scope\" > ");
        out.append("                                    <a class=\"nav-link\" href=\"../Configuraciones/ListarConfiguraciones.jsp\"> ");
        out.append("                                        <i class=\"nav-icon icon-list\"></i>Listar Configuraciones ");
        out.append("                                    </a> ");
        out.append("                                </li> ");
        out.append("                                <li class=\"nav-item ng-scope\"> ");
        out.append("                                    <a class=\"nav-link\" href=\"../Configuraciones/crearConfiguraciones.jsp\"> ");
        out.append("                                        <i class=\"nav-icon icon-plus\"></i>Crear Configuracion ");
        out.append("                                    </a> ");
        out.append("                                </li> ");
        out.append("                            </ul> ");
        out.append("                        </li> ");
        out.append("                        <li class=\"nav-item nav-dropdown\">		 ");
        out.append("                            <a class=\"nav-link nav-dropdown-toggle ng-scope md-default-theme\" href=\"#\"> ");
        out.append("                                <i class=\"nav-icon icon-tag\"></i>Suscripciones</a> ");
        out.append("                            <ul class=\"nav-dropdown-items\"> ");
        out.append("                                <li class=\"nav-item ng-scope\" > ");
        out.append("                                    <a class=\"nav-link\" href=\"../Suscripciones/ListarSuscripciones.jsp\"> ");
        out.append("                                        <i class=\"nav-icon icon-list\"></i>Listar Suscripciones ");
        out.append("                                    </a> ");
        out.append("                                </li> ");
        out.append("                                <li class=\"nav-item ng-scope\"> ");
        out.append("                                    <a class=\"nav-link\" href=\"../Suscripciones/crearSuscripcion.jsp\"> ");
        out.append("                                        <i class=\"nav-icon icon-plus\"></i>Crear Suscripciones ");
        out.append("                                    </a> ");
        out.append("                                </li> ");
        out.append("                            </ul> ");
        out.append("                        </li> ");
        out.append("                        <li class=\"nav-item nav-dropdown\">		 ");
        out.append("                            <a class=\"nav-link nav-dropdown-toggle ng-scope md-default-theme\" href=\"#\"> ");
        out.append("                                <i class=\"nav-icon icon-credit-card\"></i>Planes</a> ");
        out.append("                            <ul class=\"nav-dropdown-items\"> ");
        out.append("                                <li class=\"nav-item ng-scope\" > ");
        out.append("                                    <a class=\"nav-link\" href=\"../Planes/ListarPlanes.jsp\"> ");
        out.append("                                        <i class=\"nav-icon icon-list\"></i>Listar Planes ");
        out.append("                                    </a> ");
        out.append("                                </li> ");
        out.append("                                <li class=\"nav-item ng-scope\"> ");
        out.append("                                    <a class=\"nav-link\" href=\"../Planes/crearPlanes.jsp\"> ");
        out.append("                                        <i class=\"nav-icon icon-plus\"></i>Crear Planes ");
        out.append("                                    </a> ");
        out.append("                                </li> ");
        out.append("                            </ul> ");
        out.append("                        </li> ");
        out.append("                        <li class=\"nav-item nav-dropdown\">		 ");
        out.append("                            <a class=\"nav-link nav-dropdown-toggle ng-scope md-default-theme\" href=\"#\"> ");
        out.append("                                <i class=\"nav-icon icon-credit-card\"></i>Roles</a> ");
        out.append("                            <ul class=\"nav-dropdown-items\"> ");
        out.append("                                <li class=\"nav-item ng-scope\" > ");
        out.append("                                    <a class=\"nav-link\" href=\"../Roles/ListarRoles.jsp\"> ");
        out.append("                                        <i class=\"nav-icon icon-list\"></i>Listar Roles ");
        out.append("                                    </a> ");
        out.append("                                </li> ");
        out.append("                                <li class=\"nav-item ng-scope\"> ");
        out.append("                                    <a class=\"nav-link\" href=\"../Roles/crearRoles.jsp\"> ");
        out.append("                                        <i class=\"nav-icon icon-plus\"></i>Crear Roles ");
        out.append("                                    </a> ");
        out.append("                                </li> ");
        out.append("                            </ul> ");
        out.append("                        </li> ");
        out.append("                    </ul> ");
        out.append("                </li> ");
        return out.toString();
    }

    private String getOpcAdministracion() {
        StringBuilder out = new StringBuilder();
        out.append("                <li class=\"nav-title\">Administración</li> ");
        out.append("                <li class=\"nav-item nav-dropdown\">			 ");
        out.append("                    <a class=\"nav-link nav-dropdown-toggle\" href=\"#\"> ");
        out.append("                        <i class=\"nav-icon icon-settings\"></i> Settings</a> ");
        out.append("                    <ul class=\"nav-dropdown-items\"> ");
        out.append("                        <li class=\"nav-item nav-dropdown\">		 ");
        out.append("                            <a class=\"nav-link nav-dropdown-toggle ng-scope md-default-theme\" href=\"#\"> ");
        out.append("                                <i class=\"nav-icon icon-credit-card\"></i>Profesiones</a> ");
        out.append("                            <ul class=\"nav-dropdown-items\"> ");
        out.append("                                <li class=\"nav-item ng-scope\" > ");
        out.append("                                    <a class=\"nav-link\" href=\"../Profesiones/ListarProfesiones.jsp\"> ");
        out.append("                                        <i class=\"nav-icon icon-list\"></i>Listar Profesiones ");
        out.append("                                    </a> ");
        out.append("                                </li> ");
        out.append("                                <li class=\"nav-item ng-scope\"> ");
        out.append("                                    <a class=\"nav-link\" href=\"../Profesiones/crearProfesiones.jsp\"> ");
        out.append("                                        <i class=\"nav-icon icon-plus\"></i>Crear Profesión ");
        out.append("                                    </a> ");
        out.append("                                </li> ");
        out.append("                            </ul> ");
        out.append("                        </li> ");
        out.append("                        <li class=\"nav-item nav-dropdown\">	 ");
        out.append("                            <a class=\"nav-link nav-dropdown-toggle\" href=\"#\"> ");
        out.append("                                <i class=\"nav-icon icon-location-pin\"></i>Sucursales</a> ");
        out.append("                            <ul class=\"nav-dropdown-items\"> ");
        out.append("                                <li class=\"nav-item\"> ");
        out.append("                                    <a class=\"nav-link\" href=\"../Sucursales/ListarSucursales.jsp\"> ");
        out.append("                                        <i class=\"nav-icon icon-list\"></i>Listar Sucursales ");
        out.append("                                    </a> ");
        out.append("                                </li> ");
        out.append("                                <li class=\"nav-item\"> ");
        out.append("                                    <a class=\"nav-link\" href=\"../Sucursales/crearSucursales.jsp\"> ");
        out.append("                                        <i class=\"nav-icon icon-plus\"></i>Crear Sucursales ");
        out.append("                                    </a> ");
        out.append("                                </li> ");
        out.append("                            </ul> ");
        out.append("                        </li> ");
        out.append("                    </ul> ");
        out.append("                </li> ");
        out.append("                <li class=\"nav-item nav-dropdown\">               ");
        out.append("                    <a class=\"nav-link nav-dropdown-toggle\" href=\"#\"> ");
        out.append("                        <i class=\"nav-icon icon-people\"></i> Users</a> ");
        out.append("                    <ul class=\"nav-dropdown-items\"> ");
        out.append("                        <li class=\"nav-item\"> ");
        out.append("                            <a class=\"nav-link\" href=\"../Users/ListarUsers.jsp\"> ");
        out.append("                                <i class=\"nav-icon icon-list\"></i>Listar Users ");
        out.append("                            </a> ");
        out.append("                        </li> ");
        out.append("                        <li class=\"nav-item\"> ");
        out.append("                            <a class=\"nav-link\" href=\"../Users/crearUsers.jsp\"> ");
        out.append("                                <i class=\"nav-icon icon-plus\"></i>Crear Users ");
        out.append("                            </a> ");
        out.append("                        </li> ");
        out.append("                    </ul> ");
        out.append("                </li> ");
        return out.toString();
    }

    private String getOpcAtencion() {
        StringBuilder out = new StringBuilder();
        out.append("                <li class=\"nav-item\"> ");
        out.append("                    <a class=\"nav-link\" href=\"../Atencion/atencionList.jsp\"> ");
        out.append("                        <i class=\"nav-icon icon-eye\"></i> Atención</a> ");
        out.append("                </li> ");
        return out.toString();
    }

    private String getOpcRecepcion() {
        StringBuilder out = new StringBuilder();
        out.append("                <li class=\"nav-item nav-dropdown\">   ");
        out.append("                    <a class=\"nav-link nav-dropdown-toggle\" href=\"#\"> ");
        out.append("                        <i class=\"nav-icon icon-emotsmile\"></i> Recepción</a> ");
        out.append("                    <ul class=\"nav-dropdown-items\"> ");
        out.append("                        <li class=\"nav-item\"> ");
        out.append("                            <a class=\"nav-link\" href=\"../Pacientes/ListarPacientes.jsp\"> ");
        out.append("                                <i class=\"nav-icon icon-list\"></i>Listar Pacientes ");
        out.append("                            </a> ");
        out.append("                        </li> ");
        out.append("                        <li class=\"nav-item\"> ");
        out.append("                            <a class=\"nav-link\" href=\"../Pacientes/crearPacientes.jsp\"> ");
        out.append("                                <i class=\"nav-icon icon-plus\"></i>Crear Pacientes ");
        out.append("                            </a> ");
        out.append("                        </li> ");
        out.append("                        <li class=\"nav-item\"> ");
        out.append("                            <a class=\"nav-link\" href=\"../Atencion/MedicosList.jsp\"> ");
        out.append("                                <i class=\"nav-icon icon-list\"></i>Listas de Atención ");
        out.append("                            </a> ");
        out.append("                        </li> ");
        out.append("                    </ul>		 ");
        out.append("                </li> ");
        return out.toString();
    }

    private String getOpcFicha() {
        StringBuilder out = new StringBuilder();
        out.append("                <li class=\"nav-item nav-dropdown\"> ");
        out.append("                    <a class=\"nav-link nav-dropdown-toggle\" href=\"#\"> ");
        out.append("                        <i class=\"nav-icon icon-note\"></i> Fichas</a> ");
        out.append("                    <ul class=\"nav-dropdown-items\"> ");
        out.append("                        <li class=\"nav-item\"> ");
        out.append("                            <a class=\"nav-link\" href=\"../Fichas/listarFichas.jsp\"> ");
        out.append("                                <i class=\"nav-icon icon-list\"></i>Listar Fichas ");
        out.append("                            </a> ");
        out.append("                        </li> ");
        out.append("                        <li class=\"nav-item\"> ");
        out.append("                            <a class=\"nav-link\" href=\"../Fichas/ExportarFichas.jsp\"> ");
        out.append("                                <i class=\"nav-icon icon-arrow-down-circle\"></i>Expotar Fichas ");
        out.append("                            </a> ");
        out.append("                        </li>                             ");
        out.append("                    </ul> ");
        out.append("                </li> ");
        return out.toString();
    }

    private String getOpcExamenes() {
        StringBuilder out = new StringBuilder();
        out.append("                <li class=\"nav-item nav-dropdown\"> ");
        out.append("                    <a class=\"nav-link nav-dropdown-toggle\" href=\"#\"> ");
        out.append("                        <i class=\"nav-icon icon-paper-clip\"></i> Examenes</a> ");
        out.append("                    <ul class=\"nav-dropdown-items\"> ");
        out.append("                        <li class=\"nav-item\"> ");
        out.append("                            <a class=\"nav-link\" href=\"../Examenes/listarExamenes.jsp\"> ");
        out.append("                                <i class=\"nav-icon icon-list\"></i>Listar Examenes ");
        out.append("                            </a> ");
        out.append("                        </li> ");
        out.append("                        <li class=\"nav-item\"> ");
        out.append("                            <a class=\"nav-link\" href=\"../Examenes/crearExamenes.jsp\"> ");
        out.append("                                <i class=\"nav-icon icon-plus\"></i>Crear Examenes ");
        out.append("                            </a> ");
        out.append("                        </li> ");
        out.append("                    </ul> ");
        out.append("                </li> ");
        return out.toString();
    }

    private String getOpcHome() {
        StringBuilder out = new StringBuilder();
        out.append("                <li class=\"nav-item\"> ");
        out.append("                    <a class=\"nav-link\" href=\"../base/index.jsp\"> ");
        out.append("                        <i class=\"nav-icon icon-home\"></i> Home ");
        out.append("                    </a> ");
        out.append("                </li> ");
        return out.toString();
    }

    private String getOpcModulos() {

        StringBuilder out = new StringBuilder();
        out.append("                <li class=\"nav-title\">Modulos</li> ");
        return out.toString();
    }

}
