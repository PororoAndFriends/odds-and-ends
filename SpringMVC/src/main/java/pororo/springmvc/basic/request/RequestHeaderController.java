package pororo.springmvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@Slf4j
@RestController
public class RequestHeaderController {


    // spring에서 웬만한 기본적인 Servlet 객체같은 것들은 매개변수로 적어주기면 하면 지원이 됨
    @RequestMapping("/headers")
    public String headers(HttpServletRequest request,
                          HttpServletResponse response,
                          HttpMethod method,
                          Locale locale,
                          @RequestHeader MultiValueMap<String, String> headerMap,
                          @RequestHeader("host") String host,
                          @CookieValue(value="myCookie", required = false) String cookie
                          ) {

        log.info("request={}",request);
        log.info("response={}",response);
        log.info("method={}",method);
        log.info("locale={}",locale);
        log.info("headerMap={}",headerMap);
        log.info("host={}",host);
        log.info("cookie={}",cookie);

        return "ok";
    }


}
