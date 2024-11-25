package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

// Spring Bean이 관리하는 Component이기 때문에 @Autowired 사용 가능
@Component
// Lombok에서 지원하는 어노테이션. Required : final 관련된 변수들의 생성자를 자동으로 만들어줌
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

//    @Autowired : 필드주입. 생성자를 제외하곤 모두 final을 빼줘야 함. private여도 가능
//    필드 주입은 추천하는 방식은 아님. 임의로 생성된 객체에 대해 Autoawired가 주입되지 않는데 이를 주입하려면 어처피 setter가 필요
//    보통의 경우 테스트 코드를 짤 때 간단하게 주입하기 위해 사용
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    // 만든 annotation @MainDiscountPolicy를 통한 빈 조회
// cf) Qulifier가 Primary보다 조회 우선순위가 높음(Qulifier가 더 자세함)
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        // Qulifier를 통한 동일타입 빈 중 하나 선택
//    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscount") DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    //    setter 를 통한 주입. @Autowired(required=false)로 설정시 선택적 주입 가능. 변경 가능
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }


//    @Autowired
//    생성자가 하나일 때는 @Autowired 생략 가능
//    생성자를 통해 주입하면 불변, 필수적으로 주입
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    // 결국 이런 꼬라지는 OCP(Open close principle), DIP(Dependency inversion principle) 위반
//    private final DiscountPolicy discountPolicy = new FIxDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
