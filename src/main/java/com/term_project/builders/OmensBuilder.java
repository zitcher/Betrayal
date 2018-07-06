package com.term_project.builders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.term_project.omens.Book;
import com.term_project.omens.CrystalBall;
import com.term_project.omens.Dog;
import com.term_project.omens.Girl;
import com.term_project.omens.HolySymbol;
import com.term_project.omens.Madman;
import com.term_project.omens.Mask;
import com.term_project.omens.Medallion;
import com.term_project.omens.Omen;
import com.term_project.omens.Skull;
import com.term_project.omens.Spear;

public class OmensBuilder implements Builder<Omen> {

  @Override
  public Queue<Omen> build() {
    List<Omen> omenList = new ArrayList<>();

    omenList.add(new Book());
    omenList.add(new CrystalBall());
    omenList.add(new Dog());
    omenList.add(new Girl());
    omenList.add(new HolySymbol());
    omenList.add(new Madman());
    omenList.add(new Mask());
    omenList.add(new Medallion());
    omenList.add(new Skull());
    omenList.add(new Spear());

    Collections.shuffle(omenList);
    Queue<Omen> omenDeck = new LinkedList<>(omenList);
    return omenDeck;
  }
}
