//Scott Hogan
//Data Structures and Algorithm Analysis
//5.20 - Implement a generic Map class using the 
//structure provided in the book. The QuadraticProbingHashTable 
//is available on D2L, you should not modify this class.
//NOTE: Make sure to provide equals and hashCode methods 
//in the private class to get this working properly

public class GenericMap<KeyType, ValueType> {
	public GenericMap() {
		data = new QuadraticProbingHashTable<Entry<KeyType, ValueType>>();
	}
	
	private QuadraticProbingHashTable<Entry<KeyType, ValueType>> data;
	
	//Gets the value of the key that is inputed and returns its' value
	public ValueType get(KeyType key) {
		@SuppressWarnings("unchecked")
		ValueType v = (ValueType) new Object();
		Entry<KeyType, ValueType> e = new Entry<KeyType, ValueType>(key, v);
		e = data.find(e);
		return e.val;
	}
	
	//Used to insert keys and values into a generic Map
	public void put(KeyType key, ValueType val) {
		Entry<KeyType, ValueType> e = new Entry<KeyType, ValueType>(key, val);
		data.insert(e);
	}
	
	//Checks to see if the map is empty
	public boolean isEmpty() {
		return data.isEmpty();
	}

	private static class Entry<KeyType, ValueType> {
		Entry(KeyType k, ValueType v) {
			key = k;
			val = v;
		}

		public int hashCode() {
			return key.hashCode();
		}

		public boolean equals(Object rhs) {
			return rhs instanceof Entry && key.equals(((Entry<KeyType, ValueType>) rhs).key);
		}

		private KeyType key;
		private ValueType val;
	}

	//Main class used to insert keys and values into the generic map and print out their values
	public static void main(String[] args) {
		GenericMap<String, String> gMap = new GenericMap<String, String>();
		
		System.out.println("Inserting keys Scott, Austin, and Ellis into a generic map table");
		System.out.println();
		
		gMap.put("Scott", "CSCI Student 1");
		gMap.put("Austin", "CRJU Student 1");
		gMap.put("Ellis", "FILM Student 1");

		System.out.println("Getting value for key Scott: "+gMap.get("Scott"));
		System.out.println("Getting value for key Austin: " + gMap.get("Austin"));
		System.out.println("Getting value for key Ellis: " + gMap.get("Ellis"));
		System.out.println();
		
		System.out.println("Checking to see if the table is empty. Results: " + gMap.isEmpty());
		System.out.println();
		System.out.println("Inserting key Alex into the table...");
		System.out.println();
		gMap.put("Alex", "CSCI Student 2");
		System.out.println("Getting value for key Alex: " + gMap.get("Alex"));
	}
}
