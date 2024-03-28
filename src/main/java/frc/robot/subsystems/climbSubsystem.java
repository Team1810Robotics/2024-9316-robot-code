package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimbConstants;
import edu.wpi.first.wpilibj.Encoder;


public class climbSubsystem extends SubsystemBase{

    private Victor climbLeftMotor;
    private Victor climbRightMotor;
    private Encoder climbEncoder;
    private double climbDistance;
    private String climbDirection;
   // private Victor climbTensionMotor;

    public climbSubsystem() {
        climbLeftMotor = new Victor(ClimbConstants.CLIMB_LEFT_MOTOR_PORT);
        climbRightMotor = new Victor(ClimbConstants.CLIMB_RIGHT_MOTOR_PORT);
        climbEncoder = new Encoder(ClimbConstants.CLIMB_ENCODER_PORT_A , ClimbConstants.CLIMB_ENCODER_PORT_B);
        climbEncoder.setDistancePerPulse(0.01); //needs tuning
        climbDistance = 0.0;
    
        //climbTensionMotor = new Victor(ClimbConstants.CLIMB_TENSION_MOTOR_PORT);
    }


    public void climbUp() {
        climbLeftMotor.set(1);
        climbRightMotor.set(1);
        climbDistance =  climbEncoder.getDistance();
    }

    public void climbDown() {
        climbLeftMotor.set(-1);
        climbRightMotor.set(-1);
        climbDistance =  climbEncoder.getDistance();
    }

    public void stop() {
        climbLeftMotor.stopMotor();
        climbRightMotor.stopMotor();
    }
    public double getClimbDistance(){
        return climbDistance;
    }
    public void resetClimbDistance(){
        climbDistance = 0.0;
    }
    
    



}