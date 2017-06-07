package javagame;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javagame.Menu.GameSetting;
import javax.imageio.ImageIO;

public final class Sheep {

    private float[] PositionOfSheep;
    private float[] SheepSize;
    private int[] Rate;
    private BufferedImage[] ImageOfSheep;
    private int ImageStatus;
    private String ImageCode;

    public static boolean AutoMove = false;

    public String SaveToFileForResume() {
        return String.format("Sheep,%f,%f,%d,%d,%d,%s", getXPosition(), getYPosition(), Rate[0], Rate[1], ImageStatus, ImageCode);
    }

    public Sheep(float[] PositionOfSheep, int[] Rate, int ImageStatus, String ImageCode) {
        this.PositionOfSheep = PositionOfSheep;
        this.Rate = Rate;
        this.ImageStatus = ImageStatus;
        this.ImageCode = ImageCode;
        initImage();
    }

    public Sheep(int[] Rate, float PositionYOfSheep) {
        ImageCode = "A";
        initImage();
        this.Rate = Rate;
        this.PositionOfSheep = new float[]{GameSetting.getCrosswalkMiddlePosition() - getSheepWidth() / 2, PositionYOfSheep - getSheepHeight() / 2};
    }

    private void initImage() {
        try {
            ImageOfSheep = new BufferedImage[4];

            ImageOfSheep[0] = ImageIO.read(new File(Const.PATH + Const.SHEEP_PATH_IMAGE.replace("{0}", "A-Up")));
            ImageOfSheep[1] = ImageIO.read(new File(Const.PATH + Const.SHEEP_PATH_IMAGE.replace("{0}", "A-Down")));
            ImageOfSheep[2] = ImageIO.read(new File(Const.PATH + Const.SHEEP_PATH_IMAGE.replace("{0}", "A-Right")));
            ImageOfSheep[3] = ImageIO.read(new File(Const.PATH + Const.SHEEP_PATH_IMAGE.replace("{0}", "A-Left")));

            SheepSize = new float[]{ImageOfSheep[0].getWidth(), ImageOfSheep[0].getHeight()};
            
        } catch (IOException ex) {
            System.err.println("Sheep Sheep() " + ex);
        }
    }

    public float getXPosition() {
        return PositionOfSheep[0];
    }

    public float getYPosition() {
        return PositionOfSheep[1];
    }

    public void setRate(int[] rate) {
        Rate = rate;
    }

    public void setPositionX(float x) {
        PositionOfSheep[0] = x;
    }

    public int[] getRate() {
        return Rate;
    }

    public float getSheepWidth() {
        return SheepSize[0];
    }

    public float getSheepHeight() {
        return SheepSize[1];
    }

    public Image getImage() {
        return ImageOfSheep[ImageStatus];
    }

    public void goUp() {
        if (Const.TOP_MARGIN - getSheepWidth() >= getYPosition()) {
            return;
        }
        ImageStatus = 0;
        PositionOfSheep[1] -= Rate[1];
    }

    public void goDown() {
        if (((GameSetting.getLtrLineCount() + GameSetting.getRtlLineCount()) * Const.LINE_HEIGHT) + Const.TOP_MARGIN <= getYPosition()) {
            return;
        }
        ImageStatus = 1;
        PositionOfSheep[1] += Rate[1];
    }

    public void goRight() {
        if (GameSetting.getCrosswalkMiddlePosition() + Const.CROSSWALK_WIDTH / 2 <= PositionOfSheep[0] + getSheepWidth()) {
            return;
        }
        ImageStatus = 2;
        PositionOfSheep[0] += Rate[0];
    }

    public void goLeft() {
        if (GameSetting.getCrosswalkMiddlePosition() - Const.CROSSWALK_WIDTH / 2 >= PositionOfSheep[0]) {
            return;
        }
        ImageStatus = 3;
        PositionOfSheep[0] -= Rate[0];
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == 13) {
            // Stop Game Code
        }
        if (Sheep.AutoMove) {
            return;
        }
        if (keyCode == KeyEvent.VK_UP || keyCode == 87) {
            goUp();
            CheckLine();
        } else if (keyCode == KeyEvent.VK_DOWN || keyCode == 83) {
            goDown();
            CheckLine();
        } else if (keyCode == KeyEvent.VK_RIGHT || keyCode == 68) {
            goRight();
        } else if (keyCode == KeyEvent.VK_LEFT || keyCode == 65) {
            goLeft();
        }
        int line = (int) Math.floor((PositionOfSheep[1] - Const.TOP_MARGIN) / Const.LINE_HEIGHT) + 1;
        System.out.println(getXPosition() + "  " + getYPosition() + " " + line);
    }

    public void keyPressed(int keyCode) {
        if (keyCode == 87) {
            goUp();
            CheckLine();
        } else if (keyCode == 83) {
            goDown();
            CheckLine();
        } else if (keyCode == 68) {
            goRight();
        } else if (keyCode == 65) {
            goLeft();
        }

    }

    public void CheckLine() {
        Line.SheepCurrentLine = (int) Math.floor((PositionOfSheep[1] - Const.TOP_MARGIN) / Const.LINE_HEIGHT) + 1;
    }

//    public static int lineIdForCrash()
//    {
//        int check = (int) ((PositionOfSheep[1]+30)/100);
//        return check+1;
//    }
}
