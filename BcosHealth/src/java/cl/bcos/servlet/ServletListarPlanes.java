/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bcos.servlet;

import cl.bcos.HttpRequest;
import cl.bcos.entity.Planes;
import cl.bcos.entity.PlanesList;
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
public class ServletListarPlanes extends HttpServlet {

    private static final Logger Log = Logger.getLogger(ServletListarPlanes.class);

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
        response.setContentType("text/html;charset=UTF-8");

        HttpSession tokensession = request.getSession(true);
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

        String URL = "http://localhost:9090/bcos/api/json/listarPlanes";
//            try {
        Map<String, String> parameter = new HashMap<String, String>();
        //parameter.put("planName", planName);
        //parameter.put("userMax", userMax);
        parameter.put("token", token);

        String resultHttpRequest = "";
        try {
            resultHttpRequest = HttpRequest.HttpRequesPostMethod(URL, parameter, token);
            Log.info(resultHttpRequest);
            PlanesList res = new Gson().fromJson(resultHttpRequest, PlanesList.class);

            Log.info("res.message : " + res.getStatus().getMessage());
            Log.info("res.message : " + res.getStatus().getCode());

            if (res.getStatus().getMessage().equalsIgnoreCase("SELECT_OK") && res.getStatus().getCode().equalsIgnoreCase("200")) {
                response.setStatus(200);
                try {
                    if (res.getPlanes().size() > 0) {

                        if (accion.equalsIgnoreCase("LP-TABLA")) {
                            out.println(getPlanesTabla(res));
                        } else if (accion.equalsIgnoreCase("LP-SELECT")) {
                            out.println(getPlanesSelect(res));
                        }

                    }
                } catch (Exception e) {
                    Log.error(" NO Existen Planes");

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

            } else if (res.getStatus().getMessage().equalsIgnoreCase("TOKEN_NO_VALIDO") && res.getStatus().getCode().equalsIgnoreCase("401")) {
                Log.info("TOKEN_NO_VALIDO");
                response.setStatus(401);

            } else {
                Log.info("SELECT_NO_OK");
                response.setStatus(400);  // 400 Bad Request - no se eejcuto el insert  
            }

        } catch (IOException e) {
            Log.error(e);
            response.setStatus(404);
        }
        // Log.info("Result Call Api - " + resultHttpRequest);

        /* TODO output your page here. You may use following sample code. */
    }

    private String getPlanesTabla(PlanesList res) {

        StringBuilder out = new StringBuilder();

        out.append("<div class=\"col-sm-12\">");
        out.append("  <table class=\"table table-striped table-bordered datatable dataTable no-footer\" id=\"DataTables_Table_0\" role=\"grid\" aria-describedby=\"DataTables_Table_0_info\" style=\"border-collapse: collapse !important\">");
        out.append("   <thead>");
        out.append("  <tr role=\"row\">");
        out.append("  <th class=\"sorting_asc\" tabindex=\"0\" aria-controls=\"DataTables_Table_0\" rowspan=\"1\" colspan=\"1\" aria-sort=\"ascending\" aria-label=\"Username: activate to sort column descending\" style=\"width: 20%;\">Nombre del Plan</th>");
        out.append("  <th class=\"sorting\" tabindex=\"0\" aria-controls=\"DataTables_Table_0\" rowspan=\"1\" colspan=\"1\" aria-label=\"Date registered: activate to sort column ascending\" style=\"width: 20%;\">Maximo de usuarios</th>");
        out.append("   <th class=\"sorting d-none d-sm-table-cell\" tabindex=\"0\" aria-controls=\"DataTables_Table_0\" rowspan=\"1\" colspan=\"1\" aria-label=\"Date registered: activate to sort column ascending\" style=\"width: 20%;\">Fecha Creacion</th>");
        out.append("  <th class=\"sorting d-none d-sm-table-cell\" tabindex=\"0\" aria-controls=\"DataTables_Table_0\" rowspan=\"1\" colspan=\"1\" aria-label=\"Date registered: activate to sort column ascending\" style=\"width: 20%;\">Creado por</th>	");
        //    + //"                                            <th class=\"sorting\" tabindex=\"0\" aria-controls=\"DataTables_Table_0\" rowspan=\"1\" colspan=\"1\" aria-label=\"Actions: activate to sort column ascending\" style=\"width: 2%;\">Acciones</th>\n" +
        out.append("  </tr>");
        out.append("  </thead>");
        out.append("  <tbody class=\"contenidobusqueda\">");

        for (Planes str : res.getPlanes()) {
            //imprimimos el objeto pivote
//                            Log.info("Planes NOMBRE : " + str.getNombrePlan());
//                            Log.info("Planes MAXIMO: " + str.getNumeroMax());
//                            Log.info("Planes FECHA: " + str.getFechaCreacion());
//                            Log.info("Planes CREADOR: " + str.getNombreCreador());
            out.append("<tr role=\"row\" class=\"odd\">");
            out.append("<td class=\"sorting_1 \">");
            out.append(str.getNombrePlan());
            out.append("</td>");
            out.append(" <td >");
            out.append(str.getNumeroMax());
            out.append("</td>");

            out.append("<td class=\"d-none d-sm-table-cell\">");
            out.append(str.getFechaCreacion());
            out.append("</td>");
            out.append("<td class=\"d-none d-sm-table-cell\">");
            out.append(str.getNombreCreador());
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
// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    private String getPlanesSelect(PlanesList res) {

        StringBuilder out = new StringBuilder();

        out.append("<option value=\"0\">Seleccione un plan</option>");
       

        for (Planes str : res.getPlanes()) {

            out.append("<option value=\"");
            out.append(str.getNumeroMax()); 
            out.append("\">");
            out.append(str.getNombrePlan());
            out.append("</option>");
            

        }

        //out.append(" </div>");
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
