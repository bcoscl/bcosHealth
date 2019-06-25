/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bcos.servlet.Evaluacion;

import cl.bcos.HttpRequest;
import cl.bcos.entity.Chart;
import cl.bcos.entity.Evaluaciones;
import cl.bcos.entity.EvaluacionesList;
import cl.bcos.utils.ConstantsPath;
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aacantero
 */
public class ServletListarEvaluacion extends HttpServlet {

    private static final Logger Log = Logger.getLogger(ServletListarEvaluacion.class);
    private static final String GRAFICA = "GRAFICA";
    private static final String TABLA = "TABLA";
    private static final String FULL = "LE-FULL";

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

        String token = (String) tokensession.getAttribute("token");
        String accion = (String) request.getParameter("accion");
        String Paciente = (String) tokensession.getAttribute("PACIENTE");

        Log.info("Session PACIENTE " + (String) tokensession.getAttribute("PACIENTE"));
        Log.info("accion :" + accion);
        Log.info("Paciente :" + Paciente);
        Log.info(request);

        Log.info("token bearer:" + token);

        String URL = ConstantsPath.CONTEXT + "/bcos/api/json/listarEvaluaciones";
        Log.info(" URL :" + URL);

        Map<String, String> parameter = new HashMap<String, String>();
        parameter.put("accion", accion);
        parameter.put("Paciente", Paciente);
        parameter.put("token", token);
        parameter.put("empresasession", empresasession);

        String resultHttpRequest = "";
        try {
            resultHttpRequest = HttpRequest.HttpRequesPostMethod(URL, parameter, token);
            Log.info(resultHttpRequest);
            EvaluacionesList res = new Gson().fromJson(resultHttpRequest, EvaluacionesList.class);

            Log.info("res.message : " + res.getStatus().getMessage());
            Log.info("res.message : " + res.getStatus().getCode());

            if (res.getStatus().getMessage().equalsIgnoreCase("SELECT_OK") && res.getStatus().getCode().equalsIgnoreCase("200")) {
                response.setStatus(200);
                try {

                    if (res.getEvaluaciones().size() > 0) {
                        Log.info("getEvaluaciones");
                        if (accion.equalsIgnoreCase(FULL)) {
                            out.println(getEvaluacionesListTablaGrafica(res));
                        } else if (accion.equalsIgnoreCase(TABLA)) {
                            out.println(getEvaluacionesListTabla(res));
                        } else if (accion.equalsIgnoreCase(GRAFICA)) {
                            out.println(getEvaluacionesListGrafica(res));
                        } else {
                            Log.error("No Soportado");
                        }

                    }
                } catch (Exception e) {
                    Log.error(" NO EXISTEN REGISTROS: " + e);

                      out.println("<span>Sin Registros..</span>|Sin Registros...");
//                    out.println("<div class=\"col-sm-12\">");
//                    out.println("  <table class=\"table table-striped table-bordered datatable dataTable no-footer\" id=\"DataTables_Table_0\" role=\"grid\" aria-describedby=\"DataTables_Table_0_info\" style=\"border-collapse: collapse !important\">");
//                    out.println("   <thead>");
//                    out.println("  <tr role=\"row\">");
//                    out.println("  <th class=\"sorting d-none d-sm-table-cell\" tabindex=\"0\" aria-controls=\"DataTables_Table_0\" rowspan=\"1\" colspan=\"1\" aria-label=\"Date registered: activate to sort column ascending\" style=\"width: 20%;\">Registros</th>	");
//                    out.println("  </tr>");
//                    out.println("  </thead>");
//                    out.println("  <tbody class=\"contenidobusqueda\">");
//                    out.println("<tr role=\"row\" class=\"odd\">");
//                    out.println("<td class=\"sorting_1 \">");
//                    out.println("<span>Sin Registros..</span>");
//                    out.println("</td>");
//                    out.println(" </tr>\n");
//                    out.println(" </tbody>");
//                    out.println(" </table>");
//                    out.println(" </div>");

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
            out.println("<span>Ups.. No pudimos obtener los registros, inténtalo más tarde...</span>");
            out.println("</td>");
            out.println(" </tr>\n");
            out.println(" </tbody>");
            out.println(" </table>");
            out.println(" </div>");

            out.println("|");

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
            out.println("<span>Ups.. No pudimos Obtener los Registros, intentalo mas tarde...</span>");
            out.println("</td>");
            out.println(" </tr>\n");
            out.println(" </tbody>");
            out.println(" </table>");
            out.println(" </div>");

            //response.setStatus(404);
        }


        /* TODO output your page here. You may use following sample code. */
    }

    private String getEvaluacionesListTablaGrafica(EvaluacionesList res) {
        /*Obtiene las html de grafica y tabla separados por pipe*/

        return getEvaluacionesListTabla(res) + "|" + getEvaluacionesListGrafica(res);
    }

    private String getEvaluacionesListTabla(EvaluacionesList res) {
        Log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        StringBuilder out = new StringBuilder();

        out.append(" <table class=\"table table-responsive-sm table-hover table-outline mb-0\">");
        out.append("     <thead class=\"thead-light\">");
        out.append("<tr>");
        out.append("    <th class=\"text-center\" style=\"width: 25%;\">");
        out.append("        Evaluación");
        out.append("    </th>");
        out.append("    <!--<th>Doctor</th>-->");
        out.append("    <th class=\"text-center\" style=\"width:10%;\">Fecha</th>");
        out.append("    <th class=\"text-center\" style=\"width:10%;\">Talla</th>");
        out.append("    <th class=\"text-center\" style=\"width:10%;\">Peso</th>");
        out.append("    <th class=\"text-center\" style=\"width:10%;\" >Fatv</th>");
        out.append("    <th class=\"text-center\" style=\"width:10%;\">Fat</th>");
        out.append("    <th class=\"text-center\" style=\"width:10%;\">Musc</th>");
        out.append("</tr>");
        out.append("     </thead>");
        out.append("     <tbody>");

        for (Evaluaciones str : res.getEvaluaciones()) {

            out.append("<tr>");
            out.append("    <td>");
            
            
           
            out.append("<div class=\"header\">");
            out.append("  <a class=\"link\" href=\"javascript:void(0);\" id=\"dropdownMenuButton\" class=\"dropdown dropdown-toggle\" data-toggle=\"dropdown\">  <span class=\"from\"><i class=\"fa fa-user-md\"></i> Dr. ");
            out.append(str.getEva_c_ultmod_username());
            out.append(" </span> </a>  <!--<div id=\"dropdownMenuButton\" class=\"dropdown dropdown-toggle\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">-->");
            out.append(" <div class=\"dropdown-menu\" aria-labelledby=\"dropdownMenuButton\">");
            out.append("<a class=\"dropdown-item\"  onclick=\"javascript:popupEditeva('");
            out.append(str.getEva_n_obs_evaluacion());
            out.append("',");
            out.append(str.getEva_n_id());
            out.append(",'");
            out.append(str.getEva_d_eva_date());
            out.append("',");
            out.append(str.getEva_n_talla());
            out.append(",");
            out.append(str.getEva_n_peso());
            out.append(",");
            out.append(str.getEva_n_fat());
            out.append(",");
            out.append(str.getEva_n_fatv());
            out.append(",");
            out.append(str.getEva_n_musc());
            out.append(");\">Modificar</a>");
            out.append("<a class=\"dropdown-item\"  onclick=\"javascript:popupDeleteEva(");
            out.append(str.getEva_n_id());
            out.append(");\">quitar de la lista</a> ");
            out.append(" </div>");
            
                        
            
            
            
//            out.append("        <div><i class=\"fa fa-user-md\"></i></i> Dr. ");
//            out.append(str.getEva_c_ultmod_username());
//            out.append("</div>");
            
            
            
            
            
            out.append("        <hr>");
            out.append("        <div class=\"small text-muted\">");
            out.append("   <i class=\"fa fa-commenting\"></i>");
            out.append("   <span><strong>Comentario:</strong></span><br>");
            out.append(str.getEva_n_obs_evaluacion());
            out.append("</div>");
            out.append("    </td>");
            out.append("    <td class=\"text-center\">");
            out.append(str.getEva_d_eva_date());// fecha de evaluacion
            out.append("    </td>");
            out.append("    <td class=\"text-center\">");
            out.append(str.getEva_n_talla());
            out.append(" mts</td>");
            out.append("    <td class=\"text-center\">");
            out.append(str.getEva_n_peso());
            out.append(" Kg</td>");
            out.append("    <td class=\"text-center\">");
            out.append(str.getEva_n_fatv());// numero absoluto
            out.append("    </td>");
            out.append("    <td>");
            out.append("        <div class=\"clearfix\">");
            out.append("   <div class=\"float-center text-center\">");
            out.append("       <strong>");
            out.append(str.getEva_n_fat());
            out.append(" %</strong>");
            out.append("   </div>");
            out.append("        </div>");
            out.append("        <div class=\"progress progress-xs\">");
            out.append("   <div class=\"progress-bar bg-success\" role=\"progressbar\" style=\"width: ");
            out.append(str.getEva_n_fat());
            out.append("%\" aria-valuenow=\"");
            out.append(str.getEva_n_fat());
            out.append("\" aria-valuemin=\"0\" aria-valuemax=\"100\"></div>");
            out.append("        </div>");
            out.append("    </td>");
            out.append("    <td>");
            out.append("        <div class=\"clearfix\">");
            out.append("   <div class=\"float-center text-center\">");
            out.append("       <strong>");
            out.append(str.getEva_n_musc());
            out.append(" %</strong>");
            out.append("   </div>");
            out.append("        </div>");
            out.append("        <div class=\"progress progress-xs\">");
            out.append("   <div class=\"progress-bar bg-success\" role=\"progressbar\" style=\"width: ");
            out.append(str.getEva_n_musc());
            out.append("%\" aria-valuenow=\"");
            out.append(str.getEva_n_musc());
            out.append("\" aria-valuemin=\"0\" aria-valuemax=\"100\"></div>");
            out.append("        </div>");
            out.append("    </td>");
            out.append("</tr>");

        }

        out.append("     </tbody>");
        out.append(" </table>");

        return out.toString();

    }

    private String getEvaluacionesListGrafica(EvaluacionesList res) {
        Log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
        Map map = new HashMap();
        List<Chart> chart = new ArrayList();

        String fechas = "";
        String talla = "";
        String peso = "";
        String fatv = "";
        String musc = "";
        String fat = "";

        String ult_fechas = "";
        String ult_talla = "";
        String ult_peso = "";
        String ult_fatv = "";
        String ult_musc = "";
        String ult_fat = "";

        float max_talla = 0;
        float max_peso = 0;
        float max_fatv = 0;
        float max_musc = 0;
        float max_fat = 0;
        float min = 0;
        
        float min_talla = 1000;
        float min_peso = 1000;
        float min_fatv = 1000;
        float min_musc = 1000;
        float min_fat = 1000;
        

        int i = 0;
        for (Evaluaciones str : res.getEvaluaciones()) {

             /*busca el maximo*/  
            if (Float.parseFloat(str.getEva_n_talla()) > max_talla) {
                max_talla = Float.parseFloat(str.getEva_n_talla());
            }
            if (Float.parseFloat(str.getEva_n_peso()) > max_peso) {
                max_peso = Float.parseFloat(str.getEva_n_peso());
            }
            if (Float.parseFloat(str.getEva_n_fatv()) > max_fatv) {
                max_fatv = Float.parseFloat(str.getEva_n_fatv());
            }
            if (Float.parseFloat(str.getEva_n_fat()) > max_fat) {
                max_fat = Float.parseFloat(str.getEva_n_fat());
            }
            if (Float.parseFloat(str.getEva_n_musc()) > max_musc) {
                max_musc = Float.parseFloat(str.getEva_n_musc());
            }
            
            /*busca el minimo*/
            if (Float.parseFloat(str.getEva_n_talla()) < min_talla) {
                min_talla = Float.parseFloat(str.getEva_n_talla());
            }
            if (Float.parseFloat(str.getEva_n_peso()) < min_peso) {
                min_peso = Float.parseFloat(str.getEva_n_peso());
            }
            if (Float.parseFloat(str.getEva_n_fatv()) < min_fatv) {
                min_fatv = Float.parseFloat(str.getEva_n_fatv());
            }
            if (Float.parseFloat(str.getEva_n_fat()) < min_fat) {
                min_fat = Float.parseFloat(str.getEva_n_fat());
            }
            if (Float.parseFloat(str.getEva_n_musc()) < min_musc) {
                min_musc = Float.parseFloat(str.getEva_n_musc());
            }

            if (i > 0) {

                fechas = fechas + ",";
                talla = talla + ",";
                peso = peso + ",";
                fatv = fatv + ",";
                fat = fat + ",";
                musc = musc + ",";

            }
            fechas = fechas + /*"'" +*/ str.getEva_d_eva_date() /*+ "'"*/;
            talla = talla + str.getEva_n_talla();
            peso = peso + str.getEva_n_peso();
            fatv = fatv + str.getEva_n_fatv();
            fat = fat + str.getEva_n_fat();
            musc = musc + str.getEva_n_musc();

            if (i == 0) {

                ult_fechas = fechas;
                ult_talla = talla;
                ult_peso = peso;
                ult_fatv = fatv;
                ult_fat = fat;
                ult_musc = musc;

            }

            i++;
            
            if(i>=6){
                break;
            } 
            
        }
        Chart ctalla = new Chart();
        Chart cpeso = new Chart();
        Chart cfatv = new Chart();
        Chart cfat = new Chart();
        Chart cmusc = new Chart();
        String textodes = "Ultima Evaluación al " + ult_fechas.replace("'", "");

        ctalla.setTitle("Talla : " + ult_talla + " mt");
        ctalla.setDescripcion(textodes);
        ctalla.setDataPoints(talla);
        ctalla.setDescriptionPoints("Altura mt");
        ctalla.setLabelsPoints(fechas);
        ctalla.setMaxValue(max_talla); // mt
        ctalla.setMinValue(min_talla); // mt
        ctalla.setId("1");

        // peso en KG
        cpeso.setTitle("Peso : " + ult_peso + " Kg");
        cpeso.setDescripcion("" + textodes);
        cpeso.setDataPoints(peso);
        cpeso.setDescriptionPoints("Peso Kg");
        cpeso.setLabelsPoints(fechas);
        cpeso.setMaxValue(max_peso);
        cpeso.setMinValue(min_peso);
        cpeso.setId("2");

        // numero absoluto
        cfatv.setTitle("Grasa viceral : " + ult_fatv + " (abs)");
        cfatv.setDescripcion("fatv - " + textodes);
        cfatv.setDataPoints(fatv);
        cfatv.setDescriptionPoints("fatv abs");
        cfatv.setLabelsPoints(fechas);
        cfatv.setMaxValue(max_fatv); //numero abosluto
        cfatv.setMinValue(min_fatv);
        cfatv.setId("3");

        // % de grasa
        cfat.setTitle("Grasa Corporal : " + ult_fat + "% ");
        cfat.setDescripcion("fat - " + textodes);
        cfat.setDataPoints(fat);
        cfat.setDescriptionPoints("fat % ");
        cfat.setLabelsPoints(fechas);
        cfat.setMaxValue(max_fat); // cm
        cfat.setMinValue(min_fat);
        cfat.setId("4");

        // % de musculatura
        cmusc.setTitle("Musculatura : " + ult_musc + "% ");
        cmusc.setDescripcion("musc - " + textodes);
        cmusc.setDataPoints(musc);
        cmusc.setDescriptionPoints("musc %");
        cmusc.setLabelsPoints(fechas);
        cmusc.setMaxValue(max_musc); // cm
        cmusc.setMinValue(min_musc);
        cmusc.setId("5");

        chart.add(ctalla);
        chart.add(cfat);
        chart.add(cfatv);
        chart.add(cmusc);
        chart.add(cpeso);

        map.put("chart", chart);

        String var = gson.toJson(map);
        Log.info("Grafica :\n" + var);
        var = var.replace("\n", "");
        var = var.replace("{  ", "{");
        var = var.replace("[    ", "[");
        var = var.replace(",      ", ",");
        var = var.replace("{    ", "{");
        var = var.replace("    }", "}");

        Log.info("sin enter Grafica :\n" + var);

        return var;
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
