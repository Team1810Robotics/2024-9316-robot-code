package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;


import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ChurroConstants;

public class ChurroSubsystem extends SubsystemBase {
    
    private VictorSPX ampLinearActuator;


    public ChurroSubsystem() {
       ampLinearActuator = new VictorSPX(ChurroConstants.AMP_BAR);
    }

    public void churroUp() {
        ampLinearActuator.set(VictorSPXControlMode.PercentOutput, .5);
    }

    public void churroDown() {
        ampLinearActuator.set(VictorSPXControlMode.PercentOutput, -.5);
    }

    public void churroStop() {
        ampLinearActuator.set(VictorSPXControlMode.Disabled, 0);
    }

}

