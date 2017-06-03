package javagame;

import java.util.ArrayList;

public class CarRtl extends Car {

    public CarRtl(int Id, int Speed, CarType CarType, Line Line) {
        super(Id, new float[]{Const.GAME_WINDOWS_WIDTH,Const.GAME_WINDOWS_WIDTH + CarType.getCarWidth()}, Speed, CarType, Line);
    }

    public CarRtl(int Id, float[] Position, int Speed, CarType CarType) {
        super(Id, new float[]{Const.GAME_WINDOWS_WIDTH,Const.GAME_WINDOWS_WIDTH + CarType.getCarWidth()}, Speed, CarType);
    }

    @Override
    public boolean IsIntheCrosswalk() {
        float[] crosswalkPosition = super.Line.getCrosswalkPosition();
        return ((Position[0] <= crosswalkPosition[0] && (Position[0] >= crosswalkPosition[1]))
                || (Position[1] <= crosswalkPosition[0] && Position[1] >= crosswalkPosition[1]));

    }

    @Override
    public void MoveInLine() {
       
        
        float tempSpeed= getSpeedV2(false);
        
        if (Position[1] < 0) {
            Line.Dispose(Line.getCars().indexOf(this));
            return;
        }
        
        if(this.getLine().getCars().size()>=2){
            
            int id1 = this.getId();
            int id2 = id1 - 1;

           // int lineid1 = this.getLine().getId();
           //int lineid2 = Line.getLineIdByCar(Line.getCars().get(id2));

            if(this.getLine().getDirection()==Const.LINE_DIRECTION_RTL)
            {
            
                if(this.getLine().getCars().get(id1-2).getHeadPosition() <= this.getLine().getCars().get(id2-2).getEndPosition() + Const.CHANGE_SPEED_DISTANCE_FOR_REACH)
                {
                     tempSpeed = getSpeedV2(true);
                }
                else {
                     tempSpeed = getSpeedV2(false);
                }
                    
            }
        }
         

        Position[0] -= tempSpeed * Const.SLEEP_TIME_RE_PAINTING / 1000;
        Position[1] = Position[0] + CarType.getCarWidth();
    }
}
