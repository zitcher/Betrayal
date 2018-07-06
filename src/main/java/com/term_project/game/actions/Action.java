package com.term_project.game.actions;

import com.term_project.character.GameChar;
import com.term_project.system.MemorySlot;
import java.util.Map;

public interface Action {

	/**
	 * Describes name of the action.
	 * @return Action's name.
	 */
	public String getName();

	/**
	 * Describes action.
	 * @return Action's description.
	 */
	public String getDescription();

	/**
	 * Executes this action's effects.
	 */
	public void execute(MemorySlot gameMemory,
											GameChar character,
											String specs,
											Map<String, Integer> remaining);
}
