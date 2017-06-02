package javagame;

import java.util.ArrayList;

public abstract class Car {
    protected int Id;
    protected float[] Position;
    protected float Speed;
    protected CarType CarType;
    protected Line Line;
    protected final float changeSpeedForReach = 20f;

    //private long[] AchieveCrosswalkTime;
    //private Date CreatedDate;
    //private Date AccidentDate;
    
    public Car(int Id, float[] Position, int Speed, CarType CarType, Line Line) {
        this.Position = Position;
        this.Speed = Speed;
        this.CarType = CarType;
        this.Id = Id;
        setLine(Line);
    }

    public Car(int Id, float[] Position, int Speed, CarType CarType) {
        this.Position = Position;
        this.Speed = Speed;
        this.CarType = CarType;
        this.Id = Id;
    }

    public void setLine(Line line) {
        Line = line;
    }

    public CarType getCarType() {
        return CarType;
    }

    public float getHeadPosition() {
        return Position[0];
    }

    public float getEndPosition() {
        return Position[1];
    }

    public int getId() {
        return Id;
    }

    public Line getLine() {
        return Line;
    }

//    public float[] getAchieveCrosswalkPosistion() {
//        float StartPositionRate = 0f;
//        float crosswalkStartPositionRate = 0f;
//        float DistanceToCrosswalk = 0f;
//
//        if (Line.getDirection() == Const.LINE_DIRECTION_LTR) {
//            StartPositionRate = (-.5f) * Const.CROSSWALK_WIDTH;
//            crosswalkStartPositionRate = Const.CROSSWALK_WIDTH;
//            DistanceToCrosswalk = Const.CHANGE_SPEED_DISTANCE_TO_CROSSWALK;
//        } else {
//            StartPositionRate = (.5f) * Const.CROSSWALK_WIDTH;
//            crosswalkStartPositionRate = (-1) * Const.CROSSWALK_WIDTH;
//            DistanceToCrosswalk = (-1) * Const.CHANGE_SPEED_DISTANCE_TO_CROSSWALK;
//        }
//
//        float[] result = new float[2];
//        result[0] = Crosswalk.getMiddleOfPosition() + StartPositionRate - DistanceToCrosswalk;
//        result[1] = Crosswalk.getMiddleOfPosition() + StartPositionRate + crosswalkStartPositionRate + DistanceToCrosswalk;
//        return result;
//    }
//    public boolean IsIntheCrosswalk() {
//        float[] crosswalkPosition = Line.getCrosswalkPosition();
//        if(Line.getDirection() == Const.LINE_DIRECTION_LTR)
//        {
//            return ((Position[0] + CarType.getCarWidth() >= crosswalkPosition[0]&& (Position[0]+ CarType.getCarWidth() <= crosswalkPosition[1])) 
//                    || (Position[1]+ CarType.getCarWidth() >= crosswalkPosition[0] && Position[1]+ CarType.getCarWidth() <= crosswalkPosition[1]) );
//        }
//        else
//        {
//            return ((Position[0] <= crosswalkPosition[0] && (Position[0] >= crosswalkPosition[1]))
//                    || (Position[1] <= crosswalkPosition[0] && Position[1] >= crosswalkPosition[1]) );
//        }
//    }
//    public void setAchieveCrosswalkTime(long[] achieveCrosswalkTime) {
//        AchieveCrosswalkTime = achieveCrosswalkTime;
//    }
//    public final long[] calcuteAchieveCrosswalkTime() {
//        float[] crosswalkPosition = getAchieveCrosswalkPosistion();
//
//        long timeSinceToNow = Timer.DifferenceTime();
//        long[] Achieve = new long[2];
//
//        Achieve[0] = timeSinceToNow + (long) (crosswalkPosition[0] / Speed);
//        Achieve[1] = timeSinceToNow + (long) (crosswalkPosition[1] / Speed);
//
//        return Achieve;
//    }
//}
    
    public abstract boolean IsIntheCrosswalk();
    public abstract void MoveInLine();
 //   public abstract boolean ReachedFrontCar();
    
    
    
//        float tempSpeed = getSpeed();
//
//        if (Line.getDirection() == Const.LINE_DIRECTION_LTR) {
//            if (Position[0] >= 700) {
//                Line.Dispose(getId());
//                return;
//            }
//        } else if (Position[1] <= 0) {
//            Line.Dispose(getId());
//            return;
//        }
//
//        if (Line.getDirection() == Const.LINE_DIRECTION_LTR) {
//            Position[0] += tempSpeed * Const.SLEEP_TIME_RE_PAINTING / 1000;
//            Position[1] = Position[0] - CarType.getCarWidth();
//        } else {
//            Position[0] -= tempSpeed * Const.SLEEP_TIME_RE_PAINTING / 1000;
//            Position[1] = Position[0] + CarType.getCarWidth();
//        }
//
//    }

    private float getSpeedInCrosswalk() {
        return Speed * Const.CROSSWALK_CHANGE_SPEED_Rate;
    }

    public float getSpeed() {
        return (IsIntheCrosswalk()) ? getSpeedInCrosswalk() : Speed;
    }
    
    public float getSpeedV2(boolean flag)
    {
        return (flag==true)? getSpeed()-20 : getSpeed();
    }
    
    public void ChangeLine() {

    }
}
