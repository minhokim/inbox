package kr.re.bgp.jpademo.service.auth;

import kr.re.bgp.jpademo.entity.Account;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Getter
public class CustomUserDetails implements UserDetails {
    private final Account account;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;

    public CustomUserDetails(Account account) {
        this.account = account;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        switch (RoleEnum.valueOf(account.getRole())) {
            case ADMIN: authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN")); break;
            case MANAGER: authorities.add(new SimpleGrantedAuthority("ROLE_MANAGER")); break;
            case USER: authorities.add(new SimpleGrantedAuthority("ROLE_USER")); break;
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getEmail();
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
}
