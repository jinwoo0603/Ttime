package frontend;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JList;
import java.util.Arrays;
import javax.swing.DefaultListModel;

public class ClassListListener extends KeyAdapter {
	class DoublyCircularLinkedList {
	    private Node head;
	    private Node tail;

	    class Node {
	        public String data;
	        public Node next;
	        public Node prev;

	        Node(String data) {
	            this.data = data;
	        }
	    }

	    public void add(String data) {
	        Node newNode = new Node(data);

	        if (head == null) {
	            head = newNode;
	            tail = newNode;
	            tail.next = head;
	            head.prev = tail;
	        } else {
	            tail.next = newNode;
	            newNode.prev = tail;
	            tail = newNode;
	            tail.next = head;
	            head.prev = tail;
	        }
	    }

	    public Node search(String value) {
	        if (head == null) {
	            return null; // 빈 리스트일 경우 null 반환
	        }

	        Node current = head;
	        do {
	            if (current.data.equals(value)) {
	                return current; // 값을 찾으면 해당 노드 반환
	            }
	            current = current.next;
	        } while (current != head);

	        return null; // 값을 찾지 못한 경우 null 반환
	    }
	}
	public void keyTyped(KeyEvent e) {
		JList<String> jlist = (JList<String>)e.getSource();
		String selectedItem = jlist.getSelectedValue();
		int selectedIndex = jlist.getSelectedIndex();
		
		DoublyCircularLinkedList score_ = new DoublyCircularLinkedList();
		score_.add("A+");
		score_.add("A0");
		score_.add("B+");
		score_.add("B0");
		score_.add("C+");
		score_.add("C0");
		score_.add("D+");
		score_.add("D0");
		score_.add("F");
		score_.add("P");
		score_.add("NP");
		
		String[] sub = selectedItem.split(" ", 2);
		String selected_score = sub[0];
		String rest = " " + sub[1];
		DefaultListModel<String> classListModel = (DefaultListModel<String>) GUI.GUIData.class_list.getModel();
		
        if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
        	classListModel.remove(selectedIndex);
        	GUI.GUIData.set_calendar(selectedItem, 4, false);
        	GUI.GUIData.refresh_file();
        }
        else if (e.getKeyChar() == ',') {
        	if (selected_score != "A+") {
        		String new_score = score_.search(selected_score).prev.data;
        		classListModel.set(selectedIndex, new_score + rest);
        	}
        	GUI.GUIData.refresh_file();
        }
        else if (e.getKeyChar() == '.') {
        	if (selected_score != "NP") {
        		String new_score = score_.search(selected_score).next.data;
        		classListModel.set(selectedIndex, new_score + rest);
        	}
        	GUI.GUIData.refresh_file();
        }
    }
}
