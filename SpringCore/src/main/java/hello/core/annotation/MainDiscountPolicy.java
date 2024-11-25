package hello.core.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

// 그냥 Qulifier에 있는 어노테이션 전부 복붙
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER,
        ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
// 에 추가로 Qulifier값을 지정
@Qualifier("mainDiscountPolicy")
public @interface MainDiscountPolicy {

}
