class Node {
    private Object data;
    private Node next;

    Node() {
        this.data = null;
        this.next = null;
    }

    Node(Object data) {
        this.data = data;
        this.next = null;

    }

    Node(Object data, Node next) {
        this.data = data;
        this.next = next;

    }

    private boolean hasNext() {
    	try {
			return next != null;
		}catch (NullPointerException e){
    		return false;
		}
    }

    Object get() {
        return data;
    }

    void set(Object data) {
        this.data = data;
    }

    Node getNext() {

    	if(hasNext()){
			return next;
		}
    	return new Node(new Gem(GemType.VOID, 0));
    }

    void setNext(Node next) {
        this.next = next;
    }

}

@SuppressWarnings("WeakerAccess")
public class GemList {

    private Node head;
    private int size;
    private int score;

    public GemList() {
        head = null;
        size = 0;
        score = 0;
    }

    public void draw(double y) {
        Node current = head;
        double j = 0.0;
        for (int i = 0; i < size; i++) {
            ((Gem) current.get()).draw(j, y);
            current = current.getNext();
            j += 0.07;
        }
    }

    public void insertBefore(Gem gem, int index) {

        if (head == null) {
            head = new Node(gem);
            size++;
        }
        else if(index == 0){
			Node tempHead = this.head;
			this.head = new Node(gem, tempHead);
			size++;
		}
        else {
            Node current = head;
            for (int i = 0; i < size - 1; i++) {
                if (i == index - 1) {
                    Node newGem = new Node(gem, current.getNext());
                    current.setNext(newGem);
                    size++;
                    return;
                }
                current = current.getNext();
            }
            current.setNext(new Node(gem));
            size++;
        }
    }

    public int size() {
        return size;
    }

    public int score() {
        int score = 0;
		int multi = 1;
		int tempScore = 0;
        Node current = head;
		for (int i = 0; i < size; i++) {

			if(((Gem) current.getNext().get()).getType().equals(((Gem) current.get()).getType())){
				multi++;
				tempScore += ((Gem) current.get()).getPoints();
			}
			else {
				if(multi > 1){
					tempScore += ((Gem) current.get()).getPoints();
					score += tempScore*multi;
					tempScore = 0;
				}
				else {
					score += ((Gem) current.get()).getPoints();
				}
				multi = 1;
			}

			current = current.getNext();
		}

        this.score = score;
        return score;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append("{");
        Node current = head;
        for (int i = 0; i < size; i++) {
            out.append(current.get());
            out.append(", ");
            current = current.getNext();
        }
        out.append("}");
        return out.toString();
    }

    public static void main(String[] args) {
        GemList list = new GemList();
        System.out.println(list);
        System.out.println("size = " + list.size() + ", score = " + list.score());

        list.draw(0.9);

        list.insertBefore(new Gem(GemType.BLUE, 10), 0);
        System.out.println("\n" + list);
        System.out.println("size = " + list.size() + ", score = " + list.score());
        list.draw(0.8);

        list.insertBefore(new Gem(GemType.BLUE, 20), 99);  //not a mistake, should still work
        System.out.println("\n" + list);
        System.out.println("size = " + list.size() + ", score = " + list.score());
        list.draw(0.7);

        list.insertBefore(new Gem(GemType.ORANGE, 30), 1);
        System.out.println("\n" + list);
        System.out.println("size = " + list.size() + ", score = " + list.score());
        list.draw(0.6);

        list.insertBefore(new Gem(GemType.ORANGE, 10), 2);
        System.out.println("\n" + list);
        System.out.println("size = " + list.size() + ", score = " + list.score());
        list.draw(0.5);

        list.insertBefore(new Gem(GemType.ORANGE, 50), 3);
        System.out.println("\n" + list);
        System.out.println("size = " + list.size() + ", score = " + list.score());
        list.draw(0.4);

        list.insertBefore(new Gem(GemType.GREEN, 50), 2);
        System.out.println("\n" + list);
        System.out.println("size = " + list.size() + ", score = " + list.score());
        list.draw(0.3);

    }


}
