package mondays.net.mroki.api.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({ "password", "email", "phone", "role" })
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String password;

    private String avatar;

    private String email;

    private String phone;

    @JoinColumn()
    private Role role;
}
