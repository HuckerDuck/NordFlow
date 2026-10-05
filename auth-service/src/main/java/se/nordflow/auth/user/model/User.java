package se.nordflow.auth.user.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import se.nordflow.auth.user.common.RoleEnums;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String password;
    private String email;

    @Column (name = "first_name")
    private String firstName;

    @Column (name = "last_name")
    private String lastName;

    @Column (name = "phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private RoleEnums role;

    @Column(name = "account_enabled")
    private Boolean enabled;

    @Column(name = "created_at")
    private LocalDateTime createdDate;

    @Column(name = "updated_at")
    private LocalDateTime updatedDate;

    @PrePersist
    private void prePersist(){
        createdDate = LocalDateTime.now();
        updatedDate = LocalDateTime.now();

        if (role == null){
            role = RoleEnums.USER;
        }

        if (enabled == null){
            enabled = true;
        }
    }
}
