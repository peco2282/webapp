package com.github.peco2282.webapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

import java.util.StringJoiner;

@Entity
public class User implements IEntity<User> {
  @Id
  private Long id;
  private String name;
  private String password;
  private Integer role;
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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Integer getRole() {
    return role;
  }

  public void setRole(Integer role) {
    this.role = role;
  }

  public Integer getAuthority() {
    return authority;
  }

  public void setAuthority(Integer authority) {
    this.authority = authority;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
        .add("password='" + password + "'")
        .add("name='" + name + "'")
        .add("id=" + id)
        .add("role=" + rolesAsString())
        .add("authority=" + authoritiesAsString())
        .toString();
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

  public String rolesAsString() {
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
