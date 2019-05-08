/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bcos.servlet;

import cl.bcos.HttpRequest;
import cl.bcos.entity.Farmacos;
import cl.bcos.entity.FarmacosList;
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
public class ServletListarFarmacos extends HttpServlet {

    private static final Logger Log = Logger.getLogger(ServletListarFarmacos.class);
    private static final String ENDPOINT_PATH = "URLPATH";
    private static final String PATH = System.getProperty(ENDPOINT_PATH,System.getenv(ENDPOINT_PATH));
    private static String https = "https://";
    private static final String CF_FARMACOS_PROFILE = "CF-FARMACOS-PROFILE";

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

        String token = (String) tokensession.getAttribute("token");
        String accion = (String) request.getParameter("accion");
        String Paciente = (String) tokensession.getAttribute("PACIENTE");
        Log.info("Session PACIENTE " + (String) tokensession.getAttribute("PACIENTE"));

        Log.info("accion :" + accion);
        Log.info("Paciente :" + Paciente);
        Log.info(request);

        Log.info("token bearer:" + token);

        if(PATH.contains("localhost")){https = "http://";}String URL = https+PATH+ "/bcos/api/json/listarFarmacos";
//            try {
        Map<String, String> parameter = new HashMap<String, String>();
        parameter.put("accion", accion);
        parameter.put("Paciente", Paciente);
        parameter.put("token", token);
        parameter.put("empresasession", empresasession);

        String resultHttpRequest = "";
        try {
            resultHttpRequest = HttpRequest.HttpRequesPostMethod(URL, parameter, token);
            Log.info(resultHttpRequest);
            FarmacosList res = new Gson().fromJson(resultHttpRequest, FarmacosList.class);

            Log.info("res.message : " + res.getStatus().getMessage());
            Log.info("res.message : " + res.getStatus().getCode());

            if (res.getStatus().getMessage().equalsIgnoreCase("SELECT_OK") && res.getStatus().getCode().equalsIgnoreCase("200")) {
                response.setStatus(200);
                try {

                    if (res.getFarmacos().size() > 0) {
                        Log.info("getPaciente");
                        if (accion.equalsIgnoreCase(CF_FARMACOS_PROFILE)) {
                            out.println(getFarmacosProfileList(res));
                        }
//                        else if (accion.equalsIgnoreCase("LU-SELECT")) {
//                            Log.info("Select ");
//                            out.println(getSuscripcionesSelect(res));
//                        }

                    }
                } catch (Exception e) {
                    Log.error(" NO EXISTEN REGISTROS: " + e);

                    if (accion.equalsIgnoreCase(CF_FARMACOS_PROFILE)) {
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

    private String getFarmacosProfileList(FarmacosList res) {
        Log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        StringBuilder out = new StringBuilder();

        //Nombre, rut,  profesion,telefono,email, roles
        //about me, estado
        String farmaconame = "";

        for (Farmacos str : res.getFarmacos()) {

            farmaconame = str.getFarmaco_c_name();
            if (farmaconame.isEmpty()) {
                farmaconame = " sin Informacion ";
            }

            out.append(" <div class=\"profile-activity clearfix\"> ");
            //out.append("   <div id=\"dropdownMenuButton\" class=\"dropdown dropdown-toggle\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\"> ");
            out.append("   <i class=\"ace-icon fa fa-flask bigger-120\" style=\"color: #00b50e\"></i> ");
            out.append("   <img class=\"pull-left\" alt=\"farmaco-icon\" src=\"../../pages/Farmacos/img/farmaco.png\" with=\"40px\" height=\"40px\"> ");
            out.append("   <a class=\"user\" href=\"#\" id=\"dropdownMenuButton\" class=\"dropdown dropdown-toggle\" data-toggle=\"dropdown\"><span>");
            out.append(farmaconame);
            out.append("</span> </a> ");
            out.append("    <span><strong><em>- Observación : </em></strong>");
            out.append(str.getFarmaco_c_obs());
            out.append("</span> ");
            out.append("    <div class=\"time\"> ");
            out.append("    <i class=\"ace-icon fa fa-clock-o bigger-110\"></i> ");
            out.append("     <span> registro : ");
            out.append(str.getFarmaco_d_ultmod_date());
            out.append("    </span><!--</div>--> ");
            out.append("    </div> ");
            out.append("    <div class=\"dropdown-menu\" aria-labelledby=\"dropdownMenuButton\"> ");
            out.append("        <a class=\"dropdown-item\" href=\"javascript:popupEditFarmacos('");
            out.append(str.getFarmaco_c_name());
            out.append("','");
            out.append(str.getFarmaco_c_obs());
            out.append("','");
            out.append(str.getFarmaco_n_id());
            out.append("');\">Modificar</a> ");
            out.append("        <a class=\"dropdown-item\" href=\"javascript:popupDeleteFarmacos(");
            out.append(str.getFarmaco_n_id());
            out.append(");\">quitar de la lista</a>  ");
            out.append("    </div> ");
            out.append(" </div>");

        }

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
