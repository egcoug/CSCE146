/*
 * Ellen Coughliin
 * This program draws a Sierpinski Triangle using recursion and Java Swing.
 */

import java.awt.*;
import javax.swing.*;

public class SierpinskiTriangle extends Canvas {

    // Minimum side length to stop recursion
    private static final int MIN_SIDE_LENGTH = 4;

    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Sierpinski Triangle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.add(new SierpinskiTriangle());
        frame.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        // Define the initial triangle vertices
        int width = getWidth();
        int height = getHeight();
        int[] xPoints = {width / 2, 0, width};
        int[] yPoints = {0, height, height};

        // Draw the initial filled triangle
        g.setColor(Color.BLUE);
        g.fillPolygon(xPoints, yPoints, 3);

        // Start the recursive drawing of inverted triangles
        drawSierpinski(g, xPoints, yPoints);
    }

    // Recursive method to draw the Sierpinski Triangle
    private void drawSierpinski(Graphics g, int[] xPoints, int[] yPoints) {
        // Calculate side length to check if it reaches the stopping condition
        double sideLength = Math.hypot(xPoints[1] - xPoints[0], yPoints[1] - yPoints[0]);
        if (sideLength <= MIN_SIDE_LENGTH) {
            return; // Base case: Stop recursion when side length is too small
        }

        // Calculate midpoints of each side
        int midX1 = (xPoints[0] + xPoints[1]) / 2;
        int midY1 = (yPoints[0] + yPoints[1]) / 2;
        int midX2 = (xPoints[1] + xPoints[2]) / 2;
        int midY2 = (yPoints[1] + yPoints[2]) / 2;
        int midX3 = (xPoints[2] + xPoints[0]) / 2;
        int midY3 = (yPoints[2] + yPoints[0]) / 2;

        // Draw the inverted triangle in the middle
        g.setColor(Color.WHITE);
        g.fillPolygon(new int[]{midX1, midX2, midX3}, new int[]{midY1, midY2, midY3}, 3);

        // Recursively draw smaller triangles in each of the three remaining areas
        drawSierpinski(g, new int[]{xPoints[0], midX1, midX3}, new int[]{yPoints[0], midY1, midY3});
        drawSierpinski(g, new int[]{midX1, xPoints[1], midX2}, new int[]{midY1, yPoints[1], midY2});
        drawSierpinski(g, new int[]{midX3, midX2, xPoints[2]}, new int[]{midY3, midY2, yPoints[2]});
    }
}
