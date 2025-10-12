package common.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class SmallScrollPanel extends JPanel {
	private JButton leftScrollButton = null;   //���΢����ť
	private JButton rightScrollButton = null;  //�Ҳ�΢����ť
	private AlphaScrollPane alphaScrollPane = null; //���˵���ť�������
	public SmallScrollPanel() {  //�ڹ��췽���ж����ʼ������
		//���ò��ֹ���������BorderLayout
		BorderLayout borderLayout = new BorderLayout();
		borderLayout.setHgap(0);  //�������֮���ˮƽ���
		this.setLayout(borderLayout);
		this.setOpaque(false);
		//setSize()    setLocation()    setBounds()  ��Ч��
		//setPreferredSize()��Ҫ��ʹ�ò��ֹ�������ʱ��ʹ�ã����������ó�ʼ�Ĵ�С
		//�����һ����ʵ����ʾ������һ�£����ݽ�������ı仯���仯��
		this.setPreferredSize(new Dimension(0, 84));
		
		//���΢����ť��WEST��   �Ҳ�΢����ť��EAST��    ���˵���ť������壨CENTER��
		this.add(getLeftScrollButton(), BorderLayout.WEST); //������΢����ť
		this.add(getRightScrollButton(), BorderLayout.EAST);//����Ҳ�΢����ť
		this.add(getAlphaScrollPane(), BorderLayout.CENTER);//������˵���ť�������
	}
	
	//������˵���ť�������
	private AlphaScrollPane getAlphaScrollPane() {
		if(alphaScrollPane == null) {
			alphaScrollPane = new AlphaScrollPane();
			alphaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);//ʼ�ղ���ʾ��ֱ������
			alphaScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//ʼ�ղ���ʾˮƽ������
		
			//ע���¼�������������¼��ļ�����ComponentListener,����Ĵ�С��λ�û�ɼ��Է����仯��
			alphaScrollPane.addComponentListener(new ComponentListener() {
				@Override
				public void componentHidden(ComponentEvent arg0) { //�����ò��ɼ�ʱ����
					// TODO Auto-generated method stub
					
				}
				@Override
				public void componentMoved(ComponentEvent arg0) { //���λ�ø���ʱ���� 
					// TODO Auto-generated method stub
					
				}
				@Override
				public void componentResized(ComponentEvent arg0) { //�����С����ʱ����
					JScrollBar scrollBar = alphaScrollPane.getHorizontalScrollBar();//��ȡ���������	
					//��ȡ��Χ���Ʋ���
					int scrollWidth = scrollBar.getMaximum();   //����������ĳ���
					int paneWidth = alphaScrollPane.getWidth(); //�������Ŀ��
					//�������������ݵ�ʱ����������΢����ť
					if(paneWidth >= scrollWidth) {
						getLeftScrollButton().setVisible(false);
						getRightScrollButton().setVisible(false);
					}
					//������С�����ݵ�ʱ����ʾ����΢����ť
					if(paneWidth < scrollWidth) {
						getLeftScrollButton().setVisible(true);
						getRightScrollButton().setVisible(true);
					}
				}

				@Override
				public void componentShown(ComponentEvent arg0) {//�����ÿɼ�ʱ����
					// TODO Auto-generated method stub				
				}				
			});
		}
		return alphaScrollPane;
	}
	
	//������΢����ť
	private JButton getLeftScrollButton() {
		if(leftScrollButton == null) {
			leftScrollButton = new JButton();
			//������ťͼ��
			ImageIcon icon1 = new ImageIcon(getClass().getResource("../icon/zuoyidongoff.png"));
			ImageIcon icon2 = new ImageIcon(getClass().getResource("../icon/zuoyidongon.png"));
			leftScrollButton.setIcon(icon1); //���ð�ťͼ��
			leftScrollButton.setPressedIcon(icon2);  //����ʱ��ͼ��
			leftScrollButton.setRolloverIcon(icon2); //����꾭��ʱ��ͼ��
			leftScrollButton.setPreferredSize(new Dimension(38, 0)); //���ó�ʼ��С
			leftScrollButton.setOpaque(false);
			//���ñ߿� setBorder()
			//�������ģʽ���ṩ��һ����BorderFactory�����Կ�����Ʊ߿��������ϸ�ڡ�
			//leftScrollButton.setBorder(BorderFactory.createRaisedBevelBorder()); //͹�����İ�ť
			//createEmptyBorder(int top, int left, int bottom, int right) �����ձ߿�
			//�ϡ����¡�����ʱ�뷽�����߿������ֵ
			leftScrollButton.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
			leftScrollButton.setFocusable(false); //ȡ����ť�Ľ��㹦��
			leftScrollButton.setContentAreaFilled(false); //ȡ����ť�������
			
			//�¼������� (����¼������� MouseListener  MouseMotionListener)
			leftScrollButton.addMouseListener(new MouseListener() {
				JScrollBar scrollBar = getAlphaScrollPane().getHorizontalScrollBar();//��ȡ�������ĺ��������
				Boolean isPressed = false;  //����Ƿ������
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				@Override
				public void mouseExited(MouseEvent e) { //�뿪��ť
					isPressed = false;				
				}
				@Override
				public void mousePressed(MouseEvent e) { //���°�ť
					isPressed = true;
					
					/*Thread t = new Thread();
					t.start();*/
					
					new Thread(){     //�������̣߳�while(isPressed)�������ѭ��
						int oldValue = scrollBar.getValue(); //����ԭ�й�������ֵ
						public void run() {  //run() �̵߳����
							while(isPressed) {   //ѭ���ƶ����
								try {
									Thread.sleep(10);   //�����ƶ���̫��
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								oldValue = scrollBar.getValue();  //��ȡ�������ĵ�ǰֵ
								scrollBar.setValue(oldValue - 3); //���ù����������ƶ�3������
							}
						}
					}.start();
					
				}
				@Override
				public void mouseReleased(MouseEvent e) { //�ͷŰ�ť
					isPressed = false;					
				}
			});
		}
		return leftScrollButton;	
	}
	
	
	//����Ҳ�΢����ť
	private JButton getRightScrollButton() {
		if(rightScrollButton == null) {
			rightScrollButton = new JButton();
			ImageIcon icon1 = new ImageIcon(getClass().getResource("../icon/youyidongoff.png"));
			ImageIcon icon2 = new ImageIcon(getClass().getResource("../icon/youyidongon.png"));
			rightScrollButton.setIcon(icon1); 
			rightScrollButton.setPressedIcon(icon2);  
			rightScrollButton.setRolloverIcon(icon2); 
			rightScrollButton.setPreferredSize(new Dimension(38, 0)); 
			rightScrollButton.setOpaque(false);
			rightScrollButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
			rightScrollButton.setFocusable(false); 
			rightScrollButton.setContentAreaFilled(false); 
			
			//�¼������� (����¼������� MouseListener  MouseMotionListener)
			rightScrollButton.addMouseListener(new MouseListener() {
				JScrollBar scrollBar = getAlphaScrollPane().getHorizontalScrollBar();//��ȡ�������ĺ��������
				Boolean isPressed = false;  //����Ƿ������
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				@Override
				public void mouseExited(MouseEvent e) { //�뿪��ť
					isPressed = false;				
				}
				@Override
				public void mousePressed(MouseEvent e) { //���°�ť
					isPressed = true;
					
					/*Thread t = new Thread();
					t.start();*/
					
					new Thread(){     //�������̣߳�while(isPressed)�������ѭ��
						int oldValue = scrollBar.getValue(); //����ԭ�й�������ֵ
						public void run() {  //run() �̵߳����
							while(isPressed) {   //ѭ���ƶ����
								try {
									Thread.sleep(10);   //�����ƶ���̫��
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								oldValue = scrollBar.getValue();  //��ȡ�������ĵ�ǰֵ
								scrollBar.setValue(oldValue + 3); //���ù����������ƶ�3������
							}
						}
					}.start();
					
				}
				@Override
				public void mouseReleased(MouseEvent e) { //�ͷŰ�ť
					isPressed = false;					
				}
			});
		}
		return rightScrollButton;	
	}
}

/*JAVA��JButton�������� 
1�� ��JButton��С������
     ������ΪJButen������С�������͵ģ�����һ���setSize���ܶ����С�����ã�����һ����
     button.setPreferredSize(new Dimension(30,30));
     //��30��30�� ����Ҫ���ð�ť�Ĵ�С
2�� ��JButton͸��������
     ������ť����Ϊ͸���������Ͳ��ᵲ�ź���ı���
     button.setContentAreaFilled(false);
3�� ��JButtonȥ����ť�ı߿������
     ���������ʱ����İ�ť����Ҫ�߿���Ϊ�߿�Ӱ�����ۻ�������Ϊ����Ҫ���ǵ��֮ǰ��ť������ͨͼ
     ����ʽ�����֮����и���Ч���Ļ��Ϳ��������ַ���ȥ���߿�
     button.setBorderPainted(false);
4�� ��JButton���ͼ�������
     ���� // ʵ����һ��ͼ�����
     ImageIcon image = new ImageIcon(icons[i]);
     // ʵ������ť���󣬲������ð�ť����ʾͼƬ
     JButton button = new JButton(image);
     ��������
     button.setIcon(new ImageIcon(getClass().getResource("qq.png")));
     //qq.png����Ҫ��ӵ�ͼƬ
5 �� �ð�ť�水ť�ϵ�ͼ���仯
     butten.setMargin(new Insets(0,0,0,0));
6�� ����͹�����İ�ť���ܶ�������swingҲ���ô˷���
     butten.setBorder(BorderFactory.createRaisedBevelBorder());
7�� ���ð������İ�ť���ܶ�������swingҲ���ô˷���
     button.setBorder(BorderFactory.createLoweredBevelBorder());
8�� ���ð�ť��ǰ��ɫ�ͱ���ɫ
     button.setFont(new java.awt.Font("�����п�", 1, 15));
     button.setBackground(Color.green);
9�� �ı䰴ť����ʽ 
     UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
*/
