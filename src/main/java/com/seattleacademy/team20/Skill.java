package com.seattleacademy.team20;

public class Skill {
  private String category;
  private String name;
  private int score;

  public Skill(String category, String name, int score) {
    // TODO 自動生成されたコンストラクター・スタブ
    this.category = category;
    this.name = name;
    this.score = score;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }
}