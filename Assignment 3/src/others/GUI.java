package others;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import processing.Task;

public class GUI {

	private JFrame frame;
	private JPanel queue1;
	private JPanel queue2;
	private JPanel queue3;
	private JPanel queue4;
	private JPanel queue5;

	public GUI() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame("Store queues");
		frame.setBounds(100, 100, 638, 303);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblQueue = new JLabel("Queue 1");
		lblQueue.setBounds(48, 37, 57, 14);
		frame.getContentPane().add(lblQueue);

		JLabel lblQueue_1 = new JLabel("Queue 2");
		lblQueue_1.setBounds(144, 37, 57, 14);
		frame.getContentPane().add(lblQueue_1);

		JLabel lblQueue_2 = new JLabel("Queue 3");
		lblQueue_2.setBounds(242, 37, 57, 14);
		frame.getContentPane().add(lblQueue_2);

		JLabel lblQueue_3 = new JLabel("Queue 4");
		lblQueue_3.setBounds(351, 37, 57, 14);
		frame.getContentPane().add(lblQueue_3);

		JLabel lblQueue_4 = new JLabel("Queue 5");
		lblQueue_4.setBounds(460, 37, 57, 14);
		frame.getContentPane().add(lblQueue_4);

		queue1 = new JPanel();
		queue1.setBounds(40, 62, 65, 153);
		frame.getContentPane().add(queue1);

		queue2 = new JPanel();
		queue2.setBounds(136, 62, 65, 153);
		frame.getContentPane().add(queue2);

		queue3 = new JPanel();
		queue3.setBounds(234, 62, 65, 153);
		frame.getContentPane().add(queue3);

		queue4 = new JPanel();
		queue4.setBounds(343, 62, 65, 153);
		frame.getContentPane().add(queue4);

		queue5 = new JPanel();
		queue5.setBounds(460, 62, 65, 153);
		frame.getContentPane().add(queue5);
		
		frame.setVisible(true);
	}

	public void chooseQueue(int value, Task[] tasks) {
		if (value == 0)
			displayData(tasks, queue1);
		else if (value == 1)
			displayData(tasks, queue2);
		else if (value == 2)
			displayData(tasks, queue3);
		else if (value == 3)
			displayData(tasks, queue4);
		else
			displayData(tasks, queue5);
	}

	private void displayData(Task[] tasks, JPanel queue) {

		queue.removeAll();
		queue.revalidate();

		JList<Task> jtasks = new JList<Task>(tasks);
		JScrollPane sp = new JScrollPane(jtasks);

		queue.add(sp);
		queue.repaint();
		queue.revalidate();

	}
}
