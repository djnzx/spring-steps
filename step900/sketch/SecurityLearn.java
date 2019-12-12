package tacos.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class SecurityLearning extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.userDetailsService(...)      // HttpSecurity
    http.addFilter(...);              // HttpSecurity
    http.addFilterBefore(...);        // HttpSecurity
    http.addFilterAfter(...);         // HttpSecurity
    http.addFilterAt(...);            // HttpSecurity
    http.anonymous(...);              // HttpSecurity
    http.antMatcher(...)              // HttpSecurity
    http.authenticationProvider(...)  // HttpSecurity
    http.mvcMatcher(...);             // HttpSecurity
    http.regexMatcher(...)            // HttpSecurity
    http.requestMatcher(...)          // HttpSecurity
    http.cors();                      // CorsConfigurer<HttpSecurity>
    http.authorizeRequests();         // ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry
    http.logout();                    // LogoutConfigurer<HttpSecurity>
    http.formLogin();                 // FormLoginConfigurer<HttpSecurity>
    http.logout();                    // LogoutConfigurer<HttpSecurity>
    http.csrf();                      // CsrfConfigurer<HttpSecurity>
    http.headers();                   // HeadersConfigurer<HttpSecurity>
    http.anonymous();                 // AnonymousConfigurer<HttpSecurity>
    http.exceptionHandling()          // ExceptionHandlingConfigurer<HttpSecurity>
    http.httpBasic()                  // HttpBasicConfigurer<HttpSecurity>
    http.jee();                       // JeeConfigurer<HttpSecurity>
    http.oauth2Client();              // OAuth2ClientConfigurer<HttpSecurity>
    http.oauth2Login();               // OAuth2LoginConfigurer<HttpSecurity>
    http.oauth2ResourceServer();      // OAuth2ResourceServerConfigurer<HttpSecurity>
    http.openidLogin()                // OpenIDLoginConfigurer<HttpSecurity>
    http.portMapper()                 // PortMapperConfigurer<HttpSecurity>
    http.rememberMe()                 // RememberMeConfigurer<HttpSecurity>
    http.requestCache();              // RequestCacheConfigurer<HttpSecurity>
    http.requestMatchers()            // RequestMatcherConfigurer
    http.requiresChannel()            // ChannelSecurityConfigurer<HttpSecurity>
    http.saml2Login()                 // Saml2LoginConfigurer<HttpSecurity>
    http.securityContext()            // SecurityContextConfigurer<HttpSecurity>
    http.servletApi()                 // ServletApiConfigurer<HttpSecurity>
    http.sessionManagement()          // SessionManagementConfigurer<HttpSecurity>
    http.x509()                       // X509Configurer<HttpSecurity>
  }
}
