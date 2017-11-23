//Scott Hogan
//Data Structures
//Refer to radixSortVariable method for process on sorting multiple length strings
//by using a maximum word length

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sorting {

	public static void main(String[] args) {
		
		String[] variableWords = new String[] {"hello", "darkness", "my", "old", "friend"};
		radixSortVariable(variableWords, 8);
		
	}

	public static void insertSort(Integer[] arr) {
		int hole = 0;
		int moveCount = 0;
		for (int position = 1; position < arr.length; position++) {
			Integer temp = arr[position];
			for (hole = position; hole > 0 && temp.compareTo(arr[hole - 1]) < 0; hole--) {
				arr[hole] = arr[hole - 1];// move number one space over
				moveCount++;
			}
			arr[hole] = temp;
		}
		System.out.println("Move Count:" + moveCount);
	}

	public static void shellSort(Integer[] arr) {
		// sort a shell at a time
		int hole;
		int moveCount = 0;
		// N/2 shells
		for (int sequence = arr.length / 2; sequence > 0; sequence /= 2) {
			System.out.println("Shell:" + sequence);
			for (int i = sequence; i < arr.length; i++)// loop for each sub-list
			{
				Integer temp = arr[i];
				for (hole = i; hole >= sequence && temp.compareTo(arr[hole - sequence]) < 0; hole -= sequence) {
					arr[hole] = arr[hole - sequence];
					moveCount++;
				}
				arr[hole] = temp;
			}
		}
		System.out.println("Shellsort Move Count:" + moveCount);
	}

	public static void mergeSort(Integer[] arr) {
		// call mergeSort(arr, temp[], 0, length-1)
		mergeSort(arr, new Integer[arr.length], 0, arr.length - 1);
	}

	public static void mergeSort(Integer[] arr, Integer[] temp, int left, int right) {
		// if left < right
		if (left < right) {
			// find center
			int center = (left + right) / 2;
			// call mergeSort on left half (left,center)
			mergeSort(arr, temp, left, center);
			// call mergeSort on right half (center+1,right)
			mergeSort(arr, temp, center + 1, right);
			// call merge over left/right halves
			merge(arr, temp, left, center + 1, right);
			// System.out.println(Arrays.toString(arr));
		}
	}

	public static void merge(Integer[] arr, Integer[] temp, int leftStart, int rightStart, int rightEnd) {
		// determine leftEnd
		int leftEnd = rightStart - 1;
		// set temp array position (same as left start)
		int tempPos = leftStart;
		// determine number of elements (end - start + 1)
		int count = rightEnd - leftStart + 1;
		// while items left in both lists
		while (leftStart <= leftEnd && rightStart <= rightEnd) {
			// put smaller into temp array, move pointers forward
			if (arr[leftStart] < arr[rightStart]) {
				temp[tempPos] = arr[leftStart];
				leftStart++;
				tempPos++;
			} else {
				temp[tempPos] = arr[rightStart];
				rightStart++;
				tempPos++;
			}
		}
		// while items left in either list
		while (leftStart <= leftEnd) {
			// add left over items to end of temp array
			temp[tempPos] = arr[leftStart];
			leftStart++;
			tempPos++;
		}
		while (rightStart <= rightEnd) {
			// add left over items to end of temp array
			temp[tempPos] = arr[rightStart];
			rightStart++;
			tempPos++;
		}
		// merge temp data to original using number of items and rightEnd
		for (int i = 0; i < count; i++) {
			arr[rightEnd] = temp[rightEnd];
			rightEnd--;
		}
	}

	public static void quickSort(Integer[] arr) {
		// convert array to list for processing
		List<Integer> temp = Arrays.asList(arr);
		quickSort(temp);
	}

	public static void quickSort(List<Integer> list) {
		// if list has more than 1 item
		if (list.size() > 1) {
			// create 3 lists (smaller, same, larger)
			List<Integer> smaller = new ArrayList<>();
			List<Integer> same = new ArrayList<>();
			List<Integer> larger = new ArrayList<>();

			// pick item for middle
			Integer middle = list.get(0);

			// loop through list putting items into correct containers
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) > middle) {
					larger.add(list.get(i));
				} else if (list.get(i) < middle) {
					smaller.add(list.get(i));
				} else
					same.add(list.get(i));
			}

			// recursively sort smaller/larger
			quickSort(larger);
			quickSort(smaller);

			// put all items into original list [.clear(), .addAll()]
			int pos = 0;
			for (int i = 0; i < smaller.size(); i++) {
				list.set(pos, smaller.get(i));
				pos++;
			}
			for (int i = 0; i < same.size(); i++) {
				list.set(pos, same.get(i));
				pos++;
			}
			for (int i = 0; i < larger.size(); i++) {
				list.set(pos, larger.get(i));
				pos++;
			}

			/*
			 * list.clear(); list.addAll(smaller); list.addAll(same); list.addAll(larger);
			 */
		}
	}

	public static void bucketSort(Integer[] arr, int min, int max) {
		// determine number of buckets from min/max
		int maxBuckets = max - min + 1;
		// create buckets for entire range
		int[] buckets = new int[maxBuckets];

		// process items into specific buckets (shift if needed)
		for (Integer a : arr)// N
		{
			buckets[a - min]++;
		}

		// put items back into original list in order
		int index = 0;
		for (int b = 0; b < buckets.length; b++)// M
		{
			for (int i = 0; i < buckets[b]; i++) {
				arr[index] = b + min;// shift back to correct value
				index++;
			}
		}
	}

	public static void radixSortVariable( String [ ] arr, int maxLen )
	{
		final int BUCKETS = 256;
		ArrayList<String> [ ] lengthWords = new ArrayList[ maxLen + 1 ];
		ArrayList<String> [ ] buckets = new ArrayList[ BUCKETS ];
	  
	for( int i = 0; i < lengthWords.length; i++ )
		lengthWords[ i ] = new ArrayList<>( );
	  
	for( int i = 0; i < BUCKETS; i++ )
		buckets[ i ] = new ArrayList<>( );
	  
	for( String s : arr )
		lengthWords[ s.length( ) ].add( s );

	int idx = 0;
	for( ArrayList<String> wordList : lengthWords )
		for( String s : wordList )
			arr[ idx++ ] = s;
	  
		int startingIndex = arr.length;
		for( int pos = maxLen - 1; pos >= 0; pos-- )
		{
			System.out.println("\nString Postion:" + pos);
			startingIndex -= lengthWords[ pos + 1 ].size( );
	  
			//Loop through string
			for( int i = startingIndex; i < arr.length; i++ )
				//Add to buckets
				buckets[ arr[ i ].charAt( pos ) ].add( arr[ i ] );
	
			idx = startingIndex;
			
			//Go through the buckets
			for( ArrayList<String> thisBucket : buckets )
			{
				if(thisBucket.size() > 0)
					System.out.println(thisBucket.toString());
				for( String s : thisBucket )
					arr[ idx++ ] = s;
	  
				//Clear buckets for next time
				thisBucket.clear( );
			}
		}
	}

	public static void radixSortStrings(String[] arr, int strLen) {
		// number of buckets = 256 (characters in the character set)
		int buckets = 256;
		// if you were doing a case insensitive sort, and you knew everything was single
		// words, you could use 26 as your size

		// Buckets need to be lists instead of counters
		ArrayList<String>[] bucket = new ArrayList[buckets];
		// create array of lists and initialize each object
		for (int i = 0; i < buckets; i++) {
			bucket[i] = new ArrayList<>();
		}

		// pointer for position in original list
		int index = 0;
		// loop from end of string to beginning
		for (int i = strLen - 1; i >= 0; i--) {
			System.out.println("\nString Postion:" + i);
			// loop through each string
			for (int j = 0; j < arr.length; j++) {
				// add to appropriate bucket
				bucket[(int) arr[j].charAt(i)].add(arr[j]);
			}
			// loop through buckets
			for (int j = 0; j < bucket.length; j++) {
				if (bucket[j].size() > 0)
					System.out.println(j + ":" + bucket[j].toString());
				// add each string back to original array in new order
				for (String s : bucket[j]) {
					arr[index] = s;
					index++;
				}
				// clear the bucket
				bucket[j].clear();
			}
			index = 0;
		}
	}

}
