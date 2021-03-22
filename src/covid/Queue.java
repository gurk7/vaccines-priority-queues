package covid;

/**
 * A queue of Subjects, implemented as a cyclic array.
 * 
 * IMPORTANT: you may not use any loops/recursions in this class.
 */
public class Queue {

	Subject[] cyclicSubjects; // a cyclic array to implement the queue
	int size;
	int front;
	
	/**
	 * Constructs an empty queue with the given capacity (this is the initial size of the array, which may change later on).
	 */
	public Queue(int capacity){
		// your code comes here
		cyclicSubjects = new Subject[capacity];
		size = 0;
		front = 0;
	}
	
	/**
	 * Removes and returns the first subject in the queue
	 * If the queue if empty should return null.
	 * 
	 * @return the first subject in the queue
	 */
	public Subject dequeue(){
		// your code comes here
		if (size > 0) {
			Subject frontSubject = cyclicSubjects[front];
			front = (front + 1) % cyclicSubjects.length;
			size--;
			return frontSubject;
		}
		else {
			// If the queue if empty should return null.
			return null;
		}
	}
	
	/**
	 * Returns and does not remove the subject next in line to receive the second dose of the vaccine.
	 * 
	 * @return the subject next in line to receive the second dose of the vaccine.
	 */
	public Subject peek(){
		// your code comes here
		if (size > 0) return cyclicSubjects[front]; //queue is not empty
		return null;
	}
	
	/**
	 * Adds a new subject to the back of the queue
	 * 
	 * If at any point the queue becomes full as a result of inserting too many subjects, 
     * then the size of the array should be doubled to handle extra subjects.
     * NOTE: you may use a loop to iterate through the entire array
     * for the purpose of resizing it and for this purpose only.
     * 
	 * @param e - the subject
	 * @throws a runtime exception if the array is full.
	 */
	public void enqueue(Subject e){
		// your code comes here

		// EdgeCase - the Queue (array) is full.
		// The size of the array should be doubled to handle extra subjects.
		// Instead of throwing a RunTimeException
		if (IsQueueFull()) ResizeQueue();

		// Queue is not full
		cyclicSubjects[(front + size) % cyclicSubjects.length] = e;
		size++;

		// If at any point the queue becomes full as a result of inserting too many subjects,
		// Then the size of the array should be doubled to handle extra subjects.
		if (IsQueueFull()) ResizeQueue();

	}

	private boolean IsQueueFull() {
		return cyclicSubjects.length == size;
	}

	private void ResizeQueue() {
		// Can also be implemented with mem copy (Arrays.copy)
		Subject[] copySubjects = new Subject[cyclicSubjects.length * 2];

		// Can also be implements with mem copy
		for (int i=0; i < size; i++) {
			copySubjects[i] = cyclicSubjects[i + front % cyclicSubjects.length];
		}

		front = 0;
		cyclicSubjects = copySubjects;
	}
	
    
    /**
     * Removes a Subject with a given name from the queue.
     * If such a Subject does not exist returns false.
     * 
     * The order rest of the Subjects should have the same order after removal.
     * 
     * NOTE: you may use a loop to iterate through the entire array in this function.
     * 
     * @param name -  the name of the Subject who should be removed.
     * @return - true if and only if the subject was removed.
     */
	public boolean remove(String name){
		// your code comes here
		int subjectIndex = GetIndex(name);

		//If such a Subject does not exist returns false.
		if (subjectIndex == -1) return false;

		//The order rest of the Subjects should have the same order after removal.
		FixArrayHole(subjectIndex);
		size--;
		return true; // Subject was removed
	}

	private int GetIndex(String name) {
		for (int i = 0; i < size; i++) {
			if (cyclicSubjects[i + front % cyclicSubjects.length].name.equals(name))
				return i + front % cyclicSubjects.length;
		}
		return -1; //Name is not found in the queue
	}

	private void FixArrayHole(int holeIndex) {
		int rear = (front + size) %  cyclicSubjects.length;
		while(holeIndex != rear) {
			cyclicSubjects[holeIndex % cyclicSubjects.length] = cyclicSubjects[(holeIndex +1) % cyclicSubjects.length];
			holeIndex++;
		}
	}
	public static void main (String[] args){
    	/*
    	 * A basic test for the queue.
    	 * You should be able to run this before implementing the simulation.
    	 * 
    	 * Expected outcome: 
		 * Benedict, age: 24
		 * Benedict, age: 24
		 * Benedict, age: 24
		 * Corwin, age: 23
		 * Eric, age: 22
		 * Caine, age: 21
		 * Bleys, age: 20
		 * true
	     * Brand, age: 19
	     * Gerard, age: 17
		 * Random, age: 16
		 * null
    	 * 
    	 */
    	Queue q = new Queue(5);
    	Subject a = new Subject(24, "Benedict");
    	Subject b = new Subject(23, "Corwin");
    	Subject c = new Subject(22, "Eric");
    	Subject d = new Subject(21, "Caine");
    	Subject e = new Subject(20, "Bleys");
    	Subject f = new Subject(19, "Brand");
    	Subject g = new Subject(18, "Julian");
    	Subject h = new Subject(17, "Gerard");
    	Subject i = new Subject(16, "Random");
    	
    	
   
    	q.enqueue(a);
    	System.out.println(q.peek());
    	
    	q.enqueue(b);
    	q.enqueue(c);
    	q.enqueue(d);
    	System.out.println(q.peek());
    	q.enqueue(e);
    	q.enqueue(f);
     	System.out.println(q.dequeue());
    	System.out.println(q.dequeue());
        System.out.println(q.dequeue());
    	System.out.println(q.dequeue());
    	
    	q.enqueue(g);
    	q.enqueue(h);
    	q.enqueue(i);
    	
     	System.out.println(q.dequeue());
     	System.out.println(q.remove("Julian"));
    	System.out.println(q.dequeue());
        System.out.println(q.dequeue());
    	System.out.println(q.dequeue());
    	System.out.println(q.dequeue());
    }
}

