package frc.robot.commands;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;


public class TankDrive extends Command {
    private DriveSubsystem driveSubsystem;

    private DoubleSupplier leftInput;
    private DoubleSupplier rightInput;

    /**
     * 
      Ave Maria
      Gratia plena
      Maria, gratia plena
      Maria, gratia plena
      Ave, ave dominus
      Dominus tecum
      Bendicta tu in mulieribus
      Et benedictus
      Et benedictus fructus ventris
      Ventris tui, Iesus
      Ave Maria
      Ave Maria
      Maiden mild
      I listen to a maidens prayer
      For thou canst hear amid the wild
      'Tis thou, 'tis thou canst save me amid despair
      We slumber safely 'til the morrow
      Though e'en by men outcast reviled
      Oh, maiden
      See a maiden sorrow
      Oh, mother hear a suppliant child
      Ave Maria

      @param leftInput value from left joystick
      @param rightInput value from right joystick
     */
    public TankDrive(DoubleSupplier leftInput, DoubleSupplier rightInput, DriveSubsystem driveSubsystem) {
        this.leftInput = leftInput;
        this.rightInput = rightInput;
        this.driveSubsystem = driveSubsystem;


        addRequirements(driveSubsystem);
    }

    @Override
    public void execute() {
        driveSubsystem.teleopDrive(rightInput.getAsDouble(), leftInput.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        driveSubsystem.stop();
    }
}
