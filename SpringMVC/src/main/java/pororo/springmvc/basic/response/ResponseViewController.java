package pororo.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello");

        return mav;
    }

    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "hello");

        return "response/hello";
    }

    // mapping 정보와 같으면 return이 없어도 spring에서 관례적으로 허용해줌. 추천하지는 않음
    @RequestMapping("response/hello")
    public void responseViewV3(Model model) {
        model.addAttribute("data", "hello");
    }
}
