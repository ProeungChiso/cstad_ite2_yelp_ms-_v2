package kh.edu.cstad.gateway.security;

import kh.edu.cstad.gateway.dto.UserProfile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
public class SecurityController {

    @GetMapping("/profile")
    UserProfile secured(@AuthenticationPrincipal Authentication auth) {
        OAuth2AuthenticationToken oauth2 = (OAuth2AuthenticationToken) auth;
        DefaultOidcUser oidcUserInfo = (DefaultOidcUser) oauth2.getPrincipal();
        Map<String, Object> attributes = oidcUserInfo.getAttributes();
        System.out.println(oauth2.getPrincipal() instanceof DefaultOidcUser);
        System.out.println("Get all attributes: " + oidcUserInfo.getAttributes());
        System.out.println("Get username: " + oauth2.getPrincipal().getAttributes().get("chiso"));
        System.out.println("Get jti: " + oauth2.getPrincipal().getAttributes().get("jti"));
        System.out.println("Get sub: " + oauth2.getPrincipal().getAttributes().get("sub"));
        return new UserProfile(
                attributes.get("chiso").toString(),
                attributes.get("jti").toString(),
                attributes.get("sub").toString()
        );
    }

}