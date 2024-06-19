package com.github.peco2282.webapp

import com.github.peco2282.webapp.authentication.*
import com.github.peco2282.webapp.mapper.UserMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.oauth2.core.*
import org.springframework.security.oauth2.jwt.*
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

typealias WebUser = org.springframework.security.core.userdetails.User

@Configuration
@EnableWebSecurity
class WebSecurityConfig {
//  @Bean
//  fun jwtDecoder(): JwtDecoder {
//    val jwtDecoder = NimbusJwtDecoder.withJwkSetUri("").build()
//    val withIssuer = JwtValidators.createDefaultWithIssuer("")
//    val withAudience = DelegatingOAuth2TokenValidator(
//      withIssuer//, OAuth2TokenValidator { null } // audienceはあなたのリソースサーバーを識別する値
//    )
//    jwtDecoder.setJwtValidator(withAudience)
//    return jwtDecoder
//  }

  @Autowired
  lateinit var mapper: UserMapper


  @Bean
  @Throws(Exception::class)
  fun securityFilterChain(http: HttpSecurity, manager: AuthenticationManager): SecurityFilterChain {
    http
      .authorizeHttpRequests {
        it
//          .anyRequest().permitAll()
          .requestMatchers("/api/**").access(BearerAuthorizationManager())
          .requestMatchers("/login").permitAll()
          .requestMatchers("/users/**").permitAll()
          .requestMatchers("/vote/**").permitAll()
          .requestMatchers("/users/create").anonymous()
          .anyRequest().authenticated()
      }
      .formLogin {
        it
//          .loginProcessingUrl("/login")
          .loginPage("/login")
          .defaultSuccessUrl("/")
          .failureUrl("/login?error")
          .permitAll()
          .failureHandler(failureHandler())
      }
      .logout {
        it.deleteCookies()
          .invalidateHttpSession(false)
//          .logoutUrl("/logout")
          .permitAll()
      }
      .csrf {
//        it.disable()
        it.ignoringRequestMatchers("/api/**")
      }
//      .addFilterBefore(
//        customAnonymousAuthenticationFilter(AntPathRequestMatcher("/api/**")),
//        AuthorizationFilter::class.java
//      )
      .addFilter(bearerAuthorizationFilter(manager))
//      .addFilter(bearerAuthorizationFilter(manager))
      .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }

    return http.build()
  }

  @Bean
  @Throws(Exception::class)
  fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager =
    authenticationConfiguration.authenticationManager

  @Bean
  fun userDetailsService(): UserDetailsService {
    val l = mapper.selectAll()
    val list = ArrayList<UserDetails>()
    l.forEach {
      list += WebUser.builder()
        .passwordEncoder(AuthConstant.PASSWORD_ENCODER) // .withDefaultPasswordEncoder()
        .username(it.name)
        .password(it.password)
        .roles(*it.rolesAsStringArray())
        .build()
    }
    return InMemoryUserDetailsManager(list)
  }

//  @Bean
//  fun bearerAuthFilter(): FilterRegistrationBean<BearerAuthFilter> {
//    val registration = FilterRegistrationBean<BearerAuthFilter>()
//    registration.setFilter(BearerAuthFilter())
//    registration.addUrlPatterns("/api/**")
//    return registration
//  }

  fun bearerAuthorizationFilter(authenticationManager: AuthenticationManager): BearerAuthorizationFilter {
    // 独自フィルター作成
    val filter = BearerAuthorizationFilter(authenticationManager) // WebUsernamePasswordAuthenticationFilter()
//    filter.setAuthenticationManager(authenticationManager)
//    filter.setRequiresAuthenticationRequestMatcher(AntPathRequestMatcher("/api/**", "GET")) // ログイン時URL
//    filter.setAuthenticationSuccessHandler(successHandler()) // 成功時URL
    filter.setAuthenticationFailureHandler(failureHandler()) // 失敗時URL
    //filter.setUsernameParameter("username"); // ユーザ パラメータ名
    //filter.setPasswordParameter("password"); // パスワード パラメータ名
    return filter
  }

  @Bean
  fun successHandler() = SuccessHandler()

  @Bean
  fun failureHandler() = FailureHandler()
}