/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bcos.servlet;

import cl.bcos.HttpRequest;
import cl.bcos.entity.Param;
import cl.bcos.entity.ParamList;
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
public class ServletListarConfiguraciones extends HttpServlet {

    private static final Logger Log = Logger.getLogger(ServletListarSuscripciones.class);
    private static final String ENDPOINT_PATH = "URLPATH";
    private static final String PATH = System.getProperty(ENDPOINT_PATH,System.getenv(ENDPOINT_PATH));
    private static String https = "https://";
    private static final String LISTAR_TABLA = "LC-TABLA";
    //private static final String LISTAR_SELECT = "LS-SELECT";
    // private static final String LISTAR_SELECT_BY = "LS-SELECT-BY-EMPRESA";

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

        if(PATH.contains("localhost")){https = "http://";}String URL = https +  PATH + "/bcos/api/json/listarParam";
//            try {
        Map<String, String> parameter = new HashMap<String, String>();
        parameter.put("accion", accion);
        //parameter.put("userMax", userMax);
        parameter.put("empresasession", empresasession);
        parameter.put("token", token);

        String resultHttpRequest = "";
        try {
            resultHttpRequest = HttpRequest.HttpRequesPostMethod(URL, parameter, token);
            Log.info(resultHttpRequest);

            ParamList res = new Gson().fromJson(resultHttpRequest, ParamList.class);

            Log.info("res.message : " + res.getStatus().getMessage());
            Log.info("res.message : " + res.getStatus().getCode());
            Log.info("res.accion : " + accion);

            if (res.getStatus().getMessage().equalsIgnoreCase("SELECT_OK") && res.getStatus().getCode().equalsIgnoreCase("200")) {
                response.setStatus(200);
                try {
                    if (res.getParam().size() > 0) {

                        if (accion.equalsIgnoreCase(LISTAR_TABLA)) {
                            out.println(getParamsTabla(res));
                        } else {
                            Log.error(" NO SOPORTADO ");
                        }

                    }
                } catch (Exception e) {
                    Log.error("sin registros : " + e);
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

    private String getParamsTabla(ParamList res) {
        Log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        StringBuilder out = new StringBuilder();

        out.append("<div class=\"col-sm-12\">");
        out.append("<table class=\"table table-striped table-bordered datatable dataTable no-footer\" id=\"DataTables_Table_0\" role=\"grid\" aria-describedby=\"DataTables_Table_0_info\" style=\"border-collapse: collapse !important\">");
        out.append("<thead>");
        out.append("<tr role=\"row\">");
        out.append("<th class=\"sorting_asc\" tabindex=\"0\" aria-controls=\"DataTables_Table_0\" rowspan=\"1\" colspan=\"1\" aria-sort=\"ascending\" aria-label=\"Username: activate to sort column descending\" style=\"width: 5%;\">grupo</th>");
        out.append("<th class=\"sorting d-none d-sm-table-cell\" tabindex=\"0\" aria-controls=\"DataTables_Table_0\" rowspan=\"1\" colspan=\"1\" aria-label=\"Date registered: activate to sort column ascending\" style=\"width: 5%;\">sub grupo</th>");
        out.append("<th class=\"sorting d-none d-sm-table-cell\" tabindex=\"0\" aria-controls=\"DataTables_Table_0\" rowspan=\"1\" colspan=\"1\" aria-label=\"Date registered: activate to sort column ascending\" style=\"width: 10%;\">param1</th>");
        out.append("<th class=\"sorting d-none d-sm-table-cell\" tabindex=\"0\" aria-controls=\"DataTables_Table_0\" rowspan=\"1\" colspan=\"1\" aria-label=\"Date registered: activate to sort column ascending\" style=\"width: 10%;\">param2</th>");
        out.append("<th class=\"sorting d-none d-sm-table-cell\" tabindex=\"0\" aria-controls=\"DataTables_Table_0\" rowspan=\"1\" colspan=\"1\" aria-label=\"Date registered: activate to sort column ascending\" style=\"width: 10%;\">param3</th>");
        out.append("<th class=\"sorting d-none d-sm-table-cell\" tabindex=\"0\" aria-controls=\"DataTables_Table_0\" rowspan=\"1\" colspan=\"1\" aria-label=\"Date registered: activate to sort column ascending\" style=\"width: 10%;\">param4</th>");
//        out.append("<th class=\"sorting d-none d-sm-table-cell\" tabindex=\"0\" aria-controls=\"DataTables_Table_0\" rowspan=\"1\" colspan=\"1\" aria-label=\"Date registered: activate to sort column ascending\" style=\"width: 10%;\">creado por </th>");
//        out.append("<th class=\"sorting d-none d-sm-table-cell\" tabindex=\"0\" aria-controls=\"DataTables_Table_0\" rowspan=\"1\" colspan=\"1\" aria-label=\"Date registered: activate to sort column ascending\" style=\"width: 10%;\">Fecha creacion</th>");

        out.append("<th class=\"sorting \" tabindex=\"0\" aria-controls=\"DataTables_Table_0\" rowspan=\"1\" colspan=\"1\" aria-label=\"Status: activate to sort column ascending\" style=\"width: 50%;\">Acciones</th>");
        //<!--<th class=\"sorting\" tabindex=\"0\" aria-controls=\"DataTables_Table_0\" rowspan=\"1\" colspan=\"1\" aria-label=\"Actions: activate to sort column ascending\" style=\"width: 5%;\">Acciones</th>-->" 
        out.append("</tr>");
        out.append("</thead>");
        out.append("<tbody class=\"contenidobusqueda\">");

        for (Param str : res.getParam()) {

            out.append("<tr role=\"row\" class=\"odd\">");

            out.append("<td class=\"sorting_1 \">");
            out.append(str.getParams_n_grupo());
            out.append("</td>");
            out.append("<td class=\"sorting_1 \">");
            out.append(str.getParams_n_subgrupo());
            out.append("</td>");
            out.append("<td class=\"sorting_1 \">");
            out.append(str.getParams_n_param1());
            out.append("</td>");
            out.append("<td class=\"sorting_1 \">");
            out.append(str.getParams_n_param2());
            out.append("</td>");
            out.append("<td class=\"sorting_1 \">");
            out.append(str.getParams_n_param3());
            out.append("</td>");
            out.append("<td class=\"sorting_1 \">");
            out.append(str.getParams_n_param4());
            out.append("</td>");
//            out.append("<td class=\"sorting_1 \">");
//            out.append(str.getParams_c_nombre_ultmod());
//            out.append("</td>");
//            out.append("<td class=\"sorting_1 \">");
//            out.append(str.getParams_d_ultmod());
//            out.append("</td>");

            out.append("<td> ");
            out.append("<a class=\"btn btn-success\" href=\"javascript:copyParam('");
            out.append(str.getParams_n_grupo());
            out.append("','");
            out.append(str.getParams_n_subgrupo());
            out.append("','");
            out.append(str.getParams_n_param1());
            out.append("','");
            out.append(str.getParams_n_param2());
            out.append("','");
            out.append(str.getParams_n_param3());
            out.append("','");
            out.append(str.getParams_n_param4());
            out.append("');\"> ");

            out.append("<i class=\"fa fa-copy\"></i> ");
            out.append("</a> ");
            out.append("<a class=\"btn btn-info\" href=\"javascript:EditParam('");
            out.append(str.getParams_n_grupo());
            out.append("','");
            out.append(str.getParams_n_subgrupo());
            out.append("','");
            out.append(str.getParams_n_param1());
            out.append("','");
            out.append(str.getParams_n_param2());
            out.append("','");
            out.append(str.getParams_n_param3());
            out.append("','");
            out.append(str.getParams_n_param4());
            out.append("');\"> ");

            out.append("<i class=\"fa fa-edit\"></i> ");
            out.append("</a> ");
            out.append("<a class=\"btn btn-danger\" href=\"javascript:deleteParam(");
            out.append(str.getParams_n_id());
            out.append(");\"> ");
            out.append("<i class=\"fa fa-trash-o\"></i> ");
            out.append("</a> ");
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

}
