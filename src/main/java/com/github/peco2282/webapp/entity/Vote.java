package com.github.peco2282.webapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Vote implements IEntity<Vote> {
  public static final String CHOICE_SEPARATOR = ",";
  @Id
  public long id;
  public String title;
  public String description;
  @ToString.Exclude
  public String choices;

  @ToString.Include(name = "choices")
  public List<String> getChoicesToString() {
    return Arrays.stream(choices.split(CHOICE_SEPARATOR)).toList();
  }

  @Override
  public Vote copy() {
    return this;
  }
}
