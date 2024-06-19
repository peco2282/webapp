package com.github.peco2282.webapp.entity;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Authority {
  CREATE(Role.MAINTENER, 1),
  READ(Role.VISITOR, 2),
  UPDATE(Role.MAINTENER, 3),
  DELETE(Role.MAINTENER, 4);
  public static final int CREATE_ROLE = 1 << 1;
  public static final int READ_ROLE = 1 << 2;
  public static final int UPDATE_ROLE = 1 << 3;
  public static final int DELETE_ROLE = 1 << 4;
  public static final int ALL_AUTHORITY = CREATE_ROLE + READ_ROLE + UPDATE_ROLE + DELETE_ROLE;
  private final Role from;
  private final int mask;

  Authority(Role from, int mask) {
    this.from = from;
    this.mask = mask;
  }

  public static Authority[] of(Integer authority) {
    int value = authority;
    ArrayList<Authority> authorities = new ArrayList<>();
    if (value >= DELETE_ROLE) {
      authorities.add(DELETE);
      value -= DELETE_ROLE;
    }
    if (value >= UPDATE_ROLE) {
      authorities.add(UPDATE);
      value -= UPDATE_ROLE;
    }
    if (value >= READ_ROLE) {
      authorities.add(READ);
      value -= READ_ROLE;
    }
    if (value >= CREATE_ROLE) {
      authorities.add(CREATE);
    }
    return authorities.reversed().toArray(Authority[]::new);
  }

  @Contract(pure = true)
  public static int authorities(@NotNull Authority... authorities) {
    int value = 0;
    for (Authority authority : authorities) {
      value += 1 << authority.mask;
    }
    return value;
  }

  public static int roles(Role[] roles) {
    List<Role> list = Arrays.asList(roles);
    if (list.contains(Role.ADMIN)) return ALL_AUTHORITY;
    if (list.contains(Role.MAINTENER)) return CREATE_ROLE + READ_ROLE + UPDATE_ROLE;
    if (list.contains(Role.STAFF)) return READ_ROLE + UPDATE_ROLE;
    if (list.contains(Role.USER) || list.contains(Role.VISITOR)) return READ_ROLE;
    return 0;
  }

  public Role[] roles() {
    return switch (from) {
      case ADMIN -> Role.FROM_ADMIN;
      case MAINTENER -> Role.FROM_MAINTENER;
      case STAFF -> Role.FROM_STAFF;
      case USER -> Role.FROM_USER;
      case VISITOR -> Role.FROM_VISITOR;
    };
  }
}
