package byow.lab12;
import org.junit.Test;
import static org.junit.Assert.*;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 60;
    private static final int HEIGHT = 40;

    /** To draw a hexagon (side length s) at a given location (x, y)
     * Middle has two lines.
     * Top row: s tiles; total rows, 2 * s;
     * Corner cases when Location out of bound */
    public static void addHexagon(TETile[][] world, int s, int x, int y) {
        Hexagon newHex = new Hexagon(s);
        TETile type = randomTile();
        drawHex(world, newHex, x, y, type);
    }

    private static TETile randomTile() {
        //long SEED = 2873123;
        Random RANDOM = new Random();
        int tileNum = RANDOM.nextInt(3);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.MOUNTAIN;
            default: return Tileset.GRASS;
        }
    }

    /** Helper: drawing method. Takes an hexagon class object, tile type */
    private static void drawHex(TETile[][] world, Hexagon hex, int xx, int yy, TETile t) {
        int startX = xx;
        int startY = yy;
        int sizeX = hex.sizeX();
        int sizeY = hex.sizeY();
        Boolean[][] drawTiles = hex.getTiles();

        // Copy Hex to the world
        for (int x = 0; x < sizeX; x += 1) {
            for (int y = 0; y < sizeY; y += 1) {
                //Check boundaries
                if ((x + startX) >= WIDTH || (y + startY) >= HEIGHT) { break;
                } else if (drawTiles[x][y].equals(true)) {
                    world[x + startX][y + startY] = t;
                }// else {
                   // world[x + startX][y + startY] = Tileset.NOTHING;
                }
            }
        }



    /** Hexagon: number of sides, */
    private static class Hexagon {
        int sides;
        Boolean[][] tiles; //True: to draw, False: not to draw
        Hexagon(int s) {
            sides = s;

            // Problem: X Y switched. New program to transpose it.
            /*
            tiles = new Boolean[s * 2][longline(s)];
            //Generate the top half of the Array, s rows
            for (int i = 0; i < s; i++) {
                for(int j = 0; j < s - 1 - i; j++) { //first several blanks
                    tiles[i][j] = false;
                }
                for(int j = s - 1 - i; j < longline(s) - (s - 1 - i); j++) { //several tiles
                    tiles[i][j] = true;
                }
                for(int j = longline(s) - (s - 1 - i); j < longline(s); j++) { //last several blanks
                    tiles[i][j] = false;
                }
            }

            //The bottom Half, s rows
            for (int i = s; i < 2 * s; i++) {
                tiles[i] = tiles[2 * s - 1 - i];
            }
            */


            tiles = new Boolean[longline(s)][s * 2];
            //Generate the bottom half of the Array, s rows
            /** The below code X, Y is right now */
            for (int i = 0; i < s; i++) {
                for(int j = 0; j < s - 1 - i; j++) { //first several blanks
                    tiles[j][i] = false;
                }
                for(int j = s - 1 - i; j < longline(s) - (s - 1 - i); j++) { //several tiles
                    tiles[j][i] = true;
                }
                for(int j = longline(s) - (s - 1 - i); j < longline(s); j++) { //last several blanks
                    tiles[j][i] = false;
                }
            }

            //Copy to the next half
            for (int i = 0; i < longline(s); i++) {
                for (int j = 0; j < s; j++) {
                    tiles[i][2 * s - 1 - j] = tiles[i][j];
                }
            }


        }
        Boolean[][] getTiles() {
            return tiles;
        }
        int sizeX() {
            return longline(sides);
        }
        int sizeY() {
            return 2 * sides;
        }
        int side() {
            return sides;
        }
    }

    private static int longline(int s) {
        int longline = s;
        for (int row = 2; row <= s; row++) {
            longline += 2;
        }
        return longline;
    }


    /** to draw 19 sides = 3 hex */
    public static void draw19(TETile[][] world) {
        int s= 3;
        int x = 0;
        int y = 2 * s;
        for (int i = 0; i < 3; i++) {
            addHexagon(world, s, 0, y); // 6 is 2 * 3(sides)
            y = y + 2 * s;
        }
     }

    public static void main(String[] args) {

        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] world = new TETile[WIDTH][HEIGHT];

        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        //addHexagon(world, 3, 0, 12);
        draw19(world);

        ter.renderFrame(world);
        }

}
