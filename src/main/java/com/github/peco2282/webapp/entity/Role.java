package com.github.peco2282.webapp.entity;

/**
 * 1: ADMIN<br>
 * 2: MAINTAINER<br>
 * 3: STAFF<br>
 * 4: USER<br>
 * 5: VISITOR<br>
 *
 * @author peco2282
 */
public enum Role {
  ADMIN, //
  MAINTENER,
  STAFF,
  USER,
  VISITOR;
  public static final Role[] FROM_VISITOR = new Role[]{ADMIN, MAINTENER, STAFF, USER, VISITOR};
  public static final Role[] FROM_USER = new Role[]{ADMIN, MAINTENER, STAFF, USER};
  public static final Role[] FROM_STAFF = new Role[]{ADMIN, MAINTENER, STAFF};
  public static final Role[] FROM_MAINTENER = new Role[]{ADMIN, MAINTENER};
  public static final Role[] FROM_ADMIN = new Role[]{ADMIN};

  public static final Integer DEFAULT_ROLE = VISITOR.ordinal() + 1;
  public static final Integer COUNT = values().length;

  public static int size(int role) {
    if (1 <= role && role < values().length + 1) return values().length - role + 1;
    return DEFAULT_ROLE;
//    throw new IllegalStateException("Unexpected value: " + role);
  }

  public static Role[] of(int role) {
    return switch (role) {
//      case 0 -> new Role[0];
      case 1 -> values();
      case 2 -> new Role[]{MAINTENER, STAFF, USER, VISITOR};
      case 3 -> new Role[]{STAFF, USER, VISITOR};
      case 4 -> new Role[]{USER, VISITOR};
      case 5 -> new Role[]{VISITOR};
      default -> throw new IllegalStateException("Unexpected value: " + role);
    };
  }
}