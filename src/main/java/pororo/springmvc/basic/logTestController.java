package pororo.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// `@Controller` 는 반환 값이 `String` 이면 뷰 이름으로 인식된다. 그래서 뷰를 찾고 뷰가 랜더링 된다.
// `@RestController` 는 반환 값으로 뷰를 찾는 것이 아니라, HTTP 메시지 바디에 바로 입력한다.
@RestController
@Slf4j /* lombok이 밑에 주석처리되어있는 Logger 객체를 만들어줌 */
public class logTestController {


//    private final Logger log = LoggerFactory.getLogger(logTestController.class);
//    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest(){
        String name = "Spring";


//      application.properties에서 logging.level.pororo.springmvc=trace
//      이런 식으로 로그 레벨을 설정할 수 있음. default : info
//      연산 처리속도가 있기 때문에 String끼리의 + 연산이 아닌 {}를 통해서 log 변수 출력
//      기본적으로 시간, 로그 레벨, 프로세스 ID, 쓰레드 명, 클래스명, 로그 메시지 가 출력됨
        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info("info log={}", name);
        log.warn("warn log={}", name);
        log.error("error log={}", name);

        return "ok";
    }

}
