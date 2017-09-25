<<<<<<< HEAD
/*
   This file is part of j2dcg.
   j2dcg is free software; you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation; either version 2 of the License, or
   (at your option) any later version.
   j2dcg is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.
   You should have received a copy of the GNU General Public License
   along with j2dcg; if not, write to the Free Software
   Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/

package view;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import model.Pixel;

/**
 * <p>Title: ColorDialog</p>
 * <p>Description: ... (JDialog)</p>
 * <p>Copyright: Copyright (c) 2003 Mohammed Elghaouat, Eric Paquette</p>
 * <p>Company: (ÉTS) - École de Technologie Supérieure</p>
 * @author unascribed
 * @version $Revision: 1.7 $
 */
public class ColorDialog extends JDialog {
	private JButton okButton;
	private RGBColorMediator rgbMediator;
	private CMYKColorMediator cmykMediator;
	private ActionListener okActionListener;
	private ColorDialogResult result;
	
	static public Pixel getColor(Frame owner, Pixel color, int imageWidths) {
		ColorDialogResult result = new ColorDialogResult(color);
		ColorDialog colorDialog = new ColorDialog(owner, result, imageWidths);
		colorDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		colorDialog.pack();
		colorDialog.setVisible(true);
		if (result.isAccepted()) {
			return result.getPixel();
		} else {
			return null;
		}
	}

	ColorDialog(Frame owner, ColorDialogResult result, int imageWidths) {
		super(owner, true);
		this.result = result;
		
		JTabbedPane tabbedPane = new JTabbedPane();
		JPanel rgbPanel = createRGBPanel(result, imageWidths);
		tabbedPane.addTab("RGB", rgbPanel);

		JPanel cmykPanel = createCMYKPanel(result, imageWidths);
		tabbedPane.addTab("CMYK", cmykPanel);
		
		JPanel hsvPanel = createHSVPanel(result, imageWidths);
		tabbedPane.addTab("HSV", hsvPanel);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
		AbstractAction okAction = new AbstractAction("OK") {
			public void actionPerformed(ActionEvent e) {
				ColorDialog.this.result.setAccepted(true);
				dispose();
			}
		};
		okButton = new JButton(okAction);
		buttonsPanel.add(okButton);
		AbstractAction cancelAction = new AbstractAction("Cancel") {
			public void actionPerformed(ActionEvent e) {
				ColorDialog.this.dispose();
			}
		};
		buttonsPanel.add(new JButton(cancelAction));

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.add(tabbedPane);
		mainPanel.add(buttonsPanel);

		getContentPane().add(mainPanel, BorderLayout.CENTER);
	}

	private JPanel createRGBPanel(ColorDialogResult result, int imageWidths) {	
		rgbMediator = new RGBColorMediator(result, imageWidths, 30);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		ColorSlider csRed = new ColorSlider("R:", result.getPixel().getRed(), rgbMediator.getRedImage());
		ColorSlider csGreen = new ColorSlider("G:", result.getPixel().getGreen(), rgbMediator.getGreenImage());
		ColorSlider csBlue = new ColorSlider("B:", result.getPixel().getBlue(), rgbMediator.getBlueImage());
		
		rgbMediator.setRedCS(csRed);
		rgbMediator.setGreenCS(csGreen);
		rgbMediator.setBlueCS(csBlue);
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(csRed);
		panel.add(csGreen);
		panel.add(csBlue);
		
		return panel;
	}
	
	private JPanel createCMYKPanel(ColorDialogResult result, int imageWidths) {	
		cmykMediator = new CMYKColorMediator(result, imageWidths, 30);

		JPanel cmykPanel = new JPanel();
		cmykPanel.setLayout(new BoxLayout(cmykPanel, BoxLayout.Y_AXIS));
		
		System.out.println("red: " + result.getPixel().getRed());
		System.out.println("Green: " + result.getPixel().getGreen());
		System.out.println("Blue: " + result.getPixel().getBlue());
		ColorSlider csCyan = new ColorSlider("C:", 255-result.getPixel().getRed(), cmykMediator.getCyanImage());
		ColorSlider csMagenta = new ColorSlider("M:", 255-result.getPixel().getGreen(), cmykMediator.getMagentaImage());
		ColorSlider csYellow = new ColorSlider("Y:", 255-result.getPixel().getBlue(), cmykMediator.getYellowImage());
	//	ColorSlider csKey = new ColorSlider("K:", result.getPixel().getBlue(), cmykMediator.getBlueImage());
		
		cmykMediator.setCyanCS(csCyan);
		cmykMediator.setMagentaCS(csMagenta);
		cmykMediator.setYellowCS(csYellow);
	//	rgbMediator.setKeyCS(csKey);
		
		cmykPanel.setLayout(new BoxLayout(cmykPanel, BoxLayout.Y_AXIS));
		cmykPanel.add(csCyan);
		cmykPanel.add(csMagenta);
		cmykPanel.add(csYellow);
	//	panel.add(csKey);
		
		return cmykPanel;
	}
	
	private JPanel createHSVPanel(ColorDialogResult result, int imageWidths) {	
		JPanel panel = new JPanel();
		
		return panel;
	}
}

=======
/*
   This file is part of j2dcg.
   j2dcg is free software; you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation; either version 2 of the License, or
   (at your option) any later version.
   j2dcg is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.
   You should have received a copy of the GNU General Public License
   along with j2dcg; if not, write to the Free Software
   Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/

package view;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import model.Pixel;

/**
 * <p>Title: ColorDialog</p>
 * <p>Description: ... (JDialog)</p>
 * <p>Copyright: Copyright (c) 2003 Mohammed Elghaouat, Eric Paquette</p>
 * <p>Company: (ï¿½TS) - ï¿½cole de Technologie Supï¿½rieure</p>
 * @author unascribed
 * @version $Revision: 1.7 $
 */
public class ColorDialog extends JDialog {
	private JButton okButton;
	private RGBColorMediator rgbMediator;
	private ActionListener okActionListener;
	private ColorDialogResult result;
	
	static public Pixel getColor(Frame owner, Pixel color, int imageWidths) {
		ColorDialogResult result = new ColorDialogResult(color);
		ColorDialog colorDialog = new ColorDialog(owner, result, imageWidths);
		colorDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		colorDialog.pack();
		colorDialog.setVisible(true);
		if (result.isAccepted()) {
			return result.getPixel();
		} else {
			return null;
		}
	}

	ColorDialog(Frame owner, ColorDialogResult result, int imageWidths) {
		super(owner, true);
		this.result = result;
		
		JTabbedPane tabbedPane = new JTabbedPane();
		JPanel rgbPanel = createRGBPanel(result, imageWidths);
		tabbedPane.addTab("RGB", rgbPanel);

		JPanel cmykPanel = createCMYKPanel(result, imageWidths);
		tabbedPane.addTab("CMYK", cmykPanel);
		
		JPanel hsvPanel = createHSVPanel(result, imageWidths);
		tabbedPane.addTab("HSV", hsvPanel);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
		AbstractAction okAction = new AbstractAction("OK") {
			public void actionPerformed(ActionEvent e) {
				ColorDialog.this.result.setAccepted(true);
				dispose();
			}
		};
		okButton = new JButton(okAction);
		buttonsPanel.add(okButton);
		AbstractAction cancelAction = new AbstractAction("Cancel") {
			public void actionPerformed(ActionEvent e) {
				ColorDialog.this.dispose();
			}
		};
		buttonsPanel.add(new JButton(cancelAction));

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.add(tabbedPane);
		mainPanel.add(buttonsPanel);

		getContentPane().add(mainPanel, BorderLayout.CENTER);
	}

	private JPanel createRGBPanel(ColorDialogResult result, int imageWidths) {	
		rgbMediator = new RGBColorMediator(result, imageWidths, 30);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		ColorSlider csRed = new ColorSlider("R:", result.getPixel().getRed(), rgbMediator.getRedImage());
		ColorSlider csGreen = new ColorSlider("G:", result.getPixel().getGreen(), rgbMediator.getGreenImage());
		ColorSlider csBlue = new ColorSlider("B:", result.getPixel().getBlue(), rgbMediator.getBlueImage());
		
		rgbMediator.setRedCS(csRed);
		rgbMediator.setGreenCS(csGreen);
		rgbMediator.setBlueCS(csBlue);
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(csRed);
		panel.add(csGreen);
		panel.add(csBlue);
		
		return panel;
	}
	
	private JPanel createCMYKPanel(ColorDialogResult result, int imageWidths) {	
		JPanel panel = new JPanel();
		
	//	cmykMediator = new CMYKColorMediator(result, imageWidths, 30);
		
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		/*
		ColorSlider csCyan = new ColorSlider("C:", 1-result.getPixel().getRed(), cmykMediator.getRedImage());
		ColorSlider csMagenta = new ColorSlider("M:", 1-result.getPixel().getGreen(), cmykMediator.getGreenImage());
		ColorSlider csYellow = new ColorSlider("Y:", 1-result.getPixel().getBlue(), cmykMediator.getBlueImage());
		//ColorSlider csKey = new ColorSlider("K:", result.getPixel().getBlue(), cmykMediator.getBlueImage());
		
		cmykMediator.setCyanCS(csCyan);
		cmykMediator.setMagentaCS(csMagenta);
		cmykMediator.setYellowCS(csYellow);
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(csRed);
		panel.add(csGreen);
		panel.add(csBlue);
		*/
		return panel;
	}
	
	private JPanel createHSVPanel(ColorDialogResult result, int imageWidths) {	
		JPanel panel = new JPanel();
		
		return panel;
	}
}

>>>>>>> 9c973d424d2348ec6f366bb870bb8fbf0bfbaa08
