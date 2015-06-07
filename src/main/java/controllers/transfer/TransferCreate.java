package controllers.transfer;

import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.BankResponse;
import model.beans.Depositor;
import model.beans.Recipient;
import model.beans.Secret;
import model.beans.Transfer;
import model.components.BankResponseComponent;
import model.components.DepositorComponent;
import model.components.RecipientComponent;
import model.components.SecretComponent;
import model.components.SecretStatusComponent;
import model.components.TransferComponent;
import model.components.TransferTypeComponent;
import model.logic.BankClientRestful;
import model.logic.Constants;
import model.logic.KeyGenerator;
import model.util.MailUtil;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author skuarch-lap
 */
@RestController
public class TransferCreate extends BaseController {

    private static final Logger logger = getLogger(TransferCreate.class);

    @Autowired
    private BankResponseComponent bankResponseComponent;
    @Autowired
    private TransferComponent transferComponent;
    @Autowired
    private DepositorComponent depositorComponent;
    @Autowired
    private RecipientComponent recipientComponent;
    @Autowired
    private TransferTypeComponent transferTypeComponent;
    @Autowired
    private SecretComponent secretComponent;
    @Autowired
    private SecretStatusComponent secretStatusComponent;

    //==========================================================================
    @RequestMapping(value = {"/v1/transfer/create", "v1/transfer/create"})
    public @ResponseBody
    String createTransfer(
            @ModelAttribute Transfer transfer,
            @RequestParam String number,
            @RequestParam String holder,
            @RequestParam String month,
            @RequestParam String year,
            @RequestParam String cvv,
            HttpServletResponse response, 
            Locale locale) {

        JSONObject jsono = null;
        Depositor depositor;
        long depositorId;
        Recipient recipient;
        long recipientId;
        Secret secret;
        long secretId;
        List<Secret> secretList;
        String responseBank;
        BankResponse bankResponse;
        long transferId;

        try {

            //basic configuration
            setHeaderNoChache(response);
            setContentType(response, MediaType.APPLICATION_JSON);

            //send payment to the bank
            responseBank = BankClientRestful
                    .sendPayment(number, month + "/" + year, cvv, transfer.getAmount().toString());

            //save bank response
            bankResponse = new BankResponse();
            bankResponse.setEmailDepositor(transfer.getDepositor().getEmail());
            bankResponse.setResponse(responseBank);
            bankResponseComponent.saveBankResponse(bankResponse);

            if (responseBank == null || !responseBank.contains("Approved")) {
                jsono = new JSONObject();
                jsono.put("errorBank", true);
                return jsono.toString();
            }
            

            //create depositor
            depositor = transfer.getDepositor();
            depositorId = depositorComponent.createDepositor(depositor);
            depositor.setId(depositorId);

            //create secret
            secret = new Secret();
            secret.setSecretAlphanumeric(new KeyGenerator().generateSecret(8));
            secret.setValue(transfer.getAmount());
            secret.setSecretStatus(secretStatusComponent.getStatus(Constants.SECRET_STATUS_ACTIVE));
            secretId = secretComponent.saveSecret(secret);
            secret.setId(secretId);
            secretList = new ArrayList<>();
            secretList.add(secret);

            //create recipient
            recipient = transfer.getRecipient();
            recipient.setSecret(secretList);
            recipientId = recipientComponent.createRecipient(recipient);
            recipient.setId(recipientId);

            //crear la transferencia
            transfer.setDepositor(depositor);
            transfer.setRecipient(recipient);
            transfer.setSecretAlphanumeric(secret.getSecretAlphanumeric());
            transfer.setTransferType(transferTypeComponent.getTransferType(Constants.TRANSACTION_TYPE_CREDIT_CARD));
            transferId = transferComponent.createTransfer(transfer);
            transfer.setId(transferId);
            
            //send mail to recipiet
            MailUtil.sendMailRecipientNewTransfer(transfer, locale.getDisplayLanguage());
            
            //send mail to depositor
            MailUtil.sendMailDepositorNewTransfer(transfer, locale.getDisplayLanguage());
            
            //update bankReponse
            bankResponse.setTransfer(transfer);
            bankResponseComponent.updateBankResponse(bankResponse);

            jsono = new JSONObject();
            jsono.put("created", true);

        } catch (Exception e) {
            logger.error("TransferCreate.createTransfer", e);
            jsono = new JSONObject("{\"error\":\"" + e + "\",}");
            jsono.append("created", false);
        }

        return jsono.toString();

    }

}
