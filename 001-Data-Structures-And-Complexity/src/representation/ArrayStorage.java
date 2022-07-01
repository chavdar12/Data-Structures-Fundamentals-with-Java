package representation;

public class ArrayStorage {
    private final int INITIAL_CAPACITY = 4;

    private int[] elements;
    private int index;

    public ArrayStorage() {
        this.elements = new int[INITIAL_CAPACITY];
        this.index = 0;
    }

    public boolean add(int element) {
        add(element, ++index);
        return true;
    }

    private void add(int element, int index) {
        if (index == this.elements.length) {
            grow();
        }
        this.elements[index] = element;
    }

    // TODO: Implement additional operations like: remove(int element), contains(int element) and more

    public void shrink() {
        int[] temp;

        if (elements.length > 0) {

            temp = new int[elements.length];

            System.arraycopy(elements, 0, temp, 0, elements.length);

            elements = temp;
        }
    }

    public void grow() {
        int[] temp = new int[elements.length * 2];

        System.arraycopy(elements, 0, temp, 0, elements.length);
    }

    public void remove(int index) {

        if (elements.length > 0) {

            if (elements.length - 1 - index >= 0) {

                System.arraycopy(elements, index + 1, elements, index, elements.length - 1 - index);
            }

            elements[elements.length - 1] = 0;
        }
    }
}
