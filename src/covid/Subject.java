package covid;

public class Subject {
	int age;
	String name;
	
	/**
	 * A standard constructor for the customer class
	 * 
	 * @param priority
	 * @param name
	 */
	
	public Subject(int age, String name){
		this.name = name;
		this.age = age;
	}

	/**
	 * Compares this subject to another subject.
	 * This subject is considered smaller than the other subject if and only if
	 * the age of this subject is smaller than the other customer or, if the ages are equal 
	 * then the name of this subject is larger in the lexicographic ordering than the name of the other subject.
	 * 
	 * If this subject is smaller returns a negative number. If this subject is bigger return a positive number.
	 * If the customers are equal return 0.
	 * 
	 * 
	 * @param other
	 * @return a negative/positive or zero number of this customer is smaller/greater or equal to other
	 */
	public int compareTo(Subject other) {
		// your code comes here
		if(this.age > other.age) return 1;
		else if (this.age < other.age) return -1;

		//Ages are equals (this.age = other.age)
		//Should compare lexicographic
		//The Java String compareTo() method is used for comparing two strings lexicographically.
		return this.name.compareTo(other.name);
	}
	
	/**
	 * Returns a string representation of this Subject.
	 * The string should be in the format: <name>, age: <age>.
	 * For example, 'Yoni, age: 32'
	 * 
	 * @return A string representation of this Subject.
	 */
	public String toString(){
		// your code comes here
		StringBuilder str = new StringBuilder();
		str.append(name).append(", age: ").append(age);
		return str.toString();
	}
}

