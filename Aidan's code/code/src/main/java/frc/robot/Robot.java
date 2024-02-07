// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
  private final PWMSparkMax m_leftDrive = new PWMSparkMax(0);
  private final PWMSparkMax m_rightDrive = new PWMSparkMax(1);
  private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_leftDrive, m_rightDrive);
  private final Joystick m_stick = new Joystick(0);
  private final Timer m_timer = new Timer();

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */

  // Sparks variables

private CANSparkMax frontLeft;
private CANSparkMax rearLeft;
private CANSparkMax frontRight;
private CANSparkMax rearRight;
private CANSparkMax intake;
private CANSparkMax leftShooter;
private CANSparkMax rightShooter;

//DriveTrain variables
private SpeedControllerGroup left;
private SpeedControllerGroup right;
private SpeedControllerGroup shooter;
private DifferentialDrive robotDrive;

//Movement variables
private double foward;
private double rotate;

//PS4 Variables
private Joystick ps4;
private double leftTrigger;
private double rightTrigger;
private double leftStickX;
private boolean leftBumper;
private boolean rightBumper;
private boolean squareButton;
//private boolean primerButton;
private boolean circleButton;


// Motor OI
private static final int frontRight_ID = 2;
private static final int rearRight_ID = 1;
private static final int frontLeft_ID = 6;
private static final int rearLeft_ID = 5;
private static final int intake_ID = 7; 
private static final int rightShooter_ID = 3;
private static final int LeftShooter_ID = 4;
private static final int feeder_ID = 8; 


private static final MotorType motor_Type = MotorType.kBrushless;

//PS4 OI
private static final int ps4_Port = 0;
private static final int leftTrigger_Axis = 3;
private static final int rightTrigger_Axis = 4;
private static final Hand leftStickX_Hand = Hand.kLeft;
private static final int leftBumper_ID = 5;
private static final int rightBumper_ID = 6;
private static final int squareButton_ID = 1;
//private static final int primerButton_ID = 2;
private static final int circleButton_ID = 3;

// Sensitivity
private static final double cruiseSpeed = 0.35;
private double foward_Sensitivity = cruiseSpeed;

private static final double slowTurn = 0.5;
private double rotate_Sensitivity = slowTurn;

private static final double shooter_Sensitvity = 1;

private final Timer timer = new Timer();

  @Override
  public void robotInit() {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    m_rightDrive.setInverted(true);
    /Assigning Spark ID
frontLeft = new CANSparkMax(frontLeft_ID, motor_Type);
rearLeft = new CANSparkMax(rearLeft_ID, motor_Type);
frontRight = new CANSparkMax(frontRight_ID, motor_Type);
rearRight = new CANSparkMax(rearRight_ID, motor_Type);
intake = new CANSparkMax(intake_ID, motor_Type);
leftShooter = new CANSparkMax(leftShooter_ID, motor_Type);
rightShooter = new CANSparkMax(rightShooter_ID, motor_Type);

// Instantiating Drivetrain
left = new SpeedControllerGroup(frontLeft, rearLeft);
right = new SpeedControllerGroup(frontRight, rearRight);
shooter = new SpeedControllerGroup(leftShooter, rightShooter);

robotDrive = new DifferentialDrive(left, right);

ps4 = new Joystick(ps4_Port);

  }

  /** This function is run once each time the robot enters autonomous mode. */
  @Override
  public void autonomousInit() {
    m_timer.reset();
    m_timer.start();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    // Drive for 2 seconds
    if (m_timer.get() < 2.0) {
      m_robotDrive.arcadeDrive(0.5, 0.0); // drive forwards half speed
    } else {
      m_robotDrive.stopMotor(); // stop robot
    }
  }

  /** This function is called once each time the robot enters teleoperated mode. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during teleoperated mode. */
  @Override
  public void teleopPeriodic() {
    m_robotDrive.arcadeDrive(m_stick.getY(), m_stick.getX());
    // Assign PS4 OI
leftTrigger = ps4.getRawAxis(leftTrigger_Axis);
rightTrigger = ps4.getRawAxis(rightTrigger_Axis);
leftStickX = ps4.getX(leftStickX_Hand);
leftBumper = ps4.getRawButton(leftBumper_ID);
rightBumper = ps4.getRawButton(rightBumper_ID);
squareButton = ps4.getRawButton(squareButton_ID);
//primerButton = ps4.getRawButton(primerButton_ID);
circleButton = ps4.getRawButton(circleButton_ID);


if (rightBumper){
shooter.set(shooter_Sensitvity);
} else{
shooter.stopMotor();
}

//Movement
foward = (rightTrigger - leftTrigger) * foward_Sensitivity;
rotate = leftStickX * rotate_Sensitivity;

  }

  /** This function is called once each time the robot enters test mode. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
