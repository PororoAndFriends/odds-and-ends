package pororo.springmvc.basic.request;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import pororo.springmvc.basic.HelloData;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        response.getWriter().write("ok");
    }

    @PostMapping("/request-body-json-v2")
    @ResponseBody
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {

        log.info("messageBody={}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }

    @PostMapping("/request-body-json-v3")
    @ResponseBody
    // @RequestBody에 받은 데이터타입을 HelloData로 바꾸면 자동으로 JSON으로 넘어온 값들이 바인딩됨
    // 단, @ModelAttribute와 다르게 @RequestBody는 생략할 수 없음
    public String requestBodyJsonV3(@RequestBody HelloData helloData) {

        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }

    @PostMapping("/request-body-json-v4")
    @ResponseBody
    // HttpEntity의 제네릭타입에 원하는 데이터타입을 넣어서 받을 수도 있음
    public String requestBodyJsonV4(HttpEntity<HelloData> httpEntity) {

        HelloData helloData = httpEntity.getBody();
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }

    @PostMapping("/request-body-json-v5")
    @ResponseBody
    public HelloData requestBodyJsonV5(@RequestBody HelloData helloData) {

        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        return helloData;
    }
}
