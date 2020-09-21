package gasi.ewf.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

//	@Autowired
//	private CustomAccessTokenConverter customAccessTokenConverter;
	
    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http
        	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            .and()
            .authorizeRequests()
            	.antMatchers("/login").permitAll()
            	.antMatchers("/oauth/**").permitAll()
            	.antMatchers("/tokens/**").permitAll()
            	.antMatchers("/v2/api-docs",
                    "/configuration/ui",
                    "/swagger-resources/**",
                    "/configuration/security",
                    "/swagger-ui.html",
                    "/webjars/**").permitAll()
            	.anyRequest().authenticated();
//            	.and()
//            		.exceptionHandling()
//            		.authenticationEntryPoint(new AuthExceptionEntryPoint());
    }

//    @Override
//    public void configure(final ResourceServerSecurityConfigurer config) {
//        config.tokenServices(tokenServices());
//    }
//
//    @Bean
//    public TokenStore tokenStore() {
//        return new JwtTokenStore(accessTokenConverter());
//    }
//
//    @Bean
//    public JwtAccessTokenConverter accessTokenConverter() {
//    	final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        converter.setAccessTokenConverter(customAccessTokenConverter);
//        final Resource resource = new ClassPathResource("public.txt");
//		String publicKey = null;
//		try {
//			publicKey = IOUtils.toString(resource.getInputStream());
//		} catch (final IOException e) {
//			throw new RuntimeException(e);
//		}
//		converter.setVerifierKey(publicKey);
//        return converter;
//    }
//
//    @Bean
//    @Primary
//    public DefaultTokenServices tokenServices() {
//        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
//        defaultTokenServices.setTokenStore(tokenStore());
//        return defaultTokenServices;
//    }

}
