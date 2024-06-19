package com.github.peco2282.webapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.StringJoiner;

@Entity
@Getter
@EqualsAndHashCode
@Data
@ToString
public class User implements IEntity<User> {
  @Id
  private Long id;
  private String name;
  private String password;
  @ToString.Exclude
  private Integer role;
  @ToString.Exclude
  private Integer authority;

  @NotNull
  public static User from(long id, String name, String password, int role, int authority) {
    User user = new User();
    user.id = id;
    user.name = name;
    user.password = password;
    user.role = role;
    user.authority = authority;
    return user;
  }

  public Role[] roles() {
    return Role.of(role);
  }

  public String[] rolesAsStringArray() {
    Role[] all = roles();
    String[] roles = new String[Role.size(role)];
    for (int i = 0; i < roles().length; i++) {
      roles[i] = all[i].name();
    }
    return roles;
  }

  @ToString.Include(name = "roles")
  public String roleAsString() {
    StringJoiner joiner = new StringJoiner(", ", "[", "]");
    for (Role r : roles()) {
      joiner.add(r.name());
    }
    return joiner.toString();
  }

  public Authority[] authorities() {
    return Authority.of(authority);
  }

  public String[] authoritiesAsStringArray() {
    Authority[] authorities = authorities();
    String[] auths = new String[authorities.length];
    for (int i = 0; i < authorities.length; i++) {
      auths[i] = authorities[i].name();
    }
    return auths;
  }

  @ToString.Include(name = "authorities")
  public String authoritiesAsString() {
    StringJoiner joiner = new StringJoiner(", ", "[", "]");
    for (Authority a : authorities()) {
      joiner.add(a.name());
    }
    return joiner.toString();
  }

  @Override
  public User copy() {
    return this;
  }
}
