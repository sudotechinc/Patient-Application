package com.patientsessiontracker.core;

import java.awt.Toolkit;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class ErrorHandling extends DocumentFilter {
	
	private final byte MAX = 3;
	
	@Override
	public void insertString(DocumentFilter.FilterBypass fb, int offset, String str, AttributeSet attrs)
	                         throws BadLocationException {
		
		if((fb.getDocument().getLength() + str.length()) <= MAX && str.matches("^[0-9]?[0-9]{0,2}$")) {
			super.insertString(fb, offset, str, attrs);
		} else {
			Toolkit.getDefaultToolkit().beep();
		}
	}
	
	@Override
	public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String str, AttributeSet attrs)
	                    throws BadLocationException {
        
        if ((fb.getDocument().getLength() + str.length() - length) <= MAX && str.matches("^[0-9]?[0-9]{0,2}$")) {
            super.replace(fb, offset, length, str, attrs);
        } else {
            Toolkit.getDefaultToolkit().beep();
        }
	}
}
