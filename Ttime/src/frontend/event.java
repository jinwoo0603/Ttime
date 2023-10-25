class MyActionListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		JTextField t = (JTextField)e.getSource();
		GUIData.search_result = findMatchingRows(t.getText())
	}
}