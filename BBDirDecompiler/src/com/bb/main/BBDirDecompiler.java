package com.bb.main;

import com.bb.form.FormConsole;
import com.bb.form.MainForm;

public class BBDirDecompiler {

	public static void main(String[] args) {
		
		try {
			String version = "180926";
			MainForm mainForm = new MainForm(version);
			
		} catch (Exception e) {
			FormConsole.print(e);
		}
	}
}
