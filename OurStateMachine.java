package GAPL_project1;

import java.util.List;

import org.ggp.base.util.gdl.grammar.Gdl;
import org.ggp.base.util.statemachine.MachineState;
import org.ggp.base.util.statemachine.Move;
import org.ggp.base.util.statemachine.Role;
import org.ggp.base.util.statemachine.StateMachine;
import org.ggp.base.util.statemachine.exceptions.GoalDefinitionException;
import org.ggp.base.util.statemachine.exceptions.MoveDefinitionException;
import org.ggp.base.util.statemachine.exceptions.TransitionDefinitionException;

public class OurStateMachine extends StateMachine {

	private List<Gdl> gameDescr;

	@Override
	public void initialize(List<Gdl> description) {
		this.gameDescr = description;
		// substitution result = new substitution();
		System.out.print("Description: \n" + description + "\n");
	}

	@Override
	public int getGoal(MachineState state, Role role) throws GoalDefinitionException {
		// TODO Auto-generated method stub
		System.out.print("Role name: " + role.getName() + "\n");
		// Here we want to return prove([goal(xplayer, V)], rules, {})
		// and it should return us a set of substitutes
		//	=> [{V -> ...)}, {V -> ... }, ... }]
		return 0;
	}

	@Override
	public boolean isTerminal(MachineState state) {
		// TODO Auto-generated method stub
		// Here we want to return prove([terminal)], rules, {})
		// and it should return us a set of substitutes
		// Here we should return random substitutions but not empty []
		//	=> [{}, {M -> ... }, ... }]
		return false;
	}

	@Override
	public List<Role> getRoles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MachineState getInitialState() {
		// TODO Auto-generated method stub
		// return prover.findinitial();
		// Here we want to return prove([init(F)], rules, {})
		// and it should return us a set of substitutes
		//	=> [{F -> cell(1,1,b)}, {F -> cell(1,2,b), ... }]
		return null;
	}

	@Override
	public List<Move> getLegalMoves(MachineState state, Role role) throws MoveDefinitionException {
		// TODO Auto-generated method stub
		// Here we want to return prove([legal(xplayer, M)], rules, {})
		// and it should return us a set of substitutes
		//	=> [{M -> ...)}, {M -> ... }, ... }]
		return null;
	}

	@Override
	public MachineState getNextState(MachineState state, List<Move> moves) throws TransitionDefinitionException {
		// TODO Auto-generated method stub
		// Here we want to return prove([next( ... )], rules, {})
		// and it should return us a set of substitutes
		//	=> [{M -> ...)}, {M -> ... }, ... }]
		return null;
	}

}
