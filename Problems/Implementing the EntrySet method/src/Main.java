import java.util.*;

public class Main {
    private static class TableEntry<T> {
        private final int key;
        private final T value;

        public TableEntry(int key, T value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public T getValue() {
            return value;
        }
    }

    private static class HashTable<T> {
        private final int size;
        private final TableEntry<ArrayList<T>>[] table;

        public HashTable(int size) {
            this.size = size;
            table = new TableEntry[size];
        }

        public boolean put(int key, T value) {
            // put your code here
            int index = findKey(key);

            if (index == -1) {
                return false;
            }

            ArrayList<T> now = get(key);
            if (now == null) {
                ArrayList<T> newList = new ArrayList<T>();
                newList.add(value);
                table[index] = new TableEntry<>(key, newList);
            } else {
                now.add(value);
            }
            return true;
        }

        public ArrayList<T> get(int key) {
            // put your code here
            int index = findKey(key);

            if (index == -1 || table[index] == null) {
                return null;
            }

            return table[index].getValue();
        }

        public Set<Map.Entry<Integer, ArrayList<T>>> entrySet() {
            // put your code here
            HashMap<Integer, ArrayList<T>> map = new HashMap<>();
            for (TableEntry<ArrayList<T>> entry : table) {
                if (entry != null) {
                    map.put(entry.getKey(), entry.getValue());
                }
            }

            return map.entrySet();
        }

        private int findKey(int key) {
            // put your code here
            int hash = key % size;

            while (!(table[hash] == null || table[hash].getKey() == key)) {
                hash = (hash + 1) % size;

                if (hash == key % size) {
                    return -1;
                }
            }

            return hash;
        }

        private void rehash() {
            // put your code here
        }
    }

    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        int count = scanner.nextInt();

        HashTable<String> table = new HashTable<>(count);

        for (int i = 0; i < count; i++) {
            table.put(scanner.nextInt(), scanner.next());
        }

        for (Map.Entry<Integer, ArrayList<String>> list : table.entrySet()) {
            String result = list.getKey() + ": " + list.getValue();
            System.out.println(result.replace("[", "")
                    .replace("]", "")
                    .replace(",", ""));
        }
    }
}