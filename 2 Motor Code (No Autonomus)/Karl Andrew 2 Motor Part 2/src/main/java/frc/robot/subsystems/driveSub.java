// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class driveSub extends SubsystemBase {
  /** Creates a new driveSub. */
  CANSparkMax leftMotor,rightMotor;
  public driveSub() {

leftMotor = new CANSparkMax(Constants.leftMotor,MotorType.kBrushless);
rightMotor = new CANSparkMax(Constants.rightMotor,MotorType.kBrushless);

leftMotor.restoreFactoryDefaults();
rightMotor.restoreFactoryDefaults();

leftMotor.setInverted(false);
rightMotor.setInverted(true);

  }




  public void setPower(double left,double right) {

leftMotor.set(left);
rightMotor.set(right);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}