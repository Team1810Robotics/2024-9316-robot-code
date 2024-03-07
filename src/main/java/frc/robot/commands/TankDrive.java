package frc.robot.commands;

import java.util.function.DoubleSupplier;

import com.fasterxml.jackson.databind.ser.std.NumberSerializers.DoubleSerializer;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

public class TankDrive extends Command {
    private DriveSubsystem driveSubsystem;
    private IntakeSubsystem intakeSubsystem;

    private DoubleSupplier leftInput;
    private DoubleSupplier rightInput;

    public TankDrive(DoubleSupplier leftInput, DoubleSupplier rightInput, DriveSubsystem driveSubsystem, IntakeSubsystem intakeSubsystem) {
        this.leftInput = leftInput;
        this.rightInput = rightInput;
        this.driveSubsystem = driveSubsystem;
        this.intakeSubsystem = intakeSubsystem;

        addRequirements(driveSubsystem);
        addRequirements(intakeSubsystem);
    }

    @Override
    public void execute() {
        driveSubsystem.drive(rightInput.getAsDouble(), leftInput.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        driveSubsystem.stop();
    }
}
