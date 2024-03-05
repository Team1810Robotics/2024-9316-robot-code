package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.DriveSubsystem;

public class TankDrive extends Command {

    private DoubleSupplier rightInput;
    private DoubleSupplier leftInput;
    private DriveSubsystem driveSubsystem;

    public TankDrive(DoubleSupplier leftInput, DoubleSupplier rightInput, DriveSubsystem driveSubsystem) {
        this.leftInput = leftInput;
        this.rightInput = rightInput;
        this.driveSubsystem = driveSubsystem;

        addRequirements(driveSubsystem);

    }

    @Override
    public void execute() {
        driveSubsystem.drive(
            deadband(leftInput.getAsDouble()), 
            deadband(rightInput.getAsDouble())
            );
        System.out.println("test");
    }
    
    public double deadband(double value) {
        if (Math.abs(value) >= DriveConstants.DEADBAND) {
            return (value - DriveConstants.DEADBAND) / (1 - DriveConstants.DEADBAND);
        }
        return 0;
    }

    @Override
    public void end(boolean interrupted) {
        driveSubsystem.stop();
    }
    
}
