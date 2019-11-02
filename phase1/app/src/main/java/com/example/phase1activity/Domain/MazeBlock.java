package com.example.phase1activity.Domain;

public class MazeBlock extends MazeItem {

    /**
     * The MazeBlock or Wall above this MazeBlock
     */
    private MazeItem up;

    /**
     * The MazeBlock or Wall below this MazeBlock
     */
    private MazeItem down;

    /**
     * The MazeBlock or Wall to the right of this MazeBlock
     */
    private MazeItem right;

    /**
     * The MazeBlock or Wall to the left of this MazeBlock
     */
    private MazeItem left;

    /**
     * Initialize this MazeBlock at position (a, b)
     */
    MazeBlock(int a, int b) {
        super(a, b);
        up = null;
        down = null;
        right = null;
        left = null;
    }

    /**
     * Getter for MazeBlock.up
     *
     * @return up
     */
    MazeItem getUp() {
        return up;
    }

    /**
     * Setter for MazeBlock.up
     *
     * @param up The MazeBlock or Wall that will be above this MazeBlock
     */
    public void setUp(MazeItem up) {
        this.up = up;
    }

    /**
     * Getter for MazeBlock.down
     *
     * @return down
     */
    MazeItem getDown() {
        return down;
    }

    /**
     * Setter for MazeBlock.down
     *
     * @param down The MazeBlock or Wall that will be below this MazeBlock
     */
    void setDown(MazeItem down) {
        this.down = down;
    }

    /**
     * Getter for MazeBlock.right
     *
     * @return right
     */
    MazeItem getRight() {
        return right;
    }

    /**
     * Setter for MazeBlock.right
     *
     * @param right The MazeBlock or Wall that will be to the right of this MazeBlock
     */
    void setRight(MazeItem right) {
        this.right = right;
    }

    /**
     * Getter for MazeBlock.left
     *
     * @return left
     */
    MazeItem getLeft() {
        return left;
    }

    /**
     * Setter for MazeBlock.left
     *
     * @param left The MazeBlock or Wall that will be to the left of this MazeBlock
     */
    void setLeft(MazeItem left) {
        this.left = left;
    }

    /**
     * Creates a 'link' between two MazeBlocks that are above and below each other
     *
     * @param other The MazeBlock that will be linked to this MazeBlock
     */
    void createVertLink(MazeBlock other) {
        setUp(other);
        other.setDown(this);
    }

    /**
     * Creates a 'link' between two MazeBlocks that are to the left and right of each other
     *
     * @param other The MazeBlock that will be linked to this MazeBlock
     */
    void createHorzLink(MazeBlock other) {
        setLeft(other);
        other.setRight(this);
    }
}
