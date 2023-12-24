package hello.core;

import hello.core.discount.DisconuntPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // 탐색할 패키지의 시작 위치를 지정한다 이 패키지를 포함해서 하위 패키지를 모두 탐색한다.
        // 만약 지정하지 않으면 ComponentScan이 붙은 곳 부터 시작
        // basePackages = "hello.core",
        // componentScan은 @Component어노테이션이 붙은 클래스를 스캔해서 스프링 빈으로 등록해준다
        /**
            ComponentScan 을 사용하면  @Configuration 이 붙은 정보도 자동으로 등록되기 때문에 AppConfig, TestConfig등 앞서 만든
        설정 정보도 함꼐 등록되어 해당 부분을 스캔 대상에서 제외한다
        */
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = Configuration.class)
)
public class AutoAppConfig {

/*    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }*/
}
