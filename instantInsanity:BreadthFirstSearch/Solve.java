public class Solve {
	private Cube cube1, cube2, cube3, cube4;
	private Cube [] cubes=new Cube [4];
	Solve (){
		cube1=new Cube (new Color []{Color.BLUE, Color.GREEN, Color.WHITE, Color.GREEN, Color.BLUE, Color.RED});
		cube2=new Cube (new Color []{Color.WHITE, Color.GREEN, Color.BLUE, Color.WHITE, Color.RED, Color.RED});
		cube3=new Cube (new Color []{Color.GREEN, Color.WHITE, Color.RED, Color.BLUE, Color.RED, Color.RED});
		cube4=new Cube (new Color []{Color.BLUE, Color.RED, Color.GREEN, Color.GREEN, Color.WHITE, Color.WHITE});
	}
	public static void main(String[] args){
		long start, stop;
		Solve solve=new Solve ();

		System.out.println("Generate And Test:");
		start = System.currentTimeMillis();

		solve.generateAndTest();

		stop = System.currentTimeMillis();
		System.out.println("Time Elapsed: " + (stop-start) + " milliseconds");

		System.out.print("\n\n");

		System.out.println("BreadthFirst:");
		start = System.currentTimeMillis();

		solve.breadthFirstSearch();

		stop = System.currentTimeMillis();
		System.out.println("Time Elapsed: " + (stop-start) + " milliseconds");
	}
	/**
	* Generates all 24^4 solutions to determine which are valid
	*@param Returns a queue containing all the solutions to the instant insanity puzzle
	*/
	public Queue<Solution> generateAndTest(){
		Solution.resetNumberOfCalls();
		resetAllCubes();
		int comboIndex = 0;
		Cube[] currentCombo = new Cube[4];
		LinkedQueue<Solution> validSolutions = new LinkedQueue<Solution>();
		int numberOfSolutions = 0;
		Cube[] cube1Combos = new Cube[24];
		Cube[] cube2Combos = new Cube[24];
		Cube[] cube3Combos = new Cube[24];
		Cube[] cube4Combos = new Cube[24];

		while(cube1.hasNext()){
			//going through cube combos
			cube1.next();;
			cube2.next();
			cube3.next();
			cube4.next();
			//Storing all 24 combinations inside an array for each cube
			cube1Combos[comboIndex] = cube1.copy();
			cube2Combos[comboIndex] = cube2.copy();
			cube3Combos[comboIndex] = cube3.copy();
			cube4Combos[comboIndex] = cube4.copy();
			comboIndex++;
		}
		for(int i = 0; i < 23; i++){
			for(int j = 0; j < 23; j++){
				for(int k = 0; k < 23; k++){
					for(int l = 0; l < 23; l++){
						currentCombo[0] = cube1Combos[i].copy();
						currentCombo[1] = cube2Combos[j].copy();
						currentCombo[2] = cube3Combos[k].copy();
						currentCombo[3] = cube4Combos[l].copy();
						Solution currentSolution = new Solution(currentCombo);
						if(currentSolution.isValid()){
							validSolutions.enqueue(currentSolution);
							numberOfSolutions++;
						}
					}
				}
			}
		}

		System.out.println("The Generate and Test method used the method isValid: " + Solution.getNumberOfCalls() + " times.");
		return validSolutions;

	}
	/**
	* Generates all solutions using the breadth first search algorithim to quickly determine all valid solutions
	*@return Returns a queue containing all solutions to the instant insanity puzzle
	*/
	public LinkedQueue<Solution> breadthFirstSearch(){
		Solution.resetNumberOfCalls();
		resetAllCubes();

		Cube[] cube2Combos = new Cube[24];
		Cube[] cube3Combos = new Cube[24];
		Cube[] cube4Combos = new Cube[24];
		LinkedQueue<Solution> open = new LinkedQueue<Solution>();
		LinkedQueue<Solution> solutions = new LinkedQueue<Solution>();
		int comboIndex = 0;

		while(cube1.hasNext()){
			cube1.next();
			cube2.next();
			cube3.next();
			cube4.next();
			open.enqueue(new Solution(new Cube[] {cube1.copy()}));
			cube2Combos[comboIndex] = cube2.copy();
			cube3Combos[comboIndex] = cube3.copy();
			cube4Combos[comboIndex] = cube4.copy();
			comboIndex++;
		}
		
		while(!open.isEmpty()){
			Solution current = open.dequeue();
			for(int i = 0; i < 24; i++){
				Solution tempSolution;
				if(current.size()== 1){
					tempSolution = new Solution(current, cube2Combos[i]);

				}else if(current.size() == 2){
					tempSolution = new Solution(current, cube3Combos[i]);
				}
				else{
					tempSolution = new Solution(current, cube4Combos[i]);
				}
				if(tempSolution.isValid()){
					if(tempSolution.size()== 4){
						solutions.enqueue(tempSolution);
					}
					else{
						open.enqueue(tempSolution);
					}
				}
			}
		}

		System.out.println("The breadthFirst method used the method isValid: " + Solution.getNumberOfCalls() + " times.");
		return solutions;
	}

	private void resetAllCubes(){
		cube1.reset();
		cube2.reset();
		cube3.reset();
		cube4.reset();
	}

}