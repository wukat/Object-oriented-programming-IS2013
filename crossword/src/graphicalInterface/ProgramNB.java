/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ProgramNB.java
 *
 * Created on 2013-11-10, 00:48:03
 */
package graphicalInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.PageRanges;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import Exceptions.FailedToGenerateCrosswordException;
import Strategies.EasyStrategy;
import browser.CwBrowser;
import dictionary.IntelLiCwDB;

/**
 * 
 * @author wukat
 */
public class ProgramNB extends javax.swing.JFrame {

    public final void showCw() {
        drawingPane.paint(drawingPane.getGraphics());
        drawingPane.revalidate();
        drawingPane.paint(drawingPane.getGraphics());
    }

    public class DrawingPane extends JPanel implements Printable {

        protected void paintComponent(Graphics graphic) {
            super.paintComponent(graphic);
            if (browser.hasActual()) {
                graphic.setColor(Color.BLACK);
                for (int i = 0; i < browser.getActual().getBoardWidth(); i++) {
                    for (Integer j = 1; j <= browser.getActual().getBoardHeight(); j++) {
                        graphic.drawString(j.toString() + ". ", 10,
                                30 + (j - 1) * 30);
                        if (browser.getActual().checkBoardCell(i, j - 1)) {
                            graphic.drawRect(35 + i * 30, 10 + (j - 1) * 30,
                                    30, 30);
                        }
                    }
                }
                int maxWidth = browser.getActual().getBoardWidth() * 30 + 45;
                int j = 0;
                for (String entry : easyStrategy.printAllEntries(
                        browser.getActual()).split("\n")) {
                    graphic.drawString(entry, 10, browser.getActual().getBoardHeight() * 30 + 30 + j * 15);
                    if (maxWidth < entry.length() * 7) {
                        maxWidth = entry.length() * 7;
                    }
                    j++;
                }
                if (this.getSize().width != maxWidth
                        || this.getSize().height != j * 16
                        + browser.getActual().getBoardHeight() * 30
                        + 18) {
                    this.setPreferredSize(new Dimension(maxWidth, j * 16
                            + browser.getActual().getBoardHeight() * 30 + 18));
                }
            }
        }

        public int print(Graphics g, PageFormat pf, int page)
                throws PrinterException {

            // We have only one page, and 'page'
            // is zero-based
            if (page > 0) {
                return NO_SUCH_PAGE;
            }

            int constX = 90;
            int constY = 90;
            // Now we perform our rendering
            g.drawString("Crossword", constX, constY);
            for (int i = 0; i < browser.getActual().getBoardWidth(); i++) {
                for (Integer j = 1; j <= browser.getActual().getBoardHeight(); j++) {
                    g.drawString(j.toString() + ". ", 10 + constX, 30 + (j - 1)
                            * 30 + constY);
                    if (browser.getActual().checkBoardCell(i, j - 1)) {
                        g.drawRect(25 + i * 30 + constX, 10 + (j - 1) * 30
                                + constY, 30, 30);
                    }
                }
            }
            int j = 0;
            for (String entry : easyStrategy.printAllEntries(
                    browser.getActual()).split("\n")) {
                g.drawString(entry, 10 + constX, browser.getActual().getBoardHeight() * 30 + 30 + j * 15 + constY);
                j++;
            }

            // tell the caller that this page is part
            // of the printed document
            return PAGE_EXISTS;
        }
    }

    /** Creates new form ProgramNB */
    public ProgramNB() {
        initComponents();

        lastUsedNext = true;
        actualizeButtons();
        drawingPane.setPreferredSize(new Dimension(0, 0));
    }

    public final void actualizeButtons() {
        if (lastUsedNext) {
            nextButton.setEnabled(browser.hasNext());
            prevButton.setEnabled(browser.previousIndex() > 0);
        } else {
            prevButton.setEnabled(browser.hasPrevious());
            nextButton.setEnabled(browser.nextIndex() < browser.getAmountOfCrosswords());
        }
        saveButton.setEnabled(browser.hasActual());
        solveButton.setEnabled(browser.hasActual());
        printButton.setEnabled(browser.hasActual());
        toPDFButton.setEnabled(browser.hasActual());
        if (browser.hasActual()) {
            showCw();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		buttonStrategyGropu = new javax.swing.ButtonGroup();
		generatePanel = new javax.swing.JPanel();
		easy = new javax.swing.JRadioButton();
		hard = new javax.swing.JRadioButton();
		columnsLabel = new javax.swing.JLabel();
		rowsLabel = new javax.swing.JLabel();
		columns = new javax.swing.JSpinner();
		rows = new javax.swing.JSpinner();
		generateButton = new javax.swing.JButton();
		fromFilePanel = new javax.swing.JPanel();
		importLabel = new javax.swing.JLabel();
		importButton = new javax.swing.JButton();
		loadLabel = new javax.swing.JLabel();
		loadButton = new javax.swing.JButton();
		optionsPanel = new javax.swing.JPanel();
		solveButton = new javax.swing.JButton();
		printButton = new javax.swing.JButton();
		toPDFButton = new javax.swing.JButton();
		browsePanel = new javax.swing.JPanel();
		prevButton = new javax.swing.JButton();
		nextButton = new javax.swing.JButton();
		saveButton = new javax.swing.JButton();
		crosswordPanel = new javax.swing.JPanel();
		crosswordSPanel = new javax.swing.JScrollPane();
		drawingPane = new DrawingPane();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Crosswords by wukat");

		generatePanel.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Generate"));

		buttonStrategyGropu.add(easy);
		easy.setSelected(true);
		easy.setText("Easy");
		easy.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				easyActionPerformed(evt);
			}
		});

		buttonStrategyGropu.add(hard);
		hard.setText("Hard");

		columnsLabel.setText("Columns");

		rowsLabel.setText("Rows");

		columns.setModel(new javax.swing.SpinnerNumberModel(5, 2, 12, 1));

		rows.setModel(new javax.swing.SpinnerNumberModel(5, 2, 12, 1));

		generateButton.setText("Generate");
		generateButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				generateButtonActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout generatePanelLayout = new javax.swing.GroupLayout(
				generatePanel);
		generatePanel.setLayout(generatePanelLayout);
		generatePanelLayout
				.setHorizontalGroup(generatePanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								generatePanelLayout
										.createSequentialGroup()
										.addGroup(
												generatePanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																generatePanelLayout
																		.createSequentialGroup()
																		.addGap(6,
																				6,
																				6)
																		.addGroup(
																				generatePanelLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addGroup(
																								generatePanelLayout
																										.createSequentialGroup()
																										.addComponent(
																												easy)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												hard))
																						.addGroup(
																								generatePanelLayout
																										.createSequentialGroup()
																										.addGroup(
																												generatePanelLayout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING)
																														.addComponent(
																																columnsLabel)
																														.addComponent(
																																rowsLabel))
																										.addGap(18,
																												18,
																												18)
																										.addGroup(
																												generatePanelLayout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.TRAILING)
																														.addComponent(
																																columns,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																rows,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.PREFERRED_SIZE)))))
														.addGroup(
																generatePanelLayout
																		.createSequentialGroup()
																		.addGap(29,
																				29,
																				29)
																		.addComponent(
																				generateButton)))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		generatePanelLayout
				.setVerticalGroup(generatePanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								generatePanelLayout
										.createSequentialGroup()
										.addGroup(
												generatePanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(easy)
														.addComponent(hard))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												generatePanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																columnsLabel)
														.addComponent(
																columns,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												generatePanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																rows,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(rowsLabel))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(generateButton)));

		fromFilePanel.setBorder(javax.swing.BorderFactory
				.createTitledBorder("From file"));

		importLabel.setText("Import database");

		importButton.setText("Choose file");
		importButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				importButtonActionPerformed(evt);
			}
		});

		loadLabel.setText("Load crosswords");

		loadButton.setText("Choose folder");
		loadButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				loadButtonActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout fromFilePanelLayout = new javax.swing.GroupLayout(
				fromFilePanel);
		fromFilePanel.setLayout(fromFilePanelLayout);
		fromFilePanelLayout.setHorizontalGroup(fromFilePanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						fromFilePanelLayout.createSequentialGroup()
								.addContainerGap().addComponent(importLabel)
								.addContainerGap(54, Short.MAX_VALUE))
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						fromFilePanelLayout.createSequentialGroup()
								.addContainerGap(77, Short.MAX_VALUE)
								.addComponent(importButton).addContainerGap())
				.addGroup(
						fromFilePanelLayout.createSequentialGroup()
								.addContainerGap().addComponent(loadLabel)
								.addContainerGap(53, Short.MAX_VALUE))
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						fromFilePanelLayout.createSequentialGroup()
								.addContainerGap(59, Short.MAX_VALUE)
								.addComponent(loadButton).addContainerGap()));
		fromFilePanelLayout
				.setVerticalGroup(fromFilePanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								fromFilePanelLayout
										.createSequentialGroup()
										.addComponent(importLabel)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(importButton)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(loadLabel)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(loadButton)
										.addContainerGap(7, Short.MAX_VALUE)));

		optionsPanel.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Options"));

		solveButton.setText("Solve");
		solveButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				solveButtonActionPerformed(evt);
			}
		});

		printButton.setText("Print");
		printButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				printButtonActionPerformed(evt);
			}
		});

		toPDFButton.setText("toPDF");
		toPDFButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				toPDFButtonActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout optionsPanelLayout = new javax.swing.GroupLayout(
				optionsPanel);
		optionsPanel.setLayout(optionsPanelLayout);
		optionsPanelLayout
				.setHorizontalGroup(optionsPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								optionsPanelLayout
										.createSequentialGroup()
										.addGap(6, 6, 6)
										.addComponent(solveButton)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(printButton)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(toPDFButton)
										.addContainerGap()));
		optionsPanelLayout
				.setVerticalGroup(optionsPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								optionsPanelLayout
										.createSequentialGroup()
										.addGroup(
												optionsPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																solveButton)
														.addComponent(
																printButton)
														.addComponent(
																toPDFButton))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		browsePanel.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Browse"));

		prevButton.setText("Prev");
		prevButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				prevButtonActionPerformed(evt);
			}
		});

		nextButton.setText("Next");
		nextButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				nextButtonActionPerformed(evt);
			}
		});

		saveButton.setText("Save");
		saveButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				saveButtonActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout browsePanelLayout = new javax.swing.GroupLayout(
				browsePanel);
		browsePanel.setLayout(browsePanelLayout);
		browsePanelLayout
				.setHorizontalGroup(browsePanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								browsePanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(prevButton)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												8, Short.MAX_VALUE)
										.addComponent(nextButton)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(saveButton)
										.addContainerGap()));
		browsePanelLayout
				.setVerticalGroup(browsePanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								browsePanelLayout
										.createSequentialGroup()
										.addGroup(
												browsePanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																prevButton)
														.addComponent(
																saveButton)
														.addComponent(
																nextButton))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		crosswordPanel.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Crossword"));

		crosswordSPanel.setBackground(new java.awt.Color(-1, true));
		crosswordSPanel.setAutoscrolls(true);

		drawingPane.setBackground(new java.awt.Color(-1, true));
		drawingPane.setAutoscrolls(true);
		drawingPane.setDoubleBuffered(false);

		javax.swing.GroupLayout drawingPaneLayout = new javax.swing.GroupLayout(
				drawingPane);
		drawingPane.setLayout(drawingPaneLayout);
		drawingPaneLayout.setHorizontalGroup(drawingPaneLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 841, Short.MAX_VALUE));
		drawingPaneLayout.setVerticalGroup(drawingPaneLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 451, Short.MAX_VALUE));

		crosswordSPanel.setViewportView(drawingPane);

		javax.swing.GroupLayout crosswordPanelLayout = new javax.swing.GroupLayout(
				crosswordPanel);
		crosswordPanel.setLayout(crosswordPanelLayout);
		crosswordPanelLayout.setHorizontalGroup(crosswordPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(crosswordSPanel,
						javax.swing.GroupLayout.DEFAULT_SIZE, 603,
						Short.MAX_VALUE));
		crosswordPanelLayout.setVerticalGroup(crosswordPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(crosswordSPanel,
						javax.swing.GroupLayout.DEFAULT_SIZE, 469,
						Short.MAX_VALUE));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(
														crosswordPanel,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		generatePanel,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(12, 12,
																		12)
																.addComponent(
																		fromFilePanel,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				false)
																				.addComponent(
																						browsePanel,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(
																						optionsPanel,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE))))
								.addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING,
												false)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		optionsPanel,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		browsePanel,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
												.addComponent(
														generatePanel,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(fromFilePanel, 0,
														144, Short.MAX_VALUE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(crosswordPanel,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap()));

		pack();
	}// </editor-fold>//GEN-END:initComponents

    private void easyActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_easyActionPerformed
    }// GEN-LAST:event_easyActionPerformed

    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_loadButtonActionPerformed
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (evt.getSource() == loadButton) {
            int returnVal = fc.showDialog(loadButton, "Open directory");
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    browser.loadFromFiles(fc.getSelectedFile().getPath(),
                            easyStrategy, easyStrategy);
                    actualizeButtons();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(),
                            "Operation failed", JOptionPane.ERROR_MESSAGE);
                } catch (NullPointerException e) {
                    JOptionPane.showMessageDialog(null, "Wrong file format.",
                            "Operation failed", JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,
                            "Wrong type file found in directory.",
                            "Operation failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }// GEN-LAST:event_loadButtonActionPerformed

    private void importButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_importButtonActionPerformed
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt",
                "txt");
        fc.setAcceptAllFileFilterUsed(false);
        fc.setFileFilter(filter);
        if (evt.getSource() == importButton) {
            int returnVal = fc.showDialog(importButton, "Import");

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    browser.setDefaultCwDB(new IntelLiCwDB(fc.getSelectedFile().getPath()));
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null,
                            "Failed to import database from file.",
                            "Operation failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }// GEN-LAST:event_importButtonActionPerformed

    private void generateButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_generateButtonActionPerformed
        if (evt.getSource() == generateButton) {
            if (hard.isSelected()) {
                try {
                    browser.generateCw(
                            Integer.parseInt(columns.getValue().toString()),
                            Integer.parseInt(rows.getValue().toString()),
                            easyStrategy);
                    actualizeButtons();
                } catch (FailedToGenerateCrosswordException a) {
                    JOptionPane.showMessageDialog(null,
                            "Failed to generate crossword from this database.",
                            "Operation failed", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                try {
                    browser.generateCw(
                            Integer.parseInt(columns.getValue().toString()),
                            Integer.parseInt(rows.getValue().toString()),
                            easyStrategy);
                    actualizeButtons();
                } catch (FailedToGenerateCrosswordException a) {
                    JOptionPane.showMessageDialog(null,
                            "Failed to generate crossword from this database.",
                            "Operation failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }// GEN-LAST:event_generateButtonActionPerformed

    private void solveButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_solveButtonActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_solveButtonActionPerformed

    private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_printButtonActionPerformed
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable((Printable) drawingPane);
        if (job.printDialog()) {
            try {
                job.print();
            } catch (PrinterException ex) {
                JOptionPane.showMessageDialog(null,
                        "Failed to print crossword.", "Operation failed",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }// GEN-LAST:event_printButtonActionPerformed

    private void prevButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_prevButtonActionPerformed
        browser.previous(lastUsedNext);
        lastUsedNext = false;
        actualizeButtons();
    }// GEN-LAST:event_prevButtonActionPerformed

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_nextButtonActionPerformed
        browser.next(lastUsedNext);
        lastUsedNext = true;
        actualizeButtons();
    }// GEN-LAST:event_nextButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_saveButtonActionPerformed
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (evt.getSource() == saveButton) {
            int returnVal = fc.showDialog(saveButton, "Save in directory");

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    browser.saveActual(fc.getSelectedFile().getPath());
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null,
                            "Failed to save crossword.", "Operation failed",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }// GEN-LAST:event_saveButtonActionPerformed

    private void toPDFButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_toPDFButtonActionPerformed

        Document document = new Document();
        try {
// krok 2   
            PdfWriter.getInstance(document,
                    new FileOutputStream(browser.getActual().getID().toString() + ".pdf"));
// krok 3
            document.open();
// krok 4
            document.add(null)s
            document.add(new Paragraph("Hello World"));
        } catch (DocumentException de) {
            System.err.println(de.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
// krok 5
        document.close();
//		JFileChooser fc = new JFileChooser();
//		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//		if (evt.getSource() == saveButton) {
//			int returnVal = fc.showDialog(saveButton, "Save in directory");
//
//			if (returnVal == JFileChooser.APPROVE_OPTION) {
//				try {
//					browser.saveActual(fc.getSelectedFile().getPath());
//				} catch (IOException e) {
//					JOptionPane.showMessageDialog(null,
//							"Failed to save crossword.", "Operation failed",
//							JOptionPane.ERROR_MESSAGE);
//				}
//			}
//		}
    }// GEN-LAST:event_toPDFButtonActionPerformed

    /**
     * @param args
     *            the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed"
        // desc=" Look and feel setting code (optional) ">
		/*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase
         * /tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ProgramNB.class.getName()).log(
                    java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProgramNB.class.getName()).log(
                    java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProgramNB.class.getName()).log(
                    java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProgramNB.class.getName()).log(
                    java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    browser = new CwBrowser(null);
                    new ProgramNB().setVisible(true);
                } catch (IOException e) {

                    Object[] options = {"Yes", "No",};

                    int n = JOptionPane.showOptionDialog(
                            null,
                            "Failed to load default data base. Do you want to choose it manually?",
                            "Failed to start",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.ERROR_MESSAGE, null, options,
                            options[0]);

                    if (n == 0) {
                        JFileChooser fc = new JFileChooser();
                        int returnVal = fc.showDialog(fc, "Import database");
                        if (returnVal == JFileChooser.APPROVE_OPTION) {
                            try {
                                browser = new CwBrowser(fc.getSelectedFile().getAbsolutePath());
                                new ProgramNB().setVisible(true);

                            } catch (IOException a) {
                                JOptionPane.showMessageDialog(null,
                                        "Failed to load data base.",
                                        "Failed to start",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "Operation canceled, program halts.",
                                    "Failed to start",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Operation canceled, program halts.",
                                "Failed to start", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }
    private static CwBrowser browser;
    private static EasyStrategy easyStrategy = new EasyStrategy();
    private boolean lastUsedNext = true;
	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JPanel browsePanel;
	private javax.swing.ButtonGroup buttonStrategyGropu;
	private javax.swing.JSpinner columns;
	private javax.swing.JLabel columnsLabel;
	private javax.swing.JPanel crosswordPanel;
	private javax.swing.JScrollPane crosswordSPanel;
	private javax.swing.JPanel drawingPane;
	private javax.swing.JRadioButton easy;
	private javax.swing.JPanel fromFilePanel;
	private javax.swing.JButton generateButton;
	private javax.swing.JPanel generatePanel;
	private javax.swing.JRadioButton hard;
	private javax.swing.JButton importButton;
	private javax.swing.JLabel importLabel;
	private javax.swing.JButton loadButton;
	private javax.swing.JLabel loadLabel;
	private javax.swing.JButton nextButton;
	private javax.swing.JPanel optionsPanel;
	private javax.swing.JButton prevButton;
	private javax.swing.JButton printButton;
	private javax.swing.JSpinner rows;
	private javax.swing.JLabel rowsLabel;
	private javax.swing.JButton saveButton;
	private javax.swing.JButton solveButton;
	private javax.swing.JButton toPDFButton;
	// End of variables declaration//GEN-END:variables
}