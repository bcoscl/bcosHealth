/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bcos.servlet;

import cl.bcos.HttpRequest;
import cl.bcos.entity.Roles;
import cl.bcos.entity.RolesList;
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
public class ServletListarRoles extends HttpServlet {

    private static final Logger Log = Logger.getLogger(ServletListarRoles.class);
    private static final String ENDPOINT_PATH = "URLPATH";
    private static final String PATH = System.getProperty(ENDPOINT_PATH,System.getenv(ENDPOINT_PATH));
    private static String https = "https://";
    private static final String LISTAR_TABLA = "LR-TABLA";
    private static final String LISTAR_SELECT = "LR-SELECT";

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

        Log.info("accion :" + accion);
        Log.info(request);
        //Log.info("planname : " + planName);
        //Log.info("User MAX :" + userMax);
        Log.info("token bearer:" + token);

        if(PATH.contains("localhost")){https = "http://";}String URL = https+PATH+ "/bcos/api/json/listarRoles";
//            try {
        Map<String, String> parameter = new HashMap<String, String>();
        //parameter.put("planName", planName);
        //parameter.put("userMax", userMax);
        parameter.put("empresasession", empresasession);
        parameter.put("token", token);

        String resultHttpRequest = "";
        try {
            resultHttpRequest = HttpRequest.HttpRequesPostMethod(URL, parameter, token);
            Log.info(resultHttpRequest);
            RolesList res = new Gson().fromJson(resultHttpRequest, RolesList.class);

            Log.info("res.message : " + res.getStatus().getMessage());
            Log.info("res.message : " + res.getStatus().getCode());

            if (res.getStatus().getMessage().equalsIgnoreCase("SELECT_OK") && res.getStatus().getCode().equalsIgnoreCase("200")) {
                response.setStatus(200);
                try {
                    if (res.getRoles().size() > 0) {

                        if (accion.equalsIgnoreCase(LISTAR_TABLA)) {
                            out.println(getRolesTabla(res));
                        } else if (accion.equalsIgnoreCase(LISTAR_SELECT)) {
                            out.println(getRolesSelect(res));
                        }

                    }
                } catch (Exception e) {
                    Log.error(" NO Existen Regisros");
                    Log.error(e);
                    if (accion.equalsIgnoreCase(LISTAR_TABLA)) {
                        out.println("<div class=\"col-sm-12\">");
                        out.println("  <table class=\"table table-striped table-bordered datatable dataTable no-footer\" id=\"DataTables_Table_0\" role=\"grid\" aria-describedby=\"DataTables_Table_0_info\" style=\"border-collapse: collapse !important\">");
                        out.println("   <thead>");
                        out.println("  <tr role=\"row\">");
                        out.println("  <th class=\"sorting d-none d-sm-table-cell\" tabindex=\"0\" aria-controls=\"DataTables_Table_0\" rowspan=\"1\" colspan=\"1\" aria-label=\"Date registered: activate to sort column ascending\" style=\"width: 20%;\">Registros</th>	");
                        out.println("  </tr>");
                        out.println("  </thead>");
                        out.println("  <tbody class=\"contenidobusqueda\">");
                        out.println("<tr role=\"row\" class=\"odd\">");
                        out.println("<td class=\"sorting_1 \">");
                        out.println("Sin Registros..");
                        out.println("</td>");
                        out.println(" </tr>\n");
                        out.println(" </tbody>");
                        out.println(" </table>");
                        out.println(" </div>");
                    } else if (accion.equalsIgnoreCase(LISTAR_SELECT)) {
                        out.println("No existen roles creados");
                    }
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

    private String getRolesTabla(RolesList res) {

        Log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        StringBuilder out = new StringBuilder();

        String dibujaEmpresa = res.getStatus().getMessage();
        String empresaName = "";

        out.append("<div class=\"col-sm-12\">");
        out.append("  <table class=\"table table-striped table-bordered datatable dataTable no-footer\" id=\"DataTables_Table_0\" role=\"grid\" aria-describedby=\"DataTables_Table_0_info\" style=\"border-collapse: collapse !important\">");
        out.append("   <thead>");
        out.append("  <tr role=\"row\">");
        out.append("  <th class=\"sorting_asc\" tabindex=\"0\" aria-controls=\"DataTables_Table_0\" rowspan=\"1\" colspan=\"1\" aria-sort=\"ascending\" aria-label=\"Username: activate to sort column descending\" style=\"width: 20%;\">Nombre del ROL</th>");

        //dibuja la empresa en el caso de ser SUPER ADMIN
        if (dibujaEmpresa.equalsIgnoreCase("SI")) {
            out.append("  <th class=\"sorting_asc\" tabindex=\"0\" aria-controls=\"DataTables_Table_0\" rowspan=\"1\" colspan=\"1\" aria-sort=\"ascending\" aria-label=\"Username: activate to sort column descending\" style=\"width: 20%;\">Empresa</th>");

        }

        out.append("   <th class=\"sorting d-none d-sm-table-cell\" tabindex=\"0\" aria-controls=\"DataTables_Table_0\" rowspan=\"1\" colspan=\"1\" aria-label=\"Date registered: activate to sort column ascending\" style=\"width: 20%;\">Fecha Creación</th>");
        out.append("  <th class=\"sorting d-none d-sm-table-cell\" tabindex=\"0\" aria-controls=\"DataTables_Table_0\" rowspan=\"1\" colspan=\"1\" aria-label=\"Date registered: activate to sort column ascending\" style=\"width: 20%;\">Creado por</th>	");
        //    + //"                                            <th class=\"sorting\" tabindex=\"0\" aria-controls=\"DataTables_Table_0\" rowspan=\"1\" colspan=\"1\" aria-label=\"Actions: activate to sort column ascending\" style=\"width: 2%;\">Acciones</th>\n" +
        out.append("  </tr>");
        out.append("  </thead>");
        out.append("  <tbody class=\"contenidobusqueda\">");

        for (Roles str : res.getRoles()) {
            //imprimimos el objeto pivote
//                            Log.info("Planes NOMBRE : " + str.getNombrePlan());
//                            Log.info("Planes MAXIMO: " + str.getNumeroMax());
//                            Log.info("Planes FECHA: " + str.getFechaCreacion());
//                            Log.info("Planes CREADOR: " + str.getNombreCreador());
            out.append("<tr role=\"row\" class=\"odd\">");
            out.append("<td class=\"sorting_1 \">");
            out.append(str.getRol_c_rolename());
            out.append("</td>");

            //dibuja la empresa en el caso de ser SUPER ADMIN
            if (dibujaEmpresa.equalsIgnoreCase("SI")) {
                out.append("<td class=\"sorting_1 \">");
                if (!str.getRol_c_empresaname().equalsIgnoreCase("")) {
                    empresaName = " ";
                } else {
                    empresaName = str.getRol_c_empresaname();
                }
                out.append(empresaName);
                out.append("</td>");
            }

            out.append("<td class=\"d-none d-sm-table-cell\">");
            out.append(str.getRol_d_createdate());
            out.append("</td>");
            out.append("<td class=\"d-none d-sm-table-cell\">");
            out.append(str.getRol_c_createusername());
            out.append("</td>");

            //" <td>\n" +
            //"      <!--<a class=\"btn btn-success\" href=\"#\">\n" +
            //"     <i class=\"fa fa-search-plus\"></i>\n" +
            //"       </a>-->\n" +
            //"      <a class=\"btn btn-info\" href=\"#\">\n" +
            //"    <i class=\"fa fa-edit\"></i>\n" +
            //"   </a>\n" +
            //"   <!--<a class=\"btn btn-danger\" href=\"#\">\n" +
            //"   <i class=\"fa fa-trash-o\"></i>\n" +
            //"   </a>-->\n" +
            //"  </td>\n" +
            out.append(" </tr>\n");

        }
        out.append(" </tbody>");
        out.append(" </table>");
        out.append(" </div>");

        return out.toString();

    }

    private String getRolesSelect(RolesList res) {
        Log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        StringBuilder out = new StringBuilder();

        out.append("<select class=\"selectpicker\" multiple id=\"roles_select\" required>");

        for (Roles str : res.getRoles()) {

            out.append("<option>[ ");
            out.append(str.getRol_c_rolename());
            out.append(" ]</option>");

        }
        Log.debug(out.toString());
        out.append("</select>");
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
