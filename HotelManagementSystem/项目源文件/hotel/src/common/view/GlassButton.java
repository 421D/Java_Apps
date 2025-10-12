package common.view;

import java.awt.*;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;

import javax.swing.*;

public class GlassButton extends JToggleButton { //JToggleButton��ť����ȥʱ������ȥ�����ᵯ�����������ٰ�һ��
	private boolean paintFlag = false;
	public GlassButton() { //���췽�����Զ����ʼ��
		this.setContentAreaFilled(false);//�Ƿ����
		this.setBorderPainted(false);//�����߿�
		
		this.setVerticalTextPosition(SwingConstants.BOTTOM);  //���������ͼ��Ĵ�ֱλ��
		this.setVerticalAlignment(SwingConstants.CENTER);     //��ֱ���䷽ʽ
		this.setHorizontalTextPosition(SwingConstants.CENTER);//���������ͼ���ˮƽֱλ��
		this.setHorizontalAlignment(SwingConstants.CENTER);   //ˮƽ���䷽ʽ
		this.setIconTextGap(0);  //����icon��text�ľ���
		this.setMargin(new Insets(0, 0, 0, 0));//���ð�ť�߿�ͱ�ǩ֮��Ŀհ�
		//Insetsָ��һ�����������ĸ����߽���Ӧ�����Ŀհ׿ռ䣬Insets(int, int, int, int)ָ�����������ĸ��հ׿�ȡ�
	
		//Ϊ��ťע������¼�������
		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				paintFlag = true;
				repaint();	
			}
			@Override
			public void mouseExited(MouseEvent e) {
				paintFlag = false;
				repaint();
			}
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
		});
	}

	//��дpaint���������Ƶ�����ť��İ�ťͼ��
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g.create();  //ǿ������ת�����õ�Graphics2D�Ķ���
		super.paint(g2);
		Rectangle bs = g2.getClipBounds();//���ؾ��α߿�
		if(isSelected() || paintFlag) {
			Point2D center = new Point2D.Float(bs.width/2, bs.height/2); //Բ��
			float radius = Math.min(bs.width/2, bs.height/2);   //�뾶
			Point2D focus = new Point2D.Float(bs.width/2, bs.height/2); //��һ����ɫӳ��ĵ�
			Color[] colors = {Color.WHITE, new Color(255, 255, 255, 0)}; //����ֲ���ɫ
			                                      //new Color(255, 255, 255, 0) ��1������Ϊ͸���Ȳ���,���Ϊ��,�̺���.
			float[] dist = {0f, 0.8f};
			if(radius > 0 ) {
				//RadialGradientPaintʹ��Բ�η�����ɫ����ģʽ���ĳһ��״�ķ�ʽ
				RadialGradientPaint p = new RadialGradientPaint(center,    
						radius, focus, dist, colors, CycleMethod.NO_CYCLE); 
				g2.setPaint(p);   //�������Ч��
				g2.fillRect(0, 0, bs.width, bs.height);
			}
		}	 
	}
}
