package covid;

/**
 * A heap, implemented as an array.
 * The elements in the heap are instances of the class Subject,
 * and the heap is ordered according to the Subject ages and names.
 * 
 *
 */
public class Heap {
	
	/*
	 * The array in which the elements are kept according to the heap order.
	 * The following must always hold true:
	 * 			if i < size then heap[i].heapIndex == i
	 */
	Subject[] subjects; //The array in which the elements are kept according to the heap order.
	int size; // the number of elements in the heap, necessarily size <= heap.length
	
	/**
	 * Creates an empty heap with the given capacity (this is the initial size of the array, which may change later on)
	 */
	public Heap(int capacity){
		// your code comes here
		subjects = new Subject[++capacity]; //index 0 is empty (dummy)
		size = 0;
	}
	
	/**
	 * Constructs a heap from a given arbitrary array of Subjects.
	 * This should be done according to the "buildheap" function studied in class.
	 * You may NOT use the insert function of heap.
	 * 
	 * NOTE: for this function you may use a loop which runs over the array once.
	 * 
	 */
	public Heap(Subject[] arr) {
		// your code comes here

		//index 0 is empty (dummy)
		Subject[] copy = new Subject[arr.length+1];
		for(int i=0; i < arr.length; i++) {
			copy[i+1] = arr[i];
		}
		//copy[0] is empty - dummy index
		subjects = copy;
		size = arr.length;

		for (int i = size / 2; i > 0; i--) {
			percolateDown(i);
		}
	}

	//Move a node down the tree, as long as needed,
	//Until it reaches the correct level.
	private void percolateDown(int i) {
		int k = getMaximalChildIndex(i);

		while(k != -1 && subjects[i].compareTo(subjects[k]) < 0) {
			// As long as subjects[i] < subjects[k] should swap
			// Since it is a maximum heap
			swap(i, k);
			i = k;
			k = getMaximalChildIndex(i);
		}
	}

	private int getMaximalChildIndex(int i) {
		// A leaf
		if ( 2 * i > size) return -1; //Has no children

		// A single child
		if(2 * i == size) return size;

		// Two children (2 * i < size)
		int comparison = subjects[2 * i].compareTo(subjects[2 * i + 1]);
		if(comparison > 0) // subjects[2 * i] is bigger
			return 2 * i;
		else if (comparison < 0) // subjects[2 * i + 1] is bigger
			return (2 * i) + 1;
		return 2 * i; // when children are equals
	}

	private void swap(int i, int j) {
		Subject temp = subjects[i];
		subjects[i] = subjects[j];
		subjects[j] = temp;
	}
	
    /**
     * Returns the size of the heap.
     *
     * @return the size of the heap
     */
    public int size(){
		// your code comes here
		return size;
    }
    
    /**
     * Inserts a given element into the heap.
     * Should throw a RuntimeException if the array is full.
     * 
     * If at any point the array becomes full as a result of inserting too many subjects, 
     * then the size of the array should be doubled to handle extra subjects.
     * NOTE: you may use a loop to iterate through the entire array
     * for the purpose of resizing it and for this purpose only.
     * 
     * @param e - the element to be inserted.
     */
    public void insert(Subject e){
		// your code comes here
		//Should throw a RuntimeException if the array is full.
		if (isHeapFull()) throw new RuntimeException();

		subjects[++size] = e;
		percolateUp(size);

		//If at any point the array becomes full as a result of inserting too many subjects,
		//then the size of the array should be doubled to handle extra subjects.
		if(isHeapFull()) {
			Subject[] copyArray = new Subject[2 * subjects.length - 1]; //minus one since subjects[0] was a dummy index
			for(int i=1; i < subjects.length; i++) {
				copyArray[i] = subjects[i];
			}
			subjects = copyArray;
		}
    }

    private boolean isHeapFull() {
    	return size == subjects.length - 1; // since subjects[0] is a dummy
	}

	private void percolateUp(int i) {
		int parent = i / 2;

		while (parent > 0 && subjects[i].compareTo(subjects[parent]) > 0) {
			// As long as subjects[i] > parent should swap
			// Since it is a maximum heap
			swap(i, parent);
			i = parent;
			parent = i / 2;
		}
	}
	
	/**
	 * Returns and does not remove the subject next in line to receive the first dose of the vaccine.
	 * 
	 * @return the subject next in line to receive the first dose of the vaccine.
	 */
    public Subject findMax(){
		// your code comes here
		if ( size >= 1)
			return subjects[1];
		return null;
    }
    
	/**
	 * Returns and removes the the subject next in line to receive the first dose of the vaccine.
	 * Should return null if the heap is empty.
	 * 
	 * @return the subject next in line to receive the first dose of the vaccine.
	 */
    public Subject extractMax() {
		// your code comes here
		Subject max = findMax();
		subjects[1] = subjects[size]; // 1 and not 0 since x is dummy index
		size--;
		percolateDown(1);
		return max;
    }
    
    public static void main (String[] args){
    	/*
    	 * A basic test for the heap.
    	 * You should be able to run this before implementing the queue.
    	 * 
    	 * Expected outcome: 
    	 * 	Umberto, age: 41
		 *	Leto, age: 63
		 *	Leto, age: 63
		 *	Umberto, age: 41
		 *	Jon, age: 27
		 *	Corwin, age: 16
    	 * 
    	 */
    	Heap heap = new Heap(2);
    	Subject a = new Subject(41, "Umberto");
    	Subject b = new Subject(27, "Jon");
    	Subject c = new Subject(63, "Leto");
    	Subject d = new Subject(16, "Corwin");
    	
    	heap.insert(a);
    	System.out.println(heap.findMax());
    	
    	heap.insert(b);
    	heap.insert(c);
    	heap.insert(d);
    	System.out.println(heap.findMax());
     	System.out.println(heap.extractMax());
    	System.out.println(heap.extractMax());
        System.out.println(heap.extractMax());
    	System.out.println(heap.extractMax());
    }
}
