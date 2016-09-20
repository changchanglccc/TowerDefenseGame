package model.critter;

import view.map.Position;
import view.map.Drawing;
import model.map.GameMap;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
/**
 * Critter moving behavior who implements ActionListener interface
 * 
 * @author yongpinggao
 * @version 1.0
 * @since 3/13/16
 */
public class CritterMovingBehavior implements ActionListener {
    private final int DELAY = 50;
    private Timer movingTimer;
    private int initialMoveSpeed;

    private Position currentPosition;
    private int nextIndex;
    private ArrayList<Integer> pathList;

    private int cols;
    private int entranceIndex;
    private boolean arrivedAtExit;
    /**
     * Set path list for critter.
     * @param pathList it represents a series of path
     */
    public void setPathList(ArrayList<Integer> pathList) {
        this.pathList = pathList;
    }
    /**
     * Constructor of CritterMovingBehavior class.
     * @param gameMap it represents which game map will be used
     * @param movingSpeed it represent moving speed of crietter
     */
    public CritterMovingBehavior(GameMap gameMap, int movingSpeed) {
        pathList = gameMap.findPathList();
        cols = gameMap.getmCols();
        entranceIndex = gameMap.findEntranceIndex();
        currentPosition = Drawing.indexToCoordinateConverter(entranceIndex, cols);
        nextIndex = entranceIndex;
        initialMoveSpeed = movingSpeed;
    }
    /**
     * Whether the critter arrive at Exit.
     * @return a boolean value
     */
    public boolean isArrivedAtExit() {
        return arrivedAtExit;
    }
    /**
     * Get path list
     * @return pathList which represents critter's path 
     */
    public ArrayList<Integer> getPathList() {
        return pathList;
    }
    /**
     * Get current position of critter
     * @return cuurentPosition of critter
     */
    public Position getCurrentPosition() {
        return currentPosition;
    }
    /**
     * Set currentPosition of critter.
     * @param currentPosition it represents current position of critter
     */
    public void setCurrentPosition(Position currentPosition) {
        this.currentPosition = currentPosition;
    }
    /**
     * Let critter move one step towards right.
     */
    private void moveRight() {
        currentPosition.setX(currentPosition.getX() + 1);
    }
    /**
     * Let critter move one step towards down.
     */
    private void moveDown() {
        currentPosition.setY(currentPosition.getY() + 1);
    }
    /**
     * Let critter move one step towards left.
     */
    private void moveLeft() {
        currentPosition.setX(currentPosition.getX() - 1);
    }
    /**
     * Let critter move one step towards up.
     */
    private void moveUp() {
        currentPosition.setY(currentPosition.getY() - 1);
    }
    /**
     * Get destination of critter.
     * @param index  it represents critter's current position
     * @return next index of move
     */
    private int getDestination(int index) {
        int iLeft = index - 1;
        int iRight = index + 1;
        int iDown = index + cols;
        int iUp = index - cols;
        int nextIndex;
        if (pathList.contains(iLeft)) {
            nextIndex = iLeft;
        } else if (pathList.contains(iRight)) {
            nextIndex = iRight;
        } else if (pathList.contains(iDown)) {
            nextIndex = iDown;
        } else if (pathList.contains(iUp)) {
            nextIndex = iUp;
        } else {
            return -1;
        }
        pathList.remove(new Integer(index));
        return nextIndex;
    }
    /**
     * Get moving timer
     * @return a Timer represents moving timer
     */
    public Timer getMovingTimer() {
        return movingTimer;
    }
    /**
     * recursion make it consecutive,following index
     * @param index it represents critter's current position
     */
    private void moveToIndex(int index) {
        Position position = Drawing.indexToCoordinateConverter(index, cols);
        int x = position.getX();
        int y = position.getY();

        if (currentPosition.getX() == x && currentPosition.getY() == y) {
            nextIndex = getDestination(Drawing.coordinateToIndexConverter(x, y ,cols));
            if (nextIndex != -1) {
                moveToIndex(nextIndex);
            } else {
                movingTimer.stop();
                arrivedAtExit = true;
            }
        } else {
            if (currentPosition.getY() > y) {
                moveUp();
            }
            else if (y > currentPosition.getY()) {
                moveDown();
            }
            else if (currentPosition.getX() > x) {
                moveLeft();
            }
            else if (x > currentPosition.getX()) {
                moveRight();
            }
        }
    }
    /**
     * Move method
     */
    public void move() {
        movingTimer = new Timer(DELAY - initialMoveSpeed, this);
        movingTimer.start();
    }
    /**
     * Override actionPerformed method,when event e happens, move to next index.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        moveToIndex(nextIndex);
    }
}
