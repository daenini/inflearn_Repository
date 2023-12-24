package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DisconuntPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class AllBeanTest {

    @Test
    void findAllBean() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class,DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);
        int discuntPolicy = discountService.discount(member,1000,"fixDiscountPolicy");

        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discuntPolicy).isEqualTo(1000);

        int rateDiscountPolicy = discountService.discount(member,1000,"rateDiscountPolicy");
        assertThat(rateDiscountPolicy).isEqualTo(2000);
    }

    static class DiscountService {
        private final Map<String, DisconuntPolicy> policyMap;
        private final List<DisconuntPolicy> policyList;

        @Autowired
        public DiscountService(Map<String, DisconuntPolicy> policyMap, List<DisconuntPolicy> policyList) {
            this.policyMap = policyMap;
            this.policyList = policyList;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policyList = " + policyList);
        }

        public int discount(Member member, int price, String discountCode) {
            DisconuntPolicy disconuntPolicy = policyMap.get(discountCode);

            return disconuntPolicy.discount(member,price);
        }
    }
}
