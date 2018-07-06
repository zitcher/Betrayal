package com.term_project.character;

import java.util.List;

import com.term_project.house.TileBean;
import com.term_project.items.Item;
import com.term_project.omens.Omen;

public class CharBean {
  private String name;
  private int might;
  private int speed;
  private int knowledge;
  private int sanity;
  private List<Integer> mightScale;
  private List<Integer> speedScale;
  private List<Integer> knowledgeScale;
  private List<Integer> sanityScale;
  private TileBean currentTile;
  private List<Item> items;
  private List<Omen> omens;

  public CharBean(String name, int might, int speed, int knowledge,
      int sanity, List<Integer> mightScale, List<Integer> speedScale,
      List<Integer> knowledgeScale, List<Integer> sanityScale,
      TileBean currentTile, List<Item> items, List<Omen> omens) {
    this.name = name;
    this.might = might;
    this.speed = speed;
    this.knowledge = knowledge;
    this.sanity = sanity;
    this.mightScale = mightScale;
    this.speedScale = speedScale;
    this.knowledgeScale = knowledgeScale;
    this.sanityScale = sanityScale;
    this.currentTile = currentTile;
    this.items = items;
    this.omens = omens;
  }
}
