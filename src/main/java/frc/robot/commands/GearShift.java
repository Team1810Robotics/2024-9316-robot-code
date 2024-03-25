package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.GearShiftSubsystem;

public class GearShift extends Command {
    

    private GearShiftSubsystem gearShiftSubsystem;

    private boolean isShiftUp;

    /**
     * 
     * 
     * @param isShiftUp if true, shifts up
     */
    public GearShift(GearShiftSubsystem gearShiftSubsystem, boolean isShiftUp) {
        this.gearShiftSubsystem = gearShiftSubsystem;
        this.isShiftUp = isShiftUp;

        addRequirements(gearShiftSubsystem);
    }

    @Override
    public void execute() {
        if (isShiftUp) {
            gearShiftSubsystem.shiftLeftUp();
            gearShiftSubsystem.shiftRightUp();

            // System.out.println("ShiftingUp");
        } else {
            gearShiftSubsystem.shiftLeftDown();
            gearShiftSubsystem.shiftRightDown();
            
            // System.out.println("ShiftingDown");

        }
    }
    
}
