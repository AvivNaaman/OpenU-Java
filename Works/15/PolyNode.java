
/**
 * a Class representing a node of Polynomial linked-list.
 *
 * @author Aviv Naaman
 * @version 2020a
 */
public class PolyNode
{
    /* Instance Variables */
    private int _power;
    private double _coefficient;
    private PolyNode _next;
    
    /* Constructors */
    /**
     * Create a new instance of PolyNode, by it's power and coefficient.
     * if power negative, sets it and coefficient to 0.
     * Complexity: Time O(1), Space O(1)
     * @param power The power of the PolyNode, if negative sets it and coefficient to 0.
     * @param coefficient The coefficient of the PolyNode.
     */
    public PolyNode(int power, double coefficient) {
        if (power < 0) {
            this._power = 0;
            this._coefficient = 0;
        }
        else {
            this._power = power;
            this._coefficient = coefficient;
        }
        this._next = null;
    }
    
    /**
     * Create a new instance of PolyNode, by it's power, coefficient and next PolyNode in list.
     * if power negative, sets it and coefficient to 0.
     * Complexity: Time O(1), Space O(1)
     * @param power The power of the PolyNode, if negative sets it and coefficient to 0.
     * @param coefficient The coefficient of the PolyNode.
     * @param next The next PolyNode in the list.
     */
    public PolyNode(int power, double coefficient, PolyNode next) {
        this(power, coefficient); // call power & coefficient ctor
        this._next = next;
    }
    
    /**
     * Copy Constructor - Create new PolyNode by other PolyNode values.
     * Complexity: Time O(1), Space O(1)
     * @param p p The other PolyNode to copy values from.
     */
    public PolyNode(PolyNode p) {
        // construct a new PolyNode by getting all p's values.
        this(p.getPower(), p.getCoefficient(), p.getNext());
    }
    
    /* Getters */
    /**
     * Returns the power of the PolyNode.
     * Complexity: Time O(1), Space O(1)
     * @return The power of the node.
     */
    public int getPower() {
        return this._power;
    }
    
    /**
     * Returns the coefficient of the PolyNode.
     * Complexity: Time O(1), Space O(1)
     * @return the coefficient of the node.
     */
    public double getCoefficient() {
        return this._coefficient;
    }
    
    /**
     * Returns the next PolyNode in list.
     * Complexity: Time O(1), Space O(1)
     * @return the next PolyNode in list.
     */
    public PolyNode getNext() {
        return this._next;
    }
    
    /* Setters */
    /**
     * Sets the power of the node only if not negative.
     * Complexity: Time O(1), Space O(1)
     * @param power The power to be set, if not negative.
     */
    public void setPower(int power) {
        if (power >= 0) {
            this._power = power;
        }
    }
    
    /**
     * Sets the coefficient of the node
     * Complexity: Time O(1), Space O(1)
     * @param coefficient The Coefficient to be set
     */
    public void setCoefficient (double coefficient) {
        this._coefficient = coefficient;
    }
    
    /**
     * Sets the next node in list.
     * Complexity: Time O(1), Space O(1)
     * @param next The next PolyNode to be set
     */
    public void setNext(PolyNode next) {
        this._next = next;
    }
    
    /* Methods */
    /**
     * Returns a string representing the PolyNode, 
     * if coefficient is 0 return empty, if 1 or -1 without it (preserving the sign),
     * and if power is 0, returns the coefficient, if 1 with x with no power, if more then that with power,
     * for example: "", "5x", "-7x^3", "-1", "-x", "x", "6.5"
     * Complexity: Time O(1), Space O(1)
     * @return a String representing the node.
     */
    public String toString() {
        String result = "";
        // if coefficient is 0, return empty ("").
        if (this._coefficient != 0) {
            if (this._power == 0) return _coefficient + "";
            // if coefficient is -1 add to the string '-'
            if (this._coefficient == -1 && this._power > 0) {
                result += "-";
            }
            // otherwise add the coefficient if not 1
            else if (this._coefficient != 1 && this._power > 0)
                result += "" + this._coefficient;
                
            // if power isn't 0, there's 'x'
            if (this._power > 0) {
                result += "x";
                // if power isn't 1, add it to the string with ^
                if (this._power > 1)
                    result += "^" + this._power;
            }
        }
        return result;
    }
}
