package CrossWalk.StoreData;

import CrossWalk.Utilities.Const;
import CrossWalk.UI.InitGraphic;
import CrossWalk.Object.Car;
import CrossWalk.Object.CarLtr;
import CrossWalk.Object.Line;
import CrossWalk.Object.CarRtl;
import CrossWalk.Object.Sheep;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;
import CrossWalk.Menu.GameSetting;
import CrossWalk.Utilities.ExceptionWriter;

public class ResumeAndLoad {

    public ResumeAndLoad() {
        initForSaveGame();
    }

    public final void initForSaveGame() {
        File resumeFolder = new File(Const.ROOT_PATH + Const.RESUME_ROOT_PATH);
        if (!resumeFolder.exists()) {
            resumeFolder.mkdir();
        }
    }

    public boolean isExistResumeFile() {
        return (new File(Const.ROOT_PATH + Const.RESUME_LINE_PATH).exists()
                && new File(Const.ROOT_PATH + Const.RESUME_SHEEP_PATH).exists());
    }

    public ArrayList<Line> loadGame() {
        Scanner LinesReader = null;
        Scanner SheepReader = null;
        Scanner CarReader = null;

        try {
            LinesReader = new Scanner(new File(Const.ROOT_PATH + Const.RESUME_LINE_PATH));
            CarReader = new Scanner(new File(Const.ROOT_PATH + Const.RESUME_CAR_PATH));
            SheepReader = new Scanner(new File(Const.ROOT_PATH + Const.RESUME_SHEEP_PATH));

            GameSetting.setSettingPath(Const.RESUME_SETTING_PATH);
            

        } catch (FileNotFoundException ex) {
            new ExceptionWriter().write("ResumeAndLoad writeSheepDataToFile()", ex, false);
        }
        String nextLineFromReader;

        ArrayList<Line> lines = new ArrayList<>();

        if (CarReader != null && LinesReader != null && SheepReader != null) {
            // Read Lines
            while (LinesReader.hasNextLine()) {
                nextLineFromReader = LinesReader.nextLine();
                String[] temp = nextLineFromReader.split(",");
                lines.add(new Line(Integer.parseInt(temp[1]), Integer.parseInt(temp[2]), Integer.parseInt(temp[3]), "1".equals(temp[4]), Integer.parseInt(temp[5]), "1".equals(temp[6])));
            }

            while (CarReader.hasNextLine()) {
                nextLineFromReader = CarReader.nextLine();
                String[] temp = nextLineFromReader.split(",");
                Line tempLine = lines.get(Integer.parseInt(temp[4]) - 1);

                if (tempLine.getDirection() == Const.LINE_DIRECTION_LTR) {
                    tempLine.getCars().add(new CarLtr(Float.parseFloat(temp[1]), Float.parseFloat(temp[2]), temp[3], tempLine));
                } else {
                    tempLine.getCars().add(new CarRtl(Float.parseFloat(temp[1]), Float.parseFloat(temp[2]), temp[3], tempLine));
                }

            }

            String sheepString = SheepReader.nextLine();
            String[] sheepStringSplited = sheepString.split(",");
            InitGraphic.Sheep = new Sheep(new float[]{Float.parseFloat(sheepStringSplited[1]), Float.parseFloat(sheepStringSplited[2])},
                    new int[]{Integer.parseInt(sheepStringSplited[3]), Integer.parseInt(sheepStringSplited[4])},
                    Integer.parseInt(sheepStringSplited[5]), sheepStringSplited[6] 
                    , Integer.parseInt(sheepStringSplited[7]), Integer.parseInt(sheepStringSplited[8]) , Integer.parseInt(sheepStringSplited[9])
            );
            InitGraphic.Sheep.checkLine();

            SheepReader.close();
            LinesReader.close();
            CarReader.close();

            return lines;
        }

        return null;
    }

    private void writeLinesDataToFile(ArrayList<Line> lines) {
        PrintWriter writerForLine = null;
        PrintWriter writerForCars = null;

        try {
            writerForCars = new PrintWriter(Const.ROOT_PATH + Const.RESUME_CAR_PATH, "UTF-8");
            writerForLine = new PrintWriter(Const.ROOT_PATH + Const.RESUME_LINE_PATH, "UTF-8");

            GameSetting.writeSetting(Const.RESUME_SETTING_PATH);

        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            new ExceptionWriter().write("ResumeAndLoad writeLinesDataToFile()", ex, false);
        }
        for (Line tempLine : lines) {
            writerForLine.println(tempLine.toString());
            for (Car tempCar : tempLine.getCars()) {
                writerForCars.println(tempCar.toString());
            }
        }
        if (writerForLine != null) {
            writerForLine.close();
        }
        if (writerForCars != null) {
            writerForCars.close();
        }
    }

    private void writeSheepDataToFile() {
        PrintWriter writerForSheep = null;
        try {
            writerForSheep = new PrintWriter(Const.ROOT_PATH + Const.RESUME_SHEEP_PATH, "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            new ExceptionWriter().write("ResumeAndLoad writeSheepDataToFile()", ex, false);
        }
        writerForSheep.println(InitGraphic.Sheep.toString());
        writerForSheep.close();
    }

    public void SaveGameForResume(ArrayList<Line> line) {
        writeLinesDataToFile(line);
        writeSheepDataToFile();
    }
}