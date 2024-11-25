package hello.core.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

                            // 초기 버전 의존관계 주입 후 초기화를 위한 인터페이스
public class NetworkClient /* implements InitializingBean, DisposableBean */ {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);

    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect(){
        System.out.println("connect : " + url);
    }

    public void call(String message){
        System.out.println("call : " + url + "message = " + message);
    }

    public void disconnect(){
        System.out.println("colse : " + url);
    }

    // 모든 빈 의존관계 주입이 끝난 후에 초기화 작업(옛날 방법이라 요즘은 잘 안씀)
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        System.out.println("NetworkClient.afterPropertiesSet");
//        connect();
//        call("초기화 연결 메세지");
//    }
//
//    //
//    @Override
//    public void destroy() throws Exception {
//        System.out.println("NetworkClient.destroy");
//        disconnect();
//    }

    // 요즘 가장 많이 쓰는 방식. @PostConstruct, @PreDestroy 어노테이션만 붙여주면 됨
    // 자바에서 공식으로 지원. 스프링 종속 x, 자바 표준

                                
    @PostConstruct
    public void init(){
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결");
    }

    @PreDestroy
    public void close(){
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
