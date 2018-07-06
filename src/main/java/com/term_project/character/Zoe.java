package com.term_project.character;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jfacey
 *
 */
public class Zoe extends AbstractChar implements GameChar {

  private String name;

  /**
   *
   */
  public Zoe() {
    this.setMight(3);
    this.setSpeed(3);
    this.setKnowlege(2);
    this.setSanity(2);
    // scales are of size 8. Death when index falls below 0
    this.setMightScale(buildMightScale());
    this.setSpeedScale(buildSpeedScale());
    this.setKnowledgeScale(buildKnowledgeScale());
    this.setSanityScale(buildSanityScale());

    name = "Zoe Ingstrom";
  }

  private List<Integer> buildMightScale() {
    List<Integer> mightList = new ArrayList<>();
    mightList.add(2);
    mightList.add(2);
    mightList.add(3);
    mightList.add(3);
    mightList.add(4);
    mightList.add(4);
    mightList.add(6);
    mightList.add(7);
    return mightList;
  }

  private List<Integer> buildSpeedScale() {
    List<Integer> speedList = new ArrayList<>();
    speedList.add(4);
    speedList.add(4);
    speedList.add(4);
    speedList.add(4);
    speedList.add(5);
    speedList.add(6);
    speedList.add(8);
    speedList.add(8);
    return speedList;
  }

  private List<Integer> buildKnowledgeScale() {
    List<Integer> knowledgeList = new ArrayList<>();
    knowledgeList.add(1);
    knowledgeList.add(2);
    knowledgeList.add(3);
    knowledgeList.add(4);
    knowledgeList.add(4);
    knowledgeList.add(5);
    knowledgeList.add(5);
    knowledgeList.add(5);
    return knowledgeList;
  }

  private List<Integer> buildSanityScale() {
    List<Integer> sanityList = new ArrayList<>();
    sanityList.add(3);
    sanityList.add(4);
    sanityList.add(5);
    sanityList.add(5);
    sanityList.add(6);
    sanityList.add(6);
    sanityList.add(7);
    sanityList.add(8);
    return sanityList;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getDescription() {
    return "Yellow";
  }
}
