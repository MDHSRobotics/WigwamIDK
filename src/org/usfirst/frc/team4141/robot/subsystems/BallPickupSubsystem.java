package org.usfirst.frc.team4141.robot.subsystems;

import org.usfirst.frc.team4141.MDRobotBase.MDRobotBase;
import org.usfirst.frc.team4141.MDRobotBase.MDSubsystem;
import org.usfirst.frc.team4141.MDRobotBase.config.ConfigSetting;

import edu.wpi.first.wpilibj.SpeedController;


// TODO: configure subsystem in Robot.java and configure buttons

public class BallPickupSubsystem extends MDSubsystem {

	public static String motorCollect="collectBallMotor";
	public static String motorCollect1="collectBallMotor1";
	private double pickupSpeed=-0.5;
	private SpeedController motorController;
	private SpeedController motorController1;
	
	public MDSubsystem configure() {
		super.configure();
		if(getMotors()==null 
				|| !getMotors().containsKey(motorCollect)  || !(getMotors().get(motorCollect) instanceof SpeedController))
			throw new IllegalArgumentException("Invalid motor configuration for ball Pickup system.");
		motorController = (SpeedController)(getMotors().get(motorCollect));
		
		if(getMotors()==null 
				|| !getMotors().containsKey(motorCollect1)  || !(getMotors().get(motorCollect1) instanceof SpeedController))
			throw new IllegalArgumentException("Invalid motor configuration for ball Pickup system.");
		motorController1 = (SpeedController)(getMotors().get(motorCollect1));
		return this;
	}

	
	public BallPickupSubsystem(MDRobotBase robot, String name) {
		super(robot, name);
	}
	
	@Override
	protected void setUp() {
		if(getConfigSettings().containsKey("pickupSpeed")) pickupSpeed = getConfigSettings().get("pickupSpeed").getDouble();
	}

	@Override
	public void settingChangeListener(ConfigSetting changedSetting) {
		if(changedSetting.getName().equals("pickupSpeed")) pickupSpeed = changedSetting.getDouble();
	}

	@Override
	protected void initDefaultCommand() {
		
	}
	
	public void collect(){
		motorController.set(pickupSpeed);
		motorController1.set(pickupSpeed);
	}
	
	public void stop(){
		motorController.stopMotor();
		motorController1.stopMotor();
	}
	
	
	
}
