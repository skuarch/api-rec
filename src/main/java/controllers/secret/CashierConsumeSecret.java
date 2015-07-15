package controllers.secret;

import controllers.application.BaseController;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.AffiliateBasic;
import model.beans.AffiliateType;
import model.beans.Cashier;
import model.beans.CashierSecretConsumed;
import model.beans.CompanyBasic;
import model.beans.Payment;
import model.beans.Recipient;
import model.beans.Secret;
import model.beans.SecretConsumed;
import model.beans.SecretStatus;
import model.components.AffiliateComponent;
import model.components.AffiliateTypeComponent;
import model.components.CashierComponent;
import model.components.CashierSecretConsumedComponent;
import model.components.CompanyComponent;
import model.components.PaymentComponent;
import model.components.PaymentStatusComponent;
import model.components.RecipientComponent;
import model.components.SecretComponent;
import model.components.SecretStatusComponent;
import model.logic.Constants;
import model.util.MailUtil;
import model.util.SecretUtil;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @Autowired
    private CashierSecretConsumedComponent cashierSecretConsumedComponent;
    @Autowired
    private AffiliateComponent affiliateComponent;
    @Autowired
    private CompanyComponent companyComponent;
    @Autowired
    private AffiliateTypeComponent affiliateTypeComponent;

    @RequestMapping(value = {"v1/cashier/consume/secret"})
    public @ResponseBody
    String consumeSecret(
            @ModelAttribute SecretConsumed sc,
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
        BigDecimal newAmount;
        long newSecretId;
        Secret newSecret;
        List<Secret> secretList;
        String dateOperation = new SimpleDateFormat(Constants.DATE_FORMAT).format(new Date());
        CashierSecretConsumed cashierSecretConsumed;
        AffiliateBasic affiliateBasic;
        CompanyBasic companyBasic;
        AffiliateType affiliateType;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now;
        Date expiry;
        BigDecimal profit;
        double percentageDiscount;
        double r;

        try {

            //basic configuration
            setHeaderNoChache(response);
            setContentType(response, MediaType.APPLICATION_JSON);

            //get secret
            secret = secretComponent.getActiveSecret(sc.getSecret());

            //secret exists
            if (secret == null || !secret.getSecretAlphanumeric().equals(sc.getSecret())) {
                jsono = new JSONObject("{\"error\":\"la clave no existe o es invalida\"}");
                jsono.append("consumed", false);
                return jsono.toString();
            }

            //secret is active "!= consumed"
            if (secret.getSecretStatus().getId() == Constants.SECRET_STATUS_CONSUMED) {
                jsono = new JSONObject("{\"error\":\"la clave ya fue usada\"}");
                jsono.append("consumed", false);
                return jsono.toString();
            }

            //amount is equals or less than the value of the secret?
            result = sc.getAmount().compareTo(secret.getValue());
            // 0 equals 1, the first is biggest -1, the second is biggest            
            if (result == 1) {
                jsono = new JSONObject("{\"error\":\"resursos insuficientes, el valor de la clave es de: $" + secret.getValue() + " \"}");
                jsono.append("consumed", false);
                return jsono.toString();
            }

            //consume before expiration date
            now = new Date();
            expiry = format.parse(secret.getExpiryDate());
            if (now.getTime() > expiry.getTime()) {
                jsono = new JSONObject("{\"error\":\" la clave a expirado, la fecha de expiracion es: " + secret.getExpiryDate() + " \"}");
                jsono.append("consumed", false);
                return jsono.toString();
            }

            //consumed secret and update secret table
            secretStatus = secretStatusComponent.getStatus(Constants.SECRET_STATUS_CONSUMED);
            secret.setSecretStatus(secretStatus);
            secret.setConsumedDate(dateOperation);
            secretComponent.updateSecret(secret);

            //get the owner of the secret
            recipientHashMap = recipientComponent.getRecipientBySecret(sc.getSecret());

            //send mail to recipient            
            recipientHashMap.get("email");
            recipientIdBigInteger = (BigInteger) recipientHashMap.get("id");
            recipient = recipientComponent.getRecipient(recipientIdBigInteger.longValue());

            //create new secret if is the case
            if (result == -1) {
                //Create new secret with new amount and send mail about this new secret
                newAmount = secret.getValue().subtract(sc.getAmount());
                newSecret = SecretUtil.getSecret(newAmount);
                newSecretId = secretComponent.saveSecret(newSecret);
                newSecret.setId(newSecretId);
                secretList = recipient.getSecret();
                secretList.add(newSecret);
                recipient.setSecret(secretList);
                recipientComponent.updateRecipient(recipient);
                MailUtil.sendMailRecipientNewSecret(recipient, newAmount, newSecret, locale.getDisplayLanguage());
            }

            //get cashier
            cashier = cashierComponent.getCashier(sc.getCashierId());

            //create payment
            payment = new Payment();
            payment.setCashier(cashier);
            payment.setRecipient(recipient);
            payment.setSecret(secret);
            payment.setAmount(sc.getAmount());
            payment.setPaymentStatus(paymentStatusComponent.getStatus(Constants.PAYMENT_STATUS_UNPAID));
            payment.setCreationDate(dateOperation);

            //get affiliate or company
            affiliateBasic = affiliateComponent.getAffiliateBasicByCashierId(cashier.getId());

            if (affiliateBasic == null) {

                //cashier belongs to company
                //get company
                affiliateType = affiliateTypeComponent.getAffiliateType(AffiliateType.AFFILIATE_TYPE_COMPANY);
                companyBasic = companyComponent.getCompanyBasicByCashierId(cashier.getId());
                payment.setBehalf(companyBasic.getId().longValue());
                payment.setAffiliateType(affiliateType);
                payment.setDiscountPercentage((byte) (int) companyBasic.getDiscountPercentage());
                payment.setBehalfBrand(companyBasic.getBrand());
                percentageDiscount = companyBasic.getDiscountPercentage();

            } else {

                //cashier belogs to affiliate
                affiliateType = affiliateTypeComponent.getAffiliateType(AffiliateType.AFFILIATE_TYPE_PERSON);
                payment.setBehalf(affiliateBasic.getId().longValue());
                payment.setAffiliateType(affiliateType);
                payment.setDiscountPercentage((byte) (int) affiliateBasic.getDiscountPercentage());
                payment.setBehalfBrand(affiliateBasic.getBrand());
                percentageDiscount = affiliateBasic.getDiscountPercentage();               
                
            }

            //calculate the profit
            r =  sc.getAmount().doubleValue() * percentageDiscount;
            profit = BigDecimal.valueOf(r / 100);
            payment.setProfit(profit);
            
            //total to pay
            r = sc.getAmount().doubleValue() - profit.doubleValue();
            payment.setTotalPay(BigDecimal.valueOf(r));
            
            //create the payment
            paymentComponent.savePayment(payment);

            // create cashier consumed secret
            cashierSecretConsumed = new CashierSecretConsumed();
            cashierSecretConsumed.setCashier(cashier);
            cashierSecretConsumed.setRecipient(recipient);
            cashierSecretConsumed.setSecret(secret);
            cashierSecretConsumed.setConsumedDate(dateOperation);
            cashierSecretConsumed.setAmount(sc.getAmount());
            cashierSecretConsumedComponent.saveCashierSecretConsumed(cashierSecretConsumed);

            //everything is ok
            jsono = new JSONObject();
            jsono.put("consumed", true);

        } catch (Exception e) {
            logger.error("CashierConsumeSecret.consumeSecret", e);
            jsono = new JSONObject("{\"error\":\"tuvimos un error interno, intentalo mas tarde\"}");
            jsono.append("consumed", false);
        }

        return jsono.toString();

    }

}
