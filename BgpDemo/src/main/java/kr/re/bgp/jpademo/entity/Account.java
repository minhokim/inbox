package kr.re.bgp.jpademo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import kr.re.bgp.jpademo.service.auth.RoleEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "account")
@Setter
@Getter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    private String email;
    private String password;
    private String name;
    private String refreshToken;
    private String role;

    public Account withEmail(String email) {
        this.setEmail(email);
        return this;
    }

    public Account withPassword(String password) {
        this.setPassword(password);
        return this;
    }

    public Account withRole(RoleEnum role) {
        this.setRole(role.value());
        return this;
    }

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    @CreationTimestamp
    private Timestamp created;
}
