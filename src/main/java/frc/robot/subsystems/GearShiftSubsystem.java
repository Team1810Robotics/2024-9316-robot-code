package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class GearShiftSubsystem extends SubsystemBase {

    private Servo rightGearShifter;
    private Servo leftGearShifter;

    public GearShiftSubsystem() {
        leftGearShifter = new Servo(DriveConstants.LEFT_SHIFT_SERVO);

        rightGearShifter = new Servo(DriveConstants.RIGHT_SHIFT_SERVO);

    }

    public double shiftLeftUp() {
        leftGearShifter.setAngle(160);

        return leftGearShifter.getAngle();
    }

    public double shiftRightUp() {
        rightGearShifter.setAngle(20);

        return rightGearShifter.getAngle();
    }

    public double shiftLeftDown() {
        leftGearShifter.setAngle(20);

        return leftGearShifter.getAngle();
    }

    public double shiftRightDown() {
        rightGearShifter.setAngle(160);

        return rightGearShifter.getAngle();
    }


    
}
