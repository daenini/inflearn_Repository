package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class FixDiscountPolicy implements DisconuntPolicy{

    private int discountFixAmount = 1000; //1000원 할인
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            // Enum 타입은 == 으로 비교해야함
            return discountFixAmount;
        } else {
            // vip가 아니면 할인 없음
            return 0;
        }
    }
}
