package CrossWalk.AutoWork;

import CrossWalk.Const;
import CrossWalk.InitGame;
import CrossWalk.InitGraphic;
import CrossWalk.Object.MoveableObject.CarLtr;
import CrossWalk.Object.MoveableObject.Car;
import CrossWalk.Object.Line;
import CrossWalk.Object.MoveableObject.CarType;
import CrossWalk.Object.MoveableObject.CarRtl;
import CrossWalk.Menu.GameSetting;
import CrossWalk.StoreData.WriteReplyData;

public class CreateCarInNewGame extends CreateCar implements Runnable {

    public boolean SaveReply;
    public WriteReplyData WriteReplyData;

    public CreateCarInNewGame(boolean SaveReply) {
        super();

        this.SaveReply = SaveReply;
        if (SaveReply) {
            WriteReplyData temp = new WriteReplyData();
            this.WriteReplyData = temp;
            InitGraphic.Sheep.WriteReplyData = temp;
        }
    }

    @Override
    public void run() {
        while (true) {
            if (InitGame.GameStop) {
                continue;
            }
            if (InitGame.GameEnd) {
                break;
            }
            // Random int to select line for create new car 
            int randomLine = Const.RAND.nextInt(super.getLtrLineCount() + super.getRtlLineCount());

            // Get line with random int
            Line tempLine = super.getLine().get(randomLine);

            // Create car speed 
            int speed = tempLine.getMinCarSpeed() + Const.RAND.nextInt(tempLine.getMaxCarSpeed());

            // variables for instance car object
            CarType carType;
            Car newCar;

            // Check for direction 
            if (tempLine.getDirection() == Const.LINE_DIRECTION_RTL) {
                carType = new CarType(tempLine.getDirection());
                newCar = new CarRtl(speed, carType, tempLine);
            } else {
                carType = new CarType(tempLine.getDirection());
                newCar = new CarLtr(speed, carType, tempLine);
            }
            // Call create new car method of line 
            boolean temp = tempLine.createNewCar(newCar);

            if (SaveReply && temp) {
                WriteReplyData.appendCarsToFile(newCar);
            }

            // Sleep thread wait for create new car again
            try {
                Thread.sleep(Const.CREATE_CAR_SLEEP_TIME - GameSetting.getAutoCreateCarRate());
            } catch (Exception ex) {
                System.err.println("AutoCreateCar run() " + ex);
            }

        }
    }
//  //  private float[] InitPostion(int carWidth){
//        float[] position = new float[2];
//
//        int CarWidthRate = this.getDirection() == Const.LINE_DIRECTION_LTR
//                ? CarType.getCarWidth() * (-1)
//                : CarWidth;
//        return Position[0] + CarWidthRate;
//        
//        return ;
//    }
}