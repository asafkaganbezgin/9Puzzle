import java.lang.Math;
import java.util.*;

public class Puzzle extends Board
{
    static Queue<Board> states = new LinkedList<>();
    static ArrayList<Board> allStates = new ArrayList<Board>();

    public static void main(String []args)
    {
        int count = 0;
        int correctCount = 0;

        Board[] puzzles = new Board[25];
        puzzles[0] = generatePuzzle();
        puzzles[0].countMisplaced();
        System.out.println("Puzzle 1");
        puzzles[0].printPuzzle();
        System.out.println("Total number of misplaced tiles: " + puzzles[0].getHeuristicValue());
        System.out.println();
        System.out.println("#######################################");
        count++;
        for(int i = 1 ; i <= 24 ; i++)
        {
            correctCount = 0;
            Board puzzleToBeCompared = generatePuzzle();
            puzzleToBeCompared.countMisplaced();
            for(int j = 0; j < count; j++ )
            {
                if(compare(puzzles[j], puzzleToBeCompared))
                    correctCount++;
            }
            if(correctCount == count)
            {
                puzzles[i] = puzzleToBeCompared;
                System.out.println();
                System.out.println("Puzzle " + (i + 1));
                puzzles[i].printPuzzle();
                System.out.println("Total number of misplaced tiles: " + puzzles[i].getHeuristicValue());
                System.out.println();
                System.out.println("#######################################");
                count++;
                System.out.println();
            }
        }

        System.out.println("Solutions");
        solvePuzzle(puzzles[0],null,2);
    }

    public static boolean compare(Board board_1, Board board_2)
    {
        boolean notEqual = false;

        if(board_1.getTile(0,0).getValue() != board_2.getTile(0,0).getValue())
        {
            notEqual = true;
        }
        else
        {
            for(int i = 1 ; i <= 3 ; i++)
                for(int j = 0 ; j <= 2 ; j++)
                {
                    if(board_1.getTile(i, j).getValue() != board_2.getTile(i, j).getValue())
                        notEqual = true;
                }
        }
        return notEqual;
    }

    public static Board generatePuzzle()
    {
        int max = 4; // maximum value that the random variable could get
        int min = 1; // the minimum value that the random variable could get
        int range = max - min + 1; // the range of the random variable
        int random = 0; // integer value between 1 and 4 to make movement decisions

        Board puzzle = new Board();

//        System.out.println("Initial state of the puzzle. Not shuffled.");
//        puzzle.printPuzzle();

        /* Shuffling the puzzle */
        int yOfZero = 0;
        int xOfZero = 0;

        for(int i = 0 ; i < 100 ; i++)
        {
            random = (int)(Math.random() * range) + min;
            switch (random)
            {
                case 1:
                    if(xOfZero != 2 && yOfZero != 0)
                    {
                        puzzle.swapTiles(puzzle.getTile(yOfZero, xOfZero), puzzle.getTile(yOfZero, xOfZero + 1));
                        xOfZero++;
                    }
                    break;
                case 2:
                    if(yOfZero != 3)
                    {
                        puzzle.swapTiles(puzzle.getTile(yOfZero, xOfZero), puzzle.getTile(yOfZero + 1, xOfZero));
                        yOfZero++;
                    }
                    break;
                case 3:
                    if(xOfZero != 1 && xOfZero != 2 && yOfZero >= 1)
                    {
                        puzzle.swapTiles(puzzle.getTile(yOfZero, xOfZero), puzzle.getTile(yOfZero - 1, xOfZero));
                        yOfZero--;
                    } else if((xOfZero == 1 || xOfZero == 2) && yOfZero != 1)
                    {
                        puzzle.swapTiles(puzzle.getTile(yOfZero, xOfZero), puzzle.getTile(yOfZero - 1, xOfZero));
                        yOfZero--;
                    }
                    break;
                case 4:
                    if(xOfZero == 1 || xOfZero == 2)
                    {
                        puzzle.swapTiles(puzzle.getTile(yOfZero, xOfZero), puzzle.getTile(yOfZero, xOfZero - 1));
                        xOfZero--;
                    }
                    break;
            }
        }
        return puzzle;
    }

    public static void solvePuzzle(Board puzzle, Board puzzle_r, int w)
    {
        Board temp0 = new Board();
//        temp0 = copyPuzzle(puzzle);
        Board temp1 = new Board();
        temp1 = copyPuzzle(puzzle);
        Board temp2 = new Board();
        temp2 = copyPuzzle(puzzle);
        Board temp3 = new Board();
        temp3 = copyPuzzle(puzzle);
        Board temp4 = new Board();
        temp4 = copyPuzzle(puzzle);

        int pos_x1 = temp1.findX(0);
        int pos_y1 = temp1.findY(0);
        int pos_x2 = temp2.findX(0);
        int pos_y2 = temp2.findY(0);
        int pos_x3 = temp3.findX(0);
        int pos_y3 = temp3.findY(0);
        int pos_x4 = temp4.findX(0);
        int pos_y4 = temp4.findY(0);


        for(int i = 1 ; i <= 4 ; i++)
        {
            switch (i)
            {
                case 1:
                    if (pos_x1 != 2 && pos_y1 != 0)
                    {
                        temp1.swapTiles(temp1.getTile(pos_y1, pos_x1), temp1.getTile(pos_y1, pos_x1 + 1));
                        allStates.add(temp1);
                        temp1.countMisplaced();
                        System.out.println();
                        temp1.printPuzzle();
                        temp0 = copyPuzzle(temp1);
                    }
                    break;
                case 2:
                    if (pos_y2 != 3)
                    {
                        temp2.swapTiles(temp2.getTile(pos_y2, pos_x2), temp2.getTile(pos_y2 + 1, pos_x2));
                        allStates.add(temp2);
                        temp2.countMisplaced();
                        System.out.println();
                        temp2.printPuzzle();
                        temp0 = copyPuzzle(temp2);
                    }
                    break;
                case 3:
                    if (pos_x3 != 1 && pos_x3 != 2 && pos_y3 >= 1)
                    {
                        temp3.swapTiles(temp3.getTile(pos_y3, pos_x3), temp3.getTile(pos_y3 - 1, pos_x3));
                        allStates.add(temp3);
                        temp3.countMisplaced();
                        System.out.println();
                        temp3.printPuzzle();
                        temp0 = copyPuzzle(temp3);
                    } else if ((pos_x3 == 1 || pos_x3 == 2) && pos_y3 != 1)
                    {
                        temp3.swapTiles(temp3.getTile(pos_y3, pos_x3), temp3.getTile(pos_y3 - 1, pos_x3));
                        allStates.add(temp3);
                        temp3.countMisplaced();
                        System.out.println();
                        temp3.printPuzzle();
                        temp0 = copyPuzzle(temp3);
                    }
                    break;
                case 4:
                    if (pos_x4 == 1 || pos_x4 == 2)
                    {
                        temp4.swapTiles(temp4.getTile(pos_y4, pos_x4), temp4.getTile(pos_y4, pos_x4 - 1));
                        allStates.add(temp4);
                        temp4.countMisplaced();
                        System.out.println();
                        temp4.printPuzzle();
                        temp0 = copyPuzzle(temp4);
                    }
                    break;
            }
        }



        //*************************************************************************************

        if(puzzle_r != null)
        {
            Board temp20 = new Board();
//        temp20 = copyPuzzle(puzzle);
            Board temp21 = new Board();
            temp21 = copyPuzzle(puzzle_r);
            Board temp22 = new Board();
            temp22 = copyPuzzle(puzzle_r);
            Board temp23 = new Board();
            temp23 = copyPuzzle(puzzle_r);
            Board temp24 = new Board();
            temp24 = copyPuzzle(puzzle_r);

            int pos_xx1 = temp21.findX(0);
            int pos_yy1 = temp21.findY(0);
            int pos_xx2 = temp22.findX(0);
            int pos_yy2 = temp22.findY(0);
            int pos_xx3 = temp23.findX(0);
            int pos_yy3 = temp23.findY(0);
            int pos_xx4 = temp24.findX(0);
            int pos_yy4 = temp24.findY(0);

            for(int i = 1 ; i <= 4 ; i++)
            {
                switch (i)
                {
                    case 1:
                        if (pos_xx1 != 2 && pos_yy1 != 0)
                        {
                            temp21.swapTiles(temp21.getTile(pos_yy1, pos_xx1), temp21.getTile(pos_yy1, pos_xx1 + 1));
                            allStates.add(temp21);
                            temp21.countMisplaced();
                            System.out.println();
                            temp21.printPuzzle();
                            temp20 = copyPuzzle(temp21);
                        }
                        break;
                    case 2:
                        if (pos_yy2 != 3)
                        {
                            temp22.swapTiles(temp22.getTile(pos_yy2, pos_xx2), temp22.getTile(pos_yy2 + 1, pos_xx2));
                            allStates.add(temp22);
                            temp22.countMisplaced();
                            System.out.println();
                            temp22.printPuzzle();
                            temp20 = copyPuzzle(temp2);
                        }
                        break;
                    case 3:
                        if (pos_xx3 != 1 && pos_xx3 != 2 && pos_yy3 >= 1)
                        {
                            temp23.swapTiles(temp23.getTile(pos_yy3, pos_xx3), temp23.getTile(pos_yy3 - 1, pos_xx3));
                            allStates.add(temp23);
                            temp23.countMisplaced();
                            System.out.println();
                            temp23.printPuzzle();
                            temp20 = copyPuzzle(temp23);
                        } else if ((pos_xx3 == 1 || pos_xx3 == 2) && pos_yy3 != 1)
                        {
                            temp23.swapTiles(temp23.getTile(pos_yy3, pos_xx3), temp23.getTile(pos_yy3 - 1, pos_xx3));
                            allStates.add(temp23);
                            temp23.countMisplaced();
                            System.out.println();
                            temp23.printPuzzle();
                            temp20 = copyPuzzle(temp23);
                        }
                        break;
                    case 4:
                        if (pos_xx4 == 1 || pos_xx4 == 2)
                        {
                            temp24.swapTiles(temp24.getTile(pos_yy4, pos_xx4), temp24.getTile(pos_yy4, pos_xx4 - 1));
                            allStates.add(temp24);
                            temp24.countMisplaced();
                            System.out.println();
                            temp24.printPuzzle();
                            temp20 = copyPuzzle(temp24);
                        }
                        break;
                }
            }
        }

        //*****************************************************************************************

        System.out.print("Hueristic Values: ");
        Collections.sort(allStates);

        for(int i = 0; i < w ; i++)
        {
            states.add(allStates.get(i));
        }

        System.out.println(states);

        for(int j = 0 ; j < allStates.size() ; j++)
        {
            System.out.print(allStates.get(j).getHeuristicValue() + " ");
        }

        Board b2 = new Board();
        Board b1 = new Board();
        b1 = states.remove();
        b2 = states.remove();

        System.out.println("Printing B1 and B2" +
                "");

        b1.printPuzzle();
        System.out.println();
        b2.printPuzzle();
        System.out.println();


        if(w == 2)
        {
            System.out.println("Entered Recursive");
            //states.remove().printPuzzle();
            solvePuzzle(b1, b2, w);
          //  allStates.clear();
        }

        if(w == 3)
        {
        }

        if(temp0.getHeuristicValue() == 0)
            return;
    }
}
