package com.pierre.oceanblu.service;

import com.pierre.oceanblu.model.GoogleUserInfo;
import com.pierre.oceanblu.model.User;
import com.pierre.oceanblu.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyOidcUserService extends OidcUserService {

    private final UserRepository userRepository;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {

        OidcUser oidcUser = super.loadUser(userRequest);

        try {

            return processOidcUser(oidcUser);

        } catch (Exception e) {

            throw new InternalAuthenticationServiceException(e.getMessage(), e.getCause());
        }
    }

    private OidcUser processOidcUser(OidcUser oidcUser) {

        GoogleUserInfo googleUserInfo = new GoogleUserInfo(oidcUser.getAttributes());

        Optional<User> userOptional = userRepository.getUserByUsername(googleUserInfo.getId());

        if (!userOptional.isPresent()) {

            User user = User.builder()
                    .username(googleUserInfo.getId())
                    .firstName(googleUserInfo.getGivenName())
                    .lastName(googleUserInfo.getFamilyName())
                    .role(User.Role.valueOf(oidcUser.getAuthorities().iterator().next().getAuthority().substring(5)))
                    .gender(User.Gender.MALE)
                    .password("")
                    .active(true)
                    .build();

            userRepository.save(user);
        }

        return oidcUser;
    }
}