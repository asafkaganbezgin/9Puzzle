public class Tile
{
    private int value; // actual value of the tile which could be an integer from 1 to 9
    private int pos_x; // x axis position of the tile in the puzzle
    private int pos_y; // y axis location of the tile in the puzzle
    private boolean misplaced = true;

    /* default constructor */
    public Tile()
    {
    }

    /* creates dumb Tiles. Tiles which has the value -1 are dumb tiles
     * which are specifying the restricted areas of the puzzle. */
    public Tile(int y, int x)
    {
        value = -1;
        pos_y = y;
        pos_x = x;
    }

    /* tile constructor */
    public Tile(int number, int y, int x, boolean misplaced)
    {
        value = number;
        pos_y = y;
        pos_x = x;
        this.misplaced = misplaced;
    }

    /* returns the x position of the tile */
    public int getPosX()
    {
        return pos_x;
    }

    /* returns the y position of the tile */
    public int getPosY()
    {
        return pos_y;
    }

    /* returns the value of the tile */
    public int getValue()
    {
        return value;
    }

    public boolean getMisplaced()
    {
        return misplaced;
    }

    /* sets the x position of the tile */
    public void setPosX(int position)
    {
        pos_x = position;
    }

    /* sets the y position of the tile*/
    public void setPosY(int position)
    {
        pos_y = position;
    }

    /* sets the value of the */
    public void setValue(int val)
    {
        value = val;
    }

    public void setMisplaced(boolean value)
    {
        misplaced = value;
    }
}
