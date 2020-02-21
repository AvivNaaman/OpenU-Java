/**
 * a Class for PolyNode linked-list manipulation representing Polynomial.
 *
 * @author Aviv Naaman
 * @version 2020a
 */
public class Polynom
{
    /* Instance Variables */
    private PolyNode _head;
    
    /* Constructors */
    /**
     * Creates a new empty Polynom list
     * Complexity: Time O(1) Space O(1)
     */
    public Polynom() {
        _head = null;
    }
    
    /**
     * Creates a new Polynom list and sets p as the head of it
     * Complexity: Time O(1) Space O(1)
     * @param p The head of the list.
     */
    public Polynom(PolyNode p) {
        _head = p;
    }
    
    /* Getters */
    /**
     * Returns the head of the Polynom list
     * Complexity: Time O(1) Space O(1)
     * @return The head of the Polynom list
     */
    public PolyNode getHead() {
        return this._head;
    }
    
    /* Setters */
    /**
     * Sets the head of the Polynom list
     * Complexity: Time O(1) Space O(1)
     * @param p The head to be set
     */
    public void setHead(PolyNode p) {
        this._head = p;
    }
    
    /* Methods */
    /**
     * Inserts the node to the Polynom list.
     * if power of PolyNode alreay in the list, the coefficients are summed.
     * otherwise, inserts it to the list while keeping the list sorted by power descending.
     * Complexity: Time O(n) Space O(1)
     * @param p The PolyNode to add to the list
     * @return This Polynom list
     */
    public Polynom addNode(PolyNode p) {
        if (p == null || p.getCoefficient() == 0) return this; // nothing to do if empty
        PolyNode currNode = this._head;
        // if list empty, insert at head.
        if (currNode == null) {
            this._head = p;
        }
        else {
            boolean done = false; // flag if done
            while (!done) {
                // if same deg, sum coefficients
                done = true; // set to true, if not done will be set back to false
                if (currNode.getPower() < p.getPower()) {
                    this._head = p;
                    p.setNext(currNode);
                    done = true;
                }
                else if (currNode.getPower() == p.getPower()) { // sum coefficients if powers are same
                    currNode.setCoefficient(currNode.getCoefficient() + p.getCoefficient());
                }
                // if not last, and next not null
                else if (currNode.getNext() != null) {
                    if (currNode.getNext().getPower() < p.getPower()) {
                        p.setNext(currNode.getNext());
                        currNode.setNext(p);
                    }
                    else done = false; // we're not done...
                }
                else { // if last
                    currNode.setNext(p);
                }
                currNode = currNode.getNext();
            }
        }
        return this;
    }
    
    /**
     * Multiples the polynom by scalar,
     * means, multiple each coefficient in the polynom by the number
     * Complexity: Time O(n) Space O(1)
     * @param num The number to multiple by it's scalar
     * @return This Polynom list
     */
    public Polynom multByScalar(int num) {
        if (num == 1) return this; // A*1=A - nothing to do.
        // for each node
        PolyNode currNode = this._head;
        while (currNode != null) {
            // multiple the coefficient by num and set it.
            double newCoefficient = currNode.getCoefficient() * num;
            currNode.setCoefficient(newCoefficient);
            currNode = currNode.getNext();
        }
        return this;
    }
    
    /**
     * Adds the other Polynom list to this Polynom.
     * Complexity: Time O(n) Space O(1)
     * @param other The other Polynom
     * @return This Polynom list
     */
    public Polynom addPol(Polynom other) {
        if (other == null) return this;
        PolyNode currOtherNode = other.getHead();
        PolyNode currNode = this._head;
        PolyNode prevNode = null;
        // insert other values in place:
        while (currNode != null && currOtherNode != null) {
            if (currOtherNode.getCoefficient() == 0) // if coefficient 0, nothing to do.
                currOtherNode = currOtherNode.getNext();
            else if (currOtherNode.getPower() == currNode.getPower()) {
                currNode.setCoefficient(currOtherNode.getCoefficient() + currNode.getCoefficient());
                prevNode = currNode;
                currNode = currNode.getNext();
                currOtherNode = currOtherNode.getNext();
            }
            else if (currOtherNode.getPower() > currNode.getPower())
            {
                PolyNode newNode = new PolyNode(currOtherNode);
                if (prevNode != null)
                {
                    prevNode.setNext(newNode);
                    newNode.setNext(currNode);
                    prevNode = newNode;
                }
                else {
                    newNode.setNext(currNode);
                    this._head = newNode;
                    prevNode = newNode;
                }
                currOtherNode = currOtherNode.getNext();
            }
            else if (currOtherNode.getPower() < currNode.getPower() && // if currNode power is more than current of other
                    (currNode.getNext() == null || // and next of currnode if empty or has lower power than current of other
                            (currNode.getNext() != null && currNode.getNext().getPower() < currOtherNode.getPower()))) {
                // insert the other between currNode & currNode.getNext():
                PolyNode newNode = new PolyNode(currOtherNode);
                newNode.setNext(currNode.getNext());
                currNode.setNext(newNode);
                prevNode = currNode;
                currNode = newNode;
                currOtherNode = currOtherNode.getNext();
            }
            else {
                currNode = currNode.getNext();
            }
        }
        // add all left in other Polynom
        while (currOtherNode != null) {
            PolyNode newNode = new PolyNode(currOtherNode);
            if (prevNode != null) {
                prevNode.setNext(newNode);
                prevNode = newNode;
            }
            else {
                this._head = newNode;
                prevNode = newNode;
            }
            currOtherNode = currOtherNode.getNext(); // cotinue in other polynom
        }
        return this;
    }
    
    /**
     * Multiplies this Polynom by the other.
     * @param other The Polynom to multiply this Polynom by.
     * @return This Polynom list.
     */
    public Polynom multPol(Polynom other) {
        // make no change if polynom empty.
        if (other == null || other.toString().equals("")) return this;
        // temp polynom
        Polynom tempPolynom = new Polynom();
        PolyNode currNode = this._head;
        while (currNode != null) {
            PolyNode otherNode = other.getHead();
            while (otherNode != null) {
                if (otherNode.getCoefficient() != 0) { // nothing to do if coefficient 0
                    // sum of powers and multiplication of coefficient
                    int power = otherNode.getPower() + currNode.getPower();
                    double coefficient = otherNode.getCoefficient() * currNode.getCoefficient();
                    PolyNode nodeToAdd = new PolyNode(power, coefficient);
                    tempPolynom.addNode(nodeToAdd);
                }
                otherNode = otherNode.getNext(); // continue other polynode
            }
            currNode = currNode.getNext(); // continue this node
        }
        
        // set head of temp to current head
        this._head = tempPolynom.getHead();
        return this;
    }
    
    /**
     * Calculates the derivative of this Polynom.
     * Complexity: Time O(n) Space O(1)
     * @return This Polynom list.
     */
    public Polynom differential() {
        PolyNode currNode = this._head;
        while (currNode != null) {
            // derivative is multiplication of power-1 by coefficient, in power of power-1
            double newCoefficient = currNode.getCoefficient() * currNode.getPower();
            currNode.setCoefficient(newCoefficient);
            int newPower = currNode.getPower() - 1;
            currNode.setPower(newPower);
            
            // there's no derivative for x^0 so skip it if exists.
            if (currNode.getNext() != null && currNode.getNext().getPower() == 0) {
                currNode.setNext(null);
                currNode = null;
            }
            else {
                currNode = currNode.getNext(); // continue
            }
        }
        return this;
    }
    
    /**
     * Returns a String representing the Polynom list.
     * for example: "5x^3-2x^4+x-5", "-9x^2+7x-54"
     * Complexity: Time O(n) Space O(1)
     * @return a String representing the Polynom.
     */
    public String toString() {
        String result = "";
        PolyNode currNode = this._head;
        // while there are nodes left
        while (currNode != null) {
            String nodeString = currNode.toString();
            // if not first node and not negative, add '+'
            if (!nodeString.equals("") && nodeString.charAt(0) != '-' && !result.equals("")) {
                result += "+";
            }
            result += nodeString;
            currNode = currNode.getNext(); // continue
        }
        return result;
    }
}
