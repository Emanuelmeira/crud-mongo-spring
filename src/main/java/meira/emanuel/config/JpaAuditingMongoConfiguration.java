package meira.emanuel.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;


@Configuration
@EnableMongoAuditing
public class JpaAuditingMongoConfiguration {

    //@Bean
    //public AuditorAware<String> auditorProvider() {
    //   /*
    //      if you are using spring security, you can get the currently logged username with following code segment.
    //      SecurityContextHolder.getContext().getAuthentication().getName()
    //     */
     //   return () -> Optional.ofNullable("chathuranga");
    //}
}