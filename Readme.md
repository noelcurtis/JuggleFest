### Build

This project is built with Maven.

### Test

This project is tested with JUnit.

### Usage

<pre>
	// Do the assignments
	AssignmentController.getInstance().generateAssignments("input_file.txt");
	// Get the Circuits
	ArrayLisy<Circuit> circuits =  AssignmentController.getInstance().getCircuits();
	// Get the Assigned Jugglers for a circuit
	Collection<Juggler> jugglersAssignedToCircuit = circuit[0].getJugglerAssignments();
</pre>