package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;

public class Drive extends Command {
    private DriveSubsystem driveSubsystem;

    private double leftSpeed;
    private double rightSpeed;

    public Drive(DriveSubsystem driveSubsystem, double leftSpeed, double rightSpeed) {
        this.driveSubsystem = driveSubsystem;
        this.leftSpeed = leftSpeed;
        this.rightSpeed = rightSpeed;

        addRequirements(driveSubsystem);
    }

    @Override
    public void execute() {
        driveSubsystem.tankDrive(leftSpeed, rightSpeed);
    }

    @Override
    public void end(boolean interrupted) {
        driveSubsystem.stop();
    }

}
