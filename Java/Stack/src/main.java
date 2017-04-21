
public class main {

	public static void main(String[] args) {
		StackImplementation stack = new StackImplementation();
		stack.push(1);
		stack.push(3);
		stack.push(2);
		stack.push(7);
		stack.push(2);
		stack.push(4);
		stack.push(8);
		stack.push(9);
		int olddata = 100;
		while(olddata > 0){
			olddata = stack.pop();
			System.out.println(olddata);
		}

	}

}
