package com.bb.form;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import com.bb.exception.MsgException;
import com.bb.file.FileConverter;

public class MainForm extends JFrame {

	
	private FileConverter fileConverter = null;
	

	// 상단 클래스 폴더경로
	private JTextField textField = null;
	
	// 우측상단 버튼
	private JButton button = null;
	
	// 콘솔용 텍스트영역
	public static JTextArea textArea = null;
	
	// 콘솔용 스크롤
	private JScrollPane scrollPane = null;
	
	
	// 폰트 설정
	private Font basicFont = new Font("돋움", 0, 15);
	
	// 연한회색 색상 설정
	private Color lightGrayColor = new Color(240, 240, 240);
	
	
	public MainForm(String version) {
		
		// 폼 생성
		String title = "BBDirDecompiler";
		if (version != null && version.length() > 0) {
			title = title + "_" + version;
		}
		
		this.setLayout(null);
		this.setTitle(title);
		this.setBounds(0, 0, 800, 400);
		this.setVisible(true);
		
		addBasicWindowListener();
		
		addLabel();
		
		addButton();
		
		addTextField();
		
		addTextArea();
	}
	
	
	private void addBasicWindowListener() {
		
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// 사용자 명령으로 종료
				System.exit(0);
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		this.addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void componentResized(ComponentEvent e) {
				
				Rectangle rectangle = e.getComponent().getBounds();
				
				int formWidth = (int) rectangle.getWidth();
				int formHeight = (int) rectangle.getHeight();
				
				if (textField != null) {
					textField.setBounds(100, 10, formWidth - 255, 35);
				}
				
				if (button != null) {
					button.setBounds(formWidth - 145, 10, 110, 35);
				}
				
				if (scrollPane != null) {
					scrollPane.setBounds(10, 55, formWidth - 45, formHeight - 125);
				}
			}
			
			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	
	private void addLabel() {
		// 좌측상단 레이블 추가
		JLabel label = new JLabel("Classes Dir");
		label.setFont(basicFont);
		label.setBounds(10, 10, 100, 35);
		this.getContentPane().add(label);
	}
	
	
	private void addButton() {
		// 우측상단 버튼 추가
		button = new JButton("Decompile");
		button.setFont(basicFont);
		button.setBounds(655, 10, 110, 35);
		this.getContentPane().add(button);
		
		button.addActionListener(new ActionListener() {
			
			// 버튼 클릭시 이벤트 수행
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (fileConverter == null) {
					fileConverter = new FileConverter();
				}
				
				String classesDirText = null;
				if (textField != null) {
					classesDirText = textField.getText();
				}
				
				try {
					fileConverter.convertClassToJava(classesDirText);
				
				} catch (MsgException ex) {
					FormConsole.print(ex);
					
				} catch (Exception ex) {
					FormConsole.print(ex);
				}
			}
		});
	}
	
	
	private void addTextField() {
		// 상단 클래스 폴더경로 추가
		textField = new JTextField();
		textField.setFont(basicFont);
		this.getContentPane().add(textField);
		textField.setBounds(100, 10, 545, 35);
		textField.requestFocus();
	}
	
	
	private void addTextArea() {
		// 하단 콘솔용 텍스트영역 추가
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBackground(lightGrayColor);
		textArea.setBounds(0, 0, 100, 100);
		textArea.setFont(basicFont);
		textArea.setLineWrap(true);
		
		scrollPane = new JScrollPane(textArea);
		scrollPane.setBackground(lightGrayColor);
		this.getContentPane().add(scrollPane);
		scrollPane.setBounds(10, 55, 755, 275);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	}
}