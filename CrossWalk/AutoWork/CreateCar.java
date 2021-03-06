package CrossWalk.AutoWork;

import CrossWalk.Utilities.Const;
import CrossWalk.Object.Line;
import java.util.ArrayList;
import CrossWalk.Menu.GameSetting;

public abstract class CreateCar implements Runnable {

    private ArrayList<Line> Lines;
    private final int RtlLineCount;
    private final int LtrLineCount;

    public CreateCar() {
        Lines = new ArrayList<>();
        this.LtrLineCount = GameSetting.getLtrLineCount();
        this.RtlLineCount = GameSetting.getRtlLineCount();
    }
    
    //create new lines with specified directions 
    public void InitLine() {
        boolean canCarTakeOver;

        if (GameSetting.getChangedLinesDirections() == 0) {
            for (int i = 1; i <= RtlLineCount; i++) {
                canCarTakeOver = i != RtlLineCount;
                Lines.add(new Line(i, (i + 1), (i), Const.LINE_DIRECTION_RTL, (i - 1) * Const.LINE_IMAGE_HEIGHT + Const.TOP_MARGIN, canCarTakeOver));
            }

            for (int i = RtlLineCount + 1, j = LtrLineCount; i <= RtlLineCount + LtrLineCount; i++, j--) {
                canCarTakeOver = i != RtlLineCount + 1;
                Lines.add(new Line(i, (j + 1), (j), Const.LINE_DIRECTION_LTR, (i - 1) * Const.LINE_IMAGE_HEIGHT + Const.TOP_MARGIN + Const.MIDDLE_LINE_IMAGE_HEIGHT, canCarTakeOver));
            }
        } else {
            for (int i = 1; i <= LtrLineCount; i++) {
                canCarTakeOver = (i != 1);
                Lines.add(new Line(i, (i + 1), (i), Const.LINE_DIRECTION_LTR, (i - 1) * Const.LINE_IMAGE_HEIGHT + Const.TOP_MARGIN, canCarTakeOver));
            }

            for (int i = LtrLineCount + 1, j = RtlLineCount; i <= RtlLineCount + LtrLineCount; i++, j--) {
                canCarTakeOver = (i != RtlLineCount + LtrLineCount);
                Lines.add(new Line(i, (j + 1), (j), Const.LINE_DIRECTION_RTL, (i - 1) * Const.LINE_IMAGE_HEIGHT + Const.TOP_MARGIN + Const.MIDDLE_LINE_IMAGE_HEIGHT, canCarTakeOver));
            }
        }
    }
    //create new lines from resume of saved games
    public void InitLineWithCars(ArrayList<Line> line) {
        InitLine();

        line.stream().forEach((tempLine) -> {
            tempLine.getCars().stream().forEach((car) -> {
                car.setLine(Lines.get(car.getLine().getId() - 1));
                Lines.get(car.getLine().getId() - 1).getCars().add(car);
            });
        });
    }

    // Query method for all lines
    public ArrayList<Line> getLine() {
        return Lines;
    }

    public int getRtlLineCount() {
        return RtlLineCount;
    }

    public int getLtrLineCount() {
        return LtrLineCount;
    }

    // Setter method
    public final void setLine(ArrayList<Line> line) {
        Lines = line;
    }

    @Override
    public abstract void run();
}
