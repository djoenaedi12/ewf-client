package gasi.ewf.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import gasi.ewf.entity.UserPrincipal;
import gasi.ewf.service.CustomUserDetailsService;

public class CustomTokenEnhancer implements TokenEnhancer {

	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        final Map<String, Object> additionalInfo = new HashMap<>();
        UserPrincipal principal = (UserPrincipal) customUserDetailsService.loadUserByUsername(authentication.getName());
        additionalInfo.put("email", principal.getEmail());
        additionalInfo.put("name", principal.getName());
        additionalInfo.put("image", principal.getImage());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }
}
