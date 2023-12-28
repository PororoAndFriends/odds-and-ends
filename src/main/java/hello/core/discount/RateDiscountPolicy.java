package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("mainDiscount")
// 빈에 특별한 이름을 하나 더 부여. 빈이 여러 개 조회될 떄 사용
//@Primary
// Primary : 빈 조회시 동일타입이 여러 개일 경우 우선권을 가질 수 있게 해줌
@MainDiscountPolicy

public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP) return price * discountPercent / 100;
        else return 0;
    }
}
