package frc.team7052.robot.Commands.Drive;

import frc.team7052.robot.Commands.CommandBase;
import frc.team7052.robot.Enums.DrivingState;
import frc.team7052.robot.Systems.DriveTrain;
import frc.team7052.robot.Systems.OI;
import frc.team7052.robot.Constants;

public class DriveRobot extends CommandBase {
    OI oi;

    double prevZValue = 0;
    DrivingState drivingState = DrivingState.regular;
    public DriveRobot(OI oi) {
        this.oi = oi;
        driveTrain = DriveTrain.getInstance();
        requires(driveTrain);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        if (oi.buttonPressed(Constants.kButtonLeftJoystickPress)) {
            drivingState = DrivingState.turbo;
        }
        else {
            drivingState = DrivingState.regular;
        }
        if (Math.abs(oi.getLeftBumper()) > 0.01) {
            drivingState = DrivingState.careful;
        }
        else {
            drivingState = DrivingState.regular;
        }

        driveTrain.tankDrive(oi, drivingState);

    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void interrupted() {
        end();
    }

    @Override
    protected void end() {
        //stop driveTrain
        driveTrain.stop();
    }
}
