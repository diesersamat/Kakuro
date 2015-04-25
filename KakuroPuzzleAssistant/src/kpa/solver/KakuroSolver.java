package kpa.solver;

import kpa.command.CellValueChangeCommand;
import kpa.command.UndoRedo;
import kpa.gui.RefreshListener;
import kpa.model.KCell;
import kpa.model.KEntry;
import kpa.model.KPuzzle;

import java.util.*;

/**
 * Class for solving Kakuro puzzle.
 *
 */
public final class KakuroSolver {

    private final KPuzzle puzzle;
    private final RefreshListener listener;
    private ArrayList<ArrayList<ArrayList<Integer>>> allSolutions;

    /**
     *
     * @param puzzle puzzle from MainFrame
     */
    public KakuroSolver(KPuzzle puzzle, RefreshListener rListener) {
        this.puzzle = puzzle;
        puzzle.clear();
        this.listener = rListener;
        prepareListOfSolutions();
    }

    /**
     * Prepares all possible solutions for each KEntry
     */
    private void prepareListOfSolutions() {
        allSolutions = new ArrayList<>();
        puzzle.getEntries().sort(new KEntryComparator());
        //Sorting dramatically increase performance,
        //because of fantastic reducing of the recursive call number

        //HOWEVER, COMMENT THIS SORT LINE IF YOU WANT TO CHECK BACKGOUND TASK

        for (KEntry entry : puzzle.getEntries()) {
            ArrayList<ArrayList<Integer>> solutions = new ArrayList<>();
            solutions = generate(entry.getSpecification().getSum(),
                    entry.getSpecification().getLength(),
                    solutions);//generate all possible solutions for each KEntry
            allSolutions.add(solutions);
        }
    }

    /**
     * Solves Kakuro Puzzle, using prepared Solutions
     *
     * @param from mark for recursive algorithm to start with
     */
    public void Solve(int from) throws InterruptedException {

        if (puzzle.isSolved()) {
            return;
        }
        if (listener.isCanceled()) {
            throw new InterruptedException("Cancelled by user");
        }
        for (ArrayList<Integer> solution : allSolutions.get(from)) {
            UndoRedo undoRedoManager = new UndoRedo();
            if (canApplySolution(solution, from, undoRedoManager)) {
                listener.refreshGUI();
                //if we can apply solution, then
                if ((from + 1) < puzzle.getEntries().size()) {
                    //call recursive
                    Solve(from + 1);
                    if (puzzle.isSolved()) {
                        return;
                    }
                    if (listener.isCanceled()) {
                        throw new InterruptedException("Cancelled by user");
                    }
                    undoRedoManager.undoAll(false);
                } else {
                    //or check, if puzzle is solved
                    if (puzzle.isSolved()) {
                        return;
                    }
                    if (listener.isCanceled()) {
                        throw new InterruptedException("Cancelled by user");
                    }
                }
            }
        }
    }

    /**
     * Check if it is possible to place solution to the KEntry
     *
     * @param solution mark for recursive algorithm to start with
     * @param from current number of KEntry to check
     * @return {@code true} if can apply solution, {@code false} otherwise.
     */
    private boolean canApplySolution(ArrayList<Integer> solution, int from,
                                     UndoRedo undoRedoManager) {
        undoRedoManager.clear();
        int current_solution = 0;
        for (KCell cell : puzzle.getEntries().get(from)) {
            if (cell.getState() != 0
                    && cell.getState() != solution.get(current_solution)) {
                //If cell is not empty and filled with another number,
                //we can not apply solution
                undoRedoManager.undoAll(false);
                return false;
            } else if (cell.getState() == 0) {
                undoRedoManager.did(new CellValueChangeCommand(cell,
                        solution.get(current_solution)));
                //otherwise let's change cell's state
            }
            current_solution++;
        }
        if (!puzzle.getEntries().get(from).isValid()) {
            undoRedoManager.undoAll(false);
            return false;
        }
        return true;
    }

    /**
     * Swap two elements of {@code ArrayList<Integer>}
     *
     * @param a array to swap
     * @param i element to swap
     * @param j element to swap
     */
    private void swap(ArrayList<Integer> a, int i, int j) {
        int c;
        c = a.get(i);
        a.set(i, a.get(j));
        a.set(j, c);
    }

    /**
     * Creates all permutations
     *
     * @param a array to create permutations from
     * @param n current place for recursive call
     * @param permutations array to save permutations
     */
    private void permute(ArrayList<Integer> a, int n,
                         ArrayList<ArrayList<Integer>> permutations) {
        if (n == 1) {
            permutations.add(new ArrayList<>(a));
            return;
        }
        for (int i = 0; i < n; i++) {
            swap(a, i, n - 1);
            permute(a, n - 1, permutations);
            swap(a, i, n - 1);
        }
    }

    /**
     * Generates all solutions, extends solutions received from
     * {@code generateRecursive} with their permutations
     *
     * @param sum digit sum for extension
     * @param length number of digits for extension
     * @param allSolutions array to save solutions
     * @return all solutions
     */
    private ArrayList<ArrayList<Integer>> generate(int sum, int length,
                                                   ArrayList<ArrayList<Integer>> allSolutions) {
        List<Set<Integer>> solutions = new ArrayList<>();
        generateRecursive(new HashSet<Integer>(), sum, length, 1, solutions);
        allSolutions = new ArrayList<>();
        for (Set<Integer> solution : solutions) {
            permute(new ArrayList<>(solution),
                    solution.size(), allSolutions);
        }
        return allSolutions;
    }

    /**
     * Generates all ways (modulo order of digits) in which a given combination
     * can be extended by n distinct non-zero digits at least k and with sum s,
     * in lexicographic order (NOT ROBUST).
     *
     * @param c given combination to be extended
     * @param n number of digits for extension
     * @param s digit sum for extension
     * @param k lower bound for digits in extension
     * @pre null null null null null null null null     {@code 0 <= n && && 1 <= k &&
     *        (\forall i; i : c; i < k)}
     * @modifies None
     * @post each (sorted) way of extending {@code c} with {@code n} distinct
     * non-zero digits at least {@code k} and less than {@code RADIX}, with sum
     * {@code s}, has been reported to the registered observer exactly once, in
     * lexicographic order
     * @bound {@code n} (upper bound on number of recursive calls)
     */
    private void generateRecursive(Set<Integer> c, int s, int n, int k,
                                   List<Set<Integer>> solutions) {
        int min = ((2 * k + (n - 1)) * n) / 2;
        if (min > s) {
            return;
        }
        int max = ((2 * s - (n - 1)) * n) / 2;
        if (max < s) {
            return;
        }
        if (n == 0 && s == 0) {
            solutions.add(new HashSet<>(c));
        }
        if (n > 1) {
            for (int i = k; i <= 9; i++) {
                if (!c.contains(i)) {
                    //will call recursive,
                    //if not contains and less than previous added
                    c.add(i);
                    generateRecursive(c, s - i, n - 1, i + 1, solutions);
                    c.remove(i);
                }
            }
        } else {

            if (!c.contains(s) && n != 0 && s <= 9) {
                if (s >= k) {
                    c.add(s);
                    solutions.add(new HashSet<>(c)); //call,
                    // because partition completed
                    c.remove(s);
                }
            }
        }
    }

    /**
     *
     * Class {@code KEntryComparator} compares 2 {@code KEntry} to sort them in
     * descending order by length
     */
    private class KEntryComparator implements Comparator<KEntry> {

        @Override
        public int compare(KEntry a, KEntry b) {
            return a.getSpecification().getLength()
                    < b.getSpecification().getLength() ? -1
                    : a.getSpecification().getLength()
                    == b.getSpecification().getLength() ? 0 : 1;
        }
    }
}
