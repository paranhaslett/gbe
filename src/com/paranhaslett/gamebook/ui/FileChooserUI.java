package com.paranhaslett.gamebook.ui;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class FileChooserUI extends JFileChooser {
	private static final long serialVersionUID = 6827459707236057162L;

	public FileChooserUI() {

		FileFilter xmlFilter = new GameBookFilter();
		addChoosableFileFilter(xmlFilter);
		FileFilter emaFilter = new EmaFilter();
		addChoosableFileFilter(emaFilter);
		setFileFilter(xmlFilter);
	}

	public File saveGameBook(EditorUI editorUI) {
		setApproveButtonText("Save");
		if (true) {// debug only
			setCurrentDirectory(new File(
					"C:/Users/OWNER/Paran/workspace/GameBookEditor"));
		}
		int rVal = showSaveDialog(editorUI);
		if (rVal == JFileChooser.APPROVE_OPTION) {
			return getSelectedFile();
		}
		return null;
	}

	public File loadGameBook(EditorUI editorUI) {
		if (true) {// debug only
			setCurrentDirectory(new File(
					"C:/Users/OWNER/Paran/workspace/GameBookEditor"));
		}
		int rVal = showOpenDialog(editorUI);
		if (rVal == JFileChooser.APPROVE_OPTION) {
			return getSelectedFile();
		}
		return null;
	}

	public class GameBookFilter extends FileFilter {

		@Override
		public boolean accept(File f) {
			String fn = f.getName();
			return f.isDirectory() || fn.endsWith(".gbf") || fn.endsWith(".xml");
		}

		@Override
		public String getDescription() {
			return "Gamebooks (*.gbf, *.xml)";
		}

	}

	public class EmaFilter extends FileFilter {

		@Override
		public boolean accept(File f) {
			String fn = f.getName();
			return f.isDirectory() || (fn.startsWith("gbf") && fn.endsWith(".txt"));
		}

		@Override
		public String getDescription() {
			return "Ema Wiki (gbf*.txt)";
		}

	}

}
