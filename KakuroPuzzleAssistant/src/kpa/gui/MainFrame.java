package kpa.gui;

import kpa.command.CellValueChangeCommand;
import kpa.command.UndoRedo;
import kpa.model.KCell;
import kpa.model.KPuzzle;
import kpa.solver.KakuroSolver;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Main frame for Kakuro Puzzle Assistant.
 *
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Default directory for loading of puzzles.
     */
    public static File DEFAULT_PUZZLE_DIRECTORY
            = new File(new File(".."), "puzzles");
    /**
     * The puzzle panel.
     */
    private final PuzzlePanel puzzlePanel;
    /**
     * The file chooser for open and save dialogs.
     */
    private final JFileChooser puzzleChooser = new JFileChooser();
    private UndoRedo undoRedoController;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem2;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem3;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemHighlight;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuEdit;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenu jMenuHelp;
    private javax.swing.JMenuItem jMenuItemAbort;
    private javax.swing.JMenuItem jMenuItemAbout;
    private javax.swing.JMenuItem jMenuItemApplyStrategies;
    private javax.swing.JMenuItem jMenuItemClear;
    private javax.swing.JMenuItem jMenuItemClearTextArea;
    private javax.swing.JMenuItem jMenuItemCopy;
    private javax.swing.JMenuItem jMenuItemDump;
    private javax.swing.JMenuItem jMenuItemHelp;
    private javax.swing.JMenuItem jMenuItemNew;
    private javax.swing.JMenuItem jMenuItemOpen;
    private javax.swing.JMenuItem jMenuItemQuit;
    private javax.swing.JMenuItem jMenuItemRedo;
    private javax.swing.JMenuItem jMenuItemRedoAll;
    private javax.swing.JMenuItem jMenuItemSaveAs;
    private javax.swing.JMenuItem jMenuItemSolve;
    private javax.swing.JMenuItem jMenuItemUndo;
    private javax.swing.JMenuItem jMenuItemUndoAll;
    private javax.swing.JMenu jMenuPuzzle;
    private kpa.gui.PuzzlePanel jPanelPuzzle;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItemEdit;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItemSolve;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItemView;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparatorEdit1;
    private javax.swing.JPopupMenu.Separator jSeparatorEdit2;
    private javax.swing.JPopupMenu.Separator jSeparatorFile1;
    private javax.swing.JTextArea jTextArea;
    /**
     * The puzzle being solved, or null if no puzzle loaded.
     */
    private KPuzzle puzzle = null;
    /**
     * Whether there are unsaved modifications to the puzzle.
     */
    private boolean unsavedModifications = false;
    /**
     * Background worker for background solver
     */
    private SwingWorker<Void, Void> background;
    /**
     * Listener will be passed to KakuroSolver to connect it with GUI
     */
    private RefreshListener listener;

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        puzzlePanel = jPanelPuzzle;
        initFrame();
        undoRedoController = new UndoRedo();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea = new javax.swing.JTextArea();
        jPanelPuzzle = new kpa.gui.PuzzlePanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuItemNew = new javax.swing.JMenuItem();
        jMenuItemOpen = new javax.swing.JMenuItem();
        jMenuItemSaveAs = new javax.swing.JMenuItem();
        jMenuItemDump = new javax.swing.JMenuItem();
        jSeparatorFile1 = new javax.swing.JPopupMenu.Separator();
        jMenuItemQuit = new javax.swing.JMenuItem();
        jMenuEdit = new javax.swing.JMenu();
        jMenuItemUndo = new javax.swing.JMenuItem();
        jMenuItemUndoAll = new javax.swing.JMenuItem();
        jMenuItemRedo = new javax.swing.JMenuItem();
        jMenuItemRedoAll = new javax.swing.JMenuItem();
        jMenuItemClear = new javax.swing.JMenuItem();
        jSeparatorEdit1 = new javax.swing.JPopupMenu.Separator();
        jMenuItemCopy = new javax.swing.JMenuItem();
        jMenuItemClearTextArea = new javax.swing.JMenuItem();
        jSeparatorEdit2 = new javax.swing.JPopupMenu.Separator();
        jRadioButtonMenuItemView = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItemSolve = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItemEdit = new javax.swing.JRadioButtonMenuItem();
        jMenuPuzzle = new javax.swing.JMenu();
        jCheckBoxMenuItemHighlight = new javax.swing.JCheckBoxMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItemApplyStrategies = new javax.swing.JMenuItem();
        jCheckBoxMenuItem3 = new javax.swing.JCheckBoxMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItemSolve = new javax.swing.JMenuItem();
        jMenuItemAbort = new javax.swing.JMenuItem();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem2 = new javax.swing.JCheckBoxMenuItem();
        jMenuHelp = new javax.swing.JMenu();
        jMenuItemHelp = new javax.swing.JMenuItem();
        jMenuItemAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea.setEditable(false);
        jTextArea.setColumns(20);
        jTextArea.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 13)); // NOI18N
        jTextArea.setRows(5);
        jScrollPane1.setViewportView(jTextArea);

        jPanelPuzzle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelPuzzleMouseClicked(evt);
            }
        });
        jPanelPuzzle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jPanelPuzzleKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanelPuzzleLayout = new javax.swing.GroupLayout(jPanelPuzzle);
        jPanelPuzzle.setLayout(jPanelPuzzleLayout);
        jPanelPuzzleLayout.setHorizontalGroup(
                jPanelPuzzleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 484, Short.MAX_VALUE)
        );
        jPanelPuzzleLayout.setVerticalGroup(
                jPanelPuzzleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );

        jMenuFile.setText("File");

        jMenuItemNew.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemNew.setText("New…");
        jMenuItemNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNewActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemNew);

        jMenuItemOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemOpen.setText("Open…");
        jMenuItemOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemOpenActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemOpen);

        jMenuItemSaveAs.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemSaveAs.setText("Save As…");
        jMenuItemSaveAs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSaveAsActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemSaveAs);

        jMenuItemDump.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemDump.setText("Dump");
        jMenuItemDump.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDumpActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemDump);
        jMenuFile.add(jSeparatorFile1);

        jMenuItemQuit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemQuit.setText("Quit");
        jMenuItemQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemQuitActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemQuit);

        jMenuBar1.add(jMenuFile);

        jMenuEdit.setText("Edit");
        jMenuEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuEditMouseClicked(evt);
            }
        });
        jMenuEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuEditActionPerformed(evt);
            }
        });

        jMenuItemUndo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemUndo.setText("Undo");
        jMenuItemUndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemUndoActionPerformed(evt);
            }
        });
        jMenuEdit.add(jMenuItemUndo);

        jMenuItemUndoAll.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemUndoAll.setText("Undo All");
        jMenuItemUndoAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemUndoAllActionPerformed(evt);
            }
        });
        jMenuEdit.add(jMenuItemUndoAll);

        jMenuItemRedo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemRedo.setText("Redo");
        jMenuItemRedo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRedoActionPerformed(evt);
            }
        });
        jMenuEdit.add(jMenuItemRedo);

        jMenuItemRedoAll.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemRedoAll.setText("Redo All");
        jMenuItemRedoAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRedoAllActionPerformed(evt);
            }
        });
        jMenuEdit.add(jMenuItemRedoAll);

        jMenuItemClear.setText("Clear");
        jMenuItemClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemClearActionPerformed(evt);
            }
        });
        jMenuEdit.add(jMenuItemClear);
        jMenuEdit.add(jSeparatorEdit1);

        jMenuItemCopy.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemCopy.setText("Copy");
        jMenuItemCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCopyActionPerformed(evt);
            }
        });
        jMenuEdit.add(jMenuItemCopy);

        jMenuItemClearTextArea.setText("Clear Messages");
        jMenuItemClearTextArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemClearTextAreaActionPerformed(evt);
            }
        });
        jMenuEdit.add(jMenuItemClearTextArea);
        jMenuEdit.add(jSeparatorEdit2);

        jRadioButtonMenuItemView.setText("View Mode");
        jRadioButtonMenuItemView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItemViewActionPerformed(evt);
            }
        });
        jMenuEdit.add(jRadioButtonMenuItemView);

        jRadioButtonMenuItemSolve.setText("Solve Mode");
        jRadioButtonMenuItemSolve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItemSolveActionPerformed(evt);
            }
        });
        jMenuEdit.add(jRadioButtonMenuItemSolve);

        jRadioButtonMenuItemEdit.setText("Edit Mode");
        jRadioButtonMenuItemEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItemEditActionPerformed(evt);
            }
        });
        jMenuEdit.add(jRadioButtonMenuItemEdit);

        jMenuBar1.add(jMenuEdit);

        jMenuPuzzle.setText("Puzzle");
        jMenuPuzzle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuPuzzleActionPerformed(evt);
            }
        });

        jCheckBoxMenuItemHighlight.setSelected(true);
        jCheckBoxMenuItemHighlight.setText("Highlight");
        jCheckBoxMenuItemHighlight.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxMenuItemHighlightItemStateChanged(evt);
            }
        });
        jMenuPuzzle.add(jCheckBoxMenuItemHighlight);
        jMenuPuzzle.add(jSeparator1);

        jMenuItemApplyStrategies.setText("Apply Strategies");
        jMenuItemApplyStrategies.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemApplyStrategiesActionPerformed(evt);
            }
        });
        jMenuPuzzle.add(jMenuItemApplyStrategies);

        jCheckBoxMenuItem3.setSelected(true);
        jCheckBoxMenuItem3.setText("Stop at First Change");
        jMenuPuzzle.add(jCheckBoxMenuItem3);
        jMenuPuzzle.add(jSeparator2);

        jMenuItemSolve.setText("Solve");
        jMenuItemSolve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSolveActionPerformed(evt);
            }
        });
        jMenuPuzzle.add(jMenuItemSolve);

        jMenuItemAbort.setText("Abort");
        jMenuItemAbort.setEnabled(false);
        jMenuItemAbort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAbortActionPerformed(evt);
            }
        });
        jMenuPuzzle.add(jMenuItemAbort);

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("Stop at First Solution");
        jMenuPuzzle.add(jCheckBoxMenuItem1);

        jCheckBoxMenuItem2.setSelected(true);
        jCheckBoxMenuItem2.setText("Dump Solutions");
        jMenuPuzzle.add(jCheckBoxMenuItem2);

        jMenuBar1.add(jMenuPuzzle);

        jMenuHelp.setText("Help");

        jMenuItemHelp.setText("Help");
        jMenuItemHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemHelpActionPerformed(evt);
            }
        });
        jMenuHelp.add(jMenuItemHelp);

        jMenuItemAbout.setText("About");
        jMenuItemAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAboutActionPerformed(evt);
            }
        });
        jMenuHelp.add(jMenuItemAbout);

        jMenuBar1.add(jMenuHelp);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanelPuzzle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jPanelPuzzle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE))
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemSaveAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSaveAsActionPerformed
        if (puzzle == null) {
            jTextArea.append("No puzzle to save.\n");
            return;
        }
        int result = puzzleChooser.showSaveDialog(this);
        if (result != JFileChooser.APPROVE_OPTION) {
            jTextArea.append("Save canceled by user.\n");
            return; // canceled or error
        }
        File puzzleFile = puzzleChooser.getSelectedFile();
        if (puzzleFile.exists()) {
            int response = JOptionPane.showConfirmDialog(this,
                    "Overwrite existing file?",
                    "Confirm Overwrite",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.CANCEL_OPTION) {
                jTextArea.append("Overwrite canceled by user.\n");
                return;
            }
        }
        // can write puzzleFile
        PrintWriter out;
        try {
            out = new PrintWriter(puzzleFile);
            out.print(puzzle);
            out.close();
            unsavedModifications = false;
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this,
                    "IO error while saving file: " + e,
                    "File Save Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItemSaveAsActionPerformed

    private void jMenuItemQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemQuitActionPerformed
        if (!confirmDiscard()) {
            return;
        }
        // Maybe do some finalization first
        System.exit(0);
    }//GEN-LAST:event_jMenuItemQuitActionPerformed

    private void jMenuItemOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemOpenActionPerformed
        if (!confirmDiscard()) {
            return;
        }
        int result = puzzleChooser.showOpenDialog(this);
        if (result != JFileChooser.APPROVE_OPTION) {
            jTextArea.append("Open canceled by user.\n");
            return; // canceled or error
        }
        File puzzleFile = puzzleChooser.getSelectedFile();
        final Scanner scanner;
        try {
            scanner = new Scanner(puzzleFile);
        } catch (FileNotFoundException e) {
            jTextArea.append("File not found:\n");
            jTextArea.append("  " + puzzleFile.getName() + "\n");
            jTextArea.append(e + "\n");
            return;
        }
        try {
            puzzle = new KPuzzle(scanner, puzzleFile.getName());
            this.setTitle("Kakuro Puzzle Assistant: " + puzzle.getName());
            jTextArea.append("Loaded puzzle from file "
                    + puzzle.getName() + "\n");
            jTextArea.append(puzzle.toString() + "\n");
            puzzlePanel.setPuzzle(puzzle);
            unsavedModifications = false;
            updateModeRadioButtons(KPuzzle.Mode.SOLVE);
            updateFrame();
        } catch (IllegalArgumentException e) {
            jTextArea.append("File does not contain a puzzle description:\n");
            jTextArea.append("  " + puzzleFile.getName() + "\n");
            jTextArea.append(e + "\n");
        }
    }//GEN-LAST:event_jMenuItemOpenActionPerformed

    private void jMenuItemNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNewActionPerformed
        jTextArea.append("New is not yet implemented.\n");
//        if (! confirmDiscard()) {
//            return;
//        }
//        Object[] possibleValues = { "4", "6", "8", "10", "12", "14" };
//        String value = (String)JOptionPane.showInputDialog(this,
//                "Select Size", "Input Size",
//                JOptionPane.INFORMATION_MESSAGE, null,
//                possibleValues, possibleValues[0]);
//        if (value == null || value.isEmpty()) {
//            return;
//        }
//        final int size = Integer.parseInt(value);
//        puzzle = new KPuzzle(new Scanner(
//                KPuzzle.makeEmptyDescriptor(size)),
//                "Untitled " + value + "x" + value);
//        this.setTitle("Kakuro Puzzle Assistant: " + puzzle.getName());
//        jTextArea1.append("Created new puzzle: " +
//                puzzle.getName() + "\n");
//        jTextArea1.append(puzzle.toString() + "\n");
//        puzzlePanel.setPuzzle(puzzle);
//        unsavedModifications = false;
//        updateModeRadioButtons(KPuzzle.Mode.EDIT);
//        updateFrame();
    }//GEN-LAST:event_jMenuItemNewActionPerformed

    private void jRadioButtonMenuItemViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItemViewActionPerformed
        updateModeRadioButtons(KPuzzle.Mode.VIEW);
    }//GEN-LAST:event_jRadioButtonMenuItemViewActionPerformed

    private void jRadioButtonMenuItemSolveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItemSolveActionPerformed
        updateModeRadioButtons(KPuzzle.Mode.SOLVE);
    }//GEN-LAST:event_jRadioButtonMenuItemSolveActionPerformed

    private void jRadioButtonMenuItemEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItemEditActionPerformed
        updateModeRadioButtons(KPuzzle.Mode.EDIT);
    }//GEN-LAST:event_jRadioButtonMenuItemEditActionPerformed

    private void jPanelPuzzleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelPuzzleMouseClicked
        if (evt.getClickCount() == 0) {
            return;
        }
        final KCell cell = puzzlePanel.mouseToCell(evt);
        if (cell == null) {
            return;
        }
        // cell != null

        if (cell != null & !cell.isBlocked()) {
            // clicked an unblocked cell
            this.puzzlePanel.setSelected(cell);
            jTextArea.append("Selected cell " + cell.getLocation().toString()
                    + "\n");
        } else {
            this.puzzlePanel.setSelected(null);
        }
        updateFrame();
        jPanelPuzzle.requestFocusInWindow();
    }//GEN-LAST:event_jPanelPuzzleMouseClicked

    private void jPanelPuzzleKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanelPuzzleKeyTyped
        jTextArea.append("Key typed: " + evt.getKeyChar() + "\n");
        if (puzzle == null) {
            return;
        }
        final KCell cell = this.puzzlePanel.getSelected();
        if (cell == null) {
            return;
        }
        // cell != null
        if (puzzle.getMode() != KPuzzle.Mode.SOLVE) {
            return;
        }

        // convert key typed to new state
        final char c = evt.getKeyChar();
        final int state;
        if ('1' <= c && c <= '9') {
            state = c - '0';
        } else if (c == '0' | c == ' ') {
            state = KCell.EMPTY;
        } else {
            return;
        }
        undoRedoController.did(new CellValueChangeCommand(cell, state));
        unsavedModifications = true;
        if (puzzle.isSolved()) {
            jTextArea.append("\n> > > Puzzle is SOLVED. < < <\n");
        }
        updateFrame();
    }//GEN-LAST:event_jPanelPuzzleKeyTyped

    private void jMenuItemDumpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDumpActionPerformed
        if (puzzle == null) {
            jTextArea.append("No puzzle to dump.\n");
            return;
        }
        jTextArea.append(puzzle.gridAsString());
    }//GEN-LAST:event_jMenuItemDumpActionPerformed

    private void jMenuItemCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCopyActionPerformed
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        String selectedText = jTextArea.getSelectedText();
        // Wrap text into StringSelection to be able to put it in system clipboard
        StringSelection selection = new StringSelection(selectedText);
        // Check the API for other parameter of setContents() method
        clipboard.setContents(selection, null);
    }//GEN-LAST:event_jMenuItemCopyActionPerformed

    private void jMenuItemClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemClearActionPerformed
        puzzle.clear();
        unsavedModifications = true;
        updateFrame();
    }//GEN-LAST:event_jMenuItemClearActionPerformed

    private void jMenuItemUndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemUndoActionPerformed
        undoRedoController.undo(true);
    }//GEN-LAST:event_jMenuItemUndoActionPerformed

    private void jMenuItemRedoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRedoActionPerformed
        undoRedoController.redo();
    }//GEN-LAST:event_jMenuItemRedoActionPerformed

    private void jMenuItemClearTextAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemClearTextAreaActionPerformed
        jTextArea.setText("");
    }//GEN-LAST:event_jMenuItemClearTextAreaActionPerformed

    private void jMenuItemUndoAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemUndoAllActionPerformed
        undoRedoController.undoAll(true);
    }//GEN-LAST:event_jMenuItemUndoAllActionPerformed

    private void jMenuItemRedoAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRedoAllActionPerformed
        undoRedoController.redoAll();
    }//GEN-LAST:event_jMenuItemRedoAllActionPerformed

    private void jMenuItemHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemHelpActionPerformed
        JOptionPane.showMessageDialog(this,
                new String[]{
                        "A Kakuro Puzzle consists of a rectangular grid of cells.",
                        "A group of horizontally or vertically adjacent emtpy cells",
                        "forms an entry.",
                        " ",
                        "Rule 1:",
                        "  Enter one positive digit (1 - 9) in each empty cell.",
                        " ",
                        "Rule 2:",
                        "  Within an entry, the digits must be distinct.",
                        " ",
                        "Rule 3:",
                        "  The sum of the digits in an entry is prescribed by its",
                        "  description, appearing on its left, of or above it.",
                        " ",
                        "Rule 4:",
                        "  There is exactly one solution."
                },
                "Help for Kakuro Puzzle Assistant", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jMenuItemHelpActionPerformed

    private void jMenuItemAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAboutActionPerformed
        JOptionPane.showMessageDialog(this,
                new String[]{
                        "Kakuro Solver",
                        "Copyright 2013"
                },
                "About Kakuro Puzzle Assistant", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jMenuItemAboutActionPerformed
    // End of variables declaration//GEN-END:variables

    private void jCheckBoxMenuItemHighlightItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemHighlightItemStateChanged
        updateFrame();
    }//GEN-LAST:event_jCheckBoxMenuItemHighlightItemStateChanged

    private void jMenuItemApplyStrategiesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemApplyStrategiesActionPerformed
        jTextArea.append("Apply Strategies is not yet implemented.\n");
    }//GEN-LAST:event_jMenuItemApplyStrategiesActionPerformed

    private void jMenuItemSolveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSolveActionPerformed

        listener = new RefreshListener() { //implementation of listener
            private boolean isCanceled = false;

            @Override
            public void refreshGUI() {
                updateFrame();
            }

            @Override
            public boolean isCanceled() {
                return isCanceled;
            }

            public void setCanceled() {
                isCanceled = true;
            }

        };

        background = new SwingWorker<Void, Void>() { //implementation of
            //background worker

            @Override
            protected Void doInBackground() throws Exception {
                jMenuItemSolve.setEnabled(false);
                jMenuItemAbort.setEnabled(true);
                jTextArea.append("Solving puzzle...\n");
                KakuroSolver solver = new KakuroSolver(puzzle, listener);
                solver.Solve(0);
                return null;
            }

            @Override
            protected void done() {
                try {
                    get();
                    jMenuItemSolve.setEnabled(true);
                    jMenuItemAbort.setEnabled(false);
                    jTextArea.append("Solved!\n");
                    updateFrame();
                } catch (Exception e) {
                    jTextArea.append("Cancelled by user.\n");
                    jMenuItemSolve.setEnabled(true);
                    jMenuItemAbort.setEnabled(false);
                }

            }

        };

        background.execute();


    }//GEN-LAST:event_jMenuItemSolveActionPerformed

    private void jMenuPuzzleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuPuzzleActionPerformed

    }//GEN-LAST:event_jMenuPuzzleActionPerformed

    private void jMenuEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuEditActionPerformed

    }//GEN-LAST:event_jMenuEditActionPerformed

    private void jMenuEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuEditMouseClicked
        if (undoRedoController.canRedo()) {
            jMenuItemRedo.setEnabled(true);
            jMenuItemRedoAll.setEnabled(true);
        } else {
            jMenuItemRedo.setEnabled(false);
            jMenuItemRedoAll.setEnabled(false);
        }

        if (undoRedoController.canUndo()) {
            jMenuItemUndo.setEnabled(true);
            jMenuItemUndoAll.setEnabled(true);
        } else {
            jMenuItemUndo.setEnabled(false);
            jMenuItemUndoAll.setEnabled(false);
        }
    }//GEN-LAST:event_jMenuEditMouseClicked

    private void jMenuItemAbortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAbortActionPerformed

        listener.setCanceled(); //set listener to Cancelled
        background.cancel(true); //cancel thread

    }//GEN-LAST:event_jMenuItemAbortActionPerformed

    /**
     * Completes initialization of this frame.
     */
    private void initFrame() {
        puzzleChooser.setCurrentDirectory(DEFAULT_PUZZLE_DIRECTORY);
        puzzle = null;
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Kakuro puzzle files", "zgr");
        puzzleChooser.setFileFilter(filter);
        this.setTitle("Kakuro Puzzle Assistant: No puzzle loaded");
        this.jTextArea.append("Open a puzzle file to start.\n");
        updateFrame();
    }

    /**
     * Updates this frame, that is, the visual state of controller, and repaints
     * the view.
     */
    private void updateFrame() {
        jMenuItemSaveAs.setEnabled(puzzle != null);
        jPanelPuzzle.setHighlight(
                jCheckBoxMenuItemHighlight.getState());
        jPanelPuzzle.invalidate();
        repaint();
    }

    /**
     * Confirms whether the unsaved modifications can be discarded.
     *
     * @return whether unsaved modifications can be discarded
     */
    private boolean confirmDiscard() {
        if (!unsavedModifications) {
            return true;
        }
        int response = JOptionPane.showConfirmDialog(this,
                "Discard unsaved modifications?",
                "Confirm Discard Unsaved Modifications",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        return response == JOptionPane.OK_OPTION;
    }

    /**
     * Updates the mode radio buttons in the Edit menu, and the puzzle's mode.
     *
     * @param mode the new mode
     */
    private void updateModeRadioButtons(final KPuzzle.Mode mode) {
        if (puzzle == null) {
            jRadioButtonMenuItemView.setSelected(false);
            jRadioButtonMenuItemSolve.setSelected(false);
            jRadioButtonMenuItemEdit.setSelected(false);
            return;
        }
        // puzzle != null
        jRadioButtonMenuItemView.setSelected(mode == KPuzzle.Mode.VIEW);
        jRadioButtonMenuItemSolve.setSelected(mode == KPuzzle.Mode.SOLVE);
        jRadioButtonMenuItemEdit.setSelected(mode == KPuzzle.Mode.EDIT);
        puzzle.setMode(mode);
        jTextArea.append("Mode changed to " + puzzle.getMode() + "\n");
    }

}