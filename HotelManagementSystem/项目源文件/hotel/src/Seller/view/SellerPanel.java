package Seller.view;

import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class SellerPanel extends JPanel {
	public SellerPanel() {
		//�����б߿����һ������"����ס������"
		this.setBorder(BorderFactory.createTitledBorder(null, "����ס������", 
					TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, 
					new Font("΢���ź�", Font.BOLD, 12), null));
	}
}
