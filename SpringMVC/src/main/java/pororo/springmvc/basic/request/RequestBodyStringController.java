package pororo.springmvc.basic.request;


import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyStringV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);
        response.getWriter().write("ok");
    }

    // spring mvc에서 자동으로 InputStream과 Writer를 찾아서 넣어줌
    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer writer) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);

        writer.write("ok");
    }

    // httpEntity : http의 헤더의 바디의 정보를 편하게 해줌
    // http의 바디를 읽어 httpMessageConverter를 통해 httpEntity로 반환해줌
    // return에도 사용할 수 있음. 이 때는 view를 조회하지 않고 body에 바로 메세지를 넣음
    // 더 하위에 RequestEntity와 ResponseEntity가 있음
    @PostMapping("/request-body-string-v3")
    public ResponseEntity<String> requestBodyStringV3(RequestEntity<String> httpEntity) throws IOException {


        String messageBody = httpEntity.getBody();
        log.info("messageBody={}", messageBody);

//        return new HttpEntity<>("ok");
        return new ResponseEntity<>("ok", HttpStatus.CREATED);
    }

    // 사실 그냥 @RequestBody 어노테이션으로 해결할 수 있음
    @PostMapping("/request-body-string-v4")
    // @ResponseBody의 경우에도 view를 찾지 않고 바로 body에 값을 넣어줌
    @ResponseBody
    public String requestBodyStringV4(@RequestBody String messageBody) {

        log.info("messageBody={}", messageBody);

        return "ok";
    }
}
