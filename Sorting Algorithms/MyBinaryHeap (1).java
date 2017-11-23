//Scott Hogan
//Data Structures

public class MyBinaryHeap<E extends Comparable<? super E>> {
	private static final int DEFAULT_CAPACITY = 4;
	private int currentSize;
	private E[] heap;
	private int operationCount = 0;//example done for constructor that adds all, need to add to all other methods
	public int opCount()
	{
		return operationCount;
	}
	public String toString()
	{
		String output = "Current Size:"+currentSize+"\n";
		for(int i = 1; i <= currentSize; i++)//heap.length
		{
			
			output += i+":";
			if(heap[i]!=null)
				output += heap[i];
			output += "\n";
		}
		return output;
	}
	public MyBinaryHeap()
	{
		this(DEFAULT_CAPACITY);
	}
	public MyBinaryHeap(int size)
	{
		currentSize = 0;
		heap = (E[]) new Comparable[ nextSize(size) ];
	}
	public MyBinaryHeap(E[] items)
	{
		currentSize = items.length;
		operationCount++;
		//create heap with enough space
		heap = (E[]) new Comparable[ nextSize(items.length) ];
		operationCount++;
		//put all items into structure out of order
		operationCount++;//assign i value
		operationCount++;//first comparison
		for(int i = 0; i < items.length; i++)
		{
			heap[i+1] = items[i];
			operationCount += 2;//addition for array index and assignment

			operationCount++;//comparison
			operationCount++;//increment i
		}
		//fix heap structure
		buildHeap();
	}
	public void addAll(E[] items)
	{
		//make room for new items
		if(currentSize+items.length >= heap.length)
            growArray(nextSize(currentSize+items.length));
		//put all items into structure out of order
		for(int i = 0; i < items.length; i++)
			heap[currentSize+i+1] = items[i];
		currentSize += items.length;
		//fix heap structure
		buildHeap();
	}
	public void buildHeap()
	{
		//start with lowest parent
		for(int i = currentSize / 2; i > 0; i--)
		{
			percolateDown(i);
		}
	}
	public boolean isEmpty()
	{
		return (currentSize == 0);
	}
	public void makeEmpty()
	{
		currentSize = 0;
	}
	private void growArray(int newSize)
	{
		E[] old = heap;
		heap = (E []) new Comparable[ newSize ];
        for( int i = 1; i <= currentSize; i++ )
        	heap[ i ] = old[ i ];
	}
	private void growArray()
	{
		//multiply current length by 2 to allow full level to be added
		this.growArray(heap.length<<1);
	}
	private int nextSize(int size)
	{
		//finds next size that allows full level to be added
		return 1 << Integer.toBinaryString(size).length();
	}
	public void insert(E item)
	{
		//array is currently full, add next depth
		if( currentSize == heap.length - 1 )
			growArray(heap.length * 2);

		currentSize++;
		int hole = currentSize;
		heap[0] = item;//store item in temporary location
		percolateUp(hole);

	}
	private void percolateUp(int pos)
	{
		E item = heap[0];
		//check if item is smaller than parent
		//pos/2 = parent, 11 and 10 divided by 2 = 5
		for(; item.compareTo(heap[pos/2]) < 0; pos = pos/2)
			heap[pos] = heap[pos/2];
		//put item in final position
		heap[pos] = item;
	}
	public E deleteMin()
	{
		if(currentSize == 0)
			return null;
		//smallest item
		E temp = heap[1];

		//move last item to the root
		heap[1] = heap[currentSize];
		currentSize--;

		//shift last item down to where it belongs
		percolateDown(1);

		//return smallest item
		return temp;
	}
	private void percolateDown(int pos)
	{
		int child;
		E temp = heap[pos];
		//check if there are children
		for(; pos*2 <= currentSize; pos = child)
		{
			child = pos*2;
			//is there 2 children
			//if there are check if second child is smaller
			if(child != currentSize &&
					heap[child+1].compareTo(heap[child]) < 0)
				child++;
			//smaller child compare to parent
			if(heap[child].compareTo(temp) < 0)
				heap[pos] = heap[child];
			else
				break;
		}
		heap[pos] = temp;
	}
	public E findMin()
	{
		if(currentSize == 0)
			return null;
		return heap[1];
	}
}