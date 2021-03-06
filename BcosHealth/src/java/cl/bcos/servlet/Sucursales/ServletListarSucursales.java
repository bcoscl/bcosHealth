/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bcos.servlet.Sucursales;

import cl.bcos.HttpRequest;
import cl.bcos.entity.SucursalesList;
import cl.bcos.entity.sucursales;
import cl.bcos.servlet.Suscripciones.ServletListarSuscripciones;
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
public class ServletListarSucursales extends HttpServlet {

    private static final Logger Log = Logger.getLogger(ServletListarSuscripciones.class);
//    private static final String ENDPOINT_PATH = "URLPATH";
//    private static final String PATH = "api.health.bcos.cl";   /* private static final String PATH = System.getenv(ENDPOINT_PATH);*/
//    private static String https = "https://";
    private static final String LISTAR_TABLA = "LS-TABLA";
    private static final String LISTAR_SELECT = "LS-SELECT";
    private static final String LISTAR_SELECT_MULTIPLE = "LS-SELECT-MULT";
    private static final String LISTAR_SELECT_MULTIPLE_BY_ACTIVE = "LS-SELECT-MULT-BY-ACTIVE";

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

//        if (PATH.contains("localhost")) {
//            https = "http://";
//        }
        String URL = ConstantsPath.CONTEXT + "/bcos/api/json/listarSucursales";
        Log.info(" URL :" + URL);
//            try {
        Map<String, String> parameter = new HashMap<String, String>();
        //parameter.put("planName", planName);
        parameter.put("accion", accion);
        parameter.put("token", token);
        parameter.put("empresasession", empresasession);

        String resultHttpRequest = "";
        try {
            resultHttpRequest = HttpRequest.HttpRequesPostMethod(URL, parameter, token);
            Log.info(resultHttpRequest);

            SucursalesList res = new Gson().fromJson(resultHttpRequest, SucursalesList.class);

            Log.info("res.message : " + res.getStatus().getMessage());
            Log.info("res.message : " + res.getStatus().getCode());
            Log.info("res.accion : " + accion);

            if (res.getStatus().getMessage().equalsIgnoreCase("SELECT_OK") && res.getStatus().getCode().equalsIgnoreCase("200")) {
                response.setStatus(200);
                try {
                    if (res.getSucursales().size() > 0) {

                        if (accion.equalsIgnoreCase(LISTAR_TABLA)) {
                            out.println(getSucursalesTabla(res));
                        } else if (accion.equalsIgnoreCase(LISTAR_SELECT)) {
                            Log.info("Select ");
                            out.println(getSucursalesSelect(res));
                        } else if (accion.equalsIgnoreCase(LISTAR_SELECT_MULTIPLE)
                                || accion.equalsIgnoreCase(LISTAR_SELECT_MULTIPLE_BY_ACTIVE)) {
                            Log.info("Select ");
                            out.println(getSucursalesMultiple(res));
                        }

                    }
                } catch (Exception e) {
                    Log.info(" NO EXISTEN SUSCRIPCIONES : " + e);
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

    private String getSucursalesTabla(SucursalesList res) {
        Log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        StringBuilder out = new StringBuilder();

        out.append("<div class=\"col-sm-12\">");
        out.append("<table class=\"table table-striped table-bordered datatable dataTable no-footer\" id=\"DataTables_Table_0\" role=\"grid\" aria-describedby=\"DataTables_Table_0_info\" style=\"border-collapse: collapse !important\">");
        out.append("<thead>");
        out.append("<tr role=\"row\">");
        out.append("<th class=\"sorting_asc\" tabindex=\"0\" aria-controls=\"DataTables_Table_0\" rowspan=\"1\" colspan=\"1\" aria-sort=\"ascending\" aria-label=\"Username: activate to sort column descending\" style=\"width: 10%;\">Sucursal</th>");
        out.append("<th class=\"sorting d-none d-sm-table-cell\" tabindex=\"0\" aria-controls=\"DataTables_Table_0\" rowspan=\"1\" colspan=\"1\" aria-label=\"Date registered: activate to sort column ascending\" style=\"width: 10%;\">comuna</th>");
        out.append("<th class=\"sorting d-none d-sm-table-cell\" tabindex=\"0\" aria-controls=\"DataTables_Table_0\" rowspan=\"1\" colspan=\"1\" aria-label=\"Date registered: activate to sort column ascending\" style=\"width: 10%;\">Télefono</th>");
        out.append("<th class=\"sorting d-none d-sm-table-cell\" tabindex=\"0\" aria-controls=\"DataTables_Table_0\" rowspan=\"1\" colspan=\"1\" aria-label=\"Date registered: activate to sort column ascending\" style=\"width: 10%;\">email</th>");

        out.append("<th class=\"sorting d-none d-sm-table-cell\" tabindex=\"0\" aria-controls=\"DataTables_Table_0\" rowspan=\"1\" colspan=\"1\" aria-label=\"Date registered: activate to sort column ascending\" style=\"width: 15%;\">contacto</th>");
        out.append("<th class=\"sorting d-none d-sm-table-cell\" tabindex=\"0\" aria-controls=\"DataTables_Table_0\" rowspan=\"1\" colspan=\"1\" aria-label=\"Role: activate to sort column ascending\" style=\"width: 10%;\">Empresa</th>");
        out.append("<th class=\"sorting \" tabindex=\"0\" aria-controls=\"DataTables_Table_0\" rowspan=\"1\" colspan=\"1\" aria-label=\"Status: activate to sort column ascending\" style=\"width: 10%;\">Estado</th>");
        //<!--<th class=\"sorting\" tabindex=\"0\" aria-controls=\"DataTables_Table_0\" rowspan=\"1\" colspan=\"1\" aria-label=\"Actions: activate to sort column ascending\" style=\"width: 5%;\">Acciones</th>-->" 
        out.append("</tr>");
        out.append("</thead>");
        out.append("<tbody class=\"contenidobusqueda\">");

        for (sucursales str : res.getSucursales()) {

            out.append("<tr role=\"row\" class=\"odd\">");
            out.append("<td class=\"sorting_1 \">");
            out.append(str.getSuc_c_nombre());
            out.append("</td>");
            out.append("<td class=\"d-none d-sm-table-cell\">");
            out.append(str.getSuc_c_comuna());
            out.append("</td>");
            out.append("<td class=\"d-none d-sm-table-cell\"><a href=\"tel:");
            out.append(str.getSuc_c_telefono());
            out.append("\">");
            out.append(str.getSuc_c_telefono());
            out.append("</a> </td>");

            out.append("<td class=\"d-none d-sm-table-cell\"><a href=\"mailto:");
            out.append(str.getSuc_c_email());
            out.append("\">");
            out.append(str.getSuc_c_email());
            out.append("</a></td>");

            out.append("<td class=\"d-none d-sm-table-cell\">");
            out.append(str.getSuc_c_contacname());
            out.append("</td>");
            out.append("<td class=\"d-none d-sm-table-cell\">");
            out.append(str.getSuc_c_nombre_empresa());
            out.append("</td>");
            out.append("<td >");

            out.append("<label  class=\"switch switch-label switch-pill switch-success\">");
            out.append("<input onclick=\"change('");
            out.append(str.getSuc_n_cod());
            out.append("');\" class=\"switch-input \" type=\"checkbox\" ");
            out.append(" id=\"CL_");
            out.append(str.getSuc_n_cod());
            out.append("\"");
            if (str.getSuc_c_estado().equalsIgnoreCase("TRUE")) {
                out.append(" checked ");
                //out.append(str.getEstado());

            }
            out.append("/>");

            out.append("<span  id=\"checkcontroller_");
            out.append(str.getSuc_n_cod());
            out.append("\" class=\"switch-slider\" data-checked=\"on\" data-unchecked=\"off\" ");
            //out.append(" id=\"span_");
            //out.append(str.getId());
            out.append("\"></span>");
            out.append("</label>");

            out.append("</td>");

            //out.append("<!--<td>\n" +
            //out.append("<a class=\"btn btn-success\" href=\"#\">\n" +
            //out.append("<i class=\"fa fa-search-plus\"></i>\n" +
            //out.append("</a>\n" +
            //out.append("<a class=\"btn btn-info\" href=\"#\">\n" +
            //out.append("<i class=\"fa fa-edit\"></i>\n" +
            //out.append("</a>\n" +
            //out.append("<a class=\"btn btn-danger\" href=\"#\">\n" +
            //out.append("<i class=\"fa fa-trash-o\"></i>\n" +
            //out.append("</a>\n" +
            //out.append("</td>-->\n" +
            out.append("</tr>");

        }

        out.append("</tbody>");
        out.append("</table>");
        out.append("</div>");
        return out.toString();

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

    private String getSucursalesSelect(SucursalesList res) {

        Log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        StringBuilder out = new StringBuilder();

        out.append("<option value=\"\">Seleccione Sucursal</option>");

        for (sucursales str : res.getSucursales()) {

            out.append("<option value=\"");
            out.append(str.getSuc_n_cod());
            out.append("\">");
            out.append(str.getSuc_c_nombre());
            out.append("</option>");

        }

        //out.append(" </div>");
        return out.toString();
    }

    private String getSucursalesMultiple(SucursalesList res) {

        Log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        StringBuilder out = new StringBuilder();

        out.append("<select class=\"selectpicker\" multiple id=\"sucursal_select\" required>");

        for (sucursales str : res.getSucursales()) {

            out.append("<option>[ ");
            out.append(str.getSuc_c_nombre());
            out.append(" ]</option>");

        }
        Log.debug(out.toString());
        out.append("</select>");
        return out.toString();
    }

}
