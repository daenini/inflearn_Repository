package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DisconuntPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
/*
  @RequiredArgsConstructor
 lombok의 기능으로 final이 붙은 변수를 가지고 생성자를 만들어준다
@RequiredArgsConstructor
 */
public class OrderServiceImpl implements OrderService {

   private final MemberRepository memberRepository;
    private final DisconuntPolicy disconuntPolicy;

    // Autowired는 생성자가 하나면 생략 가능
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DisconuntPolicy disconuntPolicy) {
        this.memberRepository = memberRepository;
        this.disconuntPolicy = disconuntPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = disconuntPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
