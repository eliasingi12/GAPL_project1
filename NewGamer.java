package GAPL_project1;

import java.util.List;

import org.ggp.base.player.gamer.statemachine.sample.SampleGamer;
import org.ggp.base.util.statemachine.Move;
import org.ggp.base.util.statemachine.StateMachine;
import org.ggp.base.util.statemachine.cache.CachedStateMachine;
import org.ggp.base.util.statemachine.exceptions.GoalDefinitionException;
import org.ggp.base.util.statemachine.exceptions.MoveDefinitionException;
import org.ggp.base.util.statemachine.exceptions.TransitionDefinitionException;

public class NewGamer extends SampleGamer {

	@Override
	public Move stateMachineSelectMove(long timeout)
			throws TransitionDefinitionException, MoveDefinitionException, GoalDefinitionException {

		StateMachine theMachine = getStateMachine();

		List<Move> moves = theMachine.getLegalMoves(getCurrentState(), getRole());
		Move move = moves.get(0);

		return move;
	}

	@Override
	public StateMachine getInitialStateMachine() {
		System.out.print("Test\n");
		return new CachedStateMachine(new OurStateMachine());
	}

}
