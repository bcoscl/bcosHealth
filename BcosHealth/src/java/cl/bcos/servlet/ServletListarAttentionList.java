/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bcos.servlet;

import cl.bcos.HttpRequest;
import cl.bcos.entity.Attention;
import cl.bcos.entity.AttentionList;
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
public class ServletListarAttentionList extends HttpServlet {

    private static final Logger Log = Logger.getLogger(ServletListarAttentionList.class);
    private static final String ENDPOINT_PATH = "URLPATH";
    private static final String PATH = System.getProperty(ENDPOINT_PATH,System.getenv(ENDPOINT_PATH));
    private static String https = "https://";
    private static final String AT_TABLA_BY_DOC = "AT-TABLA-BY-DOC";
    private static final String AT_TABLA = "AT-TABLA";

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
        HttpSession Paciente = request.getSession(true);

        String token = (String) tokensession.getAttribute("token");
        String accion = (String) request.getParameter("accion");
        String numuser_medico = (String) request.getParameter("numuser_medico");
        String nombre_medico = (String) request.getParameter("nombre_medico");
        String user = (String) request.getParameter("user");
        String doctor = "";

        Log.info("accion :" + accion);
        Log.info(request);

        Log.info("token bearer:" + token);
        if (accion.equalsIgnoreCase("AT-DETALLE")) {
            Paciente.setAttribute("PACIENTE", user);
            Log.info("Session PACIENTE " + (String) tokensession.getAttribute("PACIENTE"));

        } else {
            Log.info("Session PACIENTE " + (String) tokensession.getAttribute("PACIENTE"));
            if (accion.equalsIgnoreCase("AT-TABLA-BY-DOC")) {
                doctor = numuser_medico;

            } else if (accion.equalsIgnoreCase("AT-TABLA")) {
                doctor = "TOKEN";
            }

            if(PATH.contains("localhost")){https = "http://";}String URL = https +  PATH + "/bcos/api/json/listarAttentionList";
//            try {
            Map<String, String> parameter = new HashMap<String, String>();
            parameter.put("doctor", doctor);
            parameter.put("accion", accion);
            parameter.put("token", token);
            parameter.put("empresasession", empresasession);

            String resultHttpRequest = "";
            try {
                resultHttpRequest = HttpRequest.HttpRequesPostMethod(URL, parameter, token);
                Log.info(resultHttpRequest);

                AttentionList res = new Gson().fromJson(resultHttpRequest, AttentionList.class);

                Log.info("res.message : " + res.getStatus().getMessage());
                Log.info("res.message : " + res.getStatus().getCode());
                Log.info("res.accion : " + accion);

                if (res.getStatus().getMessage().equalsIgnoreCase("SELECT_OK") && res.getStatus().getCode().equalsIgnoreCase("200")) {
                    response.setStatus(200);
                    try {
                        if (res.getAttention().size() > 0) {
                            Log.info("getProfiles");
                            if (accion.equalsIgnoreCase(AT_TABLA) || accion.equalsIgnoreCase(AT_TABLA_BY_DOC)) {
                                out.println(getAttentionListTabla(res, accion));
                            }

                        }
                    } catch (Exception e) {
                        Log.error(" NO EXISTEN REGISTROS: " + e);

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

            } catch (Exception e) {
                Log.error(e);
                response.setStatus(404);
            }
        }

    }

    private String getAttentionListTabla(AttentionList res, String accion) {
        Log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        StringBuilder out = new StringBuilder();

        out.append("<div class=\"col-sm-12\">");
        out.append("<table class=\"table table-striped table-bordered datatable dataTable no-footer\" id=\"DataTables_Table_0\" role=\"grid\" aria-describedby=\"DataTables_Table_0_info\" style=\"border-collapse: collapse !important\">");
        out.append("<thead>");
        out.append("<tr role=\"row\">");
        out.append("<th class=\"col-xs-8 col-sm-8\" tabindex=\"0\" aria-controls=\"DataTables_Table_0\" rowspan=\"1\" colspan=\"1\" aria-sort=\"ascending\" aria-label=\"Username: activate to sort column descending\" >Pacientes</th>");
        out.append("<th class=\"col-xs-4 col-sm-4\" tabindex=\"0\" aria-controls=\"DataTables_Table_0\" rowspan=\"1\" colspan=\"1\" aria-sort=\"ascending\" aria-label=\"Username: activate to sort column descending\" >Acciones</th>");
        out.append("</tr>");
        out.append("</thead>");
        out.append("<tbody class=\"contenidobusqueda\">");

        //Nombre, rut,  profesion,telefono,email, roles
        //about me, estado
        for (Attention str : res.getAttention()) {

            out.append(" <tr role=\"row\" class=\"odd\">");
            out.append("<td class=\"sorting_1\">");
            out.append("    <div class=\"row\">");
            out.append("        <div class=\"col-xs-12 col-sm-2 middle\">");
            out.append("            <img class=\"img-thumbnail editable img-responsive\" alt=\" Avatar\" id=\"avatar2\" src=\"../Perfil/img/ProfilePerson.jpg\" >");
            out.append("        </div><!-- /.col -->");
            out.append("        <div class=\"col-xs-12 col-sm-10\">");
            out.append("            <h4 class=\"blue\">");
            out.append("                <span class=\"middle\" id=\"input_nombre\">");
            out.append(str.getAt_c_pacientename());
            out.append("</span>");
            out.append("            </h4>");
            out.append("            <div class=\"profile-user-info\">");
            out.append("                <div class=\"profile-info-row\">");
            out.append("                    <div class=\"profile-info-name\"> Rut </div>");
            out.append("                    <div class=\"profile-info-value\" id=\"input_rut\">");
            out.append("                        <span>");
            out.append(str.getAt_c_numuser_paciente());
            out.append("</span>");
            out.append("                    </div>");
            out.append("                </div>");
            out.append("                <div class=\"profile-info-row\">");
            out.append("                    <div class=\"profile-info-name\"> Motivo </div>");
            out.append("");
            out.append("                    <div class=\"profile-info-value\" id=\"input_motivo\">");
            out.append("                        <span>");
            out.append(str.getAt_c_obs());
            out.append("</span>");
            out.append("                    </div>");
            out.append("                </div>");
            out.append("                <div class=\"profile-info-row\">");
            out.append("                    <div class=\"profile-info-name\"> Hora llegada </div>");
            out.append("");
            out.append("                    <div class=\"profile-info-value\" id=\"input_fecha\">");
            out.append("                        <span>");
            out.append(str.getAt_d_fechamod());
            out.append("</span>");
            out.append("                    </div>");
            out.append("                </div>");
            out.append("            </div>");
            out.append("        </div><!-- /.col -->");
            out.append("    </div><!-- /.row -->");
            out.append("</td>");
            out.append("<td class=\"sorting_1\">");

            out.append("    <div class=\"btn-group\">");
            out.append("        <button class=\"btn btn-primary dropdown-toggle\" type=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">Acciones</button>");
            out.append("        <div class=\"dropdown-menu\" x-placement=\"bottom-start\" style=\"position: absolute; will-change: transform; top: 0px; left: 0px; transform: translate3d(0px, 35px, 0px);\">");
            if (accion.equalsIgnoreCase(AT_TABLA)) {
                out.append("            <a class=\"dropdown-item\" href=\"javascript:Atender('");
                out.append(str.getAt_c_numuser_paciente());
                out.append("','");
                out.append(str.getAt_n_id());
                out.append("');\">Atender</a>");
            }
            out.append("            <a class=\"dropdown-item\" href=\"javascript:enviarAlFinal('");
            out.append(str.getAt_n_id());
            out.append("');\">Enviar al final</a>");
            out.append("            <a class=\"dropdown-item\" href=\"javascript:quitardelaLista('");
            out.append(str.getAt_n_id());
            out.append("');\">Quitar de la lista</a>");

            out.append("        </div>");
            out.append("    </div>");

            out.append("</td>");
            out.append(" </tr>");

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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
