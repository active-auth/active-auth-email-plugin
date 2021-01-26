package cn.glogs.active_auth.iam_plugin.email.entity;

import cn.glogs.activeauth.iamcore.domain.AuthenticationPrincipal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationPrincipalEmail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OneToOne
    private AuthenticationPrincipal principal;

    private String emailAddress;

    private boolean enabled;

    private boolean validated;

    private Date lastToggleEnable;

    private Date lastToggleValidated;

    private Date createdAt;

    private Date updatedAt;

    public AuthenticationPrincipalEmail(AuthenticationPrincipal principal, Form form) {
        Date now = new Date();
        this.principal = principal;
        this.emailAddress = form.emailAddress;
        this.enabled = false;
        this.validated = false;
        this.lastToggleEnable = now;
        this.lastToggleValidated = now;
        this.createdAt = now;
    }

    public Vo vo() {
        return new Vo(emailAddress, enabled, validated, lastToggleEnable, lastToggleValidated, createdAt, updatedAt);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Vo {
        private String emailAddress;
        private boolean enabled;
        private boolean validated;
        private Date lastToggleEnable;
        private Date lastToggleValidated;
        private Date createdAt;
        private Date updatedAt;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Form {
        private String emailAddress;
    }
}
