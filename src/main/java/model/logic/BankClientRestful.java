package model.logic;

import java.util.HashMap;
import model.net.RestClient;

/**
 *
 * @author skuarch
 */
public class BankClientRestful {

    private static final HashMap parameters = new HashMap();
    private static final String url = "https://eps.banorte.com/recibo";
    private static final String Name = "7048870";
    private static final String Password = "eas870p";
    private static final String ClientId = "19955";
    private static final String Mode = "Y"; // P 
    private static final String TransType = "Auth";
    private static final String ResponsePath = "https://eps.banorte.com/RespuestaCC.jsp";
    private static final String OrderId = "";
    private static final String Cvv2Indicator = "1";

    //==========================================================================
    static {
        parameters.put("Name", Name);
        parameters.put("Password", Password);
        parameters.put("ClientId", ClientId);
        parameters.put("Mode", Mode);
        parameters.put("TransType", TransType);
        parameters.put("ResponsePath", ResponsePath);
        parameters.put("OrderId", OrderId);
        parameters.put("Cvv2Indicator", Cvv2Indicator);
    }

    //==========================================================================
    private BankClientRestful() {
    }

    //==========================================================================
    public static synchronized String sendPayment(String creditCardNumber, String expires, String creditCardSecretNumber, String amount) throws Exception {

        String responseString;
        String response = null;
        RestClient rc = new RestClient(url);

        try {

            parameters.put("Number", creditCardNumber);
            parameters.put("Expires", expires);
            parameters.put("Cvv2Val", creditCardSecretNumber);
            parameters.put("Total", amount);

            rc.initConnection();
            rc.writeMessage(parameters);
            responseString = rc.receiveMessage();
            rc.connect();

            if (responseString != null || responseString.length() > 0) {
                response = responseString.replaceAll("\\<.*?>", "");
                response = response.replaceAll("&oacute;", "o");
                response = response.replaceAll("&eacute;", "e");
                response = response.replaceAll("Untitled Document", "");
                response = response.replaceAll("           ", " ");
                response = response.replaceAll("          ", " ");
                response = response.replaceAll("         ", " ");
                response = response.replaceAll("        ", " ");
                response = response.replaceAll("       ", " ");
                response = response.replaceAll("     ", " ");
                response = response.replaceAll("    ", " ");
                response = response.replaceAll("   ", " ");
                response = response.replaceAll("  ", " ");
                response = response.replaceAll("&nbsp;", "");                 
                response = response.replaceAll("Datos de Respuesta de Pago por Tarjeta de Credito ", "");
                
            }

        } catch (Exception e) {
            throw e;
        } finally {
            rc.disconnect();
            rc.closeStreams();
        }

        return response;

    }

}
