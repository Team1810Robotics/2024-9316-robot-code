package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.Intake;
import frc.robot.commands.ShootSpeaker;
import frc.robot.commands.utility.Drive;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ScoreOfflineScore extends SequentialCommandGroup {

    
    public ScoreOfflineScore(ShooterSubsystem shooterSubsystem, IntakeSubsystem intakeSubsystem, DriveSubsystem driveSubsystem) {

        addCommands(
            //shoot initial note
            new ShootSpeaker(shooterSubsystem).withTimeout(2),

            new WaitCommand(1),
            //Kicks note into shoter
            new Intake(intakeSubsystem, false, true).withTimeout(.5),

            new WaitCommand(1),

            //Begin intaking again
            new Intake(intakeSubsystem, false, false),

            //Drives back, grabs note, drives forward
            new Drive(driveSubsystem, .5, .5, true).withTimeout(7),

            new WaitCommand(3),
        
            //Shoots
            new ShootSpeaker(shooterSubsystem),

            new WaitCommand(1),

            new Intake(intakeSubsystem, false, true)
        );

        
    }
    
}
