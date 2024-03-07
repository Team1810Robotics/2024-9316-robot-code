package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;

public class Shooter extends Command {
    private ShooterSubsystem shooterSubsystem;

    private double shooterSpeed;

    public Shooter(double shooterSpeed, ShooterSubsystem shooterSubsystem) {
        this.shooterSpeed = shooterSpeed;
        this.shooterSubsystem = shooterSubsystem;

        addRequirements(shooterSubsystem);
    }

    @Override
    public void execute() {
        shooterSubsystem.shoot(shooterSpeed);
    }

    @Override
    public void end(boolean interrupted) {
        shooterSubsystem.stop();
    }
}
