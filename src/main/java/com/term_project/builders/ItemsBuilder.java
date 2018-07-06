package com.term_project.builders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.term_project.items.Amulet;
import com.term_project.items.Armor;
import com.term_project.items.Axe;
import com.term_project.items.Bell;
import com.term_project.items.Bottle;
import com.term_project.items.Cat;
import com.term_project.items.HealingSalve;
import com.term_project.items.Item;
import com.term_project.items.MedicalKit;
import com.term_project.items.MysticCoin;
import com.term_project.items.Rock;
import com.term_project.items.SmellingSalts;

public class ItemsBuilder implements Builder<Item> {

  @Override
  public Queue<Item> build() {
    List<Item> itemList = new ArrayList<>();

    itemList.add(new MysticCoin());
    itemList.add(new Cat());
    itemList.add(new Rock());
    itemList.add(new Amulet());
    itemList.add(new Bottle());
    itemList.add(new Bell());
    itemList.add(new MedicalKit());
    itemList.add(new HealingSalve());
    itemList.add(new SmellingSalts());
    itemList.add(new Armor());
    itemList.add(new Axe());

    Collections.shuffle(itemList);
    Queue<Item> itemDeck = new LinkedList<>(itemList);

    return itemDeck;
  }
}
