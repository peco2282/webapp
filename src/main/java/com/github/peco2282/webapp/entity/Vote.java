package com.github.peco2282.webapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

@Entity
public class Vote implements IEntity<Vote> {
  public static final String CHOICE_SEPARATOR = ",";
  @Id
  public long id;
  public String title;
  public String description;
  public String choices;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getChoices() {
    return choices;
  }

  public void setChoices(String choices) {
    this.choices = choices;
  }

  public List<String> getChoicesToString() {
    return Arrays.stream(choices.split(CHOICE_SEPARATOR)).toList();
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Vote.class.getSimpleName() + "[", "]")
        .add("id=" + id)
        .add("title='" + title + "'")
        .add("description='" + description + "'")
        .add("choices='" + getChoicesToString() + "'")
        .toString();
  }

  @Override
  public Vote copy() {
    return this;
  }
}
