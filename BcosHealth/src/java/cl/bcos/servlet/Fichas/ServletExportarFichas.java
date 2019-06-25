/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bcos.servlet.Fichas;

import cl.bcos.HttpRequest;
import cl.bcos.entity.Consultas;
import cl.bcos.entity.ExportarFichas;
import cl.bcos.entity.ExportarFichasList;
import cl.bcos.entity.Paciente;
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
public class ServletExportarFichas extends HttpServlet {

    private static final Logger Log = Logger.getLogger(ServletExportarFichas.class);
//    private static final String ENDPOINT_PATH = "URLPATH";
//    private static final String PATH = "api.health.bcos.cl";   /* private static final String PATH = System.getenv(ENDPOINT_PATH);*/
//    private static String https = "https://";

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
        String fichas = (String) request.getParameter("fichas");
        Log.info("accion :" + accion);
        Log.info("fichas :" + fichas);
        Log.info("token :" + token);

        fichas = CleanString(fichas);

        Log.info(request);

//        if (PATH.contains("localhost")) {
//            https = "http://";
//        }
        String URL = ConstantsPath.CONTEXT + "/bcos/api/json/exportFichas";
        Log.info(" URL :" + URL);
//            try {
        Map<String, String> parameter = new HashMap<String, String>();
        //parameter.put("planName", planName);
        //parameter.put("userMax", userMax);
        parameter.put("fichas", fichas);
        parameter.put("token", token);
        parameter.put("accion", accion);
        parameter.put("empresasession", empresasession);

        String resultHttpRequest = "";
        try {
            resultHttpRequest = HttpRequest.HttpRequesPostMethod(URL, parameter, token);
            Log.info(resultHttpRequest);

            ExportarFichasList res = new Gson().fromJson(resultHttpRequest, ExportarFichasList.class);

            Log.info("res.message : " + res.getStatus().getMessage());
            Log.info("res.message : " + res.getStatus().getCode());
            Log.info("res.accion : " + accion);

            if (res.getStatus().getMessage().equalsIgnoreCase("SELECT_OK") && res.getStatus().getCode().equalsIgnoreCase("200")) {
                response.setStatus(200);
                try {
                    if (res.getExportarFichas().size() > 0) {
                        Log.info("getExportarFichas");

                        out.println(getPacientesExport(res));

                    }
                } catch (Exception e) {
                    Log.error(" NO EXISTEN REGISTROS: " + e);

                    out.append("<div class=\"card mb-0\"> ");
                    out.append("         <div  id=\"headingOne\" role=\"tab\"> ");

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
                    out.println(" </tr> ");
                    out.println(" </tbody>");
                    out.println(" </table>");
                    out.println(" </div>");
                    out.append("</div></div>");
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

    private String getPacientesExport(ExportarFichasList res) {
        Log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        StringBuilder out = new StringBuilder();

        out.append("<div class=\"card-body\"><div id=\"accordion\" role=\"tablist\">");

        //Nombre, rut,  profesion,telefono,email, roles
        //about me, estado
        int i = 0;
        for (ExportarFichas str : res.getExportarFichas()) {

            Paciente pa = str.getPaciente();

            out.append("<div class=\"card mb-0\"> ");
            out.append("         <div  id=\"headingOne\" role=\"tab\"> ");
            out.append("             <table class=\"table table-striped table-bordered datatable dataTable no-footer\" id=\"DataTables_Table_0\" role=\"grid\" aria-describedby=\"DataTables_Table_0_info\" style=\"border-collapse: collapse !important\"> ");
            out.append("                 <tbody>  ");
            out.append("                     <tr role=\"row\" class=\"odd\"> ");
            out.append("                         <td class=\"sorting_1\">     ");
            out.append("                             <div class=\"row\">         ");
            out.append("  <div class=\"col-xs-12 col-sm-2 middle\">             ");
            out.append("      <img class=\"img-thumbnail editable img-responsive\" alt=\" Avatar\" id=\"avatar2\" src=\"../Perfil/img/ProfilePerson.jpg\">         ");
            out.append("  </div><!-- /.col -->         ");
            out.append("  <div class=\"col-xs-12 col-sm-10\">             ");
            out.append("      <h4 class=\"blue\">                 ");
            out.append("          <a data-toggle=\"collapse\" href=\"#collapse_");
            out.append(i);
            out.append("\" ");
            out.append("             aria-expanded=\"false\" aria-controls=\"collapseOne\"  ");
            out.append("             class=\"collapsed\">");
            out.append(pa.getC_pacientename());
            out.append(" ");
            out.append(pa.getC_apellidos());
            out.append("</a> ");
            out.append("      </h4>             ");
            out.append("      <div class=\"profile-user-info\">                 ");
            out.append("          <div class=\"profile-info-row\">                     ");
            out.append("              <div class=\"profile-info-name\"> Rut </div>                     ");
            out.append("              <div class=\"profile-info-value\" >                         ");
            out.append("                  <span>");
            out.append(pa.getC_numuser());
            out.append("</span>                  ");
            out.append("              </div>         ");
            out.append("              <div class=\"profile-info-name\"> Direccion </div>                     ");
            out.append("              <div class=\"profile-info-value\" >                         ");
            out.append("                  <span>");
            out.append(pa.getC_direccion());
            out.append("</span>                  ");
            out.append("              </div>         ");
            out.append("          </div>             ");
            out.append("          <div class=\"profile-info-row\">               ");
            out.append("              <div class=\"profile-info-name\"> Telefono </div>           ");
            out.append("              <div class=\"profile-info-value\">     ");
            out.append("                  <span>");
            out.append(pa.getC_celular());
            out.append("</span>          ");
            out.append("              </div>          ");
            out.append("              <div class=\"profile-info-name\"> email </div>           ");
            out.append("              <div class=\"profile-info-value\" >     ");
            out.append("                  <span>");
            out.append(pa.getC_email());
            out.append("</span>          ");
            out.append("              </div>          ");
            out.append("          </div>            ");
            out.append("          <div class=\"profile-info-row\">     ");
            out.append("              <div class=\"profile-info-name\">Estado Civil</div>         ");
            out.append("              <div class=\"profile-info-value\" >           ");
            out.append("                  <span>");
            out.append(pa.getC_estado_civil());
            out.append("</span>            ");
            out.append("              </div>          ");
            out.append("              <div class=\"profile-info-name\">Sexo</div>         ");
            out.append("              <div class=\"profile-info-value\" >           ");
            out.append("                  <span>");
            out.append(pa.getC_sexo());
            out.append("</span>            ");
            out.append("              </div>          ");
            out.append("          </div>         ");
            out.append("      </div>       ");
            out.append("  </div><!-- /.col -->   ");
            out.append("                             </div><!-- /.row --> ");
            out.append("                         </td> ");
            out.append("                     </tr>     ");
            out.append("                 </tbody> ");
            out.append("             </table> ");
            out.append("         </div> ");
            out.append("         <div class=\"collapse\" id=\"collapse_");
            out.append(i);
            out.append("\"  role=\"tabpanel\" aria-labelledby=\"headingOne\"  ");
            out.append("              data-parent=\"#accordion\" style=\"\"> ");
            out.append("             <div class=\"card-body\"> ");
            out.append("                 <div class=\"card-body\"> ");
            out.append("                     <div class=\"email-app mb-4\">  ");
            out.append("                         <main class=\"inbox\">   ");
            out.append("                             <ul class=\"messages contenidobusqueda\">");

            if (str.getConsultas().size() >= 1) {
                for (Consultas con : str.getConsultas()) {

                    out.append("<li class=\"message\"> ");
                    out.append("      <div class=\"actions\">     ");
                    out.append("          <span class=\"action\">   ");
                    out.append("              <i class=\"fa fa-user-md\"></i>     ");
                    out.append("          </span>     ");
                    out.append("          <span class=\"action\">   ");
                    out.append("              <i class=\"fa fa-commenting\"></i>     ");
                    out.append("          </span> ");
                    out.append("      </div> ");
                    out.append("      <div class=\"header\"> ");
                    out.append("          <span class=\"from\">");
                    out.append(con.getConsult_c_ultmod_username());
                    out.append("</span>                  ");
                    out.append("          <span class=\"date\">   ");
                    out.append("              <span class=\"fa fa-paper-clip\"></span>");
                    out.append(con.getConsult_d_ultmod_date());
                    out.append("              <i class=\"fa fa-calendar\"></i> ");
                    out.append("          </span> ");
                    out.append("      </div> ");
                    out.append("      <div class=\"title\">control</div> ");
                    out.append("      <div class=\"description\">");
                    out.append(con.getConsult_c_obs_consulta());
                    out.append("</div>   ");
                    out.append("  </li>");

                }
            } else {
                out.append("<li class=\"message\"> sin Registros.. ");

                out.append("  </li>");

            }

            out.append("</ul></main> ");
            out.append("                     </div> ");
            out.append("                 </div> ");
            out.append("             </div> ");
            out.append("         </div> ");
            out.append("     </div>");
            out.append("<span class=\"hr\"></span> ");

            i++;
        }

        out.append("</div></div>");

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

    private String CleanString(String value) {
        Log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

        value = value.replace("][", ",");
        value = value.replace("]", ",");
        value = value.replace("[", ",");

        value = value.replace(" ", "");
        //Log.debug("value despues del replace :" + value);
        String valor[] = value.split(",");

        String valorLimpio = "";
        for (int i = 0; i < valor.length; i++) {
            if (!valor[i].isEmpty()) {
                //Log.debug("valor : " + valor[i]);
                String valor_ = valor[i];
                String val[] = valor_.replace("|", ",").split(",");
                //Log.debug("val[0] " + val[0]);
                if (valorLimpio.isEmpty()) {
                    valorLimpio = val[0];
                } else {
                    valorLimpio = valorLimpio + "," + val[0];
                }

            }

        }
        valorLimpio = valorLimpio.trim();
        valorLimpio = valorLimpio.replace(",", "','");
        valorLimpio = "'" + valorLimpio + "'";

        Log.debug(valorLimpio);
        return valorLimpio;
    }

}
