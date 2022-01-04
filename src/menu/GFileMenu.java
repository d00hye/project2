package menu;

import java.awt.FileDialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import frame.GFrame;
import frame.GPanel;
import main.GConstants.EFileMenuItem;

public class GFileMenu extends JMenu {
	private static final long serialVersionUID = 1L;

	private File file;
	private GFrame f;

	public GPanel panel;

	public GFileMenu(String text) {
		super(text);
		ActionHandler actionHandler = new ActionHandler();

		for (EFileMenuItem eFileMenuItem : EFileMenuItem.values()) {
			JMenuItem menuItem = new JMenuItem(eFileMenuItem.getText());
			menuItem.setActionCommand(eFileMenuItem.name());
			menuItem.addActionListener(actionHandler);
			menuItem.setFont((new Font("맑은 고딕", Font.PLAIN, 12)));
			this.add(menuItem);
		}
	}

	public void initialize() {
		this.file = null;
	}

	public void setAssociation(GPanel panel) {
		this.panel = panel;
	}

	private void nNew() {
		if (this.panel.isbModified()) {
			int reply = JOptionPane.showConfirmDialog(this.panel, "저장하겠습니까?", "Save", JOptionPane.YES_NO_CANCEL_OPTION);

			if (reply == JOptionPane.YES_OPTION) {
				save();
				this.panel.initialize();
				this.file = null;
			} else if (reply == JOptionPane.NO_OPTION) {
				this.panel.initialize();
				this.file = null;
			} else if (reply == JOptionPane.CANCEL_OPTION) {
			}
		} else
			this.panel.initialize();
			this.file = null;
	}

	private void open() {
		FileDialog openDialog = new FileDialog(f, "파일 열기", FileDialog.LOAD);
		openDialog.setDirectory(".");
		openDialog.setVisible(true);
		if (openDialog.getFile() == null)
			return;

		String filePath = openDialog.getDirectory() + openDialog.getFile();
		file = new File(filePath);
		ObjectInputStream objectInputStream;
		try {
			objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
			Object object = objectInputStream.readObject();
			this.panel.setShapes(object);
			objectInputStream.close();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.panel.setbModified(false);

	}

	private void saveFile(File file) {
		ObjectOutputStream objectOutputStream;
		if (file != null) {
			try {
				objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
				objectOutputStream.writeObject(this.panel.getShapes());
				objectOutputStream.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.panel.setbModified(false);
		}
	}

	private void save() {
		if (this.panel.isbModified()) {
			if (this.file == null) {
				this.saveas();
			} else {
				this.saveFile(this.file);
			}
		} else {
			this.saveFile(this.file);
		}
	}

	private void saveas() {
		FileDialog saveDialog = new FileDialog(f, "파일 저장", FileDialog.SAVE);
		saveDialog.setDirectory(".");
		saveDialog.setVisible(true);
		if (saveDialog.getFile() == null) {
			this.panel.setbModified(true);
			return;
		}

		String fileName = saveDialog.getDirectory() + saveDialog.getFile();
		file = new File(fileName);
		this.saveFile(file);
	}

	private void print() {
//		FileDialog openDialog = new FileDialog(f, "파일 열기", FileDialog.LOAD);
//		openDialog.setDirectory(".");
//		openDialog.setVisible(true);
//		if (openDialog.getFile() == null)
//			return;
//		
//		String filePath = openDialog.getDirectory() + openDialog.getFile();
//		file = new File(filePath);
//
//		try {
//			ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
//			Object object = objectInputStream.readObject();
//			this.panel.setShapes(object);
//			objectInputStream.close();
//			file.print();
//		} catch (IOException | ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (PrinterException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	private void exit() {
		if (this.panel.isbModified()) {
			int reply = JOptionPane.showConfirmDialog(this.panel, "저장하고 종료하겠습니까?", "Exit",
					JOptionPane.YES_NO_CANCEL_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				save();
				System.exit(0);
			} else if (reply == JOptionPane.NO_OPTION) {
				System.exit(0);
			} else if (reply == JOptionPane.CANCEL_OPTION) {
				this.panel.setbModified(true);
			}
		} else {
			System.exit(0);
		}
	}

//	private void saveImg() {
//		FileDialog saveDialog = new FileDialog(f, "이미지 저장", FileDialog.SAVE);
//		saveDialog.setDirectory(".");
//		saveDialog.setVisible(true);
//		if (saveDialog.getFile() == null) {
//			this.panel.setbModified(true);
//			return;
//		}
//
//		String fileName = saveDialog.getDirectory() + saveDialog.getFile();
//		file = new File(fileName + "png");
//
//		ByteArrayOutputStream bos = new ByteArrayOutputStream();
//		ObjectOutput out;
//		try {
//			out = new ObjectOutputStream(bos);
//			out.writeObject(this.panel.getShapes());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		byte[] imgBytes = bos.toByteArray();
//
//		ByteArrayInputStream inputStream = new ByteArrayInputStream(imgBytes);
//
//		try {
//			BufferedImage final_buffered_image = ImageIO.read(inputStream);
////			if( final_buffered_image!=null) {
//			ImageIO.write(final_buffered_image, "png", file);
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}

	private class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			EFileMenuItem eFileMenuItem = EFileMenuItem.valueOf(e.getActionCommand());
			switch (eFileMenuItem) {
			case eNew:
				nNew();
				break;
			case eOpen:
				open();
				break;
			case eSave:
				save();
				break;
			case eSaveAs:
				saveas();
				break;
			case ePrint:
				print();
				break;
			case eExit:
				exit();
				break;
//			case eImg:
//				break;
			default:
				break;
			}
		}
	}
}
