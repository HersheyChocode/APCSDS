/**
 * This class represents a card in a deck of cards.
 * @author Harshini Chaturvedula
 * @version November 2, 2021
 */

public class Card 
{
    private int rank;
    private String suit;
    private boolean isFaceUp;

    /**
     * This method initializes the card to be face down 
     * and have a given rank and suit.
     * @param r represents the rank of the card
     * @param s represents the suit of the rank
     */
    public Card(int r, String s) 
    {
        rank = r;
        suit = s;
        isFaceUp = false; //face down by default
    }

    /**
     * This returns the rank of the card.
     * @return the integer rank
     */
    public int getRank() 
    {
        return rank;
    }
    /**
     * This returns the suit of the card.
     * @return the string suit
     */
    public String getSuit() 
    {
        return suit;
    }
    /**
     * This returns whether the card is red.
     * @return true if the suit is diamonds or hearts
     */
    public boolean isRed() 
    {
        return this.suit.equals("d") || this.suit.equals("h");
    }
    /**
     * This returns whether the card is face up.
     * @return true if the card is facing up.
     */
    public boolean isFaceUp()
    {
        return isFaceUp;
    }
    /**
     * This method makes the card face up.
     */
    public void turnUp()
    {
        this.isFaceUp = true;
    }
    /**
     * This method makes the card face down.
     */
    public void turnDown()
    {
        this.isFaceUp = false;
    }
    /**
     * This method returns the name of the gif file for visuals.
     * @return the name of the file desired based on the card
     */
    public String getFileName()
    {
        if(!this.isFaceUp) //facing down
        {
            return "cards/backapcsds.gif";
        } 
        else 
        {
            String r; //rank string value
            if(rank==1) r = "a";
            else if(rank==10) r = "t";
            else if(rank==11) r = "j";
            else if(rank==12) r = "q";
            else if(rank==13) r = "k";
            else r = String.valueOf(rank);
            String s; //suit string value
            if(suit.equals("c")) 
                s = "c.gif";
            else if(suit.equals("d")) 
                s = "d.gif";
            else if(suit.equals("h")) 
                s =  "h.gif";
            else //spade
                s =  "s.gif";
            return "cards/" + r + s;
        }
    }

    /*public String toString() 
    {
        return suit + " " + rank;
    }*/
}
