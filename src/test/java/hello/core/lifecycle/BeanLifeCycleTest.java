package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifecycleTest(){
        // ApplicationContext <- Configurable~~ <- AnnotationConfig~~~
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig{

        // 어노테이션을 이용한 의존관계 주입 이후 초기화
        // destroyMethod는 default가 (infer)로 되어있음
        // 보통 외부라이브러리를 사용할 때 종료 메서드가 close 나 shutdown으로 설정되어 있기 때문에 자동으로 찾아서 실행시켜줌
        // 근데 요즘은 또 안씀
//        @Bean(initMethod = "init", destroyMethod = "close")
        @Bean
        public NetworkClient networkClient(){
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            
            return networkClient;
        }
    }
}
