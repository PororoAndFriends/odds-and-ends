package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;


import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
        bean1.addCount();
        assertThat(bean1.getCount()).isEqualTo(1);


        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);
        bean2.addCount();
        assertThat(bean1.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean1.class, PrototypeBean.class);
        ClientBean1 clientBean1 = ac.getBean(ClientBean1.class);
        assertThat(clientBean1.logic()).isEqualTo(1);
        ClientBean1 clientBean2 = ac.getBean(ClientBean1.class);
//         singleton인 ClientBean을 받기 때문에 2가 됨 -> prototype을 쓰고 싶은 의도 다르게 설정
        assertThat(clientBean2.logic()).isEqualTo(2);

    }

    @Test
    void ObjectProvider(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean2.class, PrototypeBean.class);
        ClientBean2 clientBean1 = ac.getBean(ClientBean2.class);
        assertThat(clientBean1.logic()).isEqualTo(1);
        ClientBean2 clientBean2 = ac.getBean(ClientBean2.class);
        assertThat(clientBean2.logic()).isEqualTo(1);
    }

    @Scope("singleton")
    static class ClientBean1 {

        private final PrototypeBean prototypeBean;  // 생성시점에 주입 -> prototype객체 하나가 이미 만들어져 있음

        public ClientBean1(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }


        public int logic(){
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("singleton")
    static class ClientBean2 {

        @Autowired
        // 컨테이너에서 빈을 찾아주는 역할(DL. Depandancy Lookup)만 수행
//        private ObjectProvider<PrototypeBean> prototypeBeanProvider;
        // 상위 타입에 ObjectFactory도 있음
//        private ObjectFactory<PrototypeBean> prototypeBeanProvider;
        // 자바에서 지원하는 Provider -> 스프링 의존성 감소
        private Provider<PrototypeBean> prototypeBeanProvider;

        public int logic(){
//            ObjectProvider 일때의 메서드 : getObject()
//            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean{
        private int count = 0;

        public void addCount(){
            count++;
        }

        public int getCount(){
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init" + this);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
        }
    }
}
