package com.term_project.builders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.term_project.events.AngryBeing;
import com.term_project.events.BloodyVision;
import com.term_project.events.BurningMan;
import com.term_project.events.CreepyCrawlies;
import com.term_project.events.Debris;
import com.term_project.events.Event;
import com.term_project.events.Funeral;
import com.term_project.events.HideousShriek;
import com.term_project.events.Mist;
import com.term_project.events.NightView;
import com.term_project.events.Rotten;
import com.term_project.events.ShriekingWind;
import com.term_project.events.Silence;
import com.term_project.events.SomethingSlimey;
import com.term_project.events.Spider;
import com.term_project.game.Stats;

public class EventsBuilder implements Builder<Event> {

  @Override
  public Queue<Event> build() {
    List<Event> eventList = new ArrayList<>();

    String name = "Spider";
    String description = "A spider the size of a fist lands "
        + "on your shoulder and crawls into your hair.";
    String function = "You must attempt a Speed roll to brush "
        + "it away or a Sanity roll to stand still:<br />4+ It's "
        + "gone. Gain 1 Speed.<br />1-3 It bites you. Take 1 "
        + "Speed damage.<br />0 It takes a chunk out of you. Take 2 Speed damage.";
    List<Stats> s1 = new ArrayList<>();
    s1.add(Stats.SPEED);
    s1.add(Stats.SANITY);
    eventList.add(new Spider(name, description, function, s1));

    name = "Burning Man";
    description = "A man on fire runs through the room. His skin bubbles and cracks, "
        + "falling away from him and leaving a fiery skull that clatters to the "
        + "ground, bounces, rolls, and disappears.";
    function = "You must attempt a Sanity roll:<br />4+ You feel a little "
        + "hot under the collar, but otherwise fine. Gain 1 Sanity.<br />2-3The "
        + "fire burns, but you can stand the heat.<br />0-1 You burst into flames! "
        + "Take 2 Sanity damage.";
    List<Stats> s2 = new ArrayList<>();
    s2.add(Stats.SANITY);
    eventList.add(new BurningMan(name, description, function, s2));

    name = "Creepy Crawlies";
    description = "A thousand bugs spill out on your skin, under your clothes, and in your hair.";
    function = "You must attempt a Sanity roll:<br />5+ You blink and they're gone. Gain 1 Sanity."
        + "<br />1-4 Lose 1 Sanity.<br />0 Lose 2 Sanity.";
    List<Stats> s3 = new ArrayList<>();
    s3.add(Stats.SANITY);
    eventList.add(new CreepyCrawlies(name, description, function, s3));

    name = "Funeral";
    description = "You see an open coffin. You're inside it.";
    function = "You must attempt a Sanity roll:<br />4+ You blink and its gone. "
        + "Gain 1 Sanity.<br />2-3 The vision disturbs you. Lose 1 Sanity.<br />0-1 "
        + "You're really in that coffin. Lose 1 Sanity and 1 Might.";
    List<Stats> s4 = new ArrayList<>();
    s4.add(Stats.SANITY);
    eventList.add(new Funeral(name, description, function, s4));

    name = "ShriekingWind";
    description = "The wind picks up, a slow crescendo to a screeching howl.";
    function = "You must attempt a Might roll:<br />5+ You keep your footing.<br />3-4 "
        + "The wind knocks you down. Take 1 Might damage<br />0-2 The wind chills "
        + "your soul. Take 1 Sanity damage.";
    List<Stats> s5 = new ArrayList<>();
    s5.add(Stats.MIGHT);
    eventList.add(new ShriekingWind(name, description, function, s5));

    name = "Silence";
    description = "Underground, everything goes silent. Even the sound of breathing is gone.";
    function = "You must attempt a Sanity roll:<br />4+ You wait calmly for your hearing to "
        + "return.<br />1-3 You scream a silent scream. Take 1 Knowledge damage.<br />0 You freak out. "
        + "Take 2 Knowledge damage.";
    List<Stats> s6 = new ArrayList<>();
    s6.add(Stats.SANITY);
    eventList.add(new Silence(name, description, function, s6));

    name = "Something Slimey";
    description = "What's around your ankle? A bug? A tentacle? A dead hand clawing?";
    function = "You must attempt a Speed roll:<br />4+ You break free. Gain 1 Speed"
        + ".<br />1-3 Lose 1 Might.<br />0 Lose 1 Might and 1 Speed.";
    List<Stats> s7 = new ArrayList<>();
    s7.add(Stats.SPEED);
    eventList.add(new SomethingSlimey(name, description, function, s7));
    //
    // name = "Phone Call";
    // description = "A phone rings in the room. You feel compelled to answer
    // it.";
    // function = "Roll 2 dice. A sweet little granny voice says:<br />4 'Tea
    // and cakes"
    // + "!' Gain 1 Sanity.<br />3 'I'm always here for you. Watching...' Gain "
    // + "1 Knowledge.<br />1-2 I'm here! Give us a kiss! Take 1 Sanity
    // damage.<br />"
    // + "0 'Bad little children must be punished!' Take 1 Might and 1 Speed
    // damage.";
    // List<Stats> s8 = new ArrayList<>();
    // eventList.add(new PhoneCall(name, description, function, s8));

    name = "Debris";
    description = "Plaster falls from the walls and ceiling";
    function = "You must attempt a Speed roll:<br />3+ You dodge "
        + "the plaster. Gain 1 Speed.<br />1-2 You're buried in "
        + "debris. Take 1 Might Damage.<br />0 You're buried in "
        + "debris. Take 2 Might Damage.";
    List<Stats> s9 = new ArrayList<>();
    s9.add(Stats.SPEED);
    eventList.add(new Debris(name, description, function, s9));

    name = "Bloody Vision";
    description = "The walls of this room are damp with blood. The blood drips "
        + "from the ceiling, down the walls, over the furniture, and onto your "
        + "shoes. In a blink, it is gone.";
    function = "You must attempt a Sanity roll:<br />4+ You steel yourself. Gain 1 "
        + "Sanity.<br />2-3 Lose 1 Sanity.<br />0-1 Lose 2 Sanity.";
    List<Stats> s10 = new ArrayList<>();
    s10.add(Stats.SANITY);
    eventList.add(new BloodyVision(name, description, function, s10));

    name = "Angry Being";
    description = "It emerges from the slime on the wall next to you.";
    function = "You must attempt a Speed roll:<br />5+ You get away. Gain "
        + "1 Speed.<br />2-4 Take 1 Sanity damage.<br />0-1 Take 1 Sanity "
        + "damage and 1 Speed damage.";
    List<Stats> s11 = new ArrayList<>();
    s11.add(Stats.SPEED);
    eventList.add(new AngryBeing(name, description, function, s11));

    name = "Night View";
    description = "You see a vision of a ghostly couple walking the "
        + "grounds silently strolling in their wedding best.";
    function = "You must attempt a Knowledge roll:<br />5+ You recognize the "
        + "ghosts as former inhabitants of the house. "
        + "You call their names, and they turn to you, whispering dark "
        + "secrets of the house. Gain 1 Knowledge.<br />0-4 You pull back "
        + "in horror, unable to watch";
    List<Stats> s12 = new ArrayList<>();
    s12.add(Stats.KNOWLEGE);
    eventList.add(new NightView(name, description, function, s12));

    name = "Rotten";
    description = "The smell in this room, it's horrible. "
        + "Smells like death, like blood. A slaughterhouse smell.";
    function = "You must attempt a Sanity roll:<br />5+ Troubling odors, "
        + "nothing more. Gain 1 Sanity.<br />2-4 Lose 1 Might.<br />1 Lose "
        + "1 Might and 1 Speed.<br />0 You double over with nausea. "
        + "Lose 1 Might, 1 Speed, 1 Sanity, and 1 Knowledge.";
    List<Stats> s13 = new ArrayList<>();
    s13.add(Stats.SANITY);
    eventList.add(new Rotten(name, description, function, s13));

    name = "Hideous Shriek";
    description = "It starts like a whisper, but ends in a soul-rending shriek.";
    function = "You must attempt a Sanity roll:<br />4+ You resist the sound.<br />1-3"
        + " Take 1 Sanity damage.<br />0 Take 2 Sanity damage.";
    List<Stats> s14 = new ArrayList<>();
    s14.add(Stats.SANITY);
    eventList.add(new HideousShriek(name, description, function, s14));

    // name = "Footsteps";
    // description = "The footsteps slowly creak. Dust rises. Footprints appear
    // "
    // + "on the dirty floor. And then, as they reach you, they are gone.";
    // function = "Roll 2 dice.<br />3-4 Gain 1 Might.<br />2 Lose 1 Sanity.<br
    // />1 Lose 1 Speed.<br />0 Lose 1 Might.";
    // stats = new ArrayList<>();
    // eventList.add(new Footsteps(name, description, function, stats));

    name = "Mist From the Walls";
    description = "Mists pour out from the walls. There are faces in the mist, human and ... inhuman. ";
    function = "You must attempt a Sanity roll:<br />4+ The faces are tricks of light and shadow. "
        + "All is well.<br />1-3 Take 1 Sanity damage.<br />0 Take 2 Sanity damage.";
    List<Stats> s15 = new ArrayList<>();
    s15.add(Stats.SANITY);
    eventList.add(new Mist(name, description, function, s15));

    Collections.shuffle(eventList);
    Queue<Event> eventDeck = new LinkedList<>(eventList);
    return eventDeck;
  }
}
