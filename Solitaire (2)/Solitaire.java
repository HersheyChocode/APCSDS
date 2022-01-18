import java.util.*;

import javax.lang.model.util.ElementScanner6;

/**
 * This class represents the game of solitaire
 * @author Harshini Chaturvedula
 * @version Nov 2,
 */
public class Solitaire
{
    /**
     * This main method runs a new solitaire game
     * @param args in main method
     **/
    public static void main(String[] args)
    {
        new Solitaire();
    }

    private Stack<Card> stock;
    private Stack<Card> waste;
    private Stack<Card>[] foundations;
    private Stack<Card>[] piles;
    private SolitaireDisplay display;

    private boolean[] foundationClicked;

    /** 
     * This constructor intializes all private stacks to be empty stacks, 
     * as well as creating a stock
     * **/
    public Solitaire()
    {

        foundations = new Stack[4];
        piles = new Stack[7];

        stock = new Stack<Card>();
        waste = new Stack<Card>();
        for(int i = 0; i<foundations.length; i++) 
            foundations[i] = new Stack<Card>();
        for(int i = 0; i<piles.length; i++) 
            piles[i] = new Stack<Card>();
        this.createStock();
        this.deal();

        display = new SolitaireDisplay(this);
    }

    /**
     * This returns the card on top of the stock,
     * or null if the stock is empty.
     * @return the top card of the stock, or null if empty
     */
    public Card getStockCard()
    {
        if(!stock.isEmpty()) return stock.peek();
        return null;
    }

     /**
     * This returns the card on top of the waste,
     * or null if the waste is empty.
     * @return the top card of the waste, or null if empty
     */
    public Card getWasteCard()
    {
        if(!waste.isEmpty()) return waste.peek();
        return null;
    }

    /** @precondition:  0 <= index < 4
     * This returns the card on top of the given
     * foundation, or null if the foundation is empty.
     * @return the top card of the foundation at the index, or null if empty
     * @param index is the position of the desired foundation stack
    **/
    public Card getFoundationCard(int index)
    {
        if(!foundations[index].isEmpty()) return foundations[index].peek();
        return null;
    }

    /**  @precondition:  0 <= index < 7
     * This returns a reference to the given pile.
     * @return the index'th pile
     * @param index is the position of the desired pile 
    **/
    public Stack<Card> getPile(int index)
    {
        return piles[index];
    }

    /**
     * This method creates the stock.
     */
    public void createStock()
    {
        ArrayList<Card> deck = new ArrayList<Card>();
        String[] suits = {"c", "d", "h", "s"};
        for(String s: suits) 
        {
            for(int r = 1; r<=13; r++) 
            {
                deck.add(new Card(r,s));
            }
        }
        for(int i = 0; i<52; i++) 
        {
            int rand = (int) ((Math.random())*(52-i));
            stock.push(deck.remove(rand));
        }
    }

    /**
     * This class deals cards to the 7 stock piles. 
     */
    public void deal()
    {
        for(int i = 0; i<piles.length; i++)
        {
            for(int j = i; j<piles.length; j++) 
            {
                piles[j].push(stock.pop());
            }
        }
        for(int i = 0; i<piles.length; i++)
        {
            piles[i].peek().turnUp();
        }
    }

    /**
     * This method moves the top card of the stack to the waste pile.
     */
    public void dealOneCard()
    {
        waste.push(stock.pop());
        waste.peek().turnUp();
    }

    /**
     * This method moves the top card of the waste to the the stock
     * until the waste is empty.
     */
    public void resetStock()
    {
        while(!waste.isEmpty())
        {
            stock.push(waste.pop());
            stock.peek().turnDown();
        }
    }

    /**
     * This method is called when the stock is clicked and either deals a card
     * or resets the stock. 
     **/
    public void stockClicked()
    {
        if(!display.isWasteSelected() && !display.isPileSelected()) 
        {
            if (!stock.isEmpty()) dealOneCard();
            else resetStock();
        } 
        System.out.println("stock clicked");
    }

    //called when the waste is clicked
    /**
     * This method selects the waste pile if unselected, and unselects it if already selected.
     */
    public void wasteClicked()
    {
        if(!waste.isEmpty()&&!display.isWasteSelected()&&!display.isPileSelected())
        {
            display.selectWaste();
        } 
        else if(display.isWasteSelected())
        {
            display.unselect();
        }
        System.out.println("waste clicked");
    }

    /**
     * This method can either move a selected card to the top of a foundation 
     * or do nothing depending on the card clicked.
     * @precondition 0 <= index < 4
     * @param index is the index of the foundations pointing to the foundation desired
     */
    public void foundationClicked(int index)
    {
        if(display.isPileSelected()) 
        {
            if(canAddToFoundation(piles[display.selectedPile()].peek(), index))
            {
                foundations[index].push(piles[display.selectedPile()].pop());
                display.unselect();
                boolean allClear = true;
                for(int i = 0; i<piles.length; i++)
                {
                    if(!piles[i].isEmpty()) allClear = false;
                }
                if(!waste.isEmpty()) allClear = false;
                if(!stock.isEmpty()) allClear = false;
                if(allClear) celebrate();
            }
        }
        else if(display.isWasteSelected())
        {
            if(canAddToFoundation(getWasteCard(), index))
            {
                foundations[index].push(waste.pop());
                display.unselect();
            }
        }
        else if(!display.isFoundationSelected() && !foundations[index].isEmpty())
        {
            display.selectFoundation(index);
        }
        else if(display.isFoundationSelected())
        {
            display.unselect();
        }
        System.out.println("foundation #" + index + " clicked");
    }

    /**
     * This method prints out a celebratory statement for when a player wins. 
     */
    private void celebrate()
    {
        System.out.println("Congratulations! You win!");
    }

    /**
     * This method moves the top card of the waste pile to the pile clicked.
     * @precondition 0<=index<7
     * @param index is the index in the array pointing to the pile clicked
     */
    public void pileClicked(int index)
    {
        if(display.isFoundationSelected())
        {
            if(canAddToPile(foundations[display.selectedFoundation()].peek(), index))
            {
                piles[index].push(foundations[display.selectedFoundation()].pop());
                display.unselect();
            }
        }
        else if(display.isPileSelected() && display.selectedPile()!=index)
        {
            Stack<Card> selectedPileCards = removeFaceUpCards(display.selectedPile());
            if(canAddToPile(selectedPileCards.peek(), index))
            {
                addToPile(selectedPileCards, index);
                display.unselect();
            }
            else
            {
                addToPile(selectedPileCards,display.selectedPile());
            }
        }
        else if(display.isWasteSelected() && canAddToPile(waste.peek(), index))
        {
            piles[index].push(waste.pop());
            display.unselect();
        }
        else if(!display.isWasteSelected() && !display.isPileSelected() && 
                !piles[index].isEmpty() && piles[index].peek().isFaceUp())
        {
            display.selectPile(index);
        }
        else if(!display.isWasteSelected() && !display.isPileSelected() && 
                 !piles[index].isEmpty() && !piles[index].peek().isFaceUp())
        {
            piles[index].peek().turnUp();
        }
        else if(display.isPileSelected())
            display.unselect();
        System.out.println("pile #" + index + " clicked");
    }

    /**
     * This method determines if a card can be validly added to a pile.
     * @precondition 0<=index<=index
     * @param card is the card selected
     * @param index is the index of the piles that points to the pile desired
     * @return true if the card can be legally moved to the top of the desired pile
     */
    private boolean canAddToPile(Card card, int index) 
    {
        if(!piles[index].isEmpty() && piles[index].peek().isFaceUp())
        {
            if(piles[index].peek().isRed()!=card.isRed()) 
            {
                if(card.getRank()==piles[index].peek().getRank()-1) return true;
            } 
            return false;
        }
        if(piles[index].isEmpty() && card.getRank()==13) return true;
        return false;
    }

    /**
     * This method removes all face-up cards on the top of 
     *  the given pile and returns a stack containing these cards.
     * @precondition 0 <= index < 7
     * @param index is the number of the pile desired
     * @return a stack containing all the face up cards of the pile
     */
    private Stack<Card> removeFaceUpCards(int index)
    {
        Stack<Card> ret = new Stack<Card>();
        while(!piles[index].isEmpty() && piles[index].peek().isFaceUp()) 
        {
            ret.push(piles[index].pop());
        }
        return ret;
    }

    /**
     * This method adds face up cards to a desired pile. 
     * @precondition 0 <= index < 7
     * @param cards is a pile of face up cards
     * @param index is the index of the piles that points to the pile desired
     */
    private void addToPile(Stack<Card> cards, int index)
    {
        while(!cards.isEmpty()) 
        {
            piles[index].push(cards.pop());
        }
    }

    /**
     * This method determines whether a card can be moven to a foundation.
     * @precondition 0 <= index < 4;
     * @param card is the given card
     * @param index is the index of the foundations pointing to the desired foundation
     * @return true if the given card can be legally moved to the top of the given foundation
     */
    private boolean canAddToFoundation(Card card, int index)
    {
        if(foundations[index].isEmpty()) 
        {
            if(card.getRank()==1) return true;
            return false;
        }
        else
        {
            String s = foundations[index].peek().getSuit();
            int r = foundations[index].peek().getRank();
            if(card.getSuit().equals(s) && card.getRank()==r+1)
            {
                return true;
            }
            return false;
        }
    }

}