package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration()
// AppConfig class에 있는 Annotation을 제외하기 위에 필터 사용
@ComponentScan(
        // basePackages를 지정해서 스캔을 시작할 위치를 설정할 수 있음
//        basePackages = "hello.core.member",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class))
public class AutoAppConfig {


}
