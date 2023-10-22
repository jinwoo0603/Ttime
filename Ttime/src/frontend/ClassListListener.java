package frontend;
import java.awt.event.*;

public class ClassListListener implements MouseListener, MouseWheelListener {
	//현재 강의목록 정보 담은 컴포넌트에 담을 이벤트 리스너
	public void mouseWheelMoved(MouseWheelEvent e) {} //마우스 휠로 성적 수정
	public void mouseClicked(MouseEvent e) {} //마우스 더블 클릭으로 삭제
	
	//이하는 인터페이스라 디폴트 구현 해야되서 기술한것. 실제 기능은 하지 않음.
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

}
