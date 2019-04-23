/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bcos.servlet;

import cl.bcos.HttpRequest;
import cl.bcos.entity.Examenes;
import cl.bcos.entity.ExamenesList;
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
public class ServletListarExamenes extends HttpServlet {

    private static final Logger Log = Logger.getLogger(ServletListarExamenes.class);
    private static final String CE_EXAMENES_PROFILE = "CE-EXAMENES-PROFILE";
    private static final String LE_TABLA = "LE-TABLA";
    private static final String LE_BY_PACIENTE_TABLA = "LE-TABLA-BY-PACIENTE";

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

        String token = (String) tokensession.getAttribute("token");
        String accion = (String) request.getParameter("accion");
        String numuser = (String) request.getParameter("numuser_paciente");
        Log.debug("numuser_paciente : "+numuser);

        if (accion.equalsIgnoreCase(LE_BY_PACIENTE_TABLA)) {
            tokensession.setAttribute("PACIENTE",numuser);
            Log.debug("rut seteado desde el ingreso");

        }
      

        String Paciente = (String) tokensession.getAttribute("PACIENTE");
        Log.info("accion :" + accion);
        Log.info("Paciente :" + Paciente);
        Log.info(request);

        Log.info("token bearer:" + token);

        String URL = "http://localhost:9090/bcos/api/json/listarExamenes";
//            try {
        Map<String, String> parameter = new HashMap<String, String>();
        parameter.put("accion", accion);
        parameter.put("Paciente", Paciente);
        parameter.put("token", token);

        String resultHttpRequest = "";
        try {
            resultHttpRequest = HttpRequest.HttpRequesPostMethod(URL, parameter, token);
            Log.info(resultHttpRequest);
            ExamenesList res = new Gson().fromJson(resultHttpRequest, ExamenesList.class);

            Log.info("res.message : " + res.getStatus().getMessage());
            Log.info("res.message : " + res.getStatus().getCode());

            if (res.getStatus().getMessage().equalsIgnoreCase("SELECT_OK") && res.getStatus().getCode().equalsIgnoreCase("200")) {
                response.setStatus(200);
                try {

                    if (res.getExamenes().size() > 0) {
                        Log.info("getExamenesProfileList");
                        if (accion.equalsIgnoreCase(CE_EXAMENES_PROFILE)) {
                            out.println(getExamenesProfileList(res));
                        } else if (accion.equalsIgnoreCase(LE_TABLA) || accion.equalsIgnoreCase(LE_BY_PACIENTE_TABLA)) {
                            Log.info("getExamenesTabla ");
                            out.println(getExamenesTabla(res));
                        }

                    }
                } catch (Exception e) {
                    Log.error(" NO EXISTEN REGISTROS: " + e);

                    if (accion.equalsIgnoreCase(CE_EXAMENES_PROFILE)) {
                        out.println("<span>Sin Registros..</span>");

                    } else {
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
                        out.println("<span>Sin Registros..</span>");
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

    private String getExamenesProfileList(ExamenesList res) {
        Log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        StringBuilder out = new StringBuilder();

        //Nombre, rut,  profesion,telefono,email, roles
        //about me, estado
        String examenname = "";
        for (Examenes str : res.getExamenes()) {

            examenname = str.getExa_c_name();
            if (examenname.isEmpty()) {
                examenname = " sin Informacion ";
            }

            out.append(" <div class=\"profile-activity clearfix\"> ");
            out.append("        <img class=\"pull-left\" alt=\"ecronica-icon\" src=\"../../pages/Examenes/img/examenes-icono.png\" with=\"40px\" height=\"40px\" onclick=\"javascript:popupVerExamenes(1);\"> ");
            out.append("        <a class=\"user\" href=\"javascript:void(0);\" id=\"dropdownMenuButton\" class=\"dropdown dropdown-toggle\" data-toggle=\"dropdown\">");
            out.append(examenname);
            out.append("        </a>  ");
            out.append("         <div class=\"dropdown-menu\" aria-labelledby=\"dropdownMenuButton\">");
            out.append("             <a class=\"dropdown-item\" onclick=\"javascript:popupVerExamenes(");
            out.append(str.getExa_n_id());
            out.append(")\">Ver</a>");
            out.append("             <a class=\"dropdown-item\" onclick=\"javascript:popupEditExamenes('");
            out.append(str.getExa_c_name());
            out.append("','");
            out.append(str.getExa_c_obs());
            out.append("',");
            out.append(str.getExa_n_id());
            out.append(")\">Modificar</a>");
            out.append("             <a class=\"dropdown-item\" onclick=\"javascript:popupDeleteExamenes(");
            out.append(str.getExa_n_id());
            out.append(")\">quitar de la lista</a>");
            out.append("         </div>");
            out.append("            <span><strong><em>- Observación : </em></strong>");
            out.append(str.getExa_c_obs());
            out.append("</span>");
            out.append("            <div class=\"time\">");
            out.append("              <i class=\"ace-icon fa fa-clock-o bigger-110\"></i>");
            out.append("               <span> registro : ");
            out.append(str.getExa_d_ultmod_date());
            out.append("</span>");
            out.append("            </div>");
            out.append("  </div>  ");

        }

        return out.toString();

    }

    private String getExamenesTabla(ExamenesList res) {
        Log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        StringBuilder out = new StringBuilder();

        out.append("<div class=\"col-sm-12\">");
        out.append("<table class=\"table table-striped table-bordered datatable dataTable no-footer\" id=\"DataTables_Table_0\" role=\"grid\" aria-describedby=\"DataTables_Table_0_info\" style=\"border-collapse: collapse !important\">");
        out.append("<thead>");
        out.append("<tr role=\"row\">");
        out.append("<th class=\"sorting_asc\" tabindex=\"0\" aria-controls=\"DataTables_Table_0\" rowspan=\"1\" colspan=\"1\" aria-sort=\"ascending\" aria-label=\"Username: activate to sort column descending\" style=\"width: 10%;\">Rut</th>");
        out.append("<th class=\"sorting_asc\" tabindex=\"0\" aria-controls=\"DataTables_Table_0\" rowspan=\"1\" colspan=\"1\" aria-sort=\"ascending\" aria-label=\"Username: activate to sort column descending\" style=\"width: 20%;\">Paciente</th>");
        out.append("<th class=\"sorting d-none d-sm-table-cell\" tabindex=\"0\" aria-controls=\"DataTables_Table_0\" rowspan=\"1\" colspan=\"1\" aria-label=\"Date registered: activate to sort column ascending\" style=\"width: 25%;\">Examen</th>");
        out.append("<th class=\"sorting d-none d-sm-table-cell\" tabindex=\"0\" aria-controls=\"DataTables_Table_0\" rowspan=\"1\" colspan=\"1\" aria-label=\"Date registered: activate to sort column ascending\" style=\"width: 30%;\">Dr ultima Atención</th>");
        out.append("<th class=\"sorting d-none d-sm-table-cell\" tabindex=\"0\" aria-controls=\"DataTables_Table_0\" rowspan=\"1\" colspan=\"1\" aria-label=\"Date registered: activate to sort column ascending\" style=\"width: 20%;\">Fecha Ult. Modificación</th>");
        out.append("<th class=\"sorting\" tabindex=\"0\" aria-controls=\"DataTables_Table_0\" rowspan=\"1\" colspan=\"1\" aria-label=\"Date registered: activate to sort column ascending\" style=\"width: 5%;\">Detalle</th>");

//        out.append("<th class=\"sorting d-none d-sm-table-cell\" tabindex=\"0\" aria-controls=\"DataTables_Table_0\" rowspan=\"1\" colspan=\"1\" aria-label=\"Date registered: activate to sort column ascending\" style=\"width: 15%;\">contacto</th>");
//        out.append("<th class=\"sorting d-none d-sm-table-cell\" tabindex=\"0\" aria-controls=\"DataTables_Table_0\" rowspan=\"1\" colspan=\"1\" aria-label=\"Role: activate to sort column ascending\" style=\"width: 10%;\">Empresa</th>");
//        out.append("<th class=\"sorting \" tabindex=\"0\" aria-controls=\"DataTables_Table_0\" rowspan=\"1\" colspan=\"1\" aria-label=\"Status: activate to sort column ascending\" style=\"width: 10%;\">Estado</th>");
        //<!--<th class=\"sorting\" tabindex=\"0\" aria-controls=\"DataTables_Table_0\" rowspan=\"1\" colspan=\"1\" aria-label=\"Actions: activate to sort column ascending\" style=\"width: 5%;\">Acciones</th>-->" 
        out.append("</tr>");
        out.append("</thead>");
        out.append("<tbody class=\"contenidobusqueda\">");

        for (Examenes str : res.getExamenes()) {

            out.append("<tr role=\"row\" class=\"odd\">");

            out.append("<td class=\"sorting_1 \">");
            out.append(str.getExa_c_numuser_paciente());
            out.append("</td>");

            out.append("<td class=\"sorting_1 \">");
            out.append(str.getExa_c_paciente_name());
            out.append("</td>");

            out.append("<td class=\"d-none d-sm-table-cell\">");
            out.append(str.getExa_c_name());
            out.append("</td>");

            out.append("<td class=\"d-none d-sm-table-cell\">");
            out.append(str.getExa_c_ultmod_username());
            out.append("</td>");

            out.append("<td class=\"d-none d-sm-table-cell\">");
            out.append(str.getExa_d_ultmod_date());
            out.append("</td>");

            out.append(" <td class=\"flex-row\"> ");
            out.append("      <a class=\"btn btn-success\" href=\"javascript:Detalle('");
            out.append(str.getExa_n_id());
            out.append("');\"> ");
            out.append("     <i class=\"fa fa-search-plus\"></i> ");
            out.append("       </a> ");
            out.append("  </td> ");

            out.append("</tr>");

        }

        out.append("</tbody>");
        out.append("</table>");
        out.append("</div>");
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
