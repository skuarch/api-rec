package controllers.application;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import model.beans.Affiliate;
import model.beans.Establishment;
import model.database.DAO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author skuarch
 */
@RestController
public class Test extends BaseController {

    @RequestMapping(value = {"/test", "test"})
    public ModelAndView test(HttpServletResponse response) {

        ModelAndView mav = null;

        try {

            mav = new ModelAndView("application/test");
            Affiliate affiliate = new DAO().get(1, new Affiliate());
            List<Establishment> establiments = affiliate.getEstablishment();
            System.out.println(establiments.get(0).getName());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mav;

    }

}
