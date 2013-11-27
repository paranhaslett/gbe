package com.paranhaslett.gamebook;

import java.awt.EventQueue;
import java.io.File;
import java.util.HashMap;

import javax.swing.filechooser.FileFilter;

import com.paranhaslett.gamebook.controller.ChanceController;
import com.paranhaslett.gamebook.controller.ChoiceController;
import com.paranhaslett.gamebook.controller.Controller;
import com.paranhaslett.gamebook.controller.LibraryController;
import com.paranhaslett.gamebook.controller.TextController;
import com.paranhaslett.gamebook.controller.BookController;
import com.paranhaslett.gamebook.controller.GotoController;
import com.paranhaslett.gamebook.controller.IfController;
import com.paranhaslett.gamebook.controller.PageController;
import com.paranhaslett.gamebook.controller.SectionController;
import com.paranhaslett.gamebook.controller.SetController;
import com.paranhaslett.gamebook.loadable.Loadable;
import com.paranhaslett.gamebook.loader.Loader;
import com.paranhaslett.gamebook.model.Book;
import com.paranhaslett.gamebook.model.Library;
import com.paranhaslett.gamebook.ui.EditorUI;
import com.paranhaslett.gamebook.ui.FileChooserUI;
import com.paranhaslett.gamebook.ui.FileChooserUI.EmaFilter;
import com.paranhaslett.gamebook.ui.FileChooserUI.GameBookFilter;
import com.paranhaslett.gamebook.ui.tree.TreeUI;

public class Editor {
	private HashMap<String, Controller> controllers = new HashMap<String, Controller>();
	private Book book;
	private LibraryController lc;
	private static Editor editor = null;
	public TreeUI tree;
	public EditorUI editorUI;
	private FileChooserUI fileChooser;
	
	private Editor(){
	}
	
	public static Editor getEd(){
		if (editor == null){
			editor = new Editor();
			editor.init();
				
		}
		return editor;
	}
	
	private void init(){
		controllers.put("Book", new BookController());
		controllers.put("Section", new SectionController());
		controllers.put("Page", new PageController());
		controllers.put("Text", new TextController());
		controllers.put("Chance", new ChanceController());
		controllers.put("Choice", new ChoiceController());
		controllers.put("Set", new SetController());
		controllers.put("If", new IfController());		
		controllers.put("Goto", new GotoController());	
		tree = new TreeUI();			
		fileChooser = new FileChooserUI();	
		editorUI = new EditorUI();
		editorUI.setVisible(true);
		//load library file silently
		create();
	}
	
	public void load(){
		File file = fileChooser.loadGameBook(editorUI);
		if( file != null){
			Loader loader = getLoader();
			if (loader != null){
				book = loader.load(file);
				getController("Book").update(book);
				setupTree();
			} else {
				create();
			}
		}
	}
	
	
	
	public void save() {
		File file = fileChooser.loadGameBook(editorUI);		
		if( file != null){
			Loader loader = getLoader();
			if (loader != null){
				loader.save(book,file);
			} else {
				throw new RuntimeException("Bad Programmer: File chooser selected wrong file type");
			}
		}
	}

	public void create(){
		book = new Book();
		book.title="New";
		getController("Book").update(book);
		setupTree();
	}
	
	private Loader getLoader() {
		FileFilter filt = fileChooser.getFileFilter();
		Loader loader = null;
		if(filt instanceof GameBookFilter){
			loader = Loadable.xmlLoader;
		}
		if(filt instanceof EmaFilter){
			loader = Loadable.emaLoader;
		}
		return loader;
	}
	
	
	private void setupTree() {
		tree.setup(book);
		editorUI.updateTree(tree);
	}

	public void update() {
		tree.update();
	}

	public Controller getController(String key) {
		return controllers.get(key);
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					getEd();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
