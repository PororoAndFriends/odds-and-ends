package hello.core.web;

import hello.core.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    // 프록시를 사용하지 않을 때는 Provider를 통해 받아야함(첫 의존관계 주입 때 주입할 수가 없기때문)
//    private final ObjectProvider<MyLogger> myLoggerProvider;
    private final MyLogger myLogger;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
//        MyLogger myLogger = myLoggerProvider.getObject();
        String requestURL = request.getRequestURI();
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");

        logDemoService.logic("testId");
        return "OK";
    }
}
