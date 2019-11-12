//package com.example.apigateway.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@EnableWebSecurity    // Enable security config. This annotation denotes config for spring security.
//public class SecurityTokenConfig extends WebSecurityConfigurerAdapter {
////    @Autowired
////    private JwtConfig jwtConfig;
//
//    @Autowired
//    private JwtRequestFilter jwtRequestFilter;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                // handle an authorized attempts
////                .exceptionHandling().authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
////                .and()
//                // Add a filter to validate the tokens with every request
////                .addFilterAfter(new JwtTokenAuthenticationFilter(jwtConfig), UsernamePasswordAuthenticationFilter.class)
//                // authorization requests config
//                .authorizeRequests()
//                // allow all who are accessing "auth" service
////                .antMatchers(HttpMethod.POST, jwtConfig.getUri()).permitAll()
//                .antMatchers("users/login", "users/signup").permitAll()
//                .anyRequest().authenticated();
//
//        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//    }
//
////    @Bean
////    public JwtConfig jwtConfig() {
////        return new JwtConfig();
////    }
//}
