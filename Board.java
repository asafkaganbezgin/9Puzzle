import java.util.Collection;

public class Board extends Tile implements Comparable
{
    private Tile[][] board;
    private int heuristicValue = 0;

    public Board()
    {
        /* initializing the puzzle
         *  the form of the puzzle is
         *  0  -1  -1  -1
         *  0   0   0  -1
         *  0   0   0  -1
         *  0   0   0  -1
         *  the -1s are forbidden locations
         *  9 of 10 zeroes are going to filled
         *  with numbers from 1 to 9.
         * */
        board = new Tile[4][4];

        Tile dumb1 = new Tile(0, 1);
        board[0][1] = dumb1;

        Tile dumb2 = new Tile(0, 2);
        board[0][2] = dumb2;

        Tile dumb3 = new Tile(0, 3);
        board[0][3] = dumb3;

        Tile dumb4 = new Tile(1, 3);
        board[1][3] = dumb4;

        Tile dumb5 = new Tile(2, 3);
        board[2][3] = dumb5;

        Tile dumb6 = new Tile(3, 3);
        board[3][3] = dumb6;

        /* filling the puzzle in the desired form */
        Tile zero = new Tile(0, 0, 0, false);
        board[0][0] = zero;

        Tile one = new Tile(1, 1, 0, false);
        board[1][0] = one;

        Tile two = new Tile(2, 1, 1, false);
        board[1][1] = two;

        Tile three = new Tile(3, 1, 2, false);
        board[1][2] = three;

        Tile four = new Tile(4, 2, 0, false);
        board[2][0] = four;

        Tile five = new Tile(5, 2, 1, false);
        board[2][1] = five;

        Tile six = new Tile(6, 2, 2, false);
        board[2][2] = six;

        Tile seven = new Tile(7, 3, 0, false);
        board[3][0] = seven;

        Tile eight = new Tile(8, 3, 1, false);
        board[3][1] = eight;

        Tile nine = new Tile(9, 3, 2, false);
        board[3][2] = nine;
    }

    public void swapTiles(Tile swapped1, Tile swapped2)
    {
        int temp_y = swapped1.getPosY();
        int temp_x = swapped1.getPosX();
        int temp_val = swapped1.getValue();

        swapped1.setValue(swapped2.getValue());
        swapped2.setValue(temp_val);
    }

    /* decrementing the x axis value of the tile by 1 */
    public void moveTileLeft(Tile tile)
    {
        if(tile.getPosX() >= 1 && board[tile.getPosY()][tile.getPosX() - 1].getValue() == 0)
            swapTiles(tile, board[tile.getPosY()][tile.getPosX() - 1]);
    }

    /* incrementing the x axis value of the tile by 1 */
    public void moveTileRight(Tile tile)
    {
        if(tile.getPosX() <= 1 && board[tile.getPosY()][tile.getPosX() + 1].getValue() == 0)
        {
            swapTiles(tile, board[tile.getPosY()][tile.getPosX() + 1]);
    }   }

    /* decrementing the y axis value of the tile by 1 */
    public void moveTileUp(Tile tile)
    {
        if(tile.getPosY() >= 1 && board[tile.getPosY() - 1][tile.getPosX()].getValue() != -1 &&
                board[tile.getPosY() - 1][tile.getPosX()].getValue() == 0)
            swapTiles(tile, board[tile.getPosY() - 1][tile.getPosX()]);
    }

    /* decrementing the y axis value of the tile by 1 */
    public void moveTileDown(Tile tile)
    {
        if(tile.getPosY() <= 2 && board[tile.getPosY() + 1][tile.getPosX()].getValue() == 0)
            swapTiles(tile, board[tile.getPosY() + 1][tile.getPosX()]);
    }

    /* printing the puzzle on console */
    public void printPuzzle()
    {
        for(int i = 0 ; i < 4 ; i++)
        {
            for (int j = 0; j < 4; j++)
                System.out.print(board[i][j].getValue() + "   ");
            System.out.println();
        }
    }

    public int findX(int val)
    {
        int x_pos = 0;
        for(int i = 0 ; i < 4 ; i++)
            for(int j = 0 ; j < 4 ; j++)
            {
                if(board[i][j].getValue() == val)
                    x_pos = board[i][j].getPosX();
            }
        return x_pos;
    }

    public int findY(int val)
    {
        int y_pos = 0;
        for(int i = 0 ; i < 4 ; i++)
            for(int j = 0 ; j < 4 ; j++)
            {
                if(board[i][j].getValue() == val)
                    y_pos = board[i][j].getPosY();
            }
        return y_pos;
    }

    public Tile getTile(int pos_y, int pos_x)
    {
        return board[pos_y][pos_x];
    }

    public void setTile(int y, int x, int value)
    {
        this.board[y][x].setValue(value);
    }

    public void countMisplaced()
    {
        for(int i = 1 ; i <= 9 ; i++)
        {
            switch (i)
            {
                case 1:
                    if(board[1][0].getValue() != 1)
                    {
                        board[1][0].setMisplaced(true);
                        heuristicValue++;
                    }
                    break;
                case 2:
                    if(board[1][1].getValue() != 2)
                    {
                        board[1][1].setMisplaced(true);
                        heuristicValue++;
                    }
                    break;
                case 3:
                    if(board[1][2].getValue() != 3)
                    {
                        board[1][2].setMisplaced(true);
                        heuristicValue++;
                    }
                    break;
                case 4:
                    if(board[2][0].getValue() != 4)
                    {
                        board[2][0].setMisplaced(true);
                        heuristicValue++;
                    }
                    break;
                case 5:
                    if(board[2][1].getValue() != 5)
                    {
                        board[2][1].setMisplaced(true);
                        heuristicValue++;
                    }
                    break;
                case 6:
                    if(board[2][2].getValue() != 6)
                    {
                        board[2][2].setMisplaced(true);
                        heuristicValue++;
                    }
                    break;
                case 7:
                    if(board[3][0].getValue() != 7)
                    {
                        board[3][0].setMisplaced(true);
                        heuristicValue++;
                    }
                    break;
                case 8:
                    if(board[3][1].getValue() != 8)
                    {
                        board[3][1].setMisplaced(true);
                        heuristicValue++;
                    }
                    break;
                case 9:
                    if(board[3][2].getValue() != 9)
                    {
                        board[3][2].setMisplaced(true);
                        heuristicValue++;
                    }
                    break;
            }
        }
    }

    public int getHeuristicValue()
    {
        return heuristicValue;
    }

    public void setHeuristicValue(int heuristicValue)
    {
        this.heuristicValue = heuristicValue;
    }

    @Override
    public int compareTo(Object comparepuzz)
    {
        int compareh = ((Board) comparepuzz).getHeuristicValue();

        return this.heuristicValue - compareh;
    }

    @Override
    public String toString() {
        return "" + heuristicValue;
    }

    public static Board copyPuzzle(Board input)
    {
        Board temp = new Board();

        temp.setTile(0, 0, input.getTile(0,0).getValue());
        for(int i = 1 ; i <= 3 ; i++)
            for(int j = 0 ; j <= 2 ; j++)
            {
                temp.setTile(i, j, input.getTile(i, j).getValue());
            }

        return temp;
    }

    public static void main(String[] args)
    {
        Board b1 = new Board();
        Board b2 = new Board();

        b1.setTile(0,0,5);
        b1.setTile(1,0,7);
        b1.setTile(1,1,3);
        b1.setTile(1,2,9);
        b1.setTile(2,0,2);
        b1.setTile(2,1,4);
        b1.setTile(2,2,8);
        b1.setTile(3,0,1);
        b1.setTile(3,1,6);
        b1.setTile(3,2,0);

        System.out.println("Puzzle b1:");
        b1.printPuzzle();
        System.out.println();

        System.out.println("Puzzle b2:");
        b2.printPuzzle();

        System.out.println();

        b2 = copyPuzzle(b1);

        System.out.println("copied b1 into b2:");
        b2.printPuzzle();


    }

}
