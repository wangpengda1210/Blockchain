import java.util.Scanner;

public class Main {
    private static class TableEntry<T> {
        private final int key;
        private final T value;
        private boolean removed;

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

        public void remove() {
            removed = true;
        }

        public boolean isRemoved() {
            return removed;
        }
    }

    private static class HashTable<T> {
        private int size;
        private TableEntry[] table;

        public HashTable(int size) {
            this.size = size;
            table = new TableEntry[size];
        }

        public boolean put(int key, T value) {
            // put your code here
            int idx = findPut(key);

            if (idx == -1) {
                rehash();
                idx = findKey(key);
            }

            table[idx] = new TableEntry(key, value);
            return true;
        }

        public T get(int key) {
            int idx = findKey(key);

            if (idx == -1 || table[idx] == null || table[idx].isRemoved()) {
                return null;
            }

            return (T) table[idx].getValue();
        }

        public void remove(int key) {
            // put your code here
            int idx = findKey(key);

            if (idx != -1 && table[idx] != null && table[idx].getKey() == key) {
                table[idx].remove();
            }
        }

        private int findKey(int key) {
            int hash = key % size;

            while (!(table[hash] == null || table[hash].getKey() == key)) {
                hash = (hash + 1) % size;

                if (hash == key % size) {
                    return -1;
                }
            }

            return hash;
        }

        private int findPut(int key) {
            int hash = key % size;

            while (!(table[hash] == null || table[hash].getKey() == key
                    || table[hash].isRemoved())) {
                hash = (hash + 1) % size;

                if (hash == key % size) {
                    return -1;
                }
            }

            return hash;
        }

        private void rehash() {
            // put your code here
            TableEntry<T>[] oldTable = table.clone();

            size = size * 2;
            table = new TableEntry[size];

            for (TableEntry<T> entry : oldTable) {
                put(entry.getKey(), entry.getValue());
            }
        }

        @Override
        public String toString() {
            StringBuilder tableStringBuilder = new StringBuilder();

            for (int i = 0; i < table.length; i++) {
                if (table[i] == null) {
                    tableStringBuilder.append(i + ": null");
                } else {
                    tableStringBuilder.append(i + ": key=" + table[i].getKey()
                            + ", value=" + table[i].getValue()
                            + ", removed=" + table[i].isRemoved());
                }

                if (i < table.length - 1) {
                    tableStringBuilder.append("\n");
                }
            }

            return tableStringBuilder.toString();
        }
    }

    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        int count = scanner.nextInt();

        HashTable<String> table = new HashTable<>(5);
        for (int i = 0; i < count; i++) {
            switch (scanner.next()) {
                case "put":
                    table.put(scanner.nextInt(), scanner.next());
                    break;
                case "get":
                    String number = table.get(scanner.nextInt());
                    if (number == null) {
                        System.out.println(-1);
                    } else {
                        System.out.println(number);
                    }
                    break;
                case "remove":
                    table.remove(scanner.nextInt());
            }
        }
    }
}