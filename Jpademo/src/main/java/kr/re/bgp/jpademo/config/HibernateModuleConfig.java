package kr.re.bgp.jpademo.config;

import com.fasterxml.jackson.datatype.hibernate5.jakarta.Hibernate5JakartaModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateModuleConfig {

    //지연로딩 프록시 객체의 Infinite recursion 이슈 대응
    @Bean
    Hibernate5JakartaModule hibernate5JakartaModule(){
        Hibernate5JakartaModule hibernate5JakartaModule = new Hibernate5JakartaModule();
        hibernate5JakartaModule.configure(Hibernate5JakartaModule.Feature.FORCE_LAZY_LOADING, true);
        return hibernate5JakartaModule;
    }

}
