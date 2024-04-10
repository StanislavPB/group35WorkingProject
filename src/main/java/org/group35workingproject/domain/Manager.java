package org.group35workingproject.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Manager  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9]+$")
    private String managerName;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9!@#$%^&*()]+$")
    private String password;

    @NotBlank
    @Email
    private String email;

    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Task> tasks;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;


}