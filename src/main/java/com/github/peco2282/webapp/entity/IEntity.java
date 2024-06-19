package com.github.peco2282.webapp.entity;

public interface IEntity<E> extends Cloneable {
  E copy();
}
