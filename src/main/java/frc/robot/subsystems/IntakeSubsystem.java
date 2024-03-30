package frc.robot.subsystems;


import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class IntakeSubsystem extends SubsystemBase {

    //Class for spike
    private Relay leftHorzontialIntakeMotors;
    private Relay rightHorzontialIntakeMotors;
    private Relay verticalIntakeMotors;

    //Class for sensors
    private DigitalInput externalNoteDetector;
    private DigitalInput internalNoteDetector;
    private DigitalInput leftVerticalIntakeSensor;
    private DigitalInput rightVerticalIntakeSensor;

    

    public IntakeSubsystem() {
        leftHorzontialIntakeMotors = new Relay(IntakeConstants.LEFT_INTAKE_MOTORS);
        rightHorzontialIntakeMotors = new Relay(IntakeConstants.RIGHT_INTAKE_MOTORS);
        verticalIntakeMotors = new Relay(IntakeConstants.VERTICAL_INTAKE_MOTORS);

        externalNoteDetector = new DigitalInput(IntakeConstants.EXTERNAL_SENSOR);
        internalNoteDetector = new DigitalInput(IntakeConstants.INTERNAL_SENSOR);
        leftVerticalIntakeSensor = new DigitalInput(IntakeConstants.LEFT_VERTICAL_SENSOR);
        rightVerticalIntakeSensor = new DigitalInput(IntakeConstants.RIGHT_VERTICAL_SENSOR);


    }


    public void horizontalIntakeOperator() {
        boolean isNote = !getExternalNoteDetector();

        if (isNote) {
            runHorizontalIntake();
        } else {
            stopHorizontalIntake();
        }

        
    }

    public void verticalIntakeOperator() {
        boolean isLeftNote = !getLeftVerticalIntakeSensor();
        boolean isRightNote = !getRightVerticalIntakeSensor();
        
        if (isLeftNote || isRightNote) {
            runVerticalIntake();
        } else {
            stopVerticalIntake();
        }
    }

    public void runHorizontalIntake() {
        leftHorzontialIntakeMotors.set(Relay.Value.kReverse);
        rightHorzontialIntakeMotors.set(Relay.Value.kForward);
    }


    public void runVerticalIntake() {
        verticalIntakeMotors.set(Relay.Value.kForward);
    }

    public void reverseIntake() {
        leftHorzontialIntakeMotors.set(Relay.Value.kForward);
        rightHorzontialIntakeMotors.set(Relay.Value.kReverse);
    }

    public void stopHorizontalIntake() {
        leftHorzontialIntakeMotors.stopMotor();
        rightHorzontialIntakeMotors.stopMotor();
    }
    public void stopVerticalIntake() {
        verticalIntakeMotors.stopMotor();
    }

    public void stop() {
        stopHorizontalIntake();
        stopVerticalIntake();
    }

    public boolean getExternalNoteDetector() {
        return externalNoteDetector.get();
    }

    public boolean getInternalNoteDetector() {
        return internalNoteDetector.get();
    }

    public boolean getLeftVerticalIntakeSensor() {
        return leftVerticalIntakeSensor.get();
    }

    public boolean getRightVerticalIntakeSensor() {
        return rightVerticalIntakeSensor.get();
    }

    @Override
    public void periodic() {
        //I don't love that this is here but I can't get it to work anywhere else 
        verticalIntakeOperator();
        horizontalIntakeOperator();
    }


}