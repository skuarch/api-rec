package controllers.secret;

import controllers.application.BaseController;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Cashier;
import model.beans.Payment;
import model.beans.Recipient;
import model.beans.Secret;
import model.beans.SecretStatus;
import model.components.CashierComponent;
import model.components.PaymentComponent;
import model.components.PaymentStatusComponent;
import model.components.RecipientComponent;
import model.components.SecretComponent;
import model.components.SecretStatusComponent;
import model.logic.Constants;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author skuarch
 */
@RestController
public class CashierConsumeSecret extends BaseController {

    private static final Logger logger = getLogger(CashierConsumeSecret.class);

    @Autowired
    private RecipientComponent recipientComponent;
    @Autowired
    private SecretComponent secretComponent;
    @Autowired
    private SecretStatusComponent secretStatusComponent;
    @Autowired
    private PaymentComponent paymentComponent;
    @Autowired
    private PaymentStatusComponent paymentStatusComponent;
    @Autowired
    private CashierComponent cashierComponent;

    @RequestMapping(value = {"v1/cashier/consume/secret/"}, method = RequestMethod.POST)
    public @ResponseBody
    String consumeSecret(
            @ModelAttribute model.beans.CashierConsumeSecret ccs,
            HttpServletResponse response,
            Locale locale
    ) {

        Secret secret;
        HashMap recipientHashMap;
        Recipient recipient;
        BigInteger recipientIdBigInteger;
        JSONObject jsono = null;
        SecretStatus secretStatus;
        int result;
        Payment payment;
        Cashier cashier;

        try {

            //basic configuration
            setHeaderNoChache(response);
            setContentType(response, MediaType.APPLICATION_JSON);

            //get secret
            secret = secretComponent.getActiveSecret(ccs.getSecret());

            //secret exists
            if (secret == null || !secret.getSecretAlphanumeric().equals(ccs.getSecret())) {
                jsono = new JSONObject("{\"error\":\"key doesn't exists or is invalid\"}");
                jsono.append("consumed", false);
                return jsono.toString();
            }

            //secret is active "!= consumed"
            if (secret.getSecretStatus().getId() == Constants.SECRET_STATUS_CONSUMED) {
                jsono = new JSONObject("{\"error\":\"the key has already been used\"}");
                jsono.append("consumed", false);
                return jsono.toString();
            }

            //amount is equals or less than the value of the secret?
            result = ccs.getAmount().compareTo(secret.getValue());
            // 0 equals 1, the first is biggest -1, the second is biggest            
            if (result == 1) {
                jsono = new JSONObject("{\"error\":\"insufficient resources\"}");
                jsono.append("consumed", false);
                return jsono.toString();
            }

            //create new secret if is the case
            if (result == -1) {
                //Create new secret with new amount and send mail of new secret

            }

            //consumed secret and update secret table
            secretStatus = secretStatusComponent.getStatus(Constants.SECRET_STATUS_CONSUMED);
            secret.setSecretStatus(secretStatus);
            secret.setConsumedDate(new SimpleDateFormat(Constants.DATE_FORMAT).format(new Date()));
            secretComponent.updateSecret(secret);

            //get the owner of the secret
            recipientHashMap = recipientComponent.getRecipientBySecret(ccs.getSecret());

            //send mail to recipient
            recipientHashMap.get("recipient_email");
            recipientIdBigInteger = (BigInteger) recipientHashMap.get("recipient_id");
            recipient = recipientComponent.getRecipient(recipientIdBigInteger.longValue());

            //get cashier
            cashier = cashierComponent.getCashier(ccs.getCashierId());

            //create payment
            payment = new Payment();
            payment.setCashier(cashier);
            payment.setRecipient(recipient);
            payment.setSecret(secret);
            payment.setAmount(ccs.getAmount());
            payment.setPaymentStatus(paymentStatusComponent.getStatus(Constants.PAYMENT_STATUS_UNPAID));
            paymentComponent.savePayment(payment);
            
            //everything is ok
            jsono = new JSONObject();
            jsono.put("consumed", true);

        } catch (Exception e) {
            logger.error("CashierConsumeSecret.consumeSecret", e);
            jsono = new JSONObject("{\"error\":\"" + e + "\"}");
            jsono.append("consumed", false);
        }

        return jsono.toString();

    }

}
