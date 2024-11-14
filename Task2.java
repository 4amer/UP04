public class BoolVector {
    private boolean[] vector;

    public BoolVector(int size) {
        vector = new boolean[size];
    }

    public BoolVector(boolean[] initialValues) {
        vector = new boolean[initialValues.length];
        System.arraycopy(initialValues, 0, vector, 0, initialValues.length);
    }

    public void set(int index, boolean value) {
        if (index >= 0 && index < vector.length) {
            vector[index] = value;
        } else {
            throw new IndexOutOfBoundsException("Index beyond vector.");
        }
    }

    public boolean get(int index) {
        if (index >= 0 && index < vector.length) {
            return vector[index];
        } else {
            throw new IndexOutOfBoundsException("Index beyond vector.");
        }
    }

    public BoolVector and(BoolVector other) {
        if (vector.length != other.vector.length) {
            throw new IllegalArgumentException("Vectors should be same size.");
        }

        BoolVector result = new BoolVector(vector.length);
        for (int i = 0; i < vector.length; i++) {
            result.set(i, this.vector[i] && other.vector[i]);
        }
        return result;
    }

    public BoolVector or(BoolVector other) {
        if (vector.length != other.vector.length) {
            throw new IllegalArgumentException("Vectors should be same size.");
        }

        BoolVector result = new BoolVector(vector.length);
        for (int i = 0; i < vector.length; i++) {
            result.set(i, this.vector[i] || other.vector[i]);
        }
        return result;
    }

    public BoolVector not() {
        BoolVector result = new BoolVector(vector.length);
        for (int i = 0; i < vector.length; i++) {
            result.set(i, !this.vector[i]);
        }
        return result;
    }

    public int countOnes() {
        int count = 0;
        for (boolean bit : vector) {
            if (bit) {
                count++;
            }
        }
        return count;
    }

    public int countZeros() {
        int count = 0;
        for (boolean bit : vector) {
            if (!bit) {
                count++;
            }
        }
        return count;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < vector.length; i++) {
            sb.append(vector[i] ? "1" : "0");
            if (i < vector.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        boolean[] values1 = {true, false, true, false};
        boolean[] values2 = {false, true, false, true};

        BoolVector vector1 = new BoolVector(values1);
        BoolVector vector2 = new BoolVector(values2);

        System.out.println("Vector 1: " + vector1);
        System.out.println("Vector 2: " + vector2);

        BoolVector andResult = vector1.and(vector2);
        System.out.println("AND Result: " + andResult);

        BoolVector orResult = vector1.or(vector2);
        System.out.println("OR Result: " + orResult);

        BoolVector notResult = vector1.not();
        System.out.println("NOT Result: " + notResult);

        System.out.println("Count of Ones in Vector 1: " + vector1.countOnes());
        System.out.println("Count of Zeros in Vector 1: " + vector1.countZeros());
    }
}
