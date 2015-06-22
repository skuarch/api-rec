package model.logic;

import java.util.ArrayList;
import model.beans.Transfer;

/**
 *
 * @author skuarch
 */
public class ReportGiftProcessor {

    public ReportGiftProcessor() {
    }

    //==========================================================================
    public void sendReportGiftByEmail(ArrayList<Transfer> transferlistDepositor, ArrayList<Transfer> transferlistRecipient, String email) throws Exception{
    
        if(transferlistDepositor == null){
            throw new IllegalArgumentException("transferlistDepositor is null");
        }
        
        if(transferlistRecipient == null){
            throw new IllegalArgumentException("transferlistRecipient is null");
        }
        
        StringBuilder bodyEmail = new StringBuilder();
        
        try {
            
            bodyEmail.append("<div style='margin: auto; border: solid 1px #ccc; min-height: 150px; width: 95%; height: 95%; text-align: center'>            \n" +
"            <div style='font-size: 12px; color:#cacaca; text-align: right; border-top: 1 px solid #ccc'>\n" +
"                regala lo que quieras en <a href='http://regaloenclave.com'>regaloenclave.com</a>\n" +
"            </div> ");
            bodyEmail.append("<br> Estos son los regalos que haz recibido");
            bodyEmail.append("<br> numero de regalos ").append(transferlistRecipient.size());
            bodyEmail.append("<br>");                    
            
            if(transferlistRecipient.size() > 0){
                
                transferlistRecipient.stream().map((transfer) -> {                    
                    return transfer;
                }).forEach((transfer) -> {
                    bodyEmail.append("<br> fecha: ").append(transfer.getDate());
                    bodyEmail.append("<br> clave: ").append(transfer.getSecretAlphanumeric());
                    bodyEmail.append("<br> monto: ").append(transfer.getAmount());
                });                
                
            }
            
            bodyEmail.append("<br> Estos son los regalos que haz hecho");
            bodyEmail.append("<br> numero de regalos ").append(transferlistDepositor.size());
            bodyEmail.append("<br>");                    
            
            if(transferlistDepositor.size() > 0){
                
                transferlistDepositor.stream().map((transfer) -> {                    
                    return transfer;
                }).forEach((transfer) -> {
                    bodyEmail.append("<br>-------------------------------------------<br/>");                    
                    bodyEmail.append("<br> fecha: ").append(transfer.getDate());                    
                    bodyEmail.append("<br> monto: ").append(transfer.getAmount());
                    bodyEmail.append("<br> beneficiario email: ").append(transfer.getRecipient().getEmail());
                    bodyEmail.append("<br> beneficiario nombre: ").append(transfer.getRecipient().getName()).append(" ").append(transfer.getRecipient().getLastName());
                });                
                
            }
            
            bodyEmail.append("<br/><br/><br/>Gracias por usar <a href=\"http://regaloenclave.com\">regaloenclave.com</a></div><br/><br/><br/>");                        
            MailSender.sendMailReportGift(email, bodyEmail.toString(), "no-responder@regaloenclave.com", "reporte de regalos", "Spanish");           
            
        } catch (Exception e) {
            throw e;
        }
        
    }
    
}
