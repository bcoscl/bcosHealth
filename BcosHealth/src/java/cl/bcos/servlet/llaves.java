package cl.bcos.servlet;



import cl.bcos.RSA;
import java.io.IOException;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;


public class llaves extends HttpServlet{
    
    private static final Logger Log = Logger.getLogger(llaves.class);

    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
            Date date = Calendar.getInstance().getTime();       
            SimpleDateFormat format1 = new SimpleDateFormat("ddMMyyyy");
            String fecha = format1.format(date); 
        
            String bits = request.getParameter("bits");
            
          try{         
              if(bits != null){
                  RSA rsa = new RSA(Integer.parseInt(bits));
                  rsa.generarKeysToByteArray();
                  byte[] privateKey = rsa.getPrivateKey();
                  byte[] publicKey = rsa.getPublicKey();
                  
                  byte[] privateXML = rsa.getPrivateXML();
                  byte[] publicXML = rsa.getPublicXML();   
                  
                  response.setContentType("application/zip");
                  response.setHeader( "Content-Disposition", "attachment;filename=Llaves_"+bits+"bits_"+fecha+".zip");
                  
                  ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
                  ZipEntry k1 = new ZipEntry("Private.key");
                  k1.setSize(privateKey.length);
                  zos.putNextEntry(k1);
                  zos.write(privateKey);
                  zos.closeEntry();
                  
                  ZipEntry k2 = new ZipEntry("Public.key");
                  k2.setSize(publicKey.length);
                  zos.putNextEntry(k2);
                  zos.write(publicKey);
                  zos.closeEntry();
                  
                  ZipEntry x1 = new ZipEntry("Private.xml");                
                  x1.setSize(privateXML.length);
                  zos.putNextEntry(x1);
                  zos.write(privateXML);
                  zos.closeEntry();                  
                    
                  ZipEntry x2 = new ZipEntry("Public.xml");
                  x2.setSize(publicXML.length);
                  zos.putNextEntry(x2);
                  zos.write(publicXML);
                  zos.closeEntry();                            
                  
                  zos.close();
              }
            
        }catch(Exception e){
            Log.error(e);
        }
    }
}