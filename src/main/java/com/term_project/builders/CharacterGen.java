package com.term_project.builders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.term_project.character.Brandon;
import com.term_project.character.Darrin;
import com.term_project.character.GameChar;
import com.term_project.character.Heather;
import com.term_project.character.Jenny;
import com.term_project.character.Longfellow;
import com.term_project.character.Missy;
import com.term_project.character.Ox;
import com.term_project.character.Peter;
import com.term_project.character.Rhinehardt;
import com.term_project.character.Vivian;
import com.term_project.character.Zoe;
import com.term_project.character.Zostra;

public class CharacterGen {

  private List<List<GameChar>> characters;
  private List<GameChar> charactersSimple;
  private Map<String, GameChar> charMap;

  public CharacterGen() {
    characters = new ArrayList<List<GameChar>>();
    charactersSimple = new ArrayList<>();
    charMap = this.buildCharMap();

  }

  private Map<String, GameChar> buildCharMap() {
    Map<String, GameChar> map = new HashMap<>();
    map.put("Brandon Jaspers", new Brandon());
    map.put("Darrin 'Flash' Williams", new Darrin());
    map.put("Heather Granville", new Heather());
    map.put("Jenny LeClerc", new Jenny());
    map.put("Professor Longfellow", new Longfellow());
    map.put("Missy Dubourde", new Missy());
    map.put("Ox Bellows", new Ox());
    map.put("Peter Akimoto", new Peter());
    map.put("Father Rhinehardt", new Rhinehardt());
    map.put("Vivian Lopez", new Vivian());
    map.put("Zoe Ingstrom", new Zoe());
    map.put("Madame Zostra", new Zostra());

    return map;
  }

  public List<List<GameChar>> build() {

    List<GameChar> red = new ArrayList<>();
    red.add(new Ox());
    red.add(new Darrin());

    List<GameChar> blue = new ArrayList<>();
    blue.add(new Zostra());
    blue.add(new Vivian());

    List<GameChar> green = new ArrayList<>();
    green.add(new Brandon());
    green.add(new Peter());

    List<GameChar> yellow = new ArrayList<>();
    yellow.add(new Missy());
    yellow.add(new Zoe());

    List<GameChar> white = new ArrayList<>();
    white.add(new Longfellow());
    white.add(new Rhinehardt());

    List<GameChar> purple = new ArrayList<>();
    purple.add(new Jenny());
    purple.add(new Heather());

    characters.add(red);
    characters.add(blue);
    characters.add(green);
    characters.add(yellow);
    characters.add(white);
    characters.add(purple);

    return characters;
  }

  public List<GameChar> buildSimple() {

    charactersSimple.add(new Ox());
    charactersSimple.add(new Zostra());
    charactersSimple.add(new Brandon());
    charactersSimple.add(new Missy());
    charactersSimple.add(new Longfellow());
    charactersSimple.add(new Jenny());

    return charactersSimple;
  }

  public GameChar getCharactersByName(String name) {
    return charMap.get(name);
  }

  public Map<String, GameChar> autoCharMap() {
    Map<String, GameChar> theMap = new HashMap<>();
    List<List<GameChar>> chars = build();

    for (int i = 0; i < chars.size(); i++) {
      theMap.put(Integer.toString(i), chars.get(i).get(i));
    }
    return theMap;
  }
}
