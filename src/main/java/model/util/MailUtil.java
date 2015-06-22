package model.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import model.beans.Affiliate;
import model.beans.Freelancer;
import model.beans.Partner;
import model.beans.Recipient;
import model.beans.Secret;
import model.beans.Transfer;
import model.logic.MailSender;
import model.logic.ReportGiftProcessor;
import org.apache.log4j.Logger;

/**
 * send mails using threads.
 *
 * @author skuarch-lap
 */
public class MailUtil {

    private static final Logger logger = Logger.getLogger(MailUtil.class);

    //==========================================================================
    private MailUtil() {
    }

    //==========================================================================
    public static void sendMailNewPartner(Partner partner, String displayLanguage) {

        new Thread(() -> {
            try {

                MailSender.sendMailNewPartner(partner, displayLanguage);

            } catch (Exception e) {
                logger.error("MailUtil.sendMailNewAffiliatePerson", e);
            }
        }).start();

    }

    //==========================================================================
    public static void sendMailNewAffiliate(Affiliate affiliate, String displayLanguage) {

        new Thread(() -> {
            try {

                MailSender.sendMailNewAffiliate(affiliate, displayLanguage);

            } catch (Exception e) {
                logger.error("MailUtil.sendMailNewAffiliatePerson", e);
            }
        }).start();

    }

    //==========================================================================
    public static void sendMailUpdateInformation(String email, String displayLanguage) {

        new Thread(() -> {
            try {

                MailSender.sendMailNewUpdateInformation(email, displayLanguage);

            } catch (Exception e) {
                logger.error("MailUtil.sendMailUpdateInformation", e);
            }
        }).start();

    }

    //==========================================================================
    public static void sendMailNewFreelancer(Freelancer freelancer, String displayLanguage) {

        new Thread(() -> {
            try {

                MailSender.sendMailNewFreelancer(freelancer, displayLanguage);

            } catch (Exception e) {
                logger.error("MailUtil.sendMailNewAffiliatePerson", e);
            }
        }).start();

    }

    //==========================================================================
    public static void sendMailDepositorNewTransfer(Transfer transfer, String displayLanguage) {

        new Thread(() -> {

            try {

                MailSender.sendMailDepositorNewTransfer(transfer, displayLanguage);

            } catch (Exception e) {
                logger.error("MailUtil.sendMailDepositorNewTransfer", e);
            }

        }).start();

    }

    //==========================================================================
    public static void sendMailRecipientNewTransfer(Transfer transfer, String displayLanguage) {

        new Thread(() -> {

            try {

                MailSender.sendMailRecipientNewTransfer(transfer, displayLanguage);

            } catch (Exception e) {
                logger.error("MailUtil.sendMailRecipientNewTransfer", e);
            }

        }).start();

    }

    //==========================================================================
    public static void sendMailRecipientNewTransfer(Recipient recipient, BigDecimal amount, String displayLanguage) {

        new Thread(() -> {

            try {

                MailSender.sendMailRecipientNewTransfer(recipient, amount, displayLanguage);

            } catch (Exception e) {
                logger.error("MailUtil.sendMailRecipientNewTransfer", e);
            }

        }).start();

    }
    
    //==========================================================================
    public static void sendMailRecipientNewSecret(Recipient recipient, BigDecimal amount, Secret secret,String displayLanguage) {

        new Thread(() -> {

            try {

                MailSender.sendMailRecipientNewSecret(recipient, amount, secret,displayLanguage);

            } catch (Exception e) {
                logger.error("MailUtil.sendMailRecipientNewTransfer", e);
            }

        }).start();

    }
    
    //==========================================================================
    public static void sendReportGiftByEmail(ArrayList<Transfer> transferlistDepositor,ArrayList<Transfer> transferlistRecipient,String email, String displayLanguage) {

        new Thread(() -> {

            try {

                new ReportGiftProcessor().sendReportGiftByEmail(transferlistDepositor, transferlistRecipient, email);

            } catch (Exception e) {
                logger.error("MailUtil.sendMailRecipientNewTransfer", e);
            }

        }).start();

    }

}
