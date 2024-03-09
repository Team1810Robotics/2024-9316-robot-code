package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;


public class TankDrive extends Command {
    private DriveSubsystem driveSubsystem;

    private DoubleSupplier leftInput;
    private DoubleSupplier rightInput;

    public TankDrive(DoubleSupplier leftInput, DoubleSupplier rightInput, DriveSubsystem driveSubsystem) {
        this.leftInput = leftInput;
        this.rightInput = rightInput;
        this.driveSubsystem = driveSubsystem;

        // Shuffleboard.getTab("drive").addNumber("Left Drive", leftInput);
        // Shuffleboard.getTab("drive").addNumber("Right Drive", rightInput);

        addRequirements(driveSubsystem);
    }

    @Override
    public void execute() {
        driveSubsystem.drive(leftInput.getAsDouble(), rightInput.getAsDouble());



    }

    @Override
    public void end(boolean interrupted) {
        driveSubsystem.stop();
    }
}
