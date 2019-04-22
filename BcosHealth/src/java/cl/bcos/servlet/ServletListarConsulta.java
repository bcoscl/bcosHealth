/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bcos.servlet;

import cl.bcos.HttpRequest;
import cl.bcos.entity.Consultas;
import cl.bcos.entity.ConsultasList;
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
public class ServletListarConsulta extends HttpServlet {

    private static final Logger Log = Logger.getLogger(ServletListarConsulta.class);
    private static final String CC_CONSULTAS_PROFILE = "CC-CONSULTAS-PROFILE";

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
        String Paciente = (String) tokensession.getAttribute("PACIENTE");

        Log.info("accion :" + accion);
        Log.info("Paciente :" + Paciente);
        Log.info(request);

        Log.info("token bearer:" + token);

        String URL = "http://localhost:9090/bcos/api/json/listarConsultas";
// try {
        Map<String, String> parameter = new HashMap<String, String>();
        parameter.put("accion", accion);
        parameter.put("Paciente", Paciente);
        parameter.put("token", token);

        String resultHttpRequest = "";
        try {
            resultHttpRequest = HttpRequest.HttpRequesPostMethod(URL, parameter, token);
            Log.info(resultHttpRequest);
            ConsultasList res = new Gson().fromJson(resultHttpRequest, ConsultasList.class);

            Log.info("res.message : " + res.getStatus().getMessage());
            Log.info("res.message : " + res.getStatus().getCode());

            if (res.getStatus().getMessage().equalsIgnoreCase("SELECT_OK") && res.getStatus().getCode().equalsIgnoreCase("200")) {
                response.setStatus(200);
                try {

                    if (res.getConsultas().size() > 0) {
                        Log.info("getConsultas");
                        if (accion.equalsIgnoreCase(CC_CONSULTAS_PROFILE)) {
                            out.println(getConsultasProfileList(res));
                        }
//  else if (accion.equalsIgnoreCase("LU-SELECT")) {
//Log.info("Select ");
//out.println(getSuscripcionesSelect(res));
//  }

                    }
                } catch (Exception e) {
                    Log.error(" NO EXISTEN REGISTROS: " + e);

                    if (accion.equalsIgnoreCase(CC_CONSULTAS_PROFILE)) {
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

    private String getConsultasProfileList(ConsultasList res) {
        Log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        StringBuilder out = new StringBuilder();
        int i = 0;
        String read = "";
        //Nombre, rut,  profesion,telefono,email, roles
        //about me, estado
        out.append("<div class=\"card-footer\">");
        out.append("<div class=\"row\">");
        out.append("   <div class=\"col-xs-12 col-sm-5 \">");
        out.append("  <h5 class=\"blue\">");
        out.append("<span class=\"middle\">Historial: ");
        out.append(res.getConsultas().get(0).getConsult_c_paciente_name());
        out.append("</span>");

        out.append("  </h5>");
        out.append("   </div>");

        out.append("   <div class=\"col-xs-12 col-sm-3\">");

        out.append("   </div>");
        out.append("   <div class=\"col-xs-12 col-sm-3 \">");

        out.append("   </div>");
        out.append("   <div class=\"col-xs-12 col-sm-1\">");
        out.append("  <button id=\"Export\" onclick=\"javascript:Export();\" class=\"btn btn-block btn-ghost-danger active\" type=\"button\" aria-pressed=\"true\"><span class=\"fa fa-print\"></span></button>");

        out.append("   </div>");
        out.append("</div>");
        out.append(" </div>");

        out.append(" <div class=\"email-app mb-4\">");
        out.append(" <main class=\"inbox\">");

        out.append("   <ul class=\"messages contenidobusqueda\">");
        String drname = "";
        for (Consultas str : res.getConsultas()) {

            drname = str.getConsult_c_ultmod_username();
            if (drname.isEmpty()) {
                drname = " sin Informacion ";
            }

            if (i == 0) {
                read = "unread";
                i = 1;
            } else {
                read = "";

            }
            out.append("<li class=\"message ");
            out.append(read);
            out.append("\">");
            out.append("<div class=\"actions\">");
            out.append("    <span class=\"action\">");
            out.append("  <i class=\"fa fa-user-md\"></i>");
            out.append("    </span>");
            out.append("    <span class=\"action\">");
            out.append("  <i class=\"fa fa-commenting\"></i>");
            out.append("    </span>");
            out.append("</div>");
            out.append("<div class=\"header\">");
            out.append("  <a class=\"link\" href=\"javascript:void(0);\" id=\"dropdownMenuButton\" class=\"dropdown dropdown-toggle\" data-toggle=\"dropdown\">  <span class=\"from\">Dr. ");
            out.append(drname);
            out.append(" </span> </a>  <!--<div id=\"dropdownMenuButton\" class=\"dropdown dropdown-toggle\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">-->");
            out.append(" <div class=\"dropdown-menu\" aria-labelledby=\"dropdownMenuButton\">");
            out.append("<a class=\"dropdown-item\"  onclick=\"javascript:popupEditConsulta('");
            out.append(str.getConsult_c_titulo());
            out.append("','");
            out.append(str.getConsult_c_obs_consulta());
            out.append("',");
            out.append(str.getConsult_n_id());
            out.append(",'");
            out.append(str.getConsult_c_paciente_name());
            out.append("');\">Modificar</a>");
            out.append("<a class=\"dropdown-item\"  onclick=\"javascript:popupDeleteConsulta(");
            out.append(str.getConsult_n_id());
            out.append(");\">quitar de la lista</a> ");
            out.append(" </div>");
            // out.append("  </div>");
            out.append("   ");
            out.append("    <span class=\"date\">");
            out.append("  <span class=\"fa fa-paper-clip\"></span>");
            out.append(str.getConsult_d_createdate());
            out.append(". <i class=\"fa fa-calendar\"></i></span>");
            out.append("</div>");
            out.append("<div class=\"title\">");
            out.append(str.getConsult_c_titulo());
            out.append("</div>");
            out.append("<div class=\"description\">");
            out.append(str.getConsult_c_obs_consulta());
            out.append("</div>");
            out.append("  </li>");

        }
        out.append("</ul>");
        out.append("</main>");
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
