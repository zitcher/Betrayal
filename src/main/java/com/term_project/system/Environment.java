package com.term_project.system;

import java.util.HashMap;
import java.io.IOException;
import java.sql.SQLException;

/**
 * The Environment holds important variables and information in commandMemory.
 * The Environment also starts and runs the REPL.
 *
 * @author ZacharyHoffman
 */
public final class Environment {
  private HashMap<String, MemorySlot> commandMemory;
  private REPL repl;

  /**
   * Initializes Environment with the default information it needs in it
   * commandMemory to run.
   */
  public Environment() {
    commandMemory = new HashMap<>();
    /* EXAMPLE OF USE
    commandMemory.put(
        "autocorrect",
        new MemorySlot()
          .setTrie(null)
          .setBigram(null)
          .setBool("prefix", false)
          .setBool("whitespace", false)
          .setBool("smart", false)
          .setInt("led", 0)
    );
    */
    repl = null;
  }

  /**
   * Initializes the a REPL then runs it.
   * @throws IOException If repl's BufferedReader fails to run.
   */
  public void startRepl() throws IOException {
    HashMap<String, ReplUsable> commandMap = new HashMap<>();

    //add all the commands to the map
    /* EXAMPLE OF USE
    commandMap.put("stars", new StarsCommand(commandMemory));
    */

    //init the repl
    repl = new REPL(commandMap);

    //start the repl
    repl.runREPL();

    //close the databases here if any were added to commandMemory
  }

  /**
   * Passes a command to the repl that is currently being used.
   * Will print out "ERROR: REPL not yet instantiated" if startRepl() has yet
   * to be called as there is no REPL to pass the command to.
   * @param command The command in the repl to run.
   */
  public void runCommand(String command) {
    if (repl != null) {
      try {
        repl.runCommand(command);
      } catch (RuntimeException e) {
        System.out.println("ERROR: " + e.getMessage());
      }

    } else {
      System.out.println("ERROR: REPL not yet instantiated");
    }
  }

  /**
   * Gets the MemorySlot from memory associated with the given key.
   * @param key The key linked to the MemorySlot this function returns.
   * @return The MemorySlot associated with the given key.
   * @throws NullPointerException If key was never stored in memory.
   */
  public MemorySlot getFromMemory(String key) throws NullPointerException {
    MemorySlot found = commandMemory.get(key);

    if (found == null) {
      throw new NullPointerException("ERROR: " + key + " not found.");
    } else {
      return found;
    }
  }
}
